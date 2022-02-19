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

public class ModelSorcerer
extends ModelBase {
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer staffProngRight2;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarm;
    ModelRenderer staffPoleRight;
    ModelRenderer staffCapRight;
    ModelRenderer staffProngRight3;
    ModelRenderer staffProngRight1;
    ModelRenderer staffProngRight4;
    ModelRenderer head;
    ModelRenderer ray2;
    ModelRenderer ray8;
    ModelRenderer ray6;
    ModelRenderer ray4;
    ModelRenderer ray1;
    ModelRenderer ray7;
    ModelRenderer ray5;
    ModelRenderer ray3;
    ModelRenderer staffPoleLeft;
    ModelRenderer staffCapLeft;
    ModelRenderer staffProngLeft1;
    ModelRenderer staffProngLeft2;
    ModelRenderer staffProngLeft4;
    ModelRenderer staffProngLeft3;

    public ModelSorcerer() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 24, 0);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 10, 23);
        this.rightarm.addBox(-6.0f, 4.0f, -2.0f, 4, 4, 4);
        this.rightarm.setRotationPoint(-4.0f, 2.0f, -2.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.staffProngRight2 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngRight2.addBox(-3.0f, -7.0f, -3.0f, 1, 2, 1);
        this.staffProngRight2.setRotationPoint(-4.0f, 2.0f, 0.0f);
        this.staffProngRight2.setTextureSize(64, 32);
        this.staffProngRight2.mirror = true;
        this.setRotation(this.staffProngRight2, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 12);
        this.rightleg.addBox(-2.0f, 10.0f, -6.0f, 4, 2, 8);
        this.rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 12);
        this.leftleg.addBox(-2.0f, 10.0f, -6.0f, 4, 2, 8);
        this.leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 10, 23);
        this.leftarm.addBox(1.0f, 4.0f, -4.0f, 4, 4, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.staffPoleRight = new ModelRenderer((ModelBase)this, 53, 0);
        this.staffPoleRight.addBox(-5.0f, -4.0f, -5.0f, 2, 22, 2);
        this.staffPoleRight.setRotationPoint(-4.0f, 2.0f, 0.0f);
        this.staffPoleRight.setTextureSize(64, 32);
        this.staffPoleRight.mirror = true;
        this.setRotation(this.staffPoleRight, 0.0f, 0.0f, 0.0f);
        this.staffCapRight = new ModelRenderer((ModelBase)this, 40, 25);
        this.staffCapRight.addBox(-6.0f, -5.0f, -6.0f, 4, 2, 4);
        this.staffCapRight.setRotationPoint(-4.0f, 2.0f, 0.0f);
        this.staffCapRight.setTextureSize(64, 32);
        this.staffCapRight.mirror = true;
        this.setRotation(this.staffCapRight, 0.0f, 0.0f, 0.0f);
        this.staffProngRight3 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngRight3.addBox(-6.0f, -7.0f, -3.0f, 1, 2, 1);
        this.staffProngRight3.setRotationPoint(-4.0f, 2.0f, 0.0f);
        this.staffProngRight3.setTextureSize(64, 32);
        this.staffProngRight3.mirror = true;
        this.setRotation(this.staffProngRight3, 0.0f, 0.0f, 0.0f);
        this.staffProngRight1 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngRight1.addBox(-3.0f, -7.0f, -6.0f, 1, 2, 1);
        this.staffProngRight1.setRotationPoint(-4.0f, 2.0f, 0.0f);
        this.staffProngRight1.setTextureSize(64, 32);
        this.staffProngRight1.mirror = true;
        this.setRotation(this.staffProngRight1, 0.0f, 0.0f, 0.0f);
        this.staffProngRight4 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngRight4.addBox(-6.0f, -7.0f, -6.0f, 1, 2, 1);
        this.staffProngRight4.setRotationPoint(-4.0f, 2.0f, 0.0f);
        this.staffProngRight4.setTextureSize(64, 32);
        this.staffProngRight4.mirror = true;
        this.setRotation(this.staffProngRight4, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
        this.head.setRotationPoint(0.0f, -5.0f, -1.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.ray2 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray2.addBox(-7.0f, -1.0f, -1.0f, 2, 2, 2);
        this.ray2.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray2.setTextureSize(64, 32);
        this.ray2.mirror = true;
        this.setRotation(this.ray2, 0.0f, 0.0f, -0.7853982f);
        this.ray8 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray8.addBox(-1.0f, 6.0f, -1.0f, 2, 2, 2);
        this.ray8.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray8.setTextureSize(64, 32);
        this.ray8.mirror = true;
        this.setRotation(this.ray8, 0.0f, 0.0f, -0.7853982f);
        this.ray6 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray6.addBox(5.0f, -1.0f, -1.0f, 2, 2, 2);
        this.ray6.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray6.setTextureSize(64, 32);
        this.ray6.mirror = true;
        this.setRotation(this.ray6, 0.0f, 0.0f, -0.7853982f);
        this.ray4 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray4.addBox(-1.0f, -7.0f, -1.0f, 2, 2, 2);
        this.ray4.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray4.setTextureSize(64, 32);
        this.ray4.mirror = true;
        this.setRotation(this.ray4, 0.0f, 0.0f, -0.7853982f);
        this.ray1 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray1.addBox(-1.0f, 6.0f, -1.0f, 2, 2, 2);
        this.ray1.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray1.setTextureSize(64, 32);
        this.ray1.mirror = true;
        this.setRotation(this.ray1, 0.0f, 0.0f, 0.0f);
        this.ray7 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray7.addBox(5.0f, -1.0f, -1.0f, 2, 2, 2);
        this.ray7.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray7.setTextureSize(64, 32);
        this.ray7.mirror = true;
        this.setRotation(this.ray7, 0.0f, 0.0f, 0.0f);
        this.ray5 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray5.addBox(-1.0f, -7.0f, -1.0f, 2, 2, 2);
        this.ray5.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray5.setTextureSize(64, 32);
        this.ray5.mirror = true;
        this.setRotation(this.ray5, 0.0f, 0.0f, 0.0f);
        this.ray3 = new ModelRenderer((ModelBase)this, 0, 23);
        this.ray3.addBox(-7.0f, -1.0f, -1.0f, 2, 2, 2);
        this.ray3.setRotationPoint(0.0f, -9.0f, -1.0f);
        this.ray3.setTextureSize(64, 32);
        this.ray3.mirror = true;
        this.setRotation(this.ray3, 0.0f, 0.0f, 0.0f);
        this.staffPoleLeft = new ModelRenderer((ModelBase)this, 53, 0);
        this.staffPoleLeft.addBox(2.0f, -4.0f, -5.0f, 2, 22, 2);
        this.staffPoleLeft.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.staffPoleLeft.setTextureSize(64, 32);
        this.staffPoleLeft.mirror = true;
        this.setRotation(this.staffPoleLeft, 0.0f, 0.0f, 0.0f);
        this.staffCapLeft = new ModelRenderer((ModelBase)this, 40, 25);
        this.staffCapLeft.addBox(1.0f, -5.0f, -6.0f, 4, 2, 4);
        this.staffCapLeft.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.staffCapLeft.setTextureSize(64, 32);
        this.staffCapLeft.mirror = true;
        this.setRotation(this.staffCapLeft, 0.0f, 0.0f, 0.0f);
        this.staffProngLeft1 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngLeft1.addBox(4.0f, -7.0f, -6.0f, 1, 2, 1);
        this.staffProngLeft1.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.staffProngLeft1.setTextureSize(64, 32);
        this.staffProngLeft1.mirror = true;
        this.setRotation(this.staffProngLeft1, 0.0f, 0.0f, 0.0f);
        this.staffProngLeft2 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngLeft2.addBox(4.0f, -7.0f, -3.0f, 1, 2, 1);
        this.staffProngLeft2.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.staffProngLeft2.setTextureSize(64, 32);
        this.staffProngLeft2.mirror = true;
        this.setRotation(this.staffProngLeft2, 0.0f, 0.0f, 0.0f);
        this.staffProngLeft4 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngLeft4.addBox(1.0f, -7.0f, -6.0f, 1, 2, 1);
        this.staffProngLeft4.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.staffProngLeft4.setTextureSize(64, 32);
        this.staffProngLeft4.mirror = true;
        this.setRotation(this.staffProngLeft4, 0.0f, 0.0f, 0.0f);
        this.staffProngLeft3 = new ModelRenderer((ModelBase)this, 40, 20);
        this.staffProngLeft3.addBox(1.0f, -7.0f, -3.0f, 1, 2, 1);
        this.staffProngLeft3.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.staffProngLeft3.setTextureSize(64, 32);
        this.staffProngLeft3.mirror = true;
        this.setRotation(this.staffProngLeft3, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.staffProngRight2.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.leftarm.render(f5);
        this.staffPoleRight.render(f5);
        this.staffCapRight.render(f5);
        this.staffProngRight3.render(f5);
        this.staffProngRight1.render(f5);
        this.staffProngRight4.render(f5);
        this.head.render(f5);
        this.ray2.render(f5);
        this.ray8.render(f5);
        this.ray6.render(f5);
        this.ray4.render(f5);
        this.ray1.render(f5);
        this.ray7.render(f5);
        this.ray5.render(f5);
        this.ray3.render(f5);
        this.staffPoleLeft.render(f5);
        this.staffCapLeft.render(f5);
        this.staffProngLeft1.render(f5);
        this.staffProngLeft2.render(f5);
        this.staffProngLeft4.render(f5);
        this.staffProngLeft3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.ray5.rotateAngleZ = this.ray7.rotateAngleZ = f2 / 10.0f;
        this.ray3.rotateAngleZ = this.ray7.rotateAngleZ;
        this.ray1.rotateAngleZ = this.ray7.rotateAngleZ;
        this.ray6.rotateAngleZ = this.ray8.rotateAngleZ = f2 / 10.0f - 0.7853982f;
        this.ray4.rotateAngleZ = this.ray8.rotateAngleZ;
        this.ray2.rotateAngleZ = this.ray8.rotateAngleZ;
        this.staffProngRight3.rotateAngleX = this.staffProngRight4.rotateAngleX = MathHelper.cos((float)f) * f1 * 1.2f;
        this.staffProngRight2.rotateAngleX = this.staffProngRight4.rotateAngleX;
        this.staffProngRight1.rotateAngleX = this.staffProngRight4.rotateAngleX;
        this.staffCapRight.rotateAngleX = this.staffProngRight4.rotateAngleX;
        this.staffPoleRight.rotateAngleX = this.staffProngRight4.rotateAngleX;
        this.rightarm.rotateAngleX = this.staffProngRight4.rotateAngleX;
        this.leftleg.rotateAngleX = this.staffProngRight4.rotateAngleX;
        this.staffProngLeft3.rotateAngleX = this.staffProngLeft4.rotateAngleX = MathHelper.cos((float)(f + (float)Math.PI)) * f1 * 1.2f;
        this.staffProngLeft2.rotateAngleX = this.staffProngLeft4.rotateAngleX;
        this.staffProngLeft1.rotateAngleX = this.staffProngLeft4.rotateAngleX;
        this.staffCapLeft.rotateAngleX = this.staffProngLeft4.rotateAngleX;
        this.staffPoleLeft.rotateAngleX = this.staffProngLeft4.rotateAngleX;
        this.leftarm.rotateAngleX = this.staffProngLeft4.rotateAngleX;
        this.rightleg.rotateAngleX = this.staffProngLeft4.rotateAngleX;
    }
}

