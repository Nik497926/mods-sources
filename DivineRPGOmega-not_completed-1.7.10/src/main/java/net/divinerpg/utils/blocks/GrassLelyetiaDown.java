/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package net.divinerpg.utils.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class GrassLelyetiaDown
extends Block
implements IGrowable {
    private static Block.SoundType grass = Block.soundTypeGrass;
    @SideOnly(value=Side.CLIENT)
    private IIcon top;
    @SideOnly(value=Side.CLIENT)
    private IIcon side;
    @SideOnly(value=Side.CLIENT)
    private IIcon bottom;

    public GrassLelyetiaDown(Material p_i45394_1_) {
        super(p_i45394_1_);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setHardness(1.5f);
        this.setResistance(0.2f);
        this.setStepSound(grass);
        this.setTickRandomly(true);
        this.setHarvestLevel("shovel", 1);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int intSide, int meta) {
        return intSide == 1 ? this.top : (intSide == 0 ? this.bottom : this.side);
    }

    public Item getItemDropped(int par1, Random par2, int par3) {
        return Item.getItemFromBlock((Block)JourneyBlocks.StoneLelyetia);
    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            if (world.getBlockLightValue(x, y - 1, z) < 4 && world.getBlockLightOpacity(x, y - 1, z) > 2) {
                world.setBlock(x, y, z, JourneyBlocks.StoneLelyetia);
            } else if (world.getBlockLightValue(x, y - 1, z) >= 9) {
                for (int l = 0; l < 4; ++l) {
                    int k1;
                    int j1;
                    int i1 = x + random.nextInt(3) - 1;
                    if (world.getBlock(i1, j1 = y + random.nextInt(5) - 3, k1 = z + random.nextInt(3) - 1) != JourneyBlocks.StoneLelyetia || world.getBlockLightValue(i1, j1 - 1, k1) < 4 || world.getBlockLightOpacity(i1, j1 - 1, k1) > 2) continue;
                    world.setBlock(i1, j1, k1, (Block)this);
                }
            }
        }
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean flag) {
        return true;
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return true;
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z) {
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.top = icon.registerIcon("divinerpg:stoneLelyetia");
        this.side = icon.registerIcon("divinerpg:grassLelyetiaDown_side");
        this.bottom = icon.registerIcon("divinerpg:grassLelyetia_end");
        this.blockIcon = icon.registerIcon("divinerpg:grassLelyetiaDown_side");
    }
}

