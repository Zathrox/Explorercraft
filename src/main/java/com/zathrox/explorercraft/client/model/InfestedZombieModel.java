package com.zathrox.explorercraft.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfestedZombieModel <T extends MonsterEntity> extends BipedModel<T> {

    private  RendererModel bipedHeadInner;
    private  RendererModel leaves;
    private  RendererModel headLeaf;
    private  RendererModel headLeaf2;
    private  RendererModel headLeaf3;
    private  RendererModel headLeaf4;
    private  RendererModel brownMushroom;
    private  RendererModel bMushroom1;
    private  RendererModel bMushroom2;
    private  RendererModel redMushroom;
    private  RendererModel rMushroom1;
    private  RendererModel rMushroom2;
    private  RendererModel brownMushroom2;
    private  RendererModel bMushroom3;
    private  RendererModel bMushroom4;
    private  RendererModel brownMushroom3;
    private  RendererModel bMushroom5;
    private  RendererModel bMushroom6;
    private RendererModel bipedBodyLayer;
    private RendererModel bipedRightLayer;

    public InfestedZombieModel() {
        this(0.0F, false);
    }

    public InfestedZombieModel(float modelSize, boolean armoured) {
        super(modelSize, 0.0F, 64,  armoured? 32 : 64);


        if (!armoured) {
            textureWidth = 64;
            textureHeight = 64;
            this.bipedHeadwear.showModel = false;

            this.bipedBody = new RendererModel(this,16, 16);
            this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, modelSize);
            this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

            this.bipedHead = new RendererModel(this, 0, 0);
            this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize);
            this.bipedHead.setRotationPoint(0.0F, 0.0F, modelSize);

            this.bipedRightArm = new RendererModel(this, 40, 16);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + 0.0f, 0.0F);
            this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, modelSize);

            this.bipedLeftArm = new RendererModel(this, 40, 32);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + 0.0f, 0.0F);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, modelSize);

            this.bipedRightLeg = new RendererModel(this, 0, 16);
            this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + 0.0f, 0.0F);
            this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);

            this.bipedLeftLeg = new RendererModel(this, 0, 32);
            this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + 0.0f, 0.0F);
            this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, modelSize);

            this.bipedRightLayer = new RendererModel(this);
            this.bipedRightLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.bipedRightArm.addChild(bipedRightLayer);
            this.bipedRightLayer.cubeList.add(new ModelBox(bipedRightLayer, 18, 32, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.1F, false));

            this.leaves = new RendererModel(this);
            this.leaves.setRotationPoint(0.0F, 21.0F, 0.0F);
            this.bipedHead.addChild(leaves);

            this.headLeaf = new RendererModel(this);
            this.headLeaf.setRotationPoint(-8.0F, -8.0F, 8.0F);
            this.leaves.addChild(headLeaf);
            this.headLeaf.cubeList.add(new ModelBox(headLeaf, 32, 59, 6.5357F, -24.5F, -7.2857F, 5, 5, 0, 0.0F, false));

            this.headLeaf2 = new RendererModel(this);
            this.headLeaf2.setRotationPoint(1.0357F, -29.5F, 0.7143F);
            this.setRotationAngle(headLeaf2, 0.0F, 1.5708F, 0.0F);
            this.leaves.addChild(headLeaf2);
            this.headLeaf2.cubeList.add(new ModelBox(headLeaf2, 32, 54, -2.5F, -2.5F, 0.0F, 5, 5, 0, 0.0F, false));

            this.headLeaf3 = new RendererModel(this);
            this.headLeaf3.setRotationPoint(1.0357F, -29.5F, 0.7143F);
            this.setRotationAngle(headLeaf3, 0.0F, 0.6981F, 0.0F);
            this.leaves.addChild(headLeaf3);
            this.headLeaf3.cubeList.add(new ModelBox(headLeaf3, 32, 54, -2.5F, -2.0F, 0.0F, 5, 5, 0, 0.0F, false));

            this.headLeaf4 = new RendererModel(this);
            this.headLeaf4.setRotationPoint(1.0357F, -29.5F, 0.7143F);
            this.setRotationAngle(headLeaf4, 0.0F, 2.1817F, 0.0F);
            this.leaves.addChild(headLeaf4);
            this.headLeaf4.cubeList.add(new ModelBox(headLeaf4, 32, 54, -2.5F, -3.0F, 0.0F, 5, 5, 0, 0.0F, false));

            this.brownMushroom = new RendererModel(this);
            this.brownMushroom.setRotationPoint(-1.5F, -7.0F, -1.5F);
            this.bipedHead.addChild(brownMushroom);

            this.bMushroom1 = new RendererModel(this);
            this.bMushroom1.setRotationPoint(0.0F, -4.0F, -9.0F);
            this.brownMushroom.addChild(bMushroom1);
            this.bMushroom1.cubeList.add(new ModelBox(bMushroom1, 40, 44, -4.0F, -2.0F, 7.0F, 8, 8, 4, -2.0F, false));

            this.bMushroom2 = new RendererModel(this);
            this.bMushroom2.setRotationPoint(0.0F, -4.0F, -9.0F);
            this.setRotationAngle(bMushroom2, 0.0F, 1.5708F, 0.0F);
            this.brownMushroom.addChild(bMushroom2);
            this.bMushroom2.cubeList.add(new ModelBox(bMushroom2, 40, 44, -13.0F, -2.0F, -2.0F, 8, 8, 4, -2.0F, false));


            redMushroom = new RendererModel(this);
            redMushroom.setRotationPoint(2.25F, -1.75F, 0.5F);
            setRotationAngle(redMushroom, 0.2618F, 0.0F, 0.0873F);
            bipedLeftArm.addChild(redMushroom);

            rMushroom1 = new RendererModel(this);
            rMushroom1.setRotationPoint(0.0F, -4.0F, -9.0F);
            redMushroom.addChild(rMushroom1);
            rMushroom1.cubeList.add(new ModelBox(rMushroom1, 40, 52, -4.0F, -2.0F, 7.0F, 8, 8, 4, -2.0F, false));

            rMushroom2 = new RendererModel(this);
            rMushroom2.setRotationPoint(0.0F, -4.0F, -9.0F);
            setRotationAngle(rMushroom2, 0.0F, 1.5708F, 0.0F);
            redMushroom.addChild(rMushroom2);
            rMushroom2.cubeList.add(new ModelBox(rMushroom2, 40, 52, -13.0F, -2.0F, -2.0F, 8, 8, 4, -2.0F, false));

            brownMushroom2 = new RendererModel(this);
            brownMushroom2.setRotationPoint(3.75F, -1.75F, -1.5F);
            setRotationAngle(brownMushroom2, 0.5236F, 0.0F, 0.6109F);
            bipedLeftArm.addChild(brownMushroom2);

            bMushroom3 = new RendererModel(this);
            bMushroom3.setRotationPoint(0.0F, -1.0F, 0.0F);
            brownMushroom2.addChild(bMushroom3);
            bMushroom3.cubeList.add(new ModelBox(bMushroom3, 36, 42, -3.8566F, -1.8226F, -3.1024F, 8, 8, 6, -3.0F, false));

            bMushroom4 = new RendererModel(this);
            bMushroom4.setRotationPoint(1.0F, -1.0F, 1.0F);
            setRotationAngle(bMushroom4, 0.0F, 1.5708F, 0.0F);
            brownMushroom2.addChild(bMushroom4);
            bMushroom4.cubeList.add(new ModelBox(bMushroom4, 36, 42, -2.8976F, -1.8226F, -3.8566F, 8, 8, 6, -3.0F, false));

            brownMushroom3 = new RendererModel(this);
            brownMushroom3.setRotationPoint(0.75F, -1.75F, -1.5F);
            setRotationAngle(brownMushroom3, 0.6109F, 0.0F, 0.0F);
            bipedLeftArm.addChild(brownMushroom3);

            bMushroom5 = new RendererModel(this);
            bMushroom5.setRotationPoint(0.0F, -1.0F, 0.0F);
            brownMushroom3.addChild(bMushroom5);
            bMushroom5.cubeList.add(new ModelBox(bMushroom5, 36, 42, -3.8566F, -3.8226F, -3.1024F, 8, 8, 6, -3.0F, false));

            bMushroom6 = new RendererModel(this);
            bMushroom6.setRotationPoint(1.0F, -1.0F, 1.0F);
            setRotationAngle(bMushroom6, 0.0F, 1.5708F, 0.0F);
            brownMushroom3.addChild(bMushroom6);
            bMushroom6.cubeList.add(new ModelBox(bMushroom6, 36, 42, -2.8976F, -3.8226F, -3.8566F, 8, 8, 6, -3.0F, false));

            //- Back Vines
            this.bipedBodyLayer = new RendererModel(this, 16, 32);
            this.bipedBodyLayer.addBox(-4.0F, 0F, -2.0F, 8, 12, 4, 0.1F);
            this.bipedBody.addChild(this.bipedBodyLayer);

            //- Head Inner
            this.bipedHeadInner = new RendererModel(this, 0, 48);
            this.bipedHeadInner.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.2F);
            this.bipedHead.addChild(this.bipedHeadInner);

        } else {

            //- Head Inner
            this.bipedHeadInner = new RendererModel(this, 0, 0);
            this.bipedHeadInner.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.25F);
            this.bipedHead.addChild(this.bipedHeadInner);
        }





    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        boolean flag = this.func_212850_a_(entityIn);
        float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
        this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
        float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
        this.bipedRightArm.rotateAngleX = f2;
        this.bipedLeftArm.rotateAngleX = f2;
        this.bipedRightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.bipedLeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    public boolean func_212850_a_(MonsterEntity p_212850_1_) {
        return false;
    }

    public void setRotationAngle(RendererModel rendererModel, float x, float y, float z) {
        rendererModel.rotateAngleX = x;
        rendererModel.rotateAngleY = y;
        rendererModel.rotateAngleZ = z;
    }

}
