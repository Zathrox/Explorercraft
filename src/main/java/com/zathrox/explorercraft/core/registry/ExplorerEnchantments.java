package com.zathrox.explorercraft.core.registry;

import com.zathrox.explorercraft.common.enchantments.ExplorerDamageEnchantment;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = new DeferredRegister<>(ForgeRegistries.ENCHANTMENTS, Explorercraft.MOD_ID);

    public static final RegistryObject<Enchantment> NETHERBANE = ENCHANTMENTS.register("netherbane", () -> new ExplorerDamageEnchantment(Enchantment.Rarity.RARE, 0, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> ENDERBANE = ENCHANTMENTS.register("enderbane", () -> new ExplorerDamageEnchantment(Enchantment.Rarity.RARE, 1, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> ILLIAGERBANE = ENCHANTMENTS.register("illiagerbane", () -> new ExplorerDamageEnchantment(Enchantment.Rarity.RARE, 2, EquipmentSlotType.MAINHAND));

}
