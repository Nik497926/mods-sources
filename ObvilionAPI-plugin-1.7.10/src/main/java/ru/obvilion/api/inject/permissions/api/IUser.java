package ru.obvilion.api.inject.permissions.api;

import java.util.List;

public interface IUser {
    List<IGroup> getGroups();

    IGroup getPrimaryGroup();

    String getPrefix();

    String getSuffix();

    IPrefix getPrefixObj();

    ISuffix getSuffixObj();

    IPrefix getOwnPrefix();

    ISuffix getOwnSuffix();

    void setOwnPrefix(IPrefix to);

    void setOwnSuffix(ISuffix to);

    boolean removeOwnPrefix();

    boolean removeOwnSuffix();

    void save();

    List<IPermission> getPermissions();

    IPermission getPermission(String permission);

    boolean hasPermission(String permission);

    boolean hasPermission(IPermission permission);

    boolean addPermission(String permission);

    boolean addPermission(IPermission permission);

    boolean removePermission(String permission);

    boolean removePermission(IPermission permission);
}
