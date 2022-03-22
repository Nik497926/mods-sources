/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileEAltar;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandHUD;

public class BlockEAltar
extends Block
implements ITileEntityProvider,
IWandHUD {
    protected BlockEAltar(Material p_i45394_1_) {
        super(Material.rock);
        this.setHardness(3.5f);
        this.setStepSound(soundTypeStone);
        this.setBlockName("efiraltar");
        GameRegistry.registerBlock(this, "efiraltar");
    }

    public TileEntity createNewTileEntity(World world, int side) {
        return new TileEAltar();
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int i, int i1, int i2) {
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side) {
        TileEAltar tile = (TileEAltar)world.getTileEntity(x, y, z);
        for (int i = 0; i < tile.getSizeInventory(); ++i) {
            ItemStack a = tile.getStackInSlot(i);
            if (a == null) continue;
            EntityItem b = new EntityItem(world, x, (double)y + Math.random(), z, a);
            world.spawnEntityInWorld(b);
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par1, float par2, float par3) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileEAltar) {
            return ((TileEAltar)te).onBlockActivated(world, x, y, z, player);
        }
        return false;
    }
}

