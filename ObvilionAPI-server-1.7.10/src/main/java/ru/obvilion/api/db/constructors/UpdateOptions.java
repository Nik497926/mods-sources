package ru.obvilion.api.db.constructors;

import ru.obvilion.api.db.constructors.conditions.SqlCond;

import java.util.Arrays;
import java.util.List;

public class UpdateOptions {
    private List<String> rows;
    private SqlCond condition;

    public void setRows(String... rows) {
        if (rows.length == 0) {
            return;
        }

        this.rows = Arrays.asList(rows);
    }

    public List<String> getRows() {
        return rows;
    }

    public void setCondition(SqlCond condition) {
        this.condition = condition;
    }

    public SqlCond getCondition() {
        return condition;
    }

    public void check() {
        if (rows == null) {
            throw new IllegalStateException("Rows is not specified!");
        }

        if (condition == null) {
            throw new IllegalStateException("Condition is not specified!");
        }
    }
}
