/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityReflector
extends EntityThrowable {
    int age;

    public EntityReflector(World w) {
        super(w);
    }

    public EntityReflector(World w, EntityLivingBase e) {
        super(w, e);
        this.motionX *= 3.0;
        this.motionY *= 3.0;
        this.motionZ *= 3.0;
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (pos.entityHit != null && this.getThrower() != null) {
            double xDist = (pos.entityHit.posX - this.getThrower().posX) / 5.0;
            double yDist = (pos.entityHit.posY - this.getThrower().posY) / 5.0;
            double zDist = (pos.entityHit.posZ - this.getThrower().posZ) / 5.0;
            pos.entityHit.addVelocity(xDist, yDist, zDist);
        }
        this.setDead();
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    public void onUpdate() {
        super.onUpdate();
        ++this.age;
        if (this.age > 18) {
            this.setDead();
        }
    }
}

