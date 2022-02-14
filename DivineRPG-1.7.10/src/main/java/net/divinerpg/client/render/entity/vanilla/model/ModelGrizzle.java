/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGrizzle
extends ModelBase {
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg4;
    ModelRenderer leg3;
    ModelRenderer body;
    ModelRenderer head;
    ModelRenderer snout;
    ModelRenderer ear2;
    ModelRenderer ear1;
    ModelRenderer tail;

    public ModelGrizzle() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg1.addBox(-1.9999f, 0.0f, -2.0f, 4, 10, 4);
        this.leg1.setRotationPoint(-4.0f, 14.0f, -8.0f);
        this.leg1.setTextureSize(128, 128);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg2.addBox(-1.9999f, 0.0f, -2.0f, 4, 8, 4);
        this.leg2.setRotationPoint(-4.0f, 16.0f, 8.0f);
        this.leg2.setTextureSize(128, 128);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg4.addBox(-2.0001f, 0.0f, -2.0f, 4, 8, 4);
        this.leg4.setRotationPoint(4.0f, 16.0f, 8.0f);
        this.leg4.setTextureSize(128, 128);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg3.addBox(-2.0001f, 0.0f, -2.0f, 4, 10, 4);
        this.leg3.setRotationPoint(4.0f, 14.0f, -8.0f);
        this.leg3.setTextureSize(128, 128);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 12, 10, 22);
        this.body.setRotationPoint(-6.0f, 4.5f, -11.0f);
        this.body.setTextureSize(128, 128);
        this.body.mirror = true;
        this.setRotation(this.body, -0.0872665f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 32);
        this.head.addBox(-4.0f, -3.0f, -5.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, 5.0f, -11.0f);
        this.head.setTextureSize(128, 128);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.snout = new ModelRenderer((ModelBase)this, 0, 46);
        this.snout.addBox(-2.0f, 1.0f, -9.0f, 4, 4, 4);
        this.snout.setRotationPoint(0.0f, 5.0f, -11.0f);
        this.snout.setTextureSize(128, 128);
        this.snout.mirror = true;
        this.setRotation(this.snout, 0.0f, 0.0f, 0.0f);
        this.ear2 = new ModelRenderer((ModelBase)this, 0, 14);
        this.ear2.addBox(3.0f, -4.0f, -1.0f, 2, 2, 1);
        this.ear2.setRotationPoint(0.0f, 5.0f, -11.0f);
        this.ear2.setTextureSize(128, 128);
        this.ear2.mirror = true;
        this.setRotation(this.ear2, 0.0f, 0.0f, 0.0f);
        this.ear1 = new ModelRenderer((ModelBase)this, 0, 14);
        this.ear1.addBox(-5.0f, -4.0f, -1.0f, 2, 2, 1);
        this.ear1.setRotationPoint(0.0f, 5.0f, -11.0f);
        this.ear1.setTextureSize(128, 128);
        this.ear1.mirror = true;
        this.setRotation(this.ear1, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 0, 26);
        this.tail.addBox(5.0f, 4.0f, 21.54f, 2, 2, 2);
        this.tail.setRotationPoint(-6.0f, 4.5f, -11.0f);
        this.tail.setTextureSize(128, 128);
        this.tail.mirror = true;
        this.setRotation(this.tail, -0.0872665f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg4.render(f5);
        this.leg3.render(f5);
        this.body.render(f5);
        this.head.render(f5);
        this.snout.render(f5);
        this.ear2.render(f5);
        this.ear1.render(f5);
        this.tail.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * f1;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * f1;
        this.ear2.rotateAngleX = this.snout.rotateAngleX = f4 / 57.295776f;
        this.ear1.rotateAngleX = this.snout.rotateAngleX;
        this.head.rotateAngleX = this.snout.rotateAngleX;
        this.ear2.rotateAngleY = this.snout.rotateAngleY = f3 / 57.295776f;
        this.ear1.rotateAngleY = this.snout.rotateAngleY;
        this.head.rotateAngleY = this.snout.rotateAngleY;
    }
}

