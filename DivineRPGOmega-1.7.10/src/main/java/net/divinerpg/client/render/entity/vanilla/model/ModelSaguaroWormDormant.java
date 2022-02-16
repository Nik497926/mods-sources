/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSaguaroWormDormant
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

    public ModelSaguaroWormDormant() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape1.setRotationPoint(-8.0f, -8.0f, -8.0f);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape2.setRotationPoint(-8.0f, 8.0f, -8.0f);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape3.setRotationPoint(-8.0f, -24.0f, -8.0f);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
    }
}

