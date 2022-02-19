/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockHeatTrap
extends BlockMod {
    public BlockHeatTrap(String name, float hardness) {
        super(name, hardness);
    }

    public int tickRate(World par1) {
        return 30;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        float var5 = 0.0625f;
        return AxisAlignedBB.getBoundingBox((double)((float)var2 + var5), (double)var3, (double)((float)var4 + var5), (double)((float)(var2 + 1) - var5), (double)((float)(var3 + 1) - var5), (double)((float)(var4 + 1) - var5));
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        float var5 = 0.0625f;
        return AxisAlignedBB.getBoundingBox((double)((float)var2 + var5), (double)var3, (double)((float)var4 + var5), (double)((float)(var2 + 1) - var5), (double)(var3 + 1), (double)((float)(var4 + 1) - var5));
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (this == ArcanaBlocks.heatTrapOn) {
            par1World.setBlock(par2, par3, par4, ArcanaBlocks.heatTrap);
        }
    }

    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
        if (this == ArcanaBlocks.heatTrap) {
            var1.setBlock(var2, var3, var4, ArcanaBlocks.heatTrapOn);
        }
        if (this == ArcanaBlocks.heatTrapOn && var5 instanceof EntityPlayerMP) {
            var5.attackEntityFrom(Util.trapSource, 16.0f);
            var5.setFire(15);
            ((EntityPlayerMP)var5).addStat((StatBase)DivineRPGAchievements.hoterThanHell, 1);
        }
    }
}

