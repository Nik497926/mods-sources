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

public class HiveSoldier
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body1;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;

    public HiveSoldier() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 7, 8);
        this.head.setRotationPoint(0.0f, 1.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body1.addBox(0.0f, 10.0f, -2.0f, 2, 2, 2);
        this.body1.setRotationPoint(2.0f, 0.0f, 1.0f);
        this.body1.setTextureSize(64, 32);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 2, 12, 2);
        this.rightarm.setRotationPoint(-4.0f, 2.0f, 1.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
        this.leftarm.setRotationPoint(6.0f, 2.0f, 1.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 2, 12, 2);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 1.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 2, 12, 2);
        this.leftleg.setRotationPoint(4.0f, 12.0f, 1.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body2.addBox(-4.0f, 0.0f, -2.0f, 4, 12, 2);
        this.body2.setRotationPoint(2.0f, 0.0f, 1.0f);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body3.addBox(-7.0f, 2.0f, -2.0f, 3, 2, 2);
        this.body3.setRotationPoint(9.0f, 3.0f, 1.0f);
        this.body3.setTextureSize(64, 32);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body4.addBox(-6.0f, 10.0f, -2.0f, 2, 2, 2);
        this.body4.setRotationPoint(2.0f, 0.0f, 1.0f);
        this.body4.setTextureSize(64, 32);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.0f, 0.0f, 0.0f);
        this.body5 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body5.addBox(-7.0f, 2.0f, -2.0f, 3, 2, 2);
        this.body5.setRotationPoint(2.0f, 3.0f, 1.0f);
        this.body5.setTextureSize(64, 32);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.0f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body6.addBox(-7.0f, 2.0f, -2.0f, 3, 2, 2);
        this.body6.setRotationPoint(9.0f, -1.0f, 1.0f);
        this.body6.setTextureSize(64, 32);
        this.body6.mirror = true;
        this.setRotation(this.body6, 0.0f, 0.0f, 0.0f);
        this.body7 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body7.addBox(-7.0f, 2.0f, -2.0f, 3, 2, 2);
        this.body7.setRotationPoint(2.0f, -1.0f, 1.0f);
        this.body7.setTextureSize(64, 32);
        this.body7.mirror = true;
        this.setRotation(this.body7, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body1.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.body4.render(f5);
        this.body5.render(f5);
        this.body6.render(f5);
        this.body7.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
    }
}

