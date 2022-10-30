package com.rumaruka.simplegrinder.common.recipe;

import com.rumaruka.simplegrinder.init.SGRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class GrinderRecipe extends AbstractGrinderRecipe{

    public static final RecipeType<GrinderRecipe>RECIPE_TYPE = new RecipeType<GrinderRecipe>() {
        @Override
        public String toString() {
            return "simplegrinder:grinder";
        }
    };

    public GrinderRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SGRecipe.GRINDER;
    }
}
