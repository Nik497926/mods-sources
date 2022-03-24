package ru.obvilion.api.db.constructors.conditions;

import ru.obvilion.api.db.DatabaseObject;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OrCond implements SqlCond {
    private final SqlCond[] conditions;

    public OrCond(SqlCond... conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toSql(DatabaseObject obj) {
        return "(" +
            Arrays.stream(conditions)
                .map(cond -> cond.toSql(obj))
                .collect(Collectors.joining(" OR ")) +
            ")";
    }
}
