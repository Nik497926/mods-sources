/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package net.divinerpg.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.Util;

public class UsesColor {
    @SideOnly(value=Side.CLIENT)
    public static String setColor(int dur, double max) {
        double res = (double)dur / max * 100.0;
        if (res >= 0.0 && res < 15.0) {
            return Util.DARK_RED;
        }
        if (res >= 15.0 && res < 35.0) {
            return Util.RED;
        }
        if (res >= 35.0 && res < 55.0) {
            return Util.GOLD;
        }
        if (res >= 55.0 && res < 70.0) {
            return Util.YELLOW;
        }
        if (res >= 70.0) {
            return Util.GREEN;
        }
        return Util.WHITE;
    }
}

