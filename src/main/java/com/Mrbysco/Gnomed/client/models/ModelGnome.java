package com.mrbysco.gnomed.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ModelGnome<T extends MobEntity> extends EntityModel<T> {
    private final ModelRenderer Left_Leg;
    private final ModelRenderer Left_Foot;
    private final ModelRenderer Right_Leg;
    private final ModelRenderer Right_Foot;
    private final ModelRenderer Torso;
    private final ModelRenderer Left_Arm;
    private final ModelRenderer Right_Arm;
    private final ModelRenderer Head;
    private final ModelRenderer Beard;
    private final ModelRenderer Left_Ear;
    private final ModelRenderer Hat_Base;
    private final ModelRenderer Hat_2;
    private final ModelRenderer Right_Ear;
    private final ModelRenderer Nose;

    public ModelGnome() {
        texWidth = 64;
        texHeight = 64;

        Left_Leg = new ModelRenderer(this);
        Left_Leg.setPos(1.5F, 18.0F, 0.0F);
        Left_Leg.texOffs(0, 32).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        Left_Foot = new ModelRenderer(this);
        Left_Foot.setPos(0.0F, 0.0F, 0.0F);
        Left_Leg.addChild(Left_Foot);
        Left_Foot.texOffs(0, 39).addBox(-1.5F, 4.0F, -2.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

        Right_Leg = new ModelRenderer(this);
        Right_Leg.setPos(-1.5F, 18.0F, 0.0F);
        Right_Leg.texOffs(14, 32).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        Right_Foot = new ModelRenderer(this);
        Right_Foot.setPos(0.0F, 0.0F, 0.0F);
        Right_Leg.addChild(Right_Foot);
        Right_Foot.texOffs(14, 39).addBox(-1.5F, 4.0F, -2.5F, 3.0F, 2.0F, 4.0F, 0.0F, false);

        Torso = new ModelRenderer(this);
        Torso.setPos(0.0F, 14.5F, 0.0F);
        Torso.texOffs(0, 21).addBox(-3.0F, -3.5F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);

        Left_Arm = new ModelRenderer(this);
        Left_Arm.setPos(4.0F, 11.0F, 0.0F);
        Left_Arm.texOffs(28, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        Right_Arm = new ModelRenderer(this);
        Right_Arm.setPos(-4.0F, 11.0F, 0.0F);
        Right_Arm.texOffs(36, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        Head = new ModelRenderer(this);
        Head.setPos(0.0F, 9.0F, 0.0F);
        Head.texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        Beard = new ModelRenderer(this);
        Beard.setPos(0.0F, 0.0F, 0.0F);
        Head.addChild(Beard);
        Beard.texOffs(0, 8).addBox(-2.5F, 1.0F, -2.5F, 5.0F, 2.0F, 3.0F, 0.0F, false);

        Left_Ear = new ModelRenderer(this);
        Left_Ear.setPos(0.0F, 0.0F, 0.0F);
        Head.addChild(Left_Ear);
        setRotationAngle(Left_Ear, 0.0F, -0.6517F, -0.2143F);
        Left_Ear.texOffs(0, 13).addBox(0.2F, -0.6F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        Hat_Base = new ModelRenderer(this);
        Hat_Base.setPos(0.0F, 0.0F, 0.0F);
        Head.addChild(Hat_Base);
        setRotationAngle(Hat_Base, -0.1607F, 0.0F, 0.0F);
        Hat_Base.texOffs(16, 0).addBox(-2.5F, -4.2F, -2.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);

        Hat_2 = new ModelRenderer(this);
        Hat_2.setPos(0.0F, -2.3959F, -0.7337F);
        Hat_Base.addChild(Hat_2);
        setRotationAngle(Hat_2, -0.2588F, 0.0F, 0.0F);
        Hat_2.texOffs(36, 0).addBox(-2.0F, -2.6F, -1.8F, 4.0F, 2.0F, 4.0F, 0.0F, false);

        Right_Ear = new ModelRenderer(this);
        Right_Ear.setPos(0.0F, 0.0F, 0.0F);
        Head.addChild(Right_Ear);
        setRotationAngle(Right_Ear, 0.0F, 0.6517F, 0.2143F);
        Right_Ear.texOffs(6, 13).addBox(-2.2F, -0.6F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        Nose = new ModelRenderer(this);
        Nose.setPos(0.0F, 0.0F, 0.0F);
        Head.addChild(Nose);
        setRotationAngle(Nose, 0.0F, 0.0F, 0.0089F);
        Nose.texOffs(0, 16).addBox(-0.5F, 0.0F, -3.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Left_Leg.render(matrixStack, buffer, packedLight, packedOverlay);
        Right_Leg.render(matrixStack, buffer, packedLight, packedOverlay);
        Torso.render(matrixStack, buffer, packedLight, packedOverlay);
        Left_Arm.render(matrixStack, buffer, packedLight, packedOverlay);
        Right_Arm.render(matrixStack, buffer, packedLight, packedOverlay);
        Head.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.Right_Leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.Left_Leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.Right_Leg.yRot = 0.0F;
        this.Left_Leg.yRot = 0.0F;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
