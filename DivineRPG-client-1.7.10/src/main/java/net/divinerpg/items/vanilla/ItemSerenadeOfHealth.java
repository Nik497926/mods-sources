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
package net.divinerpg.items.vanilla;

import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSerenadeOfHealth
extends ItemMod {
    public ItemSerenadeOfHealth(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.utility);
        this.setMaxDamage(7);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        float current = player.getHealth();
        if (current >= 0.0f && current < 20.0f) {
            player.setHealth(20.0f);
            stack.damageItem(1, (EntityLivingBase)player);
            world.playSoundAtEntity((Entity)player, Sounds.heal.getPrefixedName(), 1.0f, 1.0f);
        }
        return stack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.usesRemaining(stack.getMaxDamage() - stack.getItemDamage()));
    }
}

