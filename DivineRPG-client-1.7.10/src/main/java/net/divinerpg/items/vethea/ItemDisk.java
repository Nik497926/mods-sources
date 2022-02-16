/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import net.divinerpg.entities.vethea.projectile.EntityAmthirmisDisk;
import net.divinerpg.entities.vethea.projectile.EntityArksianeDisk;
import net.divinerpg.entities.vethea.projectile.EntityCermileDisk;
import net.divinerpg.entities.vethea.projectile.EntityDarvenDisk;
import net.divinerpg.entities.vethea.projectile.EntityHeliosisDisk;
import net.divinerpg.entities.vethea.projectile.EntityKarosDisk;
import net.divinerpg.entities.vethea.projectile.EntityPardimalDisk;
import net.divinerpg.entities.vethea.projectile.EntityQuadroticDisk;
import net.divinerpg.entities.vethea.projectile.EntityTeakerDisk;
import net.divinerpg.items.vethea.ItemVetheanDisk;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDisk
extends ItemVetheanDisk {
    private int dam;

    public ItemDisk(int par1, String name) {
        super(par1, name);
        this.dam = par1;
    }

    @Override
    public void shoot(ItemStack var1, World var2, EntityPlayer var3) {
        if (var1.getItem() == VetheaItems.teakerDisk) {
            var2.spawnEntityInWorld((Entity)new EntityTeakerDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.amthrimisDisk) {
            var2.spawnEntityInWorld((Entity)new EntityAmthirmisDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.darvenDisk) {
            var2.spawnEntityInWorld((Entity)new EntityDarvenDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.cermileDisk) {
            var2.spawnEntityInWorld((Entity)new EntityCermileDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.pardimalDisk) {
            var2.spawnEntityInWorld((Entity)new EntityPardimalDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.quadroticDisk) {
            var2.spawnEntityInWorld((Entity)new EntityQuadroticDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.karosDisk) {
            var2.spawnEntityInWorld((Entity)new EntityKarosDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.heliosisDisk) {
            var2.spawnEntityInWorld((Entity)new EntityHeliosisDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
        if (var1.getItem() == VetheaItems.arksianeDisk) {
            var2.spawnEntityInWorld((Entity)new EntityArksianeDisk(var2, (EntityLivingBase)var3, this.dam, this));
        }
    }
}

