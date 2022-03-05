package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.*;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
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
        return null;
    }

    @Override
    public String getSuffix() {
        return null;
    }

    public List<IPrefix> getOwnPrefixes() {
        List<IPrefix> all = new ArrayList<>();
        all.add(new PexPrefix(user.getOwnPrefix()));

        return all;
    }

    @Override
    public IPrefix getOwnPrefix() {
        return null;
    }

    @Override
    public ISuffix getOwnSuffix() {
        return null;
    }

    @Override
    public void setOwnPrefix(IPrefix to) {

    }

    @Override
    public void setOwnSuffix(ISuffix to) {

    }

    @Override
    public boolean removeOwnPrefix() {
        return false;
    }

    @Override
    public boolean removeOwnSuffix() {
        return false;
    }

    @Override
    public void save() {

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
}
