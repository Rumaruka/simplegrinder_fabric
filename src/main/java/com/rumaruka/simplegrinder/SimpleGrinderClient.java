package com.rumaruka.simplegrinder;

import com.google.common.eventbus.Subscribe;
import com.rumaruka.simplegrinder.client.screen.GrinderScreen;
import com.rumaruka.simplegrinder.init.SGMenu;
import dev.architectury.event.events.common.PlayerEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;

public class SimpleGrinderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        setupScreens();

    }

    @SuppressWarnings({ "unchecked", "RedundantCast", "rawtypes" })
    private void setupScreens() {

        ScreenRegistry.register(SGMenu.GRINDER_MENU, GrinderScreen::new);
    }


}
