/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.base.DivineBossStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BossTickHandler {
    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        this.onTickRender();
    }

    @SideOnly(value=Side.CLIENT)
    private void onTickRender() {
        if (DivineBossStatus.statusBarTime > 0 && this.mc.currentScreen == null) {
            --DivineBossStatus.statusBarTime;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            this.mc.mcProfiler.startSection("divineBossHealth");
            this.renderBossHealth();
            this.mc.mcProfiler.endSection();
            GL11.glDisable((int)3042);
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void renderBossHealth() {
        ResourceLocation r = null;
        switch (DivineBossStatus.bossNumber) {
            case 1: {
                r = this.set("ancientEntity");
                break;
            }
            case 2: {
                r = this.set("greenAyeraco");
                break;
            }
            case 4: {
                r = this.set("purpleAyeraco");
                break;
            }
            case 6: {
                r = this.set("yellowAyeraco");
                break;
            }
            case 5: {
                r = this.set("redAyeraco");
                break;
            }
            case 3: {
                r = this.set("blueAyeraco");
                break;
            }
            case 7: {
                r = this.set("kingofScorchers");
                break;
            }
            case 8: {
                r = this.set("theWatcher");
                break;
            }
            case 9: {
                r = this.set("densos");
                break;
            }
            case 10: {
                r = this.set("reyvor");
                break;
            }
            case 11: {
                r = this.set("twilightDemon");
                break;
            }
            case 12: {
                r = this.set("soulFiend");
                break;
            }
            case 13: {
                r = this.set("vamacheron");
                break;
            }
            case 14: {
                r = this.set("karot");
                break;
            }
            case 15: {
                r = this.set("eternalArcher");
                break;
            }
            case 16: {
                r = this.set("dramix");
                break;
            }
            case 17: {
                r = this.set("parasecta");
                break;
            }
            case 18: {
                r = this.set("hiveQueen");
                break;
            }
            case 19: {
                r = this.set("raglok");
                break;
            }
            case 20: {
                r = this.set("quadro");
                break;
            }
            case 21: {
                r = this.set("ladyLuna");
                break;
            }
            case 22: {
                r = this.set("wreck");
                break;
            }
            case 23: {
                r = this.set("drKaros");
            }
        }
        GuiIngame gig = this.mc.ingameGUI;
        FontRenderer fontrenderer = this.mc.fontRenderer;
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int barLength = 182;
        int finalBarLength = barLength + 1;
        int barDisX = i / 2 - barLength / 2;
        int barDisY = 12;
        int health = (int)(DivineBossStatus.healthScale * (float)finalBarLength);
        int barHeight = 10;
        this.mc.getTextureManager().bindTexture(r);
        gig.drawTexturedModalRect(barDisX, barDisY, 0, 0, health, barHeight);
        gig.drawTexturedModalRect(barDisX, barDisY, 0, barHeight, finalBarLength, barHeight);
        if (health > 0) {
            gig.drawTexturedModalRect(barDisX, barDisY, 0, 0, health, 10);
        }
    }

    public ResourceLocation set(String name) {
        return new ResourceLocation("divinerpg:textures/gui/bossBar/" + name + ".png");
    }
}

