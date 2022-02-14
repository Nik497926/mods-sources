/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package net.divinerpg.blocks.vanilla;

import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class VanillaBlock
extends BlockMod {
    protected Random rand = new Random();

    public VanillaBlock(EnumBlockType type, String name, float hardness, int harvest) {
        super(type, name, hardness, DivineRPGTabs.blocks);
        this.setHarvestLevel("pickaxe", harvest);
    }

    public static Item getBlock(Block b) {
        return Item.getItemFromBlock((Block)b);
    }

    public int quantityDroppedWithBonus(int par1, Random par2) {
        if (par1 > 0 && VanillaBlock.getBlock(this) != this.getItemDropped(0, par2, par1)) {
            int j = par2.nextInt(par1 + 2) - 1;
            if (j < 0) {
                j = 0;
            }
            return this.quantityDropped(par2) * (j + 1);
        }
        return this.quantityDropped(par2);
    }

    @Override
    public int getExpDrop(IBlockAccess block, int par5, int par7) {
        if (this.getItemDropped(par5, this.rand, par7) != VanillaBlock.getBlock(this)) {
            int j1 = 0;
            if (this == VanillaBlocks.bloodgemOre) {
                j1 = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)0, (int)4);
            }
            return j1;
        }
        return 0;
    }
}

