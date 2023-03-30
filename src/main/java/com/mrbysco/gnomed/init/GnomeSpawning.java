package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.Gnome;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class GnomeSpawning {
	@SubscribeEvent(priority =  EventPriority.HIGH)
	public static void addSpawn(BiomeLoadingEvent event) {
		ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		if(BiomeDictionary.hasType(biomeKey, Type.FOREST) || BiomeDictionary.hasType(biomeKey, Type.JUNGLE)) {
			event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));
		}
	}

	public static void setupSpawnPlacement() {
		SpawnPlacements.register(GnomeRegistry.GNOME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GnomeRegistry.GNOME.get(), Gnome.registerAttributes().build());
	}
}
