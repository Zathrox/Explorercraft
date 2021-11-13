package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.util.EmptyTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public class ExplorerCriteriaTriggers {

    public static final EmptyTrigger SLEEP_IN_BAG = CriteriaTriggers.register(new EmptyTrigger(prefix("sleep_in_bag")));

    private static ResourceLocation prefix(String name) {
        return new ResourceLocation(Explorercraft.MOD_ID, name);
    }

}
