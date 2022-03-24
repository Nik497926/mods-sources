package ru.obvilion.api.db.constructors.conditions;

import ru.obvilion.api.db.DatabaseObject;
import ru.obvilion.api.db.DatabaseUtils;

public class EqualsCond implements SqlCond {
    private final String column;
    private Object value;

    public EqualsCond(String column) {
        this.column = column;
    }

    public EqualsCond(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public String toSql(DatabaseObject obj) {
        if (obj == null) {
            if (value == null) {
                throw new IllegalStateException("Value for Equals condition not specified!");
            }

            return "(" + column + " = " + DatabaseUtils.getValueString(value) + ")";
        }

        return "(" + column + " = " + obj.getColumnValue(column) + ")";
    }
}
