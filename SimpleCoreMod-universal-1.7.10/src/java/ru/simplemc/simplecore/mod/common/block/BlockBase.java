/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase
extends Block {
    public BlockBase(Material material, String name) {
        super(material);
        this.setBlockName("simplecore." + name);
        this.setBlockTextureName("simplecore:" + name);
    }
}

