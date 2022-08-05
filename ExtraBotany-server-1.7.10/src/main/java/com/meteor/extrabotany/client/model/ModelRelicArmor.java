/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

@SideOnly(value= Side.CLIENT)
public class ModelRelicArmor
extends ModelBiped {
    public ModelRenderer helm;
    public ModelRenderer body;
    public ModelRenderer armr;
    public ModelRenderer armL;
    public ModelRenderer belt;
    public ModelRenderer bootR;
    public ModelRenderer bootL;
    public ModelRenderer helm2;
    public ModelRenderer helm3;
    public ModelRenderer helm4;
    public ModelRenderer helmLeaf1;
    public ModelRenderer helmLeaf2;
    public ModelRenderer helmLeaf3;
    public ModelRenderer helmLeaf4;
    public ModelRenderer helmLeaf5;
    public ModelRenderer helmLeaf6;
    public ModelRenderer helmbranch1;
    public ModelRenderer helmbranch2;
    public ModelRenderer helmbranch3;
    public ModelRenderer helmbranch4;
    public ModelRenderer body2;
    public ModelRenderer armRpauldron;
    public ModelRenderer armRbranch1;
    public ModelRenderer armRbranch2;
    public ModelRenderer armLpauldron;
    public ModelRenderer armLbranch1;
    public ModelRenderer armLbranch2;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer bootR1;
    public ModelRenderer bootRbranch;
    public ModelRenderer bootL2;
    public ModelRenderer bootLbranch;
    int slot;

    public ModelRelicArmor(int slot) {
        this.slot = slot;
        this.textureWidth = 64;
        this.textureHeight = 128;
        float s = 0.2f;
        this.armr = new ModelRenderer((ModelBase)this, 0, 77);
        this.armr.setRotationPoint(-5.0f, 2.0f, -0.0f);
        this.armr.addBox(-3.0f, 3.0f, -2.0f, 4, 7, 4, s);
        this.setRotateAngle(this.armr, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 44);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.addBox(-4.5f, 0.0f, -3.5f, 9, 6, 6, s);
        this.setRotateAngle(this.body, 0.0f, 0.0f, 0.0f);
        this.helm4 = new ModelRenderer((ModelBase)this, 56, 32);
        this.helm4.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helm4.addBox(-1.0f, -7.5f, -6.5f, 2, 6, 2, s);
        this.setRotateAngle(this.helm4, 0.0f, 0.0f, 0.0f);
        this.bootR1 = new ModelRenderer((ModelBase)this, 32, 77);
        this.bootR1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bootR1.addBox(-2.0f, 6.0f, -2.0f, 4, 2, 4, s);
        this.helmbranch4 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch4.mirror = true;
        this.helmbranch4.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helmbranch4.addBox(-2.0f, -8.0f, -4.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch4, 0.2617994f, 0.0f, 1.0471976f);
        this.bootL = new ModelRenderer((ModelBase)this, 32, 83);
        this.bootL.mirror = true;
        this.bootL.setRotationPoint(1.9f, 12.0f, 0.0f);
        this.bootL.addBox(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootL, 0.0f, 0.0f, 0.0f);
        this.bootR = new ModelRenderer((ModelBase)this, 32, 83);
        this.bootR.setRotationPoint(-1.9f, 12.0f, 0.1f);
        this.bootR.addBox(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootR, 0.0f, 0.0f, 0.0f);
        this.helmLeaf5 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf5.mirror = true;
        this.helmLeaf5.setRotationPoint(0.0f, 0.2f, 0.0f);
        this.helmLeaf5.addBox(-1.0f, -11.0f, -4.5f, 2, 5, 1, s);
        this.setRotateAngle(this.helmLeaf5, -0.5235988f, -0.5235988f, 0.5235988f);
        this.bootLbranch = new ModelRenderer((ModelBase)this, 48, 77);
        this.bootLbranch.mirror = true;
        this.bootLbranch.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bootLbranch.addBox(8.0f, 1.0f, -2.0f, 1, 2, 5, s);
        this.setRotateAngle(this.bootLbranch, 0.2617994f, 0.0f, 1.0471976f);
        this.helmLeaf4 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf4.mirror = true;
        this.helmLeaf4.setRotationPoint(0.0f, 0.2f, 0.0f);
        this.helmLeaf4.addBox(-1.5f, -9.0f, -6.0f, 2, 3, 1, s);
        this.setRotateAngle(this.helmLeaf4, -0.2617994f, -0.2617994f, 0.5235988f);
        this.helmLeaf2 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf2.setRotationPoint(0.0f, 0.2f, 0.0f);
        this.helmLeaf2.addBox(-1.0f, -11.0f, -4.5f, 2, 5, 1, s);
        this.setRotateAngle(this.helmLeaf2, -0.5235988f, 0.5235988f, -0.5235988f);
        this.helm = new ModelRenderer((ModelBase)this, 0, 32);
        this.helm.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helm.addBox(-4.0f, -8.0f, -4.5f, 8, 3, 9, s);
        this.setRotateAngle(this.helm, 0.08726646f, 0.0f, 0.0f);
        this.helm2 = new ModelRenderer((ModelBase)this, 34, 32);
        this.helm2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helm2.addBox(-4.0f, -5.0f, -4.5f, 2, 5, 6, s);
        this.helm3 = new ModelRenderer((ModelBase)this, 34, 32);
        this.helm3.mirror = true;
        this.helm3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helm3.addBox(2.0f, -5.0f, -4.5f, 2, 5, 6, s);
        this.helmbranch1 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch1.mirror = true;
        this.helmbranch1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helmbranch1.addBox(-2.0f, -10.0f, -1.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch1, 0.5235988f, 0.0f, -0.08726646f);
        this.bootL2 = new ModelRenderer((ModelBase)this, 32, 77);
        this.bootL2.mirror = true;
        this.bootL2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bootL2.addBox(-2.0f, 6.0f, -2.0f, 4, 2, 4, s);
        this.helmbranch2 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helmbranch2.addBox(1.0f, -10.0f, -1.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch2, 0.5235988f, 0.0f, 0.08726646f);
        this.legR = new ModelRenderer((ModelBase)this, 16, 77);
        this.legR.setRotationPoint(-1.9f, 12.0f, 0.0f);
        this.legR.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, s);
        this.setRotateAngle(this.legR, 0.0f, 0.0f, 0.0f);
        this.helmLeaf6 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf6.mirror = true;
        this.helmLeaf6.setRotationPoint(0.0f, 0.2f, 0.0f);
        this.helmLeaf6.addBox(-0.5f, -13.0f, -3.0f, 2, 7, 1, s);
        this.setRotateAngle(this.helmLeaf6, -0.7853982f, -0.7853982f, 0.7853982f);
        this.armLbranch1 = new ModelRenderer((ModelBase)this, 51, 44);
        this.armLbranch1.mirror = true;
        this.armLbranch1.setRotationPoint(0.0f, 0.0f, -0.0f);
        this.armLbranch1.addBox(2.5f, -5.0f, -1.0f, 1, 5, 2, s);
        this.setRotateAngle(this.armLbranch1, 0.0f, 0.0f, 0.7853982f);
        this.bootRbranch = new ModelRenderer((ModelBase)this, 48, 77);
        this.bootRbranch.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bootRbranch.addBox(-9.0f, 1.0f, -2.0f, 1, 2, 5, s);
        this.setRotateAngle(this.bootRbranch, 0.2617994f, 0.0f, -1.0471976f);
        this.armRbranch2 = new ModelRenderer((ModelBase)this, 50, 43);
        this.armRbranch2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.armRbranch2.addBox(-1.5f, -5.0f, -1.5f, 1, 5, 3, s);
        this.setRotateAngle(this.armRbranch2, 0.0f, 0.0f, -0.5235988f);
        this.helmLeaf3 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf3.setRotationPoint(0.0f, 0.2f, 0.0f);
        this.helmLeaf3.addBox(-1.5f, -13.0f, -3.0f, 2, 7, 1, s);
        this.setRotateAngle(this.helmLeaf3, -0.7853982f, 0.7853982f, -0.7853982f);
        this.armRpauldron = new ModelRenderer((ModelBase)this, 0, 66);
        this.armRpauldron.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.armRpauldron.addBox(-4.0f, -2.0f, -3.0f, 5, 5, 6, s);
        this.armLbranch2 = new ModelRenderer((ModelBase)this, 50, 43);
        this.armLbranch2.mirror = true;
        this.armLbranch2.setRotationPoint(0.0f, 0.0f, -0.0f);
        this.armLbranch2.addBox(0.5f, -5.0f, -1.5f, 1, 5, 3, s);
        this.setRotateAngle(this.armLbranch2, 0.0f, 0.0f, 0.5235988f);
        this.armL = new ModelRenderer((ModelBase)this, 0, 77);
        this.armL.mirror = true;
        this.armL.setRotationPoint(5.0f, 2.0f, -0.0f);
        this.armL.addBox(-1.0f, 3.0f, -2.0f, 4, 7, 4, s);
        this.setRotateAngle(this.armL, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 0, 57);
        this.body2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body2.addBox(-4.0f, 6.0f, -2.5f, 8, 4, 5, s);
        this.setRotateAngle(this.body2, -0.08726646f, 0.0f, 0.0f);
        this.helmbranch3 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch3.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.helmbranch3.addBox(1.0f, -8.0f, -4.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch3, 0.2617994f, 0.0f, -1.0471976f);
        this.armRbranch1 = new ModelRenderer((ModelBase)this, 51, 44);
        this.armRbranch1.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.armRbranch1.addBox(-3.5f, -5.0f, -1.0f, 1, 5, 2, s);
        this.setRotateAngle(this.armRbranch1, 0.0f, 0.0f, -0.7853982f);
        this.belt = new ModelRenderer((ModelBase)this, 22, 66);
        this.belt.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.belt.addBox(-4.5f, 9.5f, -3.0f, 9, 3, 6, s);
        this.helmLeaf1 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf1.setRotationPoint(0.0f, 0.2f, 0.0f);
        this.helmLeaf1.addBox(-0.5f, -9.0f, -6.0f, 2, 3, 1, s);
        this.setRotateAngle(this.helmLeaf1, -0.2617994f, 0.2617994f, -0.5235988f);
        this.armLpauldron = new ModelRenderer((ModelBase)this, 0, 66);
        this.armLpauldron.mirror = true;
        this.armLpauldron.setRotationPoint(0.0f, 0.0f, -0.0f);
        this.armLpauldron.addBox(-1.0f, -2.0f, -3.0f, 5, 5, 6, s);
        this.legL = new ModelRenderer((ModelBase)this, 16, 77);
        this.legL.mirror = true;
        this.legL.setRotationPoint(1.9f, 12.0f, 0.0f);
        this.legL.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, s);
        this.setRotateAngle(this.legL, 0.0f, 0.0f, 0.0f);
        this.helm.addChild(this.helm4);
        this.bootR.addChild(this.bootR1);
        this.helm.addChild(this.helmbranch4);
        this.helm.addChild(this.helmLeaf5);
        this.bootL.addChild(this.bootLbranch);
        this.helm.addChild(this.helmLeaf4);
        this.helm.addChild(this.helmLeaf2);
        this.helm.addChild(this.helm2);
        this.helm.addChild(this.helm3);
        this.helm.addChild(this.helmbranch1);
        this.bootL.addChild(this.bootL2);
        this.helm.addChild(this.helmbranch2);
        this.belt.addChild(this.legR);
        this.helm.addChild(this.helmLeaf6);
        this.bootR.addChild(this.bootRbranch);
        this.helm.addChild(this.helmLeaf3);
        this.armr.addChild(this.armRpauldron);
        this.body.addChild(this.body2);
        this.helm.addChild(this.helmbranch3);
        this.helm.addChild(this.helmLeaf1);
        this.armL.addChild(this.armLpauldron);
        this.belt.addChild(this.legL);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.helm.showModel = this.slot == 0;
        this.body.showModel = this.slot == 1;
        this.armr.showModel = this.slot == 1;
        this.armL.showModel = this.slot == 1;
        this.legR.showModel = this.slot == 2;
        this.legL.showModel = this.slot == 2;
        this.bootL.showModel = this.slot == 3;
        this.bootR.showModel = this.slot == 3;
        this.bipedHeadwear.showModel = false;
        this.bipedHead = this.helm;
        this.bipedBody = this.body;
        this.bipedRightArm = this.armr;
        this.bipedLeftArm = this.armL;
        if (this.slot == 2) {
            this.bipedRightLeg = this.legR;
            this.bipedLeftLeg = this.legL;
        } else {
            this.bipedRightLeg = this.bootR;
            this.bipedLeftLeg = this.bootL;
        }
        this.prepareForRender(entity);
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    public void prepareForRender(Entity entity) {
        EntityLivingBase living = (EntityLivingBase)entity;
        boolean bl = this.isSneak = living != null ? living.isSneaking() : false;
        if (living != null && living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)living;
            ItemStack itemstack = player.inventory.getCurrentItem();
            this.heldItemRight = itemstack != null ? 1 : 0;
            this.aimedBow = false;
            if (itemstack != null && player.getItemInUseCount() > 0) {
                EnumAction enumaction = itemstack.getItemUseAction();
                if (enumaction == EnumAction.block) {
                    this.heldItemRight = 3;
                } else if (enumaction == EnumAction.bow) {
                    this.aimedBow = true;
                }
            }
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

