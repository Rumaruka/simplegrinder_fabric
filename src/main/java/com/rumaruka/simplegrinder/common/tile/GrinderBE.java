package com.rumaruka.simplegrinder.common.tile;

import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
import com.rumaruka.simplegrinder.common.recipe.GrinderRecipe;
import com.rumaruka.simplegrinder.init.SGTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class GrinderBE extends AbstractCoalGrinderBE {
    public GrinderBE(BlockPos pos, BlockState state) {
        super(SGTile.COAL_GRINDER_BE, pos, state, GrinderRecipe.RECIPE_TYPE);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.coal_grinder");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new GrinderMenu(syncId, playerInventory, this, this.propertyDelegate);
    }
}
