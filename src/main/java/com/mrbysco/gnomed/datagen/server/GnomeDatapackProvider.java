package com.mrbysco.gnomed.datagen.server;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GnomeDatapackProvider extends DatapackBuiltinEntriesProvider {
	public static final ResourceKey<BiomeModifier> ADD_FOREST_GNOME_SPAWN = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
			Reference.modLoc("add_forest_gnome_spawn"));
	public static final ResourceKey<BiomeModifier> ADD_JUNGLE_GNOME_SPAWN = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
			Reference.modLoc("add_jungle_gnome_spawn"));

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
				var biomeLookup = context.lookup(Registries.BIOME);

				final BiomeModifier addSpawn = BiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
						biomeLookup.getOrThrow(BiomeTags.IS_FOREST),
						new MobSpawnSettings.SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));
				context.register(ADD_FOREST_GNOME_SPAWN, addSpawn);

				final BiomeModifier addJungleSpawn = BiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
						biomeLookup.getOrThrow(BiomeTags.IS_JUNGLE),
						new MobSpawnSettings.SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));
				context.register(ADD_JUNGLE_GNOME_SPAWN, addJungleSpawn);
			});

	public GnomeDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds) {
		super(output, registries, BUILDER, modIds);
	}
}