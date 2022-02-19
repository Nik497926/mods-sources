/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ru.deathcry.deathtrade.Config;

public class MySQL {
    private static MySQL instance;
    private static final String pathToDriver = "com.mysql.jdbc.Driver";
    private static final String ADDRESS;
    private static final String USER;
    private static final String PASSWORD;
    protected Connection con;
    protected Statement st;
    public static boolean broken;

    private MySQL() {
        try {
            Class.forName(pathToDriver);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            broken = true;
        }
        try {
            this.con = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
            this.st = this.con.createStatement();
        }
        catch (SQLException ex) {
            System.out.println("MySQL ERROR: cannot connect to database");
            broken = true;
            ex.printStackTrace();
        }
    }

    public synchronized void query(String query) {
        try {
            this.checkAndReconnect();
            this.st.execute(query);
        }
        catch (SQLException ex) {
            System.err.println("MySQL ERROR: can't execute query");
            ex.printStackTrace();
        }
    }

    public synchronized ResultSet queryWithResult(String query) {
        ResultSet rs;
        try {
            this.checkAndReconnect();
            rs = this.st.executeQuery(query);
        }
        catch (SQLException ex) {
            System.err.println("MySQL ERROR: can't execute query with result");
            ex.printStackTrace();
            return null;
        }
        return rs;
    }

    public synchronized void update(String query) {
        try {
            this.checkAndReconnect();
            this.st.executeUpdate(query);
        }
        catch (SQLException ex) {
            System.err.println("MySQL ERROR: can't execute update query");
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            this.con.close();
            this.st.close();
        }
        catch (Exception ex) {
            System.err.println("MySQL ERROR: error while closing connection");
            ex.printStackTrace();
        }
    }

    private void checkAndReconnect() {
        try {
            this.con.isValid(200);
            if (this.con == null || this.con.isClosed()) {
                this.con = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
                this.st = this.con.createStatement();
            } else if (this.st == null || this.st.isClosed()) {
                this.st = this.con.createStatement();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void transferBalance(String from, String to, double balance) {
        String query = String.format("UPDATE %s SET %s = ROUND(%s - %f, 2) WHERE %s = '%s'", Config.tableName, Config.balanceColumn, Config.balanceColumn, balance, Config.nameColumn, from);
        String query2 = String.format("UPDATE %s SET %s = ROUND(%s + %f, 2) WHERE %s = '%s'", Config.tableName, Config.balanceColumn, Config.balanceColumn, balance, Config.nameColumn, to);
        MySQL.getInstance().update(query);
        MySQL.getInstance().update(query2);
    }

    public static double getPlayerBalance(String username) {
        if (broken) {
            return 0.0;
        }
        String query = String.format("SELECT * FROM %s WHERE %s='%s'", Config.tableName, Config.nameColumn, username);
        ResultSet x = MySQL.getInstance().queryWithResult(query);
        try {
            if (x.next()) {
                return x.getDouble(Config.balanceColumn);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static MySQL getInstance() {
        if (instance == null) {
            instance = new MySQL();
        }
        return instance;
    }

    static {
        ADDRESS = String.format("jdbc:mysql://%s:%s/%s", Config.mysqlURL, Config.mysqlPort, Config.mysqlDb);
        USER = Config.mysqlUser;
        PASSWORD = Config.mysqlPassword;
        broken = false;
    }
}

