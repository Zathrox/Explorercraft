package com.zathrox.explorercraft.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.zathrox.explorercraft.client.model.InfectedZombieModel;
import com.zathrox.explorercraft.common.entity.InfectedZombieEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfectedZombieRenderer extends BipedRenderer<InfectedZombieEntity, InfectedZombieModel<InfectedZombieEntity>> {
    private static final ResourceLocation INFESTED_ZOMBIE_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/zombie/jungle_zombie.png");

    public InfectedZombieRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InfectedZombieModel<>(), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, new InfectedZombieModel(0.5F, true), new InfectedZombieModel(1.0F, true)));
    }

    @Override
    protected void applyRotations(InfectedZombieEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        if (entityLiving.isDrowning()) {
            rotationYaw += (float) (Math.cos((double) entityLiving.ticksExisted * 3.25D) * Math.PI * 0.25D);
        }

        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    public ResourceLocation getEntityTexture(InfectedZombieEntity entity) {
        return INFESTED_ZOMBIE_TEXTURES;
    }
}
