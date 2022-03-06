package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.api.*;

import java.util.UUID;

public class LPInjection implements IPermissionsInjection {
    public IUser getUser(String name) {
        return new LPUser(LuckPermsProvider.get().getUserManager().getUser(name));
    }

    public IUser getUser(UUID uuid) {
        return new LPUser(LuckPermsProvider.get().getUserManager().getUser(uuid));
    }

    public IGroup getGroup(String group) {
        Group g = LuckPermsProvider.get().getGroupManager().getGroup(group);
        return g == null ? null : new LPGroup(g);
    }
}
