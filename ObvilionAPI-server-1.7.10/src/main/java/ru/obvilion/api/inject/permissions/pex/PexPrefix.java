package ru.obvilion.api.inject.permissions.pex;

import ru.obvilion.api.inject.permissions.api.IPrefix;

public class PexPrefix implements IPrefix {
    String prefix;

    public PexPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPriority() {
        return 0;
    }

    public long getExpiry() {
        return 0;
    }
}
