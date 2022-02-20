/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.utils;

import net.frozor.accessories.AccessoriesCore;
import org.apache.logging.log4j.LogManager;

public class Logger {
    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger("AccessoriesMod");

    public static void info(String s) {
        logger.info(s);
    }

    public static void debug(String s) {
        if (AccessoriesCore.DEBUG) logger.info(s);
    }
}

