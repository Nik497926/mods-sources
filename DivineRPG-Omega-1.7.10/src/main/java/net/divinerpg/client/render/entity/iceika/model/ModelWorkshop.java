/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.iceika.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWorkshop
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarmtop;
    ModelRenderer rightlegbottom;
    ModelRenderer rightleg;
    ModelRenderer leftlegbottom;
    ModelRenderer leftleg;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer leftarmtop;

    public ModelWorkshop() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 30, 14);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 14, 4);
        this.body.setRotationPoint(0.0f, -7.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarmtop = new ModelRenderer((ModelBase)this, 37, 16);
        this.rightarmtop.addBox(-6.0f, -3.0f, -2.0f, 8, 5, 6);
        this.rightarmtop.setRotationPoint(-6.0f, -5.0f, -1.0f);
        this.rightarmtop.setTextureSize(64, 32);
        this.rightarmtop.mirror = true;
        this.setRotation(this.rightarmtop, 0.0f, 0.0f, 0.0f);
        this.rightlegbottom = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightlegbottom.addBox(-3.0f, 8.0f, -3.0f, 6, 9, 6);
        this.rightlegbottom.setRotationPoint(-4.0f, 7.0f, 0.0f);
        this.rightlegbottom.setTextureSize(64, 32);
        this.rightlegbottom.mirror = true;
        this.setRotation(this.rightlegbottom, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-4.0f, 7.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftlegbottom = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftlegbottom.addBox(-3.0f, 8.0f, -3.0f, 6, 9, 6);
        this.leftlegbottom.setRotationPoint(4.0f, 7.0f, 0.0f);
        this.leftlegbottom.setTextureSize(64, 32);
        this.leftlegbottom.mirror = true;
        this.setRotation(this.leftlegbottom, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(4.0f, 7.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-5.0f, 2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-6.0f, -5.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(0.0f, 2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(7.0f, -5.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.leftarmtop = new ModelRenderer((ModelBase)this, 36, 16);
        this.leftarmtop.addBox(-3.0f, -3.0f, -2.0f, 8, 5, 6);
        this.leftarmtop.setRotationPoint(7.0f, -5.0f, -1.0f);
        this.leftarmtop.setTextureSize(64, 32);
        this.leftarmtop.mirror = true;
        this.setRotation(this.leftarmtop, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarmtop.render(f5);
        this.rightlegbottom.render(f5);
        this.rightleg.render(f5);
        this.leftlegbottom.render(f5);
        this.leftleg.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.leftarmtop.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.head.rotateAngleY = f3 / 57.295776f;
        this.head.rotateAngleX = f4 / 57.295776f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        this.rightarmtop.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.leftarmtop.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.rightarmtop.rotateAngleZ = 0.0f;
        this.leftarmtop.rotateAngleZ = 0.0f;
        this.rightlegbottom.rotateAngleX = MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.leftlegbottom.rotateAngleX = MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.rightlegbottom.rotateAngleY = 0.0f;
        this.leftlegbottom.rotateAngleY = 0.0f;
    }
}

