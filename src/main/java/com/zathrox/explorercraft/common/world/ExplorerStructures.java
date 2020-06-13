package com.zathrox.explorercraft.common.world;

import com.zathrox.explorercraft.common.world.feature.structure.test.WizardTowerStructureOld;
import com.zathrox.explorercraft.common.world.feature.structure.WizardTowerStructure;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Explorercraft.MOD_ID)
public class ExplorerStructures {

    // Structures
    //public static final Structure<NoFeatureConfig> WIZARD_TOWER = create("wizard_tower", new WizardTowerStructureOld(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> WIZARD_TOWER_STRUCTURE = create("wizard_tower_structure", new WizardTowerStructure(NoFeatureConfig::deserialize));

    @SubscribeEvent
    public static void registerStructures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();
        //registry.register(WIZARD_TOWER);
        registry.register(WIZARD_TOWER_STRUCTURE);
        System.out.println("STRUCTURES REGISTERED");
    }

    private static <T extends Feature<?>> T create(String name, T feature) {
        feature.setRegistryName(Explorercraft.MOD_ID, name);
        return feature;
    }
}
