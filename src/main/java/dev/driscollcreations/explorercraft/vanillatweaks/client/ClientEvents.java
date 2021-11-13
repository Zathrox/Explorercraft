package dev.driscollcreations.explorercraft.vanillatweaks.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.config.VanillaTweaksConfig;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
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

    public static void textureStitching(final TextureStitchEvent.Pre event) {
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

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (VanillaTweaksConfig.swimmingHorse.get()) {
            if (Minecraft.getInstance().isPaused()) {
                return;
            }

            PlayerEntity player = Minecraft.getInstance().player;
            if (player != null && player.getVehicle() instanceof AbstractHorseEntity) {
                AbstractHorseEntity horse = (AbstractHorseEntity) player.getVehicle();
                if ((horse.isInWater())) {
                    horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, 0.0125f, 0.0D));
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPre(RenderPlayerEvent.Pre evt) {
        final PlayerEntity player = evt.getPlayer();

        if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                MatrixStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, -0.375F, 0.0f);
                }
            });
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
        final PlayerEntity player = evt.getPlayer();

        if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
            player.getSleepingPos().ifPresent(bedPos -> {
                MatrixStack matrixStack = evt.getMatrixStack();
                Block bed = player.level.getBlockState(bedPos).getBlock();
                if (bed instanceof SleepingBagBlock) {
                    matrixStack.translate(0.0f, 0.375F, 0.0f);
                }
            });
        }
    }

}
