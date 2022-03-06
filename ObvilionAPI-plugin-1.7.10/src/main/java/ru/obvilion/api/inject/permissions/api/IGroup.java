package ru.obvilion.api.inject.permissions.api;

import java.util.List;

public interface IGroup {
    IPrefix getPrefix();

    ISuffix getSuffix();

    void setPrefix(IPrefix prefix);

    void setSuffix(ISuffix suffix);

    boolean removePrefix();

    boolean removeSuffix();

    List<IPermission> getPermissions();

    IPermission getPermission(String permission);

    boolean hasPermission(String permission);

    boolean hasPermission(IPermission permission);

    boolean addPermission(String permission);

    boolean addPermission(IPermission permission);

    boolean removePermission(String permission);

    boolean removePermission(IPermission permission);

    void save();
}
