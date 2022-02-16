/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockModStairs
extends BlockStairs {
    public BlockModStairs(Block stair, String name) {
        super(stair, 0);
        this.setCreativeTab(DivineRPGTabs.blocks);
        LangRegistry.addBlock((Block)this);
        this.setBlockName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        this.useNeighborBrightness = true;
    }
}

