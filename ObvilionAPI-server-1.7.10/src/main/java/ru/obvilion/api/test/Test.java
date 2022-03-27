package ru.obvilion.api.test;

import ru.obvilion.api.db.DatabaseConnection;
import ru.obvilion.api.db.DatabaseType;
import ru.obvilion.api.db.constructors.UpdateOptions;
import ru.obvilion.api.db.constructors.conditions.EqualsCond;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("user", "");
        properties.setProperty("password", "");

        DatabaseConnection con = new DatabaseConnection(DatabaseType.POSTGRES, "balarama.db.elephantsql.com", 5432, "xddvirje", properties);
        con.connect();

        // Remove table if exists
        // con.dropTable(TestObject.class);

        // Create table if not exists
        con.createTable(TestObject.class);

        // Insert record
        TestObject testObject = new TestObject();
        testObject.text = "123";
        testObject.date = new Date(1123213123);

        con.addRecord(testObject);

        // Select records from table
        List<TestObject> objects = con.getElements(TestObject.class);
        System.out.println(objects);

        // Edit record from table
        TestObject record = objects.get(0);
        record.random_number = 12345;
        record.text = "This is new text 2!";

        UpdateOptions options = new UpdateOptions();
        options.setRows("random_number", "text");
        options.setCondition(new EqualsCond("id"));

        con.editRecord(record, options);

        // Select records from table
        objects = con.getElements(TestObject.class);
        System.out.println(objects);
    }
}
