/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.item.ModItems;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class ItemDungeonBox
extends ItemMods {
    Random rand = new Random();

    public ItemDungeonBox(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ItemStack s = ChestGenHooks.getOneItem((String)"dungeonChest", (Random)player.worldObj.rand);
        if (!player.worldObj.isRemote) {
            if (player.inventory.hasItem(ModItems.key)) {
                player.inventory.consumeInventoryItem(ModItems.key);
                int a = player.worldObj.rand.nextInt(2) + 1;
                do {
                    ItemStack s1 = ChestGenHooks.getOneItem((String)"dungeonChest", (Random)player.worldObj.rand);
                    player.inventory.addItemStackToInventory(s1);
                } while (--a != 0);
                return s.copy();
            }
            if (!player.inventory.hasItem(ModItems.key)) {
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.openChest", new Object[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
            }
        }
        return stack;
    }
}

