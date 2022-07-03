/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileGenDust;
import com.meteor.extrabotany.common.item.system.ItemBlockWithMetadataAndName;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGenDust
extends BlockContainer {
    IIcon[] icons = new IIcon[3];

    public BlockGenDust() {
        super(Material.rock);
        this.setBlockName("blockGenDust");
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)"blockGenDust");
        GameRegistry.registerTileEntity(TileGenDust.class, (String)"extraBotania.blockGenDust");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileGenDust();
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

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (w.isRemote) {
            return true;
        }
        player.openGui((Object)ExtraBotany.instance, 0, w, x, y, z);
        return true;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileGenDust) {
            ((TileGenDust)te).onBlockBreak();
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    public void registerBlockIcons(IIconRegister ir) {
        this.icons[0] = ir.registerIcon(ExtraBotany.modid + ":TileGenDust_front");
        this.icons[1] = ir.registerIcon(ExtraBotany.modid + ":TileGenDust_side");
        this.icons[2] = ir.registerIcon(ExtraBotany.modid + ":TileGenDust_top");
    }

    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0: {
                return this.icons[2];
            }
            case 4: {
                return this.icons[0];
            }
        }
        return this.icons[1];
    }

    public IIcon getIcon(IBlockAccess w, int x, int y, int z, int side) {
        switch (side) {
            case 0: {
                return this.icons[2];
            }
            case 4: {
                return this.icons[0];
            }
        }
        return this.icons[1];
    }
}

