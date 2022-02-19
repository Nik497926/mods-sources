/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package info.jbcs.minecraft.vending.tileentity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientTicks {
    public static int ticks = 0;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (++ticks == 21) {
            ticks = 0;
        }
    }
}

