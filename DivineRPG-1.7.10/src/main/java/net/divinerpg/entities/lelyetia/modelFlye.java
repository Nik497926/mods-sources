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

public class modelFlye
extends ModelBase {
    ModelRenderer Shape2;
    ModelRenderer wingR;
    ModelRenderer wingL;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer wingR2;
    ModelRenderer wingL2;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public modelFlye() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.Shape2 = new ModelRenderer((ModelBase)this, 79, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
        this.Shape2.setRotationPoint(2.0f, -4.0f, -6.0f);
        this.Shape2.setTextureSize(128, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.wingR = new ModelRenderer((ModelBase)this, 35, 0);
        this.wingR.addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
        this.wingR.setRotationPoint(-14.0f, 1.0f, 0.0f);
        this.wingR.setTextureSize(128, 32);
        this.wingR.mirror = true;
        this.setRotation(this.wingR, 0.0f, 0.0f, 0.0f);
        this.wingL = new ModelRenderer((ModelBase)this, 35, 0);
        this.wingL.addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
        this.wingL.setRotationPoint(14.0f, 0.0f, 0.0f);
        this.wingL.setTextureSize(128, 32);
        this.wingL.mirror = true;
        this.setRotation(this.wingL, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 79, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
        this.Shape3.setRotationPoint(-14.0f, -4.0f, -6.0f);
        this.Shape3.setTextureSize(128, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 68, 0);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Shape4.setRotationPoint(6.0f, 8.0f, -2.0f);
        this.Shape4.setTextureSize(128, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 79, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
        this.Shape5.setRotationPoint(-14.0f, 12.0f, -6.0f);
        this.Shape5.setTextureSize(128, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.wingR2 = new ModelRenderer((ModelBase)this, 35, 0);
        this.wingR2.addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
        this.wingR2.setRotationPoint(-14.0f, 16.0f, 0.0f);
        this.wingR2.setTextureSize(128, 32);
        this.wingR2.mirror = true;
        this.setRotation(this.wingR2, 0.0f, 0.0f, 0.0f);
        this.wingL2 = new ModelRenderer((ModelBase)this, 35, 0);
        this.wingL2.addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
        this.wingL2.setRotationPoint(14.0f, 15.0f, 0.0f);
        this.wingL2.setTextureSize(128, 32);
        this.wingL2.mirror = true;
        this.setRotation(this.wingL2, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 79, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
        this.Shape6.setRotationPoint(2.0f, 12.0f, -6.0f);
        this.Shape6.setTextureSize(128, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 68, 0);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Shape7.setRotationPoint(-2.0f, 16.0f, -2.0f);
        this.Shape7.setTextureSize(128, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 68, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Shape8.setRotationPoint(-2.0f, 0.0f, -2.0f);
        this.Shape8.setTextureSize(128, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 68, 0);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Shape9.setRotationPoint(-10.0f, 8.0f, -2.0f);
        this.Shape9.setTextureSize(128, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.Shape2.render(par7);
        this.wingR.render(par7);
        this.wingL.render(par7);
        this.Shape3.render(par7);
        this.Shape4.render(par7);
        this.Shape5.render(par7);
        this.wingR2.render(par7);
        this.wingL2.render(par7);
        this.Shape6.render(par7);
        this.Shape7.render(par7);
        this.Shape8.render(par7);
        this.Shape9.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.wingR.rotateAngleZ = MathHelper.cos((float)(par3 * 2.1f)) * (float)Math.PI * 0.15f;
        this.wingL.rotateAngleZ = -this.wingR.rotateAngleZ;
        this.wingR2.rotateAngleZ = MathHelper.cos((float)(par3 * 2.1f)) * (float)Math.PI * 0.15f;
        this.wingL2.rotateAngleZ = -this.wingR2.rotateAngleZ;
    }
}

