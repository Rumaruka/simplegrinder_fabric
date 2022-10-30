package com.rumaruka.simplegrinder.init;

import com.rumaruka.simplegrinder.common.menu.GrinderMenu;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

import static com.rumaruka.simplegrinder.SimpleGrinder.rl;

public class SGMenu {

   public static final ScreenHandlerType<GrinderMenu> GRINDER_MENU = ScreenHandlerRegistry.registerSimple(rl("coal_grinder"),GrinderMenu::new);

    public static void setup(){
        Registry.register(Registry.SCREEN_HANDLER, rl( "grinder"), GRINDER_MENU);
    }
}
