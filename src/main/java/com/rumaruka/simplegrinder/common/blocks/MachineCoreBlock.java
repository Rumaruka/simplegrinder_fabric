package com.rumaruka.simplegrinder.common.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;

public class MachineCoreBlock extends Block {
    public MachineCoreBlock( ) {
        super(FabricBlockSettings.copyOf(Blocks.COBBLESTONE));
    }
}
