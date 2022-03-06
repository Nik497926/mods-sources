package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPermission;
import ru.obvilion.api.inject.permissions.api.IUser;

public class PexPermission implements IPermission {
    public String permission;
    public Object owner = null;

    public PexPermission(String permission) {
        this.permission = permission;
    }

    public PexPermission(String permission, IUser user) {
        this.permission = permission;
        this.owner = user;
    }

    public PexPermission(String permission, IGroup group) {
        this.permission = permission;
        this.owner = group;
    }

    @Override
    public String get() {
        return permission;
    }

    @Override
    public void rename(String permission) {
        if (owner instanceof PexUser) {
            PexUser user = (PexUser) owner;
            user.removePermission(this.permission);

            this.permission = permission;
            user.addPermission(this.permission);
        }
        else if (owner instanceof PexGroup) {
            PexGroup group = (PexGroup) owner;
            group.removePermission(this.permission);

            this.permission = permission;
            group.addPermission(this.permission);
        }
    }

    @Override
    public long getExpiry() {
        return 0;
    }

    @Override
    public void setExpiry(long expiry) {

    }

    @Override
    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
