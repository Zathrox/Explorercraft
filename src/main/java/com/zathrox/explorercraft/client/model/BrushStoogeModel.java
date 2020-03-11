package com.zathrox.explorercraft.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BrushStoogeModel<T extends LivingEntity> extends EntityModel<T> {
    private ModelRenderer mobBody;
    private ModelRenderer mobHead;
    private ModelRenderer grass4;
    private ModelRenderer grass5;
    private ModelRenderer mobLegs;
    private ModelRenderer leg;
    private ModelRenderer upper;
    private ModelRenderer leg5;
    private ModelRenderer upper5;
    private ModelRenderer leg6;
    private ModelRenderer upper6;
    private ModelRenderer leg4;
    private ModelRenderer upper2;
    private ModelRenderer leg2;
    private ModelRenderer upper3;
    private ModelRenderer leg3;
    private ModelRenderer upper4;
    private ModelRenderer mobTail;
    private ModelRenderer joint1;
    private ModelRenderer joint2;
    private ModelRenderer joint3;
    private ModelRenderer joint4;
    private ModelRenderer joint5;
    private ModelRenderer grass3;
    private ModelRenderer mushroom;
    private ModelRenderer mushroom1;
    private ModelRenderer mushroom2;
    private ModelRenderer grass;
    private ModelRenderer grass1;
    private ModelRenderer grass2;
    private ModelRenderer grass6;
    private ModelRenderer grass7;

    public BrushStoogeModel() {
        super(RenderType::getEntityTranslucent);
        textureWidth = 64;
        textureHeight = 64;

        mobBody = new ModelRenderer(this, 0, 0);
        mobBody.setRotationPoint(0.0F, 24.0F, 0.0F);
        mobBody.addBox(-3.0F, -7.0F, -4.0F, 6, 5, 8, 0.0F, false);

        mobHead = new ModelRenderer(this, 0, 16);
        mobHead.setRotationPoint(0.0F, -4.0F, -4.0F);
        mobBody.addChild(mobHead);
        mobHead.addBox(-3.0F, -3.0F, -5.0F, 6, 6, 6, -1.0F);

        grass4 = new ModelRenderer(this, 38, 43);
        grass4.setRotationPoint(-0.75F, -1.5F, -1.5F);
        setRotationAngle(grass4, 0.1745F, -1.0472F, -0.4363F);
        mobHead.addChild(grass4);
        grass4.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 0, 0.0F);

        grass5 = new ModelRenderer(this, 38, 33);
        grass5.setRotationPoint(-0.75F, -1.5F, -1.5F);
        setRotationAngle(grass5, 0.1745F, -1.0472F, -0.4363F);
        mobHead.addChild(grass5);
        grass5.addBox(0.0F, -2.5F, -2.5F, 0, 5, 5, 0.0F);

        mobLegs = new ModelRenderer(this);
        mobLegs.setRotationPoint(-8.0F, -8.0F, 8.0F);
        mobBody.addChild(mobLegs);

        leg = new ModelRenderer(this, 42, 4);
        leg.setRotationPoint(10.75F, 3.875F, -10.5F);
        mobLegs.addChild(leg);
        leg.addBox(0.75F, 1.375F, -0.5F, 1, 3, 1, 0.0F);

        upper = new ModelRenderer(this, 42, 4);
        upper.setRotationPoint(-0.25F, 0.125F, 0.0F);
        setRotationAngle(upper, 0.0F, 0.0F, -0.2618F);
        leg.addChild(upper);
        upper.addBox(0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

        leg5 = new ModelRenderer(this, 42, 4);
        leg5.setRotationPoint(10.75F, 3.875F, -5.5F);
        mobLegs.addChild(leg5);
        leg5.addBox(0.75F, 1.375F, -0.5F, 1, 3, 1, 0.0F);

        upper5 = new ModelRenderer(this, 42, 4);
        upper5.setRotationPoint(-0.25F, 0.125F, 0.0F);
        setRotationAngle(upper5, 0.0F, 0.0F, -0.2618F);
        leg5.addChild(upper5);
        upper5.addBox(0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

        leg6 = new ModelRenderer(this, 42, 4);
        leg6.setRotationPoint(10.75F, 3.875F, -8.0F);
        mobLegs.addChild(leg6);
        leg6.addBox(0.75F, 1.375F, -0.5F, 1, 3, 1, 0.0F);

        upper6 = new ModelRenderer(this, 42, 4);
        upper6.setRotationPoint(-0.25F, 0.125F, 0.0F);
        setRotationAngle(upper6, 0.0F, 0.0F, -0.2618F);
        leg6.addChild(upper6);
        upper6.addBox(0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

        leg4 = new ModelRenderer(this, 53, 11);
        leg4.setRotationPoint(5.0F, 3.875F, -10.25F);
        mobLegs.addChild(leg4);
        leg4.addBox(-1.5F, 1.375F, -0.75F, 1, 3, 1, 0.0F);

        upper2 = new ModelRenderer(this, 53, 11);
        upper2.setRotationPoint(0.2F, 0.125F, -0.25F);
        setRotationAngle(upper2, 0.0F, 0.0F, 0.2618F);
        leg4.addChild(upper2);
        upper2.addBox(-1.2F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

        leg2 = new ModelRenderer(this, 53, 11);
        leg2.setRotationPoint(5.0F, 3.875F, -5.25F);
        mobLegs.addChild(leg2);
        leg2.addBox(-1.5F, 1.375F, -0.75F, 1, 3, 1, 0.0F);

        upper3 = new ModelRenderer(this, 53, 11);
        upper3.setRotationPoint(0.2F, 0.125F, -0.25F);
        setRotationAngle(upper3, 0.0F, 0.0F, 0.2618F);
        leg2.addChild(upper3);
        upper3.addBox(-1.2F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

        leg3 = new ModelRenderer(this, 53, 11);
        leg3.setRotationPoint(5.0F, 3.875F, -7.75F);
        mobLegs.addChild(leg3);
        leg3.addBox(-1.5F, 1.375F, -0.75F, 1, 3, 1, 0.0F);

        upper4 = new ModelRenderer(this, 53, 11);
        upper4.setRotationPoint(0.2F, 0.125F, -0.25F);
        setRotationAngle(upper4, 0.0F, 0.0F, 0.2618F);
        leg3.addChild(upper4);
        upper4.addBox(-1.2F, 0.0F, -0.5F, 1, 2, 1, 0.0F);

        mobTail = new ModelRenderer(this, 6, 4);
        mobTail.setRotationPoint(0.0F, -4.0F, 4.0F);
        setRotationAngle(mobTail, 0.5236F, 0.0F, 0.0F);
        mobBody.addChild(mobTail);
        mobTail.addBox(-2.0F, -2.5F, -1.0F, 4, 4, 4, 0.0F);

        joint1 = new ModelRenderer(this, 7, 4);
        joint1.setRotationPoint(0.0F, 0.0F, 3.0F);
        setRotationAngle(joint1, 0.4363F, 0.0F, 0.0F);
        mobTail.addChild(joint1);
        joint1.addBox(-1.75F, -1.75F, -0.75F, 3, 3, 4, 0.0F);

        joint2 = new ModelRenderer(this, 4, 36);
        joint2.setRotationPoint(0.0F, 0.0F, 3.0F);
        setRotationAngle(joint2, 0.3491F, 0.0F, 0.0F);
        joint1.addChild(joint2);
        joint2.addBox(-1.5157F, -1.2439F, -0.2404F, 3, 2, 3, 0.0F);

        joint3 = new ModelRenderer(this, 40, 3);
        joint3.setRotationPoint(0.0F, -0.5F, 3.0F);
        setRotationAngle(joint3, 0.4363F, 0.0F, 0.0F);
        joint2.addChild(joint3);
        joint3.addBox(-1.2657F, -0.9939F, -0.4904F, 2, 2, 2, 0.0F);

        joint4 = new ModelRenderer(this, 41, 6);
        joint4.setRotationPoint(0.0F, -0.5F, 2.0F);
        setRotationAngle(joint4, -1.1345F, 0.0F, 0.0F);
        joint3.addChild(joint4);
        joint4.addBox(-1.0157F, -2.7439F, -0.9904F, 2, 3, 2, 0.0F);

        joint5 = new ModelRenderer(this, 56, 6);
        joint5.setRotationPoint(0.0F, -3.0F, 0.0F);
        setRotationAngle(joint5, 2.1817F, 0.0F, 0.0F);
        joint4.addChild(joint5);
        joint5.addBox(-0.7657F, -0.2596F, -0.4939F, 1, 1, 3, 0.0F);

        grass3 = new ModelRenderer(this, 38, 43);
        grass3.setRotationPoint(0.0F, -0.75F, 1.75F);
        setRotationAngle(grass3, -1.1345F, -0.6981F, 0.0F);
        joint2.addChild(grass3);
        grass3.addBox(-4.25F, -2.9304F, 0.1351F, 5, 5, 0, 0.0F);

        grass7 = new ModelRenderer(this, 38, 33);
        grass7.setRotationPoint(0.0F, -0.75F, 1.75F);
        setRotationAngle(grass7, -1.1345F, -0.6981F, 0.0F);
        joint2.addChild(grass7);
        grass7.addBox(-1.75F, -2.9304F, -2.3649F, 0, 5, 5, 0.0F);
        //grass3.addBox(grass3, 38, 33, -1.75F, -2.9304F, -2.3649F, 0, 5, 5, 0.0F);

        mushroom = new ModelRenderer(this, 32, 25);
        mushroom.setRotationPoint(2.75F, -6.5833F, 1.75F);
        setRotationAngle(mushroom, 0.0F, 0.0F, 0.2618F);
        mobBody.addChild(mushroom);
        mushroom.addBox(-1.0F, -1.6667F, -0.5F, 1, 2, 1, 0.0F);

        mushroom1 = new ModelRenderer(this, 40, 23);
        mushroom1.setRotationPoint(2.75F, -6.5833F, 1.75F);
        setRotationAngle(mushroom1, 0.0F, 0.0F, 0.2618F);
        mobBody.addChild(mushroom1);
        mushroom1.addBox(-2.5F, -2.6667F, -2.0F, 4, 1, 4, 0.0F);

        mushroom2 = new ModelRenderer(this, 40, 18);
        mushroom2.setRotationPoint(2.75F, -6.5833F, 1.75F);
        setRotationAngle(mushroom2, 0.0F, 0.0F, 0.2618F);
        mobBody.addChild(mushroom2);
        mushroom2.addBox(-2.0F, -3.6667F, -1.5F, 3, 1, 3, 0.0F);

        //mushroom.addBox(mushroom, 40, 23, -2.5F, -2.6667F, -2.0F, 4, 1, 4, 0.0F, false);
        //mushroom.addBox(mushroom, 40, 18, -2.0F, -3.6667F, -1.5F, 3, 1, 3, 0.0F, false);

        grass = new ModelRenderer(this, 38, 43);
        grass.setRotationPoint(-1.5F, -8.5F, 2.0F);
        setRotationAngle(grass, 0.1745F, 0.5236F, -0.1745F);
        mobBody.addChild(grass);
        grass.addBox(-3.0F, -2.75F, -0.75F, 5, 5, 0, 0.0F);

        grass1 = new ModelRenderer(this, 38, 33);
        grass1.setRotationPoint(-1.5F, -8.5F, 2.0F);
        setRotationAngle(grass1, 0.1745F, 0.5236F, -0.1745F);
        mobBody.addChild(grass1);
        grass1.addBox(-0.5F, -2.75F, -3.25F, 0, 5, 5, 0.0F);
        //grass.addBox(grass, 38, 33, -0.5F, -2.75F, -3.25F, 0, 5, 5, 0.0F, false);

        grass2 = new ModelRenderer(this, 38, 43);
        grass2.setRotationPoint(2.0F, -6.75F, -1.75F);
        setRotationAngle(grass2, 0.1745F, 0.6109F, 0.4363F);
        mobBody.addChild(grass2);
        grass2.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 0, 0.0F);

        grass6 = new ModelRenderer(this, 38, 33);
        grass6.setRotationPoint(2.0F, -6.75F, -1.75F);
        setRotationAngle(grass6, 0.1745F, 0.6109F, 0.4363F);
        mobBody.addChild(grass6);
        grass6.addBox(0.0F, -2.5F, -2.5F, 0, 5, 5, 0.0F);
        //grass2.addBox(grass2, 38, 33, 0.0F, -2.5F, -2.5F, 0, 5, 5, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.mobHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 200F);
        this.mobHead.rotateAngleX = headPitch * ((float) Math.PI / 200F);
        float f = 0.6F * limbSwingAmount;
        this.leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * f;
        this.leg6.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * f;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.leg5.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * f;
        this.mobTail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.mobTail.rotateAngleZ = MathHelper.cos(limbSwing * 0.2662F) * f;
        this.mushroom.rotateAngleZ = MathHelper.cos(limbSwing * 0.2662F) * f;
    }


    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.mobBody.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
