/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.entities.lelyetia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelGrobbler
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer head2;
    ModelRenderer body2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer head7;
    ModelRenderer head8;
    ModelRenderer head9;
    ModelRenderer head10;
    ModelRenderer body3;
    ModelRenderer body4;

    public modelGrobbler() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 35);
        this.head.addBox(5.0f, 0.0f, 1.0f, 1, 2, 1);
        this.head.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 2);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 12, 9, 2);
        this.body.setRotationPoint(0.0f, 11.0f, 15.0f);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 1.570796f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 24);
        this.leg1.addBox(-3.0f, 0.0f, -2.0f, 4, 6, 4);
        this.leg1.setRotationPoint(-3.0f, 18.0f, 12.0f);
        this.leg1.setTextureSize(64, 64);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 24);
        this.leg2.addBox(-1.0f, 0.0f, -2.0f, 4, 6, 4);
        this.leg2.setRotationPoint(3.0f, 18.0f, 12.0f);
        this.leg2.setTextureSize(64, 64);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 2, 51);
        this.leg3.addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
        this.leg3.setRotationPoint(-3.0f, 16.0f, 0.0f);
        this.leg3.setTextureSize(64, 64);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 2, 51);
        this.leg4.addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
        this.leg4.setRotationPoint(3.0f, 16.0f, 0.0f);
        this.leg4.setTextureSize(64, 64);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 19, 51);
        this.head2.addBox(-6.0f, -4.0f, -6.0f, 12, 3, 10);
        this.head2.setRotationPoint(0.0f, 12.0f, -8.0f);
        this.head2.setTextureSize(64, 64);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 45, 32);
        this.body2.addBox(-3.0f, -9.0f, -3.0f, 6, 10, 3);
        this.body2.setRotationPoint(0.0f, 5.0f, 12.0f);
        this.body2.setTextureSize(64, 64);
        this.body2.mirror = true;
        this.setRotation(this.body2, 1.570796f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head3.addBox(-6.0f, 2.0f, -6.0f, 12, 3, 10);
        this.head3.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head3.setTextureSize(64, 64);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.head4 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head4.addBox(5.0f, 0.0f, -5.0f, 1, 2, 1);
        this.head4.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head4.setTextureSize(64, 64);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0f, 0.0f, 0.0f);
        this.head5 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head5.addBox(5.0f, 0.0f, -3.0f, 1, 2, 1);
        this.head5.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head5.setTextureSize(64, 64);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0f, 0.0f, 0.0f);
        this.head6 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head6.addBox(5.0f, 0.0f, -1.0f, 1, 2, 1);
        this.head6.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head6.setTextureSize(64, 64);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.0f, 0.0f, 0.0f);
        this.head7 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head7.addBox(-6.0f, 0.0f, 1.0f, 1, 2, 1);
        this.head7.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head7.setTextureSize(64, 64);
        this.head7.mirror = true;
        this.setRotation(this.head7, 0.0f, 0.0f, 0.0f);
        this.head8 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head8.addBox(-6.0f, 0.0f, -1.0f, 1, 2, 1);
        this.head8.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head8.setTextureSize(64, 64);
        this.head8.mirror = true;
        this.setRotation(this.head8, 0.0f, 0.0f, 0.0f);
        this.head9 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head9.addBox(-6.0f, 0.0f, -3.0f, 1, 2, 1);
        this.head9.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head9.setTextureSize(64, 64);
        this.head9.mirror = true;
        this.setRotation(this.head9, 0.0f, 0.0f, 0.0f);
        this.head10 = new ModelRenderer((ModelBase)this, 0, 35);
        this.head10.addBox(-6.0f, 0.0f, -5.0f, 1, 2, 1);
        this.head10.setRotationPoint(0.0f, 11.0f, -8.0f);
        this.head10.setTextureSize(64, 64);
        this.head10.mirror = true;
        this.setRotation(this.head10, 0.0f, 0.0f, 0.0f);
        this.body3 = new ModelRenderer((ModelBase)this, 21, 6);
        this.body3.addBox(-6.0f, -10.0f, -7.0f, 12, 18, 8);
        this.body3.setRotationPoint(0.0f, 9.0f, 6.0f);
        this.body3.setTextureSize(64, 64);
        this.body3.mirror = true;
        this.setRotation(this.body3, 1.570796f, 0.0f, 0.0f);
        this.body4 = new ModelRenderer((ModelBase)this, 45, 32);
        this.body4.addBox(-3.0f, -9.0f, -3.0f, 6, 16, 3);
        this.body4.setRotationPoint(0.0f, 6.0f, -4.0f);
        this.body4.setTextureSize(64, 64);
        this.body4.mirror = true;
        this.setRotation(this.body4, 1.570796f, 0.0f, 0.0f);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.head.render(par7);
        this.body.render(par7);
        this.leg1.render(par7);
        this.leg2.render(par7);
        this.leg3.render(par7);
        this.leg4.render(par7);
        this.head2.render(par7);
        this.body2.render(par7);
        this.head3.render(par7);
        this.head4.render(par7);
        this.head5.render(par7);
        this.head6.render(par7);
        this.head7.render(par7);
        this.head8.render(par7);
        this.head9.render(par7);
        this.head10.render(par7);
        this.body3.render(par7);
        this.body4.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.leg1.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leg1.rotateAngleY = 0.0f;
        this.leg3.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leg3.rotateAngleY = 0.0f;
        this.leg2.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.leg4.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
    }
}

