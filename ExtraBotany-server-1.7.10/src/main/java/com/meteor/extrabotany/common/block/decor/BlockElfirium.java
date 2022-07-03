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

public class BlockElfirium
extends Block {
    public BlockElfirium(String name) {
        super(Material.iron);
        this.setBlockName("blockelfirium");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockTextureName("ExtraBotania:blockelfirium");
        GameRegistry.registerBlock((Block)this, (String)"blockelfirium");
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return Item.getItemFromBlock((Block)ModBlocks.blockelfirium);
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }
}

