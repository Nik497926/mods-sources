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

public class AcidHag
extends ModelBase {
    ModelRenderer body;
    ModelRenderer leftarmtop;
    ModelRenderer rightarmtop;
    ModelRenderer leftarmbottom;
    ModelRenderer rightarmbottom;
    ModelRenderer leftarms1;
    ModelRenderer rightarms1;
    ModelRenderer leftarms2;
    ModelRenderer rightarms2;
    ModelRenderer leftarms3;
    ModelRenderer rightarms3;
    ModelRenderer leftlegs1;
    ModelRenderer rightlegs1;
    ModelRenderer rightfoot;
    ModelRenderer leftfoot;
    ModelRenderer rightlegs2;
    ModelRenderer leftlegs2;
    ModelRenderer leftlegs3;
    ModelRenderer rightlegs3;
    ModelRenderer horn1;
    ModelRenderer head;
    ModelRenderer horn2;

    public AcidHag() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.leftarmtop = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarmtop.addBox(-1.0f, -2.0f, -2.0f, 4, 8, 4);
        this.leftarmtop.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarmtop.setTextureSize(64, 32);
        this.leftarmtop.mirror = true;
        this.setRotation(this.leftarmtop, 0.0f, 0.0f, 0.0f);
        this.rightarmtop = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarmtop.addBox(-3.0f, -2.0f, -2.0f, 4, 8, 4);
        this.rightarmtop.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarmtop.setTextureSize(64, 32);
        this.rightarmtop.mirror = true;
        this.setRotation(this.rightarmtop, 0.0f, 0.0f, 0.0f);
        this.leftarmbottom = new ModelRenderer((ModelBase)this, 40, 25);
        this.leftarmbottom.addBox(-1.0f, 18.0f, -5.0f, 4, 3, 4);
        this.leftarmbottom.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarmbottom.setTextureSize(64, 32);
        this.leftarmbottom.mirror = true;
        this.setRotation(this.leftarmbottom, 0.3490659f, 0.0f, 0.0f);
        this.rightarmbottom = new ModelRenderer((ModelBase)this, 40, 25);
        this.rightarmbottom.addBox(-3.0f, 18.0f, -5.0f, 4, 3, 4);
        this.rightarmbottom.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarmbottom.setTextureSize(64, 32);
        this.rightarmbottom.mirror = true;
        this.setRotation(this.rightarmbottom, 0.3490659f, 0.0f, 0.0f);
        this.leftarms1 = new ModelRenderer((ModelBase)this, 40, 20);
        this.leftarms1.addBox(-1.0f, 6.0f, -2.0f, 4, 4, 4);
        this.leftarms1.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarms1.setTextureSize(64, 32);
        this.leftarms1.mirror = true;
        this.setRotation(this.leftarms1, 0.0872665f, 0.0f, 0.0f);
        this.rightarms1 = new ModelRenderer((ModelBase)this, 40, 20);
        this.rightarms1.addBox(-3.0f, 6.0f, -2.0f, 4, 4, 4);
        this.rightarms1.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarms1.setTextureSize(64, 32);
        this.rightarms1.mirror = true;
        this.setRotation(this.rightarms1, 0.0872665f, 0.0f, 0.0f);
        this.leftarms2 = new ModelRenderer((ModelBase)this, 40, 20);
        this.leftarms2.addBox(-1.0f, 10.0f, -3.0f, 4, 4, 4);
        this.leftarms2.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarms2.setTextureSize(64, 32);
        this.leftarms2.mirror = true;
        this.setRotation(this.leftarms2, 0.1745329f, 0.0f, 0.0f);
        this.rightarms2 = new ModelRenderer((ModelBase)this, 40, 20);
        this.rightarms2.addBox(-3.0f, 10.0f, -3.0f, 4, 4, 4);
        this.rightarms2.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarms2.setTextureSize(64, 32);
        this.rightarms2.mirror = true;
        this.setRotation(this.rightarms2, 0.1745329f, 0.0f, 0.0f);
        this.leftarms3 = new ModelRenderer((ModelBase)this, 40, 20);
        this.leftarms3.addBox(-1.0f, 14.0f, -4.0f, 4, 4, 4);
        this.leftarms3.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarms3.setTextureSize(64, 32);
        this.leftarms3.mirror = true;
        this.setRotation(this.leftarms3, 0.2617994f, 0.0f, 0.0f);
        this.rightarms3 = new ModelRenderer((ModelBase)this, 40, 20);
        this.rightarms3.addBox(-3.0f, 14.0f, -4.0f, 4, 4, 4);
        this.rightarms3.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarms3.setTextureSize(64, 32);
        this.rightarms3.mirror = true;
        this.setRotation(this.rightarms3, 0.2617994f, 0.0f, 0.0f);
        this.leftlegs1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegs1.addBox(0.0f, 3.0f, -2.0f, 2, 3, 4);
        this.leftlegs1.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftlegs1.setTextureSize(64, 32);
        this.leftlegs1.mirror = true;
        this.setRotation(this.leftlegs1, 0.0f, 0.0f, 0.0f);
        this.rightlegs1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegs1.addBox(-2.0f, 3.0f, -2.0f, 2, 3, 4);
        this.rightlegs1.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightlegs1.setTextureSize(64, 32);
        this.rightlegs1.mirror = true;
        this.setRotation(this.rightlegs1, 0.0f, 0.0f, 0.0f);
        this.rightfoot = new ModelRenderer((ModelBase)this, 32, 0);
        this.rightfoot.addBox(-2.0f, 6.0f, -6.0f, 3, 3, 7);
        this.rightfoot.setRotationPoint(-2.0f, 15.0f, 0.0f);
        this.rightfoot.setTextureSize(64, 32);
        this.rightfoot.mirror = true;
        this.setRotation(this.rightfoot, 0.0f, 0.0f, 0.0f);
        this.leftfoot = new ModelRenderer((ModelBase)this, 32, 0);
        this.leftfoot.addBox(-1.0f, 6.0f, -6.0f, 3, 3, 7);
        this.leftfoot.setRotationPoint(2.0f, 15.0f, 0.0f);
        this.leftfoot.setTextureSize(64, 32);
        this.leftfoot.mirror = true;
        this.setRotation(this.leftfoot, 0.0f, 0.0f, 0.0f);
        this.rightlegs2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegs2.addBox(-2.0f, 0.0f, -2.0f, 3, 3, 4);
        this.rightlegs2.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightlegs2.setTextureSize(64, 32);
        this.rightlegs2.mirror = true;
        this.setRotation(this.rightlegs2, 0.0f, 0.0f, 0.0f);
        this.leftlegs2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegs2.addBox(-1.0f, 0.0f, -2.0f, 3, 3, 4);
        this.leftlegs2.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftlegs2.setTextureSize(64, 32);
        this.leftlegs2.mirror = true;
        this.setRotation(this.leftlegs2, 0.0f, 0.0f, 0.0f);
        this.leftlegs3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegs3.addBox(-1.0f, 6.0f, -2.0f, 3, 3, 4);
        this.leftlegs3.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftlegs3.setTextureSize(64, 32);
        this.leftlegs3.mirror = true;
        this.setRotation(this.leftlegs3, 0.0f, 0.0f, 0.0f);
        this.rightlegs3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegs3.addBox(-2.0f, 6.0f, -2.0f, 3, 3, 4);
        this.rightlegs3.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightlegs3.setTextureSize(64, 32);
        this.rightlegs3.mirror = true;
        this.setRotation(this.rightlegs3, 0.0f, 0.0f, 0.0f);
        this.horn1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.horn1.addBox(1.0f, -7.0f, -12.0f, 2, 2, 8);
        this.horn1.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.horn1.setTextureSize(64, 32);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, -0.7853982f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.horn2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.horn2.addBox(-3.0f, -7.0f, -12.0f, 2, 2, 8);
        this.horn2.setRotationPoint(0.0f, 5.0f, 0.0f);
        this.horn2.setTextureSize(64, 32);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, -0.7853982f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.body.render(f5);
        this.leftarmtop.render(f5);
        this.rightarmtop.render(f5);
        this.leftarmbottom.render(f5);
        this.rightarmbottom.render(f5);
        this.leftarms1.render(f5);
        this.rightarms1.render(f5);
        this.leftarms2.render(f5);
        this.rightarms2.render(f5);
        this.leftarms3.render(f5);
        this.rightarms3.render(f5);
        this.leftlegs1.render(f5);
        this.rightlegs1.render(f5);
        this.rightfoot.render(f5);
        this.leftfoot.render(f5);
        this.rightlegs2.render(f5);
        this.leftlegs2.render(f5);
        this.leftlegs3.render(f5);
        this.rightlegs3.render(f5);
        this.horn1.render(f5);
        this.head.render(f5);
        this.horn2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.leftlegs1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftlegs2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftlegs3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftfoot.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.rightlegs1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightlegs2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightlegs3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightfoot.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightarms1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f + 0.0872665f;
        this.rightarms2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f + 0.1745329f;
        this.rightarms3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f + 0.2617994f;
        this.rightarmtop.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.rightarmbottom.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f + 0.3490659f;
        this.leftarms1.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f + 0.0872665f;
        this.leftarms2.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f + 0.1745329f;
        this.leftarms3.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f + 0.2617994f;
        this.leftarmtop.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.leftarmbottom.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f + 0.3490659f;
    }
}

