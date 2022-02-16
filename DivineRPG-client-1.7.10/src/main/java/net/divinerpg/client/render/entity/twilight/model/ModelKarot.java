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

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelKarot
extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Tail;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Ear3;
    ModelRenderer Ear4;

    public ModelKarot() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.addBox(-3.0f, -3.0f, -2.0f, 6, 6, 4);
        this.Head.setRotationPoint(-1.0f, 13.5f, -7.0f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 18, 14);
        this.Body.addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6);
        this.Body.setRotationPoint(0.0f, 14.0f, -3.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 1.5707964f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg1.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg1.setRotationPoint(-2.5f, 16.0f, 3.0f);
        this.Leg1.setTextureSize(64, 32);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg2.setRotationPoint(0.5f, 16.0f, 3.0f);
        this.Leg2.setTextureSize(64, 32);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg3.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg3.setRotationPoint(-2.5f, 16.0f, -4.0f);
        this.Leg3.setTextureSize(64, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg4.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg4.setRotationPoint(0.5f, 16.0f, -4.0f);
        this.Leg4.setTextureSize(64, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear1.addBox(-3.0f, -5.0f, 0.0f, 2, 2, 1);
        this.Ear1.setRotationPoint(-1.0f, 13.5f, -7.0f);
        this.Ear1.setTextureSize(64, 32);
        this.Ear1.mirror = true;
        this.setRotation(this.Ear1, 0.0f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear2.addBox(1.0f, -5.0f, 0.0f, 2, 2, 1);
        this.Ear2.setRotationPoint(-1.0f, 13.5f, -7.0f);
        this.Ear2.setTextureSize(64, 32);
        this.Ear2.mirror = true;
        this.setRotation(this.Ear2, 0.0f, 0.0f, 0.0f);
        this.Tail = new ModelRenderer((ModelBase)this, 6, 0);
        this.Tail.addBox(0.0f, 0.0f, 0.0f, 2, 2, 1);
        this.Tail.setRotationPoint(-2.0f, 13.0f, 4.0f);
        this.Tail.setTextureSize(64, 32);
        this.Tail.mirror = true;
        this.setRotation(this.Tail, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 50, 18);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape1.setRotationPoint(0.0f, 9.0f, 0.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 50, 18);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape2.setRotationPoint(-4.0f, 9.0f, 0.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 50, 18);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape3.setRotationPoint(0.0f, 9.0f, -4.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 50, 18);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape4.setRotationPoint(-4.0f, 9.0f, -4.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Ear3 = new ModelRenderer((ModelBase)this, 16, 10);
        this.Ear3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 6);
        this.Ear3.setRotationPoint(-6.0f, 11.0f, -13.0f);
        this.Ear3.setTextureSize(64, 32);
        this.Ear3.mirror = true;
        this.setRotation(this.Ear3, 0.0f, 0.0f, 0.0f);
        this.Ear4 = new ModelRenderer((ModelBase)this, 16, 10);
        this.Ear4.addBox(0.0f, 0.0f, 0.0f, 2, 2, 6);
        this.Ear4.setRotationPoint(2.0f, 11.0f, -13.0f);
        this.Ear4.setTextureSize(64, 32);
        this.Ear4.mirror = true;
        this.setRotation(this.Ear4, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.Head.render(var7);
        this.Body.render(var7);
        this.Leg1.render(var7);
        this.Leg2.render(var7);
        this.Leg3.render(var7);
        this.Leg4.render(var7);
        this.Ear1.render(var7);
        this.Ear2.render(var7);
        this.Tail.render(var7);
        this.Shape1.render(var7);
        this.Shape2.render(var7);
        this.Shape3.render(var7);
        this.Shape4.render(var7);
        this.Ear3.render(var7);
        this.Ear4.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.Body.rotateAngleX = 1.5707964f;
        this.Leg1.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.Leg2.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.Leg3.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.Leg4.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
    }
}

