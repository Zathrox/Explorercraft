package com.zathrox.explorercraft.client.render.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.zathrox.explorercraft.client.model.InfectedCreeperModel;
import com.zathrox.explorercraft.common.entity.InfectedCreeperEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class InfectedCreeperChargeLayer extends EnergyLayer<InfectedCreeperEntity, InfectedCreeperModel<InfectedCreeperEntity>> {
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final InfectedCreeperModel<InfectedCreeperEntity> infectedCreeperModel = new InfectedCreeperModel(2.0F);

    public InfectedCreeperChargeLayer(IEntityRenderer<InfectedCreeperEntity, InfectedCreeperModel<InfectedCreeperEntity>> p_i50947_1_) {
        super(p_i50947_1_);
    }
    protected float func_225634_a_(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    protected ResourceLocation func_225633_a_() {
        return LIGHTNING_TEXTURE;
    }

    protected EntityModel<InfectedCreeperEntity> func_225635_b_() {
        return this.infectedCreeperModel;
    }

}