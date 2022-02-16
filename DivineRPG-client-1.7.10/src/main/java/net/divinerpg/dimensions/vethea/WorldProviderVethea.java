/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.vethea;

import net.divinerpg.dimensions.vethea.ChunkProviderVethea;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVethea
extends WorldProvider {
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.vetheaBiome, 1.0f);
        this.dimensionId = ConfigurationHelper.vethea;
    }

    public float getCloudHeight() {
        return 256.0f;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderVethea(this.worldObj, this.worldObj.getSeed());
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return false;
    }

    public float calculateCelestialAngle(long var1, float var3) {
        return 0.3f;
    }

    public boolean canRespawnHere() {
        return true;
    }

    public String getSaveFolder() {
        return "Vethea";
    }

    public double getMovementFactor() {
        return 1.0;
    }

    public String getDimensionName() {
        return "Vethea";
    }
}

