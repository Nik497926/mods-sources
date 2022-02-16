/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.vethea.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class HoverStinger
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Wing1;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Wing2;
    ModelRenderer Wing3;
    ModelRenderer Wing4;

    public HoverStinger() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 1.0f, 8, 3, 8);
        this.Shape1.setRotationPoint(-4.0f, -1.0f, -3.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.Shape2.setRotationPoint(-3.0f, 8.0f, -1.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.6108652f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape3.addBox(1.0f, 3.0f, 2.0f, 4, 6, 4);
        this.Shape3.setRotationPoint(-3.0f, 8.0f, -1.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.7853982f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Shape4.addBox(2.0f, 7.0f, 4.0f, 2, 5, 2);
        this.Shape4.setRotationPoint(-3.0f, 8.0f, -1.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.9599311f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.addBox(0.0f, 0.0f, 1.0f, 8, 6, 8);
        this.Shape5.setRotationPoint(-4.0f, 4.0f, -2.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.4363323f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.addBox(0.0f, 0.0f, 1.0f, 10, 6, 10);
        this.Shape6.setRotationPoint(-5.0f, 2.0f, -4.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 42, 0);
        this.Shape7.addBox(0.0f, 0.0f, 1.0f, 2, 8, 2);
        this.Shape7.setRotationPoint(4.0f, -15.0f, -8.0f);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Wing1 = new ModelRenderer((ModelBase)this, 0, 26);
        this.Wing1.addBox(0.0f, -2.0f, 1.0f, 10, 6, 0);
        this.Wing1.setRotationPoint(5.0f, 4.0f, 4.0f);
        this.Wing1.setTextureSize(64, 32);
        this.Wing1.mirror = true;
        this.setRotation(this.Wing1, 0.0f, 0.0f, 0.0f);
        this.Wing1.mirror = false;
        this.Shape8 = new ModelRenderer((ModelBase)this, 22, 16);
        this.Shape8.addBox(0.0f, 0.0f, 1.0f, 8, 8, 8);
        this.Shape8.setRotationPoint(-4.0f, -11.0f, -10.0f);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 42, 0);
        this.Shape9.addBox(0.0f, 0.0f, 1.0f, 2, 8, 2);
        this.Shape9.setRotationPoint(-6.0f, -15.0f, -8.0f);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape10.addBox(0.0f, 0.0f, 1.0f, 10, 6, 10);
        this.Shape10.setRotationPoint(-5.0f, -7.0f, -4.0f);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Wing2 = new ModelRenderer((ModelBase)this, 0, 26);
        this.Wing2.addBox(-9.0f, -2.0f, 1.0f, 10, 6, 0);
        this.Wing2.setRotationPoint(-6.0f, 4.0f, 4.0f);
        this.Wing2.setTextureSize(64, 32);
        this.Wing2.mirror = false;
        this.setRotation(this.Wing2, 0.0f, 0.0f, 0.0f);
        this.Wing3 = new ModelRenderer((ModelBase)this, 0, 26);
        this.Wing3.addBox(0.0f, -2.0f, 1.0f, 10, 6, 0);
        this.Wing3.setRotationPoint(5.0f, -5.0f, 2.0f);
        this.Wing3.setTextureSize(64, 32);
        this.Wing3.mirror = true;
        this.setRotation(this.Wing3, 0.0f, 0.0f, 0.0f);
        this.Wing3.mirror = false;
        this.Wing4 = new ModelRenderer((ModelBase)this, 0, 26);
        this.Wing4.addBox(-9.0f, -2.0f, 1.0f, 10, 6, 0);
        this.Wing4.setRotationPoint(-6.0f, -5.0f, 2.0f);
        this.Wing4.setTextureSize(64, 32);
        this.Wing4.mirror = false;
        this.setRotation(this.Wing4, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Wing1.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Wing2.render(f5);
        this.Wing3.render(f5);
        this.Wing4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.Wing1.rotateAngleY = MathHelper.cos((float)(f3 * 1.3f)) * (float)Math.PI * 0.25f;
        this.Wing2.rotateAngleY = -this.Wing1.rotateAngleY;
        this.Wing3.rotateAngleY = MathHelper.cos((float)(f3 * 1.3f)) * (float)Math.PI * 0.25f;
        this.Wing4.rotateAngleY = -this.Wing3.rotateAngleY;
    }
}

