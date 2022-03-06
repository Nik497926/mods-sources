package ru.obvilion.api.inject.essentials.api;

import java.util.List;
import java.util.Map;

public class EssKit implements IKit {
    public String name;
    public Map<String, Object> data;

    public EssKit(String name) {
        this.name = name;
        this.data =
    }

    public EssKit(String name, Map<String, Object> data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<IItem> getItems() {
        return null;
    }

    @Override
    public long getDelay() {
        return 0;
    }
}
