/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentTranslation;

public enum TradeType {
    BUY,
    SELL;


    @SideOnly(value=Side.CLIENT)
    public String getTranslated() {
        return I18n.format((String)this.getTranslationKey(), (Object[])new Object[0]);
    }

    public ChatComponentTranslation getTranslationComponent() {
        return new ChatComponentTranslation(this.getTranslationKey(), new Object[0]);
    }

    private String getTranslationKey() {
        return "enum.trade_station." + this.toString().toLowerCase();
    }
}

