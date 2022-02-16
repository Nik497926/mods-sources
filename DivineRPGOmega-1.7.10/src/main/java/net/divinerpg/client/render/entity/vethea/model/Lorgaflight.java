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

public class Lorgaflight
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;

    public Lorgaflight() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, -3.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 21, 16);
        this.body.addBox(-1.0f, 3.0f, 5.0f, 10, 4, 4);
        this.body.setRotationPoint(-4.0f, 7.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.6981317f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 2, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 1.0f, 3.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 1.047198f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 2, 12, 4);
        this.leftarm.setRotationPoint(7.0f, -1.0f, 3.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, -1.047198f);
        this.body2 = new ModelRenderer((ModelBase)this, 26, 0);
        this.body2.addBox(-1.0f, 0.0f, -2.0f, 2, 2, 2);
        this.body2.setRotationPoint(2.0f, -3.0f, 1.0f);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.6981317f, 0.0f, 0.0f);
        this.body2.mirror = false;
        this.body3 = new ModelRenderer((ModelBase)this, 55, 0);
        this.body3.addBox(-1.0f, 0.0f, -2.0f, 2, 12, 2);
        this.body3.setRotationPoint(0.0f, -4.0f, 1.0f);
        this.body3.setTextureSize(64, 32);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.6981317f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer((ModelBase)this, 26, 0);
        this.body4.addBox(-1.0f, 0.0f, -2.0f, 2, 2, 2);
        this.body4.setRotationPoint(-2.0f, -3.0f, 1.0f);
        this.body4.setTextureSize(64, 32);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.6981317f, 0.0f, 0.0f);
        this.body5 = new ModelRenderer((ModelBase)this, 36, 0);
        this.body5.addBox(-1.0f, 0.0f, 1.0f, 5, 2, 2);
        this.body5.setRotationPoint(-5.0f, 1.0f, 1.0f);
        this.body5.setTextureSize(64, 32);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.6981317f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer((ModelBase)this, 36, 0);
        this.body6.addBox(-1.0f, 0.0f, 1.0f, 5, 2, 2);
        this.body6.setRotationPoint(2.0f, 1.0f, 1.0f);
        this.body6.setTextureSize(64, 32);
        this.body6.mirror = true;
        this.setRotation(this.body6, 0.6981317f, 0.0f, 0.0f);
        this.body7 = new ModelRenderer((ModelBase)this, 26, 0);
        this.body7.addBox(-1.0f, 0.0f, 4.0f, 2, 2, 2);
        this.body7.setRotationPoint(-2.0f, 5.0f, 1.0f);
        this.body7.setTextureSize(64, 32);
        this.body7.mirror = true;
        this.setRotation(this.body7, 0.6981317f, 0.0f, 0.0f);
        this.body8 = new ModelRenderer((ModelBase)this, 26, 0);
        this.body8.addBox(-1.0f, 0.0f, 4.0f, 2, 2, 2);
        this.body8.setRotationPoint(2.0f, 5.0f, 1.0f);
        this.body8.setTextureSize(64, 32);
        this.body8.mirror = true;
        this.setRotation(this.body8, 0.6981317f, 0.0f, 0.0f);
        this.body8.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.body4.render(f5);
        this.body5.render(f5);
        this.body6.render(f5);
        this.body7.render(f5);
        this.body8.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

