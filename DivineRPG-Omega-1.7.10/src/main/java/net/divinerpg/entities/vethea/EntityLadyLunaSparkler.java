/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import java.awt.Color;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.divinerpg.utils.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLadyLunaSparkler
extends EntityParticleBullet {
    public EntityLadyLunaSparkler(World world) {
        super(world);
        this.setMoreParticles();
        this.setSize(0.7f, 0.7f);
    }

    public EntityLadyLunaSparkler(World world, EntityLivingBase e) {
        super(world, e, 12.0f, EntityResourceLocation.blank.toString(), new Color(139, 103, 255));
        this.setMoreParticles();
        this.motionZ = 0.0;
        this.motionY = 0.0;
        this.motionX = 0.0;
        this.setSize(0.7f, 0.7f);
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.getThrower() != null && this.getThrower() instanceof EntityLiving && ((EntityLiving)this.getThrower()).getAttackTarget() != null) {
            double tx = ((EntityLiving)this.getThrower()).getAttackTarget().posX - this.getThrower().posX;
            double ty = ((EntityLiving)this.getThrower()).getAttackTarget().boundingBox.minY - this.getThrower().posY;
            double tz = ((EntityLiving)this.getThrower()).getAttackTarget().posZ - this.getThrower().posZ;
            if (!this.worldObj.isRemote && this.ticksExisted > 30) {
                this.setThrowableHeading(tx, ty, tz, 0.5f, 0.0f);
            }
        }
        if (!this.worldObj.isRemote && this.ticksExisted > 80) {
            this.setDead();
        }
    }

    @Override
    public void onImpact(MovingObjectPosition pos) {
        if (pos.entityHit != null) {
            pos.entityHit.attackEntityFrom(Util.causeArcanaDamage((Entity)this, (Entity)this.getThrower()), this.damage);
        }
    }
}

