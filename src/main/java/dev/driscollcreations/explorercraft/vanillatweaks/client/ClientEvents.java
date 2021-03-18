package dev.driscollcreations.explorercraft.vanillatweaks.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().isPaused()) {
            return;
        }

        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && player.getVehicle() instanceof AbstractHorseEntity) {
            AbstractHorseEntity horse = (AbstractHorseEntity) player.getVehicle();
            if ((horse.isInWater() /*&& GeneralConfig.swimmingHorse.get()*/)) {
                horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, 0.0125f, 0.0D));
            }
        }
    }

}
