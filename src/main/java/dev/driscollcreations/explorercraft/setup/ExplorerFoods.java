package dev.driscollcreations.explorercraft.setup;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ExplorerFoods {

    //---- Vanilla Tweaks
    public static final Food NOCTILUCA = (new Food.Builder()).nutrition(1).saturationMod(0.3F).effect(new EffectInstance(Effects.WATER_BREATHING, 100, 1), 1.0F).alwaysEat().build();

    //---- Cymru Expansion
    public static final Food CAWL_STEW = buildStew(10);
    public static final Food CHEESE = (new Food.Builder()).nutrition(2).saturationMod(0.3F).alwaysEat().build();
    public static final Food DRIED_FRUIT = (new Food.Builder()).nutrition(1).saturationMod(0.3F).fast().build();
    public static final Food LAMB_SHANK = (new Food.Builder()).nutrition(2).saturationMod(0.3F).meat().build();
    public static final Food COOKED_LAMB_SHANK = (new Food.Builder()).nutrition(6).saturationMod(0.8F).meat().build();
    public static final Food LEEK = (new Food.Builder()).nutrition(3).saturationMod(0.6F).build();
    public static final Food LEEK_STEW = buildStew(6);
    public static final Food WELSH_CAKES = (new Food.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final Food WELSH_RAREBIT = (new Food.Builder()).nutrition(8).saturationMod(0.5F).build();

    //---- Bamboo Grove
    public static final Food RICE = (new Food.Builder()).nutrition(1).saturationMod(0.1F).build();
    public static final Food RICE_BOWL = buildStew(7);
    public static final Food ONIGIRI = (new Food.Builder()).nutrition(3).saturationMod(0.5F).build();
    public static final Food SALMON_SUSHI = (new Food.Builder()).nutrition(7).saturationMod(0.9F).build();
    public static final Food TAMAGO_SUSHI = (new Food.Builder()).nutrition(6).saturationMod(0.6F).build();

    private static Food buildStew(int hungerValue) {
        return (new Food.Builder()).nutrition(hungerValue).saturationMod(0.6F).build();
    }
}
