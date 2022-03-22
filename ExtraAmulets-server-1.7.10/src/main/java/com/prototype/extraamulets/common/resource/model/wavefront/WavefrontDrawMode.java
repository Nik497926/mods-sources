/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

public enum WavefrontDrawMode {
    UNKNOWN(-1),
    TRIANGLES(4),
    QUADS(7),
    TRIANGLE_FAN(6);

    private final int id;

    private WavefrontDrawMode(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}

