/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraft.entity.player.EntityPlayer
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.divinerpg.utils.events.ArcanaHelper;
import net.minecraft.entity.player.EntityPlayer;

public class ArcanaTickHandler {
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.onTickEnd(event.player);
        }
    }

    private void onTickEnd(EntityPlayer player) {
        ArcanaHelper.getProperties(player).updateAllBars();
    }

    private void onTickStart(EntityPlayer player) {
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ArcanaHelper.getProperties(event.player).setBarValue(0.0f);
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ArcanaHelper.getProperties(event.player).setBarValue(0.0f);
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent evt) {
        ArcanaHelper.getProperties(evt.player).setBarValue(0.0f);
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        ArcanaHelper.getProperties(event.player).setBarValue(0.0f);
    }
}

