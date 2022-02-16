/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  net.minecraft.client.Minecraft
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.divinerpg.client.ArcanaRenderer;
import net.divinerpg.client.render.gui.GUIOverlay;
import net.minecraft.client.Minecraft;

public class ClientTicker {
    public static int tick;

    @SubscribeEvent
    public void tickClient(TickEvent.ClientTickEvent evt) {
        if (evt.phase == TickEvent.Phase.END) {
            ++tick;
            if (GUIOverlay.guiTick > 0) {
                --GUIOverlay.guiTick;
            }
            if (ArcanaRenderer.regen && ArcanaRenderer.value < 200.0f && Minecraft.getMinecraft().currentScreen == null) {
                ArcanaRenderer.value += 1.0f;
            }
        }
    }
}

