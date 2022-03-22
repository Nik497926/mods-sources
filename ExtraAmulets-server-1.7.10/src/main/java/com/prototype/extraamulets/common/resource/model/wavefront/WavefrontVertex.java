/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVector;

public class WavefrontVertex {
    private final WavefrontVector pos;
    private final WavefrontVector normal;
    private final WavefrontVector texture;

    public static Builder builder() {
        return new Builder();
    }

    public WavefrontVertex(WavefrontVector pos, WavefrontVector normal, WavefrontVector texture) {
        this.pos = pos;
        this.normal = normal;
        this.texture = texture;
    }

    public WavefrontVector getPos() {
        return this.pos;
    }

    public WavefrontVector getNormal() {
        return this.normal;
    }

    public WavefrontVector getNormalOrDefault(WavefrontVector def) {
        return this.hasNormal() ? this.getNormal() : def;
    }

    public boolean hasNormal() {
        return this.normal != null;
    }

    public WavefrontVector getTexture() {
        return this.texture;
    }

    public WavefrontVector getTextureOrDefault(WavefrontVector def) {
        return this.hasTexture() ? this.getTexture() : def;
    }

    public boolean hasTexture() {
        return this.texture != null;
    }

    public static class Builder {
        private WavefrontVector pos = WavefrontVector.zero();
        private WavefrontVector normal = null;
        private WavefrontVector texture = null;

        private Builder() {
        }

        public Builder pos(float x, float y, float z) {
            return this.pos(WavefrontVector.of(x, y, z));
        }

        public Builder pos(WavefrontVector vector) {
            if (vector != null) {
                this.pos = vector;
            }
            return this;
        }

        public Builder normal(float x, float y, float z) {
            return this.normal(WavefrontVector.of(x, y, z));
        }

        public Builder normal(WavefrontVector vector) {
            this.normal = vector;
            return this;
        }

        public Builder texture(float u, float v, float w) {
            return this.texture(WavefrontVector.of(u, v, w));
        }

        public Builder texture(WavefrontVector vector) {
            this.texture = vector;
            return this;
        }

        public WavefrontVertex build() {
            return new WavefrontVertex(this.pos, this.normal, this.texture);
        }
    }
}

