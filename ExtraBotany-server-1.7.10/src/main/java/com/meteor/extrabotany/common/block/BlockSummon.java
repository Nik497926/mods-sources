/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileBlockSummon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandHUD;

public class BlockSummon
extends Block
implements ITileEntityProvider,
IWandHUD {
    protected BlockSummon(Material material) {
        super(material);
        this.setBlockName("blocksummon");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockTextureName("ExtraBotania:blocksummon");
        GameRegistry.registerBlock(this, "blocksummon");
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side) {
        super.breakBlock(world, x, y, z, block, side);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new TileBlockSummon();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var0, float var1, float var2, float var3) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileBlockSummon) {
            ((TileBlockSummon)tile).onClickBlock(world, x, y, z, player);
            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int x, int y, int z) {

    }
}

