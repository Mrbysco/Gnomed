package com.mrbysco.gnomed.client;

import com.mrbysco.gnomed.entities.GnomeEntity;
import com.mrbysco.gnomed.items.CustomSpawnEggItem;
import com.mrbysco.gnomed.render.RenderGnome;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(GnomeEntity.class, renderManager -> new RenderGnome(renderManager));
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();

        for(CustomSpawnEggItem item : CustomSpawnEggItem.getEggs()) {
            colors.register((p_198141_1_, p_198141_2_) -> {
                return item.getColor(p_198141_2_);
            }, item);
        }
    }
}
