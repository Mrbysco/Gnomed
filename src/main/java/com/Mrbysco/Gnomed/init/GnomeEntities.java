package com.Mrbysco.Gnomed.init;

import com.Mrbysco.Gnomed.Gnomed;
import com.Mrbysco.Gnomed.Reference;
import com.Mrbysco.Gnomed.entities.EntityGnome;
import com.Mrbysco.Gnomed.render.RenderGnome;

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
