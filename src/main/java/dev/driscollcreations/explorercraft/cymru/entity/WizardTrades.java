package dev.driscollcreations.explorercraft.cymru.entity;

import com.google.common.collect.ImmutableMap;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.items.CymruItems;
import dev.driscollcreations.explorercraft.setup.ExplorerBannerPattern;
import dev.driscollcreations.explorercraft.util.ExplorerTrades;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class WizardTrades {

    private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> WIZARD_TRADES = toIntMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_SAPLING.get().asItem(), 1, 3, 1),
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_BLOSSOM_BUTTON.get().asItem(), 1, 3, 1),
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_BLOSSOM_FENCE.get().asItem(), 1, 3, 1),
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_BLOSSOM_STAIRS.get().asItem(), 1, 3, 1),
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_BLOSSOM_WALL_SIGN.get().asItem(), 1, 3, 1)
    }, 2, new VillagerTrades.ITrade[]{
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.MAPLE_SAPLING.get().asItem(), 1, 3, 1),
            new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.MAPLE_LOG.get().asItem(), 1, 3, 1)
    }, 3, new VillagerTrades.ITrade[]{
            new ExplorerTrades.ExplorerTrade(1, ExplorerBannerPattern.PATTERN_ITEMS.get(ExplorerBannerPattern.WALES).get(), 1, 3, 1)

    }));

}
