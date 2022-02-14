/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package net.divinerpg.client.render.entity.euca.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGolder
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer scale;
    ModelRenderer scale0;
    ModelRenderer scale1;
    ModelRenderer scale2;
    ModelRenderer jaw;
    ModelRenderer foot;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer foot3;

    public ModelGolder() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 11.0f, -4.0f);
        this.head.setTextureSize(64, 64);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 28, 18);
        this.body.addBox(-5.0f, -10.0f, -7.0f, 10, 17, 8);
        this.body.setRotationPoint(0.0f, 12.46667f, 4.0f);
        this.body.setTextureSize(64, 64);
        this.setRotation(this.body, 1.23597f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 34);
        this.leg1.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
        this.leg1.setRotationPoint(-3.0f, 18.0f, 7.0f);
        this.leg1.setTextureSize(64, 64);
        this.setRotation(this.leg1, -0.0349066f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 34);
        this.leg2.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
        this.leg2.setRotationPoint(3.0f, 18.0f, 7.0f);
        this.leg2.setTextureSize(64, 64);
        this.setRotation(this.leg2, -0.0349066f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 20);
        this.leg3.addBox(-2.0f, 18.0f, -2.0f, 4, 8, 4);
        this.leg3.setRotationPoint(-3.0f, -2.0f, -5.0f);
        this.leg3.setTextureSize(64, 64);
        this.setRotation(this.leg3, -0.0349066f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 20);
        this.leg4.addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
        this.leg4.setRotationPoint(3.0f, 16.0f, -5.0f);
        this.leg4.setTextureSize(64, 64);
        this.setRotation(this.leg4, -0.0349066f, 0.0f, 0.0f);
        this.scale = new ModelRenderer((ModelBase)this, 0, 54);
        this.scale.addBox(0.0f, 0.0f, 0.0f, 2, 3, 6);
        this.scale.setRotationPoint(-1.0f, 10.5f, 4.0f);
        this.scale.setTextureSize(64, 64);
        this.setRotation(this.scale, -0.3328579f, 0.0f, 0.0f);
        this.scale0 = new ModelRenderer((ModelBase)this, 0, 54);
        this.scale0.addBox(0.0f, 0.0f, 0.0f, 2, 3, 6);
        this.scale0.setRotationPoint(-1.0f, 8.0f, -1.0f);
        this.scale0.setTextureSize(64, 64);
        this.setRotation(this.scale0, -0.2956793f, 0.0f, 0.0f);
        this.scale1 = new ModelRenderer((ModelBase)this, 0, 54);
        this.scale1.addBox(0.0f, 0.0f, 0.0f, 2, 3, 6);
        this.scale1.setRotationPoint(-1.0f, 6.0f, -4.0f);
        this.scale1.setTextureSize(64, 64);
        this.setRotation(this.scale1, -0.2956793f, 0.0f, 0.0f);
        this.scale2 = new ModelRenderer((ModelBase)this, 18, 45);
        this.scale2.addBox(0.0f, 0.0f, 0.0f, 2, 6, 6);
        this.scale2.setRotationPoint(-1.0f, 4.0f, -10.0f);
        this.scale2.setTextureSize(64, 64);
        this.setRotation(this.scale2, -0.8533585f, 0.0f, 0.0f);
        this.jaw = new ModelRenderer((ModelBase)this, 32, 6);
        this.jaw.addBox(-4.0f, -4.0f, -8.0f, 8, 2, 8);
        this.jaw.setRotationPoint(0.0f, 18.0f, -3.0f);
        this.jaw.setTextureSize(64, 64);
        this.setRotation(this.jaw, 0.2974289f, 0.0f, 0.0f);
        this.foot = new ModelRenderer((ModelBase)this, 0, 48);
        this.foot.addBox(-2.0f, 0.0f, 0.0f, 4, 2, 3);
        this.foot.setRotationPoint(-3.0f, 22.0f, 2.0f);
        this.foot.setTextureSize(64, 64);
        this.setRotation(this.foot, 0.0f, 0.1047198f, 0.0f);
        this.foot1 = new ModelRenderer((ModelBase)this, 0, 48);
        this.foot1.addBox(-2.0f, 0.0f, 0.0f, 4, 2, 3);
        this.foot1.setRotationPoint(3.0f, 22.0f, 2.0f);
        this.foot1.setTextureSize(64, 64);
        this.setRotation(this.foot1, 0.0f, -0.1047198f, 0.0f);
        this.foot2 = new ModelRenderer((ModelBase)this, 0, 48);
        this.foot2.addBox(-2.0f, 0.0f, 0.0f, 4, 2, 3);
        this.foot2.setRotationPoint(3.0f, 22.0f, -10.0f);
        this.foot2.setTextureSize(64, 64);
        this.setRotation(this.foot2, 0.0f, -0.1047198f, 0.0f);
        this.foot3 = new ModelRenderer((ModelBase)this, 0, 48);
        this.foot3.addBox(-2.0f, 0.0f, 0.0f, 4, 2, 3);
        this.foot3.setRotationPoint(-3.0f, 22.0f, -10.0f);
        this.foot3.setTextureSize(64, 64);
        this.setRotation(this.foot3, 0.0f, 0.1047198f, 0.0f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.scale.render(f5);
        this.scale0.render(f5);
        this.scale1.render(f5);
        this.scale2.render(f5);
        this.jaw.render(f5);
        this.foot.render(f5);
        this.foot1.render(f5);
        this.foot2.render(f5);
        this.foot3.render(f5);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    }
}

