/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.twilight.mortum;

import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenMortum
extends BiomeGenBase {
    public BiomeGenMortum(int var1) {
        super(var1);
        this.setBiomeName("Mortum");
        this.topBlock = TwilightBlocks.mortumGrass;
        this.fillerBlock = TwilightBlocks.mortumDirt;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.waterColorMultiplier = 0x242424;
    }

    public float getSpawningChance() {
        return 0.5f;
    }
}

