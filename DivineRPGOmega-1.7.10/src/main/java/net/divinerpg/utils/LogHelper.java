/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  org.apache.logging.log4j.Level
 */
package net.divinerpg.utils;

import cpw.mods.fml.common.FMLLog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Level;

public class LogHelper {
    private static BufferedWriter writer;

    private static void log(Level level, Object msg) {
        FMLLog.log((String)"DivineRPG", (Level)level, (String)msg.toString(), (Object[])new Object[0]);
        LogHelper.writeFile(msg);
        LogHelper.flush();
    }

    public static void debug(Object msg) {
    }

    public static void error(Object msg) {
        LogHelper.log(Level.ERROR, msg);
    }

    public static void info(Object msg) {
        LogHelper.log(Level.INFO, msg);
    }

    public static void warn(Object msg) {
        LogHelper.log(Level.WARN, msg);
    }

    public static void dev(Object msg) {
        LogHelper.log(Level.INFO, "[DEVELOPMENT] " + msg);
    }

    public static void writeFile(Object msg) {
        try {
            writer.write(msg + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void flush() {
        try {
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeFile() {
        try {
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        File dir = new File("./DivineRPG");
        dir.mkdir();
        dir = new File("./DivineRPG/debug.log");
        try {
            writer = new BufferedWriter(new FileWriter(dir));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

