package ru.obvilion.api.db;

import ru.obvilion.api.db.annotations.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseUtils {
    public static void setValue(Field field, Object object, ResultSet rs, String column) {
        try {
            Class<?> c = field.getType();
            if (c == short.class) {
                field.set(object, rs.getShort(column));
                return;
            }

            if (c == long.class) {
                field.set(object, rs.getLong(column));
                return;
            }

            if (c == float.class) {
                field.set(object, rs.getFloat(column));
                return;
            }

            if (c == double.class) {
                field.set(object, rs.getDouble(column));
                return;
            }

            field.set(object, rs.getObject(column));
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getValueString(Object object) {
        if (object == null) {
            return "NULL";
        }

        if (object instanceof String) {
            return "'" + object + "'";
        }

        if (object instanceof Date) {
            return "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) object) + "'";
        }

        return object.toString();
    }

    public static String getColumnName(Field field) {
        Column column_annotation = field.getAnnotation(Column.class);
        return column_annotation == null ? field.getName() : column_annotation.value();
    }

    public static String getType(Class<?> c) {
        if (c == String.class) {
            return "TEXT";
        }
        else if (c == short.class) {
            return "SMALLINT";
        }
        else if (c == int.class) {
            return "INTEGER";
        }
        else if (c == long.class) {
            return "BIGINT";
        }
        else if (c == boolean.class) {
            return "BOOLEAN";
        }
        else if (c == Date.class) {
            return "TIMESTAMP";
        }

        return "TEXT";
    }
}
