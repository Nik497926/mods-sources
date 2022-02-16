/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;

public class EntityAIOwnerWithoutTeleport
extends EntityAIBase {
    private EntityTameable thePet;
    private EntityLivingBase theOwner;
    World theWorld;
    private double field_75336_f;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;

    public EntityAIOwnerWithoutTeleport(EntityTameable p_i1625_1_, double p_i1625_2_, float p_i1625_4_, float p_i1625_5_) {
        this.thePet = p_i1625_1_;
        this.theWorld = p_i1625_1_.worldObj;
        this.field_75336_f = p_i1625_2_;
        this.petPathfinder = p_i1625_1_.getNavigator();
        this.minDist = p_i1625_4_;
        this.maxDist = p_i1625_5_;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.thePet.getOwner();
        if (entitylivingbase == null) {
            return false;
        }
        if (this.thePet.isSitting()) {
            return false;
        }
        if (this.thePet.getDistanceSqToEntity((Entity)entitylivingbase) < (double)(this.minDist * this.minDist)) {
            return false;
        }
        this.theOwner = entitylivingbase;
        return true;
    }

    public boolean continueExecuting() {
        return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity((Entity)this.theOwner) > (double)(this.maxDist * this.maxDist) && !this.thePet.isSitting();
    }

    public void startExecuting() {
        this.field_75343_h = 0;
        this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
        this.thePet.getNavigator().setAvoidsWater(false);
    }

    public void resetTask() {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
    }

    public void updateTask() {
        this.thePet.getLookHelper().setLookPositionWithEntity((Entity)this.theOwner, 10.0f, (float)this.thePet.getVerticalFaceSpeed());
        if (!this.thePet.isSitting() && --this.field_75343_h <= 0) {
            this.field_75343_h = 10;
            this.petPathfinder.tryMoveToEntityLiving((Entity)this.theOwner, this.field_75336_f);
        }
    }
}

