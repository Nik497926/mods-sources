/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontDrawMode;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVertex;
import java.util.ArrayList;
import java.util.List;

public class WavefrontPolygon {
    private final WavefrontVertex[] vertices;

    public static Builder builder() {
        return WavefrontPolygon.builder(0);
    }

    public static Builder builder(int initialCapacity) {
        return new Builder(initialCapacity);
    }

    public WavefrontPolygon(WavefrontVertex[] vertices) {
        this.vertices = vertices;
    }

    public WavefrontDrawMode getDrawMode() {
        switch (this.getSize()) {
            case 3: {
                return WavefrontDrawMode.TRIANGLES;
            }
            case 4: {
                return WavefrontDrawMode.QUADS;
            }
        }
        return this.getSize() > 2 ? WavefrontDrawMode.TRIANGLE_FAN : WavefrontDrawMode.UNKNOWN;
    }

    public int getSize() {
        return this.vertices.length;
    }

    public WavefrontVertex getVertex(int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new IllegalArgumentException("Vertex index is out of bounds");
        }
        return this.vertices[index];
    }

    public static class Builder {
        private List<WavefrontVertex> vertices;

        private Builder(int initialCapacity) {
            if (initialCapacity > 0) {
                this.vertices = new ArrayList<WavefrontVertex>(initialCapacity);
            }
        }

        public Builder vertex(WavefrontVertex vertex) {
            if (vertex != null) {
                if (this.vertices == null) {
                    this.vertices = new ArrayList<WavefrontVertex>(3);
                }
                this.vertices.add(vertex);
            }
            return this;
        }

        public WavefrontPolygon build() {
            if (this.vertices == null || this.vertices.size() < 3) {
                throw new IllegalArgumentException("Count of vertices for polygon cannot be lower then 3");
            }
            return new WavefrontPolygon(this.vertices.toArray(new WavefrontVertex[0]));
        }
    }
}

