/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelParasectaAltar
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer P1;
    ModelRenderer P2;
    ModelRenderer P3;
    ModelRenderer P4;
    ModelRenderer S1;
    ModelRenderer S2;
    ModelRenderer S3;
    ModelRenderer S4;

    public ModelParasectaAltar() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Base = new ModelRenderer((ModelBase)this, 0, 15);
        this.Base.addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.Base.setRotationPoint(-8.0f, 10.0f, -8.0f);
        this.Base.setTextureSize(64, 32);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.P1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.P1.addBox(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.P1.setRotationPoint(-3.0f, 11.0f, -6.0f);
        this.P1.setTextureSize(64, 32);
        this.P1.mirror = true;
        this.setRotation(this.P1, 0.0f, 0.0f, 0.0f);
        this.P2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.P2.addBox(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.P2.setRotationPoint(1.0f, 11.0f, -6.0f);
        this.P2.setTextureSize(64, 32);
        this.P2.mirror = true;
        this.setRotation(this.P2, 0.0f, 0.0f, 0.0f);
        this.P3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.P3.addBox(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.P3.setRotationPoint(1.0f, 11.0f, 4.0f);
        this.P3.setTextureSize(64, 32);
        this.P3.mirror = true;
        this.setRotation(this.P3, 0.0f, 0.0f, 0.0f);
        this.P4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.P4.addBox(0.0f, 0.0f, 0.0f, 2, 9, 2);
        this.P4.setRotationPoint(-3.0f, 11.0f, 4.0f);
        this.P4.setTextureSize(64, 32);
        this.P4.mirror = true;
        this.setRotation(this.P4, 0.0f, 0.0f, 0.0f);
        this.S1 = new ModelRenderer((ModelBase)this, 22, 0);
        this.S1.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.S1.setRotationPoint(1.0f, 20.0f, 4.0f);
        this.S1.setTextureSize(64, 32);
        this.S1.mirror = true;
        this.setRotation(this.S1, 0.0f, 0.0f, 0.0f);
        this.S2 = new ModelRenderer((ModelBase)this, 22, 0);
        this.S2.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.S2.setRotationPoint(-5.0f, 20.0f, 4.0f);
        this.S2.setTextureSize(64, 32);
        this.S2.mirror = true;
        this.setRotation(this.S2, 0.0f, 0.0f, 0.0f);
        this.S3 = new ModelRenderer((ModelBase)this, 22, 0);
        this.S3.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.S3.setRotationPoint(-5.0f, 20.0f, -8.0f);
        this.S3.setTextureSize(64, 32);
        this.S3.mirror = true;
        this.setRotation(this.S3, 0.0f, 0.0f, 0.0f);
        this.S4 = new ModelRenderer((ModelBase)this, 22, 0);
        this.S4.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.S4.setRotationPoint(1.0f, 20.0f, -8.0f);
        this.S4.setTextureSize(64, 32);
        this.S4.mirror = true;
        this.setRotation(this.S4, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Base.render(f5);
        this.P1.render(f5);
        this.P2.render(f5);
        this.P3.render(f5);
        this.P4.render(f5);
        this.S1.render(f5);
        this.S2.render(f5);
        this.S3.render(f5);
        this.S4.render(f5);
    }

    public void render(float f5) {
        this.Base.render(f5);
        this.P1.render(f5);
        this.P2.render(f5);
        this.P3.render(f5);
        this.P4.render(f5);
        this.S1.render(f5);
        this.S2.render(f5);
        this.S3.render(f5);
        this.S4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

