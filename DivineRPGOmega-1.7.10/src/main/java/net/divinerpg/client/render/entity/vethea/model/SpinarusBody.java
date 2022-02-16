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

public class SpinarusBody
extends ModelBase {
    ModelRenderer part1;
    ModelRenderer part2;
    ModelRenderer part3;
    ModelRenderer part4;
    ModelRenderer part5;
    ModelRenderer part6;
    ModelRenderer part7;
    ModelRenderer part8;
    ModelRenderer part9;
    ModelRenderer part10;
    ModelRenderer part11;
    ModelRenderer part12;
    ModelRenderer part13;
    ModelRenderer part14;
    ModelRenderer part15;
    ModelRenderer part16;
    ModelRenderer part17;
    ModelRenderer part18;
    ModelRenderer part19;

    public SpinarusBody() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.part1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part1.addBox(-1.0f, -1.0f, -14.0f, 1, 4, 10);
        this.part1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part1.setTextureSize(64, 32);
        this.part1.mirror = true;
        this.setRotation(this.part1, 0.7853982f, 0.0f, -1.570796f);
        this.part2 = new ModelRenderer((ModelBase)this, 0, 14);
        this.part2.addBox(-4.0f, -1.0f, 0.0f, 8, 2, 1);
        this.part2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part2.setTextureSize(64, 32);
        this.part2.mirror = true;
        this.setRotation(this.part2, 0.0f, 0.0f, 0.0f);
        this.part3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part3.addBox(-5.0f, -2.0f, 0.0f, 10, 4, 10);
        this.part3.setRotationPoint(0.0f, 0.0f, 1.0f);
        this.part3.setTextureSize(64, 32);
        this.part3.mirror = true;
        this.setRotation(this.part3, 0.0f, 0.0f, 0.0f);
        this.part4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part4.addBox(-5.0f, -2.0f, -10.0f, 10, 4, 10);
        this.part4.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part4.setTextureSize(64, 32);
        this.part4.mirror = true;
        this.setRotation(this.part4, 0.0f, 0.0f, 0.0f);
        this.part5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part5.addBox(-6.0f, -9.0f, -3.0f, 1, 4, 10);
        this.part5.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part5.setTextureSize(64, 32);
        this.part5.mirror = true;
        this.setRotation(this.part5, -0.7853982f, 0.0f, 0.0f);
        this.part6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part6.addBox(-1.0f, 6.0f, -7.0f, 1, 4, 10);
        this.part6.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part6.setTextureSize(64, 32);
        this.part6.mirror = true;
        this.setRotation(this.part6, 0.7853982f, 0.0f, -1.570796f);
        this.part7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part7.addBox(-6.0f, -2.0f, -10.0f, 1, 4, 10);
        this.part7.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part7.setTextureSize(64, 32);
        this.part7.mirror = true;
        this.setRotation(this.part7, -0.7853982f, 0.0f, 0.0f);
        this.part8 = new ModelRenderer((ModelBase)this, 13, 19);
        this.part8.addBox(6.0f, -9.0f, 2.0f, 1, 4, 5);
        this.part8.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part8.setTextureSize(64, 32);
        this.part8.mirror = true;
        this.setRotation(this.part8, -0.7853982f, 0.0f, 0.0f);
        this.part9 = new ModelRenderer((ModelBase)this, 13, 19);
        this.part9.addBox(6.0f, -2.0f, -5.0f, 1, 4, 5);
        this.part9.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part9.setTextureSize(64, 32);
        this.part9.mirror = true;
        this.setRotation(this.part9, -0.7853982f, 0.0f, 0.0f);
        this.part10 = new ModelRenderer((ModelBase)this, 13, 19);
        this.part10.addBox(-7.0f, -9.0f, 2.0f, 1, 4, 5);
        this.part10.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part10.setTextureSize(64, 32);
        this.part10.mirror = true;
        this.setRotation(this.part10, -0.7853982f, 0.0f, 0.0f);
        this.part11 = new ModelRenderer((ModelBase)this, 13, 19);
        this.part11.addBox(-7.0f, -2.0f, -5.0f, 1, 4, 5);
        this.part11.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part11.setTextureSize(64, 32);
        this.part11.mirror = true;
        this.setRotation(this.part11, -0.7853982f, 0.0f, 0.0f);
        this.part12 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part12.addBox(-1.0f, -10.0f, -7.0f, 1, 4, 10);
        this.part12.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part12.setTextureSize(64, 32);
        this.part12.mirror = true;
        this.setRotation(this.part12, -0.7853982f, 0.0f, -1.570796f);
        this.part13 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part13.addBox(-1.0f, -3.0f, -14.0f, 1, 4, 10);
        this.part13.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part13.setTextureSize(64, 32);
        this.part13.mirror = true;
        this.setRotation(this.part13, -0.7853982f, 0.0f, -1.570796f);
        this.part14 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part14.addBox(5.0f, -2.0f, -10.0f, 1, 4, 10);
        this.part14.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part14.setTextureSize(64, 32);
        this.part14.mirror = true;
        this.setRotation(this.part14, -0.7853982f, 0.0f, 0.0f);
        this.part15 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part15.addBox(5.0f, -9.0f, -3.0f, 1, 4, 10);
        this.part15.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part15.setTextureSize(64, 32);
        this.part15.mirror = true;
        this.setRotation(this.part15, -0.7853982f, 0.0f, 0.0f);
        this.part16 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part16.addBox(-8.0f, -9.0f, -3.0f, 1, 4, 10);
        this.part16.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part16.setTextureSize(64, 32);
        this.part16.mirror = true;
        this.setRotation(this.part16, -0.7853982f, 0.0f, 0.0f);
        this.part17 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part17.addBox(-8.0f, -2.0f, -10.0f, 1, 4, 10);
        this.part17.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part17.setTextureSize(64, 32);
        this.part17.mirror = true;
        this.setRotation(this.part17, -0.7853982f, 0.0f, 0.0f);
        this.part18 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part18.addBox(7.0f, -9.0f, -3.0f, 1, 4, 10);
        this.part18.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part18.setTextureSize(64, 32);
        this.part18.mirror = true;
        this.setRotation(this.part18, -0.7853982f, 0.0f, 0.0f);
        this.part19 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part19.addBox(7.0f, -2.0f, -10.0f, 1, 4, 10);
        this.part19.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part19.setTextureSize(64, 32);
        this.part19.mirror = true;
        this.setRotation(this.part19, -0.7853982f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.part1.render(f5);
        this.part2.render(f5);
        this.part3.render(f5);
        this.part4.render(f5);
        this.part5.render(f5);
        this.part6.render(f5);
        this.part7.render(f5);
        this.part8.render(f5);
        this.part9.render(f5);
        this.part10.render(f5);
        this.part11.render(f5);
        this.part12.render(f5);
        this.part13.render(f5);
        this.part14.render(f5);
        this.part15.render(f5);
        this.part16.render(f5);
        this.part17.render(f5);
        this.part18.render(f5);
        this.part19.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

