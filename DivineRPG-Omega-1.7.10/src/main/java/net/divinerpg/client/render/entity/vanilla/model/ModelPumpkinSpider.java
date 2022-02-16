/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.divinerpg.entities.vanilla.EntityPumpkinSpider;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelPumpkinSpider
extends ModelBase {
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RearEnd;
    ModelRenderer Leg8;
    ModelRenderer Leg6;
    ModelRenderer Leg4;
    ModelRenderer Leg2;
    ModelRenderer Leg7;
    ModelRenderer Leg5;
    ModelRenderer Leg3;
    ModelRenderer Leg1;

    public ModelPumpkinSpider() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Head = new ModelRenderer((ModelBase)this, 32, 4);
        this.Head.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
        this.Head.setRotationPoint(0.0f, 20.0f, -3.0f);
        this.Head.setTextureSize(64, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Body.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
        this.Body.setRotationPoint(0.0f, 20.0f, 0.0f);
        this.Body.setTextureSize(64, 64);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.RearEnd = new ModelRenderer((ModelBase)this, 0, 20);
        this.RearEnd.addBox(-5.0f, -4.0f, -6.0f, 16, 16, 16);
        this.RearEnd.setRotationPoint(-3.0f, 12.0f, 8.0f);
        this.RearEnd.setTextureSize(64, 64);
        this.RearEnd.mirror = true;
        this.setRotation(this.RearEnd, 0.0f, 0.0f, 0.0f);
        this.Leg8 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg8.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg8.setRotationPoint(4.0f, 20.0f, -1.0f);
        this.Leg8.setTextureSize(64, 64);
        this.Leg8.mirror = true;
        this.setRotation(this.Leg8, 0.0f, 0.5759587f, 0.1919862f);
        this.Leg6 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg6.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg6.setRotationPoint(4.0f, 20.0f, 0.0f);
        this.Leg6.setTextureSize(64, 64);
        this.Leg6.mirror = true;
        this.setRotation(this.Leg6, 0.0f, 0.2792527f, 0.1919862f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg4.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg4.setRotationPoint(4.0f, 20.0f, 1.0f);
        this.Leg4.setTextureSize(64, 64);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0f, -0.2792527f, 0.1919862f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg2.addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg2.setRotationPoint(4.0f, 20.0f, 2.0f);
        this.Leg2.setTextureSize(64, 64);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0f, -0.5759587f, 0.1919862f);
        this.Leg7 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg7.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg7.setRotationPoint(-4.0f, 20.0f, -1.0f);
        this.Leg7.setTextureSize(64, 64);
        this.Leg7.mirror = true;
        this.setRotation(this.Leg7, 0.0f, -0.5759587f, -0.1919862f);
        this.Leg5 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg5.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg5.setRotationPoint(-4.0f, 20.0f, 0.0f);
        this.Leg5.setTextureSize(64, 64);
        this.Leg5.mirror = true;
        this.setRotation(this.Leg5, 0.0f, -0.2792527f, -0.1919862f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg3.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg3.setRotationPoint(-4.0f, 20.0f, 1.0f);
        this.Leg3.setTextureSize(64, 64);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0f, 0.2792527f, -0.1919862f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Leg1.addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
        this.Leg1.setRotationPoint(-4.0f, 20.0f, 2.0f);
        this.Leg1.setTextureSize(64, 64);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0f, 0.5759587f, -0.1919862f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        if (((EntityPumpkinSpider)entity).getProvoked()) {
            GL11.glTranslatef((float)0.0f, (float)-0.1875f, (float)0.0f);
        }
        this.RearEnd.render(f5);
        if (((EntityPumpkinSpider)entity).getProvoked()) {
            this.Head.render(f5);
            this.Body.render(f5);
            this.Leg8.render(f5);
            this.Leg6.render(f5);
            this.Leg4.render(f5);
            this.Leg2.render(f5);
            this.Leg7.render(f5);
            this.Leg5.render(f5);
            this.Leg3.render(f5);
            this.Leg1.render(f5);
        }
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        float var8 = 0.7853982f;
        this.Leg1.rotateAngleZ = -var8;
        this.Leg2.rotateAngleZ = var8;
        this.Leg3.rotateAngleZ = -var8 * 0.74f;
        this.Leg4.rotateAngleZ = var8 * 0.74f;
        this.Leg5.rotateAngleZ = -var8 * 0.74f;
        this.Leg6.rotateAngleZ = var8 * 0.74f;
        this.Leg7.rotateAngleZ = -var8;
        this.Leg8.rotateAngleZ = var8;
        float var9 = -0.0f;
        float var10 = 0.3926991f;
        this.Leg1.rotateAngleY = var10 * 2.0f + var9;
        this.Leg2.rotateAngleY = -var10 * 2.0f - var9;
        this.Leg3.rotateAngleY = var10 * 1.0f + var9;
        this.Leg4.rotateAngleY = -var10 * 1.0f - var9;
        this.Leg5.rotateAngleY = -var10 * 1.0f + var9;
        this.Leg6.rotateAngleY = var10 * 1.0f - var9;
        this.Leg7.rotateAngleY = -var10 * 2.0f + var9;
        this.Leg8.rotateAngleY = var10 * 2.0f - var9;
        float var11 = -MathHelper.cos((float)(f * 0.6662f * 2.0f + 0.0f)) * 0.4f * f1;
        float var12 = -MathHelper.cos((float)(f * 0.6662f * 2.0f + (float)Math.PI)) * 0.4f * f1;
        float var13 = -MathHelper.cos((float)(f * 0.6662f * 2.0f + 1.5707964f)) * 0.4f * f1;
        float var14 = -MathHelper.cos((float)(f * 0.6662f * 2.0f + 4.712389f)) * 0.4f * f1;
        float var15 = Math.abs(MathHelper.sin((float)(f * 0.6662f + 0.0f)) * 0.4f) * f1;
        float var16 = Math.abs(MathHelper.sin((float)(f * 0.6662f + (float)Math.PI)) * 0.4f) * f1;
        float var17 = Math.abs(MathHelper.sin((float)(f * 0.6662f + 1.5707964f)) * 0.4f) * f1;
        float var18 = Math.abs(MathHelper.sin((float)(f * 0.6662f + 4.712389f)) * 0.4f) * f1;
        this.Leg1.rotateAngleY += var11;
        this.Leg2.rotateAngleY += -var11;
        this.Leg3.rotateAngleY += var12;
        this.Leg4.rotateAngleY += -var12;
        this.Leg5.rotateAngleY += var13;
        this.Leg6.rotateAngleY += -var13;
        this.Leg7.rotateAngleY += var14;
        this.Leg8.rotateAngleY += -var14;
        this.Leg1.rotateAngleZ += var15;
        this.Leg2.rotateAngleZ += -var15;
        this.Leg3.rotateAngleZ += var16;
        this.Leg4.rotateAngleZ += -var16;
        this.Leg5.rotateAngleZ += var17;
        this.Leg6.rotateAngleZ += -var17;
        this.Leg7.rotateAngleZ += var18;
        this.Leg8.rotateAngleZ += -var18;
    }
}

