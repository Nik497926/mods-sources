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

public class ModelExtractor
extends ModelBase {
    ModelRenderer P2;
    ModelRenderer S1;
    ModelRenderer S2;
    ModelRenderer S3;
    ModelRenderer S4;
    ModelRenderer r1;
    ModelRenderer r2;
    ModelRenderer r3;
    ModelRenderer r4;
    ModelRenderer r5;
    ModelRenderer r6;
    ModelRenderer r7;
    ModelRenderer r8;
    ModelRenderer r9;
    ModelRenderer r10;
    ModelRenderer S7;
    ModelRenderer S6;
    ModelRenderer S5;
    ModelRenderer S8;
    ModelRenderer P4;
    ModelRenderer P3;

    public ModelExtractor() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.P2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.P2.addBox(0.0f, 0.0f, 0.0f, 14, 2, 14);
        this.P2.setRotationPoint(-7.0f, 8.0f, -7.0f);
        this.P2.setTextureSize(64, 32);
        this.P2.mirror = true;
        this.setRotation(this.P2, 0.0f, 0.0f, 0.0f);
        this.S1 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S1.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S1.setRotationPoint(3.0f, 10.0f, -5.0f);
        this.S1.setTextureSize(64, 32);
        this.S1.mirror = true;
        this.setRotation(this.S1, 0.0f, 0.0f, 0.0f);
        this.S2 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S2.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S2.setRotationPoint(-5.0f, 10.0f, 3.0f);
        this.S2.setTextureSize(64, 32);
        this.S2.mirror = true;
        this.setRotation(this.S2, 0.0f, 0.0f, 0.0f);
        this.S3 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S3.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S3.setRotationPoint(3.0f, 10.0f, 3.0f);
        this.S3.setTextureSize(64, 32);
        this.S3.mirror = true;
        this.setRotation(this.S3, 0.0f, 0.0f, 0.0f);
        this.S4 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S4.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S4.setRotationPoint(-5.0f, 10.0f, -5.0f);
        this.S4.setTextureSize(64, 32);
        this.S4.mirror = true;
        this.setRotation(this.S4, 0.0f, 0.0f, 0.0f);
        this.r1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r1.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.r1.setRotationPoint(1.0f, 20.0f, 0.0f);
        this.r1.setTextureSize(64, 32);
        this.r1.mirror = true;
        this.setRotation(this.r1, 0.0f, 0.0f, 0.0f);
        this.r2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r2.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.r2.setRotationPoint(-3.0f, 20.0f, 4.0f);
        this.r2.setTextureSize(64, 32);
        this.r2.mirror = true;
        this.setRotation(this.r2, 0.0f, 0.0f, 0.0f);
        this.r3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r3.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.r3.setRotationPoint(4.0f, 20.0f, -6.0f);
        this.r3.setTextureSize(64, 32);
        this.r3.mirror = true;
        this.setRotation(this.r3, 0.0f, 0.0f, 0.0f);
        this.r4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r4.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.r4.setRotationPoint(-5.0f, 20.0f, -4.0f);
        this.r4.setTextureSize(64, 32);
        this.r4.mirror = true;
        this.setRotation(this.r4, 0.0f, 0.0f, 0.0f);
        this.r5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r5.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.r5.setRotationPoint(4.0f, 20.0f, -1.0f);
        this.r5.setTextureSize(64, 32);
        this.r5.mirror = true;
        this.setRotation(this.r5, 0.0f, 0.0f, 0.0f);
        this.r6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r6.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.r6.setRotationPoint(-5.0f, 20.0f, 2.0f);
        this.r6.setTextureSize(64, 32);
        this.r6.mirror = true;
        this.setRotation(this.r6, 0.0f, 0.0f, 0.0f);
        this.r7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r7.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.r7.setRotationPoint(-1.0f, 20.0f, -5.0f);
        this.r7.setTextureSize(64, 32);
        this.r7.mirror = true;
        this.setRotation(this.r7, 0.0f, 0.0f, 0.0f);
        this.r8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r8.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.r8.setRotationPoint(5.0f, 20.0f, 5.0f);
        this.r8.setTextureSize(64, 32);
        this.r8.mirror = true;
        this.setRotation(this.r8, 0.0f, 0.0f, 0.0f);
        this.r9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r9.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.r9.setRotationPoint(2.0f, 20.0f, 3.0f);
        this.r9.setTextureSize(64, 32);
        this.r9.mirror = true;
        this.setRotation(this.r9, 0.0f, 0.0f, 0.0f);
        this.r10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.r10.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.r10.setRotationPoint(-3.0f, 20.0f, -2.0f);
        this.r10.setTextureSize(64, 32);
        this.r10.mirror = true;
        this.setRotation(this.r10, 0.0f, 0.0f, 0.0f);
        this.S7 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S7.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S7.setRotationPoint(-4.0f, 0.0f, 2.0f);
        this.S7.setTextureSize(64, 32);
        this.S7.mirror = true;
        this.setRotation(this.S7, 0.0f, 0.0f, 0.0f);
        this.S6 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S6.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S6.setRotationPoint(2.0f, 0.0f, 2.0f);
        this.S6.setTextureSize(64, 32);
        this.S6.mirror = true;
        this.setRotation(this.S6, 0.0f, 0.0f, 0.0f);
        this.S5 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S5.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S5.setRotationPoint(2.0f, 0.0f, -4.0f);
        this.S5.setTextureSize(64, 32);
        this.S5.mirror = true;
        this.setRotation(this.S5, 0.0f, 0.0f, 0.0f);
        this.S8 = new ModelRenderer((ModelBase)this, 12, 0);
        this.S8.addBox(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.S8.setRotationPoint(-4.0f, 0.0f, -4.0f);
        this.S8.setTextureSize(64, 32);
        this.S8.mirror = true;
        this.setRotation(this.S8, 0.0f, 0.0f, 0.0f);
        this.P4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.P4.addBox(0.0f, 0.0f, 0.0f, 14, 2, 14);
        this.P4.setRotationPoint(-7.0f, 18.0f, -7.0f);
        this.P4.setTextureSize(64, 32);
        this.P4.mirror = true;
        this.setRotation(this.P4, 0.0f, 0.0f, 0.0f);
        this.P3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.P3.addBox(0.0f, 0.0f, 0.0f, 14, 2, 14);
        this.P3.setRotationPoint(-7.0f, -1.0f, -7.0f);
        this.P3.setTextureSize(64, 32);
        this.P3.mirror = true;
        this.setRotation(this.P3, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.P2.render(f5);
        this.S1.render(f5);
        this.S2.render(f5);
        this.S3.render(f5);
        this.S4.render(f5);
        this.r1.render(f5);
        this.r2.render(f5);
        this.r3.render(f5);
        this.r4.render(f5);
        this.r5.render(f5);
        this.r6.render(f5);
        this.r7.render(f5);
        this.r8.render(f5);
        this.r9.render(f5);
        this.r10.render(f5);
        this.S7.render(f5);
        this.S6.render(f5);
        this.S5.render(f5);
        this.S8.render(f5);
        this.P4.render(f5);
        this.P3.render(f5);
    }

    public void render(float f5) {
        this.P2.render(f5);
        this.S1.render(f5);
        this.S2.render(f5);
        this.S3.render(f5);
        this.S4.render(f5);
        this.r1.render(f5);
        this.r2.render(f5);
        this.r3.render(f5);
        this.r4.render(f5);
        this.r5.render(f5);
        this.r6.render(f5);
        this.r7.render(f5);
        this.r8.render(f5);
        this.r9.render(f5);
        this.r10.render(f5);
        this.S7.render(f5);
        this.S6.render(f5);
        this.S5.render(f5);
        this.S8.render(f5);
        this.P4.render(f5);
        this.P3.render(f5);
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

