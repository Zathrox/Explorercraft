package dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.EnderreeperEntity;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.model.EnderreeperTestModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class EnderreeperRenderer extends MobRenderer<EnderreeperEntity, EnderreeperTestModel<EnderreeperEntity>> {

    private static final ResourceLocation ENDERREEPER_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID + ":textures/entity/creeper/enderreeper.png");

    public EnderreeperRenderer(EntityRendererProvider.Context context) {
        super(context, new EnderreeperTestModel<>(context.bakeLayer(EnderreeperTestModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(EnderreeperEntity entity) {
        return ENDERREEPER_TEXTURES;
    }

    @Override
    protected void scale(EnderreeperEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = entitylivingbaseIn.getEnderreeperFlashIntensity(partialTickTime);
        float f1 = (float) (1.0F + Math.sin(f * 100.0F) * f * 0.01F);
        f = Mth.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        matrixStackIn.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(EnderreeperEntity livingEntityIn, float partialTicks) {
        float f = livingEntityIn.getEnderreeperFlashIntensity(partialTicks);
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
    }

}