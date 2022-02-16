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

public class ModelLeorna
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer a;
    ModelRenderer Bar1;
    ModelRenderer Bar2;
    ModelRenderer Bar3;
    ModelRenderer Bar4;
    ModelRenderer Box;

    public ModelLeorna() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, -2.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 13);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 13);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 13);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 13);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 13);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.a = new ModelRenderer((ModelBase)this, 0, 0);
        this.a.addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.a.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.a.setTextureSize(64, 32);
        this.a.mirror = true;
        this.setRotation(this.a, 0.0f, 0.0f, 0.0f);
        this.Bar1 = new ModelRenderer((ModelBase)this, 0, 29);
        this.Bar1.addBox(0.0f, 0.0f, 0.0f, 24, 2, 1);
        this.Bar1.setRotationPoint(-12.0f, 9.0f, 3.0f);
        this.Bar1.setTextureSize(64, 32);
        this.Bar1.mirror = true;
        this.setRotation(this.Bar1, 0.0f, 0.0f, 0.0f);
        this.Bar2 = new ModelRenderer((ModelBase)this, 0, 29);
        this.Bar2.addBox(0.0f, 0.0f, 0.0f, 24, 2, 1);
        this.Bar2.setRotationPoint(-12.0f, 0.0f, 3.0f);
        this.Bar2.setTextureSize(64, 32);
        this.Bar2.mirror = true;
        this.setRotation(this.Bar2, 0.0f, 0.0f, 0.0f);
        this.Bar3 = new ModelRenderer((ModelBase)this, 0, 29);
        this.Bar3.addBox(0.0f, 0.0f, 0.0f, 24, 2, 1);
        this.Bar3.setRotationPoint(-12.0f, 3.0f, 3.0f);
        this.Bar3.setTextureSize(64, 32);
        this.Bar3.mirror = true;
        this.setRotation(this.Bar3, 0.0f, 0.0f, 0.0f);
        this.Bar4 = new ModelRenderer((ModelBase)this, 0, 29);
        this.Bar4.addBox(0.0f, 0.0f, 0.0f, 24, 2, 1);
        this.Bar4.setRotationPoint(-12.0f, 6.0f, 3.0f);
        this.Bar4.setTextureSize(64, 32);
        this.Bar4.mirror = true;
        this.setRotation(this.Bar4, 0.0f, 0.0f, 0.0f);
        this.Box = new ModelRenderer((ModelBase)this, 33, 0);
        this.Box.addBox(0.0f, 0.0f, 0.0f, 4, 12, 3);
        this.Box.setRotationPoint(-2.0f, -1.0f, 2.0f);
        this.Box.setTextureSize(64, 32);
        this.Box.mirror = true;
        this.setRotation(this.Box, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.a.render(f5);
        this.Bar1.render(f5);
        this.Bar2.render(f5);
        this.Bar3.render(f5);
        this.Bar4.render(f5);
        this.Box.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 57.295776f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        if (this.isRiding) {
            this.rightarm.rotateAngleX += -0.62831855f;
            this.leftarm.rotateAngleX += -0.62831855f;
            this.rightleg.rotateAngleX = -1.2566371f;
            this.leftleg.rotateAngleX = -1.2566371f;
            this.rightleg.rotateAngleY = 0.31415927f;
            this.leftleg.rotateAngleY = -0.31415927f;
        }
        this.rightarm.rotateAngleY = 0.0f;
        this.leftarm.rotateAngleY = 0.0f;
        if (this.onGround > -9990.0f) {
            float var8 = this.onGround;
            this.body.rotateAngleY = MathHelper.sin((float)(MathHelper.sqrt_float((float)var8) * (float)Math.PI * 2.0f)) * 0.2f;
            this.rightarm.rotationPointZ = MathHelper.sin((float)this.body.rotateAngleY) * 5.0f;
            this.rightarm.rotationPointX = -MathHelper.cos((float)this.body.rotateAngleY) * 5.0f;
            this.leftarm.rotationPointZ = -MathHelper.sin((float)this.body.rotateAngleY) * 5.0f;
            this.leftarm.rotationPointX = MathHelper.cos((float)this.body.rotateAngleY) * 5.0f;
            this.rightarm.rotateAngleY += this.body.rotateAngleY;
            this.leftarm.rotateAngleY += this.body.rotateAngleY;
            this.leftarm.rotateAngleX += this.body.rotateAngleY;
            var8 = 1.0f - this.onGround;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0f - var8;
            float var9 = MathHelper.sin((float)(var8 * (float)Math.PI));
            float var10 = MathHelper.sin((float)(this.onGround * (float)Math.PI)) * (-this.head.rotateAngleX - 0.7f) * 0.75f;
            this.rightarm.rotateAngleX = (float)((double)this.rightarm.rotateAngleX - ((double)var9 * 1.2 + (double)var10));
            this.rightarm.rotateAngleY += this.body.rotateAngleY * 2.0f;
            this.rightarm.rotateAngleZ = MathHelper.sin((float)(this.onGround * (float)Math.PI)) * -0.4f;
        }
        this.body.rotateAngleX = 0.0f;
        this.rightleg.rotationPointZ = 0.1f;
        this.leftleg.rotationPointZ = 0.1f;
        this.rightleg.rotationPointY = 12.0f;
        this.leftleg.rotationPointY = 12.0f;
        this.head.rotationPointY = 0.0f;
        this.rightarm.rotateAngleZ += MathHelper.cos((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.rotateAngleZ -= MathHelper.cos((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.rotateAngleX += MathHelper.sin((float)(par3 * 0.067f)) * 0.05f;
        this.leftarm.rotateAngleX -= MathHelper.sin((float)(par3 * 0.067f)) * 0.05f;
    }
}

