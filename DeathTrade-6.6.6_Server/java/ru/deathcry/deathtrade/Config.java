/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class Config {
    private static final String DB_CATEGORY = "MySQL";
    public static String mysqlURL;
    public static String mysqlPort;
    public static String mysqlDb;
    public static String mysqlUser;
    public static String mysqlPassword;
    public static String tableName;
    public static String nameColumn;
    public static String balanceColumn;
    private static File configFile;
    private static Configuration config;

    public static void initConfigFile(File configFile) {
        System.out.println("CONFIG LOADING!!!");
        Config.configFile = configFile;
        config = new Configuration(configFile);
        Config.loadConfig();
    }

    public static void saveConfig() {
        config.save();
    }

    public static void loadConfig() {
        try {
            config.load();
            mysqlURL = config.get(DB_CATEGORY, "host", "localhost").getString();
            mysqlPort = config.get(DB_CATEGORY, "port", "3306").getString();
            mysqlDb = config.get(DB_CATEGORY, "db", "test").getString();
            mysqlUser = config.get(DB_CATEGORY, "user", "root").getString();
            mysqlPassword = config.get(DB_CATEGORY, "password", "").getString();
            tableName = config.get(DB_CATEGORY, "table_name", "iConomy").getString();
            nameColumn = config.get(DB_CATEGORY, "name_column", "username").getString();
            balanceColumn = config.get(DB_CATEGORY, "balance_column", "balance").getString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (config.hasChanged()) {
                Config.saveConfig();
            }
        }
    }
}

