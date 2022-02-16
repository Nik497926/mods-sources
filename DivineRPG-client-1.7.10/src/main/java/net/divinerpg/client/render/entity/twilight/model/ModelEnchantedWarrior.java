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
package net.divinerpg.client.render.entity.twilight.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnchantedWarrior
extends ModelBiped {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer hornright;
    ModelRenderer hornleft;
    ModelRenderer handright;
    ModelRenderer handleft;
    public int heldItemLeft;
    public int heldItemRight;

    public ModelEnchantedWarrior() {
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
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedRightArm.setTextureSize(64, 32);
        this.bipedRightArm.mirror = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm.setTextureSize(64, 32);
        this.bipedLeftArm.mirror = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
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
        this.hornright = new ModelRenderer((ModelBase)this, 38, 2);
        this.hornright.addBox(-8.0f, 0.0f, 0.0f, 8, 4, 4);
        this.hornright.setRotationPoint(-4.0f, -8.0f, -2.0f);
        this.hornright.setTextureSize(64, 32);
        this.hornright.mirror = true;
        this.setRotation(this.hornright, 0.0f, 0.0f, 0.0f);
        this.hornleft = new ModelRenderer((ModelBase)this, 38, 2);
        this.hornleft.addBox(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.hornleft.setRotationPoint(4.0f, -8.0f, -2.0f);
        this.hornleft.setTextureSize(64, 32);
        this.hornleft.mirror = true;
        this.setRotation(this.hornleft, 0.0f, 0.0f, 0.0f);
        this.handright = new ModelRenderer((ModelBase)this, 1, 20);
        this.handright.addBox(0.0f, 0.0f, 0.0f, 12, 4, 4);
        this.handright.setRotationPoint(-8.0f, 8.0f, 6.0f);
        this.handright.setTextureSize(64, 32);
        this.handright.mirror = true;
        this.setRotation(this.handright, 0.0f, 1.5707964f, 0.0f);
        this.handleft = new ModelRenderer((ModelBase)this, 1, 22);
        this.handleft.addBox(0.0f, 0.0f, 0.0f, 12, 4, 4);
        this.handleft.setRotationPoint(4.0f, 8.0f, 6.0f);
        this.handleft.setTextureSize(64, 32);
        this.handleft.mirror = true;
        this.setRotation(this.handleft, 0.0f, 1.5707964f, 0.0f);
        this.head.addChild(this.hornright);
        this.head.addChild(this.hornleft);
    }

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
        this.setRotationAngles(var2, var3, var4, var5, var6, var7);
        this.head.render(var7);
        this.body.render(var7);
        this.bipedRightArm.render(var7);
        this.bipedLeftArm.render(var7);
        this.rightleg.render(var7);
        this.leftleg.render(var7);
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4) {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.head.rotateAngleY = var4 / 57.295776f;
        this.head.rotateAngleX = var5 / 57.295776f;
        this.bipedRightArm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.bipedRightArm.rotateAngleZ = 0.0f;
        this.bipedLeftArm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        if (this.heldItemLeft != 0) {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5f - 0.31415927f * (float)this.heldItemLeft;
        }
        if (this.heldItemRight != 0) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5f - 0.31415927f * (float)this.heldItemRight;
        }
        this.bipedRightArm.rotateAngleY = 0.0f;
        this.bipedLeftArm.rotateAngleY = 0.0f;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos((float)(var3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedRightArm.rotateAngleX += MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin((float)(var3 * 0.067f)) * 0.05f;
    }
}

