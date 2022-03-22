/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.decor;

import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class ElfiriumOre
extends Block {
    public ElfiriumOre(String name) {
        super(Material.iron);
        this.setBlockName("ElfiriumOre");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockTextureName("extrabotania:elfiriumOre");
        GameRegistry.registerBlock(this, "ElfiriumOre");
    }

    public Item getItemDropped(int par1, Random par2Random, int fortune) {
        return ModItems.fragmentEfirium;
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }
}

