package dev.driscollcreations.explorercraft.bamboogrove.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.items.JadeBowItem;
import dev.driscollcreations.explorercraft.setup.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.fmllegacy.RegistryObject;

public class BambooGroveItems {

    public static final Item.Properties basicItemProps = new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT);
    public static final Item.Properties singleStackItemProps = new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).stacksTo(0);

    //======== Foods and Misc items
    public static final RegistryObject<Item> RICE = Registration.ITEMS.register("rice", () -> new ItemNameBlockItem(BambooGroveBlocks.RICE_BASE.get(), new Item.Properties().food(ExplorerFoods.RICE).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> RICE_STRAW = Registration.ITEMS.register("rice_straw", () -> new Item(basicItemProps));
    public static final RegistryObject<Item> CHERRY_BLOSSOM = Registration.ITEMS.register("cherry_blossom", () -> new Item(basicItemProps));

    public static final RegistryObject<Item> RICE_STEW = Registration.ITEMS.register("rice_bowl", () -> new BowlFoodItem(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.RICE_BOWL).stacksTo(1)));
    public static final RegistryObject<Item> ONIGIRI = Registration.ITEMS.register("onigiri", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.ONIGIRI)));
    public static final RegistryObject<Item> SALMON_SUSHI = Registration.ITEMS.register("salmon_sushi", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.SALMON_SUSHI)));
    public static final RegistryObject<Item> TAMAGO_SUSHI = Registration.ITEMS.register("tamago_sushi", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.TAMAGO_SUSHI)));

    public static final RegistryObject<Item> BAMBOO_SIGN = Registration.ITEMS.register("bamboo_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT), BambooGroveBlocks.BAMBOO_STANDING_SIGN.get(), BambooGroveBlocks.BAMBOO_WALL_SIGN.get()));
    public static final RegistryObject<Item> CHERRY_SIGN = Registration.ITEMS.register("cherry_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT), BambooGroveBlocks.CHERRY_STANDING_SIGN.get(), BambooGroveBlocks.CHERRY_WALL_SIGN.get()));
    public static final RegistryObject<Item> CHERRY_BLOSSOM_SIGN = Registration.ITEMS.register("cherry_blossom_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT), BambooGroveBlocks.CHERRY_BLOSSOM_STANDING_SIGN.get(), BambooGroveBlocks.CHERRY_BLOSSOM_WALL_SIGN.get()));
    public static final RegistryObject<Item> MAPLE_SIGN = Registration.ITEMS.register("maple_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT), BambooGroveBlocks.MAPLE_STANDING_SIGN.get(), BambooGroveBlocks.MAPLE_WALL_SIGN.get()));

    //========= Ores
    public static final RegistryObject<Item> AMETHYST = Registration.ITEMS.register("amethyst", () -> new Item(basicItemProps));
    public static final RegistryObject<Item> RUBY = Registration.ITEMS.register("ruby", () -> new Item(basicItemProps));

    ///======== Jade Misc
    public static final RegistryObject<Item> JADE = Registration.ITEMS.register("jade", () -> new Item(basicItemProps));
    public static final RegistryObject<Item> JADE_BOW = Registration.ITEMS.register("jade_bow", () -> new JadeBowItem(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).durability(684)));
    public static final RegistryObject<Item> JADE_HORSE_ARMOR = Registration.ITEMS.register("jade_horse_armor",
            () -> new HorseArmorItem(8, new ResourceLocation(Explorercraft.MOD_ID, "textures/entity/horse/armor/horse_armor_jade.png"), singleStackItemProps));

    //======== Jade Tools
    public static final RegistryObject<Item> JADE_AXE = Registration.ITEMS.register("jade_axe",
            () -> new AxeItem(ExplorerToolList.JADE, 6.0f, -3.1F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_HOE = Registration.ITEMS.register("jade_hoe",
            () -> new HoeItem(ExplorerToolList.JADE, -1, 0.0F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_PICKAXE = Registration.ITEMS.register("jade_pickaxe",
            () -> new PickaxeItem(ExplorerToolList.JADE, 1, -2.8F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_SHOVEL = Registration.ITEMS.register("jade_shovel",
            () -> new ShovelItem(ExplorerToolList.JADE, 1.5F, -3.0F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_SWORD = Registration.ITEMS.register("jade_sword",
            () -> new SwordItem(ExplorerToolList.JADE, 3, -2.4F, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));

    //======== Jade Armor
    public static final RegistryObject<Item> JADE_HELMET = Registration.ITEMS.register("jade_helmet",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlot.HEAD, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_CHESTPLATE = Registration.ITEMS.register("jade_chestplate",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlot.CHEST, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_LEGGINGS = Registration.ITEMS.register("jade_leggings",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlot.LEGS, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> JADE_BOOTS = Registration.ITEMS.register("jade_boots",
            () -> new ArmorItem(ExplorerArmorList.JADE_ARMOR, EquipmentSlot.FEET, new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));


    public static void register() {}
}
