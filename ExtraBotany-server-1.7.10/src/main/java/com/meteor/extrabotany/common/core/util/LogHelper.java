/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHelper {
    private static final Logger logger = LogManager.getLogger("ArsMagica2");

    public static void log(Level level, String format, Object ... data) {
        logger.log(level, String.format(format, data));
    }

    public static void log(Level level, Throwable ex, String format, Object ... data) {
        logger.log(level, String.format(format, data), ex);
    }

    public static void error(String format, Object ... data) {
        LogHelper.log(Level.ERROR, format, data);
    }

    public static void warn(String format, Object ... data) {
        LogHelper.log(Level.WARN, format, data);
    }

    public static void info(String format, Object ... data) {
        LogHelper.log(Level.INFO, format, data);
    }

    public static void trace(String format, Object ... data) {
        LogHelper.log(Level.TRACE, format, data);
    }
}

