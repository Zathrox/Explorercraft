package com.zathrox.explorercraft.client.model;
//Made By BronGhast014 with Blockbench

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfectedCreeperModel<T extends Entity> extends SegmentedModel<T> {

    private final ModelRenderer quadrapedBody;
    private final ModelRenderer quadrapedFrontLeftLeg;
    private final ModelRenderer quadrapedBackLeftLeg;
    private final ModelRenderer quadrapedFrontRightLeg;
    private final ModelRenderer quadrapedBackRightLeg;
    private final ModelRenderer quadrapedHead;
    private final ModelRenderer quadrapedHeadInner;
    private final ModelRenderer mushroom;
    private final ModelRenderer mushroomX;
    private final ModelRenderer mushroomY;
    private final ModelRenderer mushroom2;
    private final ModelRenderer mushroomX2;
    private final ModelRenderer mushroomY2;
    private final ModelRenderer grass;
    private final ModelRenderer grassX;
    private final ModelRenderer grassY;
    private final ModelRenderer grass3;
    private final ModelRenderer grassX3;
    private final ModelRenderer grassY3;
    private final ModelRenderer grass2;
    private final ModelRenderer grassX2;
    private final ModelRenderer grassY2;

    public InfectedCreeperModel() {
        this(0.0F);
    }

    public InfectedCreeperModel(float scale) {
        textureWidth = 64;
        textureHeight = 64;

        quadrapedBody = new ModelRenderer(this, 16, 16);
        quadrapedBody.setRotationPoint(0.0F, 18.0F, 0.0F);
        quadrapedBody.addBox(-4.0F, -12.0F, -2.0F, 8, 12, 4, scale);

        quadrapedFrontLeftLeg = new ModelRenderer(this, 0, 16);
        quadrapedFrontLeftLeg.setRotationPoint(2.0F, 0.0F, -2.0F);
        quadrapedBody.addChild(quadrapedFrontLeftLeg);
        quadrapedFrontLeftLeg.addBox(-2.0F, 0.0F, -4.0F, 4, 6, 4, 0.0F);

        quadrapedBackLeftLeg = new ModelRenderer(this, 0, 16);
        quadrapedBackLeftLeg.setRotationPoint(2.0F, 0.0F, 2.0F);
        quadrapedBody.addChild(quadrapedBackLeftLeg);
        quadrapedBackLeftLeg.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);

        quadrapedFrontRightLeg = new ModelRenderer(this, 0, 16);
        quadrapedFrontRightLeg.setRotationPoint(-2.0F, 0.0F, -2.0F);
        quadrapedBody.addChild(quadrapedFrontRightLeg);
        quadrapedFrontRightLeg.addBox(-2.0F, 0.0F, -4.0F, 4, 6, 4, 0.0F);

        quadrapedBackRightLeg = new ModelRenderer(this, 0, 16);
        quadrapedBackRightLeg.setRotationPoint(-2.0F, 0.0F, 2.0F);
        quadrapedBody.addChild(quadrapedBackRightLeg);
        quadrapedBackRightLeg.addBox(-2.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F);

        quadrapedHead = new ModelRenderer(this, 0, 0);
        quadrapedHead.setRotationPoint(0.0F, -12.1F, 0.0F);
        quadrapedBody.addChild(quadrapedHead);
        quadrapedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.2F);

        quadrapedHeadInner = new ModelRenderer(this, 32, 0);
        quadrapedHeadInner.setRotationPoint(0.0F, 0.0F, 0.0F);
        quadrapedHead.addChild(quadrapedHeadInner);
        quadrapedHeadInner.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);

        mushroom = new ModelRenderer(this);
        mushroom.setRotationPoint(1.0F, -7.9F, 2.0F);
        setRotationAngle(mushroom, -0.0873F, 0.0F, 0.1745F);
        quadrapedHead.addChild(mushroom);

        mushroomX = new ModelRenderer(this, 11, 35);
        mushroomX.setRotationPoint(0.0F, -1.5F, 0.0F);
        mushroom.addChild(mushroomX);
        mushroomX.addBox(-4.0F, -4.0F, -2.5F, 8, 8, 5, -2.5F);

        mushroomY = new ModelRenderer(this, 11, 35);
        mushroomY.setRotationPoint(0.0F, -1.5F, 0.0F);
        setRotationAngle(mushroomY, 0.0F, -1.5708F, 0.0F);
        mushroom.addChild(mushroomY);
        mushroomY.addBox(-4.0F, -4.0F, -2.5F, 8, 8, 5, -2.5F);

        mushroom2 = new ModelRenderer(this);
        mushroom2.setRotationPoint(3.75F, -6.1F, 0.0F);
        setRotationAngle(mushroom2, -0.0873F, -0.5236F, 0.6981F);
        quadrapedBody.addChild(mushroom2);

        mushroomX2 = new ModelRenderer(this, 12, 28);
        mushroomX2.setRotationPoint(0.0F, -1.5F, 0.0F);
        mushroom2.addChild(mushroomX2);
        mushroomX2.addBox(-4.0F, -4.0F, -1.75F, 8, 8, 4, -2.0F);

        mushroomY2 = new ModelRenderer(this, 12, 28);
        mushroomY2.setRotationPoint(0.0F, -1.5F, 0.0F);
        setRotationAngle(mushroomY2, 0.0F, -1.5708F, 0.0F);
        mushroom2.addChild(mushroomY2);
        mushroomY2.addBox(-3.75F, -4.0F, -2.0F, 8, 8, 4, -2.0F);

        grass = new ModelRenderer(this);
        grass.setRotationPoint(0.5F, -6.9F, -0.5F);
        setRotationAngle(grass, 0.0F, -0.3491F, 0.0F);
        quadrapedHead.addChild(grass);

        grassX = new ModelRenderer(this, 12, 45);
        grassX.setRotationPoint(0.5F, 0.0F, -0.5F);
        grass.addChild(grassX);
        grassX.addBox(-7.5F, -9.5F, -2.0F, 14, 12, 5, -2.5F);

        grassY = new ModelRenderer(this, 12, 45);
        grassY.setRotationPoint(1.0F, -27.5F, -3.0F);
        setRotationAngle(grassY, 0.0F, -1.5708F, 0.0F);
        grass.addChild(grassY);
        grassY.addBox(-4.0F, 18.0F, -1.5F, 14, 12, 5, -2.5F);

        grass3 = new ModelRenderer(this);
        grass3.setRotationPoint(-1.5F, -4.9F, 1.5F);
        setRotationAngle(grass3, 0.0F, 0.7854F, -0.2618F);
        quadrapedHead.addChild(grass3);

        grassX3 = new ModelRenderer(this, 12, 45);
        grassX3.setRotationPoint(0.5F, 0.0F, -0.5F);
        grass3.addChild(grassX3);
        grassX3.addBox(-7.5F, -9.5F, -2.0F, 14, 12, 5, -2.5F);

        grassY3 = new ModelRenderer(this, 12, 45);
        grassY3.setRotationPoint(1.0F, -27.5F, -3.0F);
        setRotationAngle(grassY3, 0.0F, -1.5708F, 0.0F);
        grass3.addChild(grassY3);
        grassY3.addBox(-4.0F, 18.0F, -1.5F, 14, 12, 5, -2.5F);

        grass2 = new ModelRenderer(this);
        grass2.setRotationPoint(-2.5F, -3.0F, 0.0F);
        setRotationAngle(grass2, 0.0F, -0.1745F, -0.6109F);
        quadrapedBody.addChild(grass2);

        grassX2 = new ModelRenderer(this, 12, 45);
        grassX2.setRotationPoint(0.5F, 0.0F, -0.5F);
        setRotationAngle(grassX2, 0.0F, -0.4363F, 0.0F);
        grass2.addChild(grassX2);
        grassX2.addBox(-7.5F, -9.5F, -2.0F, 14, 12, 5, -2.5F);

        grassY2 = new ModelRenderer(this, 12, 45);
        grassY2.setRotationPoint(1.0F, -27.5F, -3.0F);
        setRotationAngle(grassY2, 0.0F, -1.7453F, 0.0F);
        grass2.addChild(grassY2);
        grassY2.addBox(-4.0F, 18.0F, -1.5F, 14, 12, 5, -2.5F);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.quadrapedBody);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.quadrapedHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.quadrapedHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        this.quadrapedFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.quadrapedFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.quadrapedBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.quadrapedBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}