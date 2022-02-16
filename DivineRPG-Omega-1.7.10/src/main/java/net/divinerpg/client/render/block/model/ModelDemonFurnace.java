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

public class ModelDemonFurnace
extends ModelBase {
    ModelRenderer Box;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
    ModelRenderer Arm3;
    ModelRenderer Arm4;
    ModelRenderer Arm5;
    ModelRenderer Arm6;

    public ModelDemonFurnace() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Box = new ModelRenderer((ModelBase)this, 0, 0);
        this.Box.addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Box.setRotationPoint(8.0f, 8.0f, -8.0f);
        this.Box.setTextureSize(64, 64);
        this.Box.mirror = true;
        this.setRotation(this.Box, 0.0f, 0.0f, 3.141593f);
        this.Arm1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Arm1.addBox(0.0f, 0.0f, 0.0f, 4, 19, 2);
        this.Arm1.setRotationPoint(-7.0f, -7.0f, 3.0f);
        this.Arm1.setTextureSize(64, 64);
        this.Arm1.mirror = true;
        this.setRotation(this.Arm1, 0.0f, 0.0f, 1.256637f);
        this.Arm2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Arm2.addBox(0.0f, 0.0f, 0.0f, 4, 16, 2);
        this.Arm2.setRotationPoint(-8.0f, 8.0f, 3.0f);
        this.Arm2.setTextureSize(64, 64);
        this.Arm2.mirror = true;
        this.setRotation(this.Arm2, 0.0f, 0.0f, 0.0f);
        this.Arm3 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Arm3.addBox(0.0f, 0.0f, 0.0f, 4, 16, 2);
        this.Arm3.setRotationPoint(4.0f, 8.0f, 3.0f);
        this.Arm3.setTextureSize(64, 64);
        this.Arm3.mirror = true;
        this.setRotation(this.Arm3, 0.0f, 0.0f, 0.0f);
        this.Arm4 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Arm4.addBox(0.0f, 0.0f, 0.0f, 4, 19, 2);
        this.Arm4.setRotationPoint(-8.0f, -4.0f, 3.0f);
        this.Arm4.setTextureSize(64, 64);
        this.Arm4.mirror = true;
        this.setRotation(this.Arm4, 0.0f, 0.0f, 0.6632251f);
        this.Arm5 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Arm5.addBox(0.0f, 0.0f, 0.0f, 4, 19, 2);
        this.Arm5.setRotationPoint(6.0f, -3.0f, 3.0f);
        this.Arm5.setTextureSize(64, 64);
        this.Arm5.mirror = true;
        this.setRotation(this.Arm5, 0.0f, 0.0f, -1.256637f);
        this.Arm6 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Arm6.addBox(0.0f, 0.0f, 0.0f, 4, 19, 2);
        this.Arm6.setRotationPoint(4.0f, -3.0f, 3.0f);
        this.Arm6.setTextureSize(64, 64);
        this.Arm6.mirror = true;
        this.setRotation(this.Arm6, 0.0f, 0.0f, -0.6632251f);
    }

    public void render(float f5) {
        this.Box.render(f5);
        this.Arm1.render(f5);
        this.Arm2.render(f5);
        this.Arm3.render(f5);
        this.Arm4.render(f5);
        this.Arm5.render(f5);
        this.Arm6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity f6) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, f6);
    }
}

