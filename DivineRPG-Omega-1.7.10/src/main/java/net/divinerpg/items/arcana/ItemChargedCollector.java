/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import net.divinerpg.entities.arcana.EntityConstructor;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChargedCollector
extends ItemMod {
    public ItemChargedCollector(String name) {
        super(name);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        EntityConstructor con = new EntityConstructor(world);
        Block block = world.getBlock(x, y, z);
        if (!world.isRemote && block == ArcanaBlocks.dramixAltar) {
            con.setLocationAndAngles((float)x + 0.5f, y + 1, (float)z + 0.5f, 0.0f, 0.0f);
            if (world.getCollidingBoundingBoxes((Entity)con, con.boundingBox).isEmpty()) {
                world.spawnEntityInWorld((Entity)con);
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
            }
            return true;
        }
        return false;
    }

    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}

