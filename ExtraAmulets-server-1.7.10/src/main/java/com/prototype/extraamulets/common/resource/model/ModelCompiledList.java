/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model;

import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.opengl.GL11;

public class ModelCompiledList {
    private int index = 0;
    private boolean init = false;

    public int getId() {
        return this.index;
    }

    public boolean exists() {
        return this.init;
    }

    public void gen() {
        if (!this.exists()) {
            this.index = GLAllocation.generateDisplayLists((int)1);
            this.init = true;
        }
    }

    public void begin() {
        if (this.exists()) {
            GL11.glNewList((int)this.index, (int)4864);
        }
    }

    public void end() {
        if (this.exists()) {
            GL11.glEndList();
        }
    }

    public void draw() {
        if (this.exists()) {
            GL11.glCallList((int)this.index);
        }
    }

    public void delete() {
        if (this.exists()) {
            GLAllocation.deleteDisplayLists((int)this.index);
            this.init = false;
            this.index = -1;
        }
    }
}

