/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import java.awt.Color;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGeneralsStaff
extends EntityParticleBullet {
    public EntityGeneralsStaff(World world, EntityLivingBase entity) {
        super(world, entity, 18.0f, EntityResourceLocation.generalsStaff.toString(), new Color(31, 93, 210));
        this.setMoreParticles();
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        super.onImpact(position);
        if (!this.worldObj.isRemote) {
            for (double theta = 0.0; theta < Math.PI * 2; theta += 1.5707963267948966) {
                EntityParticleBullet e = new EntityParticleBullet(this.worldObj, this.posX, this.posY, this.posZ, 18.0f, this.getTextureName(), new Color(56, 152, 186)).setMoreParticles();
                e.setThrowableHeading(Math.cos(theta), 0.4, Math.sin(theta), 0.7f, 0.0f);
                this.worldObj.spawnEntityInWorld((Entity)e);
            }
            EntityParticleBullet e = new EntityParticleBullet(this.worldObj, this.posX, this.posY, this.posZ, 18.0f, this.getTextureName(), new Color(56, 152, 186)).setMoreParticles();
            e.setThrowableHeading(0.0, 1.0, 0.0, 0.7f, 0.0f);
            this.worldObj.spawnEntityInWorld((Entity)e);
        }
    }
}

