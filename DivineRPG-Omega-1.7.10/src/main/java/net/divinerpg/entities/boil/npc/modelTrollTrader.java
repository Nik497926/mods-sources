/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.entities.boil.npc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelTrollTrader
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer Right_Leg;
    ModelRenderer Left_Leg;
    ModelRenderer Head;
    ModelRenderer Nose;
    ModelRenderer Left_Arm;
    ModelRenderer Right_Arm;
    ModelRenderer Left_Ear1;
    ModelRenderer Left_Ear2;
    ModelRenderer Staff;

    public modelTrollTrader() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Body = new ModelRenderer((ModelBase)this, 36, 15);
        this.Body.addBox(-4.0f, 0.0f, -3.0f, 8, 11, 6);
        this.Body.setRotationPoint(0.0f, 3.0f, 1.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Right_Leg = new ModelRenderer((ModelBase)this, 18, 18);
        this.Right_Leg.addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
        this.Right_Leg.setRotationPoint(-2.0f, 14.0f, 1.0f);
        this.Right_Leg.setTextureSize(64, 32);
        this.Right_Leg.mirror = true;
        this.setRotation(this.Right_Leg, 0.0f, 0.0f, 0.0f);
        this.Left_Leg = new ModelRenderer((ModelBase)this, 18, 18);
        this.Left_Leg.addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
        this.Left_Leg.setRotationPoint(2.0f, 14.0f, 1.0f);
        this.Left_Leg.setTextureSize(64, 32);
        this.Left_Leg.mirror = true;
        this.setRotation(this.Left_Leg, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.Head.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Head.setTextureSize(64, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Nose = new ModelRenderer((ModelBase)this, 33, 8);
        this.Nose.addBox(-1.0f, -4.0f, -6.0f, 2, 5, 2);
        this.Nose.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Nose.setTextureSize(64, 32);
        this.Nose.mirror = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Left_Arm = new ModelRenderer((ModelBase)this, 0, 18);
        this.Left_Arm.addBox(0.0f, -1.0f, -2.0f, 4, 10, 4);
        this.Left_Arm.setRotationPoint(4.0f, 4.0f, 1.0f);
        this.Left_Arm.setTextureSize(64, 32);
        this.Left_Arm.mirror = true;
        this.setRotation(this.Left_Arm, 0.0f, 0.0f, 0.0f);
        this.Right_Arm = new ModelRenderer((ModelBase)this, 0, 18);
        this.Right_Arm.addBox(-4.0f, -1.0f, -2.0f, 4, 10, 4);
        this.Right_Arm.setRotationPoint(-4.0f, 4.0f, 1.0f);
        this.Right_Arm.setTextureSize(64, 32);
        this.Right_Arm.mirror = true;
        this.setRotation(this.Right_Arm, 0.0f, 0.0f, 0.0f);
        this.Left_Ear1 = new ModelRenderer((ModelBase)this, 45, 2);
        this.Left_Ear1.addBox(-1.0f, -11.0f, -3.0f, 2, 1, 6);
        this.Left_Ear1.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Left_Ear1.setTextureSize(64, 32);
        this.Left_Ear1.mirror = true;
        this.setRotation(this.Left_Ear1, 0.0f, 0.0f, 0.0f);
        this.Left_Ear2 = new ModelRenderer((ModelBase)this, 43, 0);
        this.Left_Ear2.addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
        this.Left_Ear2.setRotationPoint(0.0f, 3.0f, 0.0f);
        this.Left_Ear2.setTextureSize(64, 32);
        this.Left_Ear2.mirror = true;
        this.setRotation(this.Left_Ear2, 0.0f, 0.0f, 0.0f);
        this.Staff = new ModelRenderer((ModelBase)this, 59, 0);
        this.Staff.addBox(-2.5f, -9.0f, -5.0f, 1, 20, 1);
        this.Staff.setRotationPoint(-4.0f, 4.0f, 1.0f);
        this.Staff.setTextureSize(64, 32);
        this.Staff.mirror = true;
        this.setRotation(this.Staff, 0.4363323f, 0.0f, 0.0f);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.Body.render(par7);
        this.Right_Leg.render(par7);
        this.Left_Leg.render(par7);
        this.Head.render(par7);
        this.Nose.render(par7);
        this.Left_Arm.render(par7);
        this.Right_Arm.render(par7);
        this.Left_Ear1.render(par7);
        this.Left_Ear2.render(par7);
        this.Staff.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.Head.rotateAngleY = par4 / 57.295776f;
        this.Head.rotateAngleX = par5 / 54.11268f;
        this.Nose.rotateAngleY = par4 / 57.295776f;
        this.Nose.rotateAngleX = par5 / 54.11268f;
        this.Left_Ear1.rotateAngleY = par4 / 57.295776f;
        this.Left_Ear1.rotateAngleX = par5 / 54.11268f;
        this.Left_Ear2.rotateAngleY = par4 / 57.295776f;
        this.Left_Ear2.rotateAngleX = par5 / 54.11268f;
        this.Right_Arm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.Right_Arm.rotateAngleZ = 0.0f;
        this.Staff.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f + 0.43633f;
        this.Staff.rotateAngleZ = 0.0f;
        this.Left_Arm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.Left_Arm.rotateAngleZ = 0.0f;
        this.Right_Leg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.Right_Leg.rotateAngleY = 0.0f;
        this.Left_Leg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
    }
}

