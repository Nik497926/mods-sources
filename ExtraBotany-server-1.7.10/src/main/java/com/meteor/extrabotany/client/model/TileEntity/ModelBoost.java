/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.model.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value= Side.CLIENT)
public class ModelBoost
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;

    public ModelBoost() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1);
        this.Shape1.setRotationPoint(7.0f, 21.0f, 7.0f);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 1, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1);
        this.Shape2.setRotationPoint(-8.0f, 21.0f, 7.0f);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 2, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1);
        this.Shape3.setRotationPoint(-8.0f, 21.0f, -8.0f);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 3, 0);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1);
        this.Shape4.setRotationPoint(7.0f, 21.0f, -8.0f);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 6, 1, 2);
        this.Shape5.setRotationPoint(2.0f, 20.0f, 6.0f);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 6, 1, 2);
        this.Shape6.setRotationPoint(-4.0f, 20.0f, 6.0f);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Shape7.setRotationPoint(-8.0f, 20.0f, 6.0f);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 2, 1, 6);
        this.Shape8.setRotationPoint(6.0f, 20.0f, 0.0f);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, 0.0f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape9.addBox(0.0f, 0.0f, 0.0f, 2, 1, 6);
        this.Shape9.setRotationPoint(6.0f, 20.0f, -6.0f);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape10.addBox(0.0f, 0.0f, 0.0f, 6, 1, 2);
        this.Shape10.setRotationPoint(2.0f, 20.0f, -8.0f);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape11.addBox(0.0f, 0.0f, 0.0f, 6, 1, 2);
        this.Shape11.setRotationPoint(-4.0f, 20.0f, -8.0f);
        this.Shape11.setTextureSize(16, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape12.addBox(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Shape12.setRotationPoint(-8.0f, 20.0f, -8.0f);
        this.Shape12.setTextureSize(16, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        this.Shape13 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape13.addBox(0.0f, 0.0f, 0.0f, 2, 1, 6);
        this.Shape13.setRotationPoint(-8.0f, 20.0f, 0.0f);
        this.Shape13.setTextureSize(16, 16);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        this.Shape14 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape14.addBox(0.0f, 0.0f, 0.0f, 2, 1, 6);
        this.Shape14.setRotationPoint(-8.0f, 20.0f, -6.0f);
        this.Shape14.setTextureSize(16, 16);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
    }

    public void render() {
        float f5 = 0.0625f;
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

