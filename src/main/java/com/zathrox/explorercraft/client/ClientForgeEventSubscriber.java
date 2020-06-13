package com.zathrox.explorercraft.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.zathrox.explorercraft.common.blocks.BlockSleepingBag;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.GeneralConfig;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().isGamePaused()) {
            return;
        }

        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && player.getRidingEntity() instanceof AbstractHorseEntity) {
            AbstractHorseEntity horse = (AbstractHorseEntity) player.getRidingEntity();
            if ((horse.isInWater() && GeneralConfig.swimmingHorse.get())) {
                horse.addVelocity(0f, 0.0125f, 0f);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPre(RenderPlayerEvent.Pre evt) {
        final PlayerEntity player = evt.getPlayer();

        if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
            player.getBedPosition().ifPresent(bedPos -> {
                MatrixStack matrixStack = evt.getMatrixStack();
                Block bed = player.world.getBlockState(bedPos).getBlock();
                if (bed instanceof BlockSleepingBag) {
                    matrixStack.translate(0.0f, -0.375F, 0.0f);
                }
            });
        }
    }

    @SubscribeEvent
    public void onPlayerRenderPost(RenderPlayerEvent.Post evt) {
        final PlayerEntity player = evt.getPlayer();

        if (player instanceof RemoteClientPlayerEntity && player.getPose() == Pose.SLEEPING) {
            player.getBedPosition().ifPresent(bedPos -> {
                MatrixStack matrixStack = evt.getMatrixStack();
                Block bed = player.world.getBlockState(bedPos).getBlock();
                if (bed instanceof BlockSleepingBag) {
                    matrixStack.translate(0.0f, 0.375F, 0.0f);
                }
            });
        }
    }
}