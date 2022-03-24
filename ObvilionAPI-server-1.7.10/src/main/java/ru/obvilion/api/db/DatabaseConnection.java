package ru.obvilion.api.db;

import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import ru.obvilion.api.db.annotations.*;
import ru.obvilion.api.db.constructors.SelectOptions;
import ru.obvilion.api.db.constructors.UpdateOptions;
import ru.obvilion.api.db.constructors.conditions.*;
import ru.obvilion.api.db.test.TestObject;
import ru.obvilion.api.utils.NumberUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.util.Date;

public class DatabaseConnection {
    public final DatabaseType type;
    public final Properties properties;
    public final String host, database;
    public final int port;

    private Connection connection;

    public DatabaseConnection(DatabaseType type, String host, int port, String database, Properties properties) {
        if (!type.checkDriverClass()) {
            throw new RuntimeException("Database driver not found! Need driver class: " + type.clazz);
        }

        this.type = type;
        this.host = host;
        this.port = port;
        this.database = database;
        this.properties = properties;
    }

    public void connect() {
        String url = MessageFormat.format(type.getConnectionString(), host, port + "", database);

        try {
            connection = DriverManager.getConnection(url, this.properties);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to a " + type.name() + " database [" + url + "]");
        }
    }

    public void disconnect() {
        if (connection == null) {
            return;
        }

        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Ignored
        }

        connection = null;
    }

    /* Methods: CREATE, INSERT, UPDATE, DELETE */
    public int executeUpdate(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Methods: SELECT */
    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void createTable(Class<T> c) throws IllegalAccessException, InstantiationException {
        DatabaseTable dt = c.getAnnotation(DatabaseTable.class);
        String table = dt == null ? c.getSimpleName() : dt.value();

        Field[] fields = c.getDeclaredFields();
        T exemplar = c.newInstance();

        String query = "CREATE TABLE IF NOT EXISTS " + table + " (";

        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];

            Column column_annotation = f.getAnnotation(Column.class);
            query += (column_annotation == null ? f.getName() : column_annotation.value()) + " ";

            Id id_annotation = f.getAnnotation(Id.class);
            query += id_annotation == null ? "" : "SERIAL PRIMARY KEY";

            if (id_annotation == null) {
                Type type_annotation = f.getAnnotation(Type.class);
                query += (type_annotation == null ? DatabaseUtils.getType(f.getType()) : type_annotation.value());
            }

            Unique unique_annotation = f.getAnnotation(Unique.class);
            query += unique_annotation == null ? "" : " UNIQUE";

            NotNull notnull_annotation = f.getAnnotation(NotNull.class);
            query += notnull_annotation == null ? "" : " NOT NULL";

            /* Default argument parsing */
            Default def = f.getAnnotation(Default.class);
            String default_value = " DEFAULT ";

            if (def == null) {
                Object val = f.get(exemplar);

                if (!NumberUtils.isNullOrZero(val)) {
                    default_value += DatabaseUtils.getValueString(val);
                } else {
                    default_value = "";
                }
            } else {
                if (def.value().equals("")) {
                    if (def.CURRENT_DATE()) {
                        default_value += "CURRENT_DATE";
                    }
                    else if (!def.NEXT_VALUE().equals("")) {
                        default_value += "NEXTVAL('" + def.NEXT_VALUE() + "')";
                    } else {
                        default_value = "";
                    }
                } else {
                    default_value += def.value();
                }
            }

            if (!default_value.equals("")) {
                query += default_value;
            }

            query += i + 1 == fields.length ? ");" : ", ";
        }

        executeUpdate(query);
    }

    public <T> void dropTable(Class<T> c) {
        DatabaseTable dt = c.getAnnotation(DatabaseTable.class);
        String table = dt == null ? c.getSimpleName() : dt.value();

        executeUpdate("DROP TABLE IF EXISTS " + table + ";");
    }

    public void addRecord(DatabaseObject obj) {
        try {
            Class<?> c = obj.getClass();

            DatabaseTable dt = c.getAnnotation(DatabaseTable.class);
            String table = dt == null ? c.getSimpleName() : dt.value();

            String query = "INSERT INTO " + table + " (";

            List<String> columns = new ArrayList<>();
            List<String> values = new ArrayList<>();

            for (Field f : c.getDeclaredFields()) {
                Object res = f.get(obj);

                if (NumberUtils.isNullOrZero(res)) {
                    continue;
                }

                columns.add(DatabaseUtils.getColumnName(f));
                values.add(DatabaseUtils.getValueString(res));
            }

            query += String.join(", ", columns) + ") VALUES (" + String.join(", ", values) + ");";
            executeUpdate(query);
        } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
        }
    }

    public void editRecord(DatabaseObject obj, UpdateOptions options) {
        options.check();

        try {
            Class<?> c = obj.getClass();

            DatabaseTable dt = c.getAnnotation(DatabaseTable.class);
            String table = dt == null ? c.getSimpleName() : dt.value();

            String query = "UPDATE " + table + " SET (";

            List<String> columns = new ArrayList<>();
            List<String> values = new ArrayList<>();

            for (Field f : c.getDeclaredFields()) {
                Object res = f.get(obj);

                if (NumberUtils.isNullOrZero(res)) {
                    continue;
                }

                String column = DatabaseUtils.getColumnName(f);

                if (options.getRows().contains(column)) {
                    columns.add(column);
                    values.add(DatabaseUtils.getValueString(res));
                }
            }

            query += String.join(", ", columns) + ") = (" + String.join(", ", values) + ") WHERE " + options.getCondition().toSql(obj) + ";";
            executeUpdate(query);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getElements(Class<T> c, SelectOptions options) {
        DatabaseTable dt = c.getAnnotation(DatabaseTable.class);
        String table = dt == null ? c.getSimpleName() : dt.value();

        String query = "SELECT " + options.getRows() + " FROM " + table + " " + options.getQueryAddition() + ";";
        ResultSet rs = executeQuery(query);

        List<T> result = new ArrayList<>();

        try {
            while (rs.next()) {
                Field[] fields = c.getDeclaredFields();
                T obj = c.newInstance();

                for (Field f : fields) {
                    Column column_annotation = f.getAnnotation(Column.class);
                    String column = column_annotation == null ? f.getName() : column_annotation.value();

                    DatabaseUtils.setValue(f, obj, rs, column);
                }

                result.add(obj);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    public <T> T getElement(Class<T> c, SelectOptions options) {
        return getElements(c, options).get(0);
    }

    public <T> List<T> getElements(Class<T> c) {
        return getElements(c, new SelectOptions());
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Properties properties = new Properties();
        properties.setProperty("user", "");
        properties.setProperty("password", "");

        DatabaseConnection con = new DatabaseConnection(DatabaseType.POSTGRES, "balarama.db.elephantsql.com", 5432, "xddvirje", properties);
        con.connect();

        // Remove table if exists
        // con.dropTable(TestObject.class);

        // Create table if not exists
        con.createTable(TestObject.class);

        // Insert record
        TestObject testObject = new TestObject();
        testObject.text = "123";
        testObject.date = new Date(1123213123);

        con.addRecord(testObject);

        // Select records from table
        List<TestObject> objects = con.getElements(TestObject.class);
        System.out.println(objects);

        // Edit record from table
        TestObject record = objects.get(0);
        record.random_number = 12345;
        record.text = "This is new text 2!";

        UpdateOptions options = new UpdateOptions();
        options.setRows("random_number", "text");
        options.setCondition(new EqualsCond("id"));

        con.editRecord(record, options);

        // Select records from table
        objects = con.getElements(TestObject.class);
        System.out.println(objects);
    }
}
