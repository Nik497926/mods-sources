/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.model.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelTilePoolEfir
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;

    public ModelTilePoolEfir() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape1.setRotationPoint(-11.0f, 4.0f, -6.0f);
        this.Shape1.setTextureSize(128, 128);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.7853982f, 0.5235988f, 0.6806784f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape2.setRotationPoint(-11.0f, 3.0f, -6.0f);
        this.Shape2.setTextureSize(128, 128);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.7853982f, 0.5235988f, 0.6806784f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 64);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape3.setRotationPoint(-11.0f, 2.0f, -6.0f);
        this.Shape3.setTextureSize(128, 128);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.7853982f, 0.5235988f, 0.6806784f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 96);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape4.setRotationPoint(-11.0f, 1.0f, -6.0f);
        this.Shape4.setTextureSize(128, 128);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.7853982f, 0.5235988f, 0.6806784f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 64, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape5.setRotationPoint(-11.0f, 0.0f, -6.0f);
        this.Shape5.setTextureSize(128, 128);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.7853982f, 0.5235988f, 0.6806784f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 64, 32);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape6.setRotationPoint(-11.0f, -1.0f, -6.0f);
        this.Shape6.setTextureSize(128, 128);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.7853982f, 0.5235988f, 0.6806784f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 64, 64);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape7.setRotationPoint(-11.0f, -2.0f, -6.0f);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.7853982f, 0.5235988f, 0.6806784f);
    }

    public void render() {
        float f = 0.0625f;
        this.Shape1.render(f);
        this.Shape2.render(f);
        this.Shape3.render(f);
        this.Shape4.render(f);
        this.Shape5.render(f);
        this.Shape6.render(f);
        this.Shape7.render(f);
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

