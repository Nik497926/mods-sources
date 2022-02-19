/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vethea;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityInfusionTable;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInfusionTable
extends BlockContainer {
    private IIcon side;
    private IIcon top;

    public BlockInfusionTable() {
        super(Material.rock);
        LangRegistry.addBlock((Block)this);
        this.setCreativeTab(DivineRPGTabs.vethea);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityInfusionTable();
    }

    public Block setName(String name) {
        this.setBlockTextureName(name);
        this.setBlockName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        return this;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntityInfusionTable entity = (TileEntityInfusionTable)world.getTileEntity(x, y, z);
        if (entity == null || par5EntityPlayer.isSneaking()) {
            return false;
        }
        par5EntityPlayer.openGui((Object)DivineRPG.instance, GuiHandler.infusionTable, world, x, y, z);
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.side = icon.registerIcon("divinerpg:" + this.getTextureName() + "_side");
        this.top = icon.registerIcon("divinerpg:" + this.getTextureName() + "_top");
    }

    public IIcon getIcon(int par1, int par2) {
        int var3 = par2 & 0xC;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? this.top : (var3 == 4 && (par1 == 5 || par1 == 4) ? this.top : (var3 == 8 && (par1 == 2 || par1 == 3) ? this.top : this.side));
    }
}

