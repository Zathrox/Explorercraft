package com.zathrox.explorercraft.client.render;

import com.zathrox.explorercraft.client.model.WizardModel;
import com.zathrox.explorercraft.common.entity.WizardEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WizardRenderer extends LivingRenderer
{

	private static final ResourceLocation WIZARD_TEXTURES = new ResourceLocation(Explorercraft.MOD_ID + ":textures/entities/wizard.png");
	
	public WizardRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new WizardModel(), 0.75F);
    }


	protected void renderName(WizardEntity entity, double x, double y, double z)
    {

        double d0 = entity.getDistanceSq(this.renderManager.pointedEntity);
        float f = entity.isSneaking() ? 32.0F : 64.0F;

        if (d0 < (double)(f * f))
        {
            String s = entity.getDisplayName().toString();
            this.renderLivingLabel(entity, s, x, y, z, 64);
        }

    }
	
	public int spellColor(int type)
	{
		switch(type)
		{
			case 0: return 0xFFFFFF;
			case 1: return 0xffd800;
			default: return 0xFFFFFF;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return WIZARD_TEXTURES;
	}
}
