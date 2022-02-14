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

public class ModelBasalisk
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg5;
    ModelRenderer leg6;
    ModelRenderer Spine_1;
    ModelRenderer Spine_2;
    ModelRenderer Spine_3;
    ModelRenderer Spine_4;
    ModelRenderer Spine_5;

    public ModelBasalisk() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-5.0f, -3.0f, -6.0f, 10, 6, 7);
        this.head.setRotationPoint(0.0f, 15.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 32, 10);
        this.body.addBox(-5.0f, -10.0f, -7.0f, 10, 16, 6);
        this.body.setRotationPoint(0.0f, 11.0f, 3.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.5707964f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1.addBox(-2.0f, -1.0f, -2.0f, 4, 6, 4);
        this.leg1.setRotationPoint(-3.0f, 19.0f, -5.0f);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg2.addBox(-2.0f, -1.0f, -2.0f, 4, 6, 4);
        this.leg2.setRotationPoint(3.0f, 19.0f, -5.0f);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg3.addBox(-2.0f, -1.0f, -2.0f, 4, 6, 4);
        this.leg3.setRotationPoint(-3.0f, 19.0f, 1.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg4.addBox(-2.0f, -1.0f, -2.0f, 4, 6, 4);
        this.leg4.setRotationPoint(3.0f, 19.0f, 1.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.leg5 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg5.addBox(-2.0f, -1.0f, -2.0f, 4, 6, 4);
        this.leg5.setRotationPoint(-3.0f, 19.0f, 7.0f);
        this.leg5.setTextureSize(64, 32);
        this.leg5.mirror = true;
        this.setRotation(this.leg5, 0.0f, 0.0f, 0.0f);
        this.leg6 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg6.addBox(-2.0f, -1.0f, -2.0f, 4, 6, 4);
        this.leg6.setRotationPoint(3.0f, 19.0f, 7.0f);
        this.leg6.setTextureSize(64, 32);
        this.leg6.mirror = true;
        this.setRotation(this.leg6, 0.0f, 0.0f, 0.0f);
        this.Spine_1 = new ModelRenderer((ModelBase)this, 56, 0);
        this.Spine_1.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Spine_1.setRotationPoint(0.0f, 11.0f, -5.0f);
        this.Spine_1.setTextureSize(64, 32);
        this.Spine_1.mirror = true;
        this.setRotation(this.Spine_1, 0.0f, 0.0f, 0.0f);
        this.Spine_2 = new ModelRenderer((ModelBase)this, 56, 0);
        this.Spine_2.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Spine_2.setRotationPoint(0.0f, 11.0f, -2.0f);
        this.Spine_2.setTextureSize(64, 32);
        this.Spine_2.mirror = true;
        this.setRotation(this.Spine_2, 0.0f, 0.0f, 0.0f);
        this.Spine_3 = new ModelRenderer((ModelBase)this, 56, 0);
        this.Spine_3.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Spine_3.setRotationPoint(0.0f, 11.0f, 1.0f);
        this.Spine_3.setTextureSize(64, 32);
        this.Spine_3.mirror = true;
        this.setRotation(this.Spine_3, 0.0f, 0.0f, 0.0f);
        this.Spine_4 = new ModelRenderer((ModelBase)this, 56, 0);
        this.Spine_4.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Spine_4.setRotationPoint(0.0f, 11.0f, 4.0f);
        this.Spine_4.setTextureSize(64, 32);
        this.Spine_4.mirror = true;
        this.setRotation(this.Spine_4, 0.0f, 0.0f, 0.0f);
        this.Spine_5 = new ModelRenderer((ModelBase)this, 56, 0);
        this.Spine_5.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.Spine_5.setRotationPoint(0.0f, 11.0f, 7.0f);
        this.Spine_5.setTextureSize(64, 32);
        this.Spine_5.mirror = true;
        this.setRotation(this.Spine_5, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.head.render(var7);
        this.body.render(var7);
        this.leg1.render(var7);
        this.leg2.render(var7);
        this.leg3.render(var7);
        this.leg4.render(var7);
        this.leg5.render(var7);
        this.leg6.render(var7);
        this.Spine_1.render(var7);
        this.Spine_2.render(var7);
        this.Spine_3.render(var7);
        this.Spine_4.render(var7);
        this.Spine_5.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.leg1.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leg2.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.leg3.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.leg4.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leg5.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leg6.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
    }
}

