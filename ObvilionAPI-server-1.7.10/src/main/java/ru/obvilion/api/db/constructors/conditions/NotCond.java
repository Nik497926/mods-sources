package ru.obvilion.api.db.constructors.conditions;

import ru.obvilion.api.db.DatabaseObject;

public class NotCond implements SqlCond {
    private final SqlCond condition;

    public NotCond(SqlCond condition) {
        this.condition = condition;
    }

    @Override
    public String toSql(DatabaseObject obj) {
        return "( NOT" + condition.toSql(obj) + ")";
    }
}
