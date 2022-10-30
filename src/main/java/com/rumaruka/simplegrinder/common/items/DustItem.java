package com.rumaruka.simplegrinder.common.items;

import com.rumaruka.simplegrinder.ModSetup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class DustItem extends Item {
    public DustItem( ) {
        super(new FabricItemSettings().group(ModSetup.ITEM_GROUP));
    }

    @Override
    public Item asItem() {
        return this;
    }
}
