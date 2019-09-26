package com.zathrox.explorercraft.client.render.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.InfestedSkeletonModel;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OvergrownLayer<T extends MobEntity & IRangedAttackMob, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    private static final ResourceLocation OVERGROWN_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/skeleton/overgrown_overlay.png");
    private final InfestedSkeletonModel<T> layerModel = new InfestedSkeletonModel(0.0F, true);

    public OvergrownLayer(IEntityRenderer<T, M> p_i50919_1_) {
        super(p_i50919_1_);
    }

    public void render(T p_212842_1_, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        this.bindTexture(OVERGROWN_TEXTURES);
        this.getEntityModel().setModelAttributes(this.layerModel);
        this.layerModel.setLivingAnimations(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_4_);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.layerModel.render(p_212842_1_, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, 0.062f);
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
