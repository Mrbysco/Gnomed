package com.mrbysco.gnomed;

import com.mrbysco.gnomed.client.ClientHandler;
import com.mrbysco.gnomed.init.GnomeRegistry;
import com.mrbysco.gnomed.init.GnomeSpawning;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(Reference.MOD_ID)
public class Gnomed {
	public Gnomed() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		GnomeRegistry.ITEMS.register(eventBus);
		GnomeRegistry.ENTITY_TYPES.register(eventBus);
		GnomeRegistry.SOUND_EVENTS.register(eventBus);

		eventBus.addListener(this::addTabContents);

		eventBus.addListener(GnomeSpawning::registerSpawnPlacements);
		eventBus.addListener(GnomeSpawning::registerEntityAttributes);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		}
	}

	private void addTabContents(final BuildCreativeModeTabContentsEvent event) {
		final ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
		if (tabKey == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(GnomeRegistry.GNOME_SPAWN_EGG);
		} else if (tabKey == CreativeModeTabs.COMBAT) {
			event.accept(GnomeRegistry.GNOME_HAT);
		}
	}
}
