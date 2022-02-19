/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.blocks.vanilla.DivineMetaItemBlock;
import net.divinerpg.blocks.vanilla.IDivineMetaBlock;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;

public class BlockCase
extends Block {
    public BlockCase(String name) {
        super(EnumBlockType.ROCK.getMaterial());
        this.setBlockTextureName("divinerpg:" + name);
        this.setStepSound(EnumBlockType.ROCK.getSound());
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setTextureName(this.textureName);
        this.setBlockName("divine_case");
        this.setHardness(-1.0f);
        this.setResistance(1.0E9f);
        if (!(this instanceof IDivineMetaBlock)) {
            GameRegistry.registerBlock((Block)this, (String)name);
        } else {
            GameRegistry.registerBlock((Block)this, DivineMetaItemBlock.class, (String)name);
        }
        LangRegistry.addBlock(this);
    }

    public Block setTextureName(String textureName) {
        this.textureName = textureName;
        return this;
    }
}

