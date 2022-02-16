/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.arcana;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenArcana
extends BiomeGenBase {
    public BiomeGenArcana(int id) {
        super(id);
        this.setBiomeName("Arcana");
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = null;
        this.fillerBlock = null;
        this.waterColorMultiplier = 0x242424;
    }
}

