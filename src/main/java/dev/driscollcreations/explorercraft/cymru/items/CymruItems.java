package dev.driscollcreations.explorercraft.cymru.items;

import dev.driscollcreations.explorercraft.config.CymruConfig;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.setup.ExplorerCreativeModeTabs;
import dev.driscollcreations.explorercraft.setup.ExplorerFoods;
import dev.driscollcreations.explorercraft.setup.ExplorerRarity;
import dev.driscollcreations.explorercraft.setup.Registration;
import net.minecraft.world.item.*;
import net.minecraftforge.fmllegacy.RegistryObject;

public class CymruItems {

    //==== Cymru food and crop items
    public static final RegistryObject<Item> LEEK = Registration.ITEMS.register("leek", () -> new ItemNameBlockItem(CymruBlocks.LEEKS.get(), new Item.Properties().food(ExplorerFoods.LEEK).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT)));
    public static final RegistryObject<Item> LEEK_STEW = Registration.ITEMS.register("leek_bowl", () -> new BowlFoodItem(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.LEEK_STEW).stacksTo(1)));
    public static final RegistryObject<Item> CAWL_STEW = Registration.ITEMS.register("cawl_bowl", () -> new BowlFoodItem(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH).food(ExplorerFoods.CAWL_STEW).stacksTo(CymruConfig.cawlStackSize.get())));
    public static final RegistryObject<Item> DRIED_FRUIT = Registration.ITEMS.register("dried_fruits", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.DRIED_FRUIT)));
    public static final RegistryObject<Item> CHEESE = Registration.ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.CHEESE)));
    public static final RegistryObject<Item> WELSH_CAKES = Registration.ITEMS.register("welsh_cakes", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH).food(ExplorerFoods.WELSH_CAKES)));
    public static final RegistryObject<Item> WELSH_RAREBIT = Registration.ITEMS.register("welsh_rarebit", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH).food(ExplorerFoods.WELSH_RAREBIT)));
    public static final RegistryObject<Item> LAMB_SHANK = Registration.ITEMS.register("lamb_shank_raw", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.LAMB_SHANK)));
    public static final RegistryObject<Item> COOKED_LAMB_SHANK = Registration.ITEMS.register("lamb_shank_cooked", () -> new Item(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).food(ExplorerFoods.COOKED_LAMB_SHANK)));

    // Ash
    public static final RegistryObject<Item> ASH_SIGN = Registration.ITEMS.register("ash_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT), CymruBlocks.ASH_STANDING_SIGN.get(), CymruBlocks.ASH_WALL_SIGN.get()));

    //==== Cymru mystic items
    public static final RegistryObject<Item> WELSH_SHIELD = Registration.ITEMS.register("welsh_shield", () -> new ShieldItem(new Item.Properties().tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH).durability(336)));
/*
    public static final RegistryObject<BannerPatternItem> WELSHFLAG_BANNER_ITEM = Registration.ITEMS.register("welshflag_banner_pattern", () -> new BannerPatternItem(ExplorerBannerPattern.WELSH_FLAG, (new Item.Properties().stacksTo(1).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH))));
    public static final RegistryObject<BannerPatternItem> WALES_BANNER_ITEM = Registration.ITEMS.register("wales_banner_pattern", () -> new BannerPatternItem(ExplorerBannerPattern.WALES, (new Item.Properties().stacksTo(1).tab(ExplorerCreativeModeTabs.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH))));
*/

    public static void register() {}
}