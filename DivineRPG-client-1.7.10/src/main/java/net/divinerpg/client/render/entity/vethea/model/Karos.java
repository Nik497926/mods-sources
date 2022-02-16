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

public class Karos
extends ModelBase {
    ModelRenderer head1;
    ModelRenderer body1;
    ModelRenderer rightarm1;
    ModelRenderer leftarm1;
    ModelRenderer head2;
    ModelRenderer rightarm2;
    ModelRenderer leftarm2;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;
    ModelRenderer body9;
    ModelRenderer body10;
    ModelRenderer body11;
    ModelRenderer body12;
    ModelRenderer body13;
    ModelRenderer body14;
    ModelRenderer body15;
    ModelRenderer body16;
    ModelRenderer body17;
    ModelRenderer body18;
    ModelRenderer body19;
    ModelRenderer body20;
    ModelRenderer body21;
    ModelRenderer body22;
    ModelRenderer body23;

    public Karos() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head1.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head1.setRotationPoint(8.0f, -11.0f, 0.0f);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.body1 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body1.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body1.setRotationPoint(4.0f, -1.0f, 2.0f);
        this.body1.setTextureSize(64, 32);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0f, 0.0f, 0.0f);
        this.rightarm1 = new ModelRenderer((ModelBase)this, 40, 0);
        this.rightarm1.addBox(-4.0f, -4.0f, -3.0f, 6, 4, 6);
        this.rightarm1.setRotationPoint(-10.0f, 2.0f, 0.0f);
        this.rightarm1.setTextureSize(64, 32);
        this.rightarm1.mirror = true;
        this.setRotation(this.rightarm1, 0.0f, 0.0f, 0.0f);
        this.leftarm1 = new ModelRenderer((ModelBase)this, 40, 0);
        this.leftarm1.addBox(-2.0f, -4.0f, -3.0f, 6, 4, 6);
        this.leftarm1.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarm1.setTextureSize(64, 32);
        this.leftarm1.mirror = true;
        this.setRotation(this.leftarm1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head2.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head2.setRotationPoint(-8.0f, -11.0f, 0.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.rightarm2 = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm2.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm2.setRotationPoint(-10.0f, 2.0f, 0.0f);
        this.rightarm2.setTextureSize(64, 32);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, 0.0f, 0.0f, 0.0f);
        this.leftarm2 = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm2.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm2.setRotationPoint(10.0f, 2.0f, 0.0f);
        this.leftarm2.setTextureSize(64, 32);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body2.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body2.setRotationPoint(0.0f, -7.0f, 2.0f);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body3.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body3.setRotationPoint(-4.0f, -1.0f, 2.0f);
        this.body3.setTextureSize(64, 32);
        this.body3.mirror = true;
        this.setRotation(this.body3, 0.0f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body4.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body4.setRotationPoint(-4.0f, -1.0f, -2.0f);
        this.body4.setTextureSize(64, 32);
        this.body4.mirror = true;
        this.setRotation(this.body4, 0.0f, 0.0f, 0.0f);
        this.body5 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body5.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body5.setRotationPoint(4.0f, -1.0f, -2.0f);
        this.body5.setTextureSize(64, 32);
        this.body5.mirror = true;
        this.setRotation(this.body5, 0.0f, 0.0f, 0.0f);
        this.body6 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body6.addBox(1.0f, -2.0f, -3.0f, 4, 4, 4);
        this.body6.setRotationPoint(6.0f, -6.0f, 1.0f);
        this.body6.setTextureSize(64, 32);
        this.body6.mirror = true;
        this.setRotation(this.body6, 0.0f, 0.0f, -0.6108652f);
        this.body7 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body7.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body7.setRotationPoint(7.0f, 11.0f, 5.0f);
        this.body7.setTextureSize(64, 32);
        this.body7.mirror = true;
        this.setRotation(this.body7, 0.0f, 0.0f, 0.0f);
        this.body8 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body8.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body8.setRotationPoint(0.0f, 5.0f, 2.0f);
        this.body8.setTextureSize(64, 32);
        this.body8.mirror = true;
        this.setRotation(this.body8, 0.0f, 0.0f, 0.0f);
        this.body9 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body9.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body9.setRotationPoint(0.0f, 5.0f, -2.0f);
        this.body9.setTextureSize(64, 32);
        this.body9.mirror = true;
        this.setRotation(this.body9, 0.0f, 0.0f, 0.0f);
        this.body10 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body10.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body10.setRotationPoint(0.0f, 11.0f, -2.0f);
        this.body10.setTextureSize(64, 32);
        this.body10.mirror = true;
        this.setRotation(this.body10, 0.0f, 0.0f, 0.0f);
        this.body11 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body11.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body11.setRotationPoint(2.0f, 11.0f, 3.0f);
        this.body11.setTextureSize(64, 32);
        this.body11.mirror = true;
        this.setRotation(this.body11, 0.0f, 0.0f, 0.0f);
        this.body12 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body12.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body12.setRotationPoint(1.0f, 11.0f, 5.0f);
        this.body12.setTextureSize(64, 32);
        this.body12.mirror = true;
        this.setRotation(this.body12, 0.0f, 0.0f, 0.0f);
        this.body13 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body13.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body13.setRotationPoint(5.0f, 11.0f, 4.0f);
        this.body13.setTextureSize(64, 32);
        this.body13.mirror = true;
        this.setRotation(this.body13, 0.0f, 0.0f, 0.0f);
        this.body14 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body14.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body14.setRotationPoint(6.0f, 11.0f, 0.0f);
        this.body14.setTextureSize(64, 32);
        this.body14.mirror = true;
        this.setRotation(this.body14, 0.0f, 0.0f, 0.0f);
        this.body15 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body15.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body15.setRotationPoint(4.0f, 11.0f, 1.0f);
        this.body15.setTextureSize(64, 32);
        this.body15.mirror = true;
        this.setRotation(this.body15, 0.0f, 0.0f, 0.0f);
        this.body16 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body16.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body16.setRotationPoint(7.0f, 11.0f, -2.0f);
        this.body16.setTextureSize(64, 32);
        this.body16.mirror = true;
        this.setRotation(this.body16, 0.0f, 0.0f, 0.0f);
        this.body17 = new ModelRenderer((ModelBase)this, 27, 0);
        this.body17.addBox(-4.0f, 0.0f, -2.0f, 1, 6, 1);
        this.body17.setRotationPoint(3.0f, 11.0f, -1.0f);
        this.body17.setTextureSize(64, 32);
        this.body17.mirror = true;
        this.setRotation(this.body17, 0.0f, 0.0f, 0.0f);
        this.body18 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body18.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.body18.setRotationPoint(0.0f, -7.0f, -2.0f);
        this.body18.setTextureSize(64, 32);
        this.body18.mirror = true;
        this.setRotation(this.body18, 0.0f, 0.0f, 0.0f);
        this.body19 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body19.addBox(-5.0f, -2.0f, -3.0f, 4, 4, 4);
        this.body19.setRotationPoint(-5.0f, -6.0f, 1.0f);
        this.body19.setTextureSize(64, 32);
        this.body19.mirror = true;
        this.setRotation(this.body19, 0.0f, 0.0f, 0.6108652f);
        this.body20 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body20.addBox(-4.0f, -1.0f, -2.0f, 7, 2, 2);
        this.body20.setRotationPoint(6.0f, -6.0f, 1.0f);
        this.body20.setTextureSize(64, 32);
        this.body20.mirror = true;
        this.setRotation(this.body20, 0.0f, 0.0f, -0.6108652f);
        this.body21 = new ModelRenderer((ModelBase)this, 32, 11);
        this.body21.addBox(-4.0f, -1.0f, -2.0f, 5, 2, 2);
        this.body21.setRotationPoint(9.0f, -11.0f, 1.0f);
        this.body21.setTextureSize(64, 32);
        this.body21.mirror = true;
        this.setRotation(this.body21, 0.0f, 0.0f, -1.570796f);
        this.body22 = new ModelRenderer((ModelBase)this, 16, 16);
        this.body22.addBox(-4.0f, -1.0f, -2.0f, 7, 2, 2);
        this.body22.setRotationPoint(-5.0f, -6.0f, 1.0f);
        this.body22.setTextureSize(64, 32);
        this.body22.mirror = true;
        this.setRotation(this.body22, 0.0f, 0.0f, 0.6108652f);
        this.body23 = new ModelRenderer((ModelBase)this, 32, 11);
        this.body23.addBox(-4.0f, -1.0f, -2.0f, 5, 2, 2);
        this.body23.setRotationPoint(-8.0f, -11.0f, 1.0f);
        this.body23.setTextureSize(64, 32);
        this.body23.mirror = true;
        this.setRotation(this.body23, 0.0f, 0.0f, -1.570796f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head1.render(f5);
        this.body1.render(f5);
        this.rightarm1.render(f5);
        this.leftarm1.render(f5);
        this.head2.render(f5);
        this.rightarm2.render(f5);
        this.leftarm2.render(f5);
        this.body2.render(f5);
        this.body3.render(f5);
        this.body4.render(f5);
        this.body5.render(f5);
        this.body6.render(f5);
        this.body7.render(f5);
        this.body8.render(f5);
        this.body9.render(f5);
        this.body10.render(f5);
        this.body11.render(f5);
        this.body12.render(f5);
        this.body13.render(f5);
        this.body14.render(f5);
        this.body15.render(f5);
        this.body16.render(f5);
        this.body17.render(f5);
        this.body18.render(f5);
        this.body19.render(f5);
        this.body20.render(f5);
        this.body21.render(f5);
        this.body22.render(f5);
        this.body23.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.rightarm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarm1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarm2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
    }
}

