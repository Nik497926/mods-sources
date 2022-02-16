/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.boil;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAIFlying
extends EntityMob {
    public EntityAIFlying(World par1World) {
        super(par1World);
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public void moveEntityWithHeading(float par1, float par2) {
        if (this.isInWater()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)0.8f;
            this.motionY *= (double)0.8f;
            this.motionZ *= (double)0.8f;
        } else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
        } else {
            float d1 = 0.91f;
            if (this.onGround) {
                d1 = this.worldObj.getBlock((int)MathHelper.floor_double((double)this.posX), (int)(MathHelper.floor_double((double)this.boundingBox.minY) - 1), (int)MathHelper.floor_double((double)this.posZ)).slipperiness * 0.91f;
            }
            float f3 = 0.16277136f / (d1 * d1 * d1);
            this.moveFlying(par1, par2, this.onGround ? 0.1f * f3 : 0.02f);
            d1 = 0.91f;
            if (this.onGround) {
                d1 = this.worldObj.getBlock((int)MathHelper.floor_double((double)this.posX), (int)(MathHelper.floor_double((double)this.boundingBox.minY) - 1), (int)MathHelper.floor_double((double)this.posZ)).slipperiness * 0.91f;
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)d1;
            this.motionY *= (double)d1;
            this.motionZ *= (double)d1;
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d11 = this.posX - this.prevPosX;
        double d2 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double((double)(d11 * d11 + d2 * d2)) * 4.0f;
        if (f4 > 1.0f) {
            f4 = 1.0f;
        }
        this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4f;
        this.limbSwing += this.limbSwingAmount;
    }

    public boolean isOnLadder() {
        return false;
    }
}

