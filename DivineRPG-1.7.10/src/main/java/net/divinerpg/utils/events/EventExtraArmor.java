/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.client.render.gui.GUIOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EventExtraArmor {
    private final GUIOverlay gui = new GUIOverlay();

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.isCanceled() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        if (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
            this.gui.drawArmor();
        }
    }
}

