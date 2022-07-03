/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui;

import com.meteor.extrabotany.client.gui.CustomButton;
import com.meteor.extrabotany.common.core.network.NetworkHandler2;
import com.meteor.extrabotany.common.core.network.networkItem.MessageHandleGuiItemButtonPress;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class GuiSettingArmor
extends GuiScreen {
    int guiWidth = 211;
    int guiHeight = 185;
    int guiX = 0;
    int guiY = 0;
    boolean effectHelmB;
    boolean effectChestB;
    boolean effectLegsB;
    boolean effectBootB;
    boolean superManaB;
    boolean superVisB = true;
    GuiButton effectHelm;
    GuiButton effectChest;
    GuiButton effectLegs;
    GuiButton effectBoot;
    GuiButton renderCircleBack;
    GuiButton renderCircleNext;
    GuiButton superMana;
    GuiButton superVIS;
    GuiButton teleport;

    public void updateScreen() {
        super.updateScreen();
        for (int i = 0; i < 4; ++i) {
            ItemStack st = this.mc.thePlayer.inventory.armorInventory[i];
            boolean potion = ItemNBTHelper.getBoolean((ItemStack)st, (String)"potion", (boolean)true);
            if (i == 0) {
                ((CustomButton)this.effectBoot).setActive(potion);
                this.effectBoot.displayString = potion ? "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c" : "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c";
                this.effectBootB = potion;
                continue;
            }
            if (i == 1) {
                ((CustomButton)this.effectLegs).setActive(potion);
                this.effectLegs.displayString = potion ? "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c" : "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c";
                this.effectLegsB = potion;
                continue;
            }
            if (i == 2) {
                ((CustomButton)this.effectChest).setActive(potion);
                this.effectChest.displayString = potion ? "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c" : "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c";
                this.effectChestB = potion;
                continue;
            }
            ((CustomButton)this.effectHelm).setActive(potion);
            this.effectHelm.displayString = potion ? "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c" : "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c";
            this.effectHelmB = potion;
        }
        boolean sv = ItemNBTHelper.getBoolean((ItemStack)this.mc.thePlayer.inventory.armorInventory[3], (String)"sVis", (boolean)false);
        ((CustomButton)this.superVIS).setActive(sv);
        this.superVIS.displayString = !sv ? "\u042d\u0442\u043e \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u043c\u0430\u043d\u044b, \u043d\u043e \u0443\u0432\u0435\u043b\u0438\u0447\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u0412\u0418\u0421" : "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c (\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f \u043c\u0430\u043d\u044b \u0432\u043e\u0437\u043e\u0431\u043d\u043e\u0432\u0438\u0442\u0441\u044f)";
        boolean sm = ItemNBTHelper.getBoolean((ItemStack)this.mc.thePlayer.inventory.armorInventory[3], (String)"sMana", (boolean)false);
        ((CustomButton)this.superMana).setActive(sm);
        this.superMana.displayString = !sm ? "\u042d\u0442\u043e \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u0412\u0418\u0421, \u043d\u043e \u0443\u0432\u0435\u043b\u0438\u0447\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u043c\u0430\u043d\u044b" : "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c (\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f \u0412\u0418\u0421 \u0432\u043e\u0437\u043e\u0431\u043d\u043e\u0432\u0438\u0442\u0441\u044f)";
    }

    public void initGui() {
        super.initGui();
        this.guiX = this.width / 2 - this.guiWidth / 2;
        this.guiY = this.height / 2 - this.guiHeight / 2;
        this.effectHelm = new CustomButton(0, this.guiX + 180, this.guiY + 30, 16, 16, "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c", true, 0);
        this.buttonList.add(this.effectHelm);
        this.effectChest = new CustomButton(1, this.guiX + 180, this.guiY + 47, 16, 16, "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c", true, 0);
        this.buttonList.add(this.effectChest);
        this.effectLegs = new CustomButton(2, this.guiX + 180, this.guiY + 64, 16, 16, "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c", true, 0);
        this.buttonList.add(this.effectLegs);
        this.effectBoot = new CustomButton(3, this.guiX + 180, this.guiY + 81, 16, 16, "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c", true, 0);
        this.buttonList.add(this.effectBoot);
        this.renderCircleBack = new CustomButton(4, this.guiX + 159, this.guiY + 98, 16, 16, "\u041d\u0430\u0437\u0430\u0434", false, 2);
        this.buttonList.add(this.renderCircleBack);
        this.renderCircleNext = new CustomButton(5, this.guiX + 180, this.guiY + 98, 16, 16, "\u0412\u043f\u0435\u0440\u0435\u0434", false, 1);
        this.buttonList.add(this.renderCircleNext);
        this.superMana = new CustomButton(6, this.guiX + 180, this.guiY + 115, 16, 16, "\u042d\u0442\u043e \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u0412\u0418\u0421, \u043d\u043e \u0443\u0432\u0435\u043b\u0438\u0447\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u043c\u0430\u043d\u044b", false, 0);
        this.buttonList.add(this.superMana);
        this.superVIS = new CustomButton(7, this.guiX + 180, this.guiY + 132, 16, 16, "\u042d\u0442\u043e \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u043c\u0430\u043d\u044b, \u043d\u043e \u0443\u0432\u0435\u043b\u0438\u0447\u0438\u0442 \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044e \u0412\u0418\u0421", false, 0);
        this.buttonList.add(this.superVIS);
        this.teleport = new CustomButton(8, this.guiX + 180, this.guiY + 149, 16, 16, "\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", false, 3);
        this.buttonList.add(this.teleport);
    }

    protected void actionPerformed(GuiButton btn) {
        super.actionPerformed(btn);
        NetworkHandler2.INSTANCE2.sendToServer((IMessage)new MessageHandleGuiItemButtonPress(this.mc.thePlayer.inventory.armorInventory[0], btn.id, (EntityPlayer)this.mc.thePlayer));
    }

    public void drawScreen(int x, int y, float ticks) {
        this.guiX = this.width / 2 - this.guiWidth / 2;
        this.guiY = this.height / 2 - this.guiHeight / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD2.png"));
        GuiSettingArmor.drawTexturedModalRect(this.guiX, this.guiY, 0.0f, 0, 0, this.guiWidth, this.guiHeight, Minecraft.getMinecraft());
        this.drawGradientRect(this.guiX + 3, this.guiY + 3, this.guiX + 209, this.guiY + 183, new Color(-2012213232, true).getRGB(), new Color(-2146430960, true).getRGB());
        GuiInventory.func_147046_a((int)(this.guiX + this.guiWidth + 70), (int)(this.guiY + 152), (int)60, (float)(this.guiX + this.guiWidth + 70 - x), (float)(this.guiY + 92 - y), (EntityLivingBase)this.mc.thePlayer);
        this.drawString();
        super.drawScreen(x, y, ticks);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    private void drawString() {
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        boolean unicode = font.getUnicodeFlag();
        font.setUnicodeFlag(true);
        font.drawString("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u0431\u0440\u043e\u043d\u0438", this.guiX + 70, this.guiY + 10, new Color(0xFFFFFF).getRGB());
        font.drawString("\u042d\u0444\u0444\u0435\u043a\u0442 \"\u041d\u043e\u0447\u043d\u043e\u0435 \u0437\u0440\u0435\u043d\u0438\u0435\"", this.guiX + 10, this.guiY + 35, new Color(0xFFFFFF).getRGB());
        font.drawString("\u042d\u0444\u0444\u0435\u043a\u0442 \"\u0421\u043e\u043f\u0440\u043e\u0442\u0438\u0432\u043b\u0435\u043d\u0438\u0435 \u0443\u0440\u043e\u043d\u0443\"", this.guiX + 10, this.guiY + 52, new Color(0xFFFFFF).getRGB());
        font.drawString("\u042d\u0444\u0444\u0435\u043a\u0442 \"\u0420\u0435\u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f\"", this.guiX + 10, this.guiY + 69, new Color(0xFFFFFF).getRGB());
        font.drawString("\u042d\u0444\u0444\u0435\u043a\u0442 \"\u0421\u044b\u0442\u043e\u0441\u0442\u044c\"", this.guiX + 10, this.guiY + 86, new Color(0xFFFFFF).getRGB());
        font.drawString("\u0420\u0438\u0441\u0443\u043d\u043e\u043a \u043a\u0440\u0443\u0433\u0430", this.guiX + 10, this.guiY + 103, new Color(0xFFFFFF).getRGB());
        font.drawString("\u0423\u0441\u0438\u043b\u0435\u043d\u043d\u0430\u044f \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f \u043c\u0430\u043d\u044b", this.guiX + 10, this.guiY + 120, new Color(0xFFFFFF).getRGB());
        font.drawString("\u0423\u0441\u0438\u043b\u0435\u043d\u043d\u0430\u044f \u0433\u0435\u043d\u0435\u0440\u0430\u0446\u0438\u044f \u0432\u0438\u0441", this.guiX + 10, this.guiY + 137, new Color(0xFFFFFF).getRGB());
        font.drawString("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u0432 \u0410\u0441\u0433\u0430\u0440\u0434", this.guiX + 10, this.guiY + 154, new Color(0xFFFFFF).getRGB());
        font.setUnicodeFlag(unicode);
    }

    public void drawWorldBackground(int p_146270_1_) {
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, Minecraft mc) {
        GuiSettingArmor.drawTexturedModalRect(x, y, z, texturesX, texturesY, width, height, 0.00390625f, 0.00390625f, mc);
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, float f, float f1, Minecraft mc) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD2.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(0x404040);
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.draw();
    }
}

