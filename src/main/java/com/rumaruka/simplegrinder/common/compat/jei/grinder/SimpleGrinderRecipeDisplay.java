package com.rumaruka.simplegrinder.common.compat.jei.grinder;

import com.rumaruka.simplegrinder.common.compat.jei.BuiltPlugin;
import com.rumaruka.simplegrinder.common.recipe.AbstractGrinderRecipe;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.text.Text;

import java.util.List;

public class SimpleGrinderRecipeDisplay extends AbsSimpleGrinderRecipeDisplay  {

    public SimpleGrinderRecipeDisplay(GrinderRecipe recipe) {
        super(recipe);
    }

    public SimpleGrinderRecipeDisplay(List<EntryIngredient> input, List<EntryIngredient> output, NbtCompound tag) {
        super(input, output, tag);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return BuiltPlugin.GRINDER;
    }
}
