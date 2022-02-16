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

public class ModelMegalith
extends ModelBase {
    ModelRenderer torso;
    ModelRenderer body;
    ModelRenderer rightArm;
    ModelRenderer rightHand;
    ModelRenderer leftArm;
    ModelRenderer leftHand;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;
    ModelRenderer upperBody;
    ModelRenderer head;

    public ModelMegalith() {
        this.textureWidth = 256;
        this.textureHeight = 64;
        this.torso = new ModelRenderer((ModelBase)this, 64, 0);
        this.torso.addBox(0.0f, 0.0f, 0.0f, 16, 16, 8);
        this.torso.setRotationPoint(-8.0f, -8.0f, -4.0f);
        this.torso.setTextureSize(256, 64);
        this.torso.mirror = true;
        this.setRotation(this.torso, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 20, 12, 12);
        this.body.setRotationPoint(-10.0f, -20.0f, -6.0f);
        this.body.setTextureSize(256, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightArm = new ModelRenderer((ModelBase)this, 30, 42);
        this.rightArm.addBox(-3.0f, 3.0f, -3.0f, 6, 16, 6);
        this.rightArm.setRotationPoint(-13.0f, -32.0f, 0.0f);
        this.rightArm.setTextureSize(256, 64);
        this.rightArm.mirror = true;
        this.setRotation(this.rightArm, 0.0f, 0.0f, 0.0872665f);
        this.rightHand = new ModelRenderer((ModelBase)this, 0, 24);
        this.rightHand.addBox(-4.5f, 19.0f, -4.5f, 9, 8, 9);
        this.rightHand.setRotationPoint(-13.0f, -32.0f, 0.0f);
        this.rightHand.setTextureSize(256, 64);
        this.rightHand.mirror = true;
        this.setRotation(this.rightHand, 0.0f, 0.0f, 0.0872665f);
        this.leftArm = new ModelRenderer((ModelBase)this, 30, 42);
        this.leftArm.addBox(-3.0f, 3.0f, -3.0f, 6, 16, 6);
        this.leftArm.setRotationPoint(13.0f, -32.0f, 0.0f);
        this.leftArm.setTextureSize(256, 64);
        this.leftArm.mirror = true;
        this.setRotation(this.leftArm, 0.0f, 0.0f, -0.0872665f);
        this.leftHand = new ModelRenderer((ModelBase)this, 0, 24);
        this.leftHand.addBox(-4.5f, 19.0f, -4.5f, 9, 8, 9);
        this.leftHand.setRotationPoint(13.0f, -32.0f, 0.0f);
        this.leftHand.setTextureSize(256, 64);
        this.leftHand.mirror = true;
        this.setRotation(this.leftHand, 0.0f, 0.0f, -0.0872665f);
        this.rightLeg = new ModelRenderer((ModelBase)this, 54, 38);
        this.rightLeg.addBox(-4.0f, 0.0f, -5.0f, 8, 16, 10);
        this.rightLeg.setRotationPoint(-6.0f, 8.0f, 0.0f);
        this.rightLeg.setTextureSize(256, 64);
        this.rightLeg.mirror = true;
        this.setRotation(this.rightLeg, 0.0f, 0.0f, 0.0f);
        this.leftLeg = new ModelRenderer((ModelBase)this, 54, 38);
        this.leftLeg.addBox(-4.0f, 0.0f, -5.0f, 8, 16, 10);
        this.leftLeg.setRotationPoint(6.0f, 8.0f, 0.0f);
        this.leftLeg.setTextureSize(256, 64);
        this.leftLeg.mirror = true;
        this.setRotation(this.leftLeg, 0.0f, 0.0f, 0.0f);
        this.upperBody = new ModelRenderer((ModelBase)this, 112, 0);
        this.upperBody.addBox(0.0f, 0.0f, 0.0f, 22, 12, 14);
        this.upperBody.setRotationPoint(-11.0f, -32.0f, -7.0f);
        this.upperBody.setTextureSize(256, 64);
        this.upperBody.mirror = true;
        this.setRotation(this.upperBody, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 49);
        this.head.addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.head.setRotationPoint(0.0f, -41.0f, 0.0f);
        this.head.setTextureSize(256, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.torso.render(f5);
        this.body.render(f5);
        this.rightArm.render(f5);
        this.rightHand.render(f5);
        this.leftArm.render(f5);
        this.leftHand.render(f5);
        this.rightLeg.render(f5);
        this.leftLeg.render(f5);
        this.upperBody.render(f5);
        this.head.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f3 / 57.295776f;
        this.rightHand.rotateAngleX = this.rightArm.rotateAngleX = MathHelper.cos((float)f) * f1;
        this.leftLeg.rotateAngleX = this.rightArm.rotateAngleX;
        this.leftHand.rotateAngleX = this.leftArm.rotateAngleX = MathHelper.cos((float)(f + (float)Math.PI)) * f1 * 0.7f;
        this.rightLeg.rotateAngleX = this.leftArm.rotateAngleX;
    }
}

