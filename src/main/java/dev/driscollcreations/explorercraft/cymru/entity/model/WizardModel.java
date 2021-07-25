package dev.driscollcreations.explorercraft.cymru.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import dev.driscollcreations.explorercraft.cymru.entity.WizardEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WizardModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer leftEyebrow;
    public ModelRenderer rightEyebrow;
    public ModelRenderer hair1;
    public ModelRenderer hair2;
    public ModelRenderer wizardHead;
    public ModelRenderer wizardLeftArm;
    public ModelRenderer wizardNose;
    public ModelRenderer wizardRightArm;
    public ModelRenderer wizardBody;
    public ModelRenderer wizardLegLeft;
    public ModelRenderer wizardLegRight;
    public ModelRenderer Hat_1;
    public ModelRenderer Hat_2;
    public ModelRenderer Hat_3;
    public ModelRenderer Hat_4;
    public ModelRenderer Beard_1;
    public ModelRenderer Beard_2;
    public ModelRenderer Beard_3;
    public ModelRenderer Beard_4;
    public ModelRenderer Beard_5;
    public ModelRenderer Beard_6;
    public ModelRenderer Beard_7;
    public ModelRenderer Staff_1;
    public ModelRenderer Staff_2;
    public ModelRenderer Staff_3;
    public ModelRenderer Staff_4;
    public ModelRenderer Staff_5;

    public WizardModel() {
        this.texWidth = 64;
        this.texHeight = 128;
        this.hair1 = new ModelRenderer(this, 48, 27);
        this.hair1.setPos(0.0F, 0.0F, 0.0F);
        this.hair1.addBox(-3.0F, -7.0F, 3.0F, 6, 7, 2, 0.0F);
        this.Staff_5 = new ModelRenderer(this, 48, 43);
        this.Staff_5.setPos(-3.0F, 2.0F, 0.0F);
        this.Staff_5.addBox(0.0F, -6.3F, -9.0F, 2, 2, 2, 0.0F);
        this.Beard_3 = new ModelRenderer(this, 44, 0);
        this.Beard_3.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_3.addBox(-4.0F, -1.0F, -5.0F, 8, 6, 2, 0.0F);
        this.Staff_4 = new ModelRenderer(this, 40, 44);
        this.Staff_4.setPos(-3.0F, 2.0F, 0.0F);
        this.Staff_4.addBox(0.0F, -5.9F, 10.7F, 2, 5, 2, 0.0F);
        this.setRotateAngle(Staff_4, 2.1642082724729685F, 0.0F, 0.0F);
        this.hair2 = new ModelRenderer(this, 48, 27);
        this.hair2.setPos(0.0F, 0.0F, 0.0F);
        this.hair2.addBox(-3.0F, 0.0F, 3.0F, 6, 7, 2, 0.0F);
        this.Staff_1 = new ModelRenderer(this, 56, 43);
        this.Staff_1.setPos(0.0F, 0.0F, 0.0F);
        this.Staff_1.addBox(-3.0F, 3.0F, -8.0F, 2, 19, 2, 0.0F);
        this.setRotateAngle(Staff_1, 0.8754571528003554F, 0.0F, -0.017453292519943295F);
        this.Beard_5 = new ModelRenderer(this, 32, 8);
        this.Beard_5.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_5.addBox(1.0F, -3.0F, -5.0F, 4, 1, 4, 0.0F);
        this.leftEyebrow = new ModelRenderer(this, 0, 8);
        this.leftEyebrow.setPos(0.0F, 0.0F, 0.0F);
        this.leftEyebrow.addBox(0.5F, -5.0F, -5.0F, 4, 1, 1, 0.0F);
        this.Staff_2 = new ModelRenderer(this, 48, 54);
        this.Staff_2.setPos(-3.0F, 2.0F, 0.0F);
        this.Staff_2.addBox(0.0F, -4.5F, -7.5F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Staff_2, -0.33161255787892263F, 0.0F, 0.0F);
        this.Beard_2 = new ModelRenderer(this, 48, 8);
        this.Beard_2.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_2.addBox(-3.0F, 5.0F, -5.0F, 6, 3, 2, 0.0F);
        this.wizardLegRight = new ModelRenderer(this, 0, 64);
        this.wizardLegRight.setPos(-2.0F, 13.0F, 0.0F);
        this.wizardLegRight.addBox(-2.0F, 0.0F, -3.0F, 4, 11, 6, 0.0F);
        this.Hat_3 = new ModelRenderer(this, 16, 18);
        this.Hat_3.setPos(0.0F, 0.0F, 0.0F);
        this.Hat_3.addBox(-2.0F, -14.0F, -7.0F, 4, 5, 4, 0.0F);
        this.setRotateAngle(Hat_3, -0.5410521030426025F, 0.0F, 0.0F);
        this.Beard_1 = new ModelRenderer(this, 36, 4);
        this.Beard_1.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_1.addBox(-1.0F, 8.0F, -5.0F, 2, 2, 2, 0.0F);
        this.wizardLeftArm = new ModelRenderer(this, 0, 18);
        this.wizardLeftArm.setPos(4.1F, 2.0F, -1.0F);
        this.wizardLeftArm.addBox(0.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(wizardLeftArm, 0.0F, 0.0F, -4.960524086056721E-16F);
        this.Beard_6 = new ModelRenderer(this, 32, 0);
        this.Beard_6.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_6.addBox(-5.0F, -2.0F, -5.0F, 3, 1, 3, 0.0F);
        this.wizardNose = new ModelRenderer(this, 24, 0);
        this.wizardNose.setPos(0.0F, 0.0F, 0.0F);
        this.wizardNose.addBox(-1.0F, -3.0F, -6.0F, 2, 3, 2, 0.0F);
        this.Staff_3 = new ModelRenderer(this, 48, 47);
        this.Staff_3.setPos(-3.0F, 2.0F, 0.0F);
        this.Staff_3.addBox(0.0F, -11.0F, 2.7F, 2, 5, 2, 0.0F);
        this.setRotateAngle(Staff_3, 1.0821041362364843F, 0.0F, 0.0F);
        this.Hat_4 = new ModelRenderer(this, 16, 27);
        this.Hat_4.setPos(0.0F, 0.0F, 0.0F);
        this.Hat_4.addBox(-1.0F, -15.0F, -11.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(Hat_4, -0.9948377013206482F, 0.0F, 0.0F);
        this.rightEyebrow = new ModelRenderer(this, 0, 8);
        this.rightEyebrow.setPos(0.0F, 0.0F, 0.0F);
        this.rightEyebrow.addBox(-4.5F, -5.0F, -5.0F, 4, 1, 1, 0.0F);
        this.wizardBody = new ModelRenderer(this, 36, 64);
        this.wizardBody.setPos(0.0F, 0.0F, 0.0F);
        this.wizardBody.addBox(-4.0F, 0.0F, -3.0F, 8, 13, 6, 0.0F);
        this.Hat_1 = new ModelRenderer(this, 0, 51);
        this.Hat_1.setPos(0.0F, 0.0F, 0.0F);
        this.Hat_1.addBox(-6.0F, -8.0F, -6.0F, 12, 1, 12, 0.0F);
        this.wizardLegLeft = new ModelRenderer(this, 0, 64);
        this.wizardLegLeft.setPos(2.0F, 13.0F, -1.0F);
        this.wizardLegLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 6, 0.0F);
        this.wizardRightArm = new ModelRenderer(this, 0, 18);
        this.wizardRightArm.setPos(-4.0F, 2.0F, 0.0F);
        this.wizardRightArm.addBox(-4.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(wizardRightArm, -0.9105382707654417F, 0.0F, 0.0F);
        this.Beard_4 = new ModelRenderer(this, 32, 8);
        this.Beard_4.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_4.addBox(-5.0F, -3.0F, -5.0F, 4, 1, 4, 0.0F);
        this.Beard_7 = new ModelRenderer(this, 32, 0);
        this.Beard_7.setPos(0.0F, 0.0F, 0.0F);
        this.Beard_7.addBox(2.0F, -2.0F, -5.0F, 3, 1, 3, 0.0F);
        this.Hat_2 = new ModelRenderer(this, 0, 40);
        this.Hat_2.setPos(0.0F, 0.0F, 0.0F);
        this.Hat_2.addBox(-4.0F, -11.0F, -6.0F, 8, 3, 8, 0.0F);
        this.setRotateAngle(Hat_2, -0.27925270795822144F, 0.0F, 0.0F);
        this.wizardHead = new ModelRenderer(this, 0, 0);
        this.wizardHead.setPos(0.0F, 0.0F, 0.0F);
        this.wizardHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.wizardRightArm.addChild(this.Staff_1);
        this.Staff_1.addChild(this.Staff_5);
        this.Staff_1.addChild(this.Staff_4);
        this.Staff_1.addChild(this.Staff_2);
        this.Staff_1.addChild(this.Staff_3);

    }
    

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean shakingHead;
        WizardEntity wizard = (WizardEntity) entityIn;
        shakingHead = ((AbstractVillagerEntity)entityIn).getUnhappyCounter() > 0;
        wizardHead.yRot = netHeadYaw * 0.017453292F;
        wizardHead.xRot = headPitch * 0.017453292F;
        if (shakingHead) {
            this.wizardHead.zRot = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            this.wizardHead.xRot = 0.4F;
        } else {
            this.wizardHead.zRot = 0.0F;
        }

        this.wizardBody.yRot = 0.0f;
        Hat_1.xRot = 0F + wizardHead.xRot;
        Hat_2.xRot = -0.2792527F + wizardHead.xRot;
        Hat_3.xRot = -0.5410521F + wizardHead.xRot;
        Hat_4.xRot = -0.9948377F + wizardHead.xRot;

        Hat_1.yRot = wizardHead.yRot;
        Hat_2.yRot = wizardHead.yRot;
        Hat_3.yRot = wizardHead.yRot;
        Hat_4.yRot = wizardHead.yRot;

        Beard_1.xRot = wizardHead.xRot;
        Beard_2.xRot = wizardHead.xRot;
        Beard_3.xRot = wizardHead.xRot;
        Beard_4.xRot = wizardHead.xRot;
        Beard_5.xRot = wizardHead.xRot;
        Beard_6.xRot = wizardHead.xRot;
        Beard_7.xRot = wizardHead.xRot;
        Beard_1.yRot = wizardHead.yRot;
        Beard_2.yRot = wizardHead.yRot;
        Beard_3.yRot = wizardHead.yRot;
        Beard_4.yRot = wizardHead.yRot;
        Beard_5.yRot = wizardHead.yRot;
        Beard_6.yRot = wizardHead.yRot;
        Beard_7.yRot = wizardHead.yRot;

        hair1.yRot = wizardHead.yRot;
        hair1.xRot = wizardHead.xRot;
        hair2.yRot = wizardHead.yRot;
        hair2.xRot = wizardHead.xRot;

        leftEyebrow.yRot = wizardHead.yRot;
        leftEyebrow.xRot = wizardHead.xRot;
        rightEyebrow.yRot = wizardHead.yRot;
        rightEyebrow.xRot = wizardHead.xRot;
        leftEyebrow.zRot = wizardHead.zRot;
        rightEyebrow.zRot = wizardHead.zRot;

        wizardNose.yRot = wizardHead.yRot;
        wizardNose.xRot = wizardHead.xRot;

        float armxRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;

        wizardRightArm.xRot = -0.8726646F + armxRot;
        wizardRightArm.zRot = 0.0F;
        wizardRightArm.yRot = 0.0F;

        wizardLeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        wizardLeftArm.zRot = 0.0F;
        wizardLeftArm.yRot = 0.0F;

        this.Staff_1.xRot = -5.4F;
        this.Staff_1.yRot = 0f;
        this.Staff_1.zRot = 0f;

        wizardLegRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        wizardLegLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        wizardLegRight.yRot = 0.0F;
        wizardLegLeft.yRot = 0.0F;

        if (wizard.isCharged()) {
            this.wizardLeftArm.z = 0.0F;
            this.wizardLeftArm.x = 4.0F;
            this.wizardLeftArm.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
            this.wizardLeftArm.zRot = -2.3561945F;
            this.wizardLeftArm.yRot = 0.0F;

            this.wizardRightArm.z = 0.0F;
            this.wizardRightArm.x = -4.0F;
            this.wizardRightArm.xRot = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
            this.wizardRightArm.zRot = 2.3561945F;
            this.wizardRightArm.yRot = 0.0F;
            this.Staff_1.xRot = -5f;
            this.leftEyebrow.zRot = -0.2f;
            this.rightEyebrow.zRot = 0.2f;
        }
    }

    private void setRotateAngle(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.hair1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.hair2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leftEyebrow.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardLegRight.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Hat_3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardLeftArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardNose.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Hat_4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rightEyebrow.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardBody.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Hat_1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardLegLeft.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardRightArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Beard_7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.Hat_2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.wizardHead.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}

