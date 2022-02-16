/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityVileStorm
extends EntityThrowable {
    public EntityVileStorm(World var1) {
        super(var1);
    }

    public EntityVileStorm(World var1, EntityLivingBase var3) {
        super(var1, var3);
    }

    protected void onImpact(MovingObjectPosition var1) {
        if (var1.entityHit != null) {
            int var2 = 4;
            if (var1.entityHit instanceof EntityLiving) {
                var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), (float)var2);
                ((EntityLivingBase)var1.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 45, 3));
            }
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

