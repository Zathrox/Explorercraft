package com.zathrox.explorercraft.client.render.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.EnderreeperModel;
import com.zathrox.explorercraft.common.entity.EnderreeperEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class EnderreeperChargeLayer extends LayerRenderer<EnderreeperEntity, EnderreeperModel<EnderreeperEntity>> {
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final EnderreeperModel<EnderreeperEntity> EnderreeperModel = new EnderreeperModel(2.0F);

    public EnderreeperChargeLayer(IEntityRenderer<EnderreeperEntity, EnderreeperModel<EnderreeperEntity>> p_i50947_1_) {
        super(p_i50947_1_);
    }

    public void render(EnderreeperEntity enderreeper, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (enderreeper.getPowered()) {
            boolean lvt_9_1_ = enderreeper.isInvisible();
            GlStateManager.depthMask(!lvt_9_1_);
            this.bindTexture(LIGHTNING_TEXTURE);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float lvt_10_1_ = (float) enderreeper.ticksExisted + p_212842_4_;
            GlStateManager.translatef(lvt_10_1_ * 0.01F, lvt_10_1_ * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float lvt_11_1_ = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE.ONE, GlStateManager.DestFactor.ONE.ONE);
            ((EnderreeperModel) this.getEntityModel()).setModelAttributes(this.EnderreeperModel);
            GameRenderer lvt_12_1_ = Minecraft.getInstance().gameRenderer;
            lvt_12_1_.setupFogColor(true);
            this.EnderreeperModel.render(enderreeper, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
            lvt_12_1_.setupFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}