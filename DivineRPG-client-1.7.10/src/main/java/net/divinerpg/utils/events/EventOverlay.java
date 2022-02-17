/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.gui.GUIOverlay;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EventOverlay {
    private final GUIOverlay gui = new GUIOverlay();

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {

    }
}

