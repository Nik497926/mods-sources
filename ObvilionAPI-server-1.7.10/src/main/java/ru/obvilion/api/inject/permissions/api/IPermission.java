package ru.obvilion.api.inject.permissions.api;

public interface IPermission {
    String get();

    void rename(String permission);

    long getExpiry();

    void setExpiry(long expiry);

    void setOwner(Object owner);
}
