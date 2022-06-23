/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.simplemc.simplecore.mod.common.data.TradeType;

public class GuiButtonPurchase
extends GuiButton {
    private final ResourceLocation buttonTexture;

    public GuiButtonPurchase(int buttonId, int x, int y, TradeType type, ResourceLocation buttonTexture) {
        super(buttonId, x, y, 140, 18, I18n.format((String)("gui.button.trade_station.purchase." + type.toString().toLowerCase()), (Object[])new Object[0]));
        this.buttonTexture = buttonTexture;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            if (!this.enabled) {
                this.displayString = I18n.format((String)"gui.button.trade_station.purchase.unavailable", (Object[])new Object[0]);
            }
            mc.getTextureManager().bindTexture(this.buttonTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int hoverState = this.getHoverState(this.field_146123_n);
            GL11.glEnable((int)3042);
            OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
            GL11.glBlendFunc((int)770, (int)771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 180 + hoverState * 18, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 180 + hoverState * 18, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int color = 0xE0E0E0;
            if (this.packedFGColour != 0) {
                color = this.packedFGColour;
            } else if (!this.enabled) {
                color = 0xA0A0A0;
            } else if (this.field_146123_n) {
                color = 0xFFFFA0;
            }
            this.drawCenteredString(mc.fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, color);
        }
    }
}

