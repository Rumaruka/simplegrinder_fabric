package com.rumaruka.simplegrinder.common.items;


import com.rumaruka.simplegrinder.ModSetup;
import com.rumaruka.simplegrinder.init.SGFood;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class MashCarrotItem extends Item {


    public MashCarrotItem( ) {
        super(new FabricItemSettings().group(ModSetup.ITEM_GROUP).food(SGFood.MASH_CARROT));
    }
}
