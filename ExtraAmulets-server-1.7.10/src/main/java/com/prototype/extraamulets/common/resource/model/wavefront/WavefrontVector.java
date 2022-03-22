/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

public class WavefrontVector {
    private static final WavefrontVector ZERO = new WavefrontVector(0.0f, 0.0f, 0.0f);
    private final float x;
    private final float y;
    private final float z;

    public static WavefrontVector zero() {
        return ZERO;
    }

    public static WavefrontVector of(float x, float y, float z) {
        return x == 0.0f && y == 0.0f && z == 0.0f ? WavefrontVector.zero() : new WavefrontVector(x, y, z);
    }

    private WavefrontVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public WavefrontVector expand(float x, float y, float z) {
        return new WavefrontVector(this.x + x, this.y + y, this.z + z);
    }

    public WavefrontVector subtract(float x, float y, float z) {
        return new WavefrontVector(this.x - x, this.y - y, this.z - z);
    }

    public WavefrontVector scale(float x, float y, float z) {
        return new WavefrontVector(this.x * x, this.y * y, this.z * z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WavefrontVector) {
            WavefrontVector vec = (WavefrontVector)obj;
            return this.x == vec.x && this.y == vec.y && this.z == vec.z;
        }
        return false;
    }
}

