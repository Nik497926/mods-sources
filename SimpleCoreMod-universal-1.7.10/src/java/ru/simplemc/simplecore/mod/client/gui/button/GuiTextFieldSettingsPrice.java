/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.button;

import java.math.BigDecimal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import ru.simplemc.simplecore.mod.client.gui.toast.SimpleToast;
import ru.simplemc.simplecore.mod.common.data.TradeData;

public class GuiTextFieldSettingsPrice
extends GuiTextField {
    private final String placeholder = I18n.format((String)"gui.button.trade_station.price_placeholder", (Object[])new Object[0]);
    private final TradeData data;

    public GuiTextFieldSettingsPrice(FontRenderer fontRenderer, int x, int y, TradeData data) {
        super(fontRenderer, x, y, 122, 16);
        this.data = data;
        this.setMaxStringLength(18);
        this.setText(data.getPrice().compareTo(BigDecimal.ZERO) > 0 ? data.getPrice().toPlainString() : this.placeholder);
        this.setEnableBackgroundDrawing(false);
        this.setTextColor(5870434);
        this.setEnabled(data.hasOwnerAccess((EntityPlayer)Minecraft.getMinecraft().thePlayer));
    }

    public void writeText(String value) {
        block2: {
            super.writeText(value);
            try {
                this.data.setPrice(new BigDecimal(this.getText()));
            }
            catch (Throwable t) {
                if (value.isEmpty()) break block2;
                SimpleToast.addOrUpdate(SimpleToast.Type.INFO, SimpleToast.ToastType.ERROR, (IChatComponent)new ChatComponentText("\u041e\u0448\u0438\u0431\u043a\u0430!"), (IChatComponent)new ChatComponentText("\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442 \u0446\u0435\u043d\u044b."), 5000);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean isHovered;
        super.mouseClicked(mouseX, mouseY, mouseButton);
        boolean bl = isHovered = mouseX >= this.xPosition && mouseX < this.xPosition + this.width && mouseY >= this.yPosition && mouseY < this.yPosition + this.height;
        if (isHovered && this.getText().equals(this.placeholder)) {
            this.setText("");
        } else if (this.getText().isEmpty()) {
            this.setText(this.placeholder);
        }
    }
}

