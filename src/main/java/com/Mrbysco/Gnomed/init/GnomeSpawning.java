package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.GnomeEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class GnomeSpawning {

	@SubscribeEvent(priority =  EventPriority.HIGH)
	public static void addSpawn(BiomeLoadingEvent event) {
		RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
		if(BiomeDictionary.hasType(biomeKey, Type.FOREST) || BiomeDictionary.hasType(biomeKey, Type.JUNGLE)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(GnomeRegistry.GNOME.get(), 12, 1, 1));
		}
	}

	public static void setupSpawnPlacement() {
		EntitySpawnPlacementRegistry.register(GnomeRegistry.GNOME.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
	}

	public static void entityAttributes() {
		GlobalEntityTypeAttributes.put(GnomeRegistry.GNOME.get(), GnomeEntity.registerAttributes().create());
	}
}
