package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.WizardModel;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WizardRenderer extends LivingRenderer {
    private static final ResourceLocation WIZARD_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID + ":textures/entity/wizard.png");

    public WizardRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WizardModel(), 0.75F);
    }

    @Override
    protected boolean canRenderName(Entity entity) {
        return true;
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {

        return WIZARD_TEXTURES;
    }
}
