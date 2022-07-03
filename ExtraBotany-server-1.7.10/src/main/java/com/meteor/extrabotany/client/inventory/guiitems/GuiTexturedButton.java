/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.inventory.guiitems;

import mods.flammpfeil.slashblade.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiTexturedButton
extends GuiButton {
    protected static final ResourceLocation buttonTextures = new ResourceLocation("botania::textures/gui/lexicon.png");
    private boolean next = false;

    public GuiTexturedButton(int id, int x, int y, int width, int height, String name, boolean next) {
        super(id, x, y, width, height, name);
        this.width = 10;
        this.height = 10;
        this.xPosition = x;
        this.yPosition = y;
        this.next = next;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(hovered);
            GlStateManager.enableBlend();
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, this.next ? 180 : 190, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 0xE0E0E0;
            if (this.packedFGColour != 0) {
                j = this.packedFGColour;
            } else if (!this.enabled) {
                j = 0xA0A0A0;
            } else if (hovered) {
                j = 0xFFFFA0;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
        }
    }
}

