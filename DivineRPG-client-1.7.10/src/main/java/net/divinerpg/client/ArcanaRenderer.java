/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 */
package net.divinerpg.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class ArcanaRenderer {
    Minecraft mc = Minecraft.getMinecraft();
    public static float value;
    public static boolean regen;

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        this.onTickRender();
    }

    private void onTickRender() {
        ConfigurationHelper cfg = new ConfigurationHelper();
        if (this.mc.currentScreen == null) {
            GuiIngame gig = this.mc.ingameGUI;
            ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            int i = scaledresolution.getScaledWidth();
            int k = scaledresolution.getScaledHeight();
            this.mc.getTextureManager().bindTexture(new ResourceLocation("divinerpg", "textures/gui/arcanaBar.png"));
            int y = k - ConfigurationHelper.arcanaY;
            int x = i - ConfigurationHelper.arcanaX;
            gig.drawTexturedModalRect(x, y, 0, 0, 100, 9);
            gig.drawTexturedModalRect(x, y, 0, 9, (int)(12.5 * (double)(value / 25.0f)), 18);
        }
    }
}

