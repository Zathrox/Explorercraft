package dev.driscollcreations.explorercraft.vanillatweaks.setup;

import dev.driscollcreations.explorercraft.util.BlockUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlimeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;

public class VanillaTweaksBlocks {

    public static final RegistryObject<Block> SLIMEY_STONE = BlockUtils.register("slimey_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).harvestLevel(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DISSOLVED_STONE = BlockUtils.register("dissolved_stone", () -> new SlimeBlock(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.GRASS).slipperiness(0.8F).sound(SoundType.SLIME).notSolid()));

    public static void register() {}
}
