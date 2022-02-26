/*
 * Decompiled with CFR 0.152.
 */
package ru.obvilion.accessoires.utils;

import ru.obvilion.accessoires.AccessoriesMod;
import org.apache.logging.log4j.LogManager;

public class Logger {
    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger("AccessoriesMod");

    public static void info(String s) {
        logger.info("[Accessories] " + s);
    }

    public static void debug(String s) {
        if (AccessoriesMod.DEBUG) logger.info("[Accessories] " + s);
    }
}

