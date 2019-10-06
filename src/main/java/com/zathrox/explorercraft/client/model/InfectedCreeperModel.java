package com.zathrox.explorercraft.client.model;
//Made By BronGhast014 with Blockbench

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfectedCreeperModel<T extends Entity> extends EntityModel<T> {

	private final RendererModel quadrapedBody;
	private final RendererModel quadrapedFrontLeftLeg;
	private final RendererModel quadrapedBackLeftLeg;
	private final RendererModel quadrapedFrontRightLeg;
	private final RendererModel quadrapedBackRightLeg;
	private final RendererModel quadrapedHead;
	private final RendererModel mushroom;
	private final RendererModel mushroomX;
	private final RendererModel mushroomY;
	private final RendererModel mushroom2;
	private final RendererModel mushroomX2;
	private final RendererModel mushroomY2;
	private final RendererModel grass;
	private final RendererModel grassX;
	private final RendererModel grassY;
	private final RendererModel grass3;
	private final RendererModel grassX3;
	private final RendererModel grassY3;
	private final RendererModel grass2;
	private final RendererModel grassX2;
	private final RendererModel grassY2;

	public InfectedCreeperModel() {
		this(0.0F);
	}

	public InfectedCreeperModel(float scale) {
		textureWidth = 64;
		textureHeight = 64;

		quadrapedBody = new RendererModel(this);
		quadrapedBody.setRotationPoint(0.0F, 18.0F, 0.0F);
		quadrapedBody.cubeList.add(new ModelBox(quadrapedBody, 16, 16, -4.0F, -12.0F, -2.0F, 8, 12, 4, 0.0F, false));

		quadrapedFrontLeftLeg = new RendererModel(this);
		quadrapedFrontLeftLeg.setRotationPoint(2.0F, 0.0F, -2.0F);
		quadrapedBody.addChild(quadrapedFrontLeftLeg);
		quadrapedFrontLeftLeg.cubeList.add(new ModelBox(quadrapedFrontLeftLeg, 0, 16, -2.0F, 0.0F, -4.0F, 4, 6, 4, 0.0F, false));

		quadrapedBackLeftLeg = new RendererModel(this);
		quadrapedBackLeftLeg.setRotationPoint(2.0F, 0.0F, 2.0F);
		quadrapedBody.addChild(quadrapedBackLeftLeg);
		quadrapedBackLeftLeg.cubeList.add(new ModelBox(quadrapedBackLeftLeg, 0, 16, -2.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F, false));

		quadrapedFrontRightLeg = new RendererModel(this);
		quadrapedFrontRightLeg.setRotationPoint(-2.0F, 0.0F, -2.0F);
		quadrapedBody.addChild(quadrapedFrontRightLeg);
		quadrapedFrontRightLeg.cubeList.add(new ModelBox(quadrapedFrontRightLeg, 0, 16, -2.0F, 0.0F, -4.0F, 4, 6, 4, 0.0F, false));

		quadrapedBackRightLeg = new RendererModel(this);
		quadrapedBackRightLeg.setRotationPoint(-2.0F, 0.0F, 2.0F);
		quadrapedBody.addChild(quadrapedBackRightLeg);
		quadrapedBackRightLeg.cubeList.add(new ModelBox(quadrapedBackRightLeg, 0, 16, -2.0F, 0.0F, 0.0F, 4, 6, 4, 0.0F, false));

		quadrapedHead = new RendererModel(this);
		quadrapedHead.setRotationPoint(0.0F, -12.1F, 0.0F);
		quadrapedBody.addChild(quadrapedHead);
		quadrapedHead.cubeList.add(new ModelBox(quadrapedHead, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.2F, false));
		quadrapedHead.cubeList.add(new ModelBox(quadrapedHead, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

		mushroom = new RendererModel(this);
		mushroom.setRotationPoint(1.0F, -7.9F, 2.0F);
		setRotationAngle(mushroom, -0.0873F, 0.0F, 0.1745F);
		quadrapedHead.addChild(mushroom);

		mushroomX = new RendererModel(this);
		mushroomX.setRotationPoint(0.0F, -1.5F, 0.0F);
		mushroom.addChild(mushroomX);
		mushroomX.cubeList.add(new ModelBox(mushroomX, 11, 35, -4.0F, -4.0F, -2.5F, 8, 8, 5, -2.5F, false));

		mushroomY = new RendererModel(this);
		mushroomY.setRotationPoint(0.0F, -1.5F, 0.0F);
		setRotationAngle(mushroomY, 0.0F, -1.5708F, 0.0F);
		mushroom.addChild(mushroomY);
		mushroomY.cubeList.add(new ModelBox(mushroomY, 11, 35, -4.0F, -4.0F, -2.5F, 8, 8, 5, -2.5F, false));

		mushroom2 = new RendererModel(this);
		mushroom2.setRotationPoint(3.75F, -6.1F, 0.0F);
		setRotationAngle(mushroom2, -0.0873F, -0.5236F, 0.6981F);
		quadrapedBody.addChild(mushroom2);

		mushroomX2 = new RendererModel(this);
		mushroomX2.setRotationPoint(0.0F, -1.5F, 0.0F);
		mushroom2.addChild(mushroomX2);
		mushroomX2.cubeList.add(new ModelBox(mushroomX2, 12, 28, -4.0F, -4.0F, -1.75F, 8, 8, 4, -2.0F, false));

		mushroomY2 = new RendererModel(this);
		mushroomY2.setRotationPoint(0.0F, -1.5F, 0.0F);
		setRotationAngle(mushroomY2, 0.0F, -1.5708F, 0.0F);
		mushroom2.addChild(mushroomY2);
		mushroomY2.cubeList.add(new ModelBox(mushroomY2, 12, 28, -3.75F, -4.0F, -2.0F, 8, 8, 4, -2.0F, false));

		grass = new RendererModel(this);
		grass.setRotationPoint(0.5F, -6.9F, -0.5F);
		setRotationAngle(grass, 0.0F, -0.3491F, 0.0F);
		quadrapedHead.addChild(grass);

		grassX = new RendererModel(this);
		grassX.setRotationPoint(0.5F, 0.0F, -0.5F);
		grass.addChild(grassX);
		grassX.cubeList.add(new ModelBox(grassX, 12, 45, -7.5F, -9.5F, -2.0F, 14, 12, 5, -2.5F, false));

		grassY = new RendererModel(this);
		grassY.setRotationPoint(1.0F, -27.5F, -3.0F);
		setRotationAngle(grassY, 0.0F, -1.5708F, 0.0F);
		grass.addChild(grassY);
		grassY.cubeList.add(new ModelBox(grassY, 12, 45, -4.0F, 18.0F, -1.5F, 14, 12, 5, -2.5F, false));

		grass3 = new RendererModel(this);
		grass3.setRotationPoint(-1.5F, -4.9F, 1.5F);
		setRotationAngle(grass3, 0.0F, 0.7854F, -0.2618F);
		quadrapedHead.addChild(grass3);

		grassX3 = new RendererModel(this);
		grassX3.setRotationPoint(0.5F, 0.0F, -0.5F);
		grass3.addChild(grassX3);
		grassX3.cubeList.add(new ModelBox(grassX3, 12, 45, -7.5F, -9.5F, -2.0F, 14, 12, 5, -2.5F, false));

		grassY3 = new RendererModel(this);
		grassY3.setRotationPoint(1.0F, -27.5F, -3.0F);
		setRotationAngle(grassY3, 0.0F, -1.5708F, 0.0F);
		grass3.addChild(grassY3);
		grassY3.cubeList.add(new ModelBox(grassY3, 12, 45, -4.0F, 18.0F, -1.5F, 14, 12, 5, -2.5F, false));

		grass2 = new RendererModel(this);
		grass2.setRotationPoint(-2.5F, -3.0F, 0.0F);
		setRotationAngle(grass2, 0.0F, -0.1745F, -0.6109F);
		quadrapedBody.addChild(grass2);

		grassX2 = new RendererModel(this);
		grassX2.setRotationPoint(0.5F, 0.0F, -0.5F);
		setRotationAngle(grassX2, 0.0F, -0.4363F, 0.0F);
		grass2.addChild(grassX2);
		grassX2.cubeList.add(new ModelBox(grassX2, 12, 45, -7.5F, -9.5F, -2.0F, 14, 12, 5, -2.5F, false));

		grassY2 = new RendererModel(this);
		grassY2.setRotationPoint(1.0F, -27.5F, -3.0F);
		setRotationAngle(grassY2, 0.0F, -1.7453F, 0.0F);
		grass2.addChild(grassY2);
		grassY2.cubeList.add(new ModelBox(grassY2, 12, 45, -4.0F, 18.0F, -1.5F, 14, 12, 5, -2.5F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		quadrapedBody.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.quadrapedHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.quadrapedHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.quadrapedFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.quadrapedFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.quadrapedBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.quadrapedBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}