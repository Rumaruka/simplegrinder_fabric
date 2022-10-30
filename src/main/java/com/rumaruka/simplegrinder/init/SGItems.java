package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.ModSetup;
import com.rumaruka.simplegrinder.common.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static com.rumaruka.simplegrinder.SimpleGrinder.rl;

public class SGItems {

    public static final DustItem IRON_DUST = new DustItem();
    public static final DustItem GOLD_DUST = new DustItem();
    public static final DustItem COPPER_DUST = new DustItem();

    public static final FlourItem FLOUR = new FlourItem();
    public static final MashPotatoItem MASH_POTATO = new MashPotatoItem();
    public static final MashCarrotItem MASH_CARROT = new MashCarrotItem();
    public static final OmleteItem OMLETE = new OmleteItem();


    public static final Item GRINDERBLOCK_ITEM = new BlockItem(SGBlocks.COAL_GRINDER, new FabricItemSettings().group(ModSetup.ITEM_GROUP));
    public static final Item MACHINE_CORE_ITEM = new BlockItem(SGBlocks.MACHINE_CORE, new FabricItemSettings().group(ModSetup.ITEM_GROUP));


    public static void setup() {

        Registry.register(Registry.ITEM, rl("dust_iron"), IRON_DUST);
        Registry.register(Registry.ITEM, rl("dust_gold"), GOLD_DUST);
        Registry.register(Registry.ITEM, rl("dust_copper"), COPPER_DUST);

        Registry.register(Registry.ITEM, rl("flour"), FLOUR);
        Registry.register(Registry.ITEM, rl("mash_potato"), MASH_POTATO);
        Registry.register(Registry.ITEM, rl("mash_carrot"), MASH_CARROT);
        Registry.register(Registry.ITEM, rl("omlete"), OMLETE);
        Registry.register(Registry.ITEM, rl("machine_core"), MACHINE_CORE_ITEM);
        Registry.register(Registry.ITEM, rl("coal_grinder"), GRINDERBLOCK_ITEM);

    }
}
