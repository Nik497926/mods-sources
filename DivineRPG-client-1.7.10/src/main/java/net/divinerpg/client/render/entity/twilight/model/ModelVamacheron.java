/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.twilight.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelVamacheron
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer Horn;
    ModelRenderer Shape1;
    ModelRenderer head1;
    ModelRenderer horn3;
    ModelRenderer horn4;
    ModelRenderer Horn1;
    ModelRenderer Shape2;

    public ModelVamacheron() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
        this.head.setRotationPoint(-6.0f, 4.0f, -8.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 18, 4);
        this.body.addBox(-6.0f, -10.0f, -7.0f, 8, 18, 10);
        this.body.setRotationPoint(2.0f, 5.0f, 2.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.5707964f, 0.0f, 0.0f);
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg1.addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg1.setRotationPoint(-2.0f, 12.0f, 7.0f);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg2.addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leg2.setRotationPoint(2.0f, 12.0f, 7.0f);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg3.addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
        this.leg3.setRotationPoint(-2.0f, 12.0f, -5.0f);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 16);
        this.leg4.addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
        this.leg4.setRotationPoint(2.0f, 12.0f, -5.0f);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.horn1 = new ModelRenderer((ModelBase)this, 54, 0);
        this.horn1.addBox(-4.0f, -5.0f, -4.0f, 1, 3, 3);
        this.horn1.setRotationPoint(-6.0f, 2.0f, -8.0f);
        this.horn1.setTextureSize(64, 32);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.0f, 0.0f, 0.0f);
        this.horn2 = new ModelRenderer((ModelBase)this, 55, 0);
        this.horn2.addBox(3.0f, -5.0f, -4.0f, 1, 3, 3);
        this.horn2.setRotationPoint(-6.0f, 2.0f, -8.0f);
        this.horn2.setTextureSize(64, 32);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.0f, 0.0f, 0.0f);
        this.Horn = new ModelRenderer((ModelBase)this, 55, 7);
        this.Horn.addBox(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.Horn.setRotationPoint(-7.0f, 0.0f, -20.0f);
        this.Horn.setTextureSize(64, 32);
        this.Horn.mirror = true;
        this.setRotation(this.Horn, 1.22173f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 9, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape1.setRotationPoint(-7.0f, 3.0f, -15.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.head1.addBox(0.0f, 0.0f, 0.0f, 8, 8, 6);
        this.head1.setRotationPoint(2.0f, 0.0f, -14.0f);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.horn3 = new ModelRenderer((ModelBase)this, 55, 0);
        this.horn3.addBox(0.0f, 0.0f, 0.0f, 1, 3, 3);
        this.horn3.setRotationPoint(2.0f, -3.0f, -12.0f);
        this.horn3.setTextureSize(64, 32);
        this.horn3.mirror = true;
        this.setRotation(this.horn3, 0.0f, 0.0f, 0.0f);
        this.horn4 = new ModelRenderer((ModelBase)this, 55, 0);
        this.horn4.addBox(0.0f, 0.0f, 0.0f, 1, 3, 3);
        this.horn4.setRotationPoint(9.0f, -3.0f, -12.0f);
        this.horn4.setTextureSize(64, 32);
        this.horn4.mirror = true;
        this.setRotation(this.horn4, 0.0f, 0.0f, 0.0f);
        this.Horn1 = new ModelRenderer((ModelBase)this, 55, 7);
        this.Horn1.addBox(0.0f, 0.0f, 0.0f, 2, 7, 2);
        this.Horn1.setRotationPoint(5.0f, 0.0f, -20.0f);
        this.Horn1.setTextureSize(64, 32);
        this.Horn1.mirror = true;
        this.setRotation(this.Horn1, 1.22173f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 9, 0);
        this.Shape2.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.Shape2.setRotationPoint(5.0f, 3.0f, -15.0f);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.head.render(var7);
        this.body.render(var7);
        this.leg1.render(var7);
        this.leg2.render(var7);
        this.leg3.render(var7);
        this.leg4.render(var7);
        this.horn1.render(var7);
        this.horn2.render(var7);
        this.Horn.render(var7);
        this.Shape1.render(var7);
        this.head1.render(var7);
        this.horn3.render(var7);
        this.horn4.render(var7);
        this.Horn1.render(var7);
        this.Shape2.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.body.rotateAngleX = 1.5707964f;
        this.leg1.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leg2.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.leg3.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.leg4.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
    }
}

