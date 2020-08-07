package com.mrbysco.gnomed.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelGnome - Mrbysco
 * Created using Tabula 7.0.0
 */
public class ModelGnome<T extends Entity> extends EntityModel<T> {
    public RendererModel Left_Leg;
    public RendererModel Right_Leg;
    public RendererModel Torso;
    public RendererModel Left_Arm;
    public RendererModel Right_Arm;
    public RendererModel Head;
    public RendererModel Left_Foot;
    public RendererModel Right_Foot;
    public RendererModel Beard;
    public RendererModel Nose;
    public RendererModel Left_Ear;
    public RendererModel Right_Ear;
    public RendererModel Hat_Base;
    public RendererModel Hat_2;

    public ModelGnome() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Right_Arm = new RendererModel(this, 36, 32);
        this.Right_Arm.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.Right_Arm.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.Left_Foot = new RendererModel(this, 0, 39);
        this.Left_Foot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Left_Foot.addBox(-1.5F, 4.0F, -2.5F, 3, 2, 4, 0.0F);
        this.Right_Foot = new RendererModel(this, 14, 39);
        this.Right_Foot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Right_Foot.addBox(-1.5F, 4.0F, -2.5F, 3, 2, 4, 0.0F);
        this.Left_Arm = new RendererModel(this, 28, 32);
        this.Left_Arm.setRotationPoint(4.0F, 11.0F, 0.0F);
        this.Left_Arm.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.Hat_2 = new RendererModel(this, 36, 0);
        this.Hat_2.setRotationPoint(0.0F, -2.6F, -0.2F);
        this.Hat_2.addBox(-2.0F, -2.6F, -1.8F, 4, 2, 4, 0.0F);
        this.setRotateAngle(Hat_2, -0.25883232807075907F, 0.0F, 0.0F);
        this.Beard = new RendererModel(this, 0, 8);
        this.Beard.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beard.addBox(-2.5F, 1.0F, -2.5F, 5, 2, 3, 0.0F);
        this.Left_Ear = new RendererModel(this, 0, 13);
        this.Left_Ear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Left_Ear.addBox(0.2F, -0.6F, -2.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(Left_Ear, 0.0F, -0.6517059426946827F, -0.21432643214490363F);
        this.Head = new RendererModel(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.Head.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
        this.Hat_Base = new RendererModel(this, 16, 0);
        this.Hat_Base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat_Base.addBox(-2.5F, -4.2F, -2.5F, 5, 3, 5, 0.0F);
        this.setRotateAngle(Hat_Base, -0.16074482410867777F, 0.0F, 0.0F);
        this.Right_Ear = new RendererModel(this, 6, 13);
        this.Right_Ear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Right_Ear.addBox(-2.2F, -0.6F, -2.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(Right_Ear, 0.0F, 0.6517059426946827F, 0.21432643214490363F);
        this.Left_Leg = new RendererModel(this, 0, 32);
        this.Left_Leg.setRotationPoint(1.5F, 18.0F, 0.0F);
        this.Left_Leg.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.Right_Leg = new RendererModel(this, 14, 32);
        this.Right_Leg.setRotationPoint(-1.5F, 18.0F, 0.0F);
        this.Right_Leg.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.Nose = new RendererModel(this, 0, 16);
        this.Nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Nose.addBox(-0.5F, 0.0F, -3.3F, 1, 1, 2, 0.0F);
        this.setRotateAngle(Nose, 0.0F, 0.0F, 0.008901179185171082F);
        this.Torso = new RendererModel(this, 0, 21);
        this.Torso.setRotationPoint(0.0F, 14.5F, 0.0F);
        this.Torso.addBox(-3.0F, -3.5F, -2.0F, 6, 7, 4, 0.0F);
        this.Left_Leg.addChild(this.Left_Foot);
        this.Right_Leg.addChild(this.Right_Foot);
        this.Hat_Base.addChild(this.Hat_2);
        this.Head.addChild(this.Beard);
        this.Head.addChild(this.Left_Ear);
        this.Head.addChild(this.Hat_Base);
        this.Head.addChild(this.Right_Ear);
        this.Head.addChild(this.Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Right_Arm.render(f5);
        this.Left_Arm.render(f5);
        this.Head.render(f5);
        this.Left_Leg.render(f5);
        this.Right_Leg.render(f5);
        this.Torso.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        this.Right_Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.Left_Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.Right_Leg.rotateAngleY = 0.0F;
        this.Left_Leg.rotateAngleY = 0.0F;
    }
}
