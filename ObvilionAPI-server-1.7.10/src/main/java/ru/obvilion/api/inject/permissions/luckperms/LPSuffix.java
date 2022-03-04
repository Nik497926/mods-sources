package ru.obvilion.api.inject.permissions.luckperms;

import net.luckperms.api.node.types.SuffixNode;
import ru.obvilion.api.inject.permissions.api.ISuffix;

public class LPSuffix implements ISuffix {
    SuffixNode node;

    public LPSuffix(SuffixNode node) {
        this.node = node;
    }

    @Override
    public String getSuffix() {
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
