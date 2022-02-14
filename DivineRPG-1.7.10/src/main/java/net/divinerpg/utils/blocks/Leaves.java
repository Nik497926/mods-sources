/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.IShearable
 */
package net.divinerpg.utils.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

public class Leaves
extends BlockLeavesBase
implements IShearable {
    private static Block.SoundType leaves = Block.soundTypeGrass;
    private String name;

    public Leaves() {
        super(Material.leaves, false);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setHardness(0.2f);
        this.setResistance(0.0f);
        this.setSoundType(leaves);
    }

    public Block setTextureName(String name) {
        return this.setBlockTextureName("divinerpg:" + name);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    public Block setName(String name) {
        this.name = name;
        this.setBlockName(this.name);
        this.setTextureName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        return this;
    }

    public Block setSoundType(Block.SoundType name) {
        return this.setStepSound(name);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }
}

