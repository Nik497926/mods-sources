/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import net.divinerpg.blocks.vanilla.IDivineMetaBlock;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class DivineMetaItemBlock
extends ItemBlock {
    public DivineMetaItemBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack stack) {
        String name = "tile." + ((IDivineMetaBlock)Block.getBlockFromItem((Item)stack.getItem())).getNames()[stack.getItemDamage()] + ((IDivineMetaBlock)Block.getBlockFromItem((Item)stack.getItem())).getSuffix();
        return name;
    }

    public int getMetadata(int par1) {
        return par1;
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (Block.getBlockFromItem((Item)stack.getItem()) == VanillaBlocks.lamp1 && stack.getItemDamage() == 7) {
            player.triggerAchievement((StatBase)DivineRPGAchievements.oneLampTwoLampRedLampBlueLamp);
        }
    }
}

