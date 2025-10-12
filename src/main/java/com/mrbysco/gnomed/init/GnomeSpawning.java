package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.entity.Gnome;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class GnomeSpawning {
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(GnomeRegistry.GNOME.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.AND);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(GnomeRegistry.GNOME.get(), Gnome.registerAttributes().build());
	}
}
