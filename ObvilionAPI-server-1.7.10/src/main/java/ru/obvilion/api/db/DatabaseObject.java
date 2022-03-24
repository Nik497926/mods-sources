package ru.obvilion.api.db;

import ru.obvilion.api.db.annotations.Column;

import java.lang.reflect.Field;

public class DatabaseObject implements Cloneable {
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Field getColumnField(String column) {
        Class<?> c = this.getClass();

        for (Field f : c.getDeclaredFields()) {
            Column col = f.getAnnotation(Column.class);

            if (col != null) {
                if (col.value().equals(column)) {
                    return f;
                }
            } else {
                if (f.getName().equals(column)) {
                    return f;
                }
            }
        }

        return null;
    }

    public String getColumnValue(String column) {
        Field f = getColumnField(column);

        try {
            return f == null
                ? "NULL"
                : DatabaseUtils.getValueString(f.get(this));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
