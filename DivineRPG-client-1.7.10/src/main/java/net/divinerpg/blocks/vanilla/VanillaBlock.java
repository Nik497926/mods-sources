/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VanillaBlock
extends BlockMod {
    private Random rand = new Random();

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

    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e) {
        if (!(e instanceof EntityItem) && w.getBlock(x, y, z) == VanillaBlocks.netheriteOre) {
            e.attackEntityFrom(DamageSource.onFire, 1.0f);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.0625f;
        return this == VanillaBlocks.netheriteOre ? AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f)) : super.getCollisionBoundingBoxFromPool(world, x, y, z);
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

