package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPermission;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;

public class PexGroup implements IGroup {
    PermissionGroup group;

    public PexGroup(PermissionGroup group) {
        this.group = group;
    }
    public PexGroup(String group) {
        this.group = PermissionsEx.getPermissionManager().getGroup(group);
    }

    @Override
    public List<IPermission> getPermissions() {
        return null;
    }

    @Override
    public IPermission getPermission(String permission) {
        return null;
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    @Override
    public boolean hasPermission(IPermission permission) {
        return false;
    }

    @Override
    public boolean addPermission(String permission) {
        return false;
    }

    @Override
    public boolean addPermission(IPermission permission) {
        return false;
    }

    @Override
    public boolean removePermission(String permission) {
        return false;
    }

    @Override
    public boolean removePermission(IPermission permission) {
        return false;
    }

    @Override
    public void save() {

    }
}
