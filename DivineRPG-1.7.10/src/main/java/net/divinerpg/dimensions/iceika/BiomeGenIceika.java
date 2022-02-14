/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.iceika;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenIceika
extends BiomeGenBase {
    public BiomeGenIceika(int par1) {
        super(par1);
        this.setBiomeName("Iceika");
        this.topBlock = IceikaBlocks.frozenGrass;
        this.fillerBlock = IceikaBlocks.frozenDirt;
        this.rainfall = 999.0f;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.setEnableSnow();
        this.setTemperatureRainfall(0.0f, 0.5f);
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return 6511;
    }

    public float getSpawningChance() {
        return 0.5f;
    }
}

