package com.rumaruka.simplegrinder.common.compat.jei;

import com.rumaruka.simplegrinder.common.compat.jei.grinder.SimpleGrinderCategory;
import com.rumaruka.simplegrinder.common.compat.jei.grinder.SimpleGrinderRecipeDisplay;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.init.SGBlocks;
import com.rumaruka.simplegrinder.init.SGItems;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Items;
import org.jetbrains.annotations.ApiStatus;

import static com.rumaruka.simplegrinder.common.compat.jei.BuiltPlugin.GRINDER;

@Environment(EnvType.CLIENT)
@ApiStatus.Internal
public class SimpleGrinderPlugin implements REIClientPlugin  {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new SimpleGrinderCategory(GRINDER, EntryStacks.of(SGItems.GRINDERBLOCK_ITEM),"gui.jei.category.grinder"));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(GrinderRecipe.class,GrinderRecipe.RECIPE_TYPE,SimpleGrinderRecipeDisplay::new);
    }
}
