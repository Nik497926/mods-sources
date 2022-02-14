/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.Sound;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemModRanged
extends ItemMod {
    protected int weaponDamage;
    protected Sound sound;
    protected Class<? extends EntityThrowable> clazz;

    public ItemModRanged(String name, int maxDamage, Sound sound, Class<? extends EntityThrowable> clazz) {
        super(name, DivineRPGTabs.ranged);
        this.clazz = clazz;
        this.setMaxStackSize(1);
        this.setMaxDamage(maxDamage);
        this.sound = sound;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && this.additionalRightClickChecks(player)) {
            if (this.sound != null) {
                world.playSoundAtEntity((Entity)player, this.sound.getPrefixedName(), 1.0f, 1.0f);
            }
            try {
                world.spawnEntityInWorld((Entity)this.clazz.getConstructor(World.class, EntityPlayer.class).newInstance(world, player));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stack;
    }

    protected boolean additionalRightClickChecks(EntityPlayer player) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        this.addAdditionalInformation(stack, player, list, par4);
        if (this.getMaxDamage() == -1) {
            list.add(TooltipLocalizer.infiniteUses());
        }
    }

    protected void addAdditionalInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
    }
}

