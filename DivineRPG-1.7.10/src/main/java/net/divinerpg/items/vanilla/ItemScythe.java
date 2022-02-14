/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.entities.vanilla.projectile.EntityShooterBullet;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemScythe
extends ItemMod {
    public ItemScythe() {
        super("scythe");
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            world.playSoundAtEntity((Entity)player, Sounds.deepLaugh.getPrefixedName(), 1.0f, 1.0f);
            ItemStack boots = player.inventory.armorItemInSlot(0);
            ItemStack legs = player.inventory.armorItemInSlot(1);
            ItemStack body = player.inventory.armorItemInSlot(2);
            ItemStack helmet = player.inventory.armorItemInSlot(3);
            int damage = 6;
            if (boots != null && legs != null && body != null && helmet != null && boots.getItem() == VanillaItemsArmor.jackOManBoots && body.getItem() == VanillaItemsArmor.jackOManBody && legs.getItem() == VanillaItemsArmor.jackOManLegs && helmet.getItem() == VanillaItemsArmor.jackOManHelmet) {
                damage = 18;
            }
            EntityShooterBullet entity = new EntityShooterBullet(world, (EntityLivingBase)player, (float)damage, EntityResourceLocation.scytheProjectile.toString());
            world.spawnEntityInWorld((Entity)entity);
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedDam(6.0));
        list.add(TooltipLocalizer.infiniteAmmo());
        list.add(TooltipLocalizer.infiniteUses());
    }
}

