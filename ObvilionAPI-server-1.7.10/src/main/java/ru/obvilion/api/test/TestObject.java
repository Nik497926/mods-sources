package ru.obvilion.api.test;

import ru.obvilion.api.db.DatabaseObject;
import ru.obvilion.api.db.annotations.*;

import java.util.Date;

@DatabaseTable("OwO")
public class TestObject extends DatabaseObject {
    @Id
    public int id;

    @Default("(RANDOM() * 1000)")
    public long random_number;

    @Unique
    @Type("VARCHAR(80)")
    @NotNull
    public String text;

    @Column("example_name")
    public short aShort = 123; // Default value

    @Default(CURRENT_DATE = true)
    public Date date;

    @Override
    public String toString() {
        return "TestObject{" +
                "id=" + id +
                ", random_number=" + random_number +
                ", text='" + text + '\'' +
                ", aShort=" + aShort +
                ", date=" + date +
                '}';
    }
}
