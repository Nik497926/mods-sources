/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.divinerpg.entities.base.EntityHeatSeekingProjectile;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.fx.EntityColoredFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMandragoraProjectile
extends EntityHeatSeekingProjectile {
    private EntityLiving thrower;

    public EntityMandragoraProjectile(World w) {
        super(w);
        this.setPlayersOnly();
    }

    public EntityMandragoraProjectile(World w, EntityLiving e) {
        super(w, (EntityLivingBase)e);
        this.thrower = e;
        this.setPlayersOnly();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int var3 = 0; var3 < 8; ++var3) {
            EntityColoredFX e = new EntityColoredFX(this.worldObj, this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) / 6.0, this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) / 6.0, this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) / 6.0, 0.0, 0.0, 0.0, new Color(48, 179, 95));
            e.bigger = true;
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)e);
        }
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (pos.entityHit != null && pos.entityHit instanceof EntityPlayer) {
            pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.thrower), (float)EntityStats.mandragoraDamage);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}

