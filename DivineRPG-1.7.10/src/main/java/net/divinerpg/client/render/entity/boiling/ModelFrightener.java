/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.boiling;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFrightener
extends ModelBiped {
    ModelRenderer head1;
    ModelRenderer head;
    ModelRenderer head2;
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelFrightener() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -7.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, -1.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head1.addBox(-8.0f, -1.0f, -4.0f, 8, 8, 8);
        this.head1.setRotationPoint(-4.0f, 1.0f, 0.0f);
        this.head1.setTextureSize(64, 32);
        this.setRotation(this.head1, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head2.addBox(0.0f, -1.0f, -4.0f, 8, 8, 8);
        this.head2.setRotationPoint(4.0f, 1.0f, 0.0f);
        this.head2.setTextureSize(64, 32);
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head1.render(f5);
        this.head.render(f5);
        this.head2.render(f5);
        this.body.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 57.295776f;
        this.head1.rotateAngleY = par4 / 57.295776f;
        this.head1.rotateAngleX = par5 / 57.295776f;
        this.head2.rotateAngleY = par4 / 57.295776f;
        this.head2.rotateAngleX = par5 / 57.295776f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        this.body.rotateAngleX = 0.0f;
        this.rightleg.rotationPointZ = 0.1f;
        this.leftleg.rotationPointZ = 0.1f;
        this.rightleg.rotationPointY = 12.0f;
        this.leftleg.rotationPointY = 12.0f;
        this.head.rotationPointY = -1.0f;
        this.head1.rotationPointY = -1.0f;
        this.head2.rotationPointY = -1.0f;
    }
}

