package com.zathrox.explorercraft.client.render.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.InfestedSkeletonModel;
import com.zathrox.explorercraft.common.entity.InfestedSkeletonEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MushroomLayer<T extends InfestedSkeletonEntity> extends LayerRenderer<T, InfestedSkeletonModel<T>> {

    public MushroomLayer(IEntityRenderer<T, InfestedSkeletonModel<T>> p_i50931_1_) {
        super(p_i50931_1_);
    }

    public void render(T p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if (!p_212842_1_.isChild() && !p_212842_1_.isInvisible()) {
            BlockState lvt_9_1_ = Blocks.RED_MUSHROOM.getDefaultState();
            this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.enableCull();
            GlStateManager.cullFace(GlStateManager.CullFace.FRONT);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.2F, 0.75F, 0.5F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            BlockRendererDispatcher lvt_10_1_ = Minecraft.getInstance().getBlockRendererDispatcher();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            lvt_10_1_.renderBlockBrightness(lvt_9_1_, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.1F, 0.75F, -0.6F);
            GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            lvt_10_1_.renderBlockBrightness(lvt_9_1_, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            ((InfestedSkeletonModel)this.getEntityModel()).bipedHead.postRender(0.0625F);
            GlStateManager.scalef(1.0F, -1.0F, 1.0F);
            GlStateManager.translatef(0.0F, 0.7F, -0.2F);
            GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
            lvt_10_1_.renderBlockBrightness(lvt_9_1_, 1.0F);
            GlStateManager.popMatrix();
            GlStateManager.cullFace(GlStateManager.CullFace.BACK);
            GlStateManager.disableCull();
        }
    }

    public boolean shouldCombineTextures() {
        return true;
    }
}
