/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.decor;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockExMachineSpear
extends Block {
    public BlockExMachineSpear(String name) {
        super(Material.iron);
        this.setBlockName("spearexmachine");
        this.setHardness(900.0f);
        this.setResistance(2000.0f);
        this.setHarvestLevel("pickaxe", 9);
        this.setBlockTextureName("ExtraBotania:spearexmachine");
        GameRegistry.registerBlock((Block)this, (String)"spearexmachine");
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return null;
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }
}

