package com.zathrox.explorercraft.common.world.feature.template;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;

public class Processors {
    /**
     * Processor that causes red glass to be ignored when placing the structure.
     */
    public static final BlockIgnoreStructureProcessor RED_GLASS_AND_STRUCTURE_BLOCK = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.RED_STAINED_GLASS, Blocks.STRUCTURE_BLOCK));

}
