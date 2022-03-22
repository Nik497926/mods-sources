/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.item.ItemMods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTeleportPearl
extends ItemMods {
    public ItemTeleportPearl(String name) {
        super(name);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.capabilities.isCreativeMode) {
            return stack;
        }
        --stack.stackSize;
        world.playSoundAtEntity(player, "random.bow", 0.5f, 0.4f / (Item.itemRand.nextFloat() * 0.4f + 0.8f));
        if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityTeleportPearl(world, player));
        }
        return stack;
    }
}

