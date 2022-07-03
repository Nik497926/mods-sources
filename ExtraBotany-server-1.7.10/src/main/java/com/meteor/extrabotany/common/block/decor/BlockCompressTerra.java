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

public class BlockCompressTerra
extends Block {
    public BlockCompressTerra(String name) {
        super(Material.iron);
        this.setBlockName("compressterra");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockTextureName("ExtraBotania:compressTerra");
        GameRegistry.registerBlock((Block)this, (String)"compressterra");
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return Item.getItemFromBlock((Block)ModBlocks.compressedTerra);
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }
}

