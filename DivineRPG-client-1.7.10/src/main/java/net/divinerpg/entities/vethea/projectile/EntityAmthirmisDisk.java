/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea.projectile;

import net.divinerpg.entities.vethea.projectile.EntityDisk;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityAmthirmisDisk
extends EntityDisk {
    public EntityAmthirmisDisk(World par1World) {
        super(par1World);
    }

    public EntityAmthirmisDisk(World world, EntityLivingBase player, int damage, Item diskItem) {
        super(world, player, damage, diskItem);
    }

    public EntityAmthirmisDisk(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
}

