package com.mrbysco.gnomed.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.GnomeEntity;
import com.mrbysco.gnomed.models.ModelGnome;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGnome extends MobRenderer<GnomeEntity, ModelGnome<GnomeEntity>> {
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_PREFIX + "textures/entity/gnome.png");
	
	public RenderGnome(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ModelGnome(), 0.25F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(GnomeEntity entity) {
	  return texture;
	}

	@Override
	protected void preRenderCallback(GnomeEntity entitylivingbaseIn, float partialTickTime) {
		float f = 0.999F;
		GlStateManager.scalef(0.75F,0.75F,0.75F);
	}
}