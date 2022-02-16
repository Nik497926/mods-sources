/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSoulSpider
extends ModelBase {
    ModelRenderer body;
    ModelRenderer leg1top;
    ModelRenderer leg1middle;
    ModelRenderer leg1bottom;
    ModelRenderer leg2top;
    ModelRenderer leg2middle;
    ModelRenderer leg2bottom;
    ModelRenderer leg3top;
    ModelRenderer leg4top;
    ModelRenderer leg3middle;
    ModelRenderer leg4middle;
    ModelRenderer leg3bottom;
    ModelRenderer leg4bottom;

    public ModelSoulSpider() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 5, 3, 7);
        this.body.setRotationPoint(-2.5f, 17.0f, -3.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.leg1top = new ModelRenderer((ModelBase)this, 0, 10);
        this.leg1top.addBox(0.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg1top.setRotationPoint(-2.0f, 18.0f, -1.0f);
        this.leg1top.setTextureSize(64, 32);
        this.leg1top.mirror = true;
        this.setRotation(this.leg1top, 0.0f, 0.0f, 0.8726646f);
        this.leg1middle = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1middle.addBox(-0.5f, 3.0f, -0.5f, 1, 2, 1);
        this.leg1middle.setRotationPoint(-1.5f, 18.2f, -1.0f);
        this.leg1middle.setTextureSize(64, 32);
        this.leg1middle.mirror = true;
        this.setRotation(this.leg1middle, 0.0f, 0.0f, 0.6108652f);
        this.leg1bottom = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1bottom.addBox(-1.5f, 4.5f, -0.5f, 1, 2, 1);
        this.leg1bottom.setRotationPoint(-1.75f, 18.3f, -1.0f);
        this.leg1bottom.setTextureSize(64, 32);
        this.leg1bottom.mirror = true;
        this.setRotation(this.leg1bottom, 0.0f, 0.0f, 0.3490659f);
        this.leg2top = new ModelRenderer((ModelBase)this, 0, 10);
        this.leg2top.addBox(0.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg2top.setRotationPoint(-2.0f, 18.0f, 2.0f);
        this.leg2top.setTextureSize(64, 32);
        this.leg2top.mirror = true;
        this.setRotation(this.leg2top, 0.0f, 0.0f, 0.8726646f);
        this.leg2middle = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg2middle.addBox(-0.5f, 3.0f, -0.5f, 1, 2, 1);
        this.leg2middle.setRotationPoint(-1.5f, 18.2f, 2.0f);
        this.leg2middle.setTextureSize(64, 32);
        this.leg2middle.mirror = true;
        this.setRotation(this.leg2middle, 0.0f, 0.0f, 0.6108652f);
        this.leg2bottom = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg2bottom.addBox(-1.5f, 4.5f, -0.5f, 1, 2, 1);
        this.leg2bottom.setRotationPoint(-1.8f, 18.3f, 2.0f);
        this.leg2bottom.setTextureSize(64, 32);
        this.leg2bottom.mirror = true;
        this.setRotation(this.leg2bottom, 0.0f, 0.0f, 0.3490659f);
        this.leg3top = new ModelRenderer((ModelBase)this, 0, 10);
        this.leg3top.addBox(-2.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg3top.setRotationPoint(2.0f, 18.0f, -1.0f);
        this.leg3top.setTextureSize(64, 32);
        this.leg3top.mirror = true;
        this.setRotation(this.leg3top, 0.0f, 0.0f, -0.8726646f);
        this.leg4top = new ModelRenderer((ModelBase)this, 0, 10);
        this.leg4top.addBox(-2.0f, 0.0f, -1.0f, 2, 4, 2);
        this.leg4top.setRotationPoint(2.0f, 18.0f, 2.0f);
        this.leg4top.setTextureSize(64, 32);
        this.leg4top.mirror = true;
        this.setRotation(this.leg4top, 0.0f, 0.0f, -0.8726646f);
        this.leg3middle = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg3middle.addBox(-1.5f, 3.0f, -0.5f, 1, 2, 1);
        this.leg3middle.setRotationPoint(2.5f, 17.6f, -1.0f);
        this.leg3middle.setTextureSize(64, 32);
        this.leg3middle.mirror = true;
        this.setRotation(this.leg3middle, 0.0f, 0.0f, -0.6108652f);
        this.leg4middle = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg4middle.addBox(-1.5f, 3.0f, -0.5f, 1, 2, 1);
        this.leg4middle.setRotationPoint(2.5f, 17.6f, 2.0f);
        this.leg4middle.setTextureSize(64, 32);
        this.leg4middle.mirror = true;
        this.setRotation(this.leg4middle, 0.0f, 0.0f, -0.6108652f);
        this.leg3bottom = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg3bottom.addBox(-0.5f, 4.5f, -0.5f, 1, 2, 1);
        this.leg3bottom.setRotationPoint(2.8f, 18.0f, -1.0f);
        this.leg3bottom.setTextureSize(64, 32);
        this.leg3bottom.mirror = true;
        this.setRotation(this.leg3bottom, 0.0f, 0.0f, -0.3490659f);
        this.leg4bottom = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg4bottom.addBox(-0.5f, 4.5f, -0.5f, 1, 2, 1);
        this.leg4bottom.setRotationPoint(2.9f, 18.0f, 2.0f);
        this.leg4bottom.setTextureSize(64, 32);
        this.leg4bottom.mirror = true;
        this.setRotation(this.leg4bottom, 0.0f, 0.0f, -0.3490659f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.body.render(f5);
        this.leg1top.render(f5);
        this.leg1middle.render(f5);
        this.leg1bottom.render(f5);
        this.leg2top.render(f5);
        this.leg2middle.render(f5);
        this.leg2bottom.render(f5);
        this.leg3top.render(f5);
        this.leg4top.render(f5);
        this.leg3middle.render(f5);
        this.leg4middle.render(f5);
        this.leg3bottom.render(f5);
        this.leg4bottom.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leg1bottom.rotateAngleX = this.leg2bottom.rotateAngleX = (float)Math.sin(f / 2.0f) * f1 * 1.3f;
        this.leg2middle.rotateAngleX = this.leg2bottom.rotateAngleX;
        this.leg1middle.rotateAngleX = this.leg2bottom.rotateAngleX;
        this.leg2top.rotateAngleX = this.leg2bottom.rotateAngleX;
        this.leg1top.rotateAngleX = this.leg2bottom.rotateAngleX;
        this.leg3bottom.rotateAngleX = this.leg4bottom.rotateAngleX = (float)Math.cos(f / 2.0f) * f1 * 1.3f;
        this.leg4middle.rotateAngleX = this.leg4bottom.rotateAngleX;
        this.leg3middle.rotateAngleX = this.leg4bottom.rotateAngleX;
        this.leg4top.rotateAngleX = this.leg4bottom.rotateAngleX;
        this.leg3top.rotateAngleX = this.leg4bottom.rotateAngleX;
    }
}

