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

public class ModelDramix
extends ModelBase {
    ModelRenderer Leg_Right;
    ModelRenderer Leg_Left;
    ModelRenderer Arm_Right;
    ModelRenderer Arm_Left;
    ModelRenderer Head;
    ModelRenderer Chest;

    public ModelDramix() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Leg_Right = new ModelRenderer((ModelBase)this, 0, 22);
        this.Leg_Right.addBox(-2.0f, -2.0f, -3.0f, 5, 15, 6);
        this.Leg_Right.setRotationPoint(-4.0f, 11.0f, 1.0f);
        this.Leg_Right.setTextureSize(64, 64);
        this.Leg_Right.mirror = true;
        this.setRotation(this.Leg_Right, 0.0f, 0.0f, 0.0f);
        this.Leg_Left = new ModelRenderer((ModelBase)this, 0, 22);
        this.Leg_Left.addBox(-3.0f, -2.0f, -3.0f, 5, 15, 6);
        this.Leg_Left.setRotationPoint(4.0f, 11.0f, 1.0f);
        this.Leg_Left.setTextureSize(64, 64);
        this.Leg_Left.mirror = true;
        this.setRotation(this.Leg_Left, 0.0f, 0.0f, 0.0f);
        this.Arm_Right = new ModelRenderer((ModelBase)this, 22, 22);
        this.Arm_Right.addBox(-2.0f, -2.0f, -3.0f, 4, 16, 6);
        this.Arm_Right.setRotationPoint(-8.0f, -5.0f, 1.0f);
        this.Arm_Right.setTextureSize(64, 64);
        this.Arm_Right.mirror = true;
        this.setRotation(this.Arm_Right, 0.0f, 0.0f, 0.0f);
        this.Arm_Left = new ModelRenderer((ModelBase)this, 22, 22);
        this.Arm_Left.addBox(-2.0f, -2.0f, -3.0f, 4, 16, 6);
        this.Arm_Left.setRotationPoint(8.0f, -5.0f, 1.0f);
        this.Arm_Left.setTextureSize(64, 64);
        this.Arm_Left.mirror = true;
        this.setRotation(this.Arm_Left, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 36, 0);
        this.Head.addBox(-3.0f, -5.0f, -3.0f, 6, 10, 6);
        this.Head.setRotationPoint(0.0f, -12.0f, 1.0f);
        this.Head.setTextureSize(64, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chest.addBox(-6.0f, -7.0f, -2.0f, 12, 16, 6);
        this.Chest.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Chest.setTextureSize(64, 64);
        this.Chest.mirror = true;
        this.setRotation(this.Chest, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Leg_Right.render(f5);
        this.Leg_Left.render(f5);
        this.Arm_Right.render(f5);
        this.Arm_Left.render(f5);
        this.Head.render(f5);
        this.Chest.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        this.Arm_Right.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 2.0f * var2 * 0.5f;
        this.Arm_Left.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 2.0f * var2 * 0.5f;
        this.Arm_Right.rotateAngleZ = 0.0f;
        this.Leg_Left.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f)) * 1.4f * var2;
        this.Leg_Right.rotateAngleX = MathHelper.cos((float)(var1 * 0.6662f + (float)Math.PI)) * 1.4f * var2;
        this.Leg_Left.rotateAngleY = 0.0f;
        this.Leg_Right.rotateAngleY = 0.0f;
    }
}

