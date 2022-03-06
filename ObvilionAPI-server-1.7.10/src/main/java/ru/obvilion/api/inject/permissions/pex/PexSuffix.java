package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.ISuffix;
import ru.obvilion.api.inject.permissions.api.IUser;

public class PexSuffix implements ISuffix {
    public String suffix;
    public Object owner = null;

    public PexSuffix(String suffix) {
        this.suffix = suffix;
    }

    public PexSuffix(String suffix, IUser user) {
        this.suffix = suffix;
        this.owner = user;
    }

    public PexSuffix(String suffix, IGroup group) {
        this.suffix = suffix;
        this.owner = group;
    }

    private void update() {
        if (owner instanceof PexUser) {
            PexUser user = (PexUser) owner;
            user.setOwnSuffix(this);
        }
        else if (owner instanceof PexGroup) {
            PexGroup group = (PexGroup) owner;
            group.setSuffix(this);
        }
    }

    @Override
    public String get() {
        return suffix;
    }

    @Override
    public void rename(String suffix) {
        this.suffix = suffix;
        update();
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public void setPriority(int priority) {

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
