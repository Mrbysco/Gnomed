package com.mrbysco.gnomed.entities;

import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GnomeEntity extends CreatureEntity {
	public GnomeEntity(EntityType<? extends GnomeEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.registerTargetGoals();
	}

	private void registerTargetGoals() {
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers(GnomeEntity.class));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return GnomeEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.MOVEMENT_SPEED, (double)0.30000001192092896D)
				.add(Attributes.KNOCKBACK_RESISTANCE, (double)0.4D)
				.add(Attributes.FOLLOW_RANGE, (double)30.0D);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
	}
    
    @Override
    public void tick() {
		if(!level.isClientSide) {
			if (playerDetection(level, 10)) {
				if (hasEffect(Effects.INVISIBILITY)) {
					removeEffect(Effects.INVISIBILITY);
				}
			} else {
				if (!hasEffect(Effects.INVISIBILITY)) {
					addEffect(new EffectInstance(Effects.INVISIBILITY, 480 * 20, 0));
				}
			}
		}
    	
    	super.tick();
    }

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if(!worldIn.isClientSide() && playerDetection(worldIn, 5)) {
			this.playSound(GnomeRegistry.GNOME_SPAWN.get(), 1F, 1F);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	private boolean playerDetection(IWorld worldIn, int range) {
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(getX() - 0.5f, getY() - 0.5f, getZ() - 0.5f, getX() + 0.5f, getY() + 0.5f, getZ() + 0.5f)
				.expandTowards(-range, -range, -range).expandTowards(range, range, range);
		return !worldIn.getEntitiesOfClass(PlayerEntity.class, axisalignedbb).isEmpty();
	}

	@Override
	@Nullable
    protected SoundEvent getAmbientSound()
    {
		return GnomeRegistry.GNOME_PASSIVE.get();
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return GnomeRegistry.GNOME_HURT.get();
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return GnomeRegistry.GNOME_DEATH.get();
    }
	
	/**
     * Returns the volume for the sounds this mob makes.
     */
	@Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
}