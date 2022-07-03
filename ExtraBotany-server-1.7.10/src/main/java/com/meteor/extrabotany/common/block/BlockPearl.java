/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPearl
extends Block {
    public BlockPearl() {
        super(Material.rock);
        this.setBlockName("blockPearl");
        GameRegistry.registerBlock((Block)this, (String)"blockPearl");
        this.setBlockTextureName(ExtraBotany.modid + ":BlockManaPearl");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }
}

