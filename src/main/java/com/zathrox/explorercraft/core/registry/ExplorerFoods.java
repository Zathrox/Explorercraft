package com.zathrox.explorercraft.core.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ExplorerFoods {



    public static final Food CAWL_STEW;
    public static final Food CHEESE;
    public static final Food COOKED_LAMB_SHANK;
    public static final Food DRIED_FRUIT;
    public static final Food LAMB_SHANK;
    public static final Food LEEK;
    public static final Food LEEK_STEW;
    public static final Food RICE;
    public static final Food RICE_STEW;
    public static final Food WELSH_CAKES;
    public static final Food WELSH_RAREBIT;
    public static final Food NOCTILUCA;



    private static Food buildStew(int p_221412_0_) {
        return (new Food.Builder()).hunger(p_221412_0_).saturation(0.6F).build();
    }

    static {

        CAWL_STEW = buildStew(10);
        CHEESE = (new Food.Builder()).hunger(2).saturation(0.3F).setAlwaysEdible().build();
        COOKED_LAMB_SHANK = (new Food.Builder()).hunger(6).saturation(0.8F).meat().build();
        DRIED_FRUIT = (new Food.Builder()).hunger(1).saturation(0.3F).fastToEat().build();
        LEEK = (new Food.Builder()).hunger(1).saturation(0.3F).build();
        LEEK_STEW = buildStew(6);
        LAMB_SHANK = (new Food.Builder()).hunger(2).saturation(0.3F).meat().build();
        RICE = (new Food.Builder()).hunger(1).saturation(0.1F).build();
        RICE_STEW = buildStew(6);
        WELSH_CAKES = (new Food.Builder()).hunger(2).saturation(0.1F).build();
        WELSH_RAREBIT = (new Food.Builder()).hunger(8).saturation(0.5F).build();
        NOCTILUCA = (new Food.Builder()).hunger(1).saturation(0.3F).effect(new EffectInstance(Effects.WATER_BREATHING, 50, 1), 1.0F).build();
    }
}
