/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.entities.vethea;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAIGorgosionLeap
extends EntityAIBase {
    EntityLivingBase leaper;
    EntityLivingBase leapTarget;
    float leapMotionY;

    public EntityAIGorgosionLeap(EntityLiving par1EntityLiving, float par2) {
        this.leaper = par1EntityLiving;
        this.leapMotionY = par2;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        this.leapTarget = this.leaper.getAITarget();
        if (this.leapTarget == null) {
            return false;
        }
        double var1 = this.leaper.getDistanceSqToEntity((Entity)this.leapTarget);
        return var1 <= 32.0 ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(5) == 0) : false;
    }

    public boolean continueExecuting() {
        return !this.leaper.onGround;
    }

    public void startExecuting() {
        double var1 = this.leapTarget.posX - this.leaper.posX;
        double var3 = this.leapTarget.posZ - this.leaper.posZ;
        float var5 = MathHelper.sqrt_double((double)(var1 * var1 + var3 * var3));
        this.leaper.motionX += var1 / (double)var5 * 0.5 * (double)0.8f + this.leaper.motionX * (double)0.2f;
        this.leaper.motionZ += var3 / (double)var5 * 0.5 * (double)0.8f + this.leaper.motionZ * (double)0.2f;
        this.leaper.motionY = this.leapMotionY;
    }
}

