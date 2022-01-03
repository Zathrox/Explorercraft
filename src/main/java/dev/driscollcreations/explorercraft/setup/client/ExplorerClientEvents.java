package dev.driscollcreations.explorercraft.setup.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.config.VanillaTweaksConfig;
import dev.driscollcreations.explorercraft.setup.ExplorerEntities;
import dev.driscollcreations.explorercraft.setup.ExplorerTileEntities;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.model.EnderreeperTestModel;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.renderer.EnderreeperRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ExplorerClientEvents {

    private ExplorerClientEvents() {}

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ExplorerEntities.ENDERREEPER.get(), EnderreeperRenderer::new);
        event.registerBlockEntityRenderer(ExplorerTileEntities.EXPLORER_SIGNS.get(), SignRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(EnderreeperTestModel.LAYER_LOCATION, EnderreeperTestModel::createBodyLayer);
    }


}
