package com.zathrox.explorercraft.client;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Registers {@link IBlockColor}/{@link IItemColor} handlers for this mod's blocks/items.
 *
 * @author Choonster
 */

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, value = Dist.CLIENT)
public class ExplorerColourRegistry {

    /**
     * Register the {@link IBlockColor} handlers.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void registerBlockColourHandlers(final ColorHandlerEvent.Block event) {
        final BlockColors blockColors = event.getBlockColors();

        // Use the grass colour of the biome or the default grass colour
        final IBlockColor grassColourHandler = (state, blockAccess, pos, tintIndex) -> {
            if (blockAccess != null && pos != null) {
                return BiomeColors.getGrassColor(blockAccess, pos);
            }
            return GrassColors.get(0.5d, 1.0d);
        };
        //System.out.println("Bamboo Leaves Registered");
        //blockColors.register(grassColourHandler, ExplorerBlocks.BAMBOO_LEAVES);
    }

    /**
     * Register the {@link IItemColor} handlers
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void registerItemColourHandlers(final ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors itemColors = event.getItemColors();

        // Use the Block's colour handler for an ItemBlock
        final IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
            final BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(state, null, null, tintIndex);
        };
        //System.out.println("Inventory for: Bamboo Leaves Registered");
        //itemColors.register(itemBlockColourHandler,  ExplorerBlocks.BAMBOO_LEAVES);
    }

}
