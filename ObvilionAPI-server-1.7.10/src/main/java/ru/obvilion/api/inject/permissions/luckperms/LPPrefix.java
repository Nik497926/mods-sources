package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.node.types.PrefixNode;
import ru.obvilion.api.inject.permissions.api.IPrefix;

public class LPPrefix implements IPrefix {
    public PrefixNode node;
    public Object owner = null;

    public LPPrefix(PrefixNode node) {
        this.node = node;
    }

    public LPPrefix(PrefixNode node, LPUser user) {
        this.node = node;
        this.owner = user;
    }

    public LPPrefix(PrefixNode node, LPGroup group) {
        this.node = node;
        this.owner = group;
    }

    private void update() {
        if (owner instanceof LPUser) {
            LPUser user = (LPUser) owner;
            user.setOwnPrefix(this);
        }
        else if (owner instanceof LPGroup) {
            LPGroup group = (LPGroup) owner;
            group.setPrefix(this);
        }
    }

    @Override
    public String get() {
        return node.getKey();
    }

    @Override
    public void rename(String prefix) {
        node = PrefixNode.builder(prefix, node.getPriority()).expiry(node.getExpiry()).build();
        update();
    }

    @Override
    public int getPriority() {
        return node.getPriority();
    }

    @Override
    public void setPriority(int priority) {
        node = PrefixNode.builder(node.getKey(), priority).expiry(node.getExpiry()).build();
        update();
    }

    @Override
    public long getExpiry() {
        return node.getExpiry() == null ? 0 : node.getExpiry().toEpochMilli();
    }

    @Override
    public void setExpiry(long expiry) {
        if (expiry > 0) {
            node = PrefixNode.builder(node.getKey(), node.getPriority()).build();
        } else {
            node = PrefixNode.builder(node.getKey(), node.getPriority()).expiry(expiry).build();
        }

        update();
    }

    @Override
    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
