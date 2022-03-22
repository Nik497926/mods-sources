/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.entity.gaia.IMinion;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.common.Botania;

public class EntityGaiaQuickened
extends Entity
implements IMinion {
    private static final String TAG_ATK = "atk";
    private static final String TAG_EVIL = "evil";
    private static final int range = 8;
    EntityLivingBase thrower;

    public EntityGaiaQuickened(EntityLivingBase thrower, boolean evil, float dam) {
        super(thrower.worldObj);
        this.setSize(0.0f, 0.0f);
        this.setEvil(evil);
        this.setATK(dam);
        this.thrower = thrower;
    }

    public EntityGaiaQuickened(World world) {
        super(world);
        this.setSize(0.0f, 0.0f);
    }

    public void onUpdate() {
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        super.onUpdate();
        if (this.ticksExisted > 160) {
            this.setDead();
        }
        for (int area = 0; area < 24; ++area) {
            Botania.proxy.wispFX(this.worldObj, this.posX - (double)range + Math.random() * (double)range * 2.0 * (double)(1 + area / 30), this.posY - (double)range + Math.random() * (double)range * 2.0 * (double)(1 + area / 30), this.posZ - (double)range + Math.random() * (double)range * 2.0 * (double)(1 + area / 30), 1.8f, 1.4f, 0.6f, 0.4f, -0.015f, 2.0f);
        }
        if (!this.worldObj.isRemote) {
            AxisAlignedBB var6 = AxisAlignedBB.getBoundingBox(this.posX - (double)range, this.posY - (double)range, this.posZ - (double)range, this.posX + (double)range, this.posY + (double)range, this.posZ + (double)range);
            List<EntityLiving> livings = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, var6);
            if (this.ticksExisted % 5 == 0) {
                for (EntityLiving var7 : livings) {
                    var7.attackEntityFrom(this.thrower == null ? DamageSource.generic : DamageSource.causeMobDamage(this.thrower), this.getATK());
                    var7.motionX *= 0.35f;
                    var7.motionY *= 0.35f;
                    var7.motionZ *= 0.35f;
                }
            }
        }
    }

    public boolean isEvil() {
        return this.dataWatcher.getWatchableObjectByte(25) == 1;
    }

    public void setEvil(boolean evil) {
        this.dataWatcher.updateObject(25, (byte)(evil ? 1 : 0));
    }

    public float getATK() {
        return this.dataWatcher.getWatchableObjectFloat(26);
    }

    public void setATK(float f) {
        this.dataWatcher.updateObject(26, Float.valueOf(f));
    }

    protected void entityInit() {
        this.dataWatcher.addObject(25, 0);
        this.dataWatcher.addObject(26, Float.valueOf(0.0f));
    }

    protected void readEntityFromNBT(NBTTagCompound cmp) {
        this.setATK(cmp.getFloat(TAG_ATK));
        this.setEvil(cmp.getBoolean(TAG_EVIL));
    }

    protected void writeEntityToNBT(NBTTagCompound cmp) {
        cmp.setBoolean(TAG_EVIL, this.isEvil());
        cmp.setFloat(TAG_ATK, this.getATK());
    }

    @Override
    public boolean canDestroy() {
        return this.thrower instanceof IBotaniaBossWithShader;
    }
}

