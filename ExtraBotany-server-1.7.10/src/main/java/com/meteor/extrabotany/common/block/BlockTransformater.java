/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileTransformater;
import com.meteor.extrabotany.common.item.basic.ItemEfirFragment;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandHUD;

public class BlockTransformater
extends Block
implements ITileEntityProvider,
IWandHUD {
    protected BlockTransformater(Material par2Material) {
        super(Material.rock);
        this.setHardness(3.5f);
        this.setStepSound(soundTypeStone);
        this.setBlockName("transformater");
        GameRegistry.registerBlock(this, "transformater");
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileTransformater();
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par0, float par1, float par2) {
        ItemStack in;
        if (world.isRemote) {
            return super.onBlockActivated(world, x, y, z, player, side, par0, par1, par2);
        }
        TileTransformater te = (TileTransformater)world.getTileEntity(x, y, z);
        if (player.inventory.getCurrentItem() != null && te.getStackInSlot(0) == null && (in = player.inventory.getCurrentItem().copy()).getItem() instanceof ItemEfirFragment) {
            in.stackSize = 1;
            ItemStack out = player.inventory.getCurrentItem().copy();
            if (out.stackSize == 1) {
                out = null;
            } else {
                --out.stackSize;
            }
            player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
            te.onStart(in);
            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int i, int i1, int i2) {

    }
}

