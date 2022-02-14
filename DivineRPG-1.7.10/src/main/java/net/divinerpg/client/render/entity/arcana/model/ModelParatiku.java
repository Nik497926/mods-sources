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

import net.divinerpg.entities.arcana.EntityParatiku;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelParatiku
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer WingRT;
    ModelRenderer WingRB;
    ModelRenderer WingLB;
    ModelRenderer WingLT;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;

    public ModelParatiku() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 7);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, 6.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 19, 0);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 22, 10);
        this.body.setRotationPoint(0.0f, 11.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.8644027f, 0.0f, 0.0f);
        this.WingRT = new ModelRenderer((ModelBase)this, 0, 0);
        this.WingRT.addBox(-14.0f, 0.0f, -5.0f, 14, 1, 10);
        this.WingRT.setRotationPoint(-6.0f, 7.0f, 0.0f);
        this.WingRT.setTextureSize(64, 32);
        this.WingRT.mirror = true;
        this.setRotation(this.WingRT, -0.7330383f, 0.0f, 0.0f);
        this.WingRB = new ModelRenderer((ModelBase)this, 0, 0);
        this.WingRB.addBox(-14.0f, 0.0f, -5.0f, 14, 1, 10);
        this.WingRB.setRotationPoint(-6.0f, 14.0f, 0.0f);
        this.WingRB.setTextureSize(64, 32);
        this.WingRB.mirror = true;
        this.setRotation(this.WingRB, -0.7330383f, 0.0f, 0.0f);
        this.WingLB = new ModelRenderer((ModelBase)this, 0, 0);
        this.WingLB.addBox(0.0f, 0.0f, -5.0f, 14, 1, 10);
        this.WingLB.setRotationPoint(6.0f, 14.0f, 0.0f);
        this.WingLB.setTextureSize(64, 32);
        this.WingLB.mirror = true;
        this.setRotation(this.WingLB, -0.7330383f, 0.0f, 0.0f);
        this.WingLT = new ModelRenderer((ModelBase)this, 0, 0);
        this.WingLT.addBox(0.0f, 0.0f, -5.0f, 14, 1, 10);
        this.WingLT.setRotationPoint(6.0f, 7.0f, 0.0f);
        this.WingLT.setTextureSize(64, 32);
        this.WingLT.mirror = true;
        this.setRotation(this.WingLT, -0.7330383f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 13, 21);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape1.setRotationPoint(-1.0f, 4.0f, 10.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, -0.2602503f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 13, 21);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape2.setRotationPoint(-1.0f, -4.0f, 0.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.2602503f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 13, 21);
        this.Shape3.addBox(0.0f, 0.0f, 0.0f, 2, 10, 1);
        this.Shape3.setRotationPoint(-1.0f, -1.0f, 5.0f);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.2602503f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.WingRT.render(f5);
        this.WingRB.render(f5);
        this.WingLB.render(f5);
        this.WingLT.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par2, float par3, float par4, float par5, float par6, float par7, Entity entity) {
        EntityParatiku var8 = (EntityParatiku)entity;
        this.head.rotateAngleX = par6 / 57.295776f;
        this.head.rotateAngleY = par5 / 57.295776f;
        this.head.rotateAngleZ = 0.0f;
        this.body.rotateAngleX = 0.7853982f + MathHelper.cos((float)(par4 * 0.1f)) * 0.15f;
        this.body.rotateAngleY = 0.0f;
        this.WingRT.rotateAngleY = MathHelper.cos((float)(par4 * 1.3f)) * (float)Math.PI * 0.25f;
        this.WingLT.rotateAngleY = -this.WingRT.rotateAngleY;
        this.WingRB.rotateAngleY = MathHelper.cos((float)(par4 * 1.3f)) * (float)Math.PI * 0.25f;
        this.WingLB.rotateAngleY = -this.WingRB.rotateAngleY;
        this.head.render(par7);
        this.body.render(par7);
    }
}

