package com.rumaruka.simplegrinder.common.menu.slots;

import com.rumaruka.simplegrinder.common.menu.AbstractGrinderMenu;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;


public class GrinderFuelSlot extends Slot {
    private final AbstractGrinderMenu menu;

    public GrinderFuelSlot(AbstractGrinderMenu handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.menu = handler;
    }

    public boolean canInsert(ItemStack p_39526_) {
        return this.menu.isFuel(p_39526_) || isBucket(p_39526_);
    }

    public int getMaxItemCount(ItemStack p_39528_) {
        return isBucket(p_39528_) ? 1 : super.getMaxItemCount(p_39528_);
    }

    public static boolean isBucket(ItemStack p_39530_) {
        return p_39530_.isOf(Items.BUCKET);
    }
}