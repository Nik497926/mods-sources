/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.boil.projectile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.entities.fx.EntityHellstoneFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMagmaFireball
extends EntitySmallFireball {
    float damage;

    public EntityMagmaFireball(World w) {
        super(w);
    }

    public EntityMagmaFireball(World w, EntityLivingBase e, double x, double y, double z) {
        super(w, e, x, y, z);
        this.setSize(0.3125f, 0.3125f);
        this.damage = 60.0f;
    }

    public EntityMagmaFireball(World w, EntityLivingBase e, double x, double y, double z, float damage) {
        super(w, e, x, y, z);
        this.setSize(0.3125f, 0.3125f);
        this.damage = damage;
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
    }
}

