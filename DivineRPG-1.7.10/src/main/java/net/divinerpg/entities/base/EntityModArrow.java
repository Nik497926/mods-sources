/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityModArrow
extends EntityArrow {
    private double damage;
    private Item pickedUp;

    public EntityModArrow(World par1World, double dam, Item pick) {
        super(par1World);
        this.damage = dam;
        this.pickedUp = pick;
        this.setDamage(this.damage);
    }

    public EntityModArrow(World par1World, double par2, double par4, double par6, double dam, Item pick) {
        super(par1World, par2, par4, par6);
        this.damage = dam;
        this.pickedUp = pick;
        this.setDamage(this.damage);
    }

    public EntityModArrow(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5, double dam, Item pick) {
        super(par1World, par2EntityLivingBase, par3EntityLivingBase, par4, par5);
        this.damage = dam;
        this.pickedUp = pick;
    }

    public EntityModArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3, double dam, Item pick) {
        super(par1World, par2EntityLivingBase, par3);
        this.damage = dam;
        this.pickedUp = pick;
        this.setDamage(this.damage);
    }

    public double getDamage() {
        return this.damage;
    }
}

