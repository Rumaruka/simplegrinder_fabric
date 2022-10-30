package com.rumaruka.simplegrinder.common.menu;

import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.init.SGMenu;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;


public class GrinderMenu extends AbstractGrinderMenu{
    public GrinderMenu(int syncId, PlayerInventory playerInventory) {
        super(SGMenu.GRINDER_MENU, GrinderRecipe.RECIPE_TYPE,  syncId, playerInventory);
    }

    public GrinderMenu(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(SGMenu.GRINDER_MENU, GrinderRecipe.RECIPE_TYPE,  syncId, playerInventory, inventory, propertyDelegate);
    }
}
