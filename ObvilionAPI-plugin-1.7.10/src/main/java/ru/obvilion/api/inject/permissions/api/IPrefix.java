package ru.obvilion.api.inject.permissions.api;

public interface IPrefix {
    String get();

    void rename(String prefix);

    int getPriority();

    void setPriority(int priority);

    long getExpiry();

    void setExpiry(long expiry);

    void setOwner(Object owner);
}
