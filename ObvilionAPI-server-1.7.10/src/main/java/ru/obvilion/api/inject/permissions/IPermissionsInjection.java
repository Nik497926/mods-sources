package ru.obvilion.api.inject.permissions;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IUser;

import java.util.UUID;

public interface IPermissionsInjection {
    IUser getUser(UUID uuid);

    IUser getUser(String name);

    IGroup getGroup(String name);
}
