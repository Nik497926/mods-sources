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

public class ModelCrab
extends ModelBase {
    ModelRenderer RearEnd;
    ModelRenderer Leg8;
    ModelRenderer Leg6;
    ModelRenderer Leg4;
    ModelRenderer Leg7;
    ModelRenderer Leg5;
    ModelRenderer Leg3;

    public ModelCrab() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.RearEnd = new ModelRenderer((ModelBase)this, 0, 12);
        this.RearEnd.addBox(-5.0f, -4.0f, -6.0f, 12, 8, 12);
        this.RearEnd.setRotationPoint(-1.0f, 16.0f, 0.0f);
        this.RearEnd.setTextureSize(64, 32);
        this.RearEnd.mirror = true;
        this.setRotation(this.RearEnd, 0.0f, 0.0f, 0.0f);
        this.Leg8 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg8.addBox(-1.0f, -1.0f, -1.0f, 16, 4, 4);
        this.Leg8.setRotationPoint(4.0f, 16.0f, -4.0f);
        this.Leg8.setTextureSize(64, 32);
        this.Leg8.mirror = true;
        this.setRotation(this.Leg8, 0.0f, 0.5759587f, -0.1396263f);
        this.Leg6 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg6.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg6.setRotationPoint(4.0f, 16.0f, 0.0f);
        this.Leg6.setTextureSize(64, 32);
        this.Leg6.mirror = true;
        this.setRotation(this.Leg6, 0.0f, 0.2792527f, 0.1919862f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg4.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg4.setRotationPoint(4.0f, 16.0f, 1.0f);
        this.Leg4.setTextureSize(64, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, -0.2792527f, 0.1919862f);
        this.Leg7 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg7.addBox(-15.0f, -1.0f, -1.0f, 16, 4, 4);
        this.Leg7.setRotationPoint(-4.0f, 16.0f, -4.0f);
        this.Leg7.setTextureSize(64, 32);
        this.Leg7.mirror = true;
        this.setRotation(this.Leg7, 0.0f, -0.5759587f, 0.1396263f);
        this.Leg5 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg5.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg5.setRotationPoint(-4.0f, 16.0f, 0.0f);
        this.Leg5.setTextureSize(64, 32);
        this.Leg5.mirror = true;
        this.setRotation(this.Leg5, 0.0f, -0.2792527f, -0.1919862f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg3.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg3.setRotationPoint(-4.0f, 16.0f, 1.0f);
        this.Leg3.setTextureSize(64, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.2792527f, -0.1919862f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.RearEnd.render(var7);
        this.Leg8.render(var7);
        this.Leg6.render(var7);
        this.Leg4.render(var7);
        this.Leg7.render(var7);
        this.Leg5.render(var7);
        this.Leg3.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        float var7 = 0.7853982f;
        this.Leg3.rotateAngleZ = -var7 * 0.74f;
        this.Leg4.rotateAngleZ = var7 * 0.74f;
        this.Leg5.rotateAngleZ = -var7 * 0.74f;
        this.Leg6.rotateAngleZ = var7 * 0.74f;
        this.Leg7.rotateAngleZ = -var7;
        this.Leg8.rotateAngleZ = var7;
        float var8 = -0.0f;
        float var9 = 0.3926991f;
        this.Leg3.rotateAngleY = var9 * 1.0f + var8;
        this.Leg4.rotateAngleY = -var9 * 1.0f - var8;
        this.Leg5.rotateAngleY = -var9 * 1.0f + var8;
        this.Leg6.rotateAngleY = var9 * 1.0f - var8;
        this.Leg7.rotateAngleY = -var9 * 2.0f + var8;
        this.Leg8.rotateAngleY = var9 * 2.0f - var8;
        float var10 = -MathHelper.cos((float)(var1 * 0.6662f * 2.0f + 0.0f)) * 0.4f * var2;
        float var11 = -MathHelper.cos((float)(var1 * 0.6662f * 2.0f + (float)Math.PI)) * 0.4f * var2;
        float var12 = -MathHelper.cos((float)(var1 * 0.6662f * 2.0f + 1.5707964f)) * 0.4f * var2;
        float var13 = -MathHelper.cos((float)(var1 * 0.6662f * 2.0f + 4.712389f)) * 0.4f * var2;
        float var14 = Math.abs(MathHelper.sin((float)(var1 * 0.6662f + 0.0f)) * 0.4f) * var2;
        float var15 = Math.abs(MathHelper.sin((float)(var1 * 0.6662f + (float)Math.PI)) * 0.4f) * var2;
        float var16 = Math.abs(MathHelper.sin((float)(var1 * 0.6662f + 1.5707964f)) * 0.4f) * var2;
        float var17 = Math.abs(MathHelper.sin((float)(var1 * 0.6662f + 4.712389f)) * 0.4f) * var2;
        this.Leg3.rotateAngleY += var11;
        this.Leg4.rotateAngleY += -var11;
        this.Leg5.rotateAngleY += var12;
        this.Leg6.rotateAngleY += -var12;
        this.Leg7.rotateAngleY += var13;
        this.Leg8.rotateAngleY += -var13;
        this.Leg3.rotateAngleZ += var15;
        this.Leg4.rotateAngleZ += -var15;
        this.Leg5.rotateAngleZ += var16;
        this.Leg6.rotateAngleZ += -var16;
        this.Leg7.rotateAngleZ += var17;
        this.Leg8.rotateAngleZ += -var17;
    }
}

