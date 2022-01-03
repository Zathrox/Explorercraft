package dev.driscollcreations.explorercraft;

import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.config.Config;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.setup.*;
import dev.driscollcreations.explorercraft.util.EntityEvents;
import dev.driscollcreations.explorercraft.util.ExplorerVanillaCompat;
import dev.driscollcreations.explorercraft.setup.client.ClientEvents;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Explorercraft.MOD_ID)
public class Explorercraft
{
    // Directly reference a log4j logger.
    public static final String MOD_ID = "explorercraft";
    public static final Logger LOGGER = LogManager.getLogger();

    public Explorercraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.register();
        //ExplorerStructures.STRUCTURES.register(modEventBus);
        ExplorerEntities.ENTITIES.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::entitySetup);
        modEventBus.addListener(this::doClientStuff);
        ExplorerPlacement.DECORATORS.register(modEventBus);
        ExplorerFeature.FEATURES.register(modEventBus);
        ExplorerTileEntities.TILE_ENTITIES.register(modEventBus);
        ExplorerFeature.FOLIAGE_PLACER_TYPES.register(modEventBus);
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        //forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        Config.init();
        ExplorerBannerPattern.init();
    }

    private void entitySetup(final EntityAttributeCreationEvent event) {
        event.put(ExplorerEntities.ENDERREEPER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .build());
        //event.put(ExplorerEntities.WIZARD.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.5D).add(Attributes.FOLLOW_RANGE, 48.0D).build());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ExplorerFeature.Configured.registerConfiguredFeatures();
            //ExplorerStructures.setupStructures();
            ExplorerConfiguredStructures.registerConfiguredStructures();
            ExplorerVanillaCompat.register();
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BambooGroveBlocks.BAMBOO_SAPLING.getId(), BambooGroveBlocks.POTTED_BAMBOO_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BambooGroveBlocks.CHERRY_SAPLING.getId(), BambooGroveBlocks.POTTED_CHERRY_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BambooGroveBlocks.MAPLE_SAPLING.getId(), BambooGroveBlocks.POTTED_MAPLE_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(CymruBlocks.ASH_SAPLING.getId(), CymruBlocks.POTTED_ASH_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(CymruBlocks.LEEK_WILD.getId(), CymruBlocks.POTTED_WILD_LEEK::get);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(CymruBlocks.DAFFODIL.getId(), CymruBlocks.POTTED_DAFFODIL::get);
            WoodType.register(BambooGroveBlocks.BAMBOO_WOODTYPE);
            WoodType.register(BambooGroveBlocks.CHERRY_WOODTYPE);
            WoodType.register(BambooGroveBlocks.CHERRY_BLOSSOM_WOODTYPE);
            WoodType.register(BambooGroveBlocks.MAPLE_WOODTYPE);
            WoodType.register(CymruBlocks.ASH_WOODTYPE);
        });
        EnchantmentCategory.BOW.canEnchant(BambooGroveItems.JADE_BOW.get());
        MinecraftForge.EVENT_BUS.register(new EntityEvents());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::initClient);
        //JadeBowItem.initPropertyOverride(); - overrides textures, for pulling back animation
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.BAMBOO_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.CHERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.MAPLE_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.POTTED_BAMBOO_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.POTTED_CHERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.POTTED_MAPLE_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.MAPLE_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.MAPLE_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.RICE_BASE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BambooGroveBlocks.RICE_TOP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VanillaTweaksBlocks.NOCTILUCAS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VanillaTweaksBlocks.DISSOLVED_STONE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.DAFFODIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.LEEK_WILD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.LEEKS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.POTTED_DAFFODIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.POTTED_DAFFODIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.POTTED_WILD_LEEK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.ASH_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(CymruBlocks.POTTED_ASH_SAPLING.get(), RenderType.cutout());
        event.enqueueWork(() -> {
            Sheets.addWoodType(BambooGroveBlocks.BAMBOO_WOODTYPE);
            Sheets.addWoodType(BambooGroveBlocks.CHERRY_WOODTYPE);
            Sheets.addWoodType(BambooGroveBlocks.CHERRY_BLOSSOM_WOODTYPE);
            Sheets.addWoodType(BambooGroveBlocks.MAPLE_WOODTYPE);
            Sheets.addWoodType(CymruBlocks.ASH_WOODTYPE);
        });
    }

    public static ResourceLocation getId(String path) {
        if (path.contains(":")) {
            throw new IllegalArgumentException("path contains namespace");
        }
        return new ResourceLocation(path);
    }

//    private static Method GETCODEC_METHOD;
//    public void addDimensionalSpacing(final LevelEvent.Load event) {
//        if(event.getLevel() instanceof ServerLevel){
//            ServerLevel serverLevel = (ServerLevel)event.getLevel();
//            try {
//                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
//                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverLevel.getChunkSource().generator));
//                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
//            }
//            catch(Exception e){
//                Explorercraft.LOGGER.error("Was unable to check if " + serverLevel.dimension().location() + " is using Terraforged's ChunkGenerator.");
//            }
//
//            if(serverLevel.getChunkSource().getGenerator() instanceof FlatChunkGenerator && serverLevel.dimension().equals(Level.OVERWORLD)){
//                return;
//            }
//
//            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverLevel.getChunkSource().generator.getSettings().structureConfig());
//            /*tempMap.putIfAbsent(ExplorerStructures.RUN_DOWN_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.RUN_DOWN_HOUSE.get()));
//            tempMap.putIfAbsent(ExplorerStructures.BLACKSTONE_DUNGEON.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.BLACKSTONE_DUNGEON.get()));*/
//            tempMap.putIfAbsent(ExplorerStructures.SAKURA_TREE.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.SAKURA_TREE.get()));
//            tempMap.putIfAbsent(ExplorerStructures.TORII_GATE.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.TORII_GATE.get()));
//            tempMap.putIfAbsent(ExplorerStructures.TEMPLE_RUINS.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.TEMPLE_RUINS.get()));
//            tempMap.putIfAbsent(ExplorerStructures.WIZARD_TOWER.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.WIZARD_TOWER.get()));
//            tempMap.putIfAbsent(ExplorerStructures.SLATE_DUNGEON.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.SLATE_DUNGEON.get()));
//            serverLevel.getChunkSource().generator.getSettings().structureConfig = tempMap;
//        }
//    }

}
