/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui;

import com.meteor.extrabotany.client.gui.Inventarium.LibSkillID;
import com.meteor.extrabotany.common.core.network.NetworkHandler2;
import com.meteor.extrabotany.common.core.network.networkItem.MessageHandleGuiItemButtonPress;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class GuiSkill
extends GuiScreen {
    int guiWidth = 256;
    int guiHeight = 256;
    int guiX = 0;
    int guiY = 0;
    private ArrayList<String> textSkill = new ArrayList();
    private int page = 0;
    private int skill = 0;
    String textures = "";
    private ArrayList<Integer> activeSkill = new ArrayList();
    private int tickShowError = 0;
    private String textShowError = "";

    public GuiSkill(String textures, int page, int skill) {
        this.textures = textures;
        this.page = page;
        this.skill = skill;
        this.readArm();
    }

    public void updateScreen() {
        super.updateScreen();
        this.readArm();
        if (this.tickShowError > 0) {
            --this.tickShowError;
            if (this.tickShowError == 0) {
                this.textShowError = "";
            }
        }
    }

    private void readArm() {
        EntityClientPlayerMP pl = Minecraft.getMinecraft().thePlayer;
        ItemStack arm = pl.inventory.armorInventory[1];
        NBTTagList list = ItemNBTHelper.getList((ItemStack)arm, (String)"activeSkill", (int)10, (boolean)false);
        ArrayList<Integer> _act = new ArrayList<Integer>();
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound _n = list.getCompoundTagAt(i);
            if (!_n.hasKey("id") || _act.contains(_n.getInteger("id")) || !_n.hasKey("active") || !_n.getBoolean("active")) continue;
            _act.add(_n.getInteger("id"));
        }
        this.activeSkill = _act;
    }

    public void drawScreen(int x, int y, float ticks) {
        this.guiX = this.width / 2 - this.guiWidth / 2;
        this.guiY = this.height / 2 - this.guiHeight / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawDefaultBackground();
        this.mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", this.textures.isEmpty() ? LibSkillID.texturesPart[2] : this.textures));
        this.drawTexturedModalRect(this.guiX, this.guiY, 0, 0, 256, 256);
        if (this.page < 0) {
            this.drawText();
        }
        this.drawToolTip(x, y);
        if (!this.textShowError.isEmpty()) {
            this.drawString(this.fontRendererObj, "\u00a7c\u00a7l" + this.textShowError, this.width / 2 - this.textShowError.length() * 7 / 2 + 20, this.height / 2 + this.guiHeight / 2 + 15, new Color(0).getRGB());
        }
        super.drawScreen(x, y, ticks);
    }

    private void drawText() {
        int yc;
        int xc;
        String s;
        int line = LibSkillID.allSkillCount[this.skill];
        for (int i = 0; i < line; ++i) {
            s = StatCollector.translateToLocal((String)(LibSkillID.allSkill[this.skill] + Integer.toString(i)));
            xc = this.width / 2 - s.length() * 5 / 2 - 10;
            yc = this.height / 2 - this.guiHeight / 2 + ((line <= 2 ? 125 : (line <= 4 ? 115 : 95)) + 10 * i);
            this.drawString(this.fontRendererObj, s, xc, yc, new Color(0xFFFFFF).getRGB());
        }
        int cd = LibSkillID.hasCD[this.skill];
        if (cd > 0) {
            GuiSkill.drawRect((int)(this.width / 2 - this.guiWidth / 2 + 78), (int)(this.height / 2 - this.guiHeight / 2 + 188), (int)(this.width / 2 - this.guiWidth / 2 + 179), (int)(this.height / 2 - this.guiHeight / 2 + 217), (int)new Color(16539990).getRGB());
            s = "";
            s = (double)cd / 3600.0 >= 1.0 ? "cooldown: " + Integer.toString((int)Math.floor((double)cd / 3600.0)) + " h." : ((double)cd / 600.0 >= 1.0 ? "cooldown: " + Integer.toString((int)Math.floor((double)cd / 600.0)) + " m." : "cooldown: " + Integer.toString(cd) + " s.");
            xc = this.width / 2 - s.length() * 5 / 2;
            yc = this.height / 2 + this.guiHeight / 2 - 57;
            this.drawString(this.fontRendererObj, s, xc, yc, new Color(0xFFFFFF).getRGB());
        }
        boolean active = false;
        if (this.activeSkill.contains(this.skill)) {
            active = true;
        }
        String _s = active ? StatCollector.translateToLocal((String)"gui.skill.base.activate") : StatCollector.translateToLocal((String)"gui.skill.base.disable");
        int xc2 = this.width / 2 - _s.length() * 5 / 2;
        int yc2 = this.height / 2 - this.guiHeight / 2 + 41;
        if (this.skill != 0) {
            this.drawString(this.fontRendererObj, _s, xc2, yc2, new Color(0xFFFFFF).getRGB());
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseType) {
        int sk = -1;
        int[][] _coord = this.getCoord();
        for (int i = 0; i < _coord.length; ++i) {
            int xc = this.guiX + _coord[i][0];
            int yc = this.guiY + _coord[i][1];
            if (mouseX < xc || mouseX > xc + 16 || mouseY < yc || mouseY > yc + 16) continue;
            sk = i;
            break;
        }
        if (sk != -1) {
            if (this.page != 0 && sk == 0) {
                int part = LibSkillID.part[this.skill];
                this.mc.displayGuiScreen((GuiScreen)new GuiSkill(LibSkillID.texturesPart[part + 2], part, 0));
                return;
            }
            if (this.page == 0 && sk == 0) {
                this.mc.displayGuiScreen((GuiScreen)new GuiSkill(LibSkillID.texturesPart[0], -2, 0));
                return;
            }
            if (this.page == 0) {
                this.mc.displayGuiScreen((GuiScreen)new GuiSkill(LibSkillID.texturesPart[sk + 2], sk, 0));
                return;
            }
            if (this.page > 0) {
                int id_sk = LibSkillID.idSkill[this.page - 1][sk];
                this.mc.displayGuiScreen((GuiScreen)new GuiSkill(LibSkillID.texturesPart[1], -1, id_sk));
                return;
            }
            if (sk == 1 && !this.hasMinIsPart()) {
                return;
            }
            NetworkHandler2.INSTANCE2.sendToServer((IMessage)new MessageHandleGuiItemButtonPress(this.mc.thePlayer.inventory.armorInventory[1], this.addBoost() + this.skill * (sk == 2 ? -1 : 1), (EntityPlayer)this.mc.thePlayer));
            return;
        }
    }

    private int addBoost() {
        if (ItemKillerArmor.hasFullArmor((EntityPlayer)this.mc.thePlayer)) {
            return 100;
        }
        return 0;
    }

    private boolean hasMinIsPart() {
        int id_part = LibSkillID.part[this.skill];
        int count = 0;
        int[] idSkillInPart = LibSkillID.idSkill[id_part - 1];
        for (int i = 0; i < idSkillInPart.length; ++i) {
            if (idSkillInPart[i] < 0 || !this.activeSkill.contains(idSkillInPart[i])) continue;
            ++count;
        }
        if (count >= 3) {
            this.tickShowError = 60;
            this.textShowError = StatCollector.translateToLocal((String)"gui.base.error.activate");
            return false;
        }
        return true;
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    private int[][] getCoord() {
        return this.page == -2 ? LibSkillID.coordPage_2 : (this.page == -1 ? LibSkillID.coordPage_1 : (this.page == 0 ? LibSkillID.coordPage0 : (this.page == 1 ? LibSkillID.coordPage1 : (this.page == 2 ? LibSkillID.coordPage2 : (this.page == 3 ? LibSkillID.coordPage3 : LibSkillID.coordPage4)))));
    }

    private void drawToolTip(int x, int y) {
        int[][] _coord = this.getCoord();
        String[] _tooltip = this.page == -2 ? LibSkillID.skillToolTipPage_2 : (this.page == -1 ? LibSkillID.skillToolTipPage_1 : (this.page == 0 ? LibSkillID.skillToolTipPage0 : (this.page == 1 ? LibSkillID.skillToolTipPage1 : (this.page == 2 ? LibSkillID.skillToolTipPage2 : (this.page == 3 ? LibSkillID.skillToolTipPage3 : LibSkillID.skillToolTipPage4)))));
        for (int i = 0; i < _coord.length; ++i) {
            int xc = this.guiX + _coord[i][0];
            int yc = this.guiY + _coord[i][1];
            if (x < xc || x > xc + 16 || y < yc || y > yc + 16) continue;
            ArrayList<String> list = new ArrayList<String>();
            list.add("\u00a7f" + StatCollector.translateToLocal((String)_tooltip[i]));
            if (this.page >= 0 || this.page != 0 && i != 0) {
                if (!GuiSkill.isShiftKeyDown()) {
                    list.add("\u00a7f" + StatCollector.translateToLocal((String)"gui.skill.base.shift"));
                } else {
                    list.add("\u00a7f" + StatCollector.translateToLocal((String)"gui.skill.click.name"));
                }
            }
            this.drawHoveringText(list, x, y, this.fontRendererObj);
            break;
        }
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, Minecraft mc) {
        GuiSkill.drawTexturedModalRect(x, y, z, texturesX, texturesY, width, height, 0.00390625f, 0.00390625f, mc);
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, float f, float f1, Minecraft mc) {
        Profiler prof = Minecraft.getMinecraft().mcProfiler;
        prof.startSection("extrabotania-skill");
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable((int)32826);
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.draw();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2929);
        GL11.glEnable((int)2929);
        prof.endSection();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

