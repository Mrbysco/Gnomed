package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.config.GnomeConfig;
import com.mrbysco.gnomed.entities.EntityGnome;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GnomeSpawning {

	public static void registerSpawning() 
	{
		if(GnomeConfig.gnome.GnomeSpawning)
		{
			String[] gnomeBiomes = GnomeConfig.gnome.GnomeBiomes;
			if(gnomeBiomes.length > 0) {
				for(String biomeName : gnomeBiomes) {
					ResourceLocation registryLocation = new ResourceLocation(biomeName);
					Biome biome = ForgeRegistries.BIOMES.getValue(registryLocation);
					if(biome != null) {
						EntityRegistry.addSpawn(EntityGnome.class, GnomeConfig.gnome.GnomeWeigth, 1, 4, EnumCreatureType.CREATURE, biome);
					}
				}
			}
		}
	}
}
