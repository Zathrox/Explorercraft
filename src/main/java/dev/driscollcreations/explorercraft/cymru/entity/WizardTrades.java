package dev.driscollcreations.explorercraft.cymru.entity;

import com.google.common.collect.ImmutableMap;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.items.CymruItems;
import dev.driscollcreations.explorercraft.setup.ExplorerBannerPattern;
import dev.driscollcreations.explorercraft.util.ExplorerTrades;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WizardTrades {

    private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> WIZARD_TRADES = toIntMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
            new ItemsForGemTrade(Items.SEA_PICKLE, 2, 1, 5, 1),
            new ItemsForGemTrade(Items.SLIME_BALL, 4, 1, 5, 1),
            new ItemsForGemTrade(Items.GLOWSTONE, 2, 1, 5, 1),
            new ItemsForGemTrade(Items.NAUTILUS_SHELL, 5, 1, 5, 1),
            new ItemsForGemTrade(Items.FERN, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.SUGAR_CANE, 1, 1, 8, 1),
            new ItemsForGemTrade(Items.PUMPKIN, 1, 1, 4, 1),
            new ItemsForGemTrade(Items.KELP, 3, 1, 12, 1),
            new ItemsForGemTrade(Items.CACTUS, 3, 1, 8, 1),
            new ItemsForGemTrade(Items.POPPY, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.BLUE_ORCHID, 1, 1, 8, 1),
            new ItemsForGemTrade(Items.ALLIUM, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.AZURE_BLUET, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.RED_TULIP, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.ORANGE_TULIP, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.WHITE_TULIP, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.PINK_TULIP, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.OXEYE_DAISY, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.OXEYE_DAISY, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.CORNFLOWER, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.LILY_OF_THE_VALLEY, 1, 1, 7, 1),
            new ItemsForGemTrade(Items.WHEAT_SEEDS, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.BEETROOT_SEEDS, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.PUMPKIN_SEEDS, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.MELON_SEEDS, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.ACACIA_SAPLING, 5, 1, 8, 1),
            new ItemsForGemTrade(Items.BIRCH_SAPLING, 5, 1, 8, 1),
            new ItemsForGemTrade(Items.DARK_OAK_SAPLING, 5, 1, 8, 1),
            new ItemsForGemTrade(Items.JUNGLE_SAPLING, 5, 1, 8, 1),
            new ItemsForGemTrade(Items.OAK_SAPLING, 5, 1, 8, 1),
            new ItemsForGemTrade(Items.SPRUCE_SAPLING, 5, 1, 8, 1),
            new ItemsForGemTrade(Items.VINE, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.BROWN_MUSHROOM, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.RED_MUSHROOM, 1, 1, 12, 1),
            new ItemsForGemTrade(Items.LILY_PAD, 1, 2, 5, 1),
            new ItemsForGemTrade(Items.GUNPOWDER, 1, 1, 8, 1),
            new ItemsForGemTrade(Items.GHAST_TEAR, 2, 1, 3, 1),
            new ItemsForGemTrade(Items.FERMENTED_SPIDER_EYE, 1, 1, 3, 1),
            new ItemsForGemTrade(Items.BAMBOO, 3, 1, 12, 1),
    }, 2, new VillagerTrades.ITrade[]{
            //==== Explorercraft Items ===//
            new ItemsForGemTrade(BambooGroveItems.RICE.get(), 2, 1, 5, 1),
            new ItemsForGemTrade(CymruItems.LEEK.get(), 1, 1, 12, 1),
            new ItemsForGemTrade(CymruItems.CHEESE.get(), 1, 1, 12, 1),
            new ItemsForGemTrade(CymruItems.DRIED_FRUIT.get(), 1, 1, 12, 1),
            new ItemsForGemTrade(BambooGroveBlocks.TATAMI.get(), 5, 1, 1, 1),
            new ItemsForGemTrade(CymruBlocks.DAFFODIL.get(), 1, 1, 8, 1),
            new ItemsForGemTrade(CymruBlocks.ASH_SAPLING.get(), 10, 1, 8, 1),
            new ItemsForGemTrade(BambooGroveBlocks.BAMBOO_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForGemTrade(BambooGroveBlocks.CHERRY_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForGemTrade(BambooGroveBlocks.MAPLE_SAPLING.get(), 5, 1, 8, 1),
            new ItemsForGemTrade(VanillaTweaksBlocks.NOCTILUCAS.get(), 5, 1, 8, 1)
    }, 3, new VillagerTrades.ITrade[]{
            new ExplorerTrades.ExplorerTrade(1, ExplorerBannerPattern.PATTERN_ITEMS.get(ExplorerBannerPattern.WALES).get(), 1, 3, 1),
            new ExplorerTrades.ExplorerTrade(1, ExplorerBannerPattern.PATTERN_ITEMS.get(ExplorerBannerPattern.WELSH_FLAG).get(), 1, 3, 1),
            new Trade(new ItemStack(CymruBlocks.DRAGON_HEART.get(), 1), new ItemStack(Items.IRON_INGOT, 16),  new ItemStack(CymruItems.WELSH_SHIELD.get()), 1, 5, 5),
            new Trade(new ItemStack(CymruBlocks.DRAGON_HEART.get(), 1), new ItemStack(CymruBlocks.SLATE_CHISELED.get(), 64),  new ItemStack(CymruBlocks.SLATE_WELSH.get(), 64), 1, 5, 5),
    }));

    static class Trade implements VillagerTrades.ITrade
    {
        private final ItemStack firstBuyStack;
        private final ItemStack secondBuyStack;
        private final ItemStack sellingStack;
        private final int maxUses;
        private final int experience;
        private final float priceMultiplier;

        public Trade(ItemStack firstBuyStack, ItemStack sellingStack, int maxUses, int experience, float priceMultiplier)
        {
            this.firstBuyStack = firstBuyStack;
            this.secondBuyStack = ItemStack.EMPTY;
            this.sellingStack = sellingStack;
            this.maxUses = maxUses;
            this.experience = experience;
            this.priceMultiplier = priceMultiplier;
        }

        public Trade(ItemStack firstBuyStack, ItemStack secondBuyStack, ItemStack sellingStack, int maxUses, int experience, float priceMultiplier)
        {
            this.firstBuyStack = firstBuyStack;
            this.secondBuyStack = secondBuyStack;
            this.sellingStack = sellingStack;
            this.maxUses = maxUses;
            this.experience = experience;
            this.priceMultiplier = priceMultiplier;
        }

        @Override
        public MerchantOffer getOffer(Entity trader, Random rand)
        {
            this.firstBuyStack.setCount(1);
            if(!this.secondBuyStack.isEmpty())
            {
                this.secondBuyStack.setCount(MathHelper.clamp(Math.max(this.secondBuyStack.getCount() - 4, 1) + rand.nextInt(8) + 1, 1, 64));
            }
            return new MerchantOffer(this.firstBuyStack, this.secondBuyStack, this.sellingStack, this.maxUses, this.experience, this.priceMultiplier);
        }
    }

    public static class ItemsForGemTrade implements VillagerTrades.ITrade {
        private final ItemStack buyingItem;
        private final int amountOfGems;
        private final int buyingItemAmount;
        private final int maxUses;
        private final int experience;
        private final float field_221213_f;

        public ItemsForGemTrade(Block block, int amountOfGems, int buyingItemAmount, int maxUses, int p_i50528_5_) {
            this(new ItemStack(block), amountOfGems, buyingItemAmount, maxUses, p_i50528_5_);
        }

        public ItemsForGemTrade(Item item, int amountOfGems, int buyingItemAmount, int maxUses) {
            this((ItemStack)(new ItemStack(item)), amountOfGems, buyingItemAmount, 12, maxUses);
        }

        public ItemsForGemTrade(Item item, int amountOfGems, int buyingItemAmount, int maxUses, int p_i50530_5_) {
            this(new ItemStack(item), amountOfGems, buyingItemAmount, maxUses, p_i50530_5_);
        }

        public ItemsForGemTrade(ItemStack buyingItem, int amountOfGems, int buyingItemAmount, int maxUses, int p_i50531_5_) {
            this(buyingItem, amountOfGems, buyingItemAmount, maxUses, p_i50531_5_, 0.05F);
        }

        public ItemsForGemTrade(ItemStack buyingItem, int amountOfGems, int buyingItemAmount, int maxUses, int experience, float p_i50532_6_) {
            this.buyingItem = buyingItem;
            this.amountOfGems = amountOfGems;
            this.buyingItemAmount = buyingItemAmount;
            this.maxUses = maxUses;
            this.experience = experience;
            this.field_221213_f = p_i50532_6_;
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity entity, Random random) {
            int j = random.nextInt(getGems().size());
            return new MerchantOffer(new ItemStack(this.buyingItem.getItem(), this.buyingItemAmount), new ItemStack(getGems().get(j), this.amountOfGems), this.maxUses, this.experience, this.field_221213_f);

        }
    }

    public static List<Item> getGems() {
        List<Item> gemList = new ArrayList<>();
        gemList.add(Items.DIAMOND);
        gemList.add(Items.EMERALD);
        gemList.add(BambooGroveItems.AMETHYST.get());
        gemList.add(BambooGroveItems.JADE.get());
        gemList.add(BambooGroveItems.RUBY.get());
        return gemList;
    }

    public static ItemStack createWelshFlagBanner() {
        ItemStack banner = new ItemStack(Items.WHITE_BANNER);
        CompoundNBT tag = banner.getOrCreateTagElement("BlockEntityTag");
        ListNBT lvt_2_1_ = (new BannerPattern.Builder()).addPattern(BannerPattern.HALF_VERTICAL, DyeColor.GREEN).addPattern(ExplorerBannerPattern.WELSH_FLAG, DyeColor.RED).toListTag();
        tag.put("Patterns", lvt_2_1_);
        banner.setHoverName((new TranslationTextComponent("block.explorercraf.welsh_flag", new Object[0])).withStyle(TextFormatting.DARK_RED));
        return banner;
    }

}
