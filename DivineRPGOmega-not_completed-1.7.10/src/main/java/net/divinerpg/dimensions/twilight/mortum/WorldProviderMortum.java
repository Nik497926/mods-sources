/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.twilight.mortum;

import net.divinerpg.dimensions.twilight.mortum.ChunkProviderMortum;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderMortum
extends WorldProvider {
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.mortumBiome, 0.5f);
        this.dimensionId = ConfigurationHelper.mortum;
        this.hasNoSky = true;
    }

    protected void generateLightBrightnessTable() {
        float var1 = 0.1f;
        for (int var2 = 0; var2 <= 15; ++var2) {
            float var3 = 1.0f - (float)var2 / 15.0f;
            this.lightBrightnessTable[var2] = (1.0f - var3) / (var3 * 3.0f + 1.0f) * (1.0f - var1) + var1;
        }
    }

    public float getCloudHeight() {
        return 128.0f;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderMortum(this.worldObj, this.worldObj.getSeed());
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return false;
    }

    public float calculateCelestialAngle(long var1, float var3) {
        return 0.5f;
    }

    public boolean canRespawnHere() {
        return false;
    }

    public String getSaveFolder() {
        return "Mortum";
    }

    public String getDimensionName() {
        return "Mortum";
    }
}

