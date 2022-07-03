/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.common.block.ModBlocks;

public class ItemBox
extends ItemMods {
    public static ItemStack[] stacks;

    public ItemBox(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.worldObj.isRemote) {
            if (player.inventory.hasItemStack(new ItemStack(ModItems.key))) {
                stacks = !ExtraBotany.alfheimLoaded ? new ItemStack[]{new ItemStack(ModItems.material, 1, 0), new ItemStack(ModItems.material, 1, 2), new ItemStack(ModItems.material, 1, 9), new ItemStack(ModItems.material, 1, 12), new ItemStack(ModItems.castsoulsteel)} : new ItemStack[]{new ItemStack(ModItems.material, 1, 2), new ItemStack(ModItems.material, 1, 0), new ItemStack(ModItems.material, 1, 12), new ItemStack(ModItems.recordA), new ItemStack(ModItems.recordB), new ItemStack(ModItems.recordC), new ItemStack(ModItems.dog, 1, 0), new ItemStack(ModItems.dog, 1, 1), new ItemStack(ModItems.dog, 1, 2), new ItemStack(ModItems.dog, 1, 3), new ItemStack(ModBlocks.flower, 1, 0), new ItemStack(ModBlocks.flower, 1, 1), new ItemStack(ModBlocks.flower, 1, 2), new ItemStack(ModBlocks.flower, 1, 3), new ItemStack(ModBlocks.flower, 1, 4), new ItemStack(ModBlocks.flower, 1, 5), new ItemStack(ModBlocks.flower, 1, 6), new ItemStack(ModBlocks.flower, 1, 7), new ItemStack(ModBlocks.flower, 1, 8), new ItemStack(ModBlocks.flower, 1, 9), new ItemStack(ModBlocks.flower, 1, 10), new ItemStack(ModBlocks.flower, 1, 11), new ItemStack(ModBlocks.flower, 1, 12), new ItemStack(ModBlocks.flower, 1, 13), new ItemStack(ModBlocks.flower, 1, 14), new ItemStack(ModBlocks.flower, 1, 15), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 0), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 1), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 2), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 3), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 4), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 5), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 6), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 7), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 8), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 9), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 10), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 11), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 12), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 13), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 14), new ItemStack(vazkii.botania.common.item.ModItems.rune, 1, 15), new ItemStack(vazkii.botania.common.item.ModItems.cosmetic, 1, 0), new ItemStack(vazkii.botania.common.item.ModItems.cosmetic, 1, 1), new ItemStack(vazkii.botania.common.item.ModItems.cosmetic, 1, 2), new ItemStack(vazkii.botania.common.item.ModItems.cosmetic, 1, 3)};
                int i = world.rand.nextInt(stacks.length);
                world.playSoundAtEntity((Entity)player, "random.bow", 0.5f, 0.4f / (world.rand.nextFloat() * 0.4f + 0.8f));
                player.inventory.consumeInventoryItem(ModItems.key);
                return stacks[i].copy();
            }
            if (!player.inventory.hasItemStack(new ItemStack(ModItems.key))) {
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.openChest", new Object[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
            }
        }
        return stack;
    }
}

