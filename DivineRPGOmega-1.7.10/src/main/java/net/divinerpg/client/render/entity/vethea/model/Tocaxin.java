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

public class Tocaxin
extends ModelBase {
    ModelRenderer part1;
    ModelRenderer part2;
    ModelRenderer rightarm1;
    ModelRenderer leftarm1;
    ModelRenderer leftleg1;
    ModelRenderer rightleg1;
    ModelRenderer leftleg2;
    ModelRenderer rightleg2;
    ModelRenderer part3;
    ModelRenderer part4;
    ModelRenderer part5;
    ModelRenderer part6;
    ModelRenderer rightarm2;
    ModelRenderer leftarm2;
    ModelRenderer rightarm3;
    ModelRenderer leftarm3;
    ModelRenderer part7;
    ModelRenderer part8;
    ModelRenderer part9;
    ModelRenderer part10;
    ModelRenderer part11;

    public Tocaxin() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.part1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part1.addBox(-4.0f, -10.0f, -4.0f, 8, 8, 8);
        this.part1.setRotationPoint(0.0f, -22.0f, -7.0f);
        this.part1.setTextureSize(128, 32);
        this.part1.mirror = true;
        this.setRotation(this.part1, 0.4014257f, 0.0f, 0.0f);
        this.part2 = new ModelRenderer((ModelBase)this, 34, 2);
        this.part2.addBox(-2.0f, -15.0f, -4.0f, 4, 6, 2);
        this.part2.setRotationPoint(0.0f, -12.0f, 0.0f);
        this.part2.setTextureSize(128, 32);
        this.part2.mirror = true;
        this.setRotation(this.part2, 0.4014257f, 0.0f, 0.0f);
        this.rightarm1 = new ModelRenderer((ModelBase)this, 64, 0);
        this.rightarm1.addBox(-2.0f, 8.0f, 4.0f, 2, 2, 30);
        this.rightarm1.setRotationPoint(-11.0f, -10.0f, 0.0f);
        this.rightarm1.setTextureSize(128, 32);
        this.rightarm1.mirror = true;
        this.setRotation(this.rightarm1, -1.570796f, 0.0f, 0.0f);
        this.leftarm1 = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm1.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm1.setRotationPoint(11.0f, -10.0f, 0.0f);
        this.leftarm1.setTextureSize(128, 32);
        this.leftarm1.mirror = true;
        this.setRotation(this.leftarm1, -1.047198f, 0.0f, 0.0f);
        this.leftleg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg1.addBox(-2.0f, 12.0f, -1.0f, 2, 12, 2);
        this.leftleg1.setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.leftleg1.setTextureSize(128, 32);
        this.leftleg1.mirror = true;
        this.setRotation(this.leftleg1, 0.0f, 0.0f, 0.0f);
        this.rightleg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg1.addBox(-2.0f, 12.0f, -1.0f, 2, 12, 2);
        this.rightleg1.setRotationPoint(3.0f, 0.0f, 0.0f);
        this.rightleg1.setTextureSize(128, 32);
        this.rightleg1.mirror = true;
        this.setRotation(this.rightleg1, 0.0f, 0.0f, 0.0f);
        this.leftleg2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg2.addBox(-2.0f, 0.0f, -1.0f, 2, 12, 2);
        this.leftleg2.setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.leftleg2.setTextureSize(128, 32);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.0f, 0.0f, 0.0f);
        this.rightleg2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg2.addBox(-2.0f, 0.0f, -1.0f, 2, 12, 2);
        this.rightleg2.setRotationPoint(3.0f, 0.0f, 0.0f);
        this.rightleg2.setTextureSize(128, 32);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.0f, 0.0f, 0.0f);
        this.part3 = new ModelRenderer((ModelBase)this, 46, 9);
        this.part3.addBox(-7.0f, 0.0f, 0.0f, 20, 3, 2);
        this.part3.setRotationPoint(-3.0f, -12.0f, -4.0f);
        this.part3.setTextureSize(128, 32);
        this.part3.mirror = true;
        this.setRotation(this.part3, 0.4014257f, 0.0f, 0.0f);
        this.part4 = new ModelRenderer((ModelBase)this, 68, 15);
        this.part4.addBox(-4.0f, -12.0f, -2.0f, 8, 12, 2);
        this.part4.setRotationPoint(0.0f, -12.0f, -4.0f);
        this.part4.setTextureSize(128, 32);
        this.part4.mirror = true;
        this.setRotation(this.part4, 0.4014257f, 0.0f, 0.0f);
        this.part5 = new ModelRenderer((ModelBase)this, 59, 2);
        this.part5.addBox(-4.0f, -4.0f, 0.0f, 14, 3, 2);
        this.part5.setRotationPoint(-3.0f, -12.0f, -4.0f);
        this.part5.setTextureSize(128, 32);
        this.part5.mirror = true;
        this.setRotation(this.part5, 0.4014257f, 0.0f, 0.0f);
        this.part6 = new ModelRenderer((ModelBase)this, 59, 2);
        this.part6.addBox(-4.0f, -9.0f, 0.0f, 14, 3, 2);
        this.part6.setRotationPoint(-3.0f, -12.0f, -4.0f);
        this.part6.setTextureSize(128, 32);
        this.part6.mirror = true;
        this.setRotation(this.part6, 0.4014257f, 0.0f, 0.0f);
        this.rightarm2 = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm2.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm2.setRotationPoint(-11.0f, -10.0f, 0.0f);
        this.rightarm2.setTextureSize(128, 32);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, -1.047198f, 0.0f, 0.0f);
        this.leftarm2 = new ModelRenderer((ModelBase)this, 64, 0);
        this.leftarm2.addBox(0.0f, 8.0f, 4.0f, 2, 2, 30);
        this.leftarm2.setRotationPoint(11.0f, -10.0f, 0.0f);
        this.leftarm2.setTextureSize(128, 32);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, -1.570796f, 0.0f, 0.0f);
        this.rightarm3 = new ModelRenderer((ModelBase)this, 100, 16);
        this.rightarm3.addBox(-4.0f, 6.0f, 1.0f, 6, 6, 6);
        this.rightarm3.setRotationPoint(-11.0f, -10.0f, 0.0f);
        this.rightarm3.setTextureSize(128, 32);
        this.rightarm3.mirror = true;
        this.setRotation(this.rightarm3, -1.570796f, 0.0f, 0.0f);
        this.leftarm3 = new ModelRenderer((ModelBase)this, 100, 16);
        this.leftarm3.addBox(-2.0f, 6.0f, 1.0f, 6, 6, 6);
        this.leftarm3.setRotationPoint(11.0f, -10.0f, 0.0f);
        this.leftarm3.setTextureSize(128, 32);
        this.leftarm3.mirror = true;
        this.setRotation(this.leftarm3, -1.570796f, 0.0f, 0.0f);
        this.part7 = new ModelRenderer((ModelBase)this, 41, 16);
        this.part7.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 2);
        this.part7.setRotationPoint(0.0f, -12.0f, 0.0f);
        this.part7.setTextureSize(128, 32);
        this.part7.mirror = true;
        this.setRotation(this.part7, 0.4014257f, 0.0f, 0.0f);
        this.part8 = new ModelRenderer((ModelBase)this, 41, 16);
        this.part8.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 2);
        this.part8.setRotationPoint(0.0f, -12.0f, -4.0f);
        this.part8.setTextureSize(128, 32);
        this.part8.mirror = true;
        this.setRotation(this.part8, 0.4014257f, 0.0f, 0.0f);
        this.part9 = new ModelRenderer((ModelBase)this, 59, 2);
        this.part9.addBox(-4.0f, 10.0f, 0.0f, 14, 3, 2);
        this.part9.setRotationPoint(-3.0f, -12.0f, -4.0f);
        this.part9.setTextureSize(128, 32);
        this.part9.mirror = true;
        this.setRotation(this.part9, 0.4014257f, 0.0f, 0.0f);
        this.part10 = new ModelRenderer((ModelBase)this, 59, 2);
        this.part10.addBox(-4.0f, 5.0f, 0.0f, 14, 3, 2);
        this.part10.setRotationPoint(-3.0f, -12.0f, -4.0f);
        this.part10.setTextureSize(128, 32);
        this.part10.mirror = true;
        this.setRotation(this.part10, 0.4014257f, 0.0f, 0.0f);
        this.part11 = new ModelRenderer((ModelBase)this, 68, 15);
        this.part11.addBox(-4.0f, -12.0f, -2.0f, 8, 12, 2);
        this.part11.setRotationPoint(0.0f, -12.0f, 0.0f);
        this.part11.setTextureSize(128, 32);
        this.part11.mirror = true;
        this.setRotation(this.part11, 0.4014257f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.part1.render(f5);
        this.part2.render(f5);
        this.rightarm1.render(f5);
        this.leftarm1.render(f5);
        this.leftleg1.render(f5);
        this.rightleg1.render(f5);
        this.leftleg2.render(f5);
        this.rightleg2.render(f5);
        this.part3.render(f5);
        this.part4.render(f5);
        this.part5.render(f5);
        this.part6.render(f5);
        this.rightarm2.render(f5);
        this.leftarm2.render(f5);
        this.rightarm3.render(f5);
        this.leftarm3.render(f5);
        this.part7.render(f5);
        this.part8.render(f5);
        this.part9.render(f5);
        this.part10.render(f5);
        this.part11.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftleg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftleg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightleg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightleg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f + -1.570796f;
        this.rightarm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f - 1.047198f;
        this.rightarm3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f - 1.570796f;
        this.leftarm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - 1.047198f;
        this.leftarm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - 1.570796f;
        this.leftarm3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f - 1.570796f;
    }
}

