/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.iceika;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSnowGlobe
extends ItemMod {
    public ItemSnowGlobe(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.utility);
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10) {
        Block b;
        if (par7 != 1) {
            return false;
        }
        if (p.canPlayerEdit(x, y, z, par7, i) && p.canPlayerEdit(x, y + 1, z, par7, i) && (b = w.getBlock(x, y, z)) == Blocks.snow) {
            w.setBlock(x, y + 1, z, IceikaBlocks.iceikaFire);
            return true;
        }
        return false;
    }
}

