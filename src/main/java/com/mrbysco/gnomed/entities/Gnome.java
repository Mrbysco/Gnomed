package com.mrbysco.gnomed.entities;

import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;

public class Gnome extends PathfinderMob {
	public Gnome(EntityType<? extends Gnome> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.registerTargetGoals();
	}

	private void registerTargetGoals() {
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers(Gnome.class));
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Gnome.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.30000001192092896D)
				.add(Attributes.KNOCKBACK_RESISTANCE, (double) 0.4D)
				.add(Attributes.FOLLOW_RANGE, (double) 30.0D);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	@Override
	public void tick() {
		if (!level.isClientSide) {
			if (playerDetection(level, 10)) {
				if (hasEffect(MobEffects.INVISIBILITY)) {
					removeEffect(MobEffects.INVISIBILITY);
				}
			} else {
				if (!hasEffect(MobEffects.INVISIBILITY)) {
					addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 480 * 20, 0));
				}
			}
		}

		super.tick();
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		if (!worldIn.isClientSide() && playerDetection(worldIn, 5)) {
			this.playSound(GnomeRegistry.GNOME_SPAWN.get(), 1F, 1F);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	private boolean playerDetection(LevelAccessor worldIn, int range) {
		AABB axisalignedbb = new AABB(getX() - 0.5f, getY() - 0.5f, getZ() - 0.5f, getX() + 0.5f, getY() + 0.5f, getZ() + 0.5f)
				.expandTowards(-range, -range, -range).expandTowards(range, range, range);
		return !worldIn.getEntitiesOfClass(Player.class, axisalignedbb).isEmpty();
	}

	@Override
	@Nullable
	protected SoundEvent getAmbientSound() {
		return GnomeRegistry.GNOME_PASSIVE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return GnomeRegistry.GNOME_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return GnomeRegistry.GNOME_DEATH.get();
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}
}