package com.zathrox.explorercraft.client;

import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.GeneralConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
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


}