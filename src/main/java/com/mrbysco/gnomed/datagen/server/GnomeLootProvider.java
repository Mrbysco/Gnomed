package com.mrbysco.gnomed.datagen.server;

import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContextSource;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GnomeLootProvider extends LootTableProvider {
	public GnomeLootProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Set.of(), List.of(
				new SubProviderEntry(GiveMeTheHat::new, LootContextParamSets.ENTITY)
		), lookupProvider);
	}

	@Override
	protected void validate(WritableRegistry<LootTable> tables, ValidationContextSource validationContext, ProblemReporter.Collector problems) {
		super.validate(tables, validationContext, problems);
	}

	private static class GiveMeTheHat extends EntityLootSubProvider {
		protected GiveMeTheHat(HolderLookup.Provider provider) {
			super(FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.add(GnomeRegistry.GNOME.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(GnomeRegistry.GNOME_HAT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))))));
		}

		@Override
		protected Stream<EntityType<?>> getKnownEntityTypes() {
			return GnomeRegistry.ENTITIES.getEntries().stream().map(Supplier::get);
		}
	}
}
