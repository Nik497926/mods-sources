/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.IBlockAccess
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockModFence
extends BlockFence {
    protected String name;

    public BlockModFence(String name) {
        super(name, Material.wood);
        this.name = name;
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
    }

    public boolean canConnectFenceTo(IBlockAccess blockAccess, int x, int y, int z) {
        Block block = blockAccess.getBlock(x, y, z);
        return block instanceof BlockFence || block instanceof BlockFenceGate;
    }
}

