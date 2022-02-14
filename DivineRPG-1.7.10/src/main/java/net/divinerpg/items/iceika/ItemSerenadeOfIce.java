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
package net.divinerpg.items.iceika;

import java.util.List;
import net.divinerpg.entities.iceika.projectile.EntitySerenadeOfIce;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSerenadeOfIce
extends ItemMod {
    public ItemSerenadeOfIce(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.utility);
        this.setMaxDamage(100);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            world.spawnEntityInWorld((Entity)new EntitySerenadeOfIce(world, (EntityLivingBase)player));
            world.playSoundAtEntity((Entity)player, Sounds.serenade.getPrefixedName(), 1.0f, 1.0f);
            stack.damageItem(1, (EntityLivingBase)player);
        }
        return stack;
    }

    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + "Slows mobs for 5 seconds");
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

