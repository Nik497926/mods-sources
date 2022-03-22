/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenAsgard
extends BiomeGenBase {
    public BiomeGenAsgard(int id) {
        super(id);
        this.setColor(2518783);
    }

    public int getSkyColorByTemp(float p_76731_1_) {
        return 2518783;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 5635969;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 6750149;
    }

    public int getWaterColorMultiplier() {
        return 30702;
    }
}

