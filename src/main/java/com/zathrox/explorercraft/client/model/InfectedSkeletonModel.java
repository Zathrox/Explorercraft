package com.zathrox.explorercraft.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfectedSkeletonModel<T extends MobEntity & IRangedAttackMob> extends BipedModel<T> {

    private ModelRenderer bipedHeadInner;
    private final ModelRenderer bipedBodyLayer;
    private final ModelRenderer headLeaves;
    private final ModelRenderer headLeaves2;
    private final ModelRenderer rotLeaf2;
    private final ModelRenderer rotLeaf;
    private final ModelRenderer redMushroom;
    private final ModelRenderer redMushroom1;
    private final ModelRenderer redMushroom2;
    private final ModelRenderer legLeaves;
    private final ModelRenderer legrotLeaf2;
    private final ModelRenderer lgroLeaf;

    private final ModelRenderer brown_mushroom;
    private final ModelRenderer brown_mushroom2;
    private ModelRenderer bipedRightArmLayer;


    public InfectedSkeletonModel() {
        this(0.0F, false);
    }

    public InfectedSkeletonModel(float modelSize, boolean armoured) {

        super(modelSize, 0.0F, 64, 32);

        if (!armoured) {
            textureWidth = 64;
            textureHeight = 64;
            this.bipedHeadwear.showModel = false;

            this.bipedBody = new ModelRenderer(this,16, 48);
            this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, modelSize);
            this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

            this.bipedHead = new ModelRenderer(this, 0, 32);
            this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, modelSize);
            this.bipedHead.setRotationPoint(0.0F, 0.0F, modelSize);

            this.bipedRightArm = new ModelRenderer(this, 0, 48);
            this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

            this.bipedLeftArm = new ModelRenderer(this, 0, 48);
            this.bipedLeftArm.mirror = true;
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedRightLeg = new ModelRenderer(this, 0, 48);
            this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);

            this.bipedLeftLeg = new ModelRenderer(this, 8, 48);
            this.bipedLeftLeg.mirror = true;
            this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);

            //- Arm Vines
            this.bipedRightArmLayer = new ModelRenderer(this, 0, 16);
            this.bipedRightArmLayer.addBox(-1.0F, -1.0F, -1.0F, 2, 12, 2, 0.1F);
            this.bipedRightArm.addChild(this.bipedRightArmLayer);

        }

        //- Head Inner
        this.bipedHeadInner = new ModelRenderer(this, 0, 0);
        this.bipedHeadInner.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.25F);
        this.bipedHead.addChild(this.bipedHeadInner);

        //- Red Mushroom
        redMushroom = new ModelRenderer(this,40, 29);
        redMushroom.setRotationPoint(-3.75F, -7.4167F, 3.0F);
        setRotationAngle(redMushroom, 0.0F, 0.0F, -0.4363F);
        bipedHead.addChild(redMushroom);
        redMushroom.addBox(-0.25F, -1.5833F, -0.5F, 1, 2, 1, 0.0F, false);

        redMushroom1 = new ModelRenderer(this,48, 27);
        redMushroom1.setRotationPoint(-3.75F, -7.4167F, 3.0F);
        setRotationAngle(redMushroom1, 0.0F, 0.0F, -0.4363F);
        bipedHead.addChild(redMushroom1);
        redMushroom1.addBox(-1.75F, -2.5833F, -2.0F, 4, 1, 4,  0.0F, false);

        redMushroom2 = new ModelRenderer(this,48, 22);
        redMushroom2.setRotationPoint(-3.75F, -7.4167F, 3.0F);
        setRotationAngle(redMushroom2, 0.0F, 0.0F, -0.4363F);
        bipedHead.addChild(redMushroom2);
        redMushroom2.addBox(-1.25F, -3.0833F, -1.5F, 3, 1, 3, 0.0F, false);

        //- Back Vines
        this.bipedBodyLayer = new ModelRenderer(this, 16, 16);
        this.bipedBodyLayer.addBox(-4.0F, 0F, -1.9F, 8, 12, 4, 0.0F);
        this.bipedBody.addChild(this.bipedBodyLayer);

        //- Head Leaves
        headLeaves = new ModelRenderer(this, 54, 59);
        headLeaves.setRotationPoint(1.6667F, -7.6667F, 1.3333F);
        bipedHead.addChild(headLeaves);
        headLeaves.addBox(-1.6667F, -4.3333F, 0.6667F, 5, 5, 0, 0.0F, false);

        headLeaves2 = new ModelRenderer(this, 54, 54);
        headLeaves2.setRotationPoint(1.6667F, -7.6667F, 1.3333F);
        bipedHead.addChild(headLeaves2);
        headLeaves2.addBox(0.8333F, -4.3333F, -1.8333F, 0, 5, 5, 0.0F, false);

        rotLeaf2 = new ModelRenderer(this,54, 54);
        rotLeaf2.setRotationPoint(0.8333F, -1.8333F, 0.6667F);
        setRotationAngle(rotLeaf2, -0.4363F, 0.5236F, 0.0F);
        headLeaves.addChild(rotLeaf2);
        rotLeaf2.addBox(0.0F, -2.75F, -1.5F, 0, 5, 5, 0.0F, false);

        rotLeaf = new ModelRenderer(this, 54, 54);
        rotLeaf.setRotationPoint(0.8333F, -1.8333F, 0.6667F);
        setRotationAngle(rotLeaf, 0.0F, 2.0944F, 0.0F);
        headLeaves.addChild(rotLeaf);
        rotLeaf.addBox(0.0F, -2.75F, -2.5F, 0, 5, 5, 0.0F, false);

        legLeaves = new ModelRenderer(this,54, 59);
        legLeaves.setRotationPoint(-1.3333F, 11.3333F, -0.6667F);
        bipedRightLeg.addChild(legLeaves);
        legLeaves.addBox(-1.6667F, -4.3333F, 0.6667F, 5, 5, 0, 0.0F, false);
        legLeaves.addBox(0.8333F, -4.3333F, -1.8333F, 0, 5, 5, 0.0F, false);

        legrotLeaf2 = new ModelRenderer(this,54, 54);
        legrotLeaf2.setRotationPoint(0.8333F, -1.8333F, 0.6667F);
        setRotationAngle(legrotLeaf2, -0.4363F, 0.5236F, 0.0F);
        legLeaves.addChild(legrotLeaf2);
        legrotLeaf2.addBox(0.0F, -2.75F, -1.5F, 0, 5, 5, 0.0F, false);

        lgroLeaf = new ModelRenderer(this,54, 54);
        lgroLeaf.setRotationPoint(0.8333F, -1.8333F, 0.6667F);
        setRotationAngle(lgroLeaf, 0.0F, 2.0944F, 0.0F);
        legLeaves.addChild(lgroLeaf);
        lgroLeaf.addBox(0.0F, -2.75F, -2.5F, 0, 5, 5, 0.0F, false);

        brown_mushroom = new ModelRenderer(this,38, 43);
        brown_mushroom.setRotationPoint(-2.0F, 5.0F, 0.0F);
        this.bipedBody.addChild(brown_mushroom);
        brown_mushroom.addBox(-4.0F, 1.0F, -2.0F, 8, 8, 5, -2.5F, false);

        brown_mushroom2 = new ModelRenderer(this,38, 32);
        brown_mushroom2.setRotationPoint(-2.0F, 5.0F, 0.0F);
        this.bipedBody.addChild(brown_mushroom2);
        brown_mushroom2.addBox(-2.5F, 1.0F, -3.5F, 5, 8, 8, -2.5F, false);
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.rightArmPose = BipedModel.ArmPose.EMPTY;
        this.leftArmPose = BipedModel.ArmPose.EMPTY;
        ItemStack itemstack = entityIn.getHeldItem(Hand.MAIN_HAND);
        if (itemstack.getItem() instanceof net.minecraft.item.BowItem && entityIn.isAggressive()) {
            if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
                this.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
            }
        }

        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        ItemStack itemstack = entityIn.getHeldItemMainhand();
        if (entityIn.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof net.minecraft.item.BowItem))) {
            float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
            this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
            this.bipedRightArm.rotateAngleX = (-(float)Math.PI / 2F);
            this.bipedLeftArm.rotateAngleX = (-(float)Math.PI / 2F);
            this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }

    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
        float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(sideIn);
        modelrenderer.rotationPointX += f;
        modelrenderer.translateRotate(matrixStackIn);
        modelrenderer.rotationPointX -= f;
    }
}
