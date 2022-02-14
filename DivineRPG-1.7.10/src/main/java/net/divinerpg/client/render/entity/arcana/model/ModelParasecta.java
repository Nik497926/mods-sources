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

public class ModelParasecta
extends ModelBase {
    ModelRenderer foot6;
    ModelRenderer wing2;
    ModelRenderer wing1;
    ModelRenderer body;
    ModelRenderer leg6;
    ModelRenderer foot4;
    ModelRenderer leg4;
    ModelRenderer foot5;
    ModelRenderer leg5;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer foot3;
    ModelRenderer tusk1;
    ModelRenderer head;
    ModelRenderer tusk2;

    public ModelParasecta() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.foot6 = new ModelRenderer((ModelBase)this, 15, 17);
        this.foot6.addBox(-5.0f, -1.0f, -7.0f, 2, 9, 1);
        this.foot6.setRotationPoint(-3.0f, 13.0f, 11.0f);
        this.foot6.setTextureSize(128, 32);
        this.foot6.mirror = true;
        this.setRotation(this.foot6, 0.0f, 0.0f, -0.4363323f);
        this.wing2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.wing2.addBox(-14.0f, 0.0f, -5.0f, 14, 1, 10);
        this.wing2.setRotationPoint(-10.0f, 0.0f, 0.0f);
        this.wing2.setTextureSize(128, 32);
        this.wing2.mirror = true;
        this.setRotation(this.wing2, -0.7330383f, 0.0f, 0.0f);
        this.wing1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.wing1.addBox(0.0f, 0.0f, -5.0f, 14, 1, 10);
        this.wing1.setRotationPoint(10.0f, 0.0f, 0.0f);
        this.wing1.setTextureSize(128, 32);
        this.wing1.mirror = true;
        this.setRotation(this.wing1, -0.7330383f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 78, 0);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 20, 22, 5);
        this.body.setRotationPoint(-4.0f, -3.0f, 2.0f);
        this.body.setTextureSize(128, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.8644027f, 0.0f, 0.0f);
        this.leg6 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg6.addBox(-6.0f, -10.0f, -7.0f, 4, 12, 3);
        this.leg6.setRotationPoint(-3.0f, 15.0f, 10.0f);
        this.leg6.setTextureSize(128, 32);
        this.leg6.mirror = true;
        this.setRotation(this.leg6, 0.0f, 0.0f, 0.0f);
        this.foot4 = new ModelRenderer((ModelBase)this, 15, 17);
        this.foot4.addBox(-5.0f, -1.0f, -7.0f, 2, 9, 1);
        this.foot4.setRotationPoint(-3.0f, 6.0f, 1.0f);
        this.foot4.setTextureSize(128, 32);
        this.foot4.mirror = true;
        this.setRotation(this.foot4, 0.0f, 0.0f, -0.4363323f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg4.addBox(-6.0f, -10.0f, -7.0f, 4, 12, 3);
        this.leg4.setRotationPoint(-3.0f, 8.0f, 0.0f);
        this.leg4.setTextureSize(128, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.foot5 = new ModelRenderer((ModelBase)this, 15, 17);
        this.foot5.addBox(-5.0f, -1.0f, -7.0f, 2, 9, 1);
        this.foot5.setRotationPoint(-3.0f, 10.0f, 6.0f);
        this.foot5.setTextureSize(128, 32);
        this.foot5.mirror = true;
        this.setRotation(this.foot5, 0.0f, 0.0f, -0.4363323f);
        this.leg5 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg5.addBox(-6.0f, -10.0f, -7.0f, 4, 12, 3);
        this.leg5.setRotationPoint(-3.0f, 12.0f, 5.0f);
        this.leg5.setTextureSize(128, 32);
        this.leg5.mirror = true;
        this.setRotation(this.leg5, 0.0f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg1.addBox(-6.0f, -10.0f, -7.0f, 4, 12, 3);
        this.leg1.setRotationPoint(11.0f, 8.0f, 0.0f);
        this.leg1.setTextureSize(128, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg2.addBox(-6.0f, -10.0f, -7.0f, 4, 12, 3);
        this.leg2.setRotationPoint(11.0f, 12.0f, 5.0f);
        this.leg2.setTextureSize(128, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 14);
        this.leg3.addBox(-6.0f, -10.0f, -7.0f, 4, 12, 3);
        this.leg3.setRotationPoint(11.0f, 15.0f, 10.0f);
        this.leg3.setTextureSize(128, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.foot1 = new ModelRenderer((ModelBase)this, 15, 17);
        this.foot1.addBox(-5.0f, -1.0f, -7.0f, 2, 9, 1);
        this.foot1.setRotationPoint(11.0f, 9.0f, 1.0f);
        this.foot1.setTextureSize(128, 32);
        this.foot1.mirror = true;
        this.setRotation(this.foot1, 0.0f, 0.0f, 0.4363323f);
        this.foot2 = new ModelRenderer((ModelBase)this, 15, 17);
        this.foot2.addBox(-5.0f, -1.0f, -7.0f, 2, 9, 1);
        this.foot2.setRotationPoint(11.0f, 13.0f, 6.0f);
        this.foot2.setTextureSize(128, 32);
        this.foot2.mirror = true;
        this.setRotation(this.foot2, 0.0f, 0.0f, 0.4363323f);
        this.foot3 = new ModelRenderer((ModelBase)this, 15, 17);
        this.foot3.addBox(-5.0f, -1.0f, -7.0f, 2, 9, 1);
        this.foot3.setRotationPoint(11.0f, 16.0f, 11.0f);
        this.foot3.setTextureSize(128, 32);
        this.foot3.mirror = true;
        this.setRotation(this.foot3, 0.0f, 0.0f, 0.4363323f);
        this.tusk1 = new ModelRenderer((ModelBase)this, 47, 16);
        this.tusk1.addBox(0.0f, 0.5333334f, 0.0f, 1, 3, 1);
        this.tusk1.setRotationPoint(2.0f, 0.0f, -13.0f);
        this.tusk1.setTextureSize(128, 32);
        this.tusk1.mirror = true;
        this.setRotation(this.tusk1, 0.0f, 0.0f, 0.1396263f);
        this.head = new ModelRenderer((ModelBase)this, 48, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(0.0f, -3.0f, -8.0f);
        this.head.setTextureSize(128, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.tusk2 = new ModelRenderer((ModelBase)this, 47, 16);
        this.tusk2.addBox(-1.0f, 0.5333334f, 0.0f, 1, 3, 1);
        this.tusk2.setRotationPoint(-2.0f, 0.0f, -13.0f);
        this.tusk2.setTextureSize(128, 32);
        this.tusk2.mirror = true;
        this.setRotation(this.tusk2, 0.0f, 0.0f, -0.1396263f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.foot6.render(f5);
        this.wing2.render(f5);
        this.wing1.render(f5);
        this.body.render(f5);
        this.leg6.render(f5);
        this.foot4.render(f5);
        this.leg4.render(f5);
        this.foot5.render(f5);
        this.leg5.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.foot1.render(f5);
        this.foot2.render(f5);
        this.foot3.render(f5);
        this.tusk1.render(f5);
        this.head.render(f5);
        this.tusk2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.wing1.rotateAngleY = MathHelper.cos((float)(f3 * 1.3f)) * (float)Math.PI * 0.25f;
        this.wing2.rotateAngleY = -this.wing1.rotateAngleY;
    }
}

