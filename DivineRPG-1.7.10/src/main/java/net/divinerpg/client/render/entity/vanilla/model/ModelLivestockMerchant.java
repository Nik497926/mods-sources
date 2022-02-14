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

public class ModelLivestockMerchant
extends ModelBase {
    ModelRenderer head;
    ModelRenderer hatbottom;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer hatTop;
    ModelRenderer frontRightCorner;
    ModelRenderer frontLeftCorner;
    ModelRenderer backRightCorner;
    ModelRenderer backLeftCorner;

    public ModelLivestockMerchant() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.hatbottom = new ModelRenderer((ModelBase)this, 0, 42);
        this.hatbottom.addBox(-7.0f, -8.0f, -7.0f, 14, 1, 14);
        this.hatbottom.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.hatbottom.setTextureSize(64, 64);
        this.hatbottom.mirror = true;
        this.setRotation(this.hatbottom, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.hatTop = new ModelRenderer((ModelBase)this, 0, 32);
        this.hatTop.addBox(-4.0f, -10.0f, -4.0f, 8, 2, 8);
        this.hatTop.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.hatTop.setTextureSize(64, 64);
        this.hatTop.mirror = true;
        this.setRotation(this.hatTop, 0.0f, 0.0f, 0.0f);
        this.frontRightCorner = new ModelRenderer((ModelBase)this, 0, 62);
        this.frontRightCorner.addBox(-7.0f, -8.0f, -7.0f, 1, 1, 1);
        this.frontRightCorner.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.frontRightCorner.setTextureSize(64, 64);
        this.frontRightCorner.mirror = true;
        this.setRotation(this.frontRightCorner, 0.0f, 0.0f, 0.0f);
        this.frontLeftCorner = new ModelRenderer((ModelBase)this, 4, 62);
        this.frontLeftCorner.addBox(6.0f, -8.0f, -7.0f, 1, 1, 1);
        this.frontLeftCorner.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.frontLeftCorner.setTextureSize(64, 64);
        this.frontLeftCorner.mirror = true;
        this.setRotation(this.frontLeftCorner, 0.0f, 0.0f, 0.0f);
        this.backRightCorner = new ModelRenderer((ModelBase)this, 8, 62);
        this.backRightCorner.addBox(-7.0f, -8.0f, 6.0f, 1, 1, 1);
        this.backRightCorner.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.backRightCorner.setTextureSize(64, 64);
        this.backRightCorner.mirror = true;
        this.setRotation(this.backRightCorner, 0.0f, 0.0f, 0.0f);
        this.backLeftCorner = new ModelRenderer((ModelBase)this, 12, 62);
        this.backLeftCorner.addBox(6.0f, -8.0f, 6.0f, 1, 1, 1);
        this.backLeftCorner.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.backLeftCorner.setTextureSize(64, 64);
        this.backLeftCorner.mirror = true;
        this.setRotation(this.backLeftCorner, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.hatbottom.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.hatTop.render(f5);
        this.frontRightCorner.render(f5);
        this.frontLeftCorner.render(f5);
        this.backRightCorner.render(f5);
        this.backLeftCorner.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.frontLeftCorner.rotateAngleY = this.head.rotateAngleY = f3 / 57.295776f;
        this.backLeftCorner.rotateAngleY = this.head.rotateAngleY;
        this.backRightCorner.rotateAngleY = this.head.rotateAngleY;
        this.frontRightCorner.rotateAngleY = this.head.rotateAngleY;
        this.hatbottom.rotateAngleY = this.head.rotateAngleY;
        this.hatTop.rotateAngleY = this.head.rotateAngleY;
        this.frontLeftCorner.rotateAngleX = this.head.rotateAngleX = f4 / 57.295776f;
        this.backLeftCorner.rotateAngleX = this.head.rotateAngleX;
        this.backRightCorner.rotateAngleX = this.head.rotateAngleX;
        this.frontRightCorner.rotateAngleX = this.head.rotateAngleX;
        this.hatbottom.rotateAngleX = this.head.rotateAngleX;
        this.hatTop.rotateAngleX = this.head.rotateAngleX;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
    }
}

