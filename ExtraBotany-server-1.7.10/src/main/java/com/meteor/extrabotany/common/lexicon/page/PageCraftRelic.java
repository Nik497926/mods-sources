/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.lexicon.page;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageCraftRelic
extends PageRecipe {
    ItemStack stack;
    int relativeMouseX;
    int relativeMouseY;
    ItemStack tooltipStack;
    ItemStack tooltipContainerStack;
    boolean tooltipEntry;
    static boolean mouseDownLastTick = false;
    ItemStack[] input;
    int efir = 0;

    public PageCraftRelic(String unlocalizedName, ItemStack stack, ItemStack[] input, int efir) {
        super(unlocalizedName);
        this.stack = stack;
        this.input = input;
        this.efir = efir;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        this.relativeMouseX = mx;
        this.relativeMouseY = my;
        int width = gui.getWidth();
        int height = gui.getHeight();
        int x = gui.getLeft() + 16;
        int y = gui.getTop() + height - 40;
        int yc = gui.getTop() + 36;
        int xc = x + 5;
        int[][] coordInput = new int[][]{{0, 0}, {42, 42}, {0, 42}, {42, 0}};
        HashMap<String, int[]> listText = new HashMap<String, int[]>();
        this.renderItem(gui, xc + 91, yc + 21, this.stack, true, true);
        PageCraftRelic.drawTexturedModalRect(xc + 66, yc + 24, 0.0f, 1, 160, 18, 10, Minecraft.getMinecraft());
        for (int i = 0; i < this.input.length; ++i) {
            if (this.input[i] == null) continue;
            this.renderItem(gui, xc + coordInput[i][0], yc + coordInput[i][1], this.input[i], true, true);
        }
        this.renderItem(gui, xc + 21, yc + 21, new ItemStack(ModBlocks.relicplate), true, true);
        HUDHandler.renderManaBar((int)xc, (int)(yc + 95), (int)14435, (float)0.75f, (int)this.efir, (int)10000000);
        String _s = "\u041c\u0430\u043d\u0430";
        listText.put(_s, new int[]{new Color(14435).getRGB(), gui.getLeft() + width / 2 - _s.length() * 5 / 2 - 2, yc + 105});
        if (!ExtraBotany.alfheimLoaded) {
            _s = "" + Integer.toString(this.efir);
            listText.put(_s, new int[]{new Color(14435).getRGB(), x, yc + 85});
            _s = Integer.toString(10000);
            listText.put(_s, new int[]{new Color(14435).getRGB(), gui.getLeft() + width - _s.length() * 5 - 24, yc + 85});
        }
        _s = this.stack.getDisplayName();
        listText.put(_s, new int[]{new Color(14435).getRGB(), gui.getLeft() + width / 2 - _s.length() * 5 / 2 - 2, yc + 120});
        _s = ModBlocks.relicplate.getLocalizedName();
        listText.put(_s, new int[]{new Color(14435).getRGB(), gui.getLeft() + width / 2 - _s.length() * 5 / 2 - 2, yc - 20});
        if (this.tooltipStack != null) {
            List tooltipData = this.tooltipStack.getTooltip((EntityPlayer)Minecraft.getMinecraft().thePlayer, false);
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
            RenderHelper.renderTooltip((int)mx, (int)my, parsedTooltip);
            int tooltipY1 = 8 + tooltipData.size() * 11;
            if (this.tooltipEntry) {
                RenderHelper.renderTooltipOrange((int)mx, (int)(my + tooltipY1), Arrays.asList(EnumChatFormatting.GRAY + StatCollector.translateToLocal((String)"botaniamisc.clickToRecipe")));
                tooltipY1 += 18;
            }
            if (this.tooltipContainerStack != null) {
                RenderHelper.renderTooltipGreen((int)mx, (int)(my + tooltipY1), Arrays.asList(EnumChatFormatting.AQUA + StatCollector.translateToLocal((String)"botaniamisc.craftingContainer"), this.tooltipContainerStack.getDisplayName()));
            }
        }
        this.tooltipContainerStack = null;
        this.tooltipStack = null;
        this.tooltipEntry = false;
        GL11.glDisable((int)3042);
        mouseDownLastTick = Mouse.isButtonDown((int)0);
        this.renderText(Minecraft.getMinecraft(), listText);
    }

    @SideOnly(value=Side.CLIENT)
    private void renderText(Minecraft mc, HashMap<String, int[]> list) {
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2929);
        for (Map.Entry<String, int[]> entry : list.entrySet()) {
            mc.fontRenderer.drawString(entry.getKey(), entry.getValue()[1], entry.getValue()[2], entry.getValue()[0]);
        }
        GL11.glEnable((int)2929);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItem(IGuiLexiconEntry gui, double xPos, double yPos, ItemStack stack, boolean accountForContainer, boolean center) {
        RenderItem render = new RenderItem();
        boolean mouseDown = Mouse.isButtonDown((int)0);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2929);
        GL11.glPushMatrix();
        GL11.glTranslated((double)xPos, (double)yPos, (double)0.0);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
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
            LexiconRecipeMappings.EntryData data = LexiconRecipeMappings.getDataForStack((ItemStack)this.tooltipStack);
            ItemStack book = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
            if (data != null && (data.entry != gui.getEntry() || data.page != gui.getPageOn()) && book != null && book.getItem() instanceof ILexicon && ((ILexicon)book.getItem()).isKnowledgeUnlocked(book, data.entry.getKnowledgeType())) {
                this.tooltipEntry = true;
                if (!mouseDownLastTick && mouseDown && GuiScreen.isShiftKeyDown()) {
                    GuiLexiconEntry containerStack = new GuiLexiconEntry(data.entry, (GuiScreen)gui);
                    containerStack.page = data.page;
                    Minecraft.getMinecraft().displayGuiScreen((GuiScreen)containerStack);
                }
            } else {
                this.tooltipEntry = false;
            }
            if (accountForContainer && (containerStack1 = stack.getItem().getContainerItem(stack)) != null && containerStack1.getItem() != null) {
                this.tooltipContainerStack = containerStack1;
            }
        }
        GL11.glDisable((int)2896);
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, Minecraft mc) {
        PageCraftRelic.drawTexturedModalRect(x, y, z, texturesX, texturesY, width, height, 0.00390625f, 0.00390625f, mc);
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, float f, float f1, Minecraft mc) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD.png"));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.draw();
    }
}

