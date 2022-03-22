/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLycorisradiata
extends EntityCreature {
    public EntityLycorisradiata(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.experienceValue = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0);
    }

    protected boolean isAIEnabled() {
        return false;
    }

    public void onLivingUpdate() {
        List lycoris1;
        float lycoris;
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && (lycoris = this.getBrightness(1.0f)) > 0.5f && this.rand.nextFloat() * 30.0f < (lycoris - 0.4f) * 2.0f) {
            this.setFire(8);
        }
        if ((lycoris1 = this.worldObj.getEntitiesWithinAABB(EntityLycorisradiata.class, AxisAlignedBB.getBoundingBox(this.posX - 40.0, this.posY - 40.0, this.posZ - 40.0, this.posX + 40.0, this.posY + 40.0, this.posZ + 40.0))).size() > 1) {
            this.setDead();
        }
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected void fall(float p_70069_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_) {
        return 0xF000F0;
    }

    public float getBrightness(float p_70013_1_) {
        return 2.0f;
    }

    protected String getDeathSound() {
        return "dig.grass";
    }
}

