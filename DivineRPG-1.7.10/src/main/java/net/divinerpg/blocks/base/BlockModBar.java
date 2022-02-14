/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPane
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockModBar
extends BlockPane {
    private String name;
    private Material mat;
    private IIcon icon;

    public BlockModBar(String name, Material mat, boolean dropItem, boolean unbreakable) {
        super(name, name, mat, dropItem);
        this.name = name;
        this.mat = mat;
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setBlockName(name);
        if (unbreakable) {
            this.setHardness(9999999.0f);
        }
        this.setCreativeTab(DivineRPGTabs.blocks);
    }

    public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
        return this.canPaneConnectToBlock(world.getBlock(x, y, z)) || world.isSideSolid(x, y, z, dir.getOpposite(), false);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon("divinerpg:" + this.name);
        this.icon = register.registerIcon("divinerpg:" + this.name);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_150097_e() {
        return this.icon;
    }
}

