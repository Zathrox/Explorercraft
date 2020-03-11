package com.zathrox.explorercraft.common.items;

import com.zathrox.explorercraft.client.model.WizardHatModel;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class WizardHatItem extends ArmorItem {

    public WizardHatItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        super.addInformation(p_77624_1_, p_77624_2_, tooltip, p_77624_4_);
        tooltip.add(new StringTextComponent("A very Enchanting hat").applyTextStyle(TextFormatting.DARK_PURPLE));
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (itemStack != null) {

            if (itemStack.getItem() instanceof ArmorItem && itemStack.getItem() == ExplorerItems.WIZARD_HAT) {

                WizardHatModel model = new WizardHatModel();
                model.bipedBody.showModel = armorSlot == EquipmentSlotType.HEAD;
                model.isChild = _default.isChild;
                model.isSneak = _default.isSneak;
                model.leftArmPose = _default.leftArmPose;
                model.rightArmPose = _default.rightArmPose;

                return (A) model;
            }
        }

        return null;
    }
}
