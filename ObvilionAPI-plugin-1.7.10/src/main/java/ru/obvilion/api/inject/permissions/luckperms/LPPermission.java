package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.node.types.PermissionNode;
import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPermission;
import ru.obvilion.api.inject.permissions.api.IUser;

public class LPPermission implements IPermission {
    public PermissionNode node;
    public Object owner = null;

    public LPPermission(String permission) {
        this.node = PermissionNode.builder(permission).build();
    }

    public LPPermission(PermissionNode permission) {
        this.node = permission;
    }

    public LPPermission(PermissionNode permission, IUser user) {
        this.node = permission;
        this.owner = user;
    }

    public LPPermission(PermissionNode permission, IGroup group) {
        this.node = permission;
        this.owner = group;
    }

    public PermissionNode getNode() {
        return node;
    }

    @Override
    public String get() {
        return node.getPermission();
    }

    @Override
    public void rename(String permission) {
        if (owner instanceof LPUser) {
            LPUser user = (LPUser) owner;
            user.removePermission(this);
            node = PermissionNode.builder(permission).expiry(node.getExpiry()).build();
            user.addPermission(this);
        }
        else if (owner instanceof LPGroup) {
            LPGroup group = (LPGroup) owner;
            group.removePermission(this);
            node = PermissionNode.builder(permission).expiry(node.getExpiry()).build();
            group.addPermission(this);
        }
    }

    @Override
    public long getExpiry() {
        return node.getExpiry() == null ? 0 : node.getExpiry().toEpochMilli();
    }

    @Override
    public void setExpiry(long expiry) {
        if (owner instanceof LPUser) {
            LPUser user = (LPUser) owner;
            user.removePermission(this);
            node = PermissionNode.builder(node.getPermission()).expiry(expiry).build();
            user.addPermission(this);
        }
        else if (owner instanceof LPGroup) {
            LPGroup group = (LPGroup) owner;
            group.removePermission(this);
            node = PermissionNode.builder(node.getPermission()).expiry(expiry).build();
            group.addPermission(this);
        }
    }

    @Override
    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
