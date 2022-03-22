/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary;

import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class ItemHermesWand
extends ItemRelicAdv
implements IManaUsingItem {
    public ItemHermesWand(String name) {
        super(name);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            EntityTeleportPearl p = new EntityTeleportPearl(world, player);
            world.spawnEntityInWorld(p);
            ManaItemHandler.requestManaExact(stack, player, 100, true);
        }
        return stack;
    }

    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

