package com.rumaruka.simplegrinder;

import com.rumaruka.simplegrinder.init.SGBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModSetup {

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(SimpleGrinder.rl("simplegrinder"),
            ()->new ItemStack(SGBlocks.COAL_GRINDER));
}
