/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package net.divinerpg.client.render.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPresentBox
extends ModelBase {
    public ModelRenderer lid;
    public ModelRenderer base;
    public ModelRenderer knob;

    public ModelPresentBox() {
        this.textureHeight = 128;
        this.textureWidth = 128;
        this.lid = new ModelRenderer((ModelBase)this, 0, 0);
        this.lid.addBox(-1.0f, -8.0f, -33.0f, 32, 7, 32, 0.0f);
        this.lid.rotationPointX = 1.0f;
        this.lid.rotationPointY = 7.0f;
        this.lid.rotationPointZ = 33.0f;
        this.base = new ModelRenderer((ModelBase)this, 0, 40);
        this.base.addBox(-1.0f, -1.0f, -1.0f, 32, 27, 32, 0.0f);
        this.base.rotationPointX = 1.0f;
        this.base.rotationPointY = 6.0f;
        this.base.rotationPointZ = 1.0f;
    }

    public void renderAll() {
        this.lid.render(0.03125f);
        this.base.render(0.03125f);
    }
}

