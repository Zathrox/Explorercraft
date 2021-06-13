package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ExplorerToolList implements IItemTier {

    /* Harvest Level, Durability, Efficiency, Damage and then Enchantability
    WOOD(0, 59, 2.0F, 0.0F, 15
    STONE(1, 131, 4.0F, 1.0F, 5
    IRON(2, 250, 6.0F, 2.0F, 14
    DIAMOND(3, 1561, 8.0F, 3.0F, 10
    GOLD(0, 32, 12.0F, 0.0F, 22
    */

    AMETHYST(2.0F, 6.0F, 550, 2, 5, BambooGroveItems.AMETHYST.get()),
    JADE(3.0F, 6.0F, 1100, 3, 30, BambooGroveItems.JADE.get()),
    RUBY(2.5F, 12.0F, 450, 3, 15, BambooGroveItems.RUBY.get());

    private final float attackDamage, efficiency;
    private final int durability, harvestLevel, enchantability;
    private Ingredient repairMaterial;

    ExplorerToolList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial) {
        this.attackDamage = attackDamage;
        this.efficiency = efficiency;
        this.durability = durability;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = Ingredient.of(repairMaterial);
    }

    @Override
    public int getUses() {
        return this.durability;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial;
    }
}
