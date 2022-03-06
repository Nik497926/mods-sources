package ru.obvilion.api.inject.essentials.api;

import java.util.Map;

public interface IItem {
    Map<String, String> getMeta();

    String get();

    int getCount();
}
