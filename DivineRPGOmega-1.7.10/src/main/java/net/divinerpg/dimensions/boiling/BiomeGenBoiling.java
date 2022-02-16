/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.boiling;

import java.awt.Color;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBoiling
extends BiomeGenBase {
    public BiomeGenBoiling(int par1) {
        super(par1);
        this.setBiomeName("BoilingPoint");
        this.topBlock = JourneyBlocks.hotBlock;
        this.fillerBlock = JourneyBlocks.rockDust;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.rainfall = 0.0f;
        this.setDisableRain();
        this.setColor(12846592);
    }

    public int getSkyColorByTemp(float f) {
        return Color.getHSBColor(0.0f, 0.0f, 0.0f).getRGB();
    }

    public float getSpawningChance() {
        return 0.5f;
    }
}

