package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.common.tile.GrinderBE;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.rumaruka.simplegrinder.SimpleGrinder.rl;

public class SGTile {
    public static final BlockEntityType<GrinderBE> COAL_GRINDER_BE = FabricBlockEntityTypeBuilder.create(GrinderBE::new, SGBlocks.COAL_GRINDER).build(null);

    public static void setup() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, rl("grinder"), COAL_GRINDER_BE);

    }
}
