/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.euca;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenEuca
extends BiomeGenBase {
    public BiomeGenEuca(int id) {
        super(id);
        this.setBiomeName("Euca");
        this.topBlock = JourneyBlocks.eucaGrass;
        this.fillerBlock = JourneyBlocks.eucaStone;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator.treesPerChunk = 8;
        this.enableRain = false;
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float f) {
        return 16766976;
    }

    public float getSpawningChance() {
        return 0.5f;
    }
}

