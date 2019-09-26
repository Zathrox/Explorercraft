package com.zathrox.explorercraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.EnderreeperModel;
import com.zathrox.explorercraft.client.render.layer.EnderreeperChargeLayer;
import com.zathrox.explorercraft.common.entity.EnderreeperEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderreeperRenderer extends MobRenderer<EnderreeperEntity, EnderreeperModel<EnderreeperEntity>>
{
	private static final ResourceLocation ENDERREEPER_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID + ":textures/entities/enderreeper.png");

	public EnderreeperRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new EnderreeperModel(), 0.5F);
		this.addLayer(new EnderreeperChargeLayer(this));
	}

	@Override
	protected void preRenderCallback(EnderreeperEntity entitylivingbaseIn, float partialTickTime) {
		float f = entitylivingbaseIn.getEnderreeperFlashIntensity(partialTickTime);
		float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		GlStateManager.scalef(f2, f3, f2);
	}

	@Override
	protected int getColorMultiplier(EnderreeperEntity entitylivingbaseIn, float lightBrightness, float partialTickTime) {
		float f = entitylivingbaseIn.getEnderreeperFlashIntensity(partialTickTime);
		if ((int)(f * 10.0F) % 2 == 0) {
			return 0;
		} else {
			int i = (int)(f * 0.2F * 255.0F);
			i = MathHelper.clamp(i, 0, 255);
			return i << 24 | 822083583;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EnderreeperEntity entity) {
		return ENDERREEPER_TEXTURES;
	}
}