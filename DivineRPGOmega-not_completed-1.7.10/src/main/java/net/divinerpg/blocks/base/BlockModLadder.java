/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLadder
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;

public class BlockModLadder
extends BlockLadder {
    protected String name;

    public BlockModLadder(String name) {
        this.name = name;
        this.setBlockTextureName("divinerpg:" + name);
        this.setBlockName(name);
        this.setCreativeTab(DivineRPGTabs.blocks);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setHardness(0.4f);
    }
}

