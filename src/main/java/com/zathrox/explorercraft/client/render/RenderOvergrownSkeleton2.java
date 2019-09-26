package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.ModelOvergrownSkeleton2;
import com.zathrox.explorercraft.common.entity.EntityOvergrownSkeleton2;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderOvergrownSkeleton2 extends BipedRenderer<EntityOvergrownSkeleton2, ModelOvergrownSkeleton2<EntityOvergrownSkeleton2>> {
    private static final ResourceLocation OG_SKELETON_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/skeleton/test.png");

    public RenderOvergrownSkeleton2(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelOvergrownSkeleton2<>(), 0.5F);
        //this.addLayer(new OvergrownLayer(this));
        //this.addLayer(new MushroomLayer(this));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer<>(this, new ModelOvergrownSkeleton2(0.5F, true), new ModelOvergrownSkeleton2(1.0F, true)));
    }

    protected ResourceLocation getEntityTexture(EntityOvergrownSkeleton2 entity) {
        return OG_SKELETON_TEXTURES;
    }
}
