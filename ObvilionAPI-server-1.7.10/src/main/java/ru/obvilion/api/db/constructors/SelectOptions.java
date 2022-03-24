package ru.obvilion.api.db.constructors;

import ru.obvilion.api.db.constructors.conditions.SqlCond;

public class SelectOptions {
    private String rows = "*";

    private SqlCond condition;
    private String orderBy;
    private int limit, offset;

    public void setRows(String... rows) {
        if (rows.length == 0) {
            this.rows = "*";
            return;
        }

        this.rows = String.join(", ", rows);
    }

    public String getRows() {
        return rows;
    }

    /* Optional params */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setCondition(SqlCond condtion) {
        this.condition = condition;
    }

    public void setOrder(String orderBy) {
        this.orderBy = orderBy;
    }

    /* Get's addition string to query */
    public String getQueryAddition() {
        String result = "";

        if (condition != null) {
            result += "WHERE " + condition.toSql(null);
        }

        if (offset != 0) {
            result += (result.length() > 0 ? " " : "") + "OFFSET " + offset;
        }

        if (limit != 0) {
            result += (result.length() > 0 ? " " : "") + "LIMIT " + limit;
        }

        if (orderBy != null) {
            result += (result.length() > 0 ? " " : "") + "ORDER BY " + orderBy;
        }

        return result;
    }
}
