/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class Ticker {
    public static int tick;

    @SubscribeEvent
    public void tickServer(TickEvent.ServerTickEvent evt) {
        if (evt.phase == TickEvent.Phase.END && ++tick > 100000) {
            tick = 0;
        }
    }
}

