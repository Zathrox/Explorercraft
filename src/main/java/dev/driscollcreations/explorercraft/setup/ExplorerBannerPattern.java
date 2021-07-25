package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.Maps;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.BannerPattern;
import net.minecraftforge.fml.RegistryObject;

import java.util.Map;

public class ExplorerBannerPattern {

    private static final Item.Properties PATTERN_PROPS = new Item.Properties().stacksTo(1).tab(ExplorerItemGroups.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH);
    public static final Map<BannerPattern, RegistryObject<BannerPatternItem>> PATTERN_ITEMS = Maps.newHashMap();
    public static BannerPattern WELSH_FLAG;
    public static BannerPattern WALES;

    public static void init() {
        WELSH_FLAG = addBanner("welshflag", true);
        WALES = addBanner("wales", true);
    }

    public static BannerPattern addBanner(String name, Boolean requiresItem) {
        BannerPattern pattern = BannerPattern.create(name.toUpperCase(), name, "explorercraft." + name, true);
        if (requiresItem) {
            String itemName = name + "_banner_pattern";
            RegistryObject<BannerPatternItem> item = Registration.ITEMS.register(itemName, () -> new BannerPatternItem(pattern, PATTERN_PROPS));
            PATTERN_ITEMS.put(pattern, item);
        }
        return pattern;
    }

    /*
    public static ItemStack getLeaderBannerInstance() {
      ItemStack itemstack = new ItemStack(Items.WHITE_BANNER);
      CompoundNBT compoundnbt = itemstack.getOrCreateTagElement("BlockEntityTag");
      ListNBT listnbt = (new BannerPattern.Builder()).addPattern(BannerPattern.RHOMBUS_MIDDLE, DyeColor.CYAN).addPattern(BannerPattern.STRIPE_BOTTOM, DyeColor.LIGHT_GRAY).addPattern(BannerPattern.STRIPE_CENTER, DyeColor.GRAY).addPattern(BannerPattern.BORDER, DyeColor.LIGHT_GRAY).addPattern(BannerPattern.STRIPE_MIDDLE, DyeColor.BLACK).addPattern(BannerPattern.HALF_HORIZONTAL, DyeColor.LIGHT_GRAY).addPattern(BannerPattern.CIRCLE_MIDDLE, DyeColor.LIGHT_GRAY).addPattern(BannerPattern.BORDER, DyeColor.BLACK).toListTag();
      compoundnbt.put("Patterns", listnbt);
      itemstack.hideTooltipPart(ItemStack.TooltipDisplayFlags.ADDITIONAL);
      itemstack.setHoverName((new TranslationTextComponent("block.minecraft.ominous_banner")).withStyle(TextFormatting.GOLD));
      ret
     */

}
