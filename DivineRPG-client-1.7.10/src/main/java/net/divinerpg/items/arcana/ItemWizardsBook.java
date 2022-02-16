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

import net.divinerpg.entities.arcana.EntityDramix;
import net.divinerpg.entities.arcana.EntityParasecta;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWizardsBook
extends ItemMod {
    public ItemWizardsBook(String name) {
        super(name, DivineRPGTabs.spawner);
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer p, World world, int x, int y, int z, int i, float f, float f1, float f2) {
        EntityParasecta parasecta = new EntityParasecta(world);
        EntityDramix dramix = new EntityDramix(world);
        Block block = world.getBlock(x, y, z);
        if (!world.isRemote) {
            if (block == ArcanaBlocks.parasectaAltar) {
                parasecta.setLocationAndAngles((float)x + 0.5f, y + 1, (float)z + 0.5f, 0.0f, 0.0f);
                if (world.getCollidingBoundingBoxes((Entity)parasecta, parasecta.boundingBox).isEmpty()) {
                    world.spawnEntityInWorld((Entity)parasecta);
                    if (!p.capabilities.isCreativeMode) {
                        --stack.stackSize;
                    }
                }
                return true;
            }
            if (block == ArcanaBlocks.dramixAltar) {
                dramix.setLocationAndAngles((float)x + 0.5f, y + 1, (float)z + 0.5f, 0.0f, 0.0f);
                if (world.getCollidingBoundingBoxes((Entity)dramix, dramix.boundingBox).isEmpty()) {
                    world.spawnEntityInWorld((Entity)dramix);
                    if (!p.capabilities.isCreativeMode) {
                        --stack.stackSize;
                    }
                }
                return true;
            }
        }
        return false;
    }
}

