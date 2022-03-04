package ru.obvilion.api.inject.permissions.api;

public interface ISuffix {
    String getSuffix();

    int getPriority();

    long getExpiry();
}
