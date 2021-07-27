package com.mrbysco.gnomed;

import com.mrbysco.gnomed.client.ClientHandler;
import com.mrbysco.gnomed.init.GnomeRegistry;
import com.mrbysco.gnomed.init.GnomeSpawning;
import com.mrbysco.gnomed.items.CustomSpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
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

		eventBus.addListener(GnomeSpawning::registerEntityAttributes);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		});
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		GnomeSpawning.setupSpawnPlacement();

		event.enqueueWork(() -> {
					for(RegistryObject<Item> registryObject : GnomeRegistry.ITEMS.getEntries()) {
						if(registryObject.get() instanceof CustomSpawnEggItem spawnEgg) {
							SpawnEggItem.BY_ID.put(spawnEgg.entityType.get(), spawnEgg);
						}
					}
				}
		);
	}
}
