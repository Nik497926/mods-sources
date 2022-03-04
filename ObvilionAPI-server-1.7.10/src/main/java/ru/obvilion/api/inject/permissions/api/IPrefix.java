package ru.obvilion.api.inject.permissions.api;

public interface IPrefix {
    String getPrefix();

    int getPriority();

    long getExpiry();
}
