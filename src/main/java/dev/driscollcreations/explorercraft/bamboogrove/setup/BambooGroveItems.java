package dev.driscollcreations.explorercraft.bamboogrove.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.items.JadeBowItem;
import dev.driscollcreations.explorercraft.setup.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class BambooGroveItems {

    public static final Item.Properties basicItemProps = new Item.Properties().tab(ExplorerItemGroups.EXPLORERCRAFT);
    public static final Item.Properties singleStackItemProps = new Item.Properties().tab(ExplorerItemGroups.EXPLORERCRAFT).stacksTo(0);

    //======== Food and Misc items
    public static final RegistryObject<Item> RICE = Registration.ITEMS.register("rice", () -> new BlockNamedItem(BambooGroveBlocks.RICE_BASE.get(), new Item.Properties().food(ExplorerFoods.RICE).tab(ExplorerItemGroups.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RICE_STRAW = Registration.ITEMS.register("rice_straw", () -> new Item(basicItemProps));

    public static final RegistryObject<Item> RICE_STEW = Registration.ITEMS.register("rice_bowl", () -> new SoupItem(basicItemProps.food(ExplorerFoods.RICE_BOWL).stacksTo(1)));
    public static final RegistryObject<Item> ONIGIRI = Registration.ITEMS.register("onigiri", () -> new Item(basicItemProps.food(ExplorerFoods.ONIGIRI)));
    public static final RegistryObject<Item> SALMON_SUSHI = Registration.ITEMS.register("salmon_sushi", () -> new Item(basicItemProps.food(ExplorerFoods.SALMON_SUSHI)));
    public static final RegistryObject<Item> TAMAGO_SUSHI = Registration.ITEMS.register("tamago_sushi", () -> new Item(basicItemProps.food(ExplorerFoods.TAMAGO_SUSHI)));


    ///======== Jade Misc
    public static final RegistryObject<Item> JADE = Registration.ITEMS.register("jade", () -> new Item(basicItemProps));
    public static final RegistryObject<Item> JADE_BOW = Registration.ITEMS.register("jade_bow", () -> new JadeBowItem(basicItemProps.durability(684)));
    public static final RegistryObject<Item> JADE_HORSE_ARMOR = Registration.ITEMS.register("jade_horse_armor",
            () -> new HorseArmorItem(8, new ResourceLocation(Explorercraft.MOD_ID, "textures/entities/horse/armor/horse_armor_jade.png"), singleStackItemProps));

    //======== Jade Tools
    public static final RegistryObject<Item> JADE_AXE = Registration.ITEMS.register("jade_axe",
            () -> new AxeItem(ExplorerToolList.JADE, 6.0f, -3.1F, basicItemProps));
    public static final RegistryObject<Item> JADE_HOE = Registration.ITEMS.register("jade_hoe",
            () -> new HoeItem(ExplorerToolList.JADE, -1, 0.0F, basicItemProps));
    public static final RegistryObject<Item> JADE_PICKAXE = Registration.ITEMS.register("jade_pickaxe",
            () -> new PickaxeItem(ExplorerToolList.JADE, 1, -2.8F, basicItemProps));
    public static final RegistryObject<Item> JADE_SHOVEL = Registration.ITEMS.register("jade_shovel",
            () -> new ShovelItem(ExplorerToolList.JADE, 1.5F, -3.0F, basicItemProps));
    public static final RegistryObject<Item> JADE_SWORD = Registration.ITEMS.register("jade_sword",
            () -> new SwordItem(ExplorerToolList.JADE, 3, -2.4F, basicItemProps));

    //======== Jade Armor
    public static final RegistryObject<Item> JADE_HELMET = Registration.ITEMS.register("jade_helmet",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlotType.HEAD, basicItemProps));
    public static final RegistryObject<Item> JADE_CHESTPLATE = Registration.ITEMS.register("jade_chestplate",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlotType.CHEST, basicItemProps));
    public static final RegistryObject<Item> JADE_LEGGINGS = Registration.ITEMS.register("jade_leggings",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlotType.LEGS, basicItemProps));
    public static final RegistryObject<Item> JADE_BOOTS = Registration.ITEMS.register("jade_boots",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlotType.FEET, basicItemProps));


    public static void register() {}
}
