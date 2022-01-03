package dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.EnderreeperEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderreeperTestModel<Type extends EnderreeperEntity> extends EntityModel<Type> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Explorercraft.MOD_ID, "enderreeper"), "main");
    private final ModelPart head;
    private final ModelPart core;
    private final ModelPart rightLegFront;
    private final ModelPart rightLegFront2;
    private final ModelPart rightLegBack;
    private final ModelPart leftLegFront;
    private final ModelPart leftLegBack;

    public EnderreeperTestModel(ModelPart root) {
        this.head = root.getChild("head");
        this.core = root.getChild("core");
        this.rightLegFront = root.getChild("rightLegFront");
        this.rightLegFront2 = root.getChild("rightLegFront2");
        this.rightLegBack = root.getChild("rightLegBack");
        this.leftLegFront = root.getChild("leftLegFront");
        this.leftLegBack = root.getChild("leftLegBack");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.8F, -7.2727F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(5, 53).addBox(2.0F, -1.8F, -8.2727F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 53).addBox(-3.0F, -1.8F, -8.2727F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 60).addBox(1.0F, -2.8F, -8.2727F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 60).addBox(-3.0F, -2.8F, -8.2727F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 55).addBox(-1.0F, -4.8F, -8.2727F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 57).addBox(-4.0F, -4.8F, -8.2727F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 57).addBox(3.0F, -4.8F, -8.2727F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 41).addBox(-4.0F, -7.8F, -8.2727F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 60).addBox(-1.0F, 0.2F, -8.2727F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 17).addBox(-2.0F, 0.2F, -7.2727F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.2F, -2.2F, 3.6727F));

        PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck_r1 = body.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(34, 20).addBox(-2.8F, -4.0703F, -2.4721F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -23.0F, 3.4F, -0.3881F, 0.0F, 0.0F));

        PartDefinition spine_r1 = body.addOrReplaceChild("spine_r1", CubeListBuilder.create().texOffs(10, 26).addBox(-4.0F, -15.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -5.0F, -1.6F, -0.0006F, 0.0F, 0.0F));

        PartDefinition spineBack_r1 = body.addOrReplaceChild("spineBack_r1", CubeListBuilder.create().texOffs(24, 26).addBox(-3.9F, -18.0F, 2.9F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -5.0F, -1.6F, -0.0839F, 0.0F, 0.0F));

        PartDefinition bodyTop_r1 = body.addOrReplaceChild("bodyTop_r1", CubeListBuilder.create().texOffs(34, 0).addBox(-13.4F, -4.0529F, 2.6078F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5F, -20.0F, -4.6F, -0.2151F, 0.0F, 0.0F));

        PartDefinition bodyBottom_r1 = body.addOrReplaceChild("bodyBottom_r1", CubeListBuilder.create().texOffs(42, 10).addBox(-7.0F, -14.0F, -1.0F, 7.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -5.0F, -1.6F, -0.0842F, 0.0F, 0.0F));

        PartDefinition pecRight_r1 = body.addOrReplaceChild("pecRight_r1", CubeListBuilder.create().texOffs(0, 35).addBox(-3.3F, -17.7F, -2.7F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 35).addBox(-7.6F, -17.7F, -2.7F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -5.0F, -1.6F, -0.1222F, 0.0F, 0.0F));

        PartDefinition ribs = body.addOrReplaceChild("ribs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition ribLeftMiddle_r1 = ribs.addOrReplaceChild("ribLeftMiddle_r1", CubeListBuilder.create().texOffs(14, 28).addBox(-7.0F, -15.1F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -2.3F, -2.6F, 0.003F, 0.0001F, -0.0436F));

        PartDefinition ribRightMiddle_r1 = ribs.addOrReplaceChild("ribRightMiddle_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-3.1016F, -0.6432F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -16.3F, -2.6F, 0.0F, 0.0F, 0.0785F));

        PartDefinition ribRightTop_r1 = ribs.addOrReplaceChild("ribRightTop_r1", CubeListBuilder.create().texOffs(14, 26).addBox(0.5F, -14.4F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -4.5F, -5.6F, 0.0F, 0.0F, -0.2618F));

        PartDefinition ribLeftTop_r1 = ribs.addOrReplaceChild("ribLeftTop_r1", CubeListBuilder.create().texOffs(14, 26).addBox(-11.2F, -12.6F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -4.5F, -5.6F, 0.0029F, -0.0008F, 0.2618F));

        PartDefinition ribRightBottom_r1 = ribs.addOrReplaceChild("ribRightBottom_r1", CubeListBuilder.create().texOffs(0, 30).addBox(-10.4F, -15.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.9F, -2.6F, 0.0F, 0.0F, 0.4363F));

        PartDefinition ribLeftBottom_r1 = ribs.addOrReplaceChild("ribLeftBottom_r1", CubeListBuilder.create().texOffs(14, 30).addBox(0.0F, -18.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 1.9F, -2.6F, 0.0028F, 0.0013F, -0.4363F));

        PartDefinition bone = core.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightLegFront = partdefinition.addOrReplaceChild("rightLegFront", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition frontRightLeg_r1 = rightLegFront.addOrReplaceChild("frontRightLeg_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-0.985F, -0.0526F, -1.7185F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7397F, -12.4273F, -0.9915F, -0.6732F, -0.0161F, -0.2164F));

        PartDefinition rightLegFront2 = partdefinition.addOrReplaceChild("rightLegFront2", CubeListBuilder.create().texOffs(56, 22).addBox(2.4F, -8.8F, -5.6F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition rightLegBack = partdefinition.addOrReplaceChild("rightLegBack", CubeListBuilder.create().texOffs(48, 35).addBox(2.4F, -8.8F, 3.4F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition backRightLeg_r1 = rightLegBack.addOrReplaceChild("backRightLeg_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-1.1F, -6.4F, 3.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -5.0F, 1.4F, 0.6732F, 0.0161F, -0.2164F));

        PartDefinition leftLegFront = partdefinition.addOrReplaceChild("leftLegFront", CubeListBuilder.create().texOffs(48, 22).mirror().addBox(-4.4F, -8.8F, -5.6F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition frontLeftLeg_r1 = leftLegFront.addOrReplaceChild("frontLeftLeg_r1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.7397F, -10.4273F, -2.9915F, -0.6732F, 0.0161F, 0.2164F));

        PartDefinition leftLegBack = partdefinition.addOrReplaceChild("leftLegBack", CubeListBuilder.create().texOffs(56, 34).mirror().addBox(-4.4F, -8.8F, 3.4F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition backLeftLeg_r1 = leftLegBack.addOrReplaceChild("backLeftLeg_r1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-1.5F, -3.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.7397F, -10.4273F, 2.7915F, 0.6732F, -0.0161F, 0.2164F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);

        //rightLegBack.xRot = 0.8726646F;
        //rightLegBack.xRot -= Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        //backForeRightLeg.xRot = -0.1745329F;
        //backForeRightLeg.xRot += Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        rightLegFront.xRot = -0.0F;
        rightLegFront.xRot += Math.cos(limbSwing * 0.2662F) * 0.4F * limbSwingAmount;
        rightLegFront2.xRot = 0.0F;
        rightLegFront2.xRot -= Math.cos(limbSwing * 0.2662F + 1.141593F) * 0.4F * limbSwingAmount;
        //leftLegFront.xRot = -0.8726646F;
        //leftLegFront.xRot += Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        //foreFrontLeftLeg.xRot = 0.1745329F;
        //foreFrontLeftLeg.xRot -= Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        //leftLegBack.xRot = 0.8726646F;
        //leftLegBack.xRot -= Math.cos(limbSwing * 0.6662F + 3.141593F) * 1.4F * limbSwingAmount;
        //backForeLeftLeg.xRot = -0.1745329F;
        //backForeLeftLeg.xRot += Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        head.render(poseStack, buffer, packedLight, packedOverlay);
        core.render(poseStack, buffer, packedLight, packedOverlay);
        rightLegFront.render(poseStack, buffer, packedLight, packedOverlay);
        rightLegFront2.render(poseStack, buffer, packedLight, packedOverlay);
        rightLegBack.render(poseStack, buffer, packedLight, packedOverlay);
        leftLegFront.render(poseStack, buffer, packedLight, packedOverlay);
        leftLegBack.render(poseStack, buffer, packedLight, packedOverlay);
    }
}