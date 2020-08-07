package com.mrbysco.gnomed.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.client.models.ModelGnome;
import com.mrbysco.gnomed.entities.GnomeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class GnomeRenderer extends MobRenderer<GnomeEntity, ModelGnome<GnomeEntity>> {
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_PREFIX + "textures/entity/gnome.png");

	public GnomeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelGnome(), 0.25F);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(GnomeEntity entity) {
		return texture;
	}

	@Override
	protected void preRenderCallback(GnomeEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
		float f = 0.999F;
		GlStateManager.scalef(0.75F,0.75F,0.75F);
	}
}