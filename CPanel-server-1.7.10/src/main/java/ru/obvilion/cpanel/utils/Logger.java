/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.cpanel.utils;

import ru.obvilion.cpanel.CPanelMod;
import org.apache.logging.log4j.LogManager;

public class Logger {
    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger("CPanelMod");

    public static void info(String s) {
        logger.info(s);
    }

    public static void debug(String s) {
        if (CPanelMod.DEBUG) logger.info(s);
    }
}

