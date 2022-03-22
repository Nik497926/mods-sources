/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.amulet;

import com.prototype.extraamulets.common.amulet.Amulet;
import java.util.Map;

public class Amulets {
    private final Map<String, Amulet> amulets;

    public Amulets(Map<String, Amulet> amulets) {
        this.amulets = amulets;
    }

    public void register() {
        this.amulets.forEach((name, amulet) -> amulet.registerItem((String)name));
    }

    public Map<String, Amulet> getAmulets() {
        return this.amulets;
    }
}

