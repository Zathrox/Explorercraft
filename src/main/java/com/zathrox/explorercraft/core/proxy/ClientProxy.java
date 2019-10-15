package com.zathrox.explorercraft.core.proxy;

import com.zathrox.explorercraft.client.ClientForgeEventSubscriber;
import com.zathrox.explorercraft.client.render.*;
import com.zathrox.explorercraft.common.entity.*;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.Config;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@OnlyIn(Dist.CLIENT)
public class ClientProxy  extends CommonProxy {

    public ClientProxy() {
        super();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("explorercraft-client.toml").toString());
    }

    private void clientSetup(FMLClientSetupEvent event) {
        Explorercraft.LOGGER.debug("ClientProxy clientSetup method");

        RenderingRegistry.registerEntityRenderingHandler(BrushStoogeEntity.class, manager -> new BrushStoogeRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(EnderreeperEntity.class, manager -> new EnderreeperRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(EnderGhastEntity.class, manager -> new EnderGhastRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(InfectedSkeletonEntity.class, manager -> new InfectedSkeletonRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(InfectedZombieEntity.class, manager -> new InfectedZombieRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(InfectedCreeperEntity.class, manager -> new InfectedCreeperRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(WizardEntity.class, manager -> new WizardRenderer(manager));

        MinecraftForge.EVENT_BUS.register(new ClientForgeEventSubscriber());
    }

    private void registerBlockColours(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();

        blockColors.register((state, world, pos, tint_index) -> {
                    return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D);
                }, ExplorerBlocks.LOTUS_FLOWER, ExplorerBlocks.SHORT_GRASS, ExplorerBlocks.WILLOW_LEAVES, ExplorerBlocks.WILLOW);

    }

    private void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        itemColors.register((stack, tintIndex) -> {
            return ExplorerBiomes.FUNGAL_FOREST.getGrassColor((BlockPos) null);
        }, ExplorerBlocks.LOTUS_FLOWER);

        itemColors.register((stack, tintIndex) -> {
            return GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.WILLOW_LEAVES, ExplorerBlocks.WILLOW);

        itemColors.register((stack, tintIndex) -> {
            return GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.SHORT_GRASS);

        itemColors.register((stack, tintIndex) -> {
            return GrassColors.get(0.5D, 1.0D);
        }, ExplorerBlocks.SHORT_GRASS);
    }

    @Override
    protected void postInit(InterModProcessEvent event) {
        super.postInit(event);

        this.registerBlockColours(new ColorHandlerEvent.Block(Minecraft.getInstance().getBlockColors()));
        this.registerItemColors(new ColorHandlerEvent.Item(Minecraft.getInstance().getItemColors(), Minecraft.getInstance().getBlockColors()));
    }
}