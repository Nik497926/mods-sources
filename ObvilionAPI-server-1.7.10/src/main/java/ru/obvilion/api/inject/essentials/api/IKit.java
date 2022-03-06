package ru.obvilion.api.inject.essentials.api;

import java.util.List;

public interface IKit {
    String getName();

    List<IItem> getItems();

    long getDelay();
}
