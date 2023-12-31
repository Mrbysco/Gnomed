package com.mrbysco.gnomed.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.client.ClientHandler;
import com.mrbysco.gnomed.client.models.GnomeModel;
import com.mrbysco.gnomed.entities.Gnome;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class GnomeRenderer extends MobRenderer<Gnome, GnomeModel<Gnome>> {
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_PREFIX + "textures/entity/gnome.png");

	public GnomeRenderer(EntityRendererProvider.Context context) {
		super(context, new GnomeModel<>(context.bakeLayer(ClientHandler.GNOME)), 0.25F);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Gnome gnome) {
		return texture;
	}

	@Override
	protected void scale(Gnome gnome, PoseStack poseStack, float partialTickTime) {
		super.scale(gnome, poseStack, partialTickTime);
		poseStack.scale(0.75F, 0.75F, 0.75F);
	}
}