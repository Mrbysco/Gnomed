package com.mrbysco.gnomed.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
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
	public ResourceLocation getTextureLocation(GnomeEntity entity) {
		return texture;
	}

	@Override
	protected void scale(GnomeEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
		matrixStackIn.scale(0.75F,0.75F,0.75F);
	}
}