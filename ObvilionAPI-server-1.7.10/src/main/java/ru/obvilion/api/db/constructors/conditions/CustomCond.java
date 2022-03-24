package ru.obvilion.api.db.constructors.conditions;

import ru.obvilion.api.db.DatabaseObject;

public class CustomCond implements SqlCond {
    private final String condition;

    public CustomCond(String condition) {
        this.condition = condition;
    }

    @Override
    public String toSql(DatabaseObject obj) {
        return "(" + condition + ")";
    }
}
