package ru.obvilion.api.essentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kit {
    public Kit(Map<String, Object> data) {
        Object items = data.get("items");

        List<String> result = new ArrayList<>();
        if (items instanceof List) {
            for (Object item : (List)items) {
                if (item instanceof String) {
                    result.add(item.toString());
                    System.out.println(item.toString());
                }
            }
        }
    }
}
