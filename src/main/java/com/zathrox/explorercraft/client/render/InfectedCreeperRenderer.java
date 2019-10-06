package com.zathrox.explorercraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.InfectedCreeperModel;
import com.zathrox.explorercraft.client.render.layer.InfectedCreeperChargeLayer;
import com.zathrox.explorercraft.common.entity.InfectedCreeperEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfectedCreeperRenderer extends MobRenderer<InfectedCreeperEntity, InfectedCreeperModel<InfectedCreeperEntity>> {
    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/creeper/creeping.png");

    public InfectedCreeperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InfectedCreeperModel<>(), 0.5F);
        this.addLayer(new InfectedCreeperChargeLayer(this));
    }

    protected void preRenderCallback(InfectedCreeperEntity entitylivingbaseIn, float partialTickTime) {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        GlStateManager.scalef(f2, f3, f2);
    }

    protected int getColorMultiplier(InfectedCreeperEntity entitylivingbaseIn, float lightBrightness, float partialTickTime) {
        float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
        if ((int)(f * 10.0F) % 2 == 0) {
            return 0;
        } else {
            int i = (int)(f * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 822083583;
        }
    }

    protected ResourceLocation getEntityTexture(InfectedCreeperEntity entity) {
        return CREEPER_TEXTURES;
    }
}
