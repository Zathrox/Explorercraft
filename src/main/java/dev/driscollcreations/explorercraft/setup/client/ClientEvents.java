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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    public static void initClient()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientEvents::textureStitching);
    }

    private static void textureStitching(final TextureStitchEvent.Pre event) {
        if (event.getMap().location() == Sheets.BANNER_SHEET) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Stitching banner textures");
            event.addSprite(new ResourceLocation("entity/banner/wales"));
            event.addSprite(new ResourceLocation("entity/banner/welshflag"));
            Explorercraft.LOGGER.log(Level.DEBUG, "Finished stitching banner textures!");
        }
        if (event.getMap().location() == Sheets.SHIELD_SHEET) {
            Explorercraft.LOGGER.log(Level.DEBUG, "Stitching shield textures");
            event.addSprite(new ResourceLocation("entity/shield/wales"));
            event.addSprite(new ResourceLocation("entity/shield/welshflag"));
            Explorercraft.LOGGER.log(Level.DEBUG, "Finished stitching shield textures!");
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (VanillaTweaksConfig.swimmingHorse.get()) {
            if (Minecraft.getInstance().isPaused()) {
                return;
            }

            Player player = Minecraft.getInstance().player;
            if (player != null && player.getVehicle() instanceof AbstractHorse) {
                AbstractHorse horse = (AbstractHorse) player.getVehicle();
                if ((horse.isInWater())) {
                    horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, 0.0125f, 0.0D));
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPre(RenderPlayerEvent.Pre evt) {
        final Player player = evt.getPlayer();

        if (player instanceof RemotePlayer && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                PoseStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, -0.375F, 0.0f);
                }
            });
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
        final Player player = evt.getPlayer();

        if (player instanceof RemotePlayer && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                PoseStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, 0.375F, 0.0f);
                }
            });
        }
    }

}
