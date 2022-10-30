package com.rumaruka.simplegrinder.client.screen;

import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.rumaruka.simplegrinder.SimpleGrinder.MODID;

@Environment(EnvType.CLIENT)
public class GrinderScreen extends AbstractGrinderScreen<GrinderMenu> {

    private static final Identifier GUI = new Identifier(MODID, "textures/gui/container/grind.png");

    public GrinderScreen(GrinderMenu p_97825_, PlayerInventory p_97827_, Text p_97828_) {
        super(p_97825_, p_97827_, p_97828_, GUI);
    }


}
