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
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.divinerpg.entities.fx.EntityColoredFX;
import net.divinerpg.entities.twilight.EntitySoulSpider;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySoulFiendProjectile
extends EntityThrowable {
    public EntitySoulFiendProjectile(World w) {
        super(w);
    }

    public EntitySoulFiendProjectile(World w, EntityLivingBase shooter) {
        super(w, shooter);
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (pos.entityHit == null) {
            for (int i = 0; i < 3; ++i) {
                EntitySoulSpider e = new EntitySoulSpider(this.worldObj);
                e.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0f, 0.0f);
                if (this.worldObj.isRemote) continue;
                this.worldObj.spawnEntityInWorld((Entity)e);
            }
            this.setDead();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        super.onUpdate();
        for (int var3 = 0; var3 < 8; ++var3) {
            EntityColoredFX e = new EntityColoredFX(this.worldObj, this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, 0.0, 0.0, 0.0, new Color(0, 0, 0));
            e.bigger = true;
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)e);
            EntityColoredFX e2 = new EntityColoredFX(this.worldObj, this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, 0.0, 0.0, 0.0, new Color(255, 0, 0));
            e2.bigger = true;
            e2.shortLived = true;
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)e2);
        }
    }
}

