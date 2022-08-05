/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.djgiannuzz.thaumcraftneiplugin.proxy.ServerProxy;
import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.tile.TileAutoTradeElf;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockAutoTrade
extends BlockContainer {
    public BlockAutoTrade() {
        super(Material.rock);
        this.setBlockName("blockAutoTradeElf");
        this.setBlockTextureName("blockAutoTradeElf");
        GameRegistry.registerBlock((Block)this, (String)"blockAutoTradeElf");
        GameRegistry.registerTileEntity(TileAutoTradeElf.class, (String)"extraBotania.blockAutoTradeElf");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAutoTradeElf();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z));
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z));
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, metadata));
        return ret;
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float hx, float hy, float hz) {
        player.openGui(ExtraBotany.instance, 0, w, x, y, z);
        return true;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileAutoTradeElf) {
            ((TileAutoTradeElf)te).onBlockBreak();
        }
        super.breakBlock(world, x, y, z, block, meta);
    }
}

