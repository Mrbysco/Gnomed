package com.Mrbysco.Gnomed.init;

import com.Mrbysco.Gnomed.config.GnomeConfigGen;
import com.Mrbysco.Gnomed.entities.EntityGnome;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class GnomeSpawning {

	public static void registerSpawning() 
	{
		if(GnomeConfigGen.spawning.gnome.GnomeSpawning)
		{
			EntityRegistry.addSpawn(EntityGnome.class, GnomeConfigGen.spawning.gnome.GnomeWeigth, 1, 4, EnumCreatureType.CREATURE, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.MUTATED_FOREST, Biomes.BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.MUTATED_BIRCH_FOREST_HILLS, Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST);
			EntityRegistry.addSpawn(EntityGnome.class, GnomeConfigGen.spawning.gnome.GnomeWeigth, 1, 4, EnumCreatureType.AMBIENT, Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.MUTATED_FOREST, Biomes.BIRCH_FOREST, Biomes.MUTATED_BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.MUTATED_BIRCH_FOREST_HILLS, Biomes.ROOFED_FOREST, Biomes.MUTATED_ROOFED_FOREST);
		}
	}
}
