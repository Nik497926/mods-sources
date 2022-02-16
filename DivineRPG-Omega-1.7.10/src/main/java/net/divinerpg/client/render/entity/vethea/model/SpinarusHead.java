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

public class SpinarusHead
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

    public SpinarusHead() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.part1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part1.addBox(-5.0f, -2.0f, -10.0f, 10, 4, 10);
        this.part1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part1.setTextureSize(64, 32);
        this.part1.mirror = true;
        this.setRotation(this.part1, 0.0f, 0.0f, 0.0f);
        this.part2 = new ModelRenderer((ModelBase)this, 0, 19);
        this.part2.addBox(-1.0f, -1.0f, 15.0f, 2, 2, 7);
        this.part2.setRotationPoint(-4.0f, 0.0f, -9.0f);
        this.part2.setTextureSize(64, 32);
        this.part2.mirror = true;
        this.setRotation(this.part2, 0.0f, 0.5235988f, 0.0f);
        this.part3 = new ModelRenderer((ModelBase)this, 0, 19);
        this.part3.addBox(-1.0f, -1.0f, 15.0f, 2, 2, 7);
        this.part3.setRotationPoint(4.0f, 0.0f, -9.0f);
        this.part3.setTextureSize(64, 32);
        this.part3.mirror = true;
        this.setRotation(this.part3, 0.0f, -0.5235988f, 0.0f);
        this.part4 = new ModelRenderer((ModelBase)this, 0, 14);
        this.part4.addBox(-1.0f, -1.0f, -4.0f, 1, 1, 4);
        this.part4.setRotationPoint(-1.0f, 0.0f, -10.0f);
        this.part4.setTextureSize(64, 32);
        this.part4.mirror = true;
        this.setRotation(this.part4, 0.0f, 0.3490659f, 0.0f);
        this.part5 = new ModelRenderer((ModelBase)this, 0, 14);
        this.part5.addBox(0.0f, -1.0f, -4.0f, 1, 1, 4);
        this.part5.setRotationPoint(1.0f, 0.0f, -10.0f);
        this.part5.setTextureSize(64, 32);
        this.part5.mirror = true;
        this.setRotation(this.part5, 0.0f, -0.3490659f, 0.0f);
        this.part6 = new ModelRenderer((ModelBase)this, 40, 0);
        this.part6.addBox(-4.0f, -1.0f, 0.0f, 8, 2, 1);
        this.part6.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.part6.setTextureSize(64, 32);
        this.part6.mirror = true;
        this.setRotation(this.part6, 0.0f, 0.0f, 0.0f);
        this.part7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part7.addBox(-5.0f, -2.0f, 0.0f, 10, 4, 10);
        this.part7.setRotationPoint(0.0f, 0.0f, 1.0f);
        this.part7.setTextureSize(64, 32);
        this.part7.mirror = true;
        this.setRotation(this.part7, 0.0f, 0.0f, 0.0f);
        this.part8 = new ModelRenderer((ModelBase)this, 0, 19);
        this.part8.addBox(-1.0f, -1.0f, -7.0f, 2, 2, 7);
        this.part8.setRotationPoint(4.0f, 0.0f, -9.0f);
        this.part8.setTextureSize(64, 32);
        this.part8.mirror = true;
        this.setRotation(this.part8, 0.0f, -0.5235988f, 0.0f);
        this.part9 = new ModelRenderer((ModelBase)this, 0, 19);
        this.part9.addBox(-1.0f, -1.0f, -7.0f, 2, 2, 7);
        this.part9.setRotationPoint(-4.0f, 0.0f, -9.0f);
        this.part9.setTextureSize(64, 32);
        this.part9.mirror = true;
        this.setRotation(this.part9, 0.0f, 0.5235988f, 0.0f);
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
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

