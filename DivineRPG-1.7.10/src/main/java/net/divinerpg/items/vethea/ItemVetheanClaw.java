/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import net.divinerpg.items.vethea.ItemVetheanSword;
import net.divinerpg.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVetheanClaw
extends ItemVetheanSword {
    public ItemVetheanClaw(int damage, String name) {
        super(name, Util.addHammerMaterial(damage));
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.none;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }
}

