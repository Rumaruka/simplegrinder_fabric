package com.rumaruka.simplegrinder.common.compat.jei;

import com.rumaruka.simplegrinder.ModSetup;
import com.rumaruka.simplegrinder.SimpleGrinder;
import com.rumaruka.simplegrinder.common.compat.jei.grinder.SimpleGrinderRecipeDisplay;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;


public interface  BuiltPlugin {

    CategoryIdentifier<SimpleGrinderRecipeDisplay> GRINDER = CategoryIdentifier.of(SimpleGrinder.MODID, "plugins/grinder");

}
