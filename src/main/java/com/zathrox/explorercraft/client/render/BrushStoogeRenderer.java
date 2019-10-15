package com.zathrox.explorercraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.BrushStoogeModel;
import com.zathrox.explorercraft.client.render.layer.StoogeGelLayer;
import com.zathrox.explorercraft.common.entity.BrushStoogeEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class BrushStoogeRenderer extends MobRenderer<BrushStoogeEntity, BrushStoogeModel<BrushStoogeEntity>> {
    private static final ResourceLocation BRUSH_STOOGE_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID,"textures/entity/stooge_skin.png");

    public BrushStoogeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BrushStoogeModel<>(), 0.3F);
        //this.addLayer(new StoogeGelLayer<>(this));
    }

    @Override
    public void doRender(BrushStoogeEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.shadowSize = 0.25F * (float)1F;
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


    @Override
    protected float getDeathMaxRotation(BrushStoogeEntity entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(BrushStoogeEntity entity) {
        return BRUSH_STOOGE_TEXTURES;
    }
}
