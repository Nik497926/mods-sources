package ru.obvilion.api.inject.permissions.api;

public interface IPermission {
    String getPermission();

    void editPermission(String permission);

    long getExpiry();

    void setExpiry(long expiry);

    void setOwner(Object owner);
}
