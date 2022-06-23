/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.data.TradeType;

public class GuiButtonSettingsPurchaseMode
extends GuiButton {
    private final TradeData data;

    public GuiButtonSettingsPurchaseMode(int buttonId, int x, int y, TradeData data) {
        super(buttonId, x, y, 162, 20, GuiButtonSettingsPurchaseMode.getButtonText(data.getType()));
        this.data = data;
        this.enabled = data.hasOwnerAccess((EntityPlayer)Minecraft.getMinecraft().thePlayer);
    }

    private static String getButtonText(TradeType type) {
        return I18n.format((String)"gui.button.trade_station.mode", (Object[])new Object[0]) + " \u00a7e" + type.getTranslated();
    }

    public void actionPerformed() {
        this.data.setType(this.data.getType().equals((Object)TradeType.BUY) ? TradeType.SELL : TradeType.BUY);
        this.displayString = GuiButtonSettingsPurchaseMode.getButtonText(this.data.getType());
    }

    public TradeData getData() {
        return this.data;
    }
}

