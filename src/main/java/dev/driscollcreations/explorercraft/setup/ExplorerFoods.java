package dev.driscollcreations.explorercraft.setup;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ExplorerFoods {

    //---- Vanilla Tweaks
    public static final FoodProperties NOCTILUCA = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 100, 1), 1.0F).alwaysEat().build();

    //---- Cymru Expansion
    public static final FoodProperties CAWL_STEW = buildStew(20);
    public static final FoodProperties CHEESE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).alwaysEat().build();
    public static final FoodProperties DRIED_FRUIT = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.3F).fast().build();
    public static final FoodProperties LAMB_SHANK = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).meat().build();
    public static final FoodProperties COOKED_LAMB_SHANK = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).meat().build();
    public static final FoodProperties LEEK = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build();
    public static final FoodProperties LEEK_STEW = buildStew(8);
    public static final FoodProperties WELSH_CAKES = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).fast().build();
    public static final FoodProperties WELSH_RAREBIT = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.6F).build();

    //---- Bamboo Grove
    public static final FoodProperties RICE = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();
    public static final FoodProperties RICE_BOWL = buildStew(7);
    public static final FoodProperties ONIGIRI = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).build();
    public static final FoodProperties SALMON_SUSHI = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.9F).build();
    public static final FoodProperties TAMAGO_SUSHI = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();

    private static FoodProperties buildStew(int hungerValue) {
        return (new FoodProperties.Builder()).nutrition(hungerValue).saturationMod(2.0F).build();
    }
}
