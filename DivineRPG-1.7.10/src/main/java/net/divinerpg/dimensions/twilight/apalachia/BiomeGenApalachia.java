/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.twilight.apalachia;

import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenApalachia
extends BiomeGenBase {
    public BiomeGenApalachia(int par1) {
        super(par1);
        this.setBiomeName("Apalachia");
        this.topBlock = TwilightBlocks.apalachiaGrass;
        this.fillerBlock = TwilightBlocks.apalachiaDirt;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.waterColorMultiplier = 0xC000C0;
        this.theBiomeDecorator.treesPerChunk = 6;
    }

    public float getSpawningChance() {
        return 0.7f;
    }
}

