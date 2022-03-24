package ru.obvilion.api.db;

public enum DatabaseType {
    MYSQL (
        "jdbc:mysql://{0}:{1}/{2}",
        "com.mysql.jdbc.Driver"
    ),
    POSTGRES (
        "jdbc:postgresql://{0}:{1}/{2}",
        "org.postgresql.Driver"
    ),
    DB2 (
        "jdbc:db2://{0}:{1}/{2}",
        "com.ibm.db2.jcc.DB2Driver"
    ),
    MSSQL (
        "jdbc:sqlserver://{0}:{1};databaseName={2}",
        "com.microsoft.sqlserver.jdbc.SQLServerDrive"
    );

    public final String connection_string, clazz;
    DatabaseType(String connection_string, String clazz) {
        this.connection_string = connection_string;
        this.clazz = clazz;
    }

    public String getConnectionString() {
        return connection_string;
    }

    public boolean checkDriverClass() {
        try {
            Class.forName(clazz);
            return true;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }

    public String getDriverClass() {
        return clazz;
    }
}
