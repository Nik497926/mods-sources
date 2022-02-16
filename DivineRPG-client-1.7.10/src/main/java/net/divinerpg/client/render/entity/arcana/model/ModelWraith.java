/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.arcana.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWraith
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg3;
    ModelRenderer leg4;

    public ModelWraith() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, 6.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 19, 0);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 22, 10);
        this.body.setRotationPoint(0.0f, 11.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.8644027f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg3.addBox(-3.0f, 0.0f, -3.0f, 4, 11, 4);
        this.leg3.setRotationPoint(-3.0f, 13.0f, -5.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, -1.308997f, 1.134464f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg4.addBox(-1.0f, 0.0f, -3.0f, 4, 11, 4);
        this.leg4.setRotationPoint(3.0f, 13.0f, -5.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, -1.308997f, -1.134464f, -0.0743572f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.rotateAngleY = f3 / 57.295776f;
        this.head.rotateAngleX = f4 / 57.295776f;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.leg4.rotateAngleY = 0.0f;
        this.leg3.rotateAngleY = 0.0f;
    }
}

