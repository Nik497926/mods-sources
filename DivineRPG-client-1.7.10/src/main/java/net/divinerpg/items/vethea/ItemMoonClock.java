/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import net.divinerpg.entities.vethea.EntityLadyLuna;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMoonClock
extends ItemMod {
    public ItemMoonClock() {
        super("moonClock", DivineRPGTabs.spawner);
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && world.getBlock(x, y, z) == VetheaBlocks.lunicAltar && world.getBlock(x, y + 1, z) == Blocks.air && world.getBlock(x, y + 2, z) == Blocks.air && player.canPlayerEdit(x, y + 1, z, side, stack)) {
            EntityLadyLuna entity = new EntityLadyLuna(world);
            entity.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
            world.spawnEntityInWorld((Entity)entity);
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        }
        return false;
    }
}

