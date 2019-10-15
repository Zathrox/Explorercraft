package com.zathrox.explorercraft.client.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class BrushStoogeModel<T extends Entity> extends EntityModel<T> {
    private  RendererModel mobBody;
    private  RendererModel mobHead;
    private  RendererModel grass4;
    private  RendererModel mobLegs;
    private  RendererModel leg;
    private  RendererModel upper;
    private  RendererModel leg5;
    private  RendererModel upper5;
    private  RendererModel leg6;
    private  RendererModel upper6;
    private  RendererModel leg4;
    private  RendererModel upper2;
    private  RendererModel leg2;
    private  RendererModel upper3;
    private  RendererModel leg3;
    private  RendererModel upper4;
    private  RendererModel mobTail;
    private  RendererModel joint1;
    private  RendererModel joint2;
    private  RendererModel joint3;
    private  RendererModel joint4;
    private  RendererModel joint5;
    private  RendererModel grass3;
    private  RendererModel mushroom;
    private  RendererModel grass;
    private RendererModel grass2;

    public BrushStoogeModel() {
        textureWidth = 64;
        textureHeight = 64;

        mobBody = new RendererModel(this);
        mobBody.setRotationPoint(0.0F, 24.0F, 0.0F);
        mobBody.cubeList.add(new ModelBox(mobBody, 0, 0, -3.0F, -7.0F, -4.0F, 6, 5, 8, 0.0F, false));

        mobHead = new RendererModel(this);
        mobHead.setRotationPoint(0.0F, -4.0F, -4.0F);
        mobBody.addChild(mobHead);
        mobHead.cubeList.add(new ModelBox(mobHead, 0, 16, -3.0F, -3.0F, -5.0F, 6, 6, 6, -1.0F, false));

        grass4 = new RendererModel(this);
        grass4.setRotationPoint(-0.75F, -1.5F, -1.5F);
        setRotationAngle(grass4, 0.1745F, -1.0472F, -0.4363F);
        mobHead.addChild(grass4);
        grass4.cubeList.add(new ModelBox(grass4, 38, 43, -2.5F, -2.5F, 0.0F, 5, 5, 0, 0.0F, false));
        grass4.cubeList.add(new ModelBox(grass4, 38, 33, 0.0F, -2.5F, -2.5F, 0, 5, 5, 0.0F, false));

        mobLegs = new RendererModel(this);
        mobLegs.setRotationPoint(-8.0F, -8.0F, 8.0F);
        mobBody.addChild(mobLegs);

        leg = new RendererModel(this);
        leg.setRotationPoint(10.75F, 3.875F, -10.5F);
        mobLegs.addChild(leg);
        leg.cubeList.add(new ModelBox(leg, 42, 4, 0.75F, 1.375F, -0.5F, 1, 3, 1, 0.0F, false));

        upper = new RendererModel(this);
        upper.setRotationPoint(-0.25F, 0.125F, 0.0F);
        setRotationAngle(upper, 0.0F, 0.0F, -0.2618F);
        leg.addChild(upper);
        upper.cubeList.add(new ModelBox(upper, 42, 4, 0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F, false));

        leg5 = new RendererModel(this);
        leg5.setRotationPoint(10.75F, 3.875F, -5.5F);
        mobLegs.addChild(leg5);
        leg5.cubeList.add(new ModelBox(leg5, 42, 4, 0.75F, 1.375F, -0.5F, 1, 3, 1, 0.0F, false));

        upper5 = new RendererModel(this);
        upper5.setRotationPoint(-0.25F, 0.125F, 0.0F);
        setRotationAngle(upper5, 0.0F, 0.0F, -0.2618F);
        leg5.addChild(upper5);
        upper5.cubeList.add(new ModelBox(upper5, 42, 4, 0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F, false));

        leg6 = new RendererModel(this);
        leg6.setRotationPoint(10.75F, 3.875F, -8.0F);
        mobLegs.addChild(leg6);
        leg6.cubeList.add(new ModelBox(leg6, 42, 4, 0.75F, 1.375F, -0.5F, 1, 3, 1, 0.0F, false));

        upper6 = new RendererModel(this);
        upper6.setRotationPoint(-0.25F, 0.125F, 0.0F);
        setRotationAngle(upper6, 0.0F, 0.0F, -0.2618F);
        leg6.addChild(upper6);
        upper6.cubeList.add(new ModelBox(upper6, 42, 4, 0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F, false));

        leg4 = new RendererModel(this);
        leg4.setRotationPoint(5.0F, 3.875F, -10.25F);
        mobLegs.addChild(leg4);
        leg4.cubeList.add(new ModelBox(leg4, 53, 11, -1.5F, 1.375F, -0.75F, 1, 3, 1, 0.0F, false));

        upper2 = new RendererModel(this);
        upper2.setRotationPoint(0.2F, 0.125F, -0.25F);
        setRotationAngle(upper2, 0.0F, 0.0F, 0.2618F);
        leg4.addChild(upper2);
        upper2.cubeList.add(new ModelBox(upper2, 53, 11, -1.2F, 0.0F, -0.5F, 1, 2, 1, 0.0F, false));

        leg2 = new RendererModel(this);
        leg2.setRotationPoint(5.0F, 3.875F, -5.25F);
        mobLegs.addChild(leg2);
        leg2.cubeList.add(new ModelBox(leg2, 53, 11, -1.5F, 1.375F, -0.75F, 1, 3, 1, 0.0F, false));

        upper3 = new RendererModel(this);
        upper3.setRotationPoint(0.2F, 0.125F, -0.25F);
        setRotationAngle(upper3, 0.0F, 0.0F, 0.2618F);
        leg2.addChild(upper3);
        upper3.cubeList.add(new ModelBox(upper3, 53, 11, -1.2F, 0.0F, -0.5F, 1, 2, 1, 0.0F, false));

        leg3 = new RendererModel(this);
        leg3.setRotationPoint(5.0F, 3.875F, -7.75F);
        mobLegs.addChild(leg3);
        leg3.cubeList.add(new ModelBox(leg3, 53, 11, -1.5F, 1.375F, -0.75F, 1, 3, 1, 0.0F, false));

        upper4 = new RendererModel(this);
        upper4.setRotationPoint(0.2F, 0.125F, -0.25F);
        setRotationAngle(upper4, 0.0F, 0.0F, 0.2618F);
        leg3.addChild(upper4);
        upper4.cubeList.add(new ModelBox(upper4, 53, 11, -1.2F, 0.0F, -0.5F, 1, 2, 1, 0.0F, false));

        mobTail = new RendererModel(this);
        mobTail.setRotationPoint(0.0F, -4.0F, 4.0F);
        setRotationAngle(mobTail, 0.5236F, 0.0F, 0.0F);
        mobBody.addChild(mobTail);
        mobTail.cubeList.add(new ModelBox(mobTail, 6, 4, -2.0F, -2.5F, -1.0F, 4, 4, 4, 0.0F, true));

        joint1 = new RendererModel(this);
        joint1.setRotationPoint(0.0F, 0.0F, 3.0F);
        setRotationAngle(joint1, 0.4363F, 0.0F, 0.0F);
        mobTail.addChild(joint1);
        joint1.cubeList.add(new ModelBox(joint1, 7, 4, -1.75F, -1.75F, -0.75F, 3, 3, 4, 0.0F, false));

        joint2 = new RendererModel(this);
        joint2.setRotationPoint(0.0F, 0.0F, 3.0F);
        setRotationAngle(joint2, 0.3491F, 0.0F, 0.0F);
        joint1.addChild(joint2);
        joint2.cubeList.add(new ModelBox(joint2, 4, 36, -1.5157F, -1.2439F, -0.2404F, 3, 2, 3, 0.0F, false));

        joint3 = new RendererModel(this);
        joint3.setRotationPoint(0.0F, -0.5F, 3.0F);
        setRotationAngle(joint3, 0.4363F, 0.0F, 0.0F);
        joint2.addChild(joint3);
        joint3.cubeList.add(new ModelBox(joint3, 40, 3, -1.2657F, -0.9939F, -0.4904F, 2, 2, 2, 0.0F, false));

        joint4 = new RendererModel(this);
        joint4.setRotationPoint(0.0F, -0.5F, 2.0F);
        setRotationAngle(joint4, -1.1345F, 0.0F, 0.0F);
        joint3.addChild(joint4);
        joint4.cubeList.add(new ModelBox(joint4, 41, 6, -1.0157F, -2.7439F, -0.9904F, 2, 3, 2, 0.0F, false));

        joint5 = new RendererModel(this);
        joint5.setRotationPoint(0.0F, -3.0F, 0.0F);
        setRotationAngle(joint5, 2.1817F, 0.0F, 0.0F);
        joint4.addChild(joint5);
        joint5.cubeList.add(new ModelBox(joint5, 56, 6, -0.7657F, -0.2596F, -0.4939F, 1, 1, 3, 0.0F, false));

        grass3 = new RendererModel(this);
        grass3.setRotationPoint(0.0F, -0.75F, 1.75F);
        setRotationAngle(grass3, -1.1345F, -0.6981F, 0.0F);
        joint2.addChild(grass3);
        grass3.cubeList.add(new ModelBox(grass3, 38, 43, -4.25F, -2.9304F, 0.1351F, 5, 5, 0, 0.0F, false));
        grass3.cubeList.add(new ModelBox(grass3, 38, 33, -1.75F, -2.9304F, -2.3649F, 0, 5, 5, 0.0F, false));

        mushroom = new RendererModel(this);
        mushroom.setRotationPoint(2.75F, -6.5833F, 1.75F);
        setRotationAngle(mushroom, 0.0F, 0.0F, 0.2618F);
        mobBody.addChild(mushroom);
        mushroom.cubeList.add(new ModelBox(mushroom, 32, 25, -1.0F, -1.6667F, -0.5F, 1, 2, 1, 0.0F, false));
        mushroom.cubeList.add(new ModelBox(mushroom, 40, 23, -2.5F, -2.6667F, -2.0F, 4, 1, 4, 0.0F, false));
        mushroom.cubeList.add(new ModelBox(mushroom, 40, 18, -2.0F, -3.6667F, -1.5F, 3, 1, 3, 0.0F, false));

        grass = new RendererModel(this);
        grass.setRotationPoint(-1.5F, -8.5F, 2.0F);
        setRotationAngle(grass, 0.1745F, 0.5236F, -0.1745F);
        mobBody.addChild(grass);
        grass.cubeList.add(new ModelBox(grass, 38, 43, -3.0F, -2.75F, -0.75F, 5, 5, 0, 0.0F, false));
        grass.cubeList.add(new ModelBox(grass, 38, 33, -0.5F, -2.75F, -3.25F, 0, 5, 5, 0.0F, false));

        grass2 = new RendererModel(this);
        grass2.setRotationPoint(2.0F, -6.75F, -1.75F);
        setRotationAngle(grass2, 0.1745F, 0.6109F, 0.4363F);
        mobBody.addChild(grass2);
        grass2.cubeList.add(new ModelBox(grass2, 38, 43, -2.5F, -2.5F, 0.0F, 5, 5, 0, 0.0F, false));
        grass2.cubeList.add(new ModelBox(grass2, 38, 33, 0.0F, -2.5F, -2.5F, 0, 5, 5, 0.0F, false));


    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.setModelAttributes(this);
        mobBody.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
        //GlStateManager.translatef(0.0F, 0.001F, 0.0F);


    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);

    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        this.mobHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 200F);
        this.mobHead.rotateAngleX = headPitch * ((float)Math.PI / 200F);
        float f = 0.6F * limbSwingAmount;
        this.leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * f;
        this.leg6.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * f;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.leg5.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * f;
        this.mobTail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.mobTail.rotateAngleZ = MathHelper.cos(limbSwing * 0.2662F) * f;
        this.mushroom.rotateAngleZ = MathHelper.cos(limbSwing * 0.2662F) * f;
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
