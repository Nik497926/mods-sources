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

public class ModelRazorback
extends ModelBase {
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer Spike1;
    ModelRenderer Spike2;
    ModelRenderer Spike3;
    ModelRenderer Spike4;
    ModelRenderer Spike5;
    ModelRenderer Spike6;
    ModelRenderer Spike7;
    ModelRenderer Spike8;
    ModelRenderer Spike9;
    ModelRenderer Spike10;

    public ModelRazorback() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 18, 4);
        this.body.addBox(0.0f, 0.0f, 0.0f, 8, 8, 3);
        this.body.setRotationPoint(-4.0f, 22.0f, -4.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.5707964f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 19);
        this.leg1.addBox(-2.0f, 0.0f, 0.0f, 1, 3, 1);
        this.leg1.setRotationPoint(-3.0f, 21.0f, 3.0f);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 19);
        this.leg2.addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.leg2.setRotationPoint(4.0f, 21.0f, 3.0f);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 19);
        this.leg3.addBox(-2.0f, 0.0f, -1.0f, 1, 3, 1);
        this.leg3.setRotationPoint(-3.0f, 21.0f, -3.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 19);
        this.leg4.addBox(0.0f, 0.0f, -1.0f, 1, 3, 1);
        this.leg4.setRotationPoint(4.0f, 21.0f, -3.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.Spike1 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike1.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike1.setRotationPoint(3.0f, 17.0f, 0.0f);
        this.Spike1.setTextureSize(64, 32);
        this.Spike1.mirror = true;
        this.setRotation(this.Spike1, 0.0f, 0.0f, 0.0f);
        this.Spike2 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike2.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike2.setRotationPoint(-3.0f, 17.0f, 0.0f);
        this.Spike2.setTextureSize(64, 32);
        this.Spike2.mirror = true;
        this.setRotation(this.Spike2, 0.0f, 0.0f, 0.0f);
        this.Spike3 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike3.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike3.setRotationPoint(-4.0f, 17.0f, -3.0f);
        this.Spike3.setTextureSize(64, 32);
        this.Spike3.mirror = true;
        this.setRotation(this.Spike3, 0.0f, 0.0f, 0.0f);
        this.Spike4 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike4.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike4.setRotationPoint(-4.0f, 17.0f, 2.0f);
        this.Spike4.setTextureSize(64, 32);
        this.Spike4.mirror = true;
        this.setRotation(this.Spike4, 0.0f, 0.0f, 0.0f);
        this.Spike5 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike5.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike5.setRotationPoint(2.0f, 17.0f, 2.0f);
        this.Spike5.setTextureSize(64, 32);
        this.Spike5.mirror = true;
        this.setRotation(this.Spike5, 0.0f, 0.0f, 0.0f);
        this.Spike6 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike6.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike6.setRotationPoint(2.0f, 17.0f, -2.0f);
        this.Spike6.setTextureSize(64, 32);
        this.Spike6.mirror = true;
        this.setRotation(this.Spike6, 0.0f, 0.0f, 0.0f);
        this.Spike7 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike7.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike7.setRotationPoint(0.0f, 17.0f, -3.0f);
        this.Spike7.setTextureSize(64, 32);
        this.Spike7.mirror = true;
        this.setRotation(this.Spike7, 0.0f, 0.0f, 0.0f);
        this.Spike8 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike8.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike8.setRotationPoint(-1.0f, 17.0f, -1.0f);
        this.Spike8.setTextureSize(64, 32);
        this.Spike8.mirror = true;
        this.setRotation(this.Spike8, 0.0f, 0.0f, 0.0f);
        this.Spike9 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike9.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike9.setRotationPoint(3.0f, 17.0f, -4.0f);
        this.Spike9.setTextureSize(64, 32);
        this.Spike9.mirror = true;
        this.setRotation(this.Spike9, 0.0f, 0.0f, 0.0f);
        this.Spike10 = new ModelRenderer((ModelBase)this, 0, 25);
        this.Spike10.addBox(0.0f, 0.0f, 0.0f, 1, 2, 1);
        this.Spike10.setRotationPoint(-1.0f, 17.0f, 3.0f);
        this.Spike10.setTextureSize(64, 32);
        this.Spike10.mirror = true;
        this.setRotation(this.Spike10, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.Spike1.render(f5);
        this.Spike2.render(f5);
        this.Spike3.render(f5);
        this.Spike4.render(f5);
        this.Spike5.render(f5);
        this.Spike6.render(f5);
        this.Spike7.render(f5);
        this.Spike8.render(f5);
        this.Spike9.render(f5);
        this.Spike10.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity f6) {
        this.leg1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.leg3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leg4.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
    }
}

