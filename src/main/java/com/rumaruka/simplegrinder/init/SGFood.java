package com.rumaruka.simplegrinder.init;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;

public class SGFood {

    public static final FoodComponent MASH_CARROT;
    public static final FoodComponent MASH_POTATO;
    public static final FoodComponent OMLETE;
    static {
        MASH_CARROT = (new FoodComponent.Builder().hunger(4).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,2000,1),1.0f).saturationModifier(2.9f).build());
        MASH_POTATO = (new FoodComponent.Builder().hunger(4).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,2000,1),1.0f).saturationModifier(2.9f).build());
        OMLETE = (new FoodComponent.Builder().hunger(2).saturationModifier(2.6f).build());
    }
}
