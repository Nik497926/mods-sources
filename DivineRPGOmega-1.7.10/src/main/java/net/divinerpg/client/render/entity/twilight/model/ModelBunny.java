/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.divinerpg.entities.twilight.EntityBunny;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBunny
extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer tail;

    public ModelBunny() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.addBox(-3.0f, -3.0f, -2.0f, 6, 6, 4);
        this.Head.setRotationPoint(-1.0f, 16.5f, -7.0f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 18, 14);
        this.Body.addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6);
        this.Body.setRotationPoint(0.0f, 17.0f, -3.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 1.570796f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg1.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Leg1.setRotationPoint(-2.5f, 19.0f, 3.0f);
        this.Leg1.setTextureSize(64, 32);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg2.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Leg2.setRotationPoint(0.5f, 19.0f, 3.0f);
        this.Leg2.setTextureSize(64, 32);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg3.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Leg3.setRotationPoint(-2.5f, 19.0f, -4.0f);
        this.Leg3.setTextureSize(64, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg4.addBox(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.Leg4.setRotationPoint(0.5f, 19.0f, -4.0f);
        this.Leg4.setTextureSize(64, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear1.addBox(-3.0f, -5.0f, 0.0f, 2, 2, 1);
        this.Ear1.setRotationPoint(-1.0f, 16.5f, -7.0f);
        this.Ear1.setTextureSize(64, 32);
        this.Ear1.mirror = true;
        this.setRotation(this.Ear1, 0.0f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear2.addBox(1.0f, -5.0f, 0.0f, 2, 2, 1);
        this.Ear2.setRotationPoint(-1.0f, 16.5f, -7.0f);
        this.Ear2.setTextureSize(64, 32);
        this.Ear2.mirror = true;
        this.setRotation(this.Ear2, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 6, 0);
        this.tail.addBox(0.0f, -5.0f, 8.0f, 2, 2, 1);
        this.tail.setRotationPoint(-2.0f, 16.0f, 4.0f);
        this.tail.setTextureSize(64, 32);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
        this.Body.addChild(this.tail);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Head.render(f5);
        this.Body.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Leg3.render(f5);
        this.Leg4.render(f5);
        this.Ear1.render(f5);
        this.Ear2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        if (e instanceof EntityBunny && ((EntityBunny)e).isSitting()) {
            this.Body.rotateAngleX = 0.95993f;
            this.Leg2.rotateAngleX = -1.5708f;
            this.Leg1.rotateAngleX = -1.5708f;
            this.Leg4.rotateAngleX = -0.2818f;
            this.Leg3.rotateAngleX = -0.2818f;
            this.Leg2.rotationPointY = 23.0f;
            this.Leg1.rotationPointY = 23.0f;
            this.Leg2.rotationPointZ = 2.0f;
            this.Leg1.rotationPointZ = 2.0f;
        } else {
            this.Leg2.rotationPointY = 19.0f;
            this.Leg1.rotationPointY = 19.0f;
            this.Leg2.rotationPointZ = 3.0f;
            this.Leg1.rotationPointZ = 3.0f;
            this.Body.rotateAngleX = 1.5707964f;
            this.Leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
            this.Leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
            this.Leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
            this.Leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        }
        this.Head.rotateAngleX = f4 / 57.295776f;
        this.Head.rotateAngleY = f3 / 57.295776f;
        this.Ear1.rotateAngleX = f4 / 57.295776f;
        this.Ear1.rotateAngleY = f3 / 57.295776f;
        this.Ear2.rotateAngleX = f4 / 57.295776f;
        this.Ear2.rotateAngleY = f3 / 57.295776f;
        this.tail.rotateAngleX = 1.5707964f;
    }
}

