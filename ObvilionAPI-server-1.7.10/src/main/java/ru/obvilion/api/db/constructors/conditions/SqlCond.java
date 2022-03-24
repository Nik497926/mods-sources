package ru.obvilion.api.db.constructors.conditions;

import ru.obvilion.api.db.DatabaseObject;

public interface SqlCond {
    String toSql(DatabaseObject obj);
}
