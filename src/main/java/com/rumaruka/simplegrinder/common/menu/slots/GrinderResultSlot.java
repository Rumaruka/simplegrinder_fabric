package com.rumaruka.simplegrinder.common.menu.slots;


import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;



public class GrinderResultSlot extends Slot {
    private final PlayerEntity player;
    private int removeCount;

    public GrinderResultSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
    }

    public boolean canInsert(ItemStack p_39553_) {
        return false;
    }

    public ItemStack takeStack(int p_39548_) {
        if (this.hasStack()) {
            this.removeCount += Math.min(p_39548_, this.getStack().getCount());
        }

        return super.takeStack(p_39548_);
    }

    public void onTakeItem(PlayerEntity p_150563_, ItemStack p_150564_) {
        this.onCrafted(p_150564_);
        super.onTakeItem(p_150563_, p_150564_);
    }

    protected void onCrafted(ItemStack p_39555_, int p_39556_) {
        this.removeCount += p_39556_;
        this.onCrafted(p_39555_);
    }

    protected void onCrafted(ItemStack stack) {
        stack.onCraft(this.player.world, this.player, this.removeCount);
        if (this.player instanceof ServerPlayerEntity && this.inventory instanceof AbstractFurnaceBlockEntity) {
            ((AbstractFurnaceBlockEntity)this.inventory).dropExperienceForRecipesUsed((ServerPlayerEntity)this.player);
        }

        this.removeCount = 0;
    }
}
