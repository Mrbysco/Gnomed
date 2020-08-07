package com.mrbysco.gnomed;

import com.mrbysco.gnomed.client.ClientHandler;
import com.mrbysco.gnomed.init.GnomeRegistry;
import com.mrbysco.gnomed.init.GnomeSpawning;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class Gnomed {
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	public Gnomed() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		GnomeRegistry.ITEMS.register(eventBus);
		GnomeRegistry.ENTITIES.register(eventBus);
		GnomeRegistry.SOUND_EVENTS.register(eventBus);

		eventBus.addListener(this::setupCommon);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::doClientStuff);
			eventBus.addListener(ClientHandler::registerItemColors);
		});
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(GnomeSpawning::addSpawn);
	}
}
