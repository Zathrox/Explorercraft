package dev.driscollcreations.explorercraft;

import com.mojang.serialization.Codec;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.setup.ExplorerConfiguredStructures;
import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import dev.driscollcreations.explorercraft.setup.ExplorerStructures;
import dev.driscollcreations.explorercraft.setup.Registration;
import dev.driscollcreations.explorercraft.util.ExplorerVanillaCompat;
import dev.driscollcreations.explorercraft.util.ExplorercraftResourceLocation;
import dev.driscollcreations.explorercraft.vanillatweaks.client.ClientEvents;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.register();
        ExplorerStructures.STRUCTURES.register(modEventBus);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        ExplorerFeature.FEATURES.register(modEventBus);
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);
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
        });
        EnchantmentType.BOW.canEnchant(BambooGroveItems.JADE_BOW.get());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client

        MinecraftForge.EVENT_BUS.register(new ClientEvents());

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
        RenderTypeLookup.setRenderLayer(VanillaTweaksBlocks.DISSOLVED_STONE.get(), RenderType.translucent());
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    public static ExplorercraftResourceLocation getId(String path) {
        if (path.contains(":")) {
            throw new IllegalArgumentException("path contains namespace");
        }
        return new ExplorercraftResourceLocation(path);
    }

    public void biomeModification(final BiomeLoadingEvent event) {
        /*event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_RUN_DOWN_HOUSE);
        event.getGeneration().getStructures().add(() -> ExplorerConfiguredStructures.CONFIGURED_BLACKSTONE_DUNGEON);*/
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
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

}
