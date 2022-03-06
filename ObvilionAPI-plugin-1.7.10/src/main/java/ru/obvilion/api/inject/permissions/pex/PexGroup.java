package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPermission;
import ru.obvilion.api.inject.permissions.api.IPrefix;
import ru.obvilion.api.inject.permissions.api.ISuffix;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;
import java.util.stream.Collectors;

public class PexGroup implements IGroup {
    PermissionGroup group;

    public PexGroup(PermissionGroup group) {
        this.group = group;
    }

    public PexGroup(String group) {
        this.group = PermissionsEx.getPermissionManager().getGroup(group);
    }

    @Override
    public IPrefix getPrefix() {
        return group.getPrefix() == null ? null : new PexPrefix(group.getPrefix(), this);
    }

    @Override
    public ISuffix getSuffix() {
        return group.getSuffix() == null ? null : new PexSuffix(group.getSuffix(), this);
    }

    @Override
    public void setPrefix(IPrefix prefix) {
        group.setPrefix(prefix.get(), null);
        prefix.setOwner(this);
    }

    @Override
    public void setSuffix(ISuffix suffix) {
        group.setSuffix(suffix.get(), null);
        suffix.setOwner(this);
    }

    @Override
    public boolean removePrefix() {
        if (group.getPrefix() == null) {
            return false;
        }

        group.setPrefix(null, null);
        return true;
    }

    @Override
    public boolean removeSuffix() {
        if (group.getSuffix() == null) {
            return false;
        }

        group.setSuffix(null, null);
        return true;
    }

    @Override
    public List<IPermission> getPermissions() {
        return group.getPermissions(null)
                .stream()
                .map(s -> new PexPermission(s, this))
                .collect(Collectors.toList());
    }

    @Override
    public IPermission getPermission(String permission) {
        return hasPermission(permission) ? new PexPermission(permission, this) : null;
    }

    @Override
    public boolean hasPermission(String permission) {
        return group.has(permission);
    }

    @Override
    public boolean hasPermission(IPermission permission) {
        return group.has(permission.get());
    }

    @Override
    public boolean addPermission(String permission) {
        if (group.has(permission)) {
            return false;
        }

        group.addPermission(permission);
        return true;
    }

    @Override
    public boolean addPermission(IPermission permission) {
        if (group.has(permission.get())) {
            return false;
        }

        group.addPermission(permission.get());
        return true;
    }

    @Override
    public boolean removePermission(String permission) {
        if (!hasPermission(permission)) {
            return false;
        }

        group.removePermission(permission);
        return true;
    }

    @Override
    public boolean removePermission(IPermission permission) {
        if (!hasPermission(permission)) {
            return false;
        }

        group.removePermission(permission.get());
        return true;
    }

    @Override
    public void save() {
        group.save();
    }
}
