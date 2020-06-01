package com.Mrbysco.Gnomed.render;

import com.Mrbysco.Gnomed.Reference;
import com.Mrbysco.Gnomed.entities.EntityGnome;
import com.Mrbysco.Gnomed.models.ModelGnome;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGnome extends RenderLiving<EntityGnome>
{
	public static final Factory FACTORY = new Factory();
	
	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_PREFIX + "textures/entity/gnome.png");
	
	public RenderGnome(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelGnome(), 0.25F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityGnome entity) {
	  return texture;
	}
	
	@Override
	protected void preRenderCallback(EntityGnome entitylivingbaseIn, float partialTickTime) {
		float f = 0.999F;
		GlStateManager.scale(0.75F,0.75F,0.75F);
	}
	
	public static class Factory implements IRenderFactory<EntityGnome> {
	  @Override
	  public Render<? super EntityGnome> createRenderFor(RenderManager manager) {
	    return new RenderGnome(manager);
	  }
	}
	
}