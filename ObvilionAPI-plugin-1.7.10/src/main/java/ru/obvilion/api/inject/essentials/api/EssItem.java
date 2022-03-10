package ru.obvilion.api.inject.essentials.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EssItem implements IItem {
    public String line;

    public String item_id;
    public int count;
    public Map<String, String> meta = new HashMap<>();

    public EssItem(String item) {
        this.line = item;

        String[] args = item.split(" ");

        for (int i = 0; i < args.length; i++) {
            String s = args[i];

            if (i == 0) {
                item_id = s;
            } else if (i == 1) {
                count = Integer.parseInt(s);
            } else {
                List<String> ss = Arrays.asList(s.split(":"));

                meta.put(ss.remove(0), String.join(":", ss.toArray(new String[0])));
            }
        }
    }

    @Override
    public Map<String, String> getMeta() {
        return meta;
    }

    @Override
    public String get() {
        return item_id;
    }

    @Override
    public int getCount() {
        return count;
    }
}
