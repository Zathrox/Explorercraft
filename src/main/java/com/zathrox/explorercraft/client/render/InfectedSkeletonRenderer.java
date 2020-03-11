package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.InfectedSkeletonModel;
import com.zathrox.explorercraft.common.entity.InfectedSkeletonEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfectedSkeletonRenderer extends BipedRenderer<InfectedSkeletonEntity, InfectedSkeletonModel<InfectedSkeletonEntity>> {
    private static final ResourceLocation OG_SKELETON_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/skeleton/jungle_skeleton.png");

    public InfectedSkeletonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InfectedSkeletonModel<>(), 0.5F);
        //this.addLayer(new OvergrownLayer(this));

        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new InfectedSkeletonModel(0.5F, true), new InfectedSkeletonModel(1.0F, true)));
    }

    public ResourceLocation getEntityTexture(InfectedSkeletonEntity entity) {
        return OG_SKELETON_TEXTURES;
    }
}
