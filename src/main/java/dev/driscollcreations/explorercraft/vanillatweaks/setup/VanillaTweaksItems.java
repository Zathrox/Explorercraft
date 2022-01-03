package dev.driscollcreations.explorercraft.vanillatweaks.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.setup.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fmllegacy.RegistryObject;

public class VanillaTweaksItems {

    public static final Item.Properties basicItemProps = new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT);
    public static final Item.Properties singleStackItemProps = new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).stacksTo(0);

    //======== Foods and Misc items
    public static final RegistryObject<Item> NOCTILUCA = Registration.ITEMS.register("noctiluca", () -> new ItemNameBlockItem(VanillaTweaksBlocks.NOCTILUCAS.get(), new Item.Properties().food(ExplorerFoods.NOCTILUCA).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    //======= Amethyst
    public static final RegistryObject<Item> AMETHYST_HORSE_ARMOR = Registration.ITEMS.register("amethyst_horse_armor",
            () -> new HorseArmorItem(8, new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/horse/armor/horse_armor_amethyst.png"), singleStackItemProps));

    //======== Amethyst Tools
    public static final RegistryObject<Item> AMETHYST_AXE = Registration.ITEMS.register("amethyst_axe",
            () -> new AxeItem(ExplorerToolList.AMETHYST, 6.0f, -3.1F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_HOE = Registration.ITEMS.register("amethyst_hoe",
            () -> new HoeItem(ExplorerToolList.AMETHYST, -1, 0.0F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = Registration.ITEMS.register("amethyst_pickaxe",
            () -> new PickaxeItem(ExplorerToolList.AMETHYST, 1, -2.8F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_SHOVEL = Registration.ITEMS.register("amethyst_shovel",
            () -> new ShovelItem(ExplorerToolList.AMETHYST, 1.5F, -3.0F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_SWORD = Registration.ITEMS.register("amethyst_sword",
            () -> new SwordItem(ExplorerToolList.AMETHYST, 3, -2.4F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    //======== Amethyst Armor
    public static final RegistryObject<Item> AMETHYST_HELMET = Registration.ITEMS.register("amethyst_helmet",
            () -> new ArmorItem(ExplorerArmorList.AMETHYST_ARMOR, EquipmentSlot.HEAD, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = Registration.ITEMS.register("amethyst_chestplate",
            () -> new ArmorItem(ExplorerArmorList.AMETHYST_ARMOR, EquipmentSlot.CHEST, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = Registration.ITEMS.register("amethyst_leggings",
            () -> new ArmorItem(ExplorerArmorList.AMETHYST_ARMOR, EquipmentSlot.LEGS, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> AMETHYST_BOOTS = Registration.ITEMS.register("amethyst_boots",
            () -> new ArmorItem(ExplorerArmorList.AMETHYST_ARMOR, EquipmentSlot.FEET, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    ///======== Ruby Misc
    public static final RegistryObject<Item> RUBY_HORSE_ARMOR = Registration.ITEMS.register("ruby_horse_armor",
            () -> new HorseArmorItem(8, new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/horse/armor/horse_armor_ruby.png"), singleStackItemProps));

    //======== Ruby Tools
    public static final RegistryObject<Item> RUBY_AXE = Registration.ITEMS.register("ruby_axe",
            () -> new AxeItem(ExplorerToolList.RUBY, 6.0f, -3.1F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_HOE = Registration.ITEMS.register("ruby_hoe",
            () -> new HoeItem(ExplorerToolList.RUBY, -1, 0.0F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_PICKAXE = Registration.ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ExplorerToolList.RUBY, 1, -2.8F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_SHOVEL = Registration.ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ExplorerToolList.RUBY, 1.5F, -3.0F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_SWORD = Registration.ITEMS.register("ruby_sword",
            () -> new SwordItem(ExplorerToolList.RUBY, 3, -2.4F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    //======== Ruby Armor
    public static final RegistryObject<Item> RUBY_HELMET = Registration.ITEMS.register("ruby_helmet",
            () -> new ArmorItem(ExplorerArmorList.RUBY_ARMOR, EquipmentSlot.HEAD, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_CHESTPLATE = Registration.ITEMS.register("ruby_chestplate",
            () -> new ArmorItem(ExplorerArmorList.RUBY_ARMOR, EquipmentSlot.CHEST, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_LEGGINGS = Registration.ITEMS.register("ruby_leggings",
            () -> new ArmorItem(ExplorerArmorList.RUBY_ARMOR, EquipmentSlot.LEGS, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RUBY_BOOTS = Registration.ITEMS.register("ruby_boots",
            () -> new ArmorItem(ExplorerArmorList.RUBY_ARMOR, EquipmentSlot.FEET, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    public static final RegistryObject<ForgeSpawnEggItem> ENDERREEPER_SPAWN_EGG = Registration.ITEMS.register("enderreeper_spawn_egg",
            () -> new ForgeSpawnEggItem(ExplorerEntities.ENDERREEPER, 3801171, 7078066, new Item.Properties().stacksTo(1).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    public static void register() {}
}
