package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.SkeletaurModel;
import com.zathrox.explorercraft.common.entity.SkeletaurEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletaurRenderer extends MobRenderer<SkeletaurEntity, SkeletaurModel<SkeletaurEntity>> {
    private static final ResourceLocation OG_SKELETON_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/skeletaur.png");

    public SkeletaurRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletaurModel<>(), 0.5F);
    }

    public ResourceLocation getEntityTexture(SkeletaurEntity entity) {
        return OG_SKELETON_TEXTURES;
    }
}
