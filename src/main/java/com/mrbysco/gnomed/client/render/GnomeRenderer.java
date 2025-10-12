package com.mrbysco.gnomed.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.client.ClientHandler;
import com.mrbysco.gnomed.client.models.GnomeModel;
import com.mrbysco.gnomed.client.state.GnomeRenderState;
import com.mrbysco.gnomed.entity.Gnome;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GnomeRenderer extends MobRenderer<Gnome, GnomeRenderState, GnomeModel> {
	private static final ResourceLocation texture = Reference.modLoc("textures/entity/gnome.png");

	public GnomeRenderer(EntityRendererProvider.Context context) {
		super(context, new GnomeModel(context.bakeLayer(ClientHandler.GNOME)), 0.25F);
	}

	@Override
	public GnomeRenderState createRenderState() {
		return new GnomeRenderState();
	}

	@Override
	public void extractRenderState(Gnome gnome, GnomeRenderState state, float partialTick) {
		super.extractRenderState(gnome, state, partialTick);
	}

	@NotNull
	@Override
	public ResourceLocation getTextureLocation(GnomeRenderState state) {
		return texture;
	}

	@Override
	protected void scale(GnomeRenderState state, PoseStack poseStack) {
		super.scale(state, poseStack);
		poseStack.scale(0.75F, 0.75F, 0.75F);
	}
}