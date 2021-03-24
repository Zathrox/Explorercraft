package dev.driscollcreations.explorercraft.vanillatweaks.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.items.JadeBowItem;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.setup.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class VanillaTweaksItems {

    public static final Item.Properties basicItemProps = new Item.Properties().tab(ExplorerItemGroups.EXPLORERCRAFT);

    //======== Food and Misc items
    public static final RegistryObject<Item> NOCTILUCA = Registration.ITEMS.register("noctiluca", () -> new BlockNamedItem(VanillaTweaksBlocks.NOCTILUCAS.get(), new Item.Properties().food(ExplorerFoods.NOCTILUCA).tab(ExplorerItemGroups.EXPLORERCRAFT)));




    public static void register() {}
}
