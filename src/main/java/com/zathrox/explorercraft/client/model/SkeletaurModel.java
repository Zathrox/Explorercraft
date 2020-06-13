package com.zathrox.explorercraft.client.model;// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import com.google.common.collect.ImmutableList;
import com.zathrox.explorercraft.common.entity.SkeletaurEntity;
import com.zathrox.explorercraft.common.entity.WizardEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletaurModel<T extends MobEntity> extends SegmentedModel<T> {

	private final ModelRenderer body;
	private final ModelRenderer torso;
	private final ModelRenderer head;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightItem;
	private final ModelRenderer leftArm;
	private final ModelRenderer leg1A;
	private final ModelRenderer leg2A;
	private final ModelRenderer leg3A;
	private final ModelRenderer leg4A;
	private final ModelRenderer waist;

	public SkeletaurModel() {
		super();
		textureWidth = 128;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 32);
		body.setRotationPoint(0.0F, 13.0F, 8.0F);
		body.addBox(-5.0F, -10.0F, -19.0F, 10, 10, 22, 0.0F, false);

		torso = new ModelRenderer(this, 80, 16);
		torso.setRotationPoint(0.0F, -10.0F, -17.0F);
		body.addChild(torso);
		torso.addBox(-4.0F, -12.0F, -2.0F, 8, 12, 4, 0.0F, false);

		head = new ModelRenderer(this, 64, 0);
		head.setRotationPoint(0.0F, -12.0F, -1.0F);
		torso.addChild(head);
		head.addBox(-4.0F, -8.0F, -3.0F, 8, 8, 8, 0.0F, false);

		rightArm = new ModelRenderer(this, 64, 16);
		rightArm.setRotationPoint(-5.0F, -11.0F, 0.0F);
		torso.addChild(rightArm);
		rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 12, 2, 0.0F, false);

		rightItem = new ModelRenderer(this);
		rightItem.setRotationPoint(-2.0F, 8.0F, 0.0F);
		rightArm.addChild(rightItem);

		leftArm = new ModelRenderer(this, 64, 16);
		leftArm.setRotationPoint(5.0F, -11.0F, 0.0F);
		torso.addChild(leftArm);
		leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 12, 2, 0.0F, true);

		leg1A = new ModelRenderer(this, 48, 21);
		leg1A.setRotationPoint(3.0F, 0.0F, 1.0F);
		body.addChild(leg1A);
		leg1A.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, true);

		leg2A = new ModelRenderer(this, 48, 21);
		leg2A.setRotationPoint(-3.0F, 0.0F, 1.0F);
		body.addChild(leg2A);
		leg2A.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, false);

		leg3A = new ModelRenderer(this, 48, 21);
		leg3A.setRotationPoint(3.0F, 0.0F, -17.0F);
		body.addChild(leg3A);
		leg3A.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, true);

		leg4A = new ModelRenderer(this, 48, 21);
		leg4A.setRotationPoint(-3.0F, 0.0F, -17.0F);
		body.addChild(leg4A);
		leg4A.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F, false);

		waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, 12.0F, 0.0F);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		SkeletaurEntity skeletaurEntity = (SkeletaurEntity) entityIn;
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.leg3A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg4A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg1A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg2A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
		this.rightArm.rotateAngleZ = 0.0F;
		this.leftArm.rotateAngleZ = 0.0F;
		this.rightArm.rotateAngleY = -(0.1F - f * 0.6F);
		this.leftArm.rotateAngleY = 0.1F - f * 0.6F;
		this.rightArm.rotateAngleX = (-(float) Math.PI / 2F);
		this.leftArm.rotateAngleX = (-(float) Math.PI / 2F);
		this.rightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
		this.leftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
		this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		this.body.rotateAngleX = 0.0F;
		this.torso.rotateAngleX = 0.0F;
		if (skeletaurEntity.isAttacking()) {
			this.body.rotateAngleX = -(0.5F - f * 0.6F);
			this.torso.rotateAngleX = 0.5F;
			//this.leg3A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 4.4F * limbSwingAmount;
			//this.leg4A.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 4.4F * limbSwingAmount;
		}

	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.body);
	}

}