package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.InfestedSkeletonModel;
import com.zathrox.explorercraft.common.entity.InfestedSkeletonEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfestedSkeletonRenderer extends BipedRenderer<InfestedSkeletonEntity, InfestedSkeletonModel<InfestedSkeletonEntity>> {
    private static final ResourceLocation OG_SKELETON_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/skeleton/jungle_skeleton.png");

    public InfestedSkeletonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InfestedSkeletonModel<>(), 0.5F);
        //this.addLayer(new OvergrownLayer(this));

        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new InfestedSkeletonModel(0.5F, true), new InfestedSkeletonModel(1.0F, true)));
    }

    protected ResourceLocation getEntityTexture(InfestedSkeletonEntity entity) {
        return OG_SKELETON_TEXTURES;
    }
}
