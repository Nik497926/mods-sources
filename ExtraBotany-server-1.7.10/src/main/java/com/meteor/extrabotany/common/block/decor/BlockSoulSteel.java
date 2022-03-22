/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.decor;

import com.meteor.extrabotany.common.block.ModBlocks;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockSoulSteel
extends Block {
    public BlockSoulSteel(String name) {
        super(Material.iron);
        this.setBlockName("blocksoulsteel");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockTextureName("ExtraBotania:blocksoulsteel");
        GameRegistry.registerBlock(this, "blocksoulsteel");
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return Item.getItemFromBlock(ModBlocks.blocksoulsteel);
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }
}

