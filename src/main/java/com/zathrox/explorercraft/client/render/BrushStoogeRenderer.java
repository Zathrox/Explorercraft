package com.zathrox.explorercraft.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.zathrox.explorercraft.client.model.BrushStoogeModel;
import com.zathrox.explorercraft.common.entity.BrushStoogeEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BrushStoogeRenderer<T extends LivingEntity> extends MobRenderer<BrushStoogeEntity, BrushStoogeModel<BrushStoogeEntity>> {
    private final EntityModel<T> stoogeModel = new BrushStoogeModel<>();
    private static final ResourceLocation BRUSH_STOOGE_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID,"textures/entity/stooge_skin.png");

    public BrushStoogeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BrushStoogeModel<>(), 0.3F);
        //this.addLayer(new StoogeGelLayer<>(this));
    }

    @Override
    public void render(BrushStoogeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        this.shadowSize = 0.25F;
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    protected float getDeathMaxRotation(BrushStoogeEntity entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getEntityTexture(BrushStoogeEntity entity) {
        return BRUSH_STOOGE_TEXTURES;
    }
}
