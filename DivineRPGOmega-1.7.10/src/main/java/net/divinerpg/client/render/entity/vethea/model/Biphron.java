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

public class Biphron
extends ModelBase {
    ModelRenderer head;
    ModelRenderer bodytop;
    ModelRenderer rightarmts1;
    ModelRenderer leftarmts1;
    ModelRenderer rightlegts1;
    ModelRenderer leftlegts1;
    ModelRenderer leftlegts2;
    ModelRenderer rightlegts2;
    ModelRenderer rightarmtspike1;
    ModelRenderer leftarmtspike1;
    ModelRenderer rightarmts2;
    ModelRenderer leftarmts2;
    ModelRenderer leftarmtspike2;
    ModelRenderer rightarmtspike2;
    ModelRenderer bodybottom;
    ModelRenderer rightarms1;
    ModelRenderer leftarms1;
    ModelRenderer leftarms2;
    ModelRenderer leftarmspike1;
    ModelRenderer leftarmspike2;
    ModelRenderer rightarmspike1;
    ModelRenderer rightarms2;
    ModelRenderer rightarmspike2;
    ModelRenderer leftlegs1;
    ModelRenderer leftlegs2;
    ModelRenderer rightlegs2;
    ModelRenderer rightlegs1;

    public Biphron() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.bodytop = new ModelRenderer((ModelBase)this, 16, 16);
        this.bodytop.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.bodytop.setRotationPoint(0.0f, -8.0f, 0.0f);
        this.bodytop.setTextureSize(64, 32);
        this.bodytop.mirror = true;
        this.setRotation(this.bodytop, -3.141593f, 0.0f, 0.0f);
        this.rightarmts1 = new ModelRenderer((ModelBase)this, 48, 12);
        this.rightarmts1.addBox(-3.0f, -2.0f, -2.0f, 4, 3, 4);
        this.rightarmts1.setRotationPoint(-5.0f, -10.0f, 0.0f);
        this.rightarmts1.setTextureSize(64, 32);
        this.rightarmts1.mirror = true;
        this.setRotation(this.rightarmts1, -3.141593f, 0.0f, 0.0f);
        this.leftarmts1 = new ModelRenderer((ModelBase)this, 48, 12);
        this.leftarmts1.addBox(7.0f, -2.0f, -2.0f, 4, 3, 4);
        this.leftarmts1.setRotationPoint(-3.0f, -10.0f, 0.0f);
        this.leftarmts1.setTextureSize(64, 32);
        this.leftarmts1.mirror = true;
        this.setRotation(this.leftarmts1, -3.141593f, 0.0f, 0.0f);
        this.rightlegts1 = new ModelRenderer((ModelBase)this, 41, 23);
        this.rightlegts1.addBox(-3.0f, -3.0f, -3.0f, 6, 3, 6);
        this.rightlegts1.setRotationPoint(-6.0f, -20.0f, 0.0f);
        this.rightlegts1.setTextureSize(64, 32);
        this.rightlegts1.mirror = true;
        this.setRotation(this.rightlegts1, -3.141593f, 0.0f, 0.0f);
        this.leftlegts1 = new ModelRenderer((ModelBase)this, 41, 23);
        this.leftlegts1.addBox(-3.0f, -3.0f, -3.0f, 6, 3, 6);
        this.leftlegts1.setRotationPoint(6.0f, -20.0f, 0.0f);
        this.leftlegts1.setTextureSize(64, 32);
        this.leftlegts1.mirror = true;
        this.setRotation(this.leftlegts1, -3.141593f, 0.0f, 0.0f);
        this.leftlegts2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegts2.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftlegts2.setRotationPoint(6.0f, -20.0f, 0.0f);
        this.leftlegts2.setTextureSize(64, 32);
        this.leftlegts2.mirror = true;
        this.setRotation(this.leftlegts2, -3.141593f, 0.0f, 0.0f);
        this.rightlegts2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegts2.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightlegts2.setRotationPoint(-6.0f, -20.0f, 0.0f);
        this.rightlegts2.setTextureSize(64, 32);
        this.rightlegts2.mirror = true;
        this.setRotation(this.rightlegts2, -3.141593f, 0.0f, 0.0f);
        this.rightarmtspike1 = new ModelRenderer((ModelBase)this, 33, 0);
        this.rightarmtspike1.addBox(-7.0f, 6.0f, 1.0f, 1, 8, 1);
        this.rightarmtspike1.setRotationPoint(-5.0f, -10.0f, 0.0f);
        this.rightarmtspike1.setTextureSize(64, 32);
        this.rightarmtspike1.mirror = true;
        this.setRotation(this.rightarmtspike1, -3.141593f, 0.0f, 0.0f);
        this.leftarmtspike1 = new ModelRenderer((ModelBase)this, 33, 0);
        this.leftarmtspike1.addBox(6.0f, 6.0f, 1.0f, 1, 8, 1);
        this.leftarmtspike1.setRotationPoint(5.0f, -10.0f, 0.0f);
        this.leftarmtspike1.setTextureSize(64, 32);
        this.leftarmtspike1.mirror = true;
        this.setRotation(this.leftarmtspike1, -3.141593f, 0.0f, 0.0f);
        this.rightarmts2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.rightarmts2.addBox(-7.0f, -2.0f, -2.0f, 4, 8, 4);
        this.rightarmts2.setRotationPoint(-5.0f, -10.0f, 0.0f);
        this.rightarmts2.setTextureSize(64, 32);
        this.rightarmts2.mirror = true;
        this.setRotation(this.rightarmts2, -3.141593f, 0.0f, 0.0f);
        this.leftarmts2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.leftarmts2.addBox(3.0f, -2.0f, -2.0f, 4, 8, 4);
        this.leftarmts2.setRotationPoint(5.0f, -10.0f, 0.0f);
        this.leftarmts2.setTextureSize(64, 32);
        this.leftarmts2.mirror = true;
        this.setRotation(this.leftarmts2, -3.141593f, 0.0f, 0.0f);
        this.leftarmtspike2 = new ModelRenderer((ModelBase)this, 33, 0);
        this.leftarmtspike2.addBox(6.0f, 6.0f, -2.0f, 1, 8, 1);
        this.leftarmtspike2.setRotationPoint(5.0f, -10.0f, 0.0f);
        this.leftarmtspike2.setTextureSize(64, 32);
        this.leftarmtspike2.mirror = true;
        this.setRotation(this.leftarmtspike2, -3.141593f, 0.0f, 0.0f);
        this.rightarmtspike2 = new ModelRenderer((ModelBase)this, 33, 0);
        this.rightarmtspike2.addBox(-7.0f, 6.0f, -2.0f, 1, 8, 1);
        this.rightarmtspike2.setRotationPoint(-5.0f, -10.0f, 0.0f);
        this.rightarmtspike2.setTextureSize(64, 32);
        this.rightarmtspike2.mirror = true;
        this.setRotation(this.rightarmtspike2, -3.141593f, 0.0f, 0.0f);
        this.bodybottom = new ModelRenderer((ModelBase)this, 16, 16);
        this.bodybottom.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.bodybottom.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodybottom.setTextureSize(64, 32);
        this.bodybottom.mirror = true;
        this.setRotation(this.bodybottom, 0.0f, 0.0f, 0.0f);
        this.rightarms1 = new ModelRenderer((ModelBase)this, 48, 12);
        this.rightarms1.addBox(-3.0f, -2.0f, -2.0f, 4, 3, 4);
        this.rightarms1.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarms1.setTextureSize(64, 32);
        this.rightarms1.mirror = true;
        this.setRotation(this.rightarms1, 0.0f, 0.0f, 0.0f);
        this.leftarms1 = new ModelRenderer((ModelBase)this, 48, 12);
        this.leftarms1.addBox(7.0f, -2.0f, -2.0f, 4, 3, 4);
        this.leftarms1.setRotationPoint(-3.0f, 2.0f, 0.0f);
        this.leftarms1.setTextureSize(64, 32);
        this.leftarms1.mirror = true;
        this.setRotation(this.leftarms1, 0.0f, 0.0f, 0.0f);
        this.leftarms2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.leftarms2.addBox(3.0f, -2.0f, -2.0f, 4, 8, 4);
        this.leftarms2.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarms2.setTextureSize(64, 32);
        this.leftarms2.mirror = true;
        this.setRotation(this.leftarms2, 0.0f, 0.0f, 0.0f);
        this.leftarmspike1 = new ModelRenderer((ModelBase)this, 33, 0);
        this.leftarmspike1.addBox(6.0f, 6.0f, 1.0f, 1, 8, 1);
        this.leftarmspike1.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarmspike1.setTextureSize(64, 32);
        this.leftarmspike1.mirror = true;
        this.setRotation(this.leftarmspike1, 0.0f, 0.0f, 0.0f);
        this.leftarmspike2 = new ModelRenderer((ModelBase)this, 33, 0);
        this.leftarmspike2.addBox(6.0f, 6.0f, -2.0f, 1, 8, 1);
        this.leftarmspike2.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarmspike2.setTextureSize(64, 32);
        this.leftarmspike2.mirror = true;
        this.setRotation(this.leftarmspike2, 0.0f, 0.0f, 0.0f);
        this.rightarmspike1 = new ModelRenderer((ModelBase)this, 33, 0);
        this.rightarmspike1.addBox(-7.0f, 6.0f, -2.0f, 1, 8, 1);
        this.rightarmspike1.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarmspike1.setTextureSize(64, 32);
        this.rightarmspike1.mirror = true;
        this.setRotation(this.rightarmspike1, 0.0f, 0.0f, 0.0f);
        this.rightarms2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.rightarms2.addBox(-7.0f, -2.0f, -2.0f, 4, 8, 4);
        this.rightarms2.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarms2.setTextureSize(64, 32);
        this.rightarms2.mirror = true;
        this.setRotation(this.rightarms2, 0.0f, 0.0f, 0.0f);
        this.rightarmspike2 = new ModelRenderer((ModelBase)this, 33, 0);
        this.rightarmspike2.addBox(-7.0f, 6.0f, 1.0f, 1, 8, 1);
        this.rightarmspike2.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarmspike2.setTextureSize(64, 32);
        this.rightarmspike2.mirror = true;
        this.setRotation(this.rightarmspike2, 0.0f, 0.0f, 0.0f);
        this.leftlegs1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegs1.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftlegs1.setRotationPoint(6.0f, 12.0f, 0.0f);
        this.leftlegs1.setTextureSize(64, 32);
        this.leftlegs1.mirror = true;
        this.setRotation(this.leftlegs1, 0.0f, 0.0f, 0.0f);
        this.leftlegs2 = new ModelRenderer((ModelBase)this, 41, 23);
        this.leftlegs2.addBox(-3.0f, -3.0f, -3.0f, 6, 3, 6);
        this.leftlegs2.setRotationPoint(6.0f, 12.0f, 0.0f);
        this.leftlegs2.setTextureSize(64, 32);
        this.leftlegs2.mirror = true;
        this.setRotation(this.leftlegs2, 0.0f, 0.0f, 0.0f);
        this.rightlegs2 = new ModelRenderer((ModelBase)this, 41, 23);
        this.rightlegs2.addBox(-3.0f, -3.0f, -3.0f, 6, 3, 6);
        this.rightlegs2.setRotationPoint(-6.0f, 12.0f, 0.0f);
        this.rightlegs2.setTextureSize(64, 32);
        this.rightlegs2.mirror = true;
        this.setRotation(this.rightlegs2, 0.0f, 0.0f, 0.0f);
        this.rightlegs1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegs1.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightlegs1.setRotationPoint(-6.0f, 12.0f, 0.0f);
        this.rightlegs1.setTextureSize(64, 32);
        this.rightlegs1.mirror = true;
        this.setRotation(this.rightlegs1, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.bodytop.render(f5);
        this.rightarmts1.render(f5);
        this.leftarmts1.render(f5);
        this.rightlegts1.render(f5);
        this.leftlegts1.render(f5);
        this.leftlegts2.render(f5);
        this.rightlegts2.render(f5);
        this.rightarmtspike1.render(f5);
        this.leftarmtspike1.render(f5);
        this.rightarmts2.render(f5);
        this.leftarmts2.render(f5);
        this.leftarmtspike2.render(f5);
        this.rightarmtspike2.render(f5);
        this.bodybottom.render(f5);
        this.rightarms1.render(f5);
        this.leftarms1.render(f5);
        this.leftarms2.render(f5);
        this.leftarmspike1.render(f5);
        this.leftarmspike2.render(f5);
        this.rightarmspike1.render(f5);
        this.rightarms2.render(f5);
        this.rightarmspike2.render(f5);
        this.leftlegs1.render(f5);
        this.leftlegs2.render(f5);
        this.rightlegs2.render(f5);
        this.rightlegs1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftlegs1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftlegs2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightlegs1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightlegs2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarms1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarms2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarmspike1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarmspike2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarms1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarms2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarmspike1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarmspike2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftlegts1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1 - (float)Math.PI;
        this.leftlegts2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1 - (float)Math.PI;
        this.rightlegts1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1 - (float)Math.PI;
        this.rightlegts2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1 - (float)Math.PI;
        this.rightarmts1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.rightarmts2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.rightarmtspike1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.rightarmtspike2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.leftarmts1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.leftarmts2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.leftarmtspike1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - (float)Math.PI;
        this.leftarmtspike2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - (float)Math.PI;
    }
}

