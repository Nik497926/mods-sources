/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.decor;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.item.system.ItemBlockWithMetadataAndName;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliserUpgrade;

public class BlockExtraStabilizer
extends Block
implements IInfusionStabiliserUpgrade {
    public final float[] stab = new float[]{0.2f, 0.3f, 0.4f, 0.6f, 0.8f, 1.0f, 1.3f, 1.6f, 1.9f, 2.3f, 2.7f, 3.1f, 3.6f, 4.1f, 4.6f, 5.0f, 0.2f};
    public IIcon[] icons = new IIcon[16];

    public BlockExtraStabilizer() {
        super(Material.wood);
        this.setBlockName("extraStabilizer");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
    }

    public Block setBlockName(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.setBlockName(par1Str);
    }

    public void onPostBlockPlaced(World world, int x, int y, int z, int side) {
        super.onPostBlockPlaced(world, x, y, z, side);
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

    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 15; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public boolean canStabaliseInfusion(World world, int i, int i1, int i2) {
        return true;
    }

    @Override
    public float maxStabiliser(World world, int i, int i1, int i2) {
        int mt = world.getBlockMetadata(i, i1, i2);
        return this.stab[mt];
    }

    public void registerBlockIcons(IIconRegister ir) {
        for (int i = 0; i < 16; ++i) {
            this.icons[i] = ir.registerIcon(ExtraBotany.modid + ":stab/stab" + i);
        }
    }

    public IIcon getIcon(int side, int meta) {
        return this.icons[meta];
    }
}

