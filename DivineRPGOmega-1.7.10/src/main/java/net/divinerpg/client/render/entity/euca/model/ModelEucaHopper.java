/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.euca.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEucaHopper
extends ModelBase {
    ModelRenderer head;
    ModelRenderer tail;
    ModelRenderer leftFrontLeg;
    ModelRenderer rightFrontLeg;
    ModelRenderer rightBackLeg;
    ModelRenderer leftBackLeg;
    ModelRenderer frontFin;
    ModelRenderer backFin;
    ModelRenderer leftEar;
    ModelRenderer rightEar;

    public ModelEucaHopper() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(0.0f, 0.0f, 0.0f, 11, 11, 12);
        this.head.setRotationPoint(-6.0f, 8.0f, -6.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 0, 46);
        this.tail.addBox(0.0f, 0.0f, 0.0f, 6, 4, 8);
        this.tail.setRotationPoint(-3.5f, 12.0f, 5.0f);
        this.tail.setTextureSize(64, 64);
        this.tail.mirror = true;
        this.setRotation(this.tail, 0.1745329f, 0.0f, 0.0f);
        this.leftFrontLeg = new ModelRenderer((ModelBase)this, 50, 0);
        this.leftFrontLeg.addBox(-1.0f, 0.0f, -1.0f, 3, 7, 3);
        this.leftFrontLeg.setRotationPoint(5.0f, 17.0f, -4.0f);
        this.leftFrontLeg.setTextureSize(64, 64);
        this.leftFrontLeg.mirror = true;
        this.setRotation(this.leftFrontLeg, 0.0f, 0.0f, 0.0f);
        this.rightFrontLeg = new ModelRenderer((ModelBase)this, 50, 0);
        this.rightFrontLeg.addBox(-2.0f, 0.0f, -1.0f, 3, 7, 3);
        this.rightFrontLeg.setRotationPoint(-6.0f, 17.0f, -4.0f);
        this.rightFrontLeg.setTextureSize(64, 64);
        this.rightFrontLeg.mirror = true;
        this.setRotation(this.rightFrontLeg, 0.0f, 0.0f, 0.0f);
        this.rightBackLeg = new ModelRenderer((ModelBase)this, 50, 0);
        this.rightBackLeg.addBox(-2.0f, 0.0f, -1.0f, 3, 7, 3);
        this.rightBackLeg.setRotationPoint(-6.0f, 17.0f, 3.0f);
        this.rightBackLeg.setTextureSize(64, 64);
        this.rightBackLeg.mirror = true;
        this.setRotation(this.rightBackLeg, 0.0f, 0.0f, 0.0f);
        this.leftBackLeg = new ModelRenderer((ModelBase)this, 50, 0);
        this.leftBackLeg.addBox(-1.0f, 0.0f, -1.0f, 3, 7, 3);
        this.leftBackLeg.setRotationPoint(5.0f, 17.0f, 3.0f);
        this.leftBackLeg.setTextureSize(64, 64);
        this.leftBackLeg.mirror = true;
        this.setRotation(this.leftBackLeg, 0.0f, 0.0f, 0.0f);
        this.frontFin = new ModelRenderer((ModelBase)this, 1, 24);
        this.frontFin.addBox(0.0f, 0.0f, 0.0f, 2, 7, 5);
        this.frontFin.setRotationPoint(-1.5f, 2.0f, -4.0f);
        this.frontFin.setTextureSize(64, 64);
        this.frontFin.mirror = true;
        this.setRotation(this.frontFin, -0.2617994f, 0.0f, 0.0f);
        this.backFin = new ModelRenderer((ModelBase)this, 16, 25);
        this.backFin.addBox(0.0f, 0.0f, 0.0f, 2, 7, 4);
        this.backFin.setRotationPoint(-1.5f, 4.0f, 2.0f);
        this.backFin.setTextureSize(64, 64);
        this.backFin.mirror = true;
        this.setRotation(this.backFin, -0.715585f, 0.0f, 0.0f);
        this.leftEar = new ModelRenderer((ModelBase)this, 1, 37);
        this.leftEar.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.leftEar.setRotationPoint(5.0f, 6.0f, -3.0f);
        this.leftEar.setTextureSize(64, 64);
        this.leftEar.mirror = true;
        this.setRotation(this.leftEar, 0.0f, 0.0f, 0.837758f);
        this.rightEar = new ModelRenderer((ModelBase)this, 1, 37);
        this.rightEar.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.rightEar.setRotationPoint(-6.0f, 6.0f, -3.0f);
        this.rightEar.setTextureSize(64, 64);
        this.rightEar.mirror = true;
        this.setRotation(this.rightEar, 0.0f, 0.0f, 0.7504916f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.tail.render(f5);
        this.leftFrontLeg.render(f5);
        this.rightFrontLeg.render(f5);
        this.rightBackLeg.render(f5);
        this.leftBackLeg.render(f5);
        this.frontFin.render(f5);
        this.backFin.render(f5);
        this.leftEar.render(f5);
        this.rightEar.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.rightBackLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightFrontLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.leftFrontLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftBackLeg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
    }
}

