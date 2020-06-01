package com.Mrbysco.Gnomed.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.Mrbysco.Gnomed.init.GnomeLoot;
import com.Mrbysco.Gnomed.init.GnomeSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGnome extends EntityCreature
{	
	private static final DataParameter<Boolean> GNOMING = EntityDataManager.<Boolean>createKey(EntityGnome.class, DataSerializers.BOOLEAN);

	private boolean isMounted = false;
	private int gnomeTimer = 0;
	
	public EntityGnome(World worldIn) {
		super(worldIn);
        this.setSize(0.5F, 0.8F);
        this.setGnomed(false);
	}
	
	@Override
	protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
    	this.tasks.addTask(5, new EntityAIWander(this, 1.0));
    	this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
        this.tasks.addTask(8, new EntityAIAttackMelee(this, 1.0F, false));
        this.tasks.addTask(10, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataManager.register(GNOMING, Boolean.valueOf(false));
	}

    public void setGnomed(boolean isGnomed)
    {
        this.getDataManager().set(GNOMING, Boolean.valueOf(isGnomed));
    }

    public boolean isGnomed()
    {
        return ((Boolean)this.getDataManager().get(GNOMING)).booleanValue();
    }
    
    @Override
    public void onLivingUpdate() {
    	if(!world.isRemote)
		{
	    	if(isGnomed())
	    	{
	    		gnomeTimer++;
	    		
	    		if(gnomeTimer >= 280)
	    		{
	    			this.setGnomed(false);
	    		}
	    	}
	    	else
	    	{
	    		int randNum = rand.nextInt(10000);
        		if(randNum < 2)
    			{
    	    		if(playerDetection(5))
    	    		{
    					gnomePlayer();
    	    		}
    			}
	    	}
		}
    	super.onLivingUpdate();
    }
    
    @Override
    public void onUpdate() {
    	if (playerDetection(10)) {
			if (isPotionActive(MobEffects.INVISIBILITY)) {
				removePotionEffect(MobEffects.INVISIBILITY);
			}
		} 
    	else 
    	{
			if (!isPotionActive(MobEffects.INVISIBILITY)) {
				addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 480 * 20, 0));
			}
		}
    	
    	super.onUpdate();
    }
    
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		
		AxisAlignedBB hitbox = new AxisAlignedBB(posX - 0.5f, posY - 0.5f, posZ - 0.5f, posX + 0.5f, posY + 0.5f, posZ + 0.5f)
				.expand(-5, -5, -5).expand(5, 5, 5);
		ArrayList<EntityPlayer> nearbyPlayers = new ArrayList<>(world.getEntitiesWithinAABB(EntityPlayer.class, hitbox));
		if(playerDetection(5))
		{
			if(!world.isRemote)
			{
				gnomePlayer();
			}
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	private boolean playerDetection(int range) {
		AxisAlignedBB axisalignedbb = new AxisAlignedBB(posX - 0.5f, posY - 0.5f, posZ - 0.5f, posX + 0.5f, posY + 0.5f, posZ + 0.5f)
				.expand(-range, -range, -range).expand(range, range, range);
		List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);

		return !list.isEmpty();
	}
	
	public void gnomePlayer()
	{
		this.setGnomed(true);
		this.playSound(GnomeSounds.gnome_spawn, 1F, 1F);
	}
	
	@Override
	@Nullable
    protected SoundEvent getAmbientSound()
    {
		if(isGnomed())
		{
			return null;
		}
		else
		{
			return GnomeSounds.gnome_passive;
		}
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return GnomeSounds.gnome_hurt;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return GnomeSounds.gnome_death;
    }
	
	/**
     * Returns the volume for the sounds this mob makes.
     */
	@Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    /**
     * Called when the entity is attacked.
     */
	@Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

	@Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return GnomeLoot.GNOME_LOOT;
    }
}