package dev.driscollcreations.explorercraft.vanillatweaks.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.config.VanillaTweaksConfig;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

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
