package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.node.types.SuffixNode;
import ru.obvilion.api.inject.permissions.api.ISuffix;

public class LPSuffix implements ISuffix {
    public SuffixNode node;
    public Object owner = null;

    public LPSuffix(SuffixNode node) {
        this.node = node;
    }

    public LPSuffix(SuffixNode node, LPUser user) {
        this.node = node;
        this.owner = user;
    }

    public LPSuffix(SuffixNode node, LPGroup group) {
        this.node = node;
        this.owner = group;
    }

    private void update() {
        if (owner instanceof LPUser) {
            LPUser user = (LPUser) owner;
            user.setOwnSuffix(this);
        }
        else if (owner instanceof LPGroup) {
            LPGroup group = (LPGroup) owner;
            group.setSuffix(this);
        }
    }

    @Override
    public String get() {
        return node.getKey();
    }

    @Override
    public void rename(String suffix) {
        node = SuffixNode.builder(suffix, node.getPriority())
                .expiry(node.getExpiry())
                .build();
        update();
    }

    @Override
    public int getPriority() {
        return node.getPriority();
    }

    @Override
    public void setPriority(int priority) {
        node = SuffixNode.builder(node.getKey(), priority)
                .expiry(node.getExpiry())
                .build();
        update();
    }

    @Override
    public long getExpiry() {
        return node.getExpiry() == null ? 0 : node.getExpiry().toEpochMilli();
    }

    @Override
    public void setExpiry(long expiry) {
        if (expiry > 0) {
            node = SuffixNode.builder(node.getKey(), node.getPriority()).build();
        } else {
            node = SuffixNode.builder(node.getKey(), node.getPriority())
                    .expiry(expiry)
                    .build();
        }

        update();
    }

    @Override
    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
