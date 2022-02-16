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

public class ModelTwilightDemon
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer connector;
    ModelRenderer head2;
    ModelRenderer Spear2;
    ModelRenderer Shape1;
    ModelRenderer head3;
    ModelRenderer connector1;
    ModelRenderer connector2;

    public ModelTwilightDemon() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.connector = new ModelRenderer((ModelBase)this, 0, 20);
        this.connector.addBox(0.0f, 0.0f, 0.0f, 4, 4, 6);
        this.connector.setRotationPoint(6.0f, -6.0f, -11.0f);
        this.connector.setTextureSize(64, 32);
        this.connector.mirror = true;
        this.setRotation(this.connector, 0.0f, 0.0f, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.head2.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head2.setRotationPoint(4.0f, -8.0f, -16.0f);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0f, 0.0f, 0.0f);
        this.Spear2 = new ModelRenderer((ModelBase)this, -2, 0);
        this.Spear2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 13);
        this.Spear2.setRotationPoint(5.5f, 10.0f, -1.0f);
        this.Spear2.setTextureSize(64, 32);
        this.Spear2.mirror = true;
        this.setRotation(this.Spear2, (float)Math.PI, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 13);
        this.Shape1.setRotationPoint(-7.0f, 10.0f, -1.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, (float)Math.PI, 0.0f, 0.0f);
        this.head3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.head3.addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.head3.setRotationPoint(-11.4f, -8.0f, -16.0f);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0f, 0.0f, 0.0f);
        this.connector1 = new ModelRenderer((ModelBase)this, 0, 20);
        this.connector1.addBox(0.0f, 0.0f, 0.0f, 4, 4, 6);
        this.connector1.setRotationPoint(-10.0f, -6.0f, -11.0f);
        this.connector1.setTextureSize(64, 32);
        this.connector1.mirror = true;
        this.setRotation(this.connector1, 0.0f, 0.0f, 0.0f);
        this.connector2 = new ModelRenderer((ModelBase)this, 0, 20);
        this.connector2.addBox(-4.0f, 0.0f, 0.0f, 20, 4, 4);
        this.connector2.setRotationPoint(-6.0f, -6.0f, -5.0f);
        this.connector2.setTextureSize(64, 32);
        this.connector2.mirror = true;
        this.setRotation(this.connector2, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        super.render(var1, var2, var3, var4, var5, var6, var7);
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.head.render(var7);
        this.body.render(var7);
        this.rightarm.render(var7);
        this.leftarm.render(var7);
        this.rightleg.render(var7);
        this.leftleg.render(var7);
        this.connector.render(var7);
        this.head2.render(var7);
        this.Spear2.render(var7);
        this.Shape1.render(var7);
        this.head3.render(var7);
        this.connector1.render(var7);
        this.connector2.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head2.rotateAngleY = var4 / 57.295776f;
        this.head2.rotateAngleX = var5 / 57.295776f;
        this.head3.rotateAngleY = var4 / 57.295776f;
        this.head3.rotateAngleX = var5 / 57.295776f;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.Spear2.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.Shape1.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.Spear2.rotateAngleZ = 0.0f;
        this.Shape1.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
    }
}

