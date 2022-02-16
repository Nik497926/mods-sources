/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAIFlyingNew
extends EntityMob {
    public EntityAIFlyingNew(World par1World) {
        super(par1World);
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public void moveEntityWithHeading(float par1, float par2) {
        if (this.isInWater()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(((Entity)this).motionX, ((Entity)this).motionY, ((Entity)this).motionZ);
            ((Entity)this).motionX *= (double)0.8f;
            ((Entity)this).motionY *= (double)0.8f;
            ((Entity)this).motionZ *= (double)0.8f;
        } else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(((Entity)this).motionX, ((Entity)this).motionY, ((Entity)this).motionZ);
            ((Entity)this).motionX *= 0.5;
            ((Entity)this).motionY *= 0.5;
            ((Entity)this).motionZ *= 0.5;
        } else {
            float f2 = 0.91f;
            if (((Entity)this).onGround) {
                f2 = ((Entity)this).worldObj.getBlock((int)MathHelper.floor_double((double)((Entity)this).posX), (int)(MathHelper.floor_double((double)((Entity)this).boundingBox.minY) - 1), (int)MathHelper.floor_double((double)((Entity)this).posZ)).slipperiness * 0.91f;
            }
            float f3 = 0.16277136f / f2 * f2 * f2;
            this.moveFlying(par1, par2, ((Entity)this).onGround ? 0.1f * f3 : 0.02f);
            f2 = 0.91f;
            if (((Entity)this).onGround) {
                f2 = ((Entity)this).worldObj.getBlock((int)MathHelper.floor_double((double)((Entity)this).posX), (int)(MathHelper.floor_double((double)((Entity)this).boundingBox.minY) - 1), (int)MathHelper.floor_double((double)((Entity)this).posZ)).slipperiness * 0.91f;
            }
            this.moveEntity(((Entity)this).motionX, ((Entity)this).motionY, ((Entity)this).motionZ);
            ((Entity)this).motionX *= (double)f2;
            ((Entity)this).motionY *= (double)f2;
            ((Entity)this).motionZ *= (double)f2;
        }
        ((EntityLivingBase)this).prevLimbSwingAmount = ((EntityLivingBase)this).limbSwingAmount;
        double d1 = ((Entity)this).posX - ((Entity)this).prevPosX;
        double d2 = ((Entity)this).posZ - ((Entity)this).prevPosZ;
        float f4 = MathHelper.sqrt_double((double)(d1 * d1 + d2 * d2)) * 4.0f;
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        ((EntityLivingBase)this).limbSwingAmount += (f4 - ((EntityLivingBase)this).limbSwingAmount) * 0.4f;
        ((EntityLivingBase)this).limbSwing += ((EntityLivingBase)this).limbSwingAmount;
    }

    public boolean isOnLadder() {
        return false;
    }
}

