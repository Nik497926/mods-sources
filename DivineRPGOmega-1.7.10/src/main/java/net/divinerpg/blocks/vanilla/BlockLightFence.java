/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockModFence;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockLightFence
extends BlockModFence {
    private final boolean powered;
    private String name;

    public BlockLightFence(boolean powered, String name, float hardness) {
        super(name, Material.rock);
        this.name = name;
        this.powered = powered;
        this.setBlockTextureName("divinerpg:" + name);
        this.setStepSound(Block.soundTypeGlass);
        this.setHardness(hardness);
        this.setCreativeTab(DivineRPGTabs.blocks);
        if (powered) {
            this.setLightLevel(1.0f);
            this.setCreativeTab(null);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("divinerpg:" + this.name);
    }

    public void onBlockAdded(World par1, int par2, int par3, int par4) {
        if (!par1.isRemote) {
            if (this.powered && !par1.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1.scheduleBlockUpdate(par2, par3, par4, (Block)this, 4);
            } else if (!this.powered && par1.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceBlue) {
                    par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceBlueOn);
                }
                if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceGreen) {
                    par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceGreenOn);
                }
                if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceRed) {
                    par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceRedOn);
                }
            }
        }
    }

    public void onNeighborBlockChange(World par1, int par2, int par3, int par4, Block par5) {
        if (!par1.isRemote) {
            if (this.powered && !par1.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                par1.scheduleBlockUpdate(par2, par3, par4, (Block)this, 4);
            } else if (!this.powered && par1.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
                if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceBlue) {
                    par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceBlueOn);
                }
                if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceGreen) {
                    par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceGreenOn);
                }
                if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceRed) {
                    par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceRedOn);
                }
            }
        }
    }

    public void updateTick(World par1, int par2, int par3, int par4, Random par5) {
        if (!par1.isRemote && this.powered && !par1.isBlockIndirectlyGettingPowered(par2, par3, par4)) {
            if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceBlueOn) {
                par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceBlue);
            }
            if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceGreenOn) {
                par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceGreen);
            }
            if (par1.getBlock(par2, par3, par4) == VanillaBlocks.lightFenceRedOn) {
                par1.setBlock(par2, par3, par4, VanillaBlocks.lightFenceRed);
            }
        }
    }

    public Item getItem(World w, int i, int j, int k) {
        return Item.getItemFromBlock((Block)this);
    }
}

