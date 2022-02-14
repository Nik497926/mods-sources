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

public class Shadahier
extends ModelBase {
    ModelRenderer part1;
    ModelRenderer part2;
    ModelRenderer part3;
    ModelRenderer part4;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer rightarm1;
    ModelRenderer leftarm1;
    ModelRenderer leftarm2;
    ModelRenderer rightarm2;
    ModelRenderer leftarm3;
    ModelRenderer rightarm3;
    ModelRenderer leftarm4;
    ModelRenderer rightarm4;
    ModelRenderer leftarm5;
    ModelRenderer rightarm5;
    ModelRenderer leftarm6;
    ModelRenderer rightarm6;
    ModelRenderer part5;
    ModelRenderer part6;
    ModelRenderer part7;
    ModelRenderer part8;
    ModelRenderer part9;
    ModelRenderer part10;
    ModelRenderer part11;
    ModelRenderer part12;
    ModelRenderer part13;
    ModelRenderer part14;

    public Shadahier() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.part1 = new ModelRenderer((ModelBase)this, 56, 26);
        this.part1.addBox(8.0f, -9.0f, -3.0f, 2, 4, 2);
        this.part1.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part1.setTextureSize(64, 32);
        this.part1.mirror = true;
        this.setRotation(this.part1, 0.0f, 0.0f, 0.0f);
        this.part2 = new ModelRenderer((ModelBase)this, 16, 16);
        this.part2.addBox(-4.0f, 0.0f, -2.0f, 8, 7, 4);
        this.part2.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part2.setTextureSize(64, 32);
        this.part2.mirror = true;
        this.setRotation(this.part2, 0.0f, 0.0f, 0.0f);
        this.part3 = new ModelRenderer((ModelBase)this, 33, 0);
        this.part3.addBox(-3.0f, -3.0f, -3.0f, 6, 4, 6);
        this.part3.setRotationPoint(-7.0f, 12.0f, 0.0f);
        this.part3.setTextureSize(64, 32);
        this.part3.mirror = true;
        this.setRotation(this.part3, 0.0f, 0.0f, 0.0f);
        this.part4 = new ModelRenderer((ModelBase)this, 33, 0);
        this.part4.addBox(-3.0f, -3.0f, -3.0f, 6, 4, 6);
        this.part4.setRotationPoint(7.0f, 12.0f, 0.0f);
        this.part4.setTextureSize(64, 32);
        this.part4.mirror = true;
        this.setRotation(this.part4, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 21);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
        this.rightleg.setRotationPoint(-3.0f, 17.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 21);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
        this.leftleg.setRotationPoint(3.0f, 17.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.rightarm1 = new ModelRenderer((ModelBase)this, 58, 0);
        this.rightarm1.addBox(-1.0f, 2.0f, 2.0f, 2, 8, 1);
        this.rightarm1.setRotationPoint(-7.0f, 12.0f, 0.0f);
        this.rightarm1.setTextureSize(64, 32);
        this.rightarm1.mirror = true;
        this.setRotation(this.rightarm1, 0.0f, 0.0f, 0.5235988f);
        this.leftarm1 = new ModelRenderer((ModelBase)this, 58, 0);
        this.leftarm1.addBox(-1.0f, 2.0f, 2.0f, 2, 8, 1);
        this.leftarm1.setRotationPoint(7.0f, 12.0f, 0.0f);
        this.leftarm1.setTextureSize(64, 32);
        this.leftarm1.mirror = true;
        this.setRotation(this.leftarm1, 0.0f, 0.0f, -0.5235988f);
        this.leftarm2 = new ModelRenderer((ModelBase)this, 40, 12);
        this.leftarm2.addBox(0.0f, 6.0f, -7.0f, 2, 2, 6);
        this.leftarm2.setRotationPoint(7.0f, 12.0f, 0.0f);
        this.leftarm2.setTextureSize(64, 32);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, 0.0f, 0.0f, 0.0f);
        this.rightarm2 = new ModelRenderer((ModelBase)this, 40, 12);
        this.rightarm2.addBox(-2.0f, 6.0f, -7.0f, 2, 2, 6);
        this.rightarm2.setRotationPoint(-7.0f, 12.0f, 0.0f);
        this.rightarm2.setTextureSize(64, 32);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, 0.0f, 0.0f, 0.0f);
        this.leftarm3 = new ModelRenderer((ModelBase)this, 58, 0);
        this.leftarm3.addBox(-1.0f, 2.0f, -1.0f, 2, 8, 1);
        this.leftarm3.setRotationPoint(7.0f, 12.0f, 1.0f);
        this.leftarm3.setTextureSize(64, 32);
        this.leftarm3.mirror = true;
        this.setRotation(this.leftarm3, 0.0f, 0.0f, -0.5235988f);
        this.rightarm3 = new ModelRenderer((ModelBase)this, 58, 0);
        this.rightarm3.addBox(-1.0f, 2.0f, -1.0f, 2, 8, 1);
        this.rightarm3.setRotationPoint(-7.0f, 12.0f, 1.0f);
        this.rightarm3.setTextureSize(64, 32);
        this.rightarm3.mirror = true;
        this.setRotation(this.rightarm3, 0.0f, 0.0f, 0.5235988f);
        this.leftarm4 = new ModelRenderer((ModelBase)this, 58, 0);
        this.leftarm4.addBox(-1.0f, 2.0f, -3.0f, 2, 8, 1);
        this.leftarm4.setRotationPoint(7.0f, 12.0f, 0.0f);
        this.leftarm4.setTextureSize(64, 32);
        this.leftarm4.mirror = true;
        this.setRotation(this.leftarm4, 0.0f, 0.0f, -0.5235988f);
        this.rightarm4 = new ModelRenderer((ModelBase)this, 58, 0);
        this.rightarm4.addBox(-1.0f, 2.0f, -3.0f, 2, 8, 1);
        this.rightarm4.setRotationPoint(-7.0f, 12.0f, 0.0f);
        this.rightarm4.setTextureSize(64, 32);
        this.rightarm4.mirror = true;
        this.setRotation(this.rightarm4, 0.0f, 0.0f, 0.5235988f);
        this.leftarm5 = new ModelRenderer((ModelBase)this, 58, 0);
        this.leftarm5.addBox(-1.0f, 2.0f, -1.0f, 2, 8, 1);
        this.leftarm5.setRotationPoint(7.0f, 12.0f, 0.0f);
        this.leftarm5.setTextureSize(64, 32);
        this.leftarm5.mirror = true;
        this.setRotation(this.leftarm5, 0.0f, 0.0f, -0.5235988f);
        this.rightarm5 = new ModelRenderer((ModelBase)this, 58, 0);
        this.rightarm5.addBox(-1.0f, 2.0f, -1.0f, 2, 8, 1);
        this.rightarm5.setRotationPoint(-7.0f, 12.0f, 0.0f);
        this.rightarm5.setTextureSize(64, 32);
        this.rightarm5.mirror = true;
        this.setRotation(this.rightarm5, 0.0f, 0.0f, 0.5235988f);
        this.leftarm6 = new ModelRenderer((ModelBase)this, 40, 20);
        this.leftarm6.addBox(-1.0f, 1.0f, -2.0f, 4, 8, 4);
        this.leftarm6.setRotationPoint(7.0f, 12.0f, 0.0f);
        this.leftarm6.setTextureSize(64, 32);
        this.leftarm6.mirror = true;
        this.setRotation(this.leftarm6, 0.0f, 0.0f, 0.0f);
        this.rightarm6 = new ModelRenderer((ModelBase)this, 40, 20);
        this.rightarm6.addBox(-3.0f, 1.0f, -2.0f, 4, 8, 4);
        this.rightarm6.setRotationPoint(-7.0f, 12.0f, 0.0f);
        this.rightarm6.setTextureSize(64, 32);
        this.rightarm6.mirror = true;
        this.setRotation(this.rightarm6, 0.0f, 0.0f, 0.0f);
        this.part5 = new ModelRenderer((ModelBase)this, 52, 11);
        this.part5.addBox(1.0f, -4.0f, -5.0f, 2, 2, 4);
        this.part5.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part5.setTextureSize(64, 32);
        this.part5.mirror = true;
        this.setRotation(this.part5, 0.7853982f, 0.0f, 0.0f);
        this.part6 = new ModelRenderer((ModelBase)this, 56, 26);
        this.part6.addBox(-10.0f, -9.0f, -3.0f, 2, 4, 2);
        this.part6.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part6.setTextureSize(64, 32);
        this.part6.mirror = true;
        this.setRotation(this.part6, 0.0f, 0.0f, 0.0f);
        this.part7 = new ModelRenderer((ModelBase)this, 33, 11);
        this.part7.addBox(4.0f, -7.0f, -3.0f, 4, 2, 2);
        this.part7.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part7.setTextureSize(64, 32);
        this.part7.mirror = true;
        this.setRotation(this.part7, 0.0f, 0.0f, 0.0f);
        this.part8 = new ModelRenderer((ModelBase)this, 33, 11);
        this.part8.addBox(-8.0f, -7.0f, -3.0f, 4, 2, 2);
        this.part8.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part8.setTextureSize(64, 32);
        this.part8.mirror = true;
        this.setRotation(this.part8, 0.0f, 0.0f, 0.0f);
        this.part9 = new ModelRenderer((ModelBase)this, 56, 26);
        this.part9.addBox(-10.0f, -9.0f, 1.0f, 2, 4, 2);
        this.part9.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part9.setTextureSize(64, 32);
        this.part9.mirror = true;
        this.setRotation(this.part9, 0.0f, 0.0f, 0.0f);
        this.part10 = new ModelRenderer((ModelBase)this, 33, 11);
        this.part10.addBox(-8.0f, -7.0f, 1.0f, 4, 2, 2);
        this.part10.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part10.setTextureSize(64, 32);
        this.part10.mirror = true;
        this.setRotation(this.part10, 0.0f, 0.0f, 0.0f);
        this.part11 = new ModelRenderer((ModelBase)this, 33, 11);
        this.part11.addBox(4.0f, -7.0f, 1.0f, 4, 2, 2);
        this.part11.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part11.setTextureSize(64, 32);
        this.part11.mirror = true;
        this.setRotation(this.part11, 0.0f, 0.0f, 0.0f);
        this.part12 = new ModelRenderer((ModelBase)this, 56, 26);
        this.part12.addBox(8.0f, -9.0f, 1.0f, 2, 4, 2);
        this.part12.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part12.setTextureSize(64, 32);
        this.part12.mirror = true;
        this.setRotation(this.part12, 0.0f, 0.0f, 0.0f);
        this.part13 = new ModelRenderer((ModelBase)this, 0, 0);
        this.part13.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.part13.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part13.setTextureSize(64, 32);
        this.part13.mirror = true;
        this.setRotation(this.part13, 0.0f, 0.0f, 0.0f);
        this.part14 = new ModelRenderer((ModelBase)this, 52, 11);
        this.part14.addBox(-3.0f, -4.0f, -5.0f, 2, 2, 4);
        this.part14.setRotationPoint(0.0f, 10.0f, 0.0f);
        this.part14.setTextureSize(64, 32);
        this.part14.mirror = true;
        this.setRotation(this.part14, 0.7853982f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.part1.render(f5);
        this.part2.render(f5);
        this.part3.render(f5);
        this.part4.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.rightarm1.render(f5);
        this.leftarm1.render(f5);
        this.leftarm2.render(f5);
        this.rightarm2.render(f5);
        this.leftarm3.render(f5);
        this.rightarm3.render(f5);
        this.leftarm4.render(f5);
        this.rightarm4.render(f5);
        this.leftarm5.render(f5);
        this.rightarm5.render(f5);
        this.leftarm6.render(f5);
        this.rightarm6.render(f5);
        this.part5.render(f5);
        this.part6.render(f5);
        this.part7.render(f5);
        this.part8.render(f5);
        this.part9.render(f5);
        this.part10.render(f5);
        this.part11.render(f5);
        this.part12.render(f5);
        this.part13.render(f5);
        this.part14.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarm3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarm4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarm5.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarm6.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarm3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarm4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarm5.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarm6.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
    }
}

