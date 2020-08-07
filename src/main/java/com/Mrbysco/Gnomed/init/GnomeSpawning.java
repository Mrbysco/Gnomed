package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Gnomed;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.ForgeRegistries;

public class GnomeSpawning {

	public static void addSpawn() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(BiomeDictionary.hasType(biome, Type.FOREST) || BiomeDictionary.hasType(biome, Type.JUNGLE)) {
				biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(GnomeRegistry.GNOME.get(), 12, 1, 1));
			}
		}
		EntitySpawnPlacementRegistry.register(GnomeRegistry.GNOME.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
	}
}
