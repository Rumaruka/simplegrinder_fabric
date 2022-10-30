package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.common.blocks.GrinderBlock;
import com.rumaruka.simplegrinder.common.blocks.MachineCoreBlock;
import net.minecraft.util.registry.Registry;

import static com.rumaruka.simplegrinder.SimpleGrinder.rl;

public class SGBlocks {

    public static final GrinderBlock COAL_GRINDER = new GrinderBlock();
    public static final MachineCoreBlock MACHINE_CORE = new MachineCoreBlock();

    public static void setup(){

        Registry.register(Registry.BLOCK, rl( "coal_grinder"), COAL_GRINDER);
        Registry.register(Registry.BLOCK, rl( "machine_core"), MACHINE_CORE);
    }
}
