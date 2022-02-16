/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.utils;

import net.divinerpg.utils.Util;
import net.minecraft.util.StatCollector;

public class MessageLocalizer {
    public static String normal(String message) {
        return StatCollector.translateToLocal((String)message);
    }

    public static String red(String message) {
        return Util.RED + StatCollector.translateToLocal((String)message);
    }

    public static String green(String message) {
        return Util.GREEN + StatCollector.translateToLocal((String)message);
    }
}

