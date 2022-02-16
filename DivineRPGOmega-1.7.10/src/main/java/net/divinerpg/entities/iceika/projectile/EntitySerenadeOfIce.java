/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.iceika.projectile;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySerenadeOfIce
extends EntityThrowable {
    int age;

    public EntitySerenadeOfIce(World var1) {
        super(var1);
    }

    public EntitySerenadeOfIce(World var1, EntityLivingBase var2) {
        super(var1, var2);
        this.motionX *= 3.0;
        this.motionY *= 3.0;
        this.motionZ *= 3.0;
    }

    public EntitySerenadeOfIce(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    public void onUpdate() {
        super.onUpdate();
        ++this.age;
        if (!this.worldObj.isRemote && this.age > 35) {
            this.setDead();
        }
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (pos.entityHit != null) {
            List entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, pos.entityHit.boundingBox.expand(3.0, 3.0, 3.0));
            for (EntityLivingBase e : entities) {
                if (e == this.getThrower()) continue;
                e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 3, true));
            }
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

