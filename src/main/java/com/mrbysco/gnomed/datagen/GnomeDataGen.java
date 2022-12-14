package com.mrbysco.gnomed.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddSpawnsBiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GnomeDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		HolderLookup.Provider provider = getProvider();
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, provider);
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new GnomeLoot(packOutput));

			final HolderLookup.RegistryLookup<Biome> biomeReg = provider.lookupOrThrow(Registries.BIOME);
			final BiomeModifier addSpawn = AddSpawnsBiomeModifier.singleSpawn(
					HolderSet.emptyNamed(biomeReg, BiomeTags.IS_FOREST),
					new SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));

			final BiomeModifier addJungleSpawn = AddSpawnsBiomeModifier.singleSpawn(
					HolderSet.emptyNamed(biomeReg, BiomeTags.IS_JUNGLE),
					new SpawnerData(GnomeRegistry.GNOME.get(), 12, 1, 1));

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
					packOutput, helper, Reference.MOD_ID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS,
					Map.of(new ResourceLocation(Reference.MOD_ID, "add_forest_gnome_spawn"), addSpawn,
							new ResourceLocation(Reference.MOD_ID, "add_jungle_gnome_spawn"), addJungleSpawn
					)
			));
		}
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, $ -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}

	private static class GnomeLoot extends LootTableProvider {
		public GnomeLoot(PackOutput packOutput) {
			super(packOutput, Set.of(), List.of(
					new SubProviderEntry(GiveMeTheHat::new, LootContextParamSets.ENTITY)
			));
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
			map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
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
				return GnomeRegistry.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
			}
		}
	}
}
