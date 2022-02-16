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
    protected static final String STAND = "message.standard";
    protected static final String VERS = "message.version";

    public static String normal(String message) {
        return Util.WHITE + StatCollector.translateToLocal((String)message);
    }

    public static String normal(String message, String color) {
        return color + StatCollector.translateToLocal((String)message);
    }

    public static String standard(String player) {
        return Util.AQUA + StatCollector.translateToLocal((String)STAND).replace("#", player).replace("&", Util.GREEN + "DivineRPG");
    }

    public static String version(String vers) {
        return Util.RED + StatCollector.translateToLocal((String)VERS).replace("#", vers);
    }
}

