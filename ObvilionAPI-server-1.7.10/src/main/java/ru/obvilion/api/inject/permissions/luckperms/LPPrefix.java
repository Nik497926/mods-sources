package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.node.types.PrefixNode;
import ru.obvilion.api.inject.permissions.api.IPrefix;

public class LPPrefix implements IPrefix {
    PrefixNode node;

    public LPPrefix(PrefixNode node) {
        this.node = node;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public long getExpiry() {
        return 0;
    }
}
