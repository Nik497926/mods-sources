/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.CustomAI;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlyingMob
extends EntityMob {
    public EntityFlyingMob(World par1World) {
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
            this.motionX *= 0.8f;
            this.motionY *= 0.8f;
            this.motionZ *= 0.8f;
        } else if (this.handleLavaMovement()) {
            this.moveFlying(par1, par2, 0.02f);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5;
            this.motionY *= 0.5;
            this.motionZ *= 0.5;
        } else {
            float d0 = 0.91f;
            if (this.onGround) {
                d0 = 0.54600006f;
                Block f3 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
                if (f3 != Blocks.air) {
                    d0 = f3.slipperiness * 0.91f;
                }
            }
            float f31 = 0.16277136f / (d0 * d0 * d0);
            this.moveFlying(par1, par2, this.onGround ? 0.1f * f31 : 0.02f);
            d0 = 0.91f;
            if (this.onGround) {
                d0 = 0.54600006f;
                Block d1 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));
                if (d1 != Blocks.air) {
                    d0 = d1.slipperiness * 0.91f;
                }
            }
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= d0;
            this.motionY *= d0;
            this.motionZ *= d0;
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d01 = this.posX - this.prevPosX;
        double d11 = this.posZ - this.prevPosZ;
        float f4 = MathHelper.sqrt_double(d01 * d01 + d11 * d11) * 4.0f;
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

