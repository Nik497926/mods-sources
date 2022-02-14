/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.divinerpg.entities.base.IAttackTimer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelStoneGolem
extends ModelBase {
    ModelRenderer LShoulder;
    ModelRenderer crystal;
    ModelRenderer Ring3;
    ModelRenderer Ring1;
    ModelRenderer Ring2;
    ModelRenderer headF4;
    ModelRenderer Body;
    ModelRenderer RShoulder;
    ModelRenderer LBodyL;
    ModelRenderer crown2;
    ModelRenderer Jaw;
    ModelRenderer headF1;
    ModelRenderer headF3;
    ModelRenderer headF2;
    ModelRenderer headB;
    ModelRenderer crown1;
    ModelRenderer forehead;
    ModelRenderer LFinger2;
    ModelRenderer RArm1;
    ModelRenderer RArm2;
    ModelRenderer LArm1;
    ModelRenderer LArm3;
    ModelRenderer LArm2;
    ModelRenderer LBodyR;
    ModelRenderer RArm3;
    ModelRenderer LFinger1;
    ModelRenderer LFinger4;
    ModelRenderer LFinger3;
    ModelRenderer RFinger1;
    ModelRenderer RFinger2;
    ModelRenderer RFinger4;
    ModelRenderer RFinger3;

    public ModelStoneGolem() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.LShoulder = new ModelRenderer((ModelBase)this, 0, 94);
        this.LShoulder.addBox(-6.0f, -6.0f, -7.0f, 12, 12, 14);
        this.LShoulder.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LShoulder.setTextureSize(128, 128);
        this.LShoulder.mirror = true;
        this.setRotation(this.LShoulder, 0.0f, 0.0f, 0.0f);
        this.crystal = new ModelRenderer((ModelBase)this, 104, 94);
        this.crystal.addBox(0.0f, 0.0f, 0.0f, 5, 5, 5);
        this.crystal.setRotationPoint(-3.0f, 0.0f, -3.0f);
        this.crystal.setTextureSize(128, 128);
        this.crystal.mirror = true;
        this.setRotation(this.crystal, 0.7853982f, 0.4363323f, 0.6108652f);
        this.Ring3 = new ModelRenderer((ModelBase)this, 52, 117);
        this.Ring3.addBox(0.0f, 0.0f, 0.0f, 7, 0, 7);
        this.Ring3.setRotationPoint(-2.5f, 16.0f, -3.0f);
        this.Ring3.setTextureSize(128, 128);
        this.Ring3.mirror = true;
        this.setRotation(this.Ring3, 0.0f, 0.0f, 0.0f);
        this.Ring1 = new ModelRenderer((ModelBase)this, 52, 94);
        this.Ring1.addBox(0.0f, 0.0f, 0.0f, 13, 0, 13);
        this.Ring1.setRotationPoint(-5.5f, 8.0f, -7.0f);
        this.Ring1.setTextureSize(128, 128);
        this.Ring1.mirror = true;
        this.setRotation(this.Ring1, 0.0f, 0.0f, 0.0f);
        this.Ring2 = new ModelRenderer((ModelBase)this, 52, 107);
        this.Ring2.addBox(0.0f, 0.0f, 0.0f, 10, 0, 10);
        this.Ring2.setRotationPoint(-4.0f, 12.0f, -5.0f);
        this.Ring2.setTextureSize(128, 128);
        this.Ring2.mirror = true;
        this.setRotation(this.Ring2, 0.0f, 0.0f, 0.0f);
        this.headF4 = new ModelRenderer((ModelBase)this, 82, 7);
        this.headF4.addBox(-4.0f, -8.0f, -4.0f, 1, 2, 1);
        this.headF4.setRotationPoint(8.0f, -20.0f, -1.0f);
        this.headF4.setTextureSize(128, 128);
        this.headF4.mirror = true;
        this.setRotation(this.headF4, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 68);
        this.Body.addBox(-4.0f, 0.0f, -2.0f, 28, 13, 13);
        this.Body.setRotationPoint(-10.0f, -20.0f, -5.0f);
        this.Body.setTextureSize(128, 128);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.RShoulder = new ModelRenderer((ModelBase)this, 0, 94);
        this.RShoulder.addBox(-6.0f, -6.0f, -7.0f, 12, 12, 14);
        this.RShoulder.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RShoulder.setTextureSize(128, 128);
        this.RShoulder.mirror = true;
        this.setRotation(this.RShoulder, 0.0f, 0.0f, 0.0f);
        this.LBodyL = new ModelRenderer((ModelBase)this, 0, 18);
        this.LBodyL.addBox(-2.0f, 0.0f, -2.0f, 10, 10, 11);
        this.LBodyL.setRotationPoint(8.0f, -13.5f, -4.0f);
        this.LBodyL.setTextureSize(128, 128);
        this.LBodyL.mirror = true;
        this.setRotation(this.LBodyL, 0.0f, 0.0f, 0.7853982f);
        this.crown2 = new ModelRenderer((ModelBase)this, 90, 16);
        this.crown2.addBox(-4.0f, -8.0f, -4.0f, 10, 3, 4);
        this.crown2.setRotationPoint(-1.0f, -25.0f, 5.0f);
        this.crown2.setTextureSize(128, 128);
        this.crown2.mirror = true;
        this.setRotation(this.crown2, 0.0f, 0.0f, 0.0f);
        this.Jaw = new ModelRenderer((ModelBase)this, 38, 0);
        this.Jaw.addBox(-4.0f, -8.0f, -4.0f, 11, 4, 11);
        this.Jaw.setRotationPoint(-1.5f, -16.0f, -2.0f);
        this.Jaw.setTextureSize(128, 128);
        this.Jaw.mirror = true;
        this.setRotation(this.Jaw, 0.0f, 0.0f, 0.0f);
        this.headF1 = new ModelRenderer((ModelBase)this, 82, 0);
        this.headF1.addBox(-4.0f, -8.0f, -4.0f, 10, 6, 1);
        this.headF1.setRotationPoint(-1.0f, -18.0f, -1.0f);
        this.headF1.setTextureSize(128, 128);
        this.headF1.mirror = true;
        this.setRotation(this.headF1, 0.0f, 0.0f, 0.0f);
        this.headF3 = new ModelRenderer((ModelBase)this, 86, 7);
        this.headF3.addBox(-4.0f, -8.0f, -4.0f, 4, 2, 1);
        this.headF3.setRotationPoint(2.0f, -20.0f, -1.0f);
        this.headF3.setTextureSize(128, 128);
        this.headF3.mirror = true;
        this.setRotation(this.headF3, 0.0f, 0.0f, 0.0f);
        this.headF2 = new ModelRenderer((ModelBase)this, 82, 7);
        this.headF2.addBox(-4.0f, -8.0f, -4.0f, 1, 2, 1);
        this.headF2.setRotationPoint(-1.0f, -20.0f, -1.0f);
        this.headF2.setTextureSize(128, 128);
        this.headF2.mirror = true;
        this.setRotation(this.headF2, 0.0f, 0.0f, 0.0f);
        this.headB = new ModelRenderer((ModelBase)this, 0, 0);
        this.headB.addBox(-4.0f, -8.0f, -4.0f, 10, 4, 9);
        this.headB.setRotationPoint(-1.0f, -20.0f, 0.0f);
        this.headB.setTextureSize(128, 128);
        this.headB.mirror = true;
        this.setRotation(this.headB, 0.0f, 0.0f, 0.0f);
        this.crown1 = new ModelRenderer((ModelBase)this, 56, 16);
        this.crown1.addBox(-4.0f, -8.0f, -4.0f, 8, 4, 7);
        this.crown1.setRotationPoint(0.0f, -24.5f, 2.1f);
        this.crown1.setTextureSize(128, 128);
        this.crown1.mirror = true;
        this.setRotation(this.crown1, 0.4537856f, 0.0f, 0.0f);
        this.forehead = new ModelRenderer((ModelBase)this, 0, 40);
        this.forehead.addBox(-4.0f, -8.0f, -4.0f, 10, 2, 10);
        this.forehead.setRotationPoint(-1.0f, -22.0f, -1.0f);
        this.forehead.setTextureSize(128, 128);
        this.forehead.mirror = true;
        this.setRotation(this.forehead, 0.0f, 0.0f, 0.0f);
        this.LFinger2 = new ModelRenderer((ModelBase)this, 82, 28);
        this.LFinger2.addBox(0.5f, 33.0f, 3.5f, 2, 3, 1);
        this.LFinger2.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LFinger2.setTextureSize(128, 128);
        this.LFinger2.mirror = true;
        this.setRotation(this.LFinger2, 0.0f, 0.0f, 0.0f);
        this.RArm1 = new ModelRenderer((ModelBase)this, 40, 42);
        this.RArm1.addBox(-5.0f, 5.0f, -4.0f, 8, 13, 8);
        this.RArm1.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RArm1.setTextureSize(128, 128);
        this.RArm1.mirror = true;
        this.setRotation(this.RArm1, 0.0f, 0.0f, 0.0f);
        this.RArm2 = new ModelRenderer((ModelBase)this, 82, 69);
        this.RArm2.addBox(-6.0f, 18.0f, -5.0f, 9, 13, 10);
        this.RArm2.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RArm2.setTextureSize(128, 128);
        this.RArm2.mirror = true;
        this.setRotation(this.RArm2, 0.0f, 0.0f, 0.0f);
        this.LArm1 = new ModelRenderer((ModelBase)this, 40, 42);
        this.LArm1.addBox(-3.0f, 5.0f, -4.0f, 8, 13, 8);
        this.LArm1.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LArm1.setTextureSize(128, 128);
        this.LArm1.mirror = true;
        this.setRotation(this.LArm1, 0.0f, 0.0f, 0.0f);
        this.LArm3 = new ModelRenderer((ModelBase)this, 82, 28);
        this.LArm3.addBox(-3.5f, 30.0f, -5.5f, 10, 3, 11);
        this.LArm3.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LArm3.setTextureSize(128, 128);
        this.LArm3.mirror = true;
        this.setRotation(this.LArm3, 0.0f, 0.0f, 0.0f);
        this.LArm2 = new ModelRenderer((ModelBase)this, 82, 69);
        this.LArm2.addBox(-3.0f, 18.0f, -5.0f, 9, 13, 10);
        this.LArm2.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LArm2.setTextureSize(128, 128);
        this.LArm2.mirror = true;
        this.setRotation(this.LArm2, 0.0f, 0.0f, 0.0f);
        this.LBodyR = new ModelRenderer((ModelBase)this, 0, 18);
        this.LBodyR.addBox(-2.0f, 0.0f, -2.0f, 10, 10, 11);
        this.LBodyR.setRotationPoint(-5.0f, -13.5f, -4.0f);
        this.LBodyR.setTextureSize(128, 128);
        this.LBodyR.mirror = true;
        this.setRotation(this.LBodyR, 0.0f, 0.0f, 0.7853982f);
        this.RArm3 = new ModelRenderer((ModelBase)this, 82, 28);
        this.RArm3.addBox(-6.5f, 30.0f, -5.5f, 10, 3, 11);
        this.RArm3.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RArm3.setTextureSize(128, 128);
        this.RArm3.mirror = true;
        this.setRotation(this.RArm3, 0.0f, 0.0f, 0.0f);
        this.LFinger1 = new ModelRenderer((ModelBase)this, 82, 28);
        this.LFinger1.addBox(4.5f, 33.0f, -1.0f, 1, 3, 2);
        this.LFinger1.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LFinger1.setTextureSize(128, 128);
        this.LFinger1.mirror = true;
        this.setRotation(this.LFinger1, 0.0f, 0.0f, 0.0f);
        this.LFinger4 = new ModelRenderer((ModelBase)this, 82, 28);
        this.LFinger4.addBox(0.5f, 33.0f, -4.5f, 2, 3, 1);
        this.LFinger4.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LFinger4.setTextureSize(128, 128);
        this.LFinger4.mirror = true;
        this.setRotation(this.LFinger4, 0.0f, 0.0f, 0.0f);
        this.LFinger3 = new ModelRenderer((ModelBase)this, 82, 28);
        this.LFinger3.addBox(-2.5f, 33.0f, -1.0f, 1, 3, 2);
        this.LFinger3.setRotationPoint(18.0f, -16.0f, -0.5f);
        this.LFinger3.setTextureSize(128, 128);
        this.LFinger3.mirror = true;
        this.setRotation(this.LFinger3, 0.0f, 0.0f, 0.0f);
        this.RFinger1 = new ModelRenderer((ModelBase)this, 82, 28);
        this.RFinger1.addBox(1.5f, 33.0f, -1.0f, 1, 3, 2);
        this.RFinger1.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RFinger1.setTextureSize(128, 128);
        this.RFinger1.mirror = true;
        this.setRotation(this.RFinger1, 0.0f, 0.0f, 0.0f);
        this.RFinger2 = new ModelRenderer((ModelBase)this, 82, 28);
        this.RFinger2.addBox(-2.5f, 33.0f, 3.5f, 2, 3, 1);
        this.RFinger2.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RFinger2.setTextureSize(128, 128);
        this.RFinger2.mirror = true;
        this.setRotation(this.RFinger2, 0.0f, 0.0f, 0.0f);
        this.RFinger4 = new ModelRenderer((ModelBase)this, 82, 28);
        this.RFinger4.addBox(-2.5f, 33.0f, -4.5f, 2, 3, 1);
        this.RFinger4.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RFinger4.setTextureSize(128, 128);
        this.RFinger4.mirror = true;
        this.setRotation(this.RFinger4, 0.0f, 0.0f, 0.0f);
        this.RFinger3 = new ModelRenderer((ModelBase)this, 82, 28);
        this.RFinger3.addBox(-5.5f, 33.0f, -1.0f, 1, 3, 2);
        this.RFinger3.setRotationPoint(-18.0f, -16.0f, -0.5f);
        this.RFinger3.setTextureSize(128, 128);
        this.RFinger3.mirror = true;
        this.setRotation(this.RFinger3, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.LShoulder.render(f5);
        this.crystal.render(f5);
        this.Ring3.render(f5);
        this.Ring1.render(f5);
        this.Ring2.render(f5);
        this.headF4.render(f5);
        this.Body.render(f5);
        this.RShoulder.render(f5);
        this.LBodyL.render(f5);
        this.crown2.render(f5);
        this.Jaw.render(f5);
        this.headF1.render(f5);
        this.headF3.render(f5);
        this.headF2.render(f5);
        this.headB.render(f5);
        this.crown1.render(f5);
        this.forehead.render(f5);
        this.LFinger2.render(f5);
        this.RArm1.render(f5);
        this.RArm2.render(f5);
        this.LArm1.render(f5);
        this.LArm3.render(f5);
        this.LArm2.render(f5);
        this.LBodyR.render(f5);
        this.RArm3.render(f5);
        this.LFinger1.render(f5);
        this.LFinger4.render(f5);
        this.LFinger3.render(f5);
        this.RFinger1.render(f5);
        this.RFinger2.render(f5);
        this.RFinger4.render(f5);
        this.RFinger3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setLivingAnimations(EntityLivingBase e, float par2, float par3, float par4) {
        double r = 0.0;
        float armR = MathHelper.cos((float)(par2 * 0.6662f + (float)Math.PI)) * par3 * 0.5f;
        float armL = MathHelper.cos((float)(par2 * 0.6662f)) * par3 * 0.5f;
        if (((IAttackTimer)e).getAttackTimer() > 0) {
            r = this.getSwingAngle((float)((IAttackTimer)e).getAttackTimer() - par4, 10.0f) - 1.0f;
        }
        this.RFinger3.rotateAngleX = this.RFinger4.rotateAngleX = (float)r + armR;
        this.RFinger2.rotateAngleX = this.RFinger4.rotateAngleX;
        this.RFinger1.rotateAngleX = this.RFinger4.rotateAngleX;
        this.RArm3.rotateAngleX = this.RFinger4.rotateAngleX;
        this.RArm2.rotateAngleX = this.RFinger4.rotateAngleX;
        this.RArm1.rotateAngleX = this.RFinger4.rotateAngleX;
        this.LFinger3.rotateAngleX = this.LFinger4.rotateAngleX = (float)r + armL;
        this.LFinger2.rotateAngleX = this.LFinger4.rotateAngleX;
        this.LFinger1.rotateAngleX = this.LFinger4.rotateAngleX;
        this.LArm3.rotateAngleX = this.LFinger4.rotateAngleX;
        this.LArm2.rotateAngleX = this.LFinger4.rotateAngleX;
        this.LArm1.rotateAngleX = this.LFinger4.rotateAngleX;
    }

    private float getSwingAngle(float timer, float maxTimer) {
        return (Math.abs(timer - maxTimer / 2.0f) - maxTimer * 0.25f) / (maxTimer * 0.25f);
    }
}

