/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.twilight.skythern;

import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenSkythern
extends BiomeGenBase {
    public BiomeGenSkythern(int var1) {
        super(var1);
        this.setBiomeName("Skythern");
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = TwilightBlocks.skythernGrass;
        this.fillerBlock = TwilightBlocks.skythernDirt;
        this.waterColorMultiplier = 0x242424;
    }
}

