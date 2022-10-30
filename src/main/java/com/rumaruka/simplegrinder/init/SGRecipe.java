package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

import static com.rumaruka.simplegrinder.SimpleGrinder.rl;

public class SGRecipe {

    public static final GrinderRecipeSerializer<GrinderRecipe> GRINDER = new GrinderRecipeSerializer<>(GrinderRecipe::new,100);
    public static void setup(){
        Registry.register(Registry.RECIPE_SERIALIZER, rl( "grinder"), GRINDER);
    }
}
