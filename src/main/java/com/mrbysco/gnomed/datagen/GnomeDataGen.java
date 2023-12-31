package com.mrbysco.gnomed.datagen;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.core.Cloner;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddSpawnsBiomeModifier;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GnomeDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new GnomeLoot(packOutput));

			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(GnomeDataGen::getProvider), Set.of(Reference.MOD_ID)));
		}
	}

	public static final ResourceKey<BiomeModifier> ADD_FOREST_GNOME_SPAWN = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
			new ResourceLocation(Reference.MOD_ID, "add_forest_gnome_spawn"));
	public static final ResourceKey<BiomeModifier> ADD_JUNGLE_GNOME_SPAWN = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
			new ResourceLocation(Reference.MOD_ID, "add_jungle_gnome_spawn"));

	private static RegistrySetBuilder.PatchedRegistries getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			var biomeLookup = context.lookup(Registries.BIOME);

			final BiomeModifier addSpawn = AddSpawnsBiomeModifier.singleSpawn(
					biomeLookup.getOrThrow(BiomeTags.IS_FOREST),
					new SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));
			context.register(ADD_FOREST_GNOME_SPAWN, addSpawn);

			final BiomeModifier addJungleSpawn = AddSpawnsBiomeModifier.singleSpawn(
					biomeLookup.getOrThrow(BiomeTags.IS_JUNGLE),
					new SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));
			context.register(ADD_JUNGLE_GNOME_SPAWN, addJungleSpawn);
		});
		// We need the BIOME registry to be present, so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, $ -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		Cloner.Factory cloner$factory = new Cloner.Factory();
		net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions().forEach(data -> data.runWithArguments(cloner$factory::addCodec));
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup(), cloner$factory);
	}

	private static class GnomeLoot extends LootTableProvider {
		public GnomeLoot(PackOutput packOutput) {
			super(packOutput, Set.of(), List.of(
					new SubProviderEntry(GiveMeTheHat::new, LootContextParamSets.ENTITY)
			));
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
			map.forEach((name, table) -> table.validate(validationtracker));
		}

		private static class GiveMeTheHat extends EntityLootSubProvider {
			protected GiveMeTheHat() {
				super(FeatureFlags.REGISTRY.allFlags());
			}

			@Override
			public void generate() {
				this.add(GnomeRegistry.GNOME.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(GnomeRegistry.GNOME_HAT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));
			}

			@Override
			protected Stream<EntityType<?>> getKnownEntityTypes() {
				return GnomeRegistry.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
			}
		}
	}
}
