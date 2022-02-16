/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight.projectile;

import java.awt.Color;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTwilightMageShot
extends EntityParticleBullet {
    public EntityTwilightMageShot(World world) {
        super(world);
        this.setMoreParticles();
    }

    public EntityTwilightMageShot(World world, EntityLivingBase e, int r, int g, int b) {
        super(world, e, (float)e.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue(), EntityResourceLocation.blank.toString(), new Color(r, g, b));
        this.setMoreParticles();
    }

    public float getGravityVelocity() {
        return 0.0f;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.ticksExisted > 20) {
            this.setDead();
        }
    }

    @Override
    public void onImpact(MovingObjectPosition pos) {
        super.onImpact(pos);
    }
}

