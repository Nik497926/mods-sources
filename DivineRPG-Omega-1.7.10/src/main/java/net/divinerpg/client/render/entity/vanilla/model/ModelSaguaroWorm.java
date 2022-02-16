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

public class ModelSaguaroWorm
extends ModelBase {
    ModelRenderer connector2;
    ModelRenderer middle;
    ModelRenderer base;
    ModelRenderer connector1;
    ModelRenderer head;

    public ModelSaguaroWorm() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.connector2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.connector2.addBox(0.0f, 0.0f, 0.0f, 10, 16, 10);
        this.connector2.setRotationPoint(-5.0f, -24.0f, -21.0f);
        this.connector2.setTextureSize(64, 64);
        this.connector2.mirror = true;
        this.setRotation(this.connector2, 0.5759587f, 0.0f, 0.0f);
        this.middle = new ModelRenderer((ModelBase)this, 0, 0);
        this.middle.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.middle.setRotationPoint(-8.0f, -13.0f, -16.0f);
        this.middle.setTextureSize(64, 64);
        this.middle.mirror = true;
        this.setRotation(this.middle, 0.4363323f, 0.0f, 0.0f);
        this.base = new ModelRenderer((ModelBase)this, 0, 0);
        this.base.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.base.setRotationPoint(-8.0f, 8.0f, -8.0f);
        this.base.setTextureSize(64, 64);
        this.base.mirror = true;
        this.setRotation(this.base, 0.0f, 0.0f, 0.0f);
        this.connector1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.connector1.addBox(0.0f, 0.0f, 0.0f, 10, 16, 10);
        this.connector1.setRotationPoint(-5.0f, -3.0f, -9.0f);
        this.connector1.setTextureSize(64, 64);
        this.connector1.mirror = true;
        this.setRotation(this.connector1, 0.3316126f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-8.0f, -16.0f, -8.0f, 16, 16, 16);
        this.head.setRotationPoint(0.0f, -24.0f, -14.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 1.047198f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.connector2.render(f5);
        this.middle.render(f5);
        this.base.render(f5);
        this.connector1.render(f5);
        this.head.render(f5);
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

