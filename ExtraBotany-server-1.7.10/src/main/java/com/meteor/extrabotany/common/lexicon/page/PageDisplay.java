/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class PageDisplay
extends PageRecipe {
    ItemStack stack;
    int relativeMouseX;
    int relativeMouseY;
    ItemStack tooltipStack;
    ItemStack tooltipContainerStack;
    boolean tooltipEntry;
    static boolean mouseDownLastTick = false;

    public PageDisplay(String unlocalizedName, ItemStack stack) {
        super(unlocalizedName);
        this.stack = stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        this.relativeMouseX = mx;
        this.relativeMouseY = my;
        int width = gui.getWidth() - 30;
        int height = gui.getHeight();
        int x = gui.getLeft() + 16;
        int y = gui.getTop() + height - 40;
        this.renderItem(gui, x + 5, (double)y * 0.6, this.stack, true);
        PageText.renderText(x, y, width, height, this.getUnlocalizedName());
        if (this.tooltipStack != null) {
            List tooltipData = this.tooltipStack.getTooltip(Minecraft.getMinecraft().thePlayer, false);
            ArrayList<String> parsedTooltip = new ArrayList<String>();
            boolean first = true;
            Iterator tooltipY = tooltipData.iterator();
            while (tooltipY.hasNext()) {
                String s;
                String s_ = s = (String)tooltipY.next();
                if (!first) {
                    s_ = EnumChatFormatting.GRAY + s;
                }
                parsedTooltip.add(s_);
                first = false;
            }
            RenderHelper.renderTooltip(mx, my, parsedTooltip);
            int tooltipY1 = 8 + tooltipData.size() * 11;
            if (this.tooltipEntry) {
                RenderHelper.renderTooltipOrange(mx, my + tooltipY1, Arrays.asList(EnumChatFormatting.GRAY + StatCollector.translateToLocal("botaniamisc.clickToRecipe")));
                tooltipY1 += 18;
            }
            if (this.tooltipContainerStack != null) {
                RenderHelper.renderTooltipGreen(mx, my + tooltipY1, Arrays.asList(EnumChatFormatting.AQUA + StatCollector.translateToLocal("botaniamisc.craftingContainer"), this.tooltipContainerStack.getDisplayName()));
            }
        }
        this.tooltipContainerStack = null;
        this.tooltipStack = null;
        this.tooltipEntry = false;
        GL11.glDisable(3042);
        mouseDownLastTick = Mouse.isButtonDown(0);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItem(IGuiLexiconEntry gui, double xPos, double yPos, ItemStack stack, boolean accountForContainer) {
        RenderItem render = new RenderItem();
        boolean mouseDown = Mouse.isButtonDown(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable(32826);
        GL11.glEnable(2929);
        GL11.glPushMatrix();
        GL11.glTranslated(xPos, yPos, 0.0);
        GL11.glScalef(6.0f, 6.0f, 6.0f);
        render.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, 0, 0);
        render.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, 0, 0);
        GL11.glPopMatrix();
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
        int xpi = (int)xPos;
        int ypi = (int)yPos;
        if (this.relativeMouseX >= xpi && this.relativeMouseY >= ypi && this.relativeMouseX <= xpi + 16 && this.relativeMouseY <= ypi + 16) {
            ItemStack containerStack1;
            this.tooltipStack = stack;
            LexiconRecipeMappings.EntryData data = LexiconRecipeMappings.getDataForStack(this.tooltipStack);
            ItemStack book = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
            if (data != null && (data.entry != gui.getEntry() || data.page != gui.getPageOn()) && book != null && book.getItem() instanceof ILexicon && ((ILexicon)book.getItem()).isKnowledgeUnlocked(book, data.entry.getKnowledgeType())) {
                this.tooltipEntry = true;
                if (!mouseDownLastTick && mouseDown && GuiScreen.isShiftKeyDown()) {
                    GuiLexiconEntry containerStack = new GuiLexiconEntry(data.entry, (GuiScreen)gui);
                    containerStack.page = data.page;
                    Minecraft.getMinecraft().displayGuiScreen(containerStack);
                }
            } else {
                this.tooltipEntry = false;
            }
            if (accountForContainer && (containerStack1 = stack.getItem().getContainerItem(stack)) != null && containerStack1.getItem() != null) {
                this.tooltipContainerStack = containerStack1;
            }
        }
        GL11.glDisable(2896);
    }
}

