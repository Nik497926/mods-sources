/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityKingOfScorchersMeteor
extends EntityThrowable {
    public EntityKingOfScorchersMeteor(World par1World) {
        super(par1World);
    }

    public EntityKingOfScorchersMeteor(World par1World, EntityLivingBase e) {
        super(par1World, e);
    }

    public EntityKingOfScorchersMeteor(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            List l;
            if (this.ticksExisted > 200) {
                this.setDead();
            }
            if ((l = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox)).size() > 0) {
                this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 3.0f, false);
                this.setDead();
            }
        }
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (!this.worldObj.isRemote) {
            if (pos.entityHit != null) {
                this.worldObj.createExplosion(pos.entityHit, this.posX, this.posY, this.posZ, 3.0f, false);
                this.setDead();
            } else {
                this.motionZ = 0.0;
                this.motionY = 0.0;
                this.motionX = 0.0;
            }
        }
    }
}

