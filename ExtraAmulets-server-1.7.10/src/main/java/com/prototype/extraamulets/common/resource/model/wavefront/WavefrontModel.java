/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontMesh;
import java.util.ArrayList;
import java.util.List;

public class WavefrontModel {
    private final WavefrontMesh[] meshes;

    public static Builder builder() {
        return WavefrontModel.builder(0);
    }

    public static Builder builder(int initialCapacity) {
        return new Builder(initialCapacity);
    }

    public WavefrontModel(WavefrontMesh ... meshes) {
        this.meshes = meshes;
    }

    public int getSize() {
        return this.meshes.length;
    }

    public WavefrontMesh getMesh(int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new IllegalArgumentException("Mesh index is out of bounds");
        }
        return this.meshes[index];
    }

    public WavefrontMesh getMesh(String name) {
        return this.getMesh(name, false);
    }

    public WavefrontMesh getMesh(String name, boolean ignoreCase) {
        for (WavefrontMesh mesh : this.meshes) {
            if (!(ignoreCase ? mesh.getTitle().equalsIgnoreCase(name) : mesh.getTitle().equals(name))) continue;
            return mesh;
        }
        return null;
    }

    public static class Builder {
        private static final WavefrontMesh[] EMPTY = new WavefrontMesh[0];
        private List<WavefrontMesh> meshes;

        private Builder(int initialCapacity) {
            if (initialCapacity > 0) {
                this.meshes = new ArrayList<WavefrontMesh>(initialCapacity);
            }
        }

        public Builder mesh(WavefrontMesh mesh) {
            if (mesh != null) {
                if (this.meshes == null) {
                    this.meshes = new ArrayList<WavefrontMesh>();
                }
                this.meshes.add(mesh);
            }
            return this;
        }

        public WavefrontModel build() {
            return new WavefrontModel(this.meshes == null || this.meshes.isEmpty() ? EMPTY : this.meshes.toArray(new WavefrontMesh[this.meshes.size()]));
        }
    }
}

