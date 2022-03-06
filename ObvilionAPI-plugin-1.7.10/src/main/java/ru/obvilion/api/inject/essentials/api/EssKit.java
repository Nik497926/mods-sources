package ru.obvilion.api.inject.essentials.api;

import ru.obvilion.api.inject.essentials.EssentialsInjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EssKit implements IKit {
    public String name;
    public Map<String, Object> data;

    public EssKit(String name) {
        this.name = name;
        this.data = EssentialsInjection.getEssentials().getSettings().getKit(name);
    }

    public EssKit(String name, Map<String, Object> data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IItem> getItems() {
        Object items = data.get("items");

        if (items instanceof List) {
            return ((List<?>)items).stream().map(o -> new EssItem((String) o)).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public long getDelay() {
        if (data.containsKey("delay")) {
            return ((Number) data.get("delay")).longValue();
        }

        return 0;
    }
}
