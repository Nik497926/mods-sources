/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.RenderHelper;

public class CustomButton
extends GuiButton {
    private boolean active;
    private int type;
    protected static final ResourceLocation buttonTextures = new ResourceLocation("extrabotania", "textures/gui/HUD2.png");

    public CustomButton(int id, int x, int y, int width, int height, String name, boolean active, int type) {
        super(id, x, y, width, height, name);
        this.width = width;
        this.height = height;
        this.xPosition = x;
        this.yPosition = y;
        this.active = active;
        this.type = type;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            mc.renderEngine.bindTexture(buttonTextures);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.type == 0 ? (this.active ? 16 : 0) : (this.type == 1 ? 32 : (this.type == 2 ? 48 : 65)), k == 2 ? 187 : 203, 16, 16);
            List<String> tooltip = this.getTooltip();
            int tooltipY = (tooltip.size() - 1) * 10;
            if (k == 2) {
                RenderHelper.renderTooltip((int)mouseX, (int)(mouseY + tooltipY), tooltip);
            }
        }
    }

    public void setActive(boolean newActive) {
        this.active = newActive;
    }

    public boolean getActive() {
        return this.active;
    }

    public List<String> getTooltip() {
        return Arrays.asList(this.displayString);
    }
}

