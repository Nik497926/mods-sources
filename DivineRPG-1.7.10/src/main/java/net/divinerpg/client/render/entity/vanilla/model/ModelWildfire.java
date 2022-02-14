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

public class ModelWildfire
extends ModelBase {
    public ModelRenderer P1;
    public ModelRenderer P2;
    public ModelRenderer P3;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer P4;
    public ModelRenderer P5;
    public ModelRenderer P6;
    public ModelRenderer P7;
    public ModelRenderer P8;
    public ModelRenderer rightarm;
    public ModelRenderer P9;
    public ModelRenderer P10;
    public ModelRenderer P11;
    public ModelRenderer P12;
    public ModelRenderer P13;
    public ModelRenderer P14;
    public ModelRenderer P15;
    public ModelRenderer P16;
    public ModelRenderer P17;

    public ModelWildfire() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.P1 = new ModelRenderer((ModelBase)this, 32, 17);
        this.P1.addBox(-4.0f, -13.0f, -1.0f, 2, 8, 2);
        this.P1.setRotationPoint(6.0f, -6.0f, 9.0f);
        this.P1.setTextureSize(64, 32);
        this.P1.mirror = true;
        this.setRotation(this.P1, 0.7853982f, 0.0f, 0.0f);
        this.P2 = new ModelRenderer((ModelBase)this, 16, 16);
        this.P2.addBox(-4.0f, 0.0f, -2.0f, 5, 12, 4);
        this.P2.setRotationPoint(4.0f, 0.0f, 0.0f);
        this.P2.setTextureSize(64, 32);
        this.P2.mirror = true;
        this.setRotation(this.P2, 0.0f, 0.0f, 0.0f);
        this.P3 = new ModelRenderer((ModelBase)this, 40, 16);
        this.P3.addBox(-1.0f, -3.0f, -2.0f, 2, 4, 6);
        this.P3.setRotationPoint(5.0f, -1.0f, -1.0f);
        this.P3.setTextureSize(64, 32);
        this.P3.mirror = true;
        this.setRotation(this.P3, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(7.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.P4 = new ModelRenderer((ModelBase)this, 43, 18);
        this.P4.addBox(1.0f, -2.0f, -2.0f, 1, 2, 4);
        this.P4.setRotationPoint(4.0f, 2.0f, 0.0f);
        this.P4.setTextureSize(64, 32);
        this.P4.mirror = true;
        this.setRotation(this.P4, 0.0f, 0.0f, 0.0f);
        this.P5 = new ModelRenderer((ModelBase)this, 40, 16);
        this.P5.addBox(-2.0f, -3.0f, -2.0f, 6, 2, 4);
        this.P5.setRotationPoint(7.0f, 1.0f, 0.0f);
        this.P5.setTextureSize(64, 32);
        this.P5.mirror = true;
        this.setRotation(this.P5, 0.0f, 0.0f, 0.0f);
        this.P6 = new ModelRenderer((ModelBase)this, 40, 16);
        this.P6.addBox(-2.0f, -3.0f, -2.0f, 6, 2, 4);
        this.P6.setRotationPoint(-9.0f, 1.0f, 0.0f);
        this.P6.setTextureSize(64, 32);
        this.P6.mirror = true;
        this.setRotation(this.P6, 0.0f, 0.0f, 0.0f);
        this.P7 = new ModelRenderer((ModelBase)this, 40, 16);
        this.P7.addBox(-1.0f, -3.0f, -2.0f, 2, 4, 6);
        this.P7.setRotationPoint(-5.0f, -1.0f, -1.0f);
        this.P7.setTextureSize(64, 32);
        this.P7.mirror = true;
        this.setRotation(this.P7, 0.0f, 0.0f, 0.0f);
        this.P8 = new ModelRenderer((ModelBase)this, 43, 18);
        this.P8.addBox(1.0f, -2.0f, -2.0f, 1, 2, 4);
        this.P8.setRotationPoint(-7.0f, 2.0f, 0.0f);
        this.P8.setTextureSize(64, 32);
        this.P8.mirror = true;
        this.setRotation(this.P8, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-7.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.P9 = new ModelRenderer((ModelBase)this, 23, 19);
        this.P9.addBox(-3.0f, -2.0f, -2.0f, 3, 9, 2);
        this.P9.setRotationPoint(-10.0f, -1.0f, 1.0f);
        this.P9.setTextureSize(64, 32);
        this.P9.mirror = true;
        this.setRotation(this.P9, 0.0f, 0.0f, 2.530727f);
        this.P10 = new ModelRenderer((ModelBase)this, 23, 19);
        this.P10.addBox(-3.0f, -2.0f, -2.0f, 3, 9, 2);
        this.P10.setRotationPoint(8.0f, -3.0f, 1.0f);
        this.P10.setTextureSize(64, 32);
        this.P10.mirror = true;
        this.setRotation(this.P10, 0.0f, 0.0f, -2.530727f);
        this.P11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.P11.addBox(-3.0f, -8.0f, -4.0f, 6, 12, 6);
        this.P11.setRotationPoint(0.0f, 1.0f, 0.0f);
        this.P11.setTextureSize(64, 32);
        this.P11.mirror = true;
        this.setRotation(this.P11, -0.3490659f, 0.0f, 0.0f);
        this.P12 = new ModelRenderer((ModelBase)this, 32, 17);
        this.P12.addBox(-4.0f, -13.0f, -1.0f, 2, 8, 2);
        this.P12.setRotationPoint(0.0f, -6.0f, 9.0f);
        this.P12.setTextureSize(64, 32);
        this.P12.mirror = true;
        this.setRotation(this.P12, 0.7853982f, 0.0f, 0.0f);
        this.P13 = new ModelRenderer((ModelBase)this, 19, 22);
        this.P13.addBox(-5.0f, -14.0f, -1.0f, 4, 4, 4);
        this.P13.setRotationPoint(6.0f, 1.0f, -1.0f);
        this.P13.setTextureSize(64, 32);
        this.P13.mirror = true;
        this.setRotation(this.P13, -0.3490659f, 0.0f, 0.0f);
        this.P14 = new ModelRenderer((ModelBase)this, 19, 22);
        this.P14.addBox(-5.0f, -14.0f, -1.0f, 4, 4, 4);
        this.P14.setRotationPoint(0.0f, 1.0f, -1.0f);
        this.P14.setTextureSize(64, 32);
        this.P14.mirror = true;
        this.setRotation(this.P14, -0.3490659f, 0.0f, 0.0f);
        this.P15 = new ModelRenderer((ModelBase)this, 32, 17);
        this.P15.addBox(-4.0f, -13.0f, -1.0f, 2, 8, 2);
        this.P15.setRotationPoint(0.0f, 2.0f, 0.0f);
        this.P15.setTextureSize(64, 32);
        this.P15.mirror = true;
        this.setRotation(this.P15, -0.3490659f, 0.0f, 0.0f);
        this.P16 = new ModelRenderer((ModelBase)this, 32, 17);
        this.P16.addBox(-4.0f, -13.0f, -1.0f, 2, 8, 2);
        this.P16.setRotationPoint(6.0f, 2.0f, 0.0f);
        this.P16.setTextureSize(64, 32);
        this.P16.mirror = true;
        this.setRotation(this.P16, -0.3490659f, 0.0f, 0.0f);
        this.P17 = new ModelRenderer((ModelBase)this, 16, 16);
        this.P17.addBox(-4.0f, 0.0f, -2.0f, 5, 12, 4);
        this.P17.setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.P17.setTextureSize(64, 32);
        this.P17.mirror = true;
        this.setRotation(this.P17, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.P1.render(f5);
        this.P2.render(f5);
        this.P3.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.P4.render(f5);
        this.P5.render(f5);
        this.P6.render(f5);
        this.P7.render(f5);
        this.P8.render(f5);
        this.rightarm.render(f5);
        this.P9.render(f5);
        this.P10.render(f5);
        this.P11.render(f5);
        this.P12.render(f5);
        this.P13.render(f5);
        this.P14.render(f5);
        this.P15.render(f5);
        this.P16.render(f5);
        this.P17.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.rightarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        this.rightarm.rotateAngleY = 0.0f;
        this.leftarm.rotateAngleY = 0.0f;
        this.rightarm.rotateAngleZ += MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.rotateAngleZ -= MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.rotateAngleX += MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
        this.leftarm.rotateAngleX -= MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
    }
}

