package com.zathrox.explorercraft.client.render.layer;

import com.zathrox.explorercraft.client.model.EnderreeperModel;
import com.zathrox.explorercraft.common.entity.EnderreeperEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class EnderreeperChargeLayer extends EnergyLayer<EnderreeperEntity, EnderreeperModel<EnderreeperEntity>> {
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final EnderreeperModel<EnderreeperEntity> EnderreeperModel = new EnderreeperModel(2.0F);

    public EnderreeperChargeLayer(IEntityRenderer<EnderreeperEntity, EnderreeperModel<EnderreeperEntity>> p_i50947_1_) {
        super(p_i50947_1_);
    }

    protected float func_225634_a_(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    protected ResourceLocation func_225633_a_() {
        return LIGHTNING_TEXTURE;
    }

    protected EntityModel<EnderreeperEntity> func_225635_b_() {
        return this.EnderreeperModel;
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}