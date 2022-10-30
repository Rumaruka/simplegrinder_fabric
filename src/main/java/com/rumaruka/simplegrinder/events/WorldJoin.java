package com.rumaruka.simplegrinder.events;

import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.awt.*;
import java.util.UUID;

public class WorldJoin {
    static boolean isWar = true;

    public static void onSpawn(World world, Entity player) {
        if(player instanceof PlayerEntity)

            if(isWar){


                    player.sendMessage(Text.translatable("================"));
                    player.sendMessage(Text.translatable(Formatting.BLUE+"MAKE LOVE!"));
                    player.sendMessage(Text.translatable(Formatting.YELLOW+"NOT WAR!"));
                    player.sendMessage(Text.translatable("================"));



        }

    }
}
