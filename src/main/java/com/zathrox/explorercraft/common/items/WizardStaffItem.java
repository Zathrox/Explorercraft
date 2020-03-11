package com.zathrox.explorercraft.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WizardStaffItem extends Item {

    public WizardStaffItem(Properties p_i48501_1_) {
        super(p_i48501_1_);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack item = playerIn.getHeldItem(handIn);
        playerIn.getCooldownTracker().setCooldown(this, 20);
        if (!worldIn.isRemote) {

            Vec3d aim = playerIn.getLookVec();
            FireballEntity fireball = new FireballEntity(worldIn, playerIn, 1, 1, 1);

            fireball.setPosition(playerIn.getPosX() + aim.x * 1.5D, playerIn.getPosY() + 1 + aim.y * 1.5D, playerIn.getPosZ() + 1 + aim.z * 1.5D);
            fireball.accelerationX = aim.x * 0.1;
            fireball.accelerationY = aim.y * 0.1;
            fireball.accelerationZ = aim.z * 0.1;
            item.damageItem(10, playerIn, (p_220047_1_) -> {
                p_220047_1_.sendBreakAnimation(playerIn.getActiveHand());
            });
            worldIn.addEntity(fireball);
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, item);
        } else {
            return new ActionResult<ItemStack>(ActionResultType.FAIL, item);
        }

    }


    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        return stack.isDamaged();
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {

        return 16753178;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {

        return (double) stack.getDamage() / (double) stack.getMaxDamage();
    }

}
