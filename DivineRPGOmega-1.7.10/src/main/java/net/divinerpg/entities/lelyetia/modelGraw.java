/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.entities.lelyetia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelGraw
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer WingL;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer WingR;
    ModelRenderer WingR2;
    ModelRenderer WingL2;
    ModelRenderer WingR3;
    ModelRenderer WingL3;
    ModelRenderer WingR4;
    ModelRenderer WingL4;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;

    public modelGraw() {
        this.textureWidth = 256;
        this.textureHeight = 64;
        this.Shape1 = new ModelRenderer((ModelBase)this, 82, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 14, 8, 14);
        this.Shape1.setRotationPoint(-7.0f, 3.0f, 19.0f);
        this.Shape1.setTextureSize(256, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.WingL = new ModelRenderer((ModelBase)this, 196, 10);
        this.WingL.addBox(0.0f, 1.0f, 20.0f, 22, 1, 2);
        this.WingL.setRotationPoint(10.0f, 11.0f, 2.0f);
        this.WingL.setTextureSize(256, 64);
        this.WingL.mirror = true;
        this.setRotation(this.WingL, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 82, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 14, 8, 14);
        this.Shape2.setRotationPoint(-7.0f, 3.0f, 2.0f);
        this.Shape2.setTextureSize(256, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 140, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape3.setRotationPoint(0.0f, 17.0f, -18.0f);
        this.Shape3.setTextureSize(256, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 113, 23);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 14, 7, 14);
        this.Shape4.setRotationPoint(-6.0f, 11.0f, -14.0f);
        this.Shape4.setTextureSize(256, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 39);
        this.Shape5.addBox(16.0f, 0.0f, 0.0f, 0, 2, 16);
        this.Shape5.setRotationPoint(-7.0f, 19.0f, -16.0f);
        this.Shape5.setTextureSize(256, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 140, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape6.setRotationPoint(-7.0f, 17.0f, -18.0f);
        this.Shape6.setTextureSize(256, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 140, 0);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
        this.Shape7.setRotationPoint(7.0f, 17.0f, -18.0f);
        this.Shape7.setTextureSize(256, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 140, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.Shape8.setRotationPoint(-7.0f, 21.0f, -16.0f);
        this.Shape8.setTextureSize(256, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 39);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 0, 2, 16);
        this.Shape9.setRotationPoint(-7.0f, 19.0f, -16.0f);
        this.Shape9.setTextureSize(256, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.WingR = new ModelRenderer((ModelBase)this, 196, 10);
        this.WingR.addBox(-21.0f, 1.0f, 20.0f, 22, 1, 2);
        this.WingR.setRotationPoint(-11.0f, 11.0f, 2.0f);
        this.WingR.setTextureSize(256, 64);
        this.WingR.mirror = true;
        this.setRotation(this.WingR, 0.0f, 0.0f, 0.0f);
        this.WingR2 = new ModelRenderer((ModelBase)this, 159, 21);
        this.WingR2.addBox(-21.0f, 0.0f, 0.0f, 22, 5, 6);
        this.WingR2.setRotationPoint(-11.0f, 11.0f, 2.0f);
        this.WingR2.setTextureSize(256, 64);
        this.WingR2.mirror = true;
        this.setRotation(this.WingR2, 0.0f, 0.0f, 0.0f);
        this.WingL2 = new ModelRenderer((ModelBase)this, 159, 21);
        this.WingL2.addBox(0.0f, 0.0f, 0.0f, 22, 5, 6);
        this.WingL2.setRotationPoint(10.0f, 11.0f, 2.0f);
        this.WingL2.setTextureSize(256, 64);
        this.WingL2.mirror = true;
        this.setRotation(this.WingL2, 0.0f, 0.0f, 0.0f);
        this.WingR3 = new ModelRenderer((ModelBase)this, 102, 46);
        this.WingR3.addBox(-21.0f, 2.0f, 6.0f, 22, 1, 16);
        this.WingR3.setRotationPoint(-11.0f, 11.0f, 2.0f);
        this.WingR3.setTextureSize(256, 64);
        this.WingR3.mirror = true;
        this.setRotation(this.WingR3, 0.0f, 0.0f, 0.0f);
        this.WingL3 = new ModelRenderer((ModelBase)this, 102, 46);
        this.WingL3.addBox(0.0f, 2.0f, 6.0f, 22, 1, 16);
        this.WingL3.setRotationPoint(10.0f, 11.0f, 2.0f);
        this.WingL3.setTextureSize(256, 64);
        this.WingL3.mirror = true;
        this.setRotation(this.WingL3, 0.0f, 0.0f, 0.0f);
        this.WingR4 = new ModelRenderer((ModelBase)this, 196, 10);
        this.WingR4.addBox(-21.0f, 1.0f, 12.0f, 22, 1, 2);
        this.WingR4.setRotationPoint(-11.0f, 11.0f, 2.0f);
        this.WingR4.setTextureSize(256, 64);
        this.WingR4.mirror = true;
        this.setRotation(this.WingR4, 0.0f, 0.0f, 0.0f);
        this.WingL4 = new ModelRenderer((ModelBase)this, 196, 10);
        this.WingL4.addBox(0.0f, 1.0f, 12.0f, 22, 1, 2);
        this.WingL4.setRotationPoint(10.0f, 11.0f, 2.0f);
        this.WingL4.setTextureSize(256, 64);
        this.WingL4.mirror = true;
        this.setRotation(this.WingL4, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 175, 34);
        this.Shape10.addBox(-3.0f, -12.0f, 0.0f, 6, 22, 1);
        this.Shape10.setRotationPoint(12.0f, 10.0f, 35.0f);
        this.Shape10.setTextureSize(256, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.6981317f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 202, 35);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 8, 8, 16);
        this.Shape11.setRotationPoint(6.0f, 14.0f, 31.0f);
        this.Shape11.setTextureSize(256, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 1.047198f, 0.0f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 49, 49);
        this.Shape12.addBox(3.0f, 6.0f, 16.0f, 2, 2, 6);
        this.Shape12.setRotationPoint(6.0f, 14.0f, 31.0f);
        this.Shape12.setTextureSize(256, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 1.047198f, 0.0f);
        this.Shape13 = new ModelRenderer((ModelBase)this, 202, 35);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 8, 8, 16);
        this.Shape13.setRotationPoint(-10.0f, 14.0f, 24.0f);
        this.Shape13.setTextureSize(256, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, -1.047198f, 0.0f);
        this.Shape14 = new ModelRenderer((ModelBase)this, 49, 49);
        this.Shape14.addBox(6.0f, 6.0f, 16.0f, 2, 2, 6);
        this.Shape14.setRotationPoint(-10.0f, 14.0f, 24.0f);
        this.Shape14.setTextureSize(256, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, -1.047198f, 0.0f);
        this.Shape15 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape15.addBox(0.0f, 0.0f, 0.0f, 20, 12, 35);
        this.Shape15.setRotationPoint(-10.0f, 11.0f, 0.0f);
        this.Shape15.setTextureSize(256, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer((ModelBase)this, 175, 34);
        this.Shape16.addBox(-3.0f, -12.0f, 0.0f, 6, 22, 1);
        this.Shape16.setRotationPoint(0.0f, 9.0f, 35.0f);
        this.Shape16.setTextureSize(256, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer((ModelBase)this, 175, 34);
        this.Shape17.addBox(-3.0f, -12.0f, 0.0f, 6, 22, 1);
        this.Shape17.setRotationPoint(-12.0f, 10.0f, 35.0f);
        this.Shape17.setTextureSize(256, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, -0.6981317f);
        this.Shape18 = new ModelRenderer((ModelBase)this, 49, 49);
        this.Shape18.addBox(6.0f, 6.0f, 16.0f, 2, 2, 6);
        this.Shape18.setRotationPoint(6.0f, 14.0f, 31.0f);
        this.Shape18.setTextureSize(256, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0f, 1.047198f, 0.0f);
        this.Shape19 = new ModelRenderer((ModelBase)this, 49, 49);
        this.Shape19.addBox(0.0f, 6.0f, 16.0f, 2, 2, 6);
        this.Shape19.setRotationPoint(-10.0f, 14.0f, 24.0f);
        this.Shape19.setTextureSize(256, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0f, -1.047198f, 0.0f);
        this.Shape20 = new ModelRenderer((ModelBase)this, 49, 49);
        this.Shape20.addBox(0.0f, 6.0f, 16.0f, 2, 2, 6);
        this.Shape20.setRotationPoint(6.0f, 14.0f, 31.0f);
        this.Shape20.setTextureSize(256, 64);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0f, 1.047198f, 0.0f);
        this.Shape21 = new ModelRenderer((ModelBase)this, 49, 49);
        this.Shape21.addBox(3.0f, 6.0f, 16.0f, 2, 2, 6);
        this.Shape21.setRotationPoint(-10.0f, 14.0f, 24.0f);
        this.Shape21.setTextureSize(256, 64);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0f, -1.047198f, 0.0f);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.Shape1.render(par7);
        this.WingL.render(par7);
        this.Shape2.render(par7);
        this.Shape3.render(par7);
        this.Shape4.render(par7);
        this.Shape5.render(par7);
        this.Shape6.render(par7);
        this.Shape7.render(par7);
        this.Shape8.render(par7);
        this.Shape9.render(par7);
        this.WingR.render(par7);
        this.WingR2.render(par7);
        this.WingL2.render(par7);
        this.WingR3.render(par7);
        this.WingL3.render(par7);
        this.WingR4.render(par7);
        this.WingL4.render(par7);
        this.Shape10.render(par7);
        this.Shape11.render(par7);
        this.Shape12.render(par7);
        this.Shape13.render(par7);
        this.Shape14.render(par7);
        this.Shape15.render(par7);
        this.Shape16.render(par7);
        this.Shape17.render(par7);
        this.Shape18.render(par7);
        this.Shape19.render(par7);
        this.Shape20.render(par7);
        this.Shape21.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.WingR.rotateAngleZ = MathHelper.cos((float)(par3 * 0.1f)) * (float)Math.PI * 0.15f;
        this.WingL.rotateAngleZ = -this.WingR.rotateAngleZ;
        this.WingR2.rotateAngleZ = MathHelper.cos((float)(par3 * 0.1f)) * (float)Math.PI * 0.15f;
        this.WingL2.rotateAngleZ = -this.WingR.rotateAngleZ;
        this.WingR3.rotateAngleZ = MathHelper.cos((float)(par3 * 0.1f)) * (float)Math.PI * 0.15f;
        this.WingL3.rotateAngleZ = -this.WingR.rotateAngleZ;
        this.WingR4.rotateAngleZ = MathHelper.cos((float)(par3 * 0.1f)) * (float)Math.PI * 0.15f;
        this.WingL4.rotateAngleZ = -this.WingR.rotateAngleZ;
    }
}

