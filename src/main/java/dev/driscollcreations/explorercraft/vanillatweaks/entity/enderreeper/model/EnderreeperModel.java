//package dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.model;
//
//
//import com.mojang.blaze3d.matrix.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.renderer.entity.model.EntityModel;
//import net.minecraft.client.renderer.model.ModelPart;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.Math;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class EnderreeperModel<T extends Entity> extends EntityModel<T> {
//    ModelPart ribLeftTop;
//    ModelPart ribRightTop;
//    ModelPart ribLeftMiddle;
//    ModelPart ribRightMiddle;
//    ModelPart ribLeftBottom;
//    ModelPart ribRightBottom;
//    ModelPart forehead;
//    ModelPart sideburnLeft;
//    ModelPart nose;
//    ModelPart sideburnRight;
//    ModelPart moustacheLeft;
//    ModelPart moustacheRight;
//    ModelPart jowlLeft;
//    ModelPart jowlRight;
//    ModelPart chin1;
//    ModelPart spineFront;
//    ModelPart spineBack;
//    ModelPart head;
//    ModelPart chin2;
//    ModelPart neck;
//    ModelPart bodyTop;
//    ModelPart bodyBottom;
//    ModelPart frontRightLeg;
//    ModelPart foreFrontRightLeg;
//    ModelPart frontLeftLeg;
//    ModelPart foreFrontLeftLeg;
//    ModelPart backRightLeg;
//    ModelPart backForeRightLeg;
//    ModelPart backLeftLeg;
//    ModelPart backForeLeftLeg;
//    ModelPart pecLeft;
//    ModelPart pecRight;
//    ModelPart creeperArmor;
//
//    public EnderreeperModel() {
//        this(0.0F);
//    }
//
//    public EnderreeperModel(float scale) {
//        this.texWidth = 64;
//        this.texHeight = 64;
//        ribLeftTop = new ModelPart(this, 14, 26);
//        ribLeftTop.addBox(0F, 0F, 0F, 4, 1, 1);
//        ribLeftTop.setPos(0F, 6F, -2F);
//        ribLeftTop.texOffs(64, 64);
//        ribLeftTop.mirror = true;
//        this.setRotateAngle(ribLeftTop, -0.1745329F, 0F, -0.2443461F);
//
//        this.creeperArmor = new ModelPart(this, 32, 0);
//        this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, scale + 0.5F);
//        this.creeperArmor.setPos(0.0F, 6.0F, 0.0F);
//
//        ribRightTop = new ModelPart(this, 0, 26);
//        ribRightTop.addBox(-4F, 0F, 0F, 4, 1, 1);
//        ribRightTop.setPos(0F, 6F, -2F);
//        ribRightTop.texOffs(64, 64);
//        ribRightTop.mirror = true;
//        this.setRotateAngle(ribRightTop, -0.1745329F, 0F, 0.2443461F);
//
//        ribLeftMiddle = new ModelPart(this, 14, 28);
//        ribLeftMiddle.addBox(0F, 0F, 0F, 4, 1, 1);
//        ribLeftMiddle.setPos(0F, 7F, -2F);
//        ribLeftMiddle.texOffs(64, 64);
//        ribLeftMiddle.mirror = true;
//        this.setRotateAngle(ribLeftMiddle, -0.1745329F, 0F, 0.122173F);
//
//        ribRightMiddle = new ModelPart(this, 0, 28);
//        ribRightMiddle.addBox(-4F, 0F, 0F, 4, 1, 1);
//        ribRightMiddle.setPos(0F, 7F, -2F);
//        ribRightMiddle.texOffs(64, 64);
//        ribRightMiddle.mirror = true;
//        this.setRotateAngle(ribRightMiddle, -0.1745329F, 0F, -0.122173F);
//
//        ribLeftBottom = new ModelPart(this, 14, 30);
//        ribLeftBottom.addBox(0F, 0F, 0F, 4, 1, 1);
//        ribLeftBottom.setPos(0F, 8.5F, -2F);
//        ribLeftBottom.texOffs(64, 64);
//        ribLeftBottom.mirror = true;
//        this.setRotateAngle(ribLeftBottom, -0.1745329F, 0F, 0.3665191F);
//
//        ribRightBottom = new ModelPart(this, 0, 30);
//        ribRightBottom.addBox(-4F, 0F, 0F, 4, 1, 1);
//        ribRightBottom.setPos(0F, 8.5F, -2F);
//        ribRightBottom.texOffs(64, 64);
//        ribRightBottom.mirror = true;
//        this.setRotateAngle(ribRightBottom, -0.1745329F, 0F, -0.3665191F);
//
//        forehead = new ModelPart(this, 0, 41);
//        forehead.addBox(-4F, -8F, -8F, 8, 3, 1);
//        forehead.setPos(0F, -1F, 3F);
//        forehead.texOffs(64, 64);
//        forehead.mirror = true;
//        this.setRotateAngle(forehead, 0F, 0F, 0F);
//
//        sideburnLeft = new ModelPart(this, 22, 57);
//        sideburnLeft.addBox(3F, -6F, -8F, 1, 6, 1);
//        sideburnLeft.setPos(0F, -1F, 3F);
//        sideburnLeft.texOffs(64, 64);
//        sideburnLeft.mirror = true;
//        this.setRotateAngle(sideburnLeft, 0F, 0F, 0F);
//
//        nose = new ModelPart(this, 10, 55);
//        nose.addBox(-1F, -6F, -8F, 2, 3, 1);
//        nose.setPos(0F, -1F, 3F);
//        nose.texOffs(64, 64);
//        nose.mirror = true;
//        this.setRotateAngle(nose, 0F, 0F, 0F);
//
//        sideburnRight = new ModelPart(this, 0, 57);
//        sideburnRight.addBox(-4F, -6F, -8F, 1, 6, 1);
//        sideburnRight.setPos(0F, -1F, 3F);
//        sideburnRight.texOffs(64, 64);
//        sideburnRight.mirror = true;
//        this.setRotateAngle(sideburnRight, 0F, 0F, 0F);
//
//        moustacheLeft = new ModelPart(this, 17, 59);
//        moustacheLeft.addBox(1F, -3F, -8F, 2, 1, 1);
//        moustacheLeft.setPos(0F, -1F, 3F);
//        moustacheLeft.texOffs(64, 64);
//        moustacheLeft.mirror = true;
//        this.setRotateAngle(moustacheLeft, 0F, 0F, 0F);
//
//        moustacheRight = new ModelPart(this, 5, 59);
//        moustacheRight.addBox(-3F, -3F, -8F, 2, 1, 1);
//        moustacheRight.setPos(0F, -1F, 3F);
//        moustacheRight.texOffs(64, 64);
//        moustacheRight.mirror = true;
//        this.setRotateAngle(moustacheRight, 0F, 0F, 0F);
//
//        jowlLeft = new ModelPart(this, 5, 53);
//        jowlLeft.addBox(2F, -3F, -8F, 1, 4, 1);
//        jowlLeft.setPos(0F, -1F, 3F);
//        jowlLeft.texOffs(64, 64);
//        jowlLeft.mirror = true;
//        this.setRotateAngle(jowlLeft, 0F, 0F, 0F);
//
//        jowlRight = new ModelPart(this, 17, 53);
//        jowlRight.addBox(-3F, -3F, -8F, 1, 4, 1);
//        jowlRight.setPos(0F, -1F, 3F);
//        jowlRight.texOffs(64, 64);
//        jowlRight.mirror = true;
//        this.setRotateAngle(jowlRight, 0F, 0F, 0F);
//
//        chin1 = new ModelPart(this, 10, 60);
//        chin1.addBox(-1F, -1F, -8F, 2, 2, 1);
//        chin1.setPos(0F, -1F, 3F);
//        chin1.texOffs(64, 64);
//        chin1.mirror = true;
//        this.setRotateAngle(chin1, 0F, 0F, 0F);
//
//        spineFront = new ModelPart(this, 10, 26);
//        spineFront.addBox(0F, 0F, 2F, 1, 7, 1);
//        spineFront.setPos(-0.5F, 4.6F, -4.4F);
//        spineFront.texOffs(64, 64);
//        spineFront.mirror = true;
//        this.setRotateAngle(spineFront, 0.0349066F, 0F, 0F);
//
//        spineBack = new ModelPart(this, 24, 26);
//        spineBack.addBox(-0.5F, 0F, 0F, 1, 10, 1);
//        spineBack.setPos(0F, 2F, 3.3F);
//        spineBack.texOffs(64, 64);
//        spineBack.mirror = true;
//        this.setRotateAngle(spineBack, -0.1919862F, 0F, 0F);
//
//        head = new ModelPart(this, 0, 0);
//        head.addBox(-4F, -8F, -7F, 8, 8, 9, scale);
//        head.setPos(0F, -1F, 3F);
//        head.mirror = true;
//
//        chin2 = new ModelPart(this, 14, 17);
//        chin2.addBox(-2F, 0F, -7F, 4, 1, 6);
//        chin2.setPos(0F, -1F, 3F);
//        chin2.texOffs(64, 64);
//        chin2.mirror = true;
//        this.setRotateAngle(chin2, 0F, 0F, 0F);
//
//        neck = new ModelPart(this, 34, 20);
//        neck.addBox(-1.5F, 0F, -1F, 3, 5, 3);
//        neck.setPos(0F, -2F, 3F);
//        neck.texOffs(64, 64);
//        neck.mirror = true;
//        this.setRotateAngle(neck, -0.2617994F, 0F, 0F);
//
//        bodyTop = new ModelPart(this, 34, 0);
//        bodyTop.addBox(-5F, 0F, -2F, 10, 5, 5);
//        bodyTop.setPos(0F, 2F, 1F);
//        bodyTop.texOffs(64, 64);
//        bodyTop.mirror = true;
//        this.setRotateAngle(bodyTop, -0.1745329F, 0F, 0F);
//
//        bodyBottom = new ModelPart(this, 42, 10);
//        bodyBottom.addBox(-3.5F, 0F, -2F, 7, 6, 4);
//        bodyBottom.setPos(0F, 6F, 1F);
//        bodyBottom.texOffs(64, 64);
//        bodyBottom.mirror = true;
//        this.setRotateAngle(bodyBottom, -0.1745329F, 0F, 0F);
//
//        frontRightLeg = new ModelPart(this, 0, 17);
//        frontRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
//        frontRightLeg.setPos(-2F, 12F, -1F);
//        frontRightLeg.texOffs(64, 64);
//        frontRightLeg.mirror = true;
//        this.setRotateAngle(frontRightLeg, -0.8726646F, 0F, 0.2268928F);
//
//        foreFrontRightLeg = new ModelPart(this, 56, 21);
//        foreFrontRightLeg.addBox(-2F, 2F, -6F, 2, 10, 2);
//        foreFrontRightLeg.setPos(-2F, 12F, -1F);
//        foreFrontRightLeg.texOffs(64, 64);
//        foreFrontRightLeg.mirror = true;
//        this.setRotateAngle(foreFrontRightLeg, 0.1745329F, 0F, -0.0872665F);
//
//        frontLeftLeg = new ModelPart(this, 0, 17);
//        frontLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
//        frontLeftLeg.setPos(2F, 12F, -1F);
//        frontLeftLeg.texOffs(64, 64);
//        frontLeftLeg.mirror = true;
//        this.setRotateAngle(frontLeftLeg, -0.8726646F, 0F, -0.2268928F);
//
//        foreFrontLeftLeg = new ModelPart(this, 56, 21);
//        foreFrontLeftLeg.addBox(0F, 2F, -6F, 2, 10, 2);
//        foreFrontLeftLeg.setPos(2F, 12F, -1F);
//        foreFrontLeftLeg.texOffs(64, 64);
//        foreFrontLeftLeg.mirror = true;
//        this.setRotateAngle(foreFrontLeftLeg, 0.1745329F, 0F, 0.0872665F);
//
//        backRightLeg = new ModelPart(this, 0, 17);
//        backRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
//        backRightLeg.setPos(-2F, 11F, 1F);
//        backRightLeg.texOffs(64, 64);
//        backRightLeg.mirror = true;
//        this.setRotateAngle(backRightLeg, 0.8726646F, 0F, 0.2268928F);
//
//        backForeRightLeg = new ModelPart(this, 56, 22);
//        backForeRightLeg.addBox(-2F, 3F, 3F, 2, 10, 2);
//        backForeRightLeg.setPos(-2F, 11F, 1F);
//        backForeRightLeg.texOffs(64, 64);
//        backForeRightLeg.mirror = true;
//        this.setRotateAngle(backForeRightLeg, -0.1745329F, 0F, 0F);
//
//        backLeftLeg = new ModelPart(this, 0, 17);
//        backLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
//        backLeftLeg.setPos(2F, 11F, 1F);
//        backLeftLeg.texOffs(64, 64);
//        backLeftLeg.mirror = true;
//        this.setRotateAngle(backLeftLeg, 0.8726646F, 0F, -0.2268928F);
//
//        backForeLeftLeg = new ModelPart(this, 56, 22);
//        backForeLeftLeg.addBox(4F, 3F, 3F, 2, 10, 2);
//        backForeLeftLeg.setPos(-2F, 11F, 1F);
//        backForeLeftLeg.texOffs(64, 64);
//        backForeLeftLeg.mirror = true;
//        this.setRotateAngle(backForeLeftLeg, -0.1745329F, 0F, 0F);
//
//        pecLeft = new ModelPart(this, 12, 35);
//        pecLeft.addBox(-2F, -1F, 0F, 4, 3, 2);
//        pecLeft.setPos(2.3F, 3F, -2.5F);
//        pecLeft.texOffs(64, 64);
//        pecLeft.mirror = true;
//        this.setRotateAngle(pecLeft, -0.1745329F, 0F, 0F);
//
//        pecRight = new ModelPart(this, 0, 35);
//        pecRight.addBox(-2F, -1F, 0F, 4, 3, 2);
//        pecRight.setPos(-2.3F, 3F, -2.5F);
//        pecRight.texOffs(64, 64);
//        pecRight.mirror = true;
//        this.setRotateAngle(pecRight, -0.1745329F, 0F, 0F);
//    }
//
//    private void setRotateAngle(ModelPart model, float x, float y, float z) {
//        model.xRot = x;
//        model.yRot = y;
//        model.zRot = z;
//    }
//
//    @Override
//    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        // Head Parts
//        head.xRot = netHeadYaw * 0.015453292F;
//        head.yRot = headPitch * 0.008453292F;
//        chin1.xRot = head.xRot;
//        chin1.yRot = head.yRot;
//        chin2.yRot = head.yRot;
//        chin2.xRot = head.xRot;
//        jowlLeft.xRot = head.xRot;
//        jowlLeft.yRot = head.yRot;
//        jowlRight.xRot = head.xRot;
//        jowlRight.yRot = head.yRot;
//        nose.xRot = head.xRot;
//        nose.yRot = head.yRot;
//        sideburnRight.xRot = head.xRot;
//        sideburnRight.yRot = head.yRot;
//        sideburnLeft.xRot = head.xRot;
//        sideburnLeft.yRot = head.yRot;
//        forehead.xRot = head.xRot;
//        forehead.yRot = head.yRot;
//        moustacheLeft.xRot = head.xRot;
//        moustacheLeft.yRot = head.yRot;
//        moustacheRight.xRot = head.xRot;
//        moustacheRight.yRot = head.yRot;
//
//        // Leg Parts
//        backRightLeg.xRot = 0.8726646F;
//        backRightLeg.xRot -= Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        backForeRightLeg.xRot = -0.1745329F;
//        backForeRightLeg.xRot += Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
//        frontRightLeg.xRot = -0.8726646F;
//        frontRightLeg.xRot += Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        foreFrontRightLeg.xRot = 0.1745329F;
//        foreFrontRightLeg.xRot -= Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
//        frontLeftLeg.xRot = -0.8726646F;
//        frontLeftLeg.xRot += Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
//        foreFrontLeftLeg.xRot = 0.1745329F;
//        foreFrontLeftLeg.xRot -= Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//        backLeftLeg.xRot = 0.8726646F;
//        backLeftLeg.xRot -= Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
//        backForeLeftLeg.xRot = -0.1745329F;
//        backForeLeftLeg.xRot += Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
//    }
//
//    @Override
//    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
//        ribLeftTop.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        ribRightTop.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        ribLeftMiddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        ribRightMiddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        ribLeftBottom.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        ribRightBottom.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        forehead.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        sideburnLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        nose.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        sideburnRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        moustacheLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        moustacheRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        jowlLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        jowlRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        chin1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        spineFront.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        spineBack.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        chin2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        neck.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        bodyTop.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        bodyBottom.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        frontRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        foreFrontRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        frontLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        foreFrontLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        backRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        backForeRightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        backLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        backForeLeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        pecLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        pecRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//    }
//
//}