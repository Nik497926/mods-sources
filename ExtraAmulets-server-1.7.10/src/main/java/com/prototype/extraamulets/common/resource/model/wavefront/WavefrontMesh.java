/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontPolygon;
import java.util.ArrayList;
import java.util.List;

public class WavefrontMesh {
    private final String title;
    private final WavefrontPolygon[] polygons;

    public static Builder builder() {
        return WavefrontMesh.builder(0);
    }

    public static Builder builder(int initialCapacity) {
        return new Builder(initialCapacity);
    }

    public WavefrontMesh(String title, WavefrontPolygon ... polygons) {
        this.title = title;
        this.polygons = polygons;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.polygons.length;
    }

    public WavefrontPolygon getPolygon(int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new IllegalArgumentException("Polygon index is out of bounds");
        }
        return this.polygons[index];
    }

    public static class Builder {
        private static final WavefrontPolygon[] EMPTY = new WavefrontPolygon[0];
        private String title = "";
        private List<WavefrontPolygon> polygons;

        private Builder(int initialCapacity) {
            if (initialCapacity > 0) {
                this.polygons = new ArrayList<WavefrontPolygon>(initialCapacity);
            }
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder polygon(WavefrontPolygon polygon) {
            if (polygon != null) {
                if (this.polygons == null) {
                    this.polygons = new ArrayList<WavefrontPolygon>();
                }
                this.polygons.add(polygon);
            }
            return this;
        }

        public WavefrontMesh build() {
            return new WavefrontMesh(this.title, this.polygons == null || this.polygons.isEmpty() ? EMPTY : this.polygons.toArray(new WavefrontPolygon[0]));
        }
    }
}

