package ru.obvilion.api.inject.permissions.api;

import java.util.List;

public interface IUser {
    List<IGroup> getGroups();

    IGroup getPrimaryGroup();

    List<IPrefix> getOwnPrefixes();

    List<ISuffix> getOwnSuffixes();

    IPrefix getOwnPrefix();

    ISuffix getOwnSuffix();

    IPrefix editOwnPrefix(IPrefix from, String prefix, long expiry);

    ISuffix editOwnSuffix(ISuffix from, String prefix, long expiry);

    void removeOwnPrefix(IPrefix prefix);

    void removeOwnSuffix(ISuffix suffix);

    void save();

    boolean hasPermission(String permission);

    boolean addPermission(String permission);

    boolean addPermission(String permission, long expiry);

    void removePermission(String permission);
}
