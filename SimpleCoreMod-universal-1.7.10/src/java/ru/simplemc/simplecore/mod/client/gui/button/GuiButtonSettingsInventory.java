/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.client.gui.button;

import net.minecraft.client.resources.I18n;
import ru.simplemc.simplecore.mod.client.gui.button.GuiButtonSettings;

public class GuiButtonSettingsInventory
extends GuiButtonSettings {
    public GuiButtonSettingsInventory(int buttonId, int x, int y) {
        super(buttonId, x, y, 162, I18n.format((String)"gui.button.trade_station.open_inventory", (Object[])new Object[0]));
    }
}

