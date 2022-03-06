package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IGroup;
import ru.obvilion.api.inject.permissions.api.IPrefix;
import ru.obvilion.api.inject.permissions.api.IUser;

public class PexPrefix implements IPrefix {
    public String prefix;
    public Object owner = null;

    public PexPrefix(String prefix) {
        this.prefix = prefix;
    }

    public PexPrefix(String prefix, IUser user) {
        this.prefix = prefix;
        this.owner = user;
    }

    public PexPrefix(String prefix, IGroup group) {
        this.prefix = prefix;
        this.owner = group;
    }

    private void update() {
        if (owner instanceof PexUser) {
            PexUser user = (PexUser) owner;
            user.setOwnPrefix(this);
        }
        else if (owner instanceof PexGroup) {
            PexGroup group = (PexGroup) owner;
            group.setPrefix(this);
        }
    }

    public String get() {
        return prefix;
    }

    @Override
    public void rename(String prefix) {
        this.prefix = prefix;
        update();
    }

    public int getPriority() {
        return -1;
    }

    @Override
    public void setPriority(int priority) {

    }

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
