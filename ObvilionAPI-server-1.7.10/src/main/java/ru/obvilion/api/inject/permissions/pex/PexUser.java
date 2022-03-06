package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.*;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PexUser implements IUser {
    PermissionUser user;

    public PexUser(PermissionUser user) {
        this.user = user;
    }
    public PexUser(UUID user) {
        this.user = PermissionsEx.getPermissionManager().getUser(user);
    }
    public PexUser(String user) {
        this.user = PermissionsEx.getPermissionManager().getUser(user);
    }

    public List<IGroup> getGroups() {
        return user.getParentIdentifiers().stream().map(PexGroup::new).collect(Collectors.toList());
    }

    public IGroup getPrimaryGroup() {
        List<String> all = user.getParentIdentifiers();
        return all.size() > 0 ? new PexGroup(all.get(all.size() - 1)) : null;
    }

    @Override
    public String getPrefix() {
        return user.getPrefix();
    }

    @Override
    public String getSuffix() {
        return user.getSuffix();
    }

    @Override
    public IPrefix getPrefixObj() {
        IPrefix f = this.getOwnPrefix();
        if (f != null) return f;

        for (IGroup g : getGroups()) {
            if (g.getPrefix().get().equals(getPrefix())) {
                return g.getPrefix();
            }
        }
        return null;
    }

    @Override
    public ISuffix getSuffixObj() {
        ISuffix f = this.getOwnSuffix();
        if (f != null) return f;

        for (IGroup g : getGroups()) {
            if (g.getSuffix().get().equals(getPrefix())) {
                return g.getSuffix();
            }
        }
        return null;
    }

    @Override
    public IPrefix getOwnPrefix() {
        return new PexPrefix(user.getOwnPrefix(), this);
    }

    @Override
    public ISuffix getOwnSuffix() {
        return new PexSuffix(user.getOwnPrefix(), this);
    }

    @Override
    public void setOwnPrefix(IPrefix to) {
        user.setPrefix(to.get(), null);
    }

    @Override
    public void setOwnSuffix(ISuffix to) {
        user.setSuffix(to.get(), null);
    }

    @Override
    public boolean removeOwnPrefix() {
        if (getOwnPrefix().get() == null) {
            return false;
        }

        user.setPrefix(null, null);
        return true;
    }

    @Override
    public boolean removeOwnSuffix() {
        if (getOwnSuffix().get() == null) {
            return false;
        }

        user.setSuffix(null, null);
        return true;
    }

    @Override
    public void save() {
        user.save();
    }

    @Override
    public List<IPermission> getPermissions() {
        return user.getOwnPermissions(null).stream().map(s -> new PexPermission(s, this)).collect(Collectors.toList());
    }

    @Override
    public IPermission getPermission(String permission) {
        return hasPermission(permission) ? new PexPermission(permission, this) : null;
    }

    @Override
    public boolean hasPermission(String permission) {
        return user.getOwnPermissions(null).contains(permission);
    }

    @Override
    public boolean hasPermission(IPermission permission) {
        return user.getOwnPermissions(null).contains(permission.get());
    }

    @Override
    public boolean addPermission(String permission) {
        if (hasPermission(permission)) {
            return false;
        }

        user.addPermission(permission);
        return true;
    }

    @Override
    public boolean addPermission(IPermission permission) {
        if (hasPermission(permission)) {
            return false;
        }

        user.addPermission(permission.get());
        return true;
    }

    @Override
    public boolean removePermission(String permission) {
        if (!hasPermission(permission)) {
            return false;
        }

        user.removePermission(permission);
        return true;
    }

    @Override
    public boolean removePermission(IPermission permission) {
        if (!hasPermission(permission)) {
            return false;
        }

        user.removePermission(permission.get());
        return true;
    }
}
