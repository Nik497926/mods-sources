/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import net.divinerpg.blocks.vethea.BlockNightmareBed;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemNightmareBed
extends ItemMod {
    public ItemNightmareBed() {
        super("nightmareBed", "bed", DivineRPGTabs.vethea);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (par3World.isRemote) {
            return true;
        }
        if (par7 != 1) {
            return false;
        }
        ++y;
        BlockNightmareBed bed = (BlockNightmareBed)VetheaBlocks.nightmareBedBlock;
        int i1 = MathHelper.floor_double((double)((double)(par2EntityPlayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        int b0 = 0;
        int b1 = 0;
        if (i1 == 0) {
            b1 = 1;
        }
        if (i1 == 1) {
            b0 = -1;
        }
        if (i1 == 2) {
            b1 = -1;
        }
        if (i1 == 3) {
            b0 = 1;
        }
        if (par2EntityPlayer.canPlayerEdit(x, y, z, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(x + b0, y, z + b1, par7, par1ItemStack)) {
            if (par3World.isAirBlock(x, y, z) && par3World.isAirBlock(x + b0, y, z + b1) && World.doesBlockHaveSolidTopSurface((IBlockAccess)par3World, (int)x, (int)(y - 1), (int)z) && World.doesBlockHaveSolidTopSurface((IBlockAccess)par3World, (int)(x + b0), (int)(y - 1), (int)(z + b1))) {
                par3World.setBlock(x, y, z, (Block)bed, i1, 3);
                if (par3World.getBlock(x, y, z) == bed) {
                    par3World.setBlock(x + b0, y, z + b1, (Block)bed, i1 + 8, 3);
                }
                --par1ItemStack.stackSize;
                return true;
            }
            return false;
        }
        return false;
    }
}

