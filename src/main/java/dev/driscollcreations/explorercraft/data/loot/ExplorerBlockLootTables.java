package dev.driscollcreations.explorercraft.data.loot;

import dev.driscollcreations.explorercraft.bamboogrove.blocks.PanelBlock;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.RiceBlock;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.blocks.LeekBlock;
import dev.driscollcreations.explorercraft.cymru.items.CymruItems;
import dev.driscollcreations.explorercraft.setup.Registration;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.NoctilucaBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.stream.Collectors;

public class ExplorerBlockLootTables extends BlockLoot {

    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

    @Override
    protected void addTables() {

        //======= Bamboo Grove
        dropSelf(BambooGroveBlocks.BAMBOO_BUTTON.get());
        add(BambooGroveBlocks.BAMBOO_DOOR.get(),
                block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
        dropSelf(BambooGroveBlocks.BAMBOO_FENCE.get());
        dropSelf(BambooGroveBlocks.BAMBOO_FENCE_GATE.get());
        dropSelf(BambooGroveBlocks.BAMBOO_LOG.get());
        add(BambooGroveBlocks.BAMBOO_LEAVES.get(), (leaves) -> {
            return  createLeavesDrops(BambooGroveBlocks.BAMBOO_LEAVES.get(), BambooGroveBlocks.BAMBOO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
        });
        dropSelf(BambooGroveBlocks.BAMBOO_PLANKS.get());
        dropSelf(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get());
        dropSelf(BambooGroveBlocks.BAMBOO_SAPLING.get());
        dropSelf(BambooGroveBlocks.BAMBOO_STANDING_SIGN.get());
        dropSelf(BambooGroveBlocks.BAMBOO_WALL_SIGN.get());
        add(BambooGroveBlocks.BAMBOO_SLAB.get(), BlockLoot::createSlabItemTable);
        dropSelf(BambooGroveBlocks.BAMBOO_STAIRS.get());
        dropPottedContents(BambooGroveBlocks.POTTED_BAMBOO_SAPLING.get());
        dropSelf(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get());
        dropSelf(BambooGroveBlocks.BAMBOO_TRAPDOOR.get());

        dropSelf(BambooGroveBlocks.CHERRY_BUTTON.get());
        add(BambooGroveBlocks.CHERRY_DOOR.get(),
                block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
        dropSelf(BambooGroveBlocks.CHERRY_FENCE.get());
        dropSelf(BambooGroveBlocks.CHERRY_FENCE_GATE.get());
        dropSelf(BambooGroveBlocks.CHERRY_LOG.get());
        add(BambooGroveBlocks.CHERRY_LEAVES.get(), (leaves) -> {
            return  createLeavesDrops(BambooGroveBlocks.CHERRY_LEAVES.get(), BambooGroveBlocks.CHERRY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES)
                            .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                                              .add(applyExplosionCondition(BambooGroveBlocks.CHERRY_LEAVES.get(), LootItem.lootTableItem(BambooGroveItems.CHERRY_BLOSSOM.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.006F, 0.0067F, 0.00625F, 0.008333334F, 0.055F))));
        });
        dropSelf(BambooGroveBlocks.CHERRY_PLANKS.get());
        dropSelf(BambooGroveBlocks.CHERRY_SAPLING.get());
        add(BambooGroveBlocks.CHERRY_SLAB.get(), BlockLoot::createSlabItemTable);

        dropSelf(BambooGroveBlocks.CHERRY_STAIRS.get());
        dropPottedContents(BambooGroveBlocks.POTTED_CHERRY_SAPLING.get());
        dropSelf(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get());
        dropSelf(BambooGroveBlocks.CHERRY_TRAPDOOR.get());
        dropSelf(BambooGroveBlocks.CHERRY_WOOD.get());
        dropSelf(BambooGroveBlocks.CHERRY_STRIPPED_LOG.get());
        dropSelf(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get());
        dropSelf(BambooGroveBlocks.CHERRY_WALL_SIGN.get());
        dropSelf(BambooGroveBlocks.CHERRY_STANDING_SIGN.get());

        add(BambooGroveBlocks.CHERRY_PANEL.get(),
                block -> createSinglePropConditionTable(block, PanelBlock.HALF, DoubleBlockHalf.LOWER));

        add(BambooGroveBlocks.BAMBOO_PANEL.get(),
                block -> createSinglePropConditionTable(block, PanelBlock.HALF, DoubleBlockHalf.LOWER));

        //====== Cherry Blossom Blocks
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_BUTTON.get());
        add(BambooGroveBlocks.CHERRY_BLOSSOM_DOOR.get(),
                block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE.get());
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_FENCE_GATE.get());
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get());
        add(BambooGroveBlocks.CHERRY_BLOSSOM_SLAB.get(), BlockLoot::createSlabItemTable);
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_STAIRS.get());
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_PRESSURE_PLATE.get());
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_TRAPDOOR.get());
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_WALL_SIGN.get());
        dropSelf(BambooGroveBlocks.CHERRY_BLOSSOM_STANDING_SIGN.get());

        //====== Maple Tree Blocks
        dropSelf(BambooGroveBlocks.MAPLE_BUTTON.get());
        add(BambooGroveBlocks.MAPLE_DOOR.get(),
                block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
        createDoorTable(BambooGroveBlocks.MAPLE_DOOR.get());
        dropSelf(BambooGroveBlocks.MAPLE_FENCE.get());
        dropSelf(BambooGroveBlocks.MAPLE_FENCE_GATE.get());
        dropSelf(BambooGroveBlocks.MAPLE_LOG.get());
        add(BambooGroveBlocks.MAPLE_LEAVES.get(), (leaves) -> {
            return  createLeavesDrops(BambooGroveBlocks.MAPLE_LEAVES.get(), BambooGroveBlocks.MAPLE_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
        });
        dropSelf(BambooGroveBlocks.MAPLE_PLANKS.get());
        dropSelf(BambooGroveBlocks.MAPLE_SAPLING.get());
        add(BambooGroveBlocks.MAPLE_SLAB.get(), BlockLoot::createSlabItemTable);
        dropSelf(BambooGroveBlocks.MAPLE_STAIRS.get());
        dropPottedContents(BambooGroveBlocks.POTTED_MAPLE_SAPLING.get());
        dropSelf(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get());
        dropSelf(BambooGroveBlocks.MAPLE_TRAPDOOR.get());
        dropSelf(BambooGroveBlocks.MAPLE_WOOD.get());
        dropSelf(BambooGroveBlocks.MAPLE_STRIPPED_LOG.get());
        dropSelf(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get());
        dropSelf(BambooGroveBlocks.MAPLE_WALL_SIGN.get());
        dropSelf(BambooGroveBlocks.MAPLE_STANDING_SIGN.get());

        add(BambooGroveBlocks.TATAMI.get(),
                block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        dropSelf(BambooGroveBlocks.TATAMI_HALF.get());

        dropSelf(BambooGroveBlocks.RICE_STRAW_BLOCK.get());
        add(BambooGroveBlocks.RICE_BASE.get(), noDrop());

        LootItemCondition.Builder riceLootCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BambooGroveBlocks.RICE_TOP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceBlock.AGE, 7));
        this.add(BambooGroveBlocks.RICE_TOP.get(), createCropDrops(BambooGroveBlocks.RICE_TOP.get(), BambooGroveItems.RICE_STRAW.get(), BambooGroveItems.RICE.get(), riceLootCondition));

        dropSelf(BambooGroveBlocks.JADE_BLOCK.get());
        add(BambooGroveBlocks.JADE_ORE.get(), createOreDrop(BambooGroveBlocks.JADE_ORE.get(), BambooGroveItems.JADE.get()));



        //======== Vanilla Tweaks
        dropSelf(VanillaTweaksBlocks.RUBY_BLOCK.get());
        add(VanillaTweaksBlocks.RUBY_ORE.get(), createOreDrop(VanillaTweaksBlocks.RUBY_ORE.get(), BambooGroveItems.RUBY.get()));

        dropSelf(VanillaTweaksBlocks.AMETHYST_BLOCK.get());
        add(VanillaTweaksBlocks.AMETHYST_ORE.get(), createOreDrop(VanillaTweaksBlocks.AMETHYST_ORE.get(), BambooGroveItems.AMETHYST.get()));

        dropSelf(VanillaTweaksBlocks.SLIMEY_STONE.get());
        dropSelf(VanillaTweaksBlocks.DISSOLVED_STONE.get());
        add(VanillaTweaksBlocks.LEATHER_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.BLACK_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.BLUE_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.BROWN_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.CYAN_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.GRAY_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.GREEN_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.LIGHT_BLUE_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.LIGHT_GRAY_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.LIME_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.MAGENTA_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.ORANGE_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.PINK_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.PURPLE_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.RED_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.WHITE_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(VanillaTweaksBlocks.YELLOW_SLEEPING_BAG.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));

        LootItemCondition.Builder noctilucasLootCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(VanillaTweaksBlocks.NOCTILUCAS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(NoctilucaBlock.AGE, 7));
        this.add(VanillaTweaksBlocks.NOCTILUCAS.get(), createCropDrops(VanillaTweaksBlocks.NOCTILUCAS.get(), VanillaTweaksItems.NOCTILUCA.get(),VanillaTweaksItems.NOCTILUCA.get(), noctilucasLootCondition));

        //======= MARBLE
        dropSelf(VanillaTweaksBlocks.MARBLE.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_POLISHED.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_BRICKS.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_MOSSY.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_CRACKED.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_CHISELED.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_PILLAR.get());
        add(VanillaTweaksBlocks.MARBLE_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.MARBLE_MOSSY_SLAB.get(), BlockLoot::createSlabItemTable);
        dropSelf(VanillaTweaksBlocks.MARBLE_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_MOSSY_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_WALL.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_BRICK_WALL.get());
        dropSelf(VanillaTweaksBlocks.MARBLE_MOSSY_WALL.get());

        //======= BASALT
        dropSelf(VanillaTweaksBlocks.BASALT.get());
        add(VanillaTweaksBlocks.BASALT.get(), createSingleItemTableWithSilkTouch(VanillaTweaksBlocks.BASALT.get(), VanillaTweaksBlocks.BASALT_COBBLESTONE.get()));
        dropSelf(VanillaTweaksBlocks.BASALT_POLISHED.get());
        dropSelf(VanillaTweaksBlocks.BASALT_BRICKS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_MOSSY.get());
        dropSelf(VanillaTweaksBlocks.BASALT_CRACKED.get());
        dropSelf(VanillaTweaksBlocks.BASALT_CHISELED.get());
        dropSelf(VanillaTweaksBlocks.BASALT_PILLAR.get());
        dropSelf(VanillaTweaksBlocks.BASALT_COBBLESTONE.get());
        dropSelf(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get());
        add(VanillaTweaksBlocks.BASALT_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.BASALT_POLISHED_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.BASALT_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.BASALT_MOSSY_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.BASALT_COBBLESTONE_SLAB.get(), BlockLoot::createSlabItemTable);
        add(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_SLAB.get(), BlockLoot::createSlabItemTable);
        dropSelf(VanillaTweaksBlocks.BASALT_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_POLISHED_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_BRICK_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_MOSSY_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_COBBLESTONE_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_STAIRS.get());
        dropSelf(VanillaTweaksBlocks.BASALT_WALL.get());
        dropSelf(VanillaTweaksBlocks.BASALT_BRICK_WALL.get());
        dropSelf(VanillaTweaksBlocks.BASALT_MOSSY_WALL.get());
        dropSelf(VanillaTweaksBlocks.BASALT_COBBLESTONE_WALL.get());
        dropSelf(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_WALL.get());

        //================================ CYMRU EXPANSION
        dropSelf(CymruBlocks.DRAGON_HEART.get());
        dropSelf(CymruBlocks.DAFFODIL.get());
        dropSelf(CymruBlocks.LEEK_WILD.get());
        add(CymruBlocks.LEEK_WILD.get(), (block) -> {
            return createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(CymruBlocks.LEEKS.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))));
        });
        LootItemCondition.Builder leekLootCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(CymruBlocks.LEEKS.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LeekBlock.AGE, 7));
        add(CymruBlocks.LEEKS.get(), applyExplosionDecay(CymruBlocks.LEEKS.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(CymruItems.LEEK.get()))).withPool(LootPool.lootPool().when(leekLootCondition).add(LootItem.lootTableItem(CymruItems.LEEK.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));
        dropPottedContents(CymruBlocks.POTTED_DAFFODIL.get());
        dropPottedContents(CymruBlocks.POTTED_WILD_LEEK.get());


        //======= SLATE
        dropSelf(CymruBlocks.SLATE.get());
        dropSelf(CymruBlocks.SLATE_POLISHED.get());
        dropSelf(CymruBlocks.SLATE_BRICKS.get());
        dropSelf(CymruBlocks.SLATE_MOSSY.get());
        dropSelf(CymruBlocks.SLATE_TILE.get());
        dropSelf(CymruBlocks.SLATE_CHISELED.get());
        dropSelf(CymruBlocks.SLATE_WELSH.get());
        dropSelf(CymruBlocks.SLATE_PILLAR.get());
        add(CymruBlocks.SLATE_SLAB.get(), BlockLoot::createSlabItemTable);
        add(CymruBlocks.SLATE_POLISHED_SLAB.get(), BlockLoot::createSlabItemTable);
        add(CymruBlocks.SLATE_BRICK_SLAB.get(), BlockLoot::createSlabItemTable);
        add(CymruBlocks.SLATE_MOSSY_SLAB.get(), BlockLoot::createSlabItemTable);
        add(CymruBlocks.SLATE_TILE_SLAB.get(), BlockLoot::createSlabItemTable);
        dropSelf(CymruBlocks.SLATE_STAIRS.get());
        dropSelf(CymruBlocks.SLATE_POLISHED_STAIRS.get());
        dropSelf(CymruBlocks.SLATE_BRICK_STAIRS.get());
        dropSelf(CymruBlocks.SLATE_MOSSY_STAIRS.get());
        dropSelf(CymruBlocks.SLATE_TILE_STAIRS.get());
        dropSelf(CymruBlocks.SLATE_WALL.get());
        dropSelf(CymruBlocks.SLATE_BRICK_WALL.get());
        dropSelf(CymruBlocks.SLATE_MOSSY_WALL.get());

        //========= Ash tree
        dropSelf(CymruBlocks.ASH_BUTTON.get());
        add(CymruBlocks.ASH_DOOR.get(),
                block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
        dropSelf(CymruBlocks.ASH_FENCE.get());
        dropSelf(CymruBlocks.ASH_FENCE_GATE.get());
        dropSelf(CymruBlocks.ASH_LOG.get());
        add(CymruBlocks.ASH_LEAVES.get(), (leaves) -> {
            return  createLeavesDrops(CymruBlocks.ASH_LEAVES.get(), CymruBlocks.ASH_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
        });
        dropSelf(CymruBlocks.ASH_PLANKS.get());
        dropSelf(CymruBlocks.ASH_SAPLING.get());
        add(CymruBlocks.ASH_SLAB.get(), BlockLoot::createSlabItemTable);

        dropSelf(CymruBlocks.ASH_STAIRS.get());
        dropPottedContents(CymruBlocks.POTTED_ASH_SAPLING.get());
        dropSelf(CymruBlocks.ASH_PRESSURE_PLATE.get());
        dropSelf(CymruBlocks.ASH_TRAPDOOR.get());
        dropSelf(CymruBlocks.ASH_WOOD.get());
        dropSelf(CymruBlocks.ASH_STRIPPED_LOG.get());
        dropSelf(CymruBlocks.ASH_STRIPPED_WOOD.get());
        dropSelf(CymruBlocks.ASH_WALL_SIGN.get());
        dropSelf(CymruBlocks.ASH_STANDING_SIGN.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream()
                       .map(RegistryObject::get)
                       .collect(Collectors.toList());
    }
}
