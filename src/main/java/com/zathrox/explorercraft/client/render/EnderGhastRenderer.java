package com.zathrox.explorercraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.EnderGhastModel;
import com.zathrox.explorercraft.common.entity.EnderGhastEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderGhastRenderer extends MobRenderer<EnderGhastEntity, EnderGhastModel<EnderGhastEntity>> {
    private static final ResourceLocation GHAST_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/ghast/enderghast.png");
    private static final ResourceLocation GHAST_SHOOTING_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID,"textures/entity/ghast/enderghast_fire.png");

    public EnderGhastRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EnderGhastModel<>(), 1.5F);
    }

    protected ResourceLocation getEntityTexture(EnderGhastEntity entity) {
        return entity.isAttacking() ? GHAST_SHOOTING_TEXTURES : GHAST_TEXTURES;
    }

    protected void preRenderCallback(EnderGhastEntity entitylivingbaseIn, float partialTickTime) {
        float f = 1.0F;
        float f1 = 4.5F;
        float f2 = 4.5F;
        GlStateManager.scalef(4.5F, 4.5F, 4.5F);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
