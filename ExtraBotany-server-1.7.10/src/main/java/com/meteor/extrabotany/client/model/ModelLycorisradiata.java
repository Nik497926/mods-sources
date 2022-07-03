/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLycorisradiata
extends ModelBase {
    ModelRenderer head1;
    ModelRenderer head2;

    public ModelLycorisradiata() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head1.addBox(-5.0f, -12.0f, 0.0f, 11, 13, 0);
        this.head1.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0f, -0.7853982f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head2.addBox(-5.0f, -12.0f, 0.0f, 11, 13, 0);
        this.head2.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.7853982f, 0.0f);
    }

    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7);
        this.head1.render(par7);
        this.head2.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
    }
}

