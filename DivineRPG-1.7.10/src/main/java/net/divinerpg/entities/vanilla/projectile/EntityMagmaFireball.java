/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.fx.EntityHellstoneFX;
import net.divinerpg.entities.vanilla.EntityEnderBlaze;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMagmaFireball
extends EntitySmallFireball {
    float damage;
    private EntityEnderBlaze e;

    public EntityMagmaFireball(World w) {
        super(w);
    }

    public EntityMagmaFireball(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125f, 0.3125f);
        this.damage = 60.0f;
    }

    public EntityMagmaFireball(World w, EntityLivingBase e, double x, double y, double z, float damage, EntityEnderBlaze entityEnder) {
        super(w, e, x, y, z);
        this.setSize(0.3125f, 0.3125f);
        this.damage = damage;
        e = entityEnder;
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int i = 0; i < 6; ++i) {
            EntityHellstoneFX effect = new EntityHellstoneFX(this.worldObj, this.posX, this.posY - 1.0, this.posZ, 0.0, 0.0, 0.0);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)effect);
        }
    }

    protected void onImpact(MovingObjectPosition onImpact) {
        if (onImpact.entityHit != null) {
            onImpact.entityHit.attackEntityFrom(DamageSource.causeFireballDamage((EntityFireball)this, (Entity)this.shootingEntity), this.damage);
            onImpact.entityHit.hurtResistantTime = 4;
        }
        this.setDead();
    }
}

