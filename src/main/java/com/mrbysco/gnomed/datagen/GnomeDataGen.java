package com.mrbysco.gnomed.datagen;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.datagen.client.GnomeModelProvider;
import com.mrbysco.gnomed.datagen.client.GnomeSoundProvider;
import com.mrbysco.gnomed.datagen.server.GnomeDatapackProvider;
import com.mrbysco.gnomed.datagen.server.GnomeLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class GnomeDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new GnomeLootProvider(packOutput, lookupProvider));
		generator.addProvider(true, new GnomeDatapackProvider(
				packOutput,
				event.getLookupProvider(),
				Set.of(Reference.MOD_ID)
		));

		generator.addProvider(true, new GnomeModelProvider(packOutput));
		generator.addProvider(true, new GnomeSoundProvider(packOutput));
	}
}
