/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package net.divinerpg.client.model;

import java.util.ArrayList;
import net.divinerpg.utils.LogHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public abstract class DivineModel
extends ModelBase {
    protected ArrayList<ModelRenderer> parts;
    protected String texture;

    public DivineModel(String texture, int textureWidth, int textureHeight) {
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.parts = new ArrayList();
    }

    public String getTextureName() {
        return this.texture;
    }

    public void render(float rotation) {
        if (this.parts == null || this.parts.isEmpty()) {
            LogHelper.debug("Nothing to rotate in type: " + ((Object)((Object)this)).getClass().getName());
            return;
        }
        for (ModelRenderer model : this.parts) {
            model.render(rotation);
        }
    }

    public void addPart(ModelRenderer model) {
        this.parts.add(model);
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

