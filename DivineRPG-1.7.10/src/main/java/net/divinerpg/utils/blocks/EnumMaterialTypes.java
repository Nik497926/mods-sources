/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 */
package net.divinerpg.utils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public enum EnumMaterialTypes {
    DIRT(Material.ground, Block.soundTypeGravel);

    private Material m;
    private Block.SoundType s;

    private EnumMaterialTypes(Material m, Block.SoundType s) {
        this.m = m;
        this.s = s;
    }

    public Material getMaterial() {
        return this.m;
    }

    public Block.SoundType getSound() {
        return this.s;
    }
}

