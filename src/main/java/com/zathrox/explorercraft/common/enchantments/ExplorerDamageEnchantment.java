package com.zathrox.explorercraft.common.enchantments;

import com.zathrox.explorercraft.core.registry.ExplorerCreatureAttributes;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

public class ExplorerDamageEnchantment extends Enchantment {

    private static final String[] DAMAGE_NAMES = new String[]{"nether", "ender", "illager"};
    private static final int[] MIN_COST = new int[]{1, 5, 5};
    private static final int[] LEVEL_COST = new int[]{11, 8, 8};
    private static final int[] LEVEL_COST_SPAN = new int[]{20, 20, 20};
    public final int damageType;

    public ExplorerDamageEnchantment(Enchantment.Rarity rarityIn, int damageTypeIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.WEAPON, slots);
        this.damageType = damageTypeIn;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel) {
        return MIN_COST[this.damageType] + (enchantmentLevel - 1) * LEVEL_COST[this.damageType];
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + LEVEL_COST_SPAN[this.damageType];
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel() {
        return 5;
    }

    /**
     * Calculates the additional damage that will be dealt by an item with this enchantment. This alternative to
     * calcModifierDamage is sensitive to the targets EnumCreatureAttribute.
     */
    public float calcDamageByCreature(int level, CreatureAttribute creatureType) {
        if (this.damageType == 0  && creatureType == ExplorerCreatureAttributes.NETHER) {
            return 1.0F + (float)Math.max(0, level - 1) * 0.5F;
        } else if (this.damageType == 1 && creatureType == ExplorerCreatureAttributes.ENDER) {
            return (float)level * 2.5F;
        } else {
            return this.damageType == 2 && creatureType == CreatureAttribute.ILLAGER ? (float)level * 2.5F : 0.0F;
        }
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof DamageEnchantment || ench instanceof ExplorerDamageEnchantment);
    }

    /**
     * Determines if this enchantment can be applied to a specific ItemStack.
     */
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof AxeItem ? true : super.canApply(stack);
    }

    /**
     * Called whenever a mob is damaged with an item that has this enchantment on it.
     */
    public void onEntityDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)target;
            if (this.damageType == 2 && livingentity.getCreatureAttribute() == CreatureAttribute.ILLAGER) {
                int i = 20 + user.getRNG().nextInt(10 * level);
                livingentity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, i, 3));
            }
        }

    }
}
