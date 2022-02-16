/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.arcana.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDeathHound
extends ModelBase {
    ModelRenderer WolfHead;
    ModelRenderer Body;
    ModelRenderer Mane;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Tail;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
    ModelRenderer Tail2;
    ModelRenderer Leg4;
    ModelRenderer Leg5;

    public ModelDeathHound() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.WolfHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.WolfHead.addBox(-3.0f, -3.0f, -2.0f, 6, 6, 6);
        this.WolfHead.setRotationPoint(-1.0f, 13.5f, -10.0f);
        this.WolfHead.setTextureSize(64, 32);
        this.WolfHead.mirror = true;
        this.setRotation(this.WolfHead, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 18, 14);
        this.Body.addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6);
        this.Body.setRotationPoint(0.0f, 14.0f, 2.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 1.5707964f, 0.0f, 0.0f);
        this.Mane = new ModelRenderer((ModelBase)this, 21, 0);
        this.Mane.addBox(-4.0f, -3.0f, -3.0f, 8, 9, 7);
        this.Mane.setRotationPoint(-1.0f, 14.0f, -3.0f);
        this.Mane.setTextureSize(64, 32);
        this.Mane.mirror = true;
        this.setRotation(this.Mane, 1.5707964f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg1.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg1.setRotationPoint(-2.5f, 16.0f, -1.0f);
        this.Leg1.setTextureSize(64, 32);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg2.setRotationPoint(0.5f, 16.0f, -1.0f);
        this.Leg2.setTextureSize(64, 32);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg3.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg3.setRotationPoint(-1.0f, 16.0f, -7.0f);
        this.Leg3.setTextureSize(64, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Tail = new ModelRenderer((ModelBase)this, 9, 18);
        this.Tail.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Tail.setRotationPoint(-3.0f, 12.0f, 8.0f);
        this.Tail.setTextureSize(64, 32);
        this.Tail.mirror = true;
        this.setRotation(this.Tail, 1.130069f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer((ModelBase)this, 43, 16);
        this.Ear1.addBox(-3.0f, -5.0f, 0.0f, 2, 4, 1);
        this.Ear1.setRotationPoint(-1.0f, 11.5f, -7.0f);
        this.Ear1.setTextureSize(64, 32);
        this.Ear1.mirror = true;
        this.setRotation(this.Ear1, 0.0f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer((ModelBase)this, 43, 16);
        this.Ear2.addBox(1.0f, -5.0f, 0.0f, 2, 4, 1);
        this.Ear2.setRotationPoint(-1.0f, 11.5f, -7.0f);
        this.Ear2.setTextureSize(64, 32);
        this.Ear2.mirror = true;
        this.setRotation(this.Ear2, 0.0f, 0.0f, 0.0f);
        this.Nose = new ModelRenderer((ModelBase)this, 0, 19);
        this.Nose.addBox(-2.0f, 0.0f, -5.0f, 3, 3, 4);
        this.Nose.setRotationPoint(-0.5f, 13.5f, -11.0f);
        this.Nose.setTextureSize(64, 32);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer((ModelBase)this, 9, 18);
        this.Tail2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Tail2.setRotationPoint(1.0f, 12.0f, 8.0f);
        this.Tail2.setTextureSize(64, 32);
        this.Tail2.mirror = true;
        this.setRotation(this.Tail2, 1.130069f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg4.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg4.setRotationPoint(0.5f, 16.0f, 7.0f);
        this.Leg4.setTextureSize(64, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Leg5 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg5.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg5.setRotationPoint(-2.5f, 16.0f, 7.0f);
        this.Leg5.setTextureSize(64, 32);
        this.Leg5.mirror = true;
        this.setRotation(this.Leg5, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.WolfHead.render(f5);
        this.Body.render(f5);
        this.Mane.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Leg3.render(f5);
        this.Tail.render(f5);
        this.Ear1.render(f5);
        this.Ear2.render(f5);
        this.Nose.render(f5);
        this.Tail2.render(f5);
        this.Leg4.render(f5);
        this.Leg5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par6) {
        this.Leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.Leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.Leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.Leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
    }
}

