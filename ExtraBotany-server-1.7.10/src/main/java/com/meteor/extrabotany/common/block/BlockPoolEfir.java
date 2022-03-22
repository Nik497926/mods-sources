/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandHUD;

public class BlockPoolEfir
extends Block
implements ITileEntityProvider,
IWandHUD {
    protected BlockPoolEfir(Material material) {
        super(material);
        this.setBlockName("BlockPoolEfir");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 1.0f, 1.5f, 1.0f);
        GameRegistry.registerBlock(this, "BlockPoolEfir");
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side) {
        super.breakBlock(world, x, y, z, block, side);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new TileBlockPoolEfir();
    }

    public void registerBlockIcons(IIconRegister par1IconRegister) {
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int i, int i1, int i2) {

    }
}

