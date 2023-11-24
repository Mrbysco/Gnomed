package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.entities.Gnome;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

public class GnomeSpawning {
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(GnomeRegistry.GNOME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GnomeRegistry.GNOME.get(), Gnome.registerAttributes().build());
	}
}
