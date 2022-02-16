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

public class GeneratedBlock
extends Block {
    private String name;

    public GeneratedBlock(Material Mtr) {
        super(Mtr);
        this.setCreativeTab(DivineRPGTabs.blocks);
        if (Mtr == Material.rock) {
            this.setHardness(1.6f);
            this.setResistance(25.0f);
        } else {
            this.setHarvestLevel("shovel", 1);
            this.setHardness(0.6f);
            this.setResistance(2.2f);
        }
        if (Mtr == Material.ground) {
            this.setStepSound(Block.soundTypeGravel);
        } else if (Mtr == Material.cloth) {
            this.setStepSound(Block.soundTypeCloth);
        } else if (Mtr == Material.sand) {
            this.setStepSound(Block.soundTypeSand);
        }
    }

    public Block setTextureName(String name) {
        return this.setBlockTextureName("divinerpg:" + name);
    }

    public Block setName(String name) {
        this.name = name;
        this.setBlockName(this.name);
        this.setTextureName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        return this;
    }
}

