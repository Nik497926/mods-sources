/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelAsgard
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightArm;
    ModelRenderer rightarmMain;
    ModelRenderer leftarm;
    ModelRenderer leftarmMain;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelAsgard() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.setTextureOffset("head.skull", 0, 0);
        this.setTextureOffset("head.lHorn0", 40, 6);
        this.setTextureOffset("head.lHorn1", 40, 6);
        this.setTextureOffset("head.lHorn2", 40, 6);
        this.setTextureOffset("head.lHorn3", 42, 6);
        this.setTextureOffset("head.lHorn4", 40, 6);
        this.setTextureOffset("head.lHorn5", 40, 6);
        this.setTextureOffset("head.lHorn6", 40, 6);
        this.setTextureOffset("head.lHorn7", 40, 6);
        this.setTextureOffset("head.lHorn8", 40, 6);
        this.setTextureOffset("head.lHorn9", 42, 6);
        this.setTextureOffset("head.lHorn10", 40, 6);
        this.setTextureOffset("head.lHorn11", 40, 6);
        this.head = new ModelRenderer((ModelBase)this, "head");
        this.head.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head.setTextureSize(64, 64);
        this.head.addBox("skull", -4.0f, -8.0f, -5.0f, 8, 8, 8);
        this.head.addBox("lHorn0", -4.0f, -9.0f, -2.0f, 3, 1, 4);
        this.head.addBox("lHorn1", -4.0f, -10.0f, -2.0f, 2, 1, 4);
        this.head.addBox("lHorn2", -4.0f, -11.0f, -1.5f, 1, 1, 3);
        this.head.addBox("lHorn3", -4.0f, -12.0f, -1.5f, 1, 1, 3);
        this.head.addBox("lHorn4", -4.0f, -13.0f, -1.0f, 2, 1, 2);
        this.head.addBox("lHorn5", -4.0f, -14.0f, -1.0f, 3, 1, 2);
        this.head.addBox("lHorn6", 1.0f, -9.0f, -2.0f, 3, 1, 4);
        this.head.addBox("lHorn7", 2.0f, -10.0f, -2.0f, 2, 1, 4);
        this.head.addBox("lHorn8", 3.0f, -11.0f, -1.5f, 1, 1, 3);
        this.head.addBox("lHorn9", 3.0f, -12.0f, -1.5f, 1, 1, 3);
        this.head.addBox("lHorn10", 2.0f, -13.0f, -1.0f, 2, 1, 2);
        this.head.addBox("lHorn11", 1.0f, -14.0f, -1.0f, 3, 1, 2);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 64);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.setTextureOffset("rightArm.dop0", 0, 38);
        this.setTextureOffset("rightArm.dop1", 44, 38);
        this.setTextureOffset("rightArm.dop2", 0, 38);
        this.setTextureOffset("rightArm.dop3", 44, 38);
        this.setTextureOffset("rightArm.dop4", 0, 35);
        this.setTextureOffset("rightArm.dop5", 40, 44);
        this.setTextureOffset("rightArm.dop6", 40, 47);
        this.setTextureOffset("rightArm.dop7", 41, 40);
        this.setTextureOffset("rightArm.dop8", 40, 39);
        this.rightArm = new ModelRenderer((ModelBase)this, "rightArm");
        this.rightArm.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightArm, 0.0f, 0.0f, 0.0f);
        this.rightArm.setTextureSize(64, 64);
        this.rightArm.addBox("dop0", -7.0f, 9.0f, -8.0f, 2, 2, 1);
        this.rightArm.addBox("dop1", -7.5f, 8.5f, -7.5f, 3, 3, 1);
        this.rightArm.addBox("dop2", -8.5f, 7.5f, -7.0f, 5, 5, 1);
        this.rightArm.addBox("dop3", -9.5f, 6.5f, -6.5f, 7, 7, 1);
        this.rightArm.addBox("dop4", -10.5f, 5.5f, -6.0f, 9, 9, 1);
        this.rightArm.addBox("dop5", -10.0f, 5.0f, -5.0f, 8, 2, 3);
        this.rightArm.addBox("dop6", -10.0f, 13.0f, -5.0f, 8, 2, 3);
        this.rightArm.addBox("dop7", -11.0f, 6.0f, -5.0f, 2, 8, 3);
        this.rightArm.addBox("dop8", -3.0f, 6.0f, -5.0f, 2, 8, 3);
        this.rightarmMain = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarmMain.addBox(-8.0f, 0.0f, -1.0f, 4, 12, 4);
        this.rightarmMain.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightarmMain.setTextureSize(64, 64);
        this.setRotation(this.rightarmMain, -19.0f, 0.0f, 0.0f);
        this.rightArm.addChild(this.rightarmMain);
        this.setTextureOffset("leftarm.dop0", 0, 32);
        this.setTextureOffset("leftarm.dop1", 40, 0);
        this.setTextureOffset("leftarm.dop2", 40, 0);
        this.setTextureOffset("leftarm.dop3", 41, 0);
        this.leftarm = new ModelRenderer((ModelBase)this, "leftarm");
        this.leftarm.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.addBox("dop0", 5.5f, 8.5f, -14.0f, 1, 1, 28);
        this.leftarm.addBox("dop1", 4.5f, 7.5f, -16.0f, 3, 3, 2);
        this.leftarm.addBox("dop2", 5.0f, 8.0f, -19.0f, 2, 2, 3);
        this.leftarm.addBox("dop3", 5.5f, 8.5f, -24.0f, 1, 1, 5);
        this.leftarmMain = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarmMain.addBox(4.0f, 0.0f, -1.0f, 4, 12, 4);
        this.leftarmMain.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftarmMain.setTextureSize(64, 64);
        this.leftarmMain.mirror = true;
        this.setRotation(this.leftarmMain, -44.5f, 0.0f, 0.0f);
        this.leftarm.addChild(this.leftarmMain);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.setRotationPoint(-1.9f, 12.0f, 0.0f);
        this.rightleg.setTextureSize(64, 64);
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.setRotationPoint(1.9f, 12.0f, 0.0f);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.rightArm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.head.rotateAngleY = par4 / 57.295776f;
        this.head.rotateAngleX = par5 / 57.295776f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)0.6662f) * 2.0f * par2 * 0.5f;
        this.leftarm.rotateAngleY = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ -= MathHelper.cos((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.rotateAngleX -= MathHelper.sin((float)(par3 * 0.067f)) * 0.05f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

