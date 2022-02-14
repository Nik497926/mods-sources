/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemModDoor
extends ItemMod {
    private Block door;

    public ItemModDoor(Block block, String name) {
        super(name);
        this.door = block;
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setTextureName("divinerpg:" + name);
        this.setUnlocalizedName(name);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        }
        if (!par3World.isRemote) {
            Block block = this.door;
            int rotation = MathHelper.floor_double((double)((double)((par2EntityPlayer.rotationYaw + 180.0f) * 4.0f / 360.0f) - 0.5)) & 3;
            ItemDoor.placeDoorBlock((World)par3World, (int)par4, (int)(++par5), (int)par6, (int)rotation, (Block)block);
            --par1ItemStack.stackSize;
            return true;
        }
        return false;
    }
}

