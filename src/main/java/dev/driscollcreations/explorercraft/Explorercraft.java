package dev.driscollcreations.explorercraft;

import com.mojang.serialization.Codec;
import dev.driscollcreations.explorercraft.bamboogrove.items.JadeBowItem;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.config.Config;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.cymru.entity.renderer.WizardRenderer;
import dev.driscollcreations.explorercraft.setup.*;
import dev.driscollcreations.explorercraft.util.EntityEvents;
import dev.driscollcreations.explorercraft.util.ExplorerVanillaCompat;
import dev.driscollcreations.explorercraft.util.ExplorercraftResourceLocation;
import dev.driscollcreations.explorercraft.vanillatweaks.client.ClientEvents;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.renderer.EnderreeperRenderer;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.WoodType;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod(Explorercraft.MOD_ID)
public class Explorercraft
{
    // Directly reference a log4j logger.
    public static final String MOD_ID = "explorercraft";
    public static final Logger LOGGER = LogManager.getLogger();

    public Explorercraft() {
        if (!FMLEnvironment.production) {
            GeckoLib.initialize();
        }

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.register();
        ExplorerStructures.STRUCTURES.register(modEventBus);
        ExplorerEntities.ENTITIES.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::entitySetup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(this::textureStitching);
        ExplorerPlacement.DECORATORS.register(modEventBus);
        ExplorerFeature.FEATURES.register(modEventBus);
        ExplorerTileEntities.TILE_ENTITIES.register(modEventBus);
        ExplorerFeature.FOLIAGE_PLACER_TYPES.register(modEventBus);
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        Config.init();
        ExplorerBannerPattern.init();
    }

    private void entitySetup(final EntityAttributeCreationEvent event) {
        event.put(ExplorerEntities.ENDERREEPER.get(), MobEntity.createMobAttributes().build());
        event.put(ExplorerEntities.WIZARD.get(), MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, 0.5D).add(Attributes.FOLLOW_RANGE, 48.0D).build());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ExplorerFeature.Configured.registerConfiguredFeatures();
            ExplorerStructures.setupStructures();
            ExplorerConfiguredStructures.registerConfiguredStructures();
            ExplorerVanillaCompat.register();
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BambooGroveBlocks.BAMBOO_SAPLING.getId(), BambooGroveBlocks.POTTED_BAMBOO_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BambooGroveBlocks.CHERRY_SAPLING.getId(), BambooGroveBlocks.POTTED_CHERRY_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BambooGroveBlocks.MAPLE_SAPLING.getId(), BambooGroveBlocks.POTTED_MAPLE_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(CymruBlocks.ASH_SAPLING.getId(), CymruBlocks.POTTED_ASH_SAPLING::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(CymruBlocks.LEEK_WILD.getId(), CymruBlocks.POTTED_WILD_LEEK::get);
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(CymruBlocks.DAFFODIL.getId(), CymruBlocks.POTTED_DAFFODIL::get);
            WoodType.register(BambooGroveBlocks.BAMBOO_WOODTYPE);
            WoodType.register(BambooGroveBlocks.CHERRY_WOODTYPE);
            WoodType.register(BambooGroveBlocks.CHERRY_BLOSSOM_WOODTYPE);
            WoodType.register(BambooGroveBlocks.MAPLE_WOODTYPE);
            WoodType.register(CymruBlocks.ASH_WOODTYPE);
            //GlobalEntityTypeAttributes.put(ExplorerEntities.ENDERREEPER.get(), MobEntity.createMobAttributes().build());
            //GlobalEntityTypeAttributes.put(ExplorerEntities.WIZARD.get(), MobEntity.createMobAttributes().build());
        });
        EnchantmentType.BOW.canEnchant(BambooGroveItems.JADE_BOW.get());
        MinecraftForge.EVENT_BUS.register(new EntityEvents());
    }

    private void textureStitching(final TextureStitchEvent.Pre event) {
        if (event.getMap().location() == Atlases.BANNER_SHEET) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Stitching banner textures");
            event.addSprite(new ResourceLocation("entity/banner/wales"));
            event.addSprite(new ResourceLocation("entity/banner/welshflag"));
            Explorercraft.LOGGER.log(Level.DEBUG, "Finished stitching banner textures!");
        }
        if (event.getMap().location() == Atlases.SHIELD_SHEET) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Stitching shield textures");
            event.addSprite(new ResourceLocation("entity/shield/wales"));
            event.addSprite(new ResourceLocation("entity/shield/welshflag"));
            Explorercraft.LOGGER.log(Level.DEBUG, "Finished stitching shield textures!");
        }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        JadeBowItem.initPropertyOverride();
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.BAMBOO_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.CHERRY_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.MAPLE_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.POTTED_BAMBOO_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.POTTED_CHERRY_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.POTTED_MAPLE_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.MAPLE_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.MAPLE_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.RICE_BASE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BambooGroveBlocks.RICE_TOP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VanillaTweaksBlocks.NOCTILUCAS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VanillaTweaksBlocks.DISSOLVED_STONE.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(CymruBlocks.DAFFODIL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.LEEK_WILD.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.LEEKS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.POTTED_DAFFODIL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.POTTED_DAFFODIL.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.POTTED_WILD_LEEK.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.ASH_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(CymruBlocks.POTTED_ASH_SAPLING.get(), RenderType.cutout());
        ClientRegistry.bindTileEntityRenderer(ExplorerTileEntities.EXPLORER_SIGNS.get(), SignTileEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.ENDERREEPER.get(), EnderreeperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ExplorerEntities.WIZARD.get(), WizardRenderer::new);
        event.enqueueWork(() -> {
            Atlases.addWoodType(BambooGroveBlocks.BAMBOO_WOODTYPE);
            Atlases.addWoodType(BambooGroveBlocks.CHERRY_WOODTYPE);
            Atlases.addWoodType(BambooGroveBlocks.CHERRY_BLOSSOM_WOODTYPE);
            Atlases.addWoodType(BambooGroveBlocks.MAPLE_WOODTYPE);
            Atlases.addWoodType(CymruBlocks.ASH_WOODTYPE);
        });
    }

    public static ExplorercraftResourceLocation getId(String path) {
        if (path.contains(":")) {
            throw new IllegalArgumentException("path contains namespace");
        }
        return new ExplorercraftResourceLocation(path);
    }

    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();
            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                Explorercraft.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator && serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            /*tempMap.putIfAbsent(ExplorerStructures.RUN_DOWN_HOUSE.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.RUN_DOWN_HOUSE.get()));
            tempMap.putIfAbsent(ExplorerStructures.BLACKSTONE_DUNGEON.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.BLACKSTONE_DUNGEON.get()));*/
            tempMap.putIfAbsent(ExplorerStructures.SAKURA_TREE.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.SAKURA_TREE.get()));
            tempMap.putIfAbsent(ExplorerStructures.TORII_GATE.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.TORII_GATE.get()));
            tempMap.putIfAbsent(ExplorerStructures.TEMPLE_RUINS.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.TEMPLE_RUINS.get()));
            tempMap.putIfAbsent(ExplorerStructures.WIZARD_TOWER.get(), DimensionStructuresSettings.DEFAULTS.get(ExplorerStructures.WIZARD_TOWER.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

}
