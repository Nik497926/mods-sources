/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAcid
extends BlockMod {
    private boolean decays;
    private boolean poison;

    public BlockAcid(String name, boolean decays) {
        this(name, decays, false);
    }

    public BlockAcid(String name, boolean decays, boolean poison) {
        super(EnumBlockType.SNOW, name, 0.1f, DivineRPGTabs.vethea);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.setTickRandomly(true);
        this.decays = decays;
        this.poison = poison;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        int var5 = par1World.getBlockMetadata(par2, par3, par4) & 7;
        return var5 >= 3 ? AxisAlignedBB.getBoundingBox((double)((double)par2 + this.minX), (double)((double)par3 + this.minY), (double)((double)par4 + this.minZ), (double)((double)par2 + this.maxX), (double)((float)par3 + 0.5f), (double)((double)par4 + this.maxZ)) : null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;
        float var6 = (float)(2 * (1 + var5)) / 16.0f;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, var6, 1.0f);
    }

    public boolean canPlaceBlockAt(World world, int par2, int par3, int par4) {
        Block block = world.getBlock(par2, par3 - 1, par4);
        return block != Blocks.ice && block != Blocks.packed_ice ? (block.isLeaves((IBlockAccess)world, par2, par3 - 1, par4) ? true : (block == this && (world.getBlockMetadata(par2, par2 - 1, par3) & 7) == 7 ? true : block.isOpaqueCube() && block.getMaterial().blocksMovement())) : false;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        this.canSnowStay(par1World, par2, par3, par4);
    }

    private boolean canSnowStay(World par1World, int par2, int par3, int par4) {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4)) {
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        return true;
    }

    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        par1World.setBlockToAir(par3, par4, par5);
    }

    public int tickRate(World par1World) {
        return 160;
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (this.decays) {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e) {
        if (e instanceof EntityPlayer) {
            e.attackEntityFrom(Util.acidSource, 3.0f);
            if (this.poison) {
                ((EntityPlayer)e).addPotionEffect(new PotionEffect(Potion.poison.id, 150, 1, true));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par5 == 1 ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}

