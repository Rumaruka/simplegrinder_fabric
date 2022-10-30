package com.rumaruka.simplegrinder;

import com.rumaruka.simplegrinder.events.WorldJoin;
import com.rumaruka.simplegrinder.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class SimpleGrinder implements ModInitializer {

    public static final String MODID = "simplegrinder";
    @Override
    public void onInitialize() {
        SGBlocks.setup();
        SGItems.setup();
        SGTile.setup();

        SGRecipe.setup();

        registerEvents();
    }
    private void registerEvents() {
        ServerEntityEvents.ENTITY_LOAD.register(( entity,  world) -> {
            WorldJoin.onSpawn(world, entity);
        });
    }
    public static Identifier rl(String path){
        return new Identifier(MODID,path);
    }
}
