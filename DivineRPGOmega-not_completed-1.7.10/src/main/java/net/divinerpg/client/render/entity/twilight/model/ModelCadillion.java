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

public class ModelCadillion
extends ModelBase {
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer head;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer Horn;

    public ModelCadillion() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 8, 18, 10);
        this.body.setRotationPoint(2.0f, 5.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.570796f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1.addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg1.setRotationPoint(-2.0f, 12.0f, 7.0f);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg2.addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg2.setRotationPoint(2.0f, 12.0f, 7.0f);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg2.mirror = false;
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg3.addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
        this.leg3.setRotationPoint(-2.0f, 12.0f, -5.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg4.addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
        this.leg4.setRotationPoint(2.0f, 12.0f, -5.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -3.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, 3.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.horn1 = new ModelRenderer((ModelBase)this, 55, 0);
        this.horn1.addBox(-3.0f, -6.0f, -5.0f, 1, 3, 3);
        this.horn1.setRotationPoint(0.0f, 3.0f, -8.0f);
        this.horn1.setTextureSize(64, 32);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.0f, 0.0f, 0.0f);
        this.horn2 = new ModelRenderer((ModelBase)this, 55, 0);
        this.horn2.addBox(2.0f, -6.0f, -5.0f, 1, 3, 3);
        this.horn2.setRotationPoint(0.0f, 3.0f, -8.0f);
        this.horn2.setTextureSize(64, 32);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.0f, 0.0f, 0.0f);
        this.Horn = new ModelRenderer((ModelBase)this, 55, 5);
        this.Horn.addBox(-1.0f, 3.0f, 2.0f, 2, 8, 2);
        this.Horn.setRotationPoint(0.0f, 3.0f, -8.0f);
        this.Horn.setTextureSize(64, 32);
        this.Horn.mirror = true;
        this.setRotation(this.Horn, -2.13777f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.head.render(f5);
        this.horn1.render(f5);
        this.horn2.render(f5);
        this.Horn.render(f5);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head.rotateAngleY = var4 / 57.295776f;
        this.Horn.rotateAngleY = var4 / 63.661976f;
        this.horn1.rotateAngleY = var4 / 57.295776f;
        this.horn2.rotateAngleY = var4 / 57.295776f;
        this.body.rotateAngleX = 1.570796f;
        this.leg1.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leg2.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + 3.141593f)) * 1.4f * var2;
        this.leg3.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + 3.141593f)) * 1.4f * var2;
        this.leg4.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
    }
}

