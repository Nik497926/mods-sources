/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.divinerpg.entities.twilight.EntityMoonWolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelMoonWolf
extends ModelBase {
    ModelRenderer WolfHead;
    ModelRenderer Body;
    ModelRenderer Mane;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
    ModelRenderer Ear4;
    ModelRenderer Ear3;
    ModelRenderer spike1;
    ModelRenderer spike2;
    ModelRenderer spike3;
    ModelRenderer spike4;
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    public ModelMoonWolf() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.WolfHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.WolfHead.addBox(-3.0f, -3.0f, -2.0f, 6, 6, 4);
        this.WolfHead.setRotationPoint(-1.0f, 13.5f, -7.0f);
        this.WolfHead.setTextureSize(64, 32);
        this.WolfHead.mirror = true;
        this.setRotation(this.WolfHead, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 18, 14);
        this.Body.addBox(-4.0f, -3.0f, -3.0f, 6, 9, 6);
        this.Body.setRotationPoint(0.0f, 14.0f, 2.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 1.570796f, 0.0f, 0.0f);
        this.Mane = new ModelRenderer((ModelBase)this, 21, 0);
        this.Mane.addBox(-4.0f, -3.0f, -3.0f, 8, 6, 7);
        this.Mane.setRotationPoint(-1.0f, 14.0f, -3.0f);
        this.Mane.setTextureSize(64, 32);
        this.Mane.mirror = true;
        this.setRotation(this.Mane, 1.570796f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg1.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg1.setRotationPoint(-2.5f, 16.0f, 7.0f);
        this.Leg1.setTextureSize(64, 32);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg2.addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Leg2.setRotationPoint(0.5f, 16.0f, 7.0f);
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
        this.Nose = new ModelRenderer((ModelBase)this, 0, 10);
        this.Nose.addBox(-2.0f, 0.0f, -5.0f, 3, 3, 4);
        this.Nose.setRotationPoint(-0.5f, 13.5f, -7.0f);
        this.Nose.setTextureSize(64, 32);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Ear4 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear4.addBox(0.0f, 0.0f, 0.0f, 2, 2, 1);
        this.Ear4.setRotationPoint(0.0f, 8.0f, -5.0f);
        this.Ear4.setTextureSize(64, 32);
        this.Ear4.mirror = true;
        this.setRotation(this.Ear4, 0.0f, 0.0f, 0.0f);
        this.Ear3 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 1);
        this.Ear3.setRotationPoint(-4.0f, 8.0f, -5.0f);
        this.Ear3.setTextureSize(64, 32);
        this.Ear3.mirror = true;
        this.setRotation(this.Ear3, 0.0f, 0.0f, 0.0f);
        this.spike1 = new ModelRenderer((ModelBase)this, 37, 0);
        this.spike1.addBox(13.0f, 0.0f, -1.0f, 2, 6, 1);
        this.spike1.setRotationPoint(0.0f, 14.0f, 2.0f);
        this.spike1.setTextureSize(64, 32);
        this.spike1.mirror = true;
        this.setRotation(this.spike1, 0.7853982f, 0.0f, 4.712389f);
        this.spike2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.spike2.addBox(8.0f, 0.0f, -1.0f, 2, 6, 1);
        this.spike2.setRotationPoint(0.0f, 14.0f, 2.0f);
        this.spike2.setTextureSize(64, 32);
        this.spike2.mirror = true;
        this.setRotation(this.spike2, 0.7853982f, 0.0f, 4.712389f);
        this.spike3 = new ModelRenderer((ModelBase)this, 37, 0);
        this.spike3.addBox(-15.0f, 0.5f, -1.5f, 2, 6, 1);
        this.spike3.setRotationPoint(-1.0f, 14.0f, 2.0f);
        this.spike3.setTextureSize(64, 32);
        this.spike3.mirror = true;
        this.setRotation(this.spike3, 0.7853982f, 0.0f, 1.5707964f);
        this.spike4 = new ModelRenderer((ModelBase)this, 37, 0);
        this.spike4.addBox(-10.0f, 0.5f, -1.5f, 2, 6, 1);
        this.spike4.setRotationPoint(-1.0f, 14.0f, 2.0f);
        this.spike4.setTextureSize(64, 32);
        this.spike4.mirror = true;
        this.setRotation(this.spike4, 0.7853982f, 0.0f, 1.5707964f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 4, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 4);
        this.Shape1.setRotationPoint(1.0f, 11.0f, -12.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 4, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 4);
        this.Shape2.setRotationPoint(-4.0f, 11.0f, -12.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Body.addChild(this.spike1);
        this.Body.addChild(this.spike2);
        this.Body.addChild(this.spike3);
        this.Body.addChild(this.spike4);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.WolfHead.render(f5);
        this.Body.render(f5);
        this.Mane.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Leg3.render(f5);
        this.Leg4.render(f5);
        this.Ear1.render(f5);
        this.Ear2.render(f5);
        this.Nose.render(f5);
        this.Ear4.render(f5);
        this.Ear3.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
        EntityMoonWolf var5 = (EntityMoonWolf)var1;
        if (var5.isSitting()) {
            this.Mane.setRotationPoint(-1.0f, 16.0f, -3.0f);
            this.Mane.rotateAngleX = 1.2566371f;
            this.Mane.rotateAngleY = 0.0f;
            this.Body.setRotationPoint(0.0f, 18.0f, 0.0f);
            this.Body.rotateAngleX = 0.7853982f;
            this.Leg1.setRotationPoint(-2.5f, 22.0f, 2.0f);
            this.Leg1.rotateAngleX = 4.712389f;
            this.Leg2.setRotationPoint(0.5f, 22.0f, 2.0f);
            this.Leg2.rotateAngleX = 4.712389f;
            this.Leg3.rotateAngleX = 5.811947f;
            this.Leg3.setRotationPoint(-2.49f, 17.0f, -4.0f);
            this.Leg4.rotateAngleX = 5.811947f;
            this.Leg4.setRotationPoint(0.51f, 17.0f, -4.0f);
        } else {
            this.Body.setRotationPoint(0.0f, 14.0f, 2.0f);
            this.Body.rotateAngleX = 1.5707964f;
            this.Mane.setRotationPoint(-1.0f, 14.0f, -3.0f);
            this.Mane.rotateAngleX = this.Body.rotateAngleX;
            this.Leg1.setRotationPoint(-2.5f, 16.0f, 7.0f);
            this.Leg2.setRotationPoint(0.5f, 16.0f, 7.0f);
            this.Leg3.setRotationPoint(-2.5f, 16.0f, -4.0f);
            this.Leg4.setRotationPoint(0.5f, 16.0f, -4.0f);
            this.Leg1.rotateAngleX = MathHelper.cos((float)(var2 * 0.6662f)) * 1.4f * var3;
            this.Leg2.rotateAngleX = MathHelper.cos((float)(var2 * 0.6662f + (float)Math.PI)) * 1.4f * var3;
            this.Leg3.rotateAngleX = MathHelper.cos((float)(var2 * 0.6662f + (float)Math.PI)) * 1.4f * var3;
            this.Leg4.rotateAngleX = MathHelper.cos((float)(var2 * 0.6662f)) * 1.4f * var3;
        }
        this.WolfHead.rotateAngleZ = var5.getInterestedAngle(var4) + var5.getShakeAngle(var4, 0.0f);
        this.Mane.rotateAngleZ = var5.getShakeAngle(var4, -0.08f);
        this.Body.rotateAngleZ = var5.getShakeAngle(var4, -0.16f);
        if (var5.getWolfShaking()) {
            float var6 = var5.getBrightness(var4) * var5.getShadingWhileShaking(var4);
            GL11.glColor3f((float)var6, (float)var6, (float)var6);
        }
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.WolfHead.rotateAngleX = var5 / 57.295776f;
        this.WolfHead.rotateAngleY = var4 / 57.295776f;
        this.Nose.rotateAngleX = var5 / 57.295776f;
        this.Nose.rotateAngleY = var4 / 57.295776f;
        this.Ear1.rotateAngleX = var5 / 57.295776f;
        this.Ear1.rotateAngleY = var4 / 57.295776f;
        this.Ear2.rotateAngleX = var5 / 57.295776f;
        this.Ear2.rotateAngleY = var4 / 57.295776f;
        this.Ear3.rotateAngleX = var5 / 57.295776f;
        this.Ear3.rotateAngleY = var4 / 57.295776f;
        this.Ear4.rotateAngleX = var5 / 57.295776f;
        this.Ear4.rotateAngleY = var4 / 57.295776f;
    }
}

