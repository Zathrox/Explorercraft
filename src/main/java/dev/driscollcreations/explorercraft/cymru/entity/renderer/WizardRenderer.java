package dev.driscollcreations.explorercraft.cymru.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.cymru.entity.WizardEntity;
import dev.driscollcreations.explorercraft.cymru.entity.model.WizardModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WizardRenderer extends MobRenderer<WizardEntity, WizardModel<WizardEntity>> {
    private static final ResourceLocation WIZARD_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID + ":textures/entity/wizard.png");

    public WizardRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WizardModel<>(), 0.75F);
    }

    @Override
    protected boolean shouldShowName(WizardEntity p_177070_1_) {
        return true;
    }

    @Override
    public ResourceLocation getTextureLocation(WizardEntity wizardEntity) {
        return WIZARD_TEXTURES;
    }

    protected void scale(WizardRenderer p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        float f = 0.9375F;
        p_225620_2_.scale(0.9375F, 0.9375F, 0.9375F);
    }
}