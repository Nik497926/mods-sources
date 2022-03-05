package ru.obvilion.api.inject.permissions.api;

public interface ISuffix {
    String get();

    void rename(String suffix);

    int getPriority();

    void setPriority(int priority);

    long getExpiry();

    void setExpiry(long expiry);

    void setOwner(Object owner);
}
