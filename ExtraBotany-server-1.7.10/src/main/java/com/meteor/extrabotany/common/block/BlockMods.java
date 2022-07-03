/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockMods
extends Block {
    private String name;
    private boolean isBeaconBase;

    public BlockMods(Material material, String name) {
        super(material);
        this.name = name;
        this.setBlockTextureName("ExtraBotania:" + name);
        this.setBlockName(name);
        this.setHardness(2.0f);
        GameRegistry.registerBlock((Block)this, (String)name);
    }

    public String getName() {
        return this.name;
    }

    public void setBeaconBase(boolean bool) {
        this.isBeaconBase = bool;
    }

    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return this.isBeaconBase;
    }
}

