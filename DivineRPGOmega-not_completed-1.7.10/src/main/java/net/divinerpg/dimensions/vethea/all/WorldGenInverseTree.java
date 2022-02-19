/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vethea.all;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenInverseTree
extends WorldGenerator {
    public WorldGenInverseTree(boolean par1) {
        super(par1);
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        int var6 = par2Random.nextInt(3) + 5;
        boolean var7 = true;
        if (par4 >= 1 && par4 + var6 + 1 <= 256) {
            int var11;
            int var10;
            for (int var8 = par4; var8 >= par4 + 1 - var6; --var8) {
                int var9 = 0;
                if (var8 == par4) {
                    var9 = 0;
                }
                if (var8 <= par4 - 1 - var6 + 2) {
                    var9 = 1;
                }
                for (var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10) {
                    for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11) {
                        if (var8 >= 0 && var8 < 256) {
                            Block b;
                            Block block = b = par1World.getBlock(var10, var8, var11);
                            if (b == null || block == null || block.isLeaves((IBlockAccess)par1World, var10, var8, var11)) continue;
                            var7 = false;
                            continue;
                        }
                        var7 = false;
                    }
                }
            }
            if (!var7) {
                return false;
            }
            Block b = par1World.getBlock(par3, par4 - 1, par5);
            if ((b == Blocks.stone || b == Blocks.dirt) && par4 < 256) {
                int var16;
                par1World.setBlock(par3, par4 + 1, par5, Blocks.dirt);
                for (var16 = par4 + 3 - var6; var16 >= par4 - var6; --var16) {
                    var10 = var16 - (par4 + var6);
                    var11 = 1 - var10 / 2;
                    for (int var12 = par3 - var11; var12 <= par3 + var11; ++var12) {
                        int var13 = var12 - par3;
                        for (int var14 = par5 - var11; var14 <= par5 + var11; ++var14) {
                            int var15 = var14 - par5;
                            Block block = par1World.getBlock(var12, var16, var14);
                            if (Math.abs(var13) == var11 && Math.abs(var15) == var11 && (par2Random.nextInt(2) == 0 || var10 == 0) || block != null && !block.canBeReplacedByLeaves((IBlockAccess)par1World, var12, var16, var14)) continue;
                            par1World.setBlock(var12, var16, var14, (Block)Blocks.leaves);
                        }
                    }
                }
                for (var16 = 0; var16 < var6; ++var16) {
                    Block bl;
                    Block block = bl = par1World.getBlock(par3, par4 + var16, par5);
                    if (bl != null && block != null && !block.isLeaves((IBlockAccess)par1World, par3, par4 + var16, par5)) continue;
                    par1World.setBlock(par3, par4 - var16, par5, Blocks.log);
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

