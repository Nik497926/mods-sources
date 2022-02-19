/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDepthsBeast
extends ModelBase {
    ModelRenderer Chest;
    ModelRenderer Tummy;
    ModelRenderer LeftLeg;
    ModelRenderer RightLeg;
    ModelRenderer RightShoulder;
    ModelRenderer LeftShoulder;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer RightIDK;
    ModelRenderer LeftIDK;
    ModelRenderer Head;

    public ModelDepthsBeast() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Chest = new ModelRenderer((ModelBase)this, 0, 0);
        this.Chest.addBox(-6.5f, -2.0f, -0.5f, 13, 7, 7);
        this.Chest.setRotationPoint(0.0f, 1.0f, -3.0f);
        this.Chest.setTextureSize(64, 64);
        this.Chest.mirror = true;
        this.setRotation(this.Chest, 0.0f, 0.0f, 0.0f);
        this.Tummy = new ModelRenderer((ModelBase)this, 0, 14);
        this.Tummy.addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
        this.Tummy.setRotationPoint(0.0f, 6.0f, 0.0f);
        this.Tummy.setTextureSize(64, 64);
        this.Tummy.mirror = true;
        this.setRotation(this.Tummy, 0.0f, 0.0f, 0.0f);
        this.LeftLeg = new ModelRenderer((ModelBase)this, 24, 14);
        this.LeftLeg.addBox(-1.5f, 0.0f, -2.0f, 3, 12, 4);
        this.LeftLeg.setRotationPoint(2.5f, 12.0f, 0.0f);
        this.LeftLeg.setTextureSize(64, 64);
        this.LeftLeg.mirror = true;
        this.setRotation(this.LeftLeg, 0.0f, 0.0f, 0.0f);
        this.RightLeg = new ModelRenderer((ModelBase)this, 24, 14);
        this.RightLeg.addBox(-1.5f, 0.0f, -2.0f, 3, 12, 4);
        this.RightLeg.setRotationPoint(-2.5f, 12.0f, 0.0f);
        this.RightLeg.setTextureSize(128, 64);
        this.RightLeg.mirror = true;
        this.setRotation(this.RightLeg, -0.0174533f, 0.0f, 0.0f);
        this.RightShoulder = new ModelRenderer((ModelBase)this, 40, 0);
        this.RightShoulder.addBox(-5.5f, -3.0f, -3.0f, 6, 6, 6);
        this.RightShoulder.setRotationPoint(-7.0f, 2.0f, 0.0f);
        this.RightShoulder.setTextureSize(64, 64);
        this.RightShoulder.mirror = true;
        this.setRotation(this.RightShoulder, 0.0f, 0.0f, 0.0f);
        this.LeftShoulder = new ModelRenderer((ModelBase)this, 40, 0);
        this.LeftShoulder.addBox(-0.5f, -3.0f, -3.0f, 6, 6, 6);
        this.LeftShoulder.setRotationPoint(7.0f, 2.0f, 0.0f);
        this.LeftShoulder.setTextureSize(128, 64);
        this.LeftShoulder.mirror = true;
        this.setRotation(this.LeftShoulder, 0.0f, 0.0f, 0.0f);
        this.RightArm = new ModelRenderer((ModelBase)this, 38, 14);
        this.RightArm.addBox(-1.5f, -1.5f, -1.5f, 3, 13, 3);
        this.RightArm.setRotationPoint(-10.0f, 6.5f, 0.0f);
        this.RightArm.setTextureSize(64, 64);
        this.RightArm.mirror = true;
        this.setRotation(this.RightArm, 0.0f, 0.0f, 0.0f);
        this.LeftArm = new ModelRenderer((ModelBase)this, 38, 14);
        this.LeftArm.addBox(-1.5f, -1.5f, -1.5f, 3, 13, 3);
        this.LeftArm.setRotationPoint(10.0f, 6.5f, 0.0f);
        this.LeftArm.setTextureSize(64, 64);
        this.LeftArm.mirror = true;
        this.setRotation(this.LeftArm, 0.0f, 0.0f, 0.0f);
        this.RightIDK = new ModelRenderer((ModelBase)this, 24, 30);
        this.RightIDK.addBox(-2.0f, 5.5f, -2.0f, 4, 4, 4);
        this.RightIDK.setRotationPoint(-10.0f, 6.5f, 0.0f);
        this.RightIDK.setTextureSize(64, 64);
        this.RightIDK.mirror = true;
        this.setRotation(this.RightIDK, 0.0f, 0.0f, 0.0f);
        this.LeftIDK = new ModelRenderer((ModelBase)this, 24, 30);
        this.LeftIDK.addBox(-2.0f, 5.5f, -2.0f, 4, 4, 4);
        this.LeftIDK.setRotationPoint(10.0f, 6.5f, 0.0f);
        this.LeftIDK.setTextureSize(64, 64);
        this.LeftIDK.mirror = true;
        this.setRotation(this.LeftIDK, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 24);
        this.Head.addBox(-3.0f, -3.0f, -6.0f, 6, 6, 6);
        this.Head.setRotationPoint(0.0f, 1.0f, -3.5f);
        this.Head.setTextureSize(64, 64);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Chest.render(f5);
        this.Tummy.render(f5);
        this.LeftLeg.render(f5);
        this.RightLeg.render(f5);
        this.RightShoulder.render(f5);
        this.LeftShoulder.render(f5);
        this.RightArm.render(f5);
        this.LeftArm.render(f5);
        this.RightIDK.render(f5);
        this.LeftIDK.render(f5);
        this.Head.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.RightArm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.LeftArm.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.RightIDK.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.LeftIDK.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.RightLeg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.LeftLeg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
    }
}

