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
package net.divinerpg.items.iceika;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.iceika.projectile.EntityCarol;
import net.divinerpg.entities.iceika.projectile.EntityMusic;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMusicShooter
extends ItemMod {
    public ItemMusicShooter(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.setMaxStackSize(1);
        this.setMaxDamage(4000);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (this == IceikaItems.soundOfCarols) {
                world.spawnEntityInWorld((Entity)new EntityCarol(world, (EntityLivingBase)player));
                world.playSoundAtEntity((Entity)player, Sounds.soundOfCarols.getPrefixedName(), 1.0f, 1.0f);
            } else {
                world.spawnEntityInWorld((Entity)new EntityMusic(world, (EntityLivingBase)player));
                world.playSoundAtEntity((Entity)player, Sounds.soundOfMusic.getPrefixedName(), 1.0f, 1.0f);
            }
            stack.damageItem(1, (EntityLivingBase)player);
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (this == IceikaItems.soundOfCarols) {
            list.add(TooltipLocalizer.rangedDam(16.0));
        } else {
            list.add(TooltipLocalizer.rangedDam(10.0));
        }
        list.add(TooltipLocalizer.usesRemaining(stack.getMaxDamage() - stack.getItemDamage()));
    }
}

