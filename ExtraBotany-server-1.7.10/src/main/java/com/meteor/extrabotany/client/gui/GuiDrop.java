/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui;

import cpw.mods.fml.client.GuiModList;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.lwjgl.opengl.GL11;

@SideOnly(value= Side.CLIENT)
public class GuiDrop {
    private static ResourceLocation drop = new ResourceLocation("extrabotania", "textures/gui/drops.png");
    public static Random RNG = new Random();
    private Drop[] cloud;
    private int prevGuiW;
    public static final List GUIS = new ArrayList();

    public GuiDrop() {
        this.reset();
    }

    @SubscribeEvent
    public void drawGui(GuiScreenEvent.DrawScreenEvent.Post e) {
        if (GUIS.contains(e.gui.getClass())) {
            if (this.prevGuiW != e.gui.width) {
                this.prevGuiW = e.gui.width;
                this.reset();
            }
            this.drawScreen(e.gui.width, e.gui.height, e.mouseX, e.mouseY);
        }
    }

    @SubscribeEvent
    public void openGui(GuiOpenEvent e) {
        if (e.gui != null) {
            this.reset();
        }
    }

    public void drawScreen(int width, int height, int mouseX, int mouseY) {
        Minecraft.getMinecraft().renderEngine.bindTexture(drop);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int i = 0;
        for (Drop Drop2 : this.cloud) {
            if (Drop2 != null && !Drop2.isDead()) {
                Drop2.draw(width, height, mouseX, mouseY);
                if (Drop2.isDead()) {
                    this.createDropWithChance(i, width, height);
                }
            } else {
                this.createDropWithChance(i, width, height);
            }
            ++i;
        }
    }

    private void createDropWithChance(int id, int width, int height) {
        if (RNG.nextFloat() * 100.0f <= 0.8f) {
            this.cloud[id] = new Drop(width, height);
        }
    }

    private void reset() {
        this.cloud = new Drop[150];
    }

    static {
        GUIS.add(GuiMainMenu.class);
        GUIS.add(GuiMultiplayer.class);
        GUIS.add(GuiSelectWorld.class);
        GUIS.add(GuiCreateWorld.class);
        GUIS.add(GuiLanguage.class);
        GUIS.add(GuiControls.class);
        GUIS.add(GuiScreenResourcePacks.class);
        GUIS.add(GuiScreenOptionsSounds.class);
        GUIS.add(ScreenChatOptions.class);
        GUIS.add(GuiStats.class);
        GUIS.add(GuiIngameMenu.class);
        GUIS.add(GuiModList.class);
        GUIS.add(GuiOptions.class);
        GUIS.add(GuiVideoSettings.class);
    }
}

