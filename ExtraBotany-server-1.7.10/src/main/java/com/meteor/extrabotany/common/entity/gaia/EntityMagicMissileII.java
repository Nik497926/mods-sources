/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityMagicMissile;

public class EntityMagicMissileII
extends EntityMagicMissile {
    int time = 0;
    double lockX;
    double lockY = -1.0;
    double lockZ;
    private static final String TAG_ATK = "ATK";

    public EntityMagicMissileII(EntityLivingBase thrower, boolean evil) {
        super(thrower, evil);
    }

    public EntityMagicMissileII(World world) {
        super(world);
        this.setSize(0.0f, 0.0f);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(27, Float.valueOf(0.0f));
    }

    public float getATK() {
        return this.dataWatcher.getWatchableObjectFloat(27);
    }

    public void setATK(float atk) {
        this.dataWatcher.updateObject(27, Float.valueOf(atk));
    }

    public void writeEntityToNBT(NBTTagCompound cmp) {
        super.writeEntityToNBT(cmp);
        cmp.setFloat(TAG_ATK, this.getATK());
    }

    public void readEntityFromNBT(NBTTagCompound cmp) {
        super.readEntityFromNBT(cmp);
        this.setATK(cmp.getFloat(TAG_ATK));
    }

    public void onUpdate() {
        double lastTickPosX = this.lastTickPosX;
        double lastTickPosY = this.lastTickPosY;
        double lastTickPosZ = this.lastTickPosZ;
        super.onUpdate();
        if (!(this.worldObj.isRemote || this.getTarget() && this.time <= 40)) {
            this.setDead();
        } else {
            boolean evil = this.isEvil();
            Vector3 thisVec = Vector3.fromEntityCenter(this);
            Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
            Vector3 diff = thisVec.copy().sub(oldPos);
            Vector3 step = diff.copy().normalize().multiply(0.05);
            int steps = (int)(diff.mag() / step.mag());
            Vector3 particlePos = oldPos.copy();
            Botania.proxy.setSparkleFXCorrupt(evil);
            for (int target = 0; target < steps; ++target) {
                Botania.proxy.sparkleFX(this.worldObj, particlePos.x, particlePos.y, particlePos.z, 1.0f, evil ? 0.0f : 0.4f, 1.0f, 0.8f, 2);
                if (this.worldObj.rand.nextInt(steps) <= 1) {
                    Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5) * 0.4, particlePos.y + (Math.random() - 0.5) * 0.4, particlePos.z + (Math.random() - 0.5) * 0.4, 1.0f, evil ? 0.0f : 0.4f, 1.0f, 0.8f, 2);
                }
                particlePos.add(step);
            }
            Botania.proxy.setSparkleFXCorrupt(false);
            EntityLivingBase var21 = this.getTargetEntity();
            if (var21 != null) {
                if (this.lockY == -1.0) {
                    this.lockX = var21.posX;
                    this.lockY = var21.posY;
                    this.lockZ = var21.posZ;
                }
                Vector3 targetVec = evil ? new Vector3(this.lockX, this.lockY, this.lockZ) : Vector3.fromEntityCenter(var21);
                Vector3 diffVec = targetVec.copy().sub(thisVec);
                Vector3 motionVec = diffVec.copy().normalize().multiply(evil ? 0.5 : 0.6);
                this.motionX = motionVec.x;
                this.motionY = motionVec.y;
                if (this.time < 10) {
                    this.motionY = Math.abs(this.motionY);
                }
                this.motionZ = motionVec.z;
                List targetList = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.posX - 0.5, this.posY - 0.5, this.posZ - 0.5, this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5));
                if (targetList.contains(var21) && var21 != null) {
                    EntityLivingBase thrower = this.getThrower();
                    if (thrower != null) {
                        EntityPlayer player = thrower instanceof EntityPlayer ? (EntityPlayer)thrower : null;
                        var21.attackEntityFrom(player == null ? DamageSource.causeMobDamage(thrower) : DamageSource.causePlayerDamage(player), this.getATK());
                    } else {
                        var21.attackEntityFrom(DamageSource.generic, this.getATK());
                    }
                    this.setDead();
                }
                if (evil && diffVec.mag() < 1.0) {
                    this.setDead();
                }
            }
            ++this.time;
        }
    }
}

