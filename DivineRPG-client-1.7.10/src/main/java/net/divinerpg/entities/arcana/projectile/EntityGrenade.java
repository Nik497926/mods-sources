/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntitySnowball
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenade
extends EntitySnowball {
    public EntityGrenade(World var1) {
        super(var1);
    }

    public EntityGrenade(World var1, EntityPlayer par3EntityPlayer) {
        super(var1, (EntityLivingBase)par3EntityPlayer);
    }

    public EntityGrenade(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
            this.setDead();
        }
    }
}

