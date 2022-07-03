/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.entity.gaia.EntityMagicLandmineII;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.lib.LibObfuscation;

public class EntityOdinMissile
extends EntityThrowable {
    private static final String TAG_TIME = "time";
    double lockX;
    double lockY = -1.0;
    double lockZ;
    int time = 0;

    public EntityOdinMissile(World world) {
        super(world);
        this.setSize(0.0f, 0.0f);
    }

    public EntityOdinMissile(EntityLivingBase thrower, boolean evil) {
        this(thrower.worldObj);
        ReflectionHelper.setPrivateValue(EntityThrowable.class, (this), (Object)thrower, (String[])LibObfuscation.THROWER);
        this.setEvil(evil);
    }

    protected void entityInit() {
        this.dataWatcher.addObject(25, (Object)0);
        this.dataWatcher.addObject(26, (Object)0);
    }

    public void setEvil(boolean evil) {
        this.dataWatcher.updateObject(25, (Object)((byte)(evil ? 1 : 0)));
    }

    public boolean isEvil() {
        return this.dataWatcher.getWatchableObjectByte(25) == 1;
    }

    public void setTarget(EntityLivingBase e) {
        this.dataWatcher.updateObject(26, (Object)(e == null ? -1 : e.getEntityId()));
    }

    public EntityLivingBase getTargetEntity() {
        int id = this.dataWatcher.getWatchableObjectInt(26);
        Entity e = this.worldObj.getEntityByID(id);
        if (e != null && e instanceof EntityLivingBase) {
            return (EntityLivingBase)e;
        }
        return null;
    }

    public void onUpdate() {
        double lastTickPosX = this.lastTickPosX;
        double lastTickPosY = this.lastTickPosY;
        double lastTickPosZ = this.lastTickPosZ;
        super.onUpdate();
        if (!(this.worldObj.isRemote || this.getTarget() && this.time <= 40)) {
            this.setDead();
            return;
        }
        boolean evil = this.isEvil();
        Vector3 thisVec = Vector3.fromEntityCenter((Entity)this);
        Vector3 oldPos = new Vector3(lastTickPosX, lastTickPosY, lastTickPosZ);
        Vector3 diff = thisVec.copy().sub(oldPos);
        Vector3 step = diff.copy().normalize().multiply(0.05);
        int steps = (int)(diff.mag() / step.mag());
        Vector3 particlePos = oldPos.copy();
        Botania.proxy.setSparkleFXCorrupt(evil);
        for (int i = 0; i < steps; ++i) {
            Botania.proxy.sparkleFX(this.worldObj, particlePos.x, particlePos.y, particlePos.z, 1.0f, evil ? 0.0f : 0.4f, 1.0f, 0.8f, 2);
            if (this.worldObj.rand.nextInt(steps) <= 1) {
                Botania.proxy.sparkleFX(this.worldObj, particlePos.x + (Math.random() - 0.5) * 0.4, particlePos.y + (Math.random() - 0.5) * 0.4, particlePos.z + (Math.random() - 0.5) * 0.4, 1.0f, evil ? 0.0f : 0.4f, 1.0f, 0.8f, 2);
            }
            particlePos.add(step);
        }
        Botania.proxy.setSparkleFXCorrupt(false);
        EntityLivingBase target = this.getTargetEntity();
        if (target != null) {
            if (this.lockY == -1.0) {
                this.lockX = target.posX;
                this.lockY = target.posY;
                this.lockZ = target.posZ;
            }
            Vector3 targetVec = evil ? new Vector3(this.lockX, this.lockY, this.lockZ) : Vector3.fromEntityCenter((Entity)target);
            Vector3 diffVec = targetVec.copy().sub(thisVec);
            Vector3 motionVec = diffVec.copy().normalize().multiply(evil ? 0.5 : 0.6);
            this.motionX = motionVec.x;
            this.motionY = motionVec.y;
            if (this.time < 10) {
                this.motionY = Math.abs(this.motionY);
            }
            this.motionZ = motionVec.z;
            List targetList = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox((double)(this.posX - 0.5), (double)(this.posY - 0.5), (double)(this.posZ - 0.5), (double)(this.posX + 0.5), (double)(this.posY + 0.5), (double)(this.posZ + 0.5)));
            if (targetList.contains(target) && target != null) {
                EntityLivingBase thrower = this.getThrower();
                if (thrower != null) {
                    EntityPlayer player = thrower instanceof EntityPlayer ? (EntityPlayer)thrower : null;
                    target.attackEntityFrom(EntityMagicLandmineII.damageOdin, 4.0f);
                } else {
                    target.attackEntityFrom(EntityMagicLandmineII.damageOdin, 4.0f);
                }
                this.setDead();
            }
            if (evil && diffVec.mag() < 1.0) {
                this.setDead();
            }
        }
        ++this.time;
    }

    public void writeEntityToNBT(NBTTagCompound cmp) {
        super.writeEntityToNBT(cmp);
        cmp.setInteger(TAG_TIME, this.time);
    }

    public void readEntityFromNBT(NBTTagCompound cmp) {
        super.readEntityFromNBT(cmp);
        this.time = cmp.getInteger(TAG_TIME);
    }

    public boolean getTarget() {
        EntityLivingBase target = this.getTargetEntity();
        if (target != null && target.getHealth() > 0.0f && !target.isDead && this.worldObj.loadedEntityList.contains(target)) {
            return true;
        }
        if (target != null) {
            this.setTarget(null);
        }
        double range = 12.0;
        List entities = this.worldObj.getEntitiesWithinAABB(this.isEvil() ? EntityPlayer.class : IMob.class, AxisAlignedBB.getBoundingBox((double)(this.posX - range), (double)(this.posY - range), (double)(this.posZ - range), (double)(this.posX + range), (double)(this.posY + range), (double)(this.posZ + range)));
        while (entities.size() > 0) {
            Entity e = (Entity)entities.get(this.worldObj.rand.nextInt(entities.size()));
            if (!(e instanceof EntityLivingBase) || e.isDead) {
                entities.remove(e);
                continue;
            }
            target = (EntityLivingBase)e;
            this.setTarget(target);
            break;
        }
        return target != null;
    }

    protected void onImpact(MovingObjectPosition pos) {
        Block block = this.worldObj.getBlock(pos.blockX, pos.blockY, pos.blockZ);
        if (!(block instanceof BlockBush || block instanceof BlockLeaves || pos.entityHit != null && this.getTargetEntity() != pos.entityHit)) {
            this.setDead();
        }
    }
}

