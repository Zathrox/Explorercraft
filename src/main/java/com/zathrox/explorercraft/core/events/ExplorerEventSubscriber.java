package com.zathrox.explorercraft.core.events;

import com.google.common.base.Preconditions;
import com.zathrox.explorercraft.common.blocks.*;
import com.zathrox.explorercraft.common.blocks.trees.AshTree;
import com.zathrox.explorercraft.common.blocks.trees.BambooTree;
import com.zathrox.explorercraft.common.blocks.trees.CherryTree;
import com.zathrox.explorercraft.common.blocks.trees.MapleTree;
import com.zathrox.explorercraft.common.items.WizardHatItem;
import com.zathrox.explorercraft.common.items.WizardStaffItem;
import com.zathrox.explorercraft.common.tileentity.TileEntitySleepingBag;
import com.zathrox.explorercraft.common.world.ExplorercraftFeatureList;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.interfaces.NoAutomaticItemBlock;
import com.zathrox.explorercraft.core.registry.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

import static net.minecraft.block.material.Material.ROCK;

/**
 * Subscribe to events from the MOD EventBus that should be handled on both PHYSICAL sides in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class ExplorerEventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger(Explorercraft.MOD_ID + " Mod Event Subscriber");

    /**
     * This method will be called by Forge when it is time for the mod to register its Blocks.
     * This method will always be called before the Item registry method.
     */
    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {

        final Block basalt;
        final Block basaltBricks;
        final Block basaltMossy;
        final Block marble;
        final Block marbleBricks;
        final Block marbleMossy;
        final Block marblePolished;
        final Block slate;
        final Block slateBricks;
        final Block slateMossy;
        final Block slatePolished;
        final Block slateTile;
        final Block daffodil;
        final Block leek_wild;
        final Block basaltCobblestone;
        final Block basaltCobblestoneMossy;
        final Block basaltPolished;
        final Block ash;
        final Block bamboo;
        final Block cherry;
        final Block maple;
        final Block ash_sapling;
        final Block bamboo_sapling;
        final Block cherry_sapling;
        final Block maple_sapling;


        // Register all your blocks inside this registerAll call
        event.getRegistry().registerAll(
                // This block has the ROCK material, meaning it needs at least a wooden pickaxe to break it. It is very similar to Iron Ore
                //setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "example_ore"),
                // This block has the IRON material, meaning it needs at least a stone pickaxe to break it. It is very similar to the Iron Block
                //setup(new Block(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)), "example_block"),
                // This block has the ROCK material, meaning it needs at least a wooden pickaxe to break it. It is very similar to Furnace
                //setup(new MiniModelBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)), "mini_model"),

                setup(new Block(Block.Properties.create(ROCK).hardnessAndResistance(3.0F, 3.0F)), "amethyst_block"),
                setup(new Block(Block.Properties.create(ROCK).hardnessAndResistance(3.0F, 3.0F)), "jade_block"),
                setup(new Block(Block.Properties.create(ROCK).hardnessAndResistance(3.0F, 3.0F)), "ruby_block"),
                setup(new OreExplorerBlock(Block.Properties.create(ROCK).hardnessAndResistance(3.0F, 3.0F)), "amethyst_ore"),
                setup(new OreExplorerBlock(Block.Properties.create(ROCK).hardnessAndResistance(3.0F, 3.0F)), "jade_ore"),
                setup(new OreExplorerBlock(Block.Properties.create(ROCK).hardnessAndResistance(3.0F, 3.0F)), "ruby_ore"),
                setup(new LeekBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)), "leeks"),
                setup(new NoctilucaBlock(Block.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().tickRandomly().sound(SoundType.WET_GRASS)), "noctilucas"),
                setup(new RiceBaseBlock(Block.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().sound(SoundType.WET_GRASS)), "rice_base"),
                setup(new RiceBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.WET_GRASS)), "rice_top"),
                setup(daffodil = new FlowerBlock(Effects.HASTE, 6, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT)), "daffodil"),
                setup(new FlowerPotBlock( daffodil, Block.Properties.create(Material.MISCELLANEOUS)),"potted_daffodil"),
                setup(leek_wild = new LeekWildBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement()), "leek_wild"),
                setup(new FlowerPotBlock(leek_wild, Block.Properties.create(Material.MISCELLANEOUS)),"potted_wild_leek"),
                setup(new TatamiBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.WOOD)), "tatami"),
                setup(new TatamiHalfBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.WOOD)), "tatami_half"),
                setup(new DragonHeartBlock(Block.Properties.create(Material.DRAGON_EGG, MaterialColor.BLACK).hardnessAndResistance(3.0F, 9.0F).lightValue(1)), "dragon_heart"),

                setup(basalt = new Block(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt"),
                setup(basaltPolished = new Block(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_polished"),
                setup(basaltBricks = new Block(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_bricks"),
                setup(basaltMossy = new Block(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_mossy"),
                setup(new Block(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_cracked"),
                setup(new Block(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_chiseled"),
                setup(new RotatedPillarBlock(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_pillar"),
                setup(basaltCobblestone = new Block(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_cobblestone"),
                setup(basaltCobblestoneMossy = new Block(Block.Properties.create(ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "basalt_cobblestone_mossy"),

                setup(marble = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble"),
                setup(marblePolished = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble_polished"),
                setup(marbleBricks = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble_bricks"),
                setup(marbleMossy = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble_mossy"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble_cracked"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble_chiseled"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "marble_pillar"),

                setup(slate = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate"),
                setup(slatePolished = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_polished"),
                setup(slateBricks = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_bricks"),
                setup(slateMossy = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_mossy"),
                setup(slateTile = new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_tile"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_chiseled"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_welsh"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)), "slate_pillar"),

                setup(new WoodButtonExplorerBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ash_button"),
                setup(new DoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "ash_door"),
                setup(new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ash_fence"),
                setup(new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ash_fence_gate"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "ash_leaves"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ash_log"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ash_stripped_log"),
                setup(ash = new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ash_planks"),
                setup(new PressurePlateExplorerBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ash_pressure_plate"),
                setup(ash_sapling = new SaplingExplorerBlock(new AshTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)), "ash_sapling"),
                setup(new FlowerPotBlock(ash_sapling, Block.Properties.create(Material.MISCELLANEOUS)),"potted_ash_sapling"),
                setup(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ash_slab"),
                setup(new StairsExplorerBlock(ash.getDefaultState(), Block.Properties.from(ash)), "ash_stairs"),
                //setup(new StandingSignExplorerBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)), "ash_sign"),
                setup(new TrapDoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "ash_trapdoor"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ash_wood"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ash_stripped_wood"),

                setup(new WoodButtonExplorerBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "bamboo_button"),
                setup(new DoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "bamboo_door"),
                setup(new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "bamboo_fence"),
                setup(new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "bamboo_fence_gate"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "bamboo_leaves"),
                setup(new BambooLogBlock(Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "bamboo_log"),
                setup(bamboo = new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "bamboo_planks"),
                setup(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "bamboo_planks_vertical"),
                setup(new PressurePlateExplorerBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "bamboo_pressure_plate"),
                setup(bamboo_sapling = new SaplingExplorerBlock(new BambooTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)), "bamboo_sapling"),
                setup(new FlowerPotBlock(bamboo_sapling, Block.Properties.create(Material.MISCELLANEOUS)),"potted_bamboo_sapling"),
                setup(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "bamboo_slab"),
                setup(new StairsExplorerBlock(bamboo.getDefaultState(), Block.Properties.from(bamboo)), "bamboo_stairs"),
                //setup(new StandingSignExplorerBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)), "bamboo_sign"),
                setup(new TrapDoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "bamboo_trapdoor"),

                setup(new WoodButtonExplorerBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "cherry_button"),
                setup(new DoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "cherry_door"),
                setup(new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence"),
                setup(new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence_gate"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "cherry_leaves"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_log"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_stripped_log"),
                setup(cherry = new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks"),
                setup(new PressurePlateExplorerBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "cherry_pressure_plate"),
                setup(cherry_sapling = new SaplingExplorerBlock(new CherryTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)), "cherry_sapling"),
                setup(new FlowerPotBlock(cherry_sapling, Block.Properties.create(Material.MISCELLANEOUS)),"potted_cherry_sapling"),
                setup(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab"),
                setup(new StairsExplorerBlock(cherry.getDefaultState(), Block.Properties.from(cherry)), "cherry_stairs"),
                //setup(new StandingSignExplorerBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)), "cherry_sign"),
                setup(new TrapDoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "cherry_trapdoor"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_wood"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_stripped_wood"),

                setup(new WoodButtonExplorerBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "maple_button"),
                setup(new DoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "maple_door"),
                setup(new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "maple_fence"),
                setup(new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "maple_fence_gate"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "maple_leaves"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "maple_log"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.QUARTZ).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "maple_stripped_log"),
                setup(maple = new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "maple_planks"),
                setup(new PressurePlateExplorerBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "maple_pressure_plate"),
                setup(maple_sapling = new SaplingExplorerBlock(new MapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)), "maple_sapling"),
                setup(new FlowerPotBlock(maple_sapling, Block.Properties.create(Material.MISCELLANEOUS)),"potted_maple_sapling"),
                setup(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "maple_slab"),
                setup(new StairsExplorerBlock(maple.getDefaultState(), Block.Properties.from(maple)), "maple_stairs"),
                //setup(new StandingSignExplorerBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)), "maple_sign"),
                setup(new TrapDoorExplorerBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "maple_trapdoor"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "maple_wood"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "maple_stripped_wood"),

                //=== SLABS BLOCKS
                setup(new SlabBlock(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_slab"),
                setup(new SlabBlock(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_brick_slab"),
                setup(new SlabBlock(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_mossy_slab"),
                setup(new SlabBlock(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_cobblestone_slab"),
                setup(new SlabBlock(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_cobblestone_mossy_slab"),
                setup(new SlabBlock(Block.Properties.create(ROCK).hardnessAndResistance(1.5F, 6.0F)), "basalt_polished_slab"),

                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "marble_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "marble_brick_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "marble_mossy_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "marble_polished_slab"),

                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "slate_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "slate_brick_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "slate_mossy_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "slate_polished_slab"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)), "slate_tile_slab"),

                //=== STAIRS BLOCKS
                setup(new StairsExplorerBlock(basalt.getDefaultState(), Block.Properties.from(basalt)), "basalt_stairs"),
                setup(new StairsExplorerBlock(basaltBricks.getDefaultState(), Block.Properties.from(basaltBricks)), "basalt_brick_stairs"),
                setup(new StairsExplorerBlock(basaltMossy.getDefaultState(), Block.Properties.from(basaltMossy)), "basalt_mossy_stairs"),
                setup(new StairsExplorerBlock(basaltCobblestone.getDefaultState(), Block.Properties.from(basaltCobblestone)), "basalt_cobblestone_stairs"),
                setup(new StairsExplorerBlock(basaltCobblestoneMossy.getDefaultState(), Block.Properties.from(basaltCobblestoneMossy)), "basalt_cobblestone_mossy_stairs"),
                setup(new StairsExplorerBlock(basaltPolished.getDefaultState(), Block.Properties.from(basaltPolished)), "basalt_polished_stairs"),

                setup(new StairsExplorerBlock(marble.getDefaultState(), Block.Properties.from(marble)), "marble_stairs"),
                setup(new StairsExplorerBlock(marbleBricks.getDefaultState(), Block.Properties.from(marbleBricks)), "marble_brick_stairs"),
                setup(new StairsExplorerBlock(marbleMossy.getDefaultState(), Block.Properties.from(marbleMossy)), "marble_mossy_stairs"),
                setup(new StairsExplorerBlock(marblePolished.getDefaultState(), Block.Properties.from(marblePolished)), "marble_polished_stairs"),

                setup(new StairsExplorerBlock(slate.getDefaultState(), Block.Properties.from(slate)), "slate_stairs"),
                setup(new StairsExplorerBlock(slateBricks.getDefaultState(), Block.Properties.from(slateBricks)), "slate_brick_stairs"),
                setup(new StairsExplorerBlock(slateMossy.getDefaultState(), Block.Properties.from(slateMossy)), "slate_mossy_stairs"),
                setup(new StairsExplorerBlock(slatePolished.getDefaultState(), Block.Properties.from(slatePolished)), "slate_polished_stairs"),
                setup(new StairsExplorerBlock(slateTile.getDefaultState(), Block.Properties.from(slateTile)), "slate_tile_stairs"),

                //=== WALL BLOCKS
                setup(new WallBlock(Block.Properties.from(basalt)), "basalt_wall"),
                setup(new WallBlock(Block.Properties.from(basaltBricks)), "basalt_brick_wall"),
                setup(new WallBlock(Block.Properties.from(basaltMossy)), "basalt_mossy_wall"),
                setup(new WallBlock(Block.Properties.from(basaltCobblestone)), "basalt_cobblestone_wall"),
                setup(new WallBlock(Block.Properties.from(basaltCobblestoneMossy)), "basalt_cobblestone_mossy_wall"),

                setup(new WallBlock(Block.Properties.from(marble)), "marble_wall"),
                setup(new WallBlock(Block.Properties.from(marbleBricks)), "marble_brick_wall"),
                setup(new WallBlock(Block.Properties.from(marbleMossy)), "marble_mossy_wall"),

                setup(new WallBlock(Block.Properties.from(slate)), "slate_wall"),
                setup(new WallBlock(Block.Properties.from(slateBricks)), "slate_brick_wall"),
                setup(new WallBlock(Block.Properties.from(slateMossy)), "slate_mossy_wall"),

                setup(new BlockSleepingBag(DyeColor.BROWN, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_leather"),
                setup(new BlockSleepingBag(DyeColor.WHITE, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_white"),
                setup(new BlockSleepingBag(DyeColor.ORANGE, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_orange"),
                setup(new BlockSleepingBag(DyeColor.MAGENTA, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_magenta"),
                setup(new BlockSleepingBag(DyeColor.LIGHT_BLUE, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_light_blue"),
                setup(new BlockSleepingBag(DyeColor.YELLOW, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_yellow"),
                setup(new BlockSleepingBag(DyeColor.LIME, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_lime"),
                setup(new BlockSleepingBag(DyeColor.PINK, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_pink"),
                setup(new BlockSleepingBag(DyeColor.GRAY, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_gray"),
                setup(new BlockSleepingBag(DyeColor.LIGHT_GRAY, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_light_gray"),
                setup(new BlockSleepingBag(DyeColor.CYAN, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_cyan"),
                setup(new BlockSleepingBag(DyeColor.PURPLE, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_purple"),
                setup(new BlockSleepingBag(DyeColor.BLUE, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_blue"),
                setup(new BlockSleepingBag(DyeColor.BROWN, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_brown"),
                setup(new BlockSleepingBag(DyeColor.GREEN, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_green"),
                setup(new BlockSleepingBag(DyeColor.RED, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_red"),
                setup(new BlockSleepingBag(DyeColor.BLACK, Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH)), "sleeping_bag_black")
        );
        LOGGER.debug("Registered Blocks");
    }

    /**
     * This method will be called by Forge when it is time for the mod to register its Items.
     * This method will always be called after the Block registry method.
     */
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                setup(new Item(new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "amethyst"),
                setup(new Item(new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "jade"),
                setup(new Item(new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "ruby"),

                setup(new AxeItem(ToolMaterialList.AMETHYST, 6.0f, -3.1F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"amethyst_axe"),
                setup(new HoeItem(ToolMaterialList.AMETHYST, -1.0F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 		        		"amethyst_hoe"),
                setup(new PickaxeItem(ToolMaterialList.AMETHYST, 1, -2.8F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 			"amethyst_pickaxe"),
                setup(new ShovelItem(ToolMaterialList.AMETHYST, 1.5F, -3.0F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 			"amethyst_shovel"),
                setup(new SwordItem(ToolMaterialList.AMETHYST,3, -2.4F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)),			"amethyst_sword"),

                setup(new ArmorItem(ArmorMaterialList.AMETHYST_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"amethyst_helmet"),
                setup(new ArmorItem(ArmorMaterialList.AMETHYST_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"amethyst_chestplate"),
                setup(new ArmorItem(ArmorMaterialList.AMETHYST_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"amethyst_leggings"),
                setup(new ArmorItem(ArmorMaterialList.AMETHYST_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"amethyst_boots"),

                setup(new AxeItem(ToolMaterialList.JADE, 6.0f, -3.1F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"jade_axe"),
                setup(new HoeItem(ToolMaterialList.JADE, -1.0F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 		        		"jade_hoe"),
                setup(new PickaxeItem(ToolMaterialList.JADE, 1, -2.8F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 			"jade_pickaxe"),
                setup(new ShovelItem(ToolMaterialList.JADE, 1.5F, -3.0F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 			"jade_shovel"),
                setup(new SwordItem(ToolMaterialList.JADE,3, -2.4F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)),			"jade_sword"),

                setup(new ArmorItem(ArmorMaterialList.JADE_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"jade_helmet"),
                setup(new ArmorItem(ArmorMaterialList.JADE_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"jade_chestplate"),
                setup(new ArmorItem(ArmorMaterialList.JADE_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"jade_leggings"),
                setup(new ArmorItem(ArmorMaterialList.JADE_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"jade_boots"),

                setup(new AxeItem(ToolMaterialList.RUBY, 6.0f, -3.1F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"ruby_axe"),
                setup(new HoeItem(ToolMaterialList.RUBY, -1.0F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 		        		"ruby_hoe"),
                setup(new PickaxeItem(ToolMaterialList.RUBY, 1, -2.8F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 			"ruby_pickaxe"),
                setup(new ShovelItem(ToolMaterialList.RUBY, 1.5F, -3.0F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 			"ruby_shovel"),
                setup(new SwordItem(ToolMaterialList.RUBY,3, -2.4F, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)),			"ruby_sword"),

                setup(new ArmorItem(ArmorMaterialList.RUBY_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"ruby_helmet"),
                setup(new ArmorItem(ArmorMaterialList.RUBY_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"ruby_chestplate"),
                setup(new ArmorItem(ArmorMaterialList.RUBY_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"ruby_leggings"),
                setup(new ArmorItem(ArmorMaterialList.RUBY_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"ruby_boots"),

                setup(new HorseArmorItem(5, new ResourceLocation(Explorercraft.MOD_ID, "textures/entities/horse/armor/horse_armor_amethyst.png"), new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT).maxStackSize(1)), 	"amethyst_horse_armor"),
                setup(new HorseArmorItem(8, new ResourceLocation(Explorercraft.MOD_ID, "textures/entities/horse/armor/horse_armor_jade.png"), new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT).maxStackSize(1)), 	"jade_horse_armor"),
                setup(new HorseArmorItem(10, new ResourceLocation(Explorercraft.MOD_ID, "textures/entities/horse/armor/horse_armor_ruby.png"), new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT).maxStackSize(1)), 	"ruby_horse_armor"),

                setup(new BlockNamedItem(ExplorerBlocks.RICE_BASE, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "rice"),
                setup(new BlockNamedItem(ExplorerBlocks.LEEKS, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "leek"),
                setup(new BlockNamedItem(ExplorerBlocks.NOCTILUCAS, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "noctiluca"),
                setup(new Item((new Item.Properties()).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.DRIED_FRUIT)), "dried_fruits"),
                setup(new Item((new Item.Properties()).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.CHEESE)), "cheese"),
                setup(new Item((new Item.Properties()).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.WELSH_CAKES).rarity(ExplorerRarity.WELSH)) , "welsh_cakes"),
                setup(new Item((new Item.Properties()).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.WELSH_RAREBIT).rarity(ExplorerRarity.WELSH)), "welsh_rarebit"),
                setup(new Item((new Item.Properties()).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.LAMB_SHANK)), "lamb_shank"),
                setup(new Item((new Item.Properties()).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.COOKED_LAMB_SHANK)), "lamb_shank_cooked"),
                setup(new SoupItem(new Item.Properties().maxStackSize(1).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.RICE_STEW)), "rice_bowl"),
                setup(new SoupItem(new Item.Properties().maxStackSize(1).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.LEEK_STEW)), "leek_bowl"),
                setup(new SoupItem(new Item.Properties().maxStackSize(1).group(ExplorerItemGroups.EXPLORERCRAFT).food(ExplorerFoods.CAWL_STEW).rarity(ExplorerRarity.WELSH)), "cawl_bowl"),
                setup(new Item(new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), "rice_straw"),
                setup(new ShieldItem(new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT).rarity(ExplorerRarity.WELSH).maxDamage(336)), "welsh_shield"),
                setup(new WizardHatItem(ArmorMaterialList.WIZARD_HAT, EquipmentSlotType.HEAD, new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)), 	"wizard_hat"),
                setup(new WizardStaffItem(new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT).maxStackSize(1).maxDamage(99).rarity(ExplorerRarity.WELSH)), "wizard_staff"),
                setup(new BannerPatternItem(ExplorerBannerPattern.WELSH_FLAG, new Item.Properties().maxStackSize(1).group(ExplorerItemGroups.EXPLORERCRAFT)), "welshflag_banner_pattern")
                //setup(new BannerPatternItem(BannerPattern.create("WELSH_DRAGON", "welshdragon", "dra", new ItemStack(ExplorerItems.RUBY)), new Item.Properties().maxStackSize(1).group(ExplorerItemGroups.EXPLORERCRAFT)), "welshdragon_banner_pattern")
                //setup(new BannerPatternItem(ExplorerBannerPattern.WALES, new Item.Properties().maxStackSize(1).group(ExplorerItemGroups.EXPLORERCRAFT)), "wales_banner_pattern")
        );

        // We need to go over the entire registry so that we include any potential Registry Overrides
        for (final Block block : ForgeRegistries.BLOCKS.getValues()) {

            final ResourceLocation blockRegistryName = block.getRegistryName();
            Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" is null! This is not allowed!");

            // Check that the blocks is from our mod, if not, continue to the next block
            if (!blockRegistryName.getNamespace().equals(Explorercraft.MOD_ID)) {
                continue;
            }

            // If you have blocks that don't have a corresponding ItemBlock, uncomment this code and create an Interface - or even better an Annotation - called NoAutomaticItemBlock with no methods and implement it on your blocks that shouldn't have ItemBlocks
			//if (block == ExplorerBlocks.LEEKS || block == ExplorerBlocks.RICE_BASE || block == ExplorerBlocks.RICE_TOP) {
            if (block.getClass().isAnnotationPresent(NoAutomaticItemBlock.class) || block instanceof FlowerPotBlock) {
                continue;
            }

            // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
            final Item.Properties properties = new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT);
            // Create the new BlockItem with the block and it's properties
            final BlockItem blockItem = new BlockItem(block, properties);
            // Setup the new BlockItem with the block's registry name and register it
            registry.register(setup(blockItem, blockRegistryName));
        }
        LOGGER.debug("Registered Items");
    }

    /**
     * This method will be called by Forge when it is time for the mod to register its TileEntityType.
     * This method will always be called after the Block and Item registry methods.
     */
    @SubscribeEvent
    public static void onRegisterTileEntityTypes(@Nonnull final RegistryEvent.Register<TileEntityType<?>> event) {
        // Register your TileEntityTypes here if you have them
        event.getRegistry().registerAll(
                // We don't have a datafixer for our TileEntity, so we pass null into build
                setup(TileEntityType.Builder.create(TileEntitySleepingBag::new, ExplorerBlocks.SLEEPING_BAG_WHITE).build(null), "sleeping_bag")
        );
        LOGGER.debug("Registered TileEntityTypes");
    }


    @SubscribeEvent
    public void onRegisterFeatures(RegistryEvent.Register<Feature<?>> event) {
        ExplorercraftFeatureList.registerFeatures(event.getRegistry());
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(Explorercraft.MOD_ID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }

}