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

public class ModelTwilightGolem
extends ModelBase {
    ModelRenderer torso;
    ModelRenderer body;
    ModelRenderer headLeft;
    ModelRenderer headRight;
    ModelRenderer rightShoulder;
    ModelRenderer rightArm;
    ModelRenderer rightHand;
    ModelRenderer leftShoulder;
    ModelRenderer leftArm;
    ModelRenderer leftHand;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;

    public ModelTwilightGolem() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.torso = new ModelRenderer((ModelBase)this, 64, 0);
        this.torso.addBox(0.0f, 0.0f, 0.0f, 16, 16, 8);
        this.torso.setRotationPoint(-8.0f, -8.0f, -4.0f);
        this.torso.setTextureSize(128, 64);
        this.torso.mirror = true;
        this.setRotation(this.torso, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 20, 12, 12);
        this.body.setRotationPoint(-10.0f, -20.0f, -6.0f);
        this.body.setTextureSize(128, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.headLeft = new ModelRenderer((ModelBase)this, 0, 44);
        this.headLeft.addBox(-4.0f, -5.0f, -8.0f, 7, 12, 8);
        this.headLeft.setRotationPoint(5.0f, -20.0f, -2.0f);
        this.headLeft.setTextureSize(128, 64);
        this.headLeft.mirror = true;
        this.setRotation(this.headLeft, 0.0f, 0.0f, 0.0f);
        this.headRight = new ModelRenderer((ModelBase)this, 0, 44);
        this.headRight.addBox(-4.0f, -5.0f, -8.0f, 7, 12, 8);
        this.headRight.setRotationPoint(-4.0f, -20.0f, -2.0f);
        this.headRight.setTextureSize(128, 64);
        this.headRight.mirror = true;
        this.setRotation(this.headRight, 0.0f, 0.0f, 0.0f);
        this.rightShoulder = new ModelRenderer((ModelBase)this, 0, 32);
        this.rightShoulder.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
        this.rightShoulder.setRotationPoint(-12.0f, -20.0f, 0.0f);
        this.rightShoulder.setTextureSize(128, 64);
        this.rightShoulder.mirror = true;
        this.setRotation(this.rightShoulder, 0.0f, 0.0f, 0.0f);
        this.rightArm = new ModelRenderer((ModelBase)this, 30, 44);
        this.rightArm.addBox(-2.0f, 3.0f, -2.0f, 4, 16, 4);
        this.rightArm.setRotationPoint(-12.0f, -20.0f, 0.0f);
        this.rightArm.setTextureSize(128, 64);
        this.rightArm.mirror = true;
        this.setRotation(this.rightArm, 0.0f, 0.0f, 0.0f);
        this.rightHand = new ModelRenderer((ModelBase)this, 0, 32);
        this.rightHand.addBox(-3.0f, 19.0f, -3.0f, 6, 6, 6);
        this.rightHand.setRotationPoint(-12.0f, -20.0f, 0.0f);
        this.rightHand.setTextureSize(128, 64);
        this.rightHand.mirror = true;
        this.setRotation(this.rightHand, 0.0f, 0.0f, 0.0f);
        this.leftShoulder = new ModelRenderer((ModelBase)this, 0, 32);
        this.leftShoulder.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
        this.leftShoulder.setRotationPoint(12.0f, -20.0f, 0.0f);
        this.leftShoulder.setTextureSize(128, 64);
        this.leftShoulder.mirror = true;
        this.setRotation(this.leftShoulder, 0.0f, 0.0f, 0.0f);
        this.leftArm = new ModelRenderer((ModelBase)this, 30, 44);
        this.leftArm.addBox(-2.0f, 3.0f, -2.0f, 4, 16, 4);
        this.leftArm.setRotationPoint(12.0f, -20.0f, 0.0f);
        this.leftArm.setTextureSize(128, 64);
        this.leftArm.mirror = true;
        this.setRotation(this.leftArm, 0.0f, 0.0f, 0.0f);
        this.leftHand = new ModelRenderer((ModelBase)this, 0, 32);
        this.leftHand.addBox(-3.0f, 19.0f, -3.0f, 6, 6, 6);
        this.leftHand.setRotationPoint(12.0f, -20.0f, 0.0f);
        this.leftHand.setTextureSize(128, 64);
        this.leftHand.mirror = true;
        this.setRotation(this.leftHand, 0.0f, 0.0f, 0.0f);
        this.rightLeg = new ModelRenderer((ModelBase)this, 46, 40);
        this.rightLeg.addBox(-4.0f, 0.0f, -4.0f, 8, 16, 8);
        this.rightLeg.setRotationPoint(-6.0f, 8.0f, 0.0f);
        this.rightLeg.setTextureSize(128, 64);
        this.rightLeg.mirror = true;
        this.setRotation(this.rightLeg, 0.0f, 0.0f, 0.0f);
        this.leftLeg = new ModelRenderer((ModelBase)this, 46, 40);
        this.leftLeg.addBox(-4.0f, 0.0f, -4.0f, 8, 16, 8);
        this.leftLeg.setRotationPoint(6.0f, 8.0f, 0.0f);
        this.leftLeg.setTextureSize(128, 64);
        this.leftLeg.mirror = true;
        this.setRotation(this.leftLeg, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.torso.render(f5);
        this.body.render(f5);
        this.headLeft.render(f5);
        this.headRight.render(f5);
        this.rightShoulder.render(f5);
        this.rightArm.render(f5);
        this.rightHand.render(f5);
        this.leftShoulder.render(f5);
        this.leftArm.render(f5);
        this.leftHand.render(f5);
        this.rightLeg.render(f5);
        this.leftLeg.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.headLeft.rotateAngleX = this.headRight.rotateAngleX = f4 / 57.295776f;
        this.headLeft.rotateAngleY = this.headRight.rotateAngleY = f3 / 57.295776f;
        this.rightArm.rotateAngleX = this.rightShoulder.rotateAngleX = MathHelper.cos((float)f) * f1;
        this.rightHand.rotateAngleX = this.rightShoulder.rotateAngleX;
        this.leftLeg.rotateAngleX = this.rightShoulder.rotateAngleX;
        this.leftArm.rotateAngleX = this.leftShoulder.rotateAngleX = MathHelper.cos((float)(f + (float)Math.PI)) * f1 * 0.7f;
        this.leftHand.rotateAngleX = this.leftShoulder.rotateAngleX;
        this.rightLeg.rotateAngleX = this.leftShoulder.rotateAngleX;
    }
}

