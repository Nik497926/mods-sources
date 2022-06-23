/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.util;

import java.util.Arrays;
import java.util.List;
import net.minecraft.util.StatCollector;

public class TranslateUtils {
    public static List<String> translateForItemStack(String key) {
        return Arrays.asList(StatCollector.translateToLocal((String)key).split("<br>"));
    }

    public static List<String> translateForItemStackFormatted(String key, Object ... objects) {
        return Arrays.asList(StatCollector.translateToLocalFormatted((String)key, (Object[])objects).split("<br>"));
    }

    public static String translateValueOf(double value, String name) {
        if (value >= 1.0E9) {
            name = "\u041c\u041b\u0420\u0414 " + name;
            value /= 1.0E9;
        } else if (value >= 1000000.0) {
            name = "\u041c\u041b\u041d " + name;
            value /= 1000000.0;
        }
        return String.format("%s %s", String.format("%.2f", value), name);
    }

    public static String translateTicksToHumanTime(int ticks) {
        int seconds = ticks / 20;
        int hours = seconds / 3600;
        return String.format("%d \u0447, %02d \u043c\u0438\u043d, %02d \u0441\u0435\u043a.", hours, seconds % 3600 / 60, seconds % 60);
    }
}

