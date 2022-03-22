/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model;

import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontDrawMode;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontMesh;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontModel;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontPolygon;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVector;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVertex;
import net.minecraft.client.renderer.Tessellator;

public class ModelBufferBuilder {
    protected final Tessellator tessellator = Tessellator.instance;
    private WavefrontDrawMode drawMode = WavefrontDrawMode.UNKNOWN;
    private boolean backCulling = false;

    public boolean isBackCulling() {
        return this.backCulling;
    }

    public ModelBufferBuilder setBackCulling(boolean flag) {
        this.backCulling = flag;
        return this;
    }

    public void model(WavefrontModel model) {
        int size = model.getSize();
        for (int i = 0; i < size; ++i) {
            this.mesh(model.getMesh(i));
        }
    }

    public void mesh(WavefrontMesh mesh) {
        int size = mesh.getSize();
        for (int i = 0; i < size; ++i) {
            this.polygon(mesh.getPolygon(i));
        }
    }

    public void polygon(WavefrontPolygon polygon) {
        int size = polygon.getSize();
        block10: for (int i = 0; i < size; ++i) {
            int j;
            WavefrontDrawMode mode = polygon.getDrawMode();
            if (this.drawMode != mode) {
                this.finish();
                this.start(mode);
            }
            if (this.isBackCulling()) {
                switch (this.drawMode) {
                    case TRIANGLES: {
                        this.vertex(polygon.getVertex(2));
                        this.vertex(polygon.getVertex(1));
                        this.vertex(polygon.getVertex(0));
                        break;
                    }
                    case QUADS: {
                        this.vertex(polygon.getVertex(3));
                        this.vertex(polygon.getVertex(2));
                        this.vertex(polygon.getVertex(1));
                        this.vertex(polygon.getVertex(0));
                        break;
                    }
                    case TRIANGLE_FAN: {
                        for (j = polygon.getSize() - 1; j >= 0; --j) {
                            this.vertex(polygon.getVertex(j));
                        }
                        continue block10;
                    }
                }
                continue;
            }
            switch (this.drawMode) {
                case TRIANGLES: {
                    this.vertex(polygon.getVertex(0));
                    this.vertex(polygon.getVertex(1));
                    this.vertex(polygon.getVertex(2));
                    continue block10;
                }
                case QUADS: {
                    this.vertex(polygon.getVertex(0));
                    this.vertex(polygon.getVertex(1));
                    this.vertex(polygon.getVertex(2));
                    this.vertex(polygon.getVertex(3));
                    continue block10;
                }
                case TRIANGLE_FAN: {
                    for (j = 0; j < polygon.getSize(); ++j) {
                        this.vertex(polygon.getVertex(j));
                    }
                    continue block10;
                }
            }
        }
    }

    public void start(WavefrontDrawMode mode) {
        if (mode != WavefrontDrawMode.UNKNOWN) {
            this.tessellator.startDrawing(mode.getId());
            this.drawMode = mode;
        }
    }

    public void finish() {
        if (this.drawMode != WavefrontDrawMode.UNKNOWN) {
            this.tessellator.draw();
            this.drawMode = WavefrontDrawMode.UNKNOWN;
        }
    }

    public void vertex(WavefrontVertex vertex) {
        this.normal(vertex.getNormal());
        this.texture(vertex.getTexture());
        this.pos(vertex.getPos());
    }

    protected void texture(WavefrontVector vector) {
        this.tessellator.setTextureUV((double)vector.getX(), (double)vector.getY());
    }

    protected void pos(WavefrontVector vector) {
        this.tessellator.addVertex((double)vector.getX(), (double)vector.getY(), (double)vector.getZ());
    }

    protected void normal(WavefrontVector vector) {
        this.tessellator.setNormal(vector.getX(), vector.getY(), vector.getZ());
    }
}

