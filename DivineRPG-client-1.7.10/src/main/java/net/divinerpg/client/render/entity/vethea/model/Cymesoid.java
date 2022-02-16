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

public class Cymesoid
extends ModelBase {
    ModelRenderer head;
    ModelRenderer bodyext1;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer body;
    ModelRenderer bodyext2;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;

    public Cymesoid() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.bodyext1 = new ModelRenderer((ModelBase)this, 20, 22);
        this.bodyext1.addBox(-12.0f, 0.0f, 2.0f, 8, 6, 2);
        this.bodyext1.setRotationPoint(4.0f, 8.0f, 0.0f);
        this.bodyext1.setTextureSize(64, 32);
        this.bodyext1.mirror = true;
        this.setRotation(this.bodyext1, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.bodyext2 = new ModelRenderer((ModelBase)this, 19, 22);
        this.bodyext2.addBox(-4.0f, 0.0f, 2.0f, 8, 6, 2);
        this.bodyext2.setRotationPoint(4.0f, 8.0f, 0.0f);
        this.bodyext2.setTextureSize(64, 32);
        this.bodyext2.mirror = true;
        this.setRotation(this.bodyext2, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape1.setRotationPoint(1.0f, 0.0f, 2.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, -2.094395f, -0.7853982f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape2.setRotationPoint(1.0f, 0.0f, 2.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, -2.356194f, -0.6108652f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape3.setRotationPoint(1.0f, 0.0f, 2.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, -2.617994f, -0.4363323f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape4.addBox(2.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape4.setRotationPoint(2.0f, 0.0f, 2.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, -2.96706f, -0.2617994f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape5.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape5.setRotationPoint(2.0f, 0.0f, 2.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0f, -0.2617994f, -0.2617994f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape6.setRotationPoint(0.0f, 0.0f, 2.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0f, -0.5235988f, -0.4363323f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape7.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape7.setRotationPoint(-1.0f, 0.0f, 2.0f);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0f, -0.7853982f, -0.6108652f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Shape8.addBox(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Shape8.setRotationPoint(-1.0f, 0.0f, 2.0f);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0f, -1.047198f, -0.7853982f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.bodyext1.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.body.render(f5);
        this.bodyext2.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f + 0.0872665f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f + 0.0872665f;
    }
}

