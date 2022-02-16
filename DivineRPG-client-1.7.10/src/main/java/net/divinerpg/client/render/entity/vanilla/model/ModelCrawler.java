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

public class ModelCrawler
extends ModelBase {
    ModelRenderer earL;
    ModelRenderer rightfoot;
    ModelRenderer leftfoot;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;
    ModelRenderer head;
    ModelRenderer earR;
    ModelRenderer earL2;
    ModelRenderer earR2;

    public ModelCrawler() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.earL = new ModelRenderer((ModelBase)this, 36, 2);
        this.earL.addBox(5.0f, 1.0f, -8.0f, 1, 2, 5);
        this.earL.setRotationPoint(-1.0f, 6.0f, -8.0f);
        this.earL.setTextureSize(64, 32);
        this.earL.mirror = true;
        this.setRotation(this.earL, 0.0f, 0.0f, 0.0f);
        this.rightfoot = new ModelRenderer((ModelBase)this, 28, 16);
        this.rightfoot.addBox(-4.0f, 7.0f, -4.0f, 6, 4, 6);
        this.rightfoot.setRotationPoint(-3.0f, 13.0f, -5.0f);
        this.rightfoot.setTextureSize(64, 32);
        this.rightfoot.mirror = true;
        this.setRotation(this.rightfoot, 0.0f, 0.0f, 0.0f);
        this.leftfoot = new ModelRenderer((ModelBase)this, 28, 16);
        this.leftfoot.addBox(-2.0f, 7.0f, -4.0f, 6, 4, 6);
        this.leftfoot.setRotationPoint(3.0f, 13.0f, -5.0f);
        this.leftfoot.setTextureSize(64, 32);
        this.leftfoot.mirror = true;
        this.setRotation(this.leftfoot, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-1.0f, 0.0f, -3.0f, 4, 7, 4);
        this.leftleg.setRotationPoint(3.0f, 13.0f, -5.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-3.0f, 0.0f, -3.0f, 4, 7, 4);
        this.rightleg.setRotationPoint(-3.0f, 13.0f, -5.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 19, 16);
        this.body.addBox(-7.0f, -6.0f, -5.0f, 7, 5, 10);
        this.body.setRotationPoint(7.0f, 15.0f, -6.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, -0.4363323f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 19, 0);
        this.body2.addBox(-7.0f, -6.0f, -5.0f, 7, 5, 10);
        this.body2.setRotationPoint(0.0f, 15.0f, -6.0f);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, -0.4363323f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer((ModelBase)this, 24, 19);
        this.body3.addBox(-7.0f, -6.0f, -5.0f, 7, 5, 2);
        this.body3.setRotationPoint(0.0f, 27.0f, 2.0f);
        this.body3.setTextureSize(64, 32);
        this.body3.mirror = true;
        this.setRotation(this.body3, -1.570796f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer((ModelBase)this, 19, 19);
        this.body4.addBox(-7.0f, -6.0f, -5.0f, 7, 5, 2);
        this.body4.setRotationPoint(7.0f, 27.0f, 2.0f);
        this.body4.setTextureSize(64, 32);
        this.body4.mirror = true;
        this.setRotation(this.body4, -1.570796f, 0.0f, 0.0f);
        this.body5 = new ModelRenderer((ModelBase)this, 24, 19);
        this.body5.addBox(-7.0f, -6.0f, -5.0f, 6, 5, 4);
        this.body5.setRotationPoint(1.0f, 24.0f, 3.0f);
        this.body5.setTextureSize(64, 32);
        this.body5.mirror = true;
        this.setRotation(this.body5, -1.308997f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer((ModelBase)this, 19, 19);
        this.body6.addBox(-7.0f, -6.0f, -5.0f, 6, 5, 4);
        this.body6.setRotationPoint(7.0f, 24.0f, 3.0f);
        this.body6.setTextureSize(64, 32);
        this.body6.mirror = true;
        this.setRotation(this.body6, -1.308997f, 0.0f, 0.0f);
        this.body7 = new ModelRenderer((ModelBase)this, 24, 19);
        this.body7.addBox(-7.0f, -6.0f, -5.0f, 7, 5, 7);
        this.body7.setRotationPoint(0.0f, 20.0f, 1.0f);
        this.body7.setTextureSize(64, 32);
        this.body7.mirror = true;
        this.setRotation(this.body7, -0.7853982f, 0.0f, 0.0f);
        this.body8 = new ModelRenderer((ModelBase)this, 19, 19);
        this.body8.addBox(-7.0f, -6.0f, -5.0f, 7, 5, 7);
        this.body8.setRotationPoint(7.0f, 20.0f, 1.0f);
        this.body8.setTextureSize(64, 32);
        this.body8.mirror = true;
        this.setRotation(this.body8, -0.7853982f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, 6.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.earR = new ModelRenderer((ModelBase)this, 36, 2);
        this.earR.addBox(-4.0f, 1.0f, -8.0f, 1, 2, 5);
        this.earR.setRotationPoint(-1.0f, 6.0f, -8.0f);
        this.earR.setTextureSize(64, 32);
        this.earR.mirror = true;
        this.setRotation(this.earR, 0.0f, 0.0f, 0.0f);
        this.earL2 = new ModelRenderer((ModelBase)this, 36, 2);
        this.earL2.addBox(5.0f, -7.0f, -3.0f, 1, 5, 2);
        this.earL2.setRotationPoint(-1.0f, 6.0f, -8.0f);
        this.earL2.setTextureSize(64, 32);
        this.earL2.mirror = true;
        this.setRotation(this.earL2, 0.0f, 0.0f, 0.0f);
        this.earR2 = new ModelRenderer((ModelBase)this, 36, 2);
        this.earR2.addBox(-4.0f, -7.0f, -3.0f, 1, 5, 2);
        this.earR2.setRotationPoint(-1.0f, 6.0f, -8.0f);
        this.earR2.setTextureSize(64, 32);
        this.earR2.mirror = true;
        this.setRotation(this.earR2, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.earL.render(f5);
        this.rightfoot.render(f5);
        this.leftfoot.render(f5);
        this.leftleg.render(f5);
        this.rightleg.render(f5);
        this.body.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.body4.render(f5);
        this.body5.render(f5);
        this.body6.render(f5);
        this.body7.render(f5);
        this.body8.render(f5);
        this.head.render(f5);
        this.earR.render(f5);
        this.earL2.render(f5);
        this.earR2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head.rotateAngleY = var4 / 57.295776f;
        this.head.rotateAngleX = var5 / 57.295776f;
        this.earL.rotateAngleY = var4 / 57.295776f;
        this.earL.rotateAngleX = var5 / 57.295776f;
        this.earR.rotateAngleY = var4 / 57.295776f;
        this.earR.rotateAngleX = var5 / 57.295776f;
        this.earL2.rotateAngleY = var4 / 57.295776f;
        this.earL2.rotateAngleX = var5 / 57.295776f;
        this.earR2.rotateAngleY = var4 / 57.295776f;
        this.earR2.rotateAngleX = var5 / 57.295776f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.rightfoot.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)((float)((double)(var1 * 0.6662f) + Math.PI))) * 1.4f * var2;
        this.leftfoot.rotateAngleX = MathHelper.cos((float)((float)((double)(var1 * 0.6662f) + Math.PI))) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.rightfoot.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        this.leftfoot.rotateAngleY = 0.0f;
    }
}

