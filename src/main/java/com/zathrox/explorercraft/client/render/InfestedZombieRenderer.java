package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.InfestedSkeletonModel;
import com.zathrox.explorercraft.client.model.InfestedZombieModel;
import com.zathrox.explorercraft.common.entity.InfestedSkeletonEntity;
import com.zathrox.explorercraft.common.entity.InfestedZombieEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfestedZombieRenderer extends BipedRenderer<InfestedZombieEntity, InfestedZombieModel<InfestedZombieEntity>> {
    private static final ResourceLocation INFESTED_ZOMBIE_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/zombie/jungle_zombie.png");

   public InfestedZombieRenderer(EntityRendererManager renderManagerIn) {
       super(renderManagerIn, new InfestedZombieModel<>(), 0.5F);
       this.addLayer(new BipedArmorLayer<>(this, new InfestedZombieModel(0.5F, true), new InfestedZombieModel(1.0F, true)));
   }

   @Override
   protected void applyRotations(InfestedZombieEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
       if (entityLiving.isDrowning()) {
            rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25D) * Math.PI * 0.25D);
       }

       super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }

    @Override
    protected ResourceLocation getEntityTexture(InfestedZombieEntity entity) {
        return INFESTED_ZOMBIE_TEXTURES;
    }
}
