/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.cpanel.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EventHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event){
        System.out.println("Joined!");
    }
}

