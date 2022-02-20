/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.utils;

import org.apache.logging.log4j.LogManager;

public class Logger {
    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger("AccessoriesMod");

    public static void info(String s) {
        logger.info(s);
    }
}

