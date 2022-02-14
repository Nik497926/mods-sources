/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.vethea.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHungerHungry
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarmm;
    ModelRenderer leftarmb;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarmt;
    ModelRenderer leftarmm;
    ModelRenderer rightarmt;
    ModelRenderer rightarmb;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape7;
    ModelRenderer Shape6;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;

    public ModelHungerHungry() {
        this.textureWidth = 56;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(56, 64);
        this.head.mirror = true;
        this.setRotation(this.head, -0.7853982f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(56, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarmm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarmm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarmm.setRotationPoint(-5.0f, 7.0f, 0.5f);
        this.rightarmm.setTextureSize(56, 64);
        this.rightarmm.mirror = true;
        this.setRotation(this.rightarmm, 0.1745329f, 0.0f, 1.570796f);
        this.leftarmb = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarmb.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarmb.setRotationPoint(3.0f, 9.0f, 0.5f);
        this.leftarmb.setTextureSize(56, 64);
        this.leftarmb.mirror = true;
        this.setRotation(this.leftarmb, 0.1745329f, 0.0f, -0.9250245f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(56, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(56, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.leftarmt = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarmt.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarmt.setRotationPoint(5.0f, 3.0f, 0.5f);
        this.leftarmt.setTextureSize(56, 64);
        this.leftarmt.mirror = true;
        this.setRotation(this.leftarmt, 0.1745329f, 0.0f, -2.042035f);
        this.leftarmm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarmm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarmm.setRotationPoint(5.0f, 7.0f, 0.5f);
        this.leftarmm.setTextureSize(56, 64);
        this.leftarmm.mirror = true;
        this.setRotation(this.leftarmm, 0.1745329f, 0.0f, -1.570796f);
        this.rightarmt = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarmt.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarmt.setRotationPoint(-5.0f, 3.0f, 0.5f);
        this.rightarmt.setTextureSize(56, 64);
        this.rightarmt.mirror = true;
        this.setRotation(this.rightarmt, 0.1745329f, 0.0f, 2.042035f);
        this.rightarmb = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarmb.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarmb.setRotationPoint(-3.0f, 9.0f, 0.5f);
        this.rightarmb.setTextureSize(56, 64);
        this.rightarmb.mirror = true;
        this.setRotation(this.rightarmb, 0.1745329f, 0.0f, 0.9250245f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape1.addBox(0.0f, 0.0f, -9.0f, 8, 2, 9);
        this.Shape1.setRotationPoint(-4.0f, 2.0f, -1.0f);
        this.Shape1.setTextureSize(56, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape2.addBox(0.0f, 0.0f, -7.0f, 6, 1, 8);
        this.Shape2.setRotationPoint(-3.0f, 1.0f, -2.0f);
        this.Shape2.setTextureSize(56, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape3.addBox(0.0f, 0.0f, -9.0f, 8, 2, 9);
        this.Shape3.setRotationPoint(-4.0f, 8.0f, -1.0f);
        this.Shape3.setTextureSize(56, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape3.mirror = false;
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape4.addBox(0.0f, 0.0f, -7.0f, 6, 1, 8);
        this.Shape4.setRotationPoint(-3.0f, 10.0f, -2.0f);
        this.Shape4.setTextureSize(56, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape4.mirror = false;
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape5.setRotationPoint(2.0f, 3.4f, -9.0f);
        this.Shape5.setTextureSize(56, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape7.setRotationPoint(-0.5f, 3.4f, -9.0f);
        this.Shape7.setTextureSize(56, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape6.setRotationPoint(-3.0f, 3.4f, -9.0f);
        this.Shape6.setTextureSize(56, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape8.setRotationPoint(2.0f, 3.4f, -7.0f);
        this.Shape8.setTextureSize(56, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape9.setRotationPoint(2.0f, 3.4f, -5.0f);
        this.Shape9.setTextureSize(56, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape10.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape10.setRotationPoint(-3.0f, 3.4f, -7.0f);
        this.Shape10.setTextureSize(56, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape11.setRotationPoint(-3.0f, 3.4f, -5.0f);
        this.Shape11.setTextureSize(56, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape12.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape12.setRotationPoint(-1.7f, 7.4f, -9.0f);
        this.Shape12.setTextureSize(56, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        this.Shape13 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape13.setRotationPoint(2.0f, 7.4f, -6.0f);
        this.Shape13.setTextureSize(56, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        this.Shape14 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape14.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape14.setRotationPoint(-3.0f, 7.4f, -6.0f);
        this.Shape14.setTextureSize(56, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        this.Shape15 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape15.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape15.setRotationPoint(0.7f, 7.4f, -9.0f);
        this.Shape15.setTextureSize(56, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape16.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape16.setRotationPoint(2.0f, 7.4f, -8.0f);
        this.Shape16.setTextureSize(56, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape17.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Shape17.setRotationPoint(-3.0f, 7.4f, -8.0f);
        this.Shape17.setTextureSize(56, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarmm.render(f5);
        this.leftarmb.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.leftarmt.render(f5);
        this.leftarmm.render(f5);
        this.rightarmt.render(f5);
        this.rightarmb.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape7.render(f5);
        this.Shape6.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape17.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

