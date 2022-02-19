/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 */
package net.divinerpg.utils.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class WashBricks
extends Block {
    private String name;
    private static Material WshBricks = Material.rock;

    public WashBricks() {
        super(WshBricks);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setHardness(50.0f);
        this.setResistance(0.5f);
        this.setLightLevel(0.0f);
    }

    public Block setTextureName(String name) {
        return this.setBlockTextureName("dvinerpg:" + name);
    }

    public Block setName(String name) {
        this.name = name;
        this.setBlockName(this.name);
        this.setTextureName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        return this;
    }
}

