package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.IPermissionsInjection;
import ru.obvilion.api.inject.permissions.api.*;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.UUID;

public class PexInjection implements IPermissionsInjection {
    public IUser getUser(UUID playerId) {
        return (IUser) new PexUser(playerId);
    }

    public IUser getUser(String userName) {
        return (IUser) new PexUser(userName);
    }

    public IGroup getGroup(String group) {
        boolean has = PermissionsEx.getPermissionManager().getGroup(group) == null;
        return has ? (IGroup) new PexGroup(group) : null;
    }

//        public void setGroupUntil(UUID playerId, String group, long val) { setOwnOption(playerId, "group-" + group + "-until", val+""); }
//        public void setGroupUntil(String userName, String group, long val) { setOwnOption(userName, "group-" + group + "-until", val+""); }
}
