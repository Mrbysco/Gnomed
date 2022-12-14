package com.mrbysco.gnomed;

import com.mrbysco.gnomed.client.ClientHandler;
import com.mrbysco.gnomed.init.GnomeRegistry;
import com.mrbysco.gnomed.init.GnomeSpawning;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		});
	}

	private void addTabContents(final CreativeModeTabEvent.BuildContents event) {
		if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(GnomeRegistry.GNOME_SPAWN_EGG);
		}
		if (event.getTab() == CreativeModeTabs.COMBAT) {
			event.accept(GnomeRegistry.GNOME_SPAWN_EGG);
		}
	}
}
