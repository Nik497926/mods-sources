/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.utils;

import net.frozor.accessories.client.ui.UIScroll;
import org.apache.logging.log4j.LogManager;

public class Logger {
    public static org.apache.logging.log4j.Logger logger = LogManager.getLogger((String)UIScroll.l("j\u0004H\u0002X\u0014D\u0015B\u0002X*D\u0003"));

    public static void info(String s) {
        logger.info(s);
    }
}

