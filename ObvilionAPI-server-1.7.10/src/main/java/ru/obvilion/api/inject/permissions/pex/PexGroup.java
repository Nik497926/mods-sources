package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PexGroup implements IGroup {
    PermissionGroup group;

    public PexGroup(PermissionGroup group) {
        this.group = group;
    }
    public PexGroup(String group) {
        this.group = PermissionsEx.getPermissionManager().getGroup(group);
    }
}
