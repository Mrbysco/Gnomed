package com.mrbysco.gnomed.client;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.client.models.GnomeModel;
import com.mrbysco.gnomed.client.render.GnomeRenderer;
import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
    public static final ModelLayerLocation GNOME = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "gnome"), "gnome");

    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GnomeRegistry.GNOME.get(), GnomeRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GNOME, () -> GnomeModel.createMesh());
    }
}
