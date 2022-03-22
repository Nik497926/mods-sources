/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileElfPool;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockPool;

public class BlockElfPool
extends BlockModContainer
implements IWandHUD,
IWandable,
ILexiconable,
ICraftAchievement {
    boolean lastFragile = false;
    public static IIcon manaIcon;

    public BlockElfPool() {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setResistance(10.0f);
        this.setStepSound(soundTypeStone);
        this.setBlockName("elfpool");
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        manaIcon = IconHelper.forName(par1IconRegister, "manaWater");
    }

    @Override
    public Block setBlockName(String par1Str) {
        GameRegistry.registerBlock(this, ItemBlockPool.class, par1Str);
        return super.setBlockName(par1Str);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileElfPool();
    }

    public int damageDropped(int meta) {
        return meta;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileElfPool pool = (TileElfPool)par1World.getTileEntity(par2, par3, par4);
        this.lastFragile = pool.fragile;
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if (!this.lastFragile) {
            drops.add(new ItemStack(this, 1, metadata));
        }
        return drops;
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB par1, List list, Entity entity) {
        float f = 0.0625f;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, par1, list, entity);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, f, 0.5f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, par1, list, entity);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, f);
        super.addCollisionBoxesToList(world, x, y, z, par1, list, entity);
        this.setBlockBounds(1.0f - f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, par1, list, entity);
        this.setBlockBounds(0.0f, 0.0f, 1.0f - f, 1.0f, 0.5f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, par1, list, entity);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.DOWN;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public IIcon getIcon(int par1, int par2) {
        return ModBlocks.livingrock.getIcon(par1, 0);
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
        TileElfPool pool = (TileElfPool)par1World.getTileEntity(par2, par3, par4);
        int val = (int)((double)pool.getCurrentMana() / (double)pool.manaCap * 15.0);
        if (pool.getCurrentMana() > 0) {
            val = Math.max(val, 1);
        }
        return val;
    }

    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        ((TileElfPool)world.getTileEntity(x, y, z)).onWanded(player, stack);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {

    }

    public LexiconEntry getEntry(World world, int i, int i1, int i2, EntityPlayer entityPlayer, ItemStack itemStack) {
        return null;
    }

    public Achievement getAchievementOnCraft(ItemStack itemStack, EntityPlayer entityPlayer, IInventory iInventory) {
        return null;
    }
}

