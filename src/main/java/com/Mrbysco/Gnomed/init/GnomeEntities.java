package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Gnomed;
import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.EntityGnome;
import com.mrbysco.gnomed.render.RenderGnome;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GnomeEntities {

	static int ID = 0;
	
	public static void register() 
	{
		registerEntity("gnome", EntityGnome.class, "Gnome", 80, 3, true, 1189750, 13442571);
	}

	@SideOnly(Side.CLIENT)
	public static void RegisterEntityRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGnome.class, RenderGnome.FACTORY);
	}
	
	public static void registerEntity(String registryName, Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) 
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, registryName), entityClass, Reference.MOD_PREFIX + entityName, ID, Gnomed.instance, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary);
		ID++;
	}
}
