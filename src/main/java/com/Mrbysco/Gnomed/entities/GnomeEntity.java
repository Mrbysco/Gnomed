package com.mrbysco.gnomed.entities;

import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
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
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setCallsForHelp(GnomeEntity.class));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);

		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}

	@Override
	protected void registerData() {
		super.registerData();
	}
    
    @Override
    public void tick() {
		if(!world.isRemote) {
			if (playerDetection(world, 10)) {
				if (isPotionActive(Effects.INVISIBILITY)) {
					removePotionEffect(Effects.INVISIBILITY);
				}
			} else {
				if (!isPotionActive(Effects.INVISIBILITY)) {
					addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 480 * 20, 0));
				}
			}
		}
    	
    	super.tick();
    }

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if(!worldIn.isRemote() && playerDetection(worldIn, 5)) {
			this.playSound(GnomeRegistry.GNOME_SPAWN.get(), 1F, 1F);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	private boolean playerDetection(IWorld worldIn, int range) {
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(getPosX() - 0.5f, getPosY() - 0.5f, getPosZ() - 0.5f, getPosX() + 0.5f, getPosY() + 0.5f, getPosZ() + 0.5f)
				.expand(-range, -range, -range).expand(range, range, range);
		return !worldIn.getEntitiesWithinAABB(PlayerEntity.class, axisalignedbb).isEmpty();
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