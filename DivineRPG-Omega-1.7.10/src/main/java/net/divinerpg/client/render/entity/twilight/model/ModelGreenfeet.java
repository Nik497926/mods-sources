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

public class ModelGreenfeet
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leftarmbox;
    ModelRenderer leftarm;
    ModelRenderer rightlegext;
    ModelRenderer leftlegext;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer rightarm;
    ModelRenderer rightarmbox;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public ModelGreenfeet() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 2.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 25, 16);
        this.body.addBox(-7.0f, 0.0f, -2.0f, 14, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.4461433f, 0.0f, 0.0f);
        this.leftarmbox = new ModelRenderer((ModelBase)this, 32, 0);
        this.leftarmbox.addBox(-2.0f, 10.0f, -3.0f, 6, 4, 6);
        this.leftarmbox.setRotationPoint(8.0f, 2.0f, 3.0f);
        this.leftarmbox.setTextureSize(64, 32);
        this.leftarmbox.mirror = true;
        this.setRotation(this.leftarmbox, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(8.0f, 2.0f, 3.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightlegext = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegext.addBox(-2.0f, 0.0f, -6.0f, 4, 3, 8);
        this.rightlegext.setRotationPoint(-4.0f, 9.0f, 4.0f);
        this.rightlegext.setTextureSize(64, 32);
        this.rightlegext.mirror = true;
        this.setRotation(this.rightlegext, 0.0f, 0.0f, 0.0f);
        this.leftlegext = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegext.addBox(-2.0f, 0.0f, -6.0f, 4, 3, 8);
        this.leftlegext.setRotationPoint(4.0f, 9.0f, 4.0f);
        this.leftlegext.setTextureSize(64, 32);
        this.leftlegext.mirror = true;
        this.setRotation(this.leftlegext, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 3.0f, -6.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(4.0f, 9.0f, 4.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 3.0f, -6.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-4.0f, 9.0f, 4.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-8.0f, 2.0f, 3.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.rightarmbox = new ModelRenderer((ModelBase)this, 32, 0);
        this.rightarmbox.addBox(-4.0f, 10.0f, -3.0f, 6, 4, 6);
        this.rightarmbox.setRotationPoint(-8.0f, 2.0f, 3.0f);
        this.rightarmbox.setTextureSize(64, 32);
        this.rightarmbox.mirror = true;
        this.setRotation(this.rightarmbox, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Shape1.addBox(-5.0f, 0.0f, 0.0f, 2, 9, 1);
        this.Shape1.setRotationPoint(-1.0f, -3.0f, 10.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, -0.5948578f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Shape2.addBox(-5.0f, 0.0f, 0.0f, 2, 9, 1);
        this.Shape2.setRotationPoint(-1.0f, 0.0f, 12.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.5948578f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 2, 9, 1);
        this.Shape3.setRotationPoint(-1.0f, -3.0f, 10.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.5948578f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Shape4.addBox(0.0f, 0.0f, 0.0f, 2, 9, 1);
        this.Shape4.setRotationPoint(-1.0f, 0.0f, 12.0f);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.5948578f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Shape5.addBox(5.0f, 0.0f, 0.0f, 2, 9, 1);
        this.Shape5.setRotationPoint(-1.0f, -3.0f, 10.0f);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.5948578f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 57, 0);
        this.Shape6.addBox(5.0f, 0.0f, 0.0f, 2, 9, 1);
        this.Shape6.setRotationPoint(-1.0f, 0.0f, 12.0f);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, -0.5948578f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.leftarmbox.render(f5);
        this.leftarm.render(f5);
        this.rightlegext.render(f5);
        this.leftlegext.render(f5);
        this.leftleg.render(f5);
        this.rightleg.render(f5);
        this.rightarm.render(f5);
        this.rightarmbox.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head.rotateAngleY = var4 / 57.295776f;
        this.head.rotateAngleX = var5 / 57.295776f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.rightarmbox.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.leftarmbox.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.rightarmbox.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.leftarmbox.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.rightlegext.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.leftlegext.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.rightlegext.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        this.leftlegext.rotateAngleY = 0.0f;
        this.rightarm.rotateAngleY = 0.0f;
        this.rightarmbox.rotateAngleY = 0.0f;
        this.leftarm.rotateAngleY = 0.0f;
        this.leftarmbox.rotateAngleY = 0.0f;
        this.rightarm.rotateAngleZ += MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.rotateAngleZ -= MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarmbox.rotateAngleZ += MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarmbox.rotateAngleZ -= MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.rotateAngleX += MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
        this.leftarm.rotateAngleX -= MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
        this.rightarmbox.rotateAngleX += MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
        this.leftarmbox.rotateAngleX -= MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
    }
}

