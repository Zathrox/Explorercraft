package com.zathrox.explorercraft.client.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderreeperModel<T extends Entity> extends EntityModel<T>
{
    ModelRenderer ribLeftTop;
    ModelRenderer ribRightTop;
    ModelRenderer ribLeftMiddle;
    ModelRenderer ribRightMiddle;
    ModelRenderer ribLeftBottom;
    ModelRenderer ribRightBottom;
    ModelRenderer forehead;
    ModelRenderer sideburnLeft;
    ModelRenderer nose;
    ModelRenderer sideburnRight;
    ModelRenderer moustacheLeft;
    ModelRenderer moustacheRight;
    ModelRenderer jowlLeft;
    ModelRenderer jowlRight;
    ModelRenderer chin1;
    ModelRenderer spineFront;
    ModelRenderer spineBack;
    ModelRenderer head;
    ModelRenderer chin2;
    ModelRenderer neck;
    ModelRenderer bodyTop;
    ModelRenderer bodyBottom;
    ModelRenderer frontRightLeg;
    ModelRenderer foreFrontRightLeg;
    ModelRenderer frontLeftLeg;
    ModelRenderer foreFrontLeftLeg;
    ModelRenderer backRightLeg;
    ModelRenderer backForeRightLeg;
    ModelRenderer backLeftLeg;
    ModelRenderer backForeLeftLeg;
    ModelRenderer pecLeft;
    ModelRenderer pecRight;
    ModelRenderer creeperArmor;

    public EnderreeperModel() { this(0.0F); }

    public EnderreeperModel(float scale)
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        ribLeftTop = new ModelRenderer(this, 14, 26);
        ribLeftTop.addBox(0F, 0F, 0F, 4, 1, 1);
        ribLeftTop.setRotationPoint(0F, 6F, -2F);
        ribLeftTop.setTextureSize(64, 64);
        ribLeftTop.mirror = true;
        this.setRotateAngle(ribLeftTop, -0.1745329F, 0F, -0.2443461F);

        this.creeperArmor = new ModelRenderer(this, 32, 0);
        this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale + 0.5F);
        this.creeperArmor.setRotationPoint(0.0F, 6.0F, 0.0F);

        ribRightTop = new ModelRenderer(this, 0, 26);
        ribRightTop.addBox(-4F, 0F, 0F, 4, 1, 1);
        ribRightTop.setRotationPoint(0F, 6F, -2F);
        ribRightTop.setTextureSize(64, 64);
        ribRightTop.mirror = true;
        this.setRotateAngle(ribRightTop, -0.1745329F, 0F, 0.2443461F);

        ribLeftMiddle = new ModelRenderer(this, 14, 28);
        ribLeftMiddle.addBox(0F, 0F, 0F, 4, 1, 1);
        ribLeftMiddle.setRotationPoint(0F, 7F, -2F);
        ribLeftMiddle.setTextureSize(64, 64);
        ribLeftMiddle.mirror = true;
        this.setRotateAngle(ribLeftMiddle, -0.1745329F, 0F, 0.122173F);

        ribRightMiddle = new ModelRenderer(this, 0, 28);
        ribRightMiddle.addBox(-4F, 0F, 0F, 4, 1, 1);
        ribRightMiddle.setRotationPoint(0F, 7F, -2F);
        ribRightMiddle.setTextureSize(64, 64);
        ribRightMiddle.mirror = true;
        this.setRotateAngle(ribRightMiddle, -0.1745329F, 0F, -0.122173F);

        ribLeftBottom = new ModelRenderer(this, 14, 30);
        ribLeftBottom.addBox(0F, 0F, 0F, 4, 1, 1);
        ribLeftBottom.setRotationPoint(0F, 8.5F, -2F);
        ribLeftBottom.setTextureSize(64, 64);
        ribLeftBottom.mirror = true;
        this.setRotateAngle(ribLeftBottom, -0.1745329F, 0F, 0.3665191F);

        ribRightBottom = new ModelRenderer(this, 0, 30);
        ribRightBottom.addBox(-4F, 0F, 0F, 4, 1, 1);
        ribRightBottom.setRotationPoint(0F, 8.5F, -2F);
        ribRightBottom.setTextureSize(64, 64);
        ribRightBottom.mirror = true;
        this.setRotateAngle(ribRightBottom, -0.1745329F, 0F, -0.3665191F);

        forehead = new ModelRenderer(this, 0, 41);
        forehead.addBox(-4F, -8F, -8F, 8, 3, 1);
        forehead.setRotationPoint(0F, -1F, 3F);
        forehead.setTextureSize(64, 64);
        forehead.mirror = true;
        this.setRotateAngle(forehead, 0F, 0F, 0F);

        sideburnLeft = new ModelRenderer(this, 22, 57);
        sideburnLeft.addBox(3F, -6F, -8F, 1, 6, 1);
        sideburnLeft.setRotationPoint(0F, -1F, 3F);
        sideburnLeft.setTextureSize(64, 64);
        sideburnLeft.mirror = true;
        this.setRotateAngle(sideburnLeft, 0F, 0F, 0F);

        nose = new ModelRenderer(this, 10, 55);
        nose.addBox(-1F, -6F, -8F, 2, 3, 1);
        nose.setRotationPoint(0F, -1F, 3F);
        nose.setTextureSize(64, 64);
        nose.mirror = true;
        this.setRotateAngle(nose, 0F, 0F, 0F);

        sideburnRight = new ModelRenderer(this, 0, 57);
        sideburnRight.addBox(-4F, -6F, -8F, 1, 6, 1);
        sideburnRight.setRotationPoint(0F, -1F, 3F);
        sideburnRight.setTextureSize(64, 64);
        sideburnRight.mirror = true;
        this.setRotateAngle(sideburnRight, 0F, 0F, 0F);

        moustacheLeft = new ModelRenderer(this, 17, 59);
        moustacheLeft.addBox(1F, -3F, -8F, 2, 1, 1);
        moustacheLeft.setRotationPoint(0F, -1F, 3F);
        moustacheLeft.setTextureSize(64, 64);
        moustacheLeft.mirror = true;
        this.setRotateAngle(moustacheLeft, 0F, 0F, 0F);

        moustacheRight = new ModelRenderer(this, 5, 59);
        moustacheRight.addBox(-3F, -3F, -8F, 2, 1, 1);
        moustacheRight.setRotationPoint(0F, -1F, 3F);
        moustacheRight.setTextureSize(64, 64);
        moustacheRight.mirror = true;
        this.setRotateAngle(moustacheRight, 0F, 0F, 0F);

        jowlLeft = new ModelRenderer(this, 5, 53);
        jowlLeft.addBox(2F, -3F, -8F, 1, 4, 1);
        jowlLeft.setRotationPoint(0F, -1F, 3F);
        jowlLeft.setTextureSize(64, 64);
        jowlLeft.mirror = true;
        this.setRotateAngle(jowlLeft, 0F, 0F, 0F);

        jowlRight = new ModelRenderer(this, 17, 53);
        jowlRight.addBox(-3F, -3F, -8F, 1, 4, 1);
        jowlRight.setRotationPoint(0F, -1F, 3F);
        jowlRight.setTextureSize(64, 64);
        jowlRight.mirror = true;
        this.setRotateAngle(jowlRight, 0F, 0F, 0F);

        chin1 = new ModelRenderer(this, 10, 60);
        chin1.addBox(-1F, -1F, -8F, 2, 2, 1);
        chin1.setRotationPoint(0F, -1F, 3F);
        chin1.setTextureSize(64, 64);
        chin1.mirror = true;
        this.setRotateAngle(chin1, 0F, 0F, 0F);

        spineFront = new ModelRenderer(this, 10, 26);
        spineFront.addBox(0F, 0F, 2F, 1, 7, 1);
        spineFront.setRotationPoint(-0.5F, 4.6F, -4.4F);
        spineFront.setTextureSize(64, 64);
        spineFront.mirror = true;
        this.setRotateAngle(spineFront, 0.0349066F, 0F, 0F);

        spineBack = new ModelRenderer(this, 24, 26);
        spineBack.addBox(-0.5F, 0F, 0F, 1, 10, 1);
        spineBack.setRotationPoint(0F, 2F, 3.3F);
        spineBack.setTextureSize(64, 64);
        spineBack.mirror = true;
        this.setRotateAngle(spineBack, -0.1919862F, 0F, 0F);

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -7F, 8, 8, 9, scale);
        head.setRotationPoint(0F, -1F, 3F);
        head.mirror = true;

        chin2 = new ModelRenderer(this, 14, 17);
        chin2.addBox(-2F, 0F, -7F, 4, 1, 6);
        chin2.setRotationPoint(0F, -1F, 3F);
        chin2.setTextureSize(64, 64);
        chin2.mirror = true;
        this.setRotateAngle(chin2, 0F, 0F, 0F);

        neck = new ModelRenderer(this, 34, 20);
        neck.addBox(-1.5F, 0F, -1F, 3, 5, 3);
        neck.setRotationPoint(0F, -2F, 3F);
        neck.setTextureSize(64, 64);
        neck.mirror = true;
        this.setRotateAngle(neck, -0.2617994F, 0F, 0F);

        bodyTop = new ModelRenderer(this, 34, 0);
        bodyTop.addBox(-5F, 0F, -2F, 10, 5, 5);
        bodyTop.setRotationPoint(0F, 2F, 1F);
        bodyTop.setTextureSize(64, 64);
        bodyTop.mirror = true;
        this.setRotateAngle(bodyTop, -0.1745329F, 0F, 0F);

        bodyBottom = new ModelRenderer(this, 42, 10);
        bodyBottom.addBox(-3.5F, 0F, -2F, 7, 6, 4);
        bodyBottom.setRotationPoint(0F, 6F, 1F);
        bodyBottom.setTextureSize(64, 64);
        bodyBottom.mirror = true;
        this.setRotateAngle(bodyBottom, -0.1745329F, 0F, 0F);

        frontRightLeg = new ModelRenderer(this, 0, 17);
        frontRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        frontRightLeg.setRotationPoint(-2F, 12F, -1F);
        frontRightLeg.setTextureSize(64, 64);
        frontRightLeg.mirror = true;
        this.setRotateAngle(frontRightLeg, -0.8726646F, 0F, 0.2268928F);

        foreFrontRightLeg = new ModelRenderer(this, 56, 21);
        foreFrontRightLeg.addBox(-2F, 2F, -6F, 2, 10, 2);
        foreFrontRightLeg.setRotationPoint(-2F, 12F, -1F);
        foreFrontRightLeg.setTextureSize(64, 64);
        foreFrontRightLeg.mirror = true;
        this.setRotateAngle(foreFrontRightLeg, 0.1745329F, 0F, -0.0872665F);

        frontLeftLeg = new ModelRenderer(this, 0, 17);
        frontLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        frontLeftLeg.setRotationPoint(2F, 12F, -1F);
        frontLeftLeg.setTextureSize(64, 64);
        frontLeftLeg.mirror = true;
        this.setRotateAngle(frontLeftLeg, -0.8726646F, 0F, -0.2268928F);

        foreFrontLeftLeg = new ModelRenderer(this, 56, 21);
        foreFrontLeftLeg.addBox(0F, 2F, -6F, 2, 10, 2);
        foreFrontLeftLeg.setRotationPoint(2F, 12F, -1F);
        foreFrontLeftLeg.setTextureSize(64, 64);
        foreFrontLeftLeg.mirror = true;
        this.setRotateAngle(foreFrontLeftLeg, 0.1745329F, 0F, 0.0872665F);

        backRightLeg = new ModelRenderer(this, 0, 17);
        backRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        backRightLeg.setRotationPoint(-2F, 11F, 1F);
        backRightLeg.setTextureSize(64, 64);
        backRightLeg.mirror = true;
        this.setRotateAngle(backRightLeg, 0.8726646F, 0F, 0.2268928F);

        backForeRightLeg = new ModelRenderer(this, 56, 22);
        backForeRightLeg.addBox(-2F, 3F, 3F, 2, 10, 2);
        backForeRightLeg.setRotationPoint(-2F, 11F, 1F);
        backForeRightLeg.setTextureSize(64, 64);
        backForeRightLeg.mirror = true;
        this.setRotateAngle(backForeRightLeg, -0.1745329F, 0F, 0F);

        backLeftLeg = new ModelRenderer(this, 0, 17);
        backLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
        backLeftLeg.setRotationPoint(2F, 11F, 1F);
        backLeftLeg.setTextureSize(64, 64);
        backLeftLeg.mirror = true;
        this.setRotateAngle(backLeftLeg, 0.8726646F, 0F, -0.2268928F);

        backForeLeftLeg = new ModelRenderer(this, 56, 22);
        backForeLeftLeg.addBox(4F, 3F, 3F, 2, 10, 2);
        backForeLeftLeg.setRotationPoint(-2F, 11F, 1F);
        backForeLeftLeg.setTextureSize(64, 64);
        backForeLeftLeg.mirror = true;
        this.setRotateAngle(backForeLeftLeg, -0.1745329F, 0F, 0F);

        pecLeft = new ModelRenderer(this, 12, 35);
        pecLeft.addBox(-2F, -1F, 0F, 4, 3, 2);
        pecLeft.setRotationPoint(2.3F, 3F, -2.5F);
        pecLeft.setTextureSize(64, 64);
        pecLeft.mirror = true;
        this.setRotateAngle(pecLeft, -0.1745329F, 0F, 0F);

        pecRight = new ModelRenderer(this, 0, 35);
        pecRight.addBox(-2F, -1F, 0F, 4, 3, 2);
        pecRight.setRotationPoint(-2.3F, 3F, -2.5F);
        pecRight.setTextureSize(64, 64);
        pecRight.mirror = true;
        this.setRotateAngle(pecRight, -0.1745329F, 0F, 0F);
    }

    private void setRotateAngle(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Head Parts
        head.rotateAngleX = netHeadYaw * 0.015453292F;
        head.rotateAngleY = headPitch * 0.008453292F;
        chin1.rotateAngleX = head.rotateAngleX;
        chin1.rotateAngleY = head.rotateAngleY;
        chin2.rotateAngleY = head.rotateAngleY;
        chin2.rotateAngleX = head.rotateAngleX;
        jowlLeft.rotateAngleX = head.rotateAngleX;
        jowlLeft.rotateAngleY = head.rotateAngleY;
        jowlRight.rotateAngleX = head.rotateAngleX;
        jowlRight.rotateAngleY = head.rotateAngleY;
        nose.rotateAngleX = head.rotateAngleX;
        nose.rotateAngleY = head.rotateAngleY;
        sideburnRight.rotateAngleX = head.rotateAngleX;
        sideburnRight.rotateAngleY = head.rotateAngleY;
        sideburnLeft.rotateAngleX = head.rotateAngleX;
        sideburnLeft.rotateAngleY = head.rotateAngleY;
        forehead.rotateAngleX = head.rotateAngleX;
        forehead.rotateAngleY = head.rotateAngleY;
        moustacheLeft.rotateAngleX = head.rotateAngleX;
        moustacheLeft.rotateAngleY = head.rotateAngleY;
        moustacheRight.rotateAngleX = head.rotateAngleX;
        moustacheRight.rotateAngleY = head.rotateAngleY;

        // Leg Parts
        backRightLeg.rotateAngleX = 0.8726646F;
        backRightLeg.rotateAngleX -= MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        backForeRightLeg.rotateAngleX = -0.1745329F;
        backForeRightLeg.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        frontRightLeg.rotateAngleX = -0.8726646F;
        frontRightLeg.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        foreFrontRightLeg.rotateAngleX = 0.1745329F;
        foreFrontRightLeg.rotateAngleX -= MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        frontLeftLeg.rotateAngleX = -0.8726646F;
        frontLeftLeg.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        foreFrontLeftLeg.rotateAngleX = 0.1745329F;
        foreFrontLeftLeg.rotateAngleX -= MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        backLeftLeg.rotateAngleX = 0.8726646F;
        backLeftLeg.rotateAngleX -= MathHelper.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        backForeLeftLeg.rotateAngleX = -0.1745329F;
        backForeLeftLeg.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ribLeftTop.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        ribRightTop.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        ribLeftMiddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        ribRightMiddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        ribLeftBottom.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        ribRightBottom.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        forehead.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        sideburnLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        nose.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        sideburnRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        moustacheLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        moustacheRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        jowlLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        jowlRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        chin1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        spineFront.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        spineBack.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        chin2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        neck.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        bodyTop.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        bodyBottom.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        frontRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        foreFrontRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        frontLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        foreFrontLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        backRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        backForeRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        backLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        backForeLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        pecLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        pecRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}