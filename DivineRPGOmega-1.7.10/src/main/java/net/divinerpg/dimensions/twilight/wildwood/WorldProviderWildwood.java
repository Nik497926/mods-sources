/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.twilight.wildwood;

import net.divinerpg.dimensions.twilight.wildwood.ChunkProviderWildwood;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderWildwood
extends WorldProvider {
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.wildwoodBiome, 0.5f);
        this.dimensionId = ConfigurationHelper.wildwood;
        this.isHellWorld = false;
    }

    public String getSaveFolder() {
        return "The Wildwood";
    }

    public float getCloudHeight() {
        return 128.0f;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderWildwood(this.worldObj, this.worldObj.getSeed());
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public float calculateCelestialAngle(long var1, float var3) {
        return 0.1f;
    }

    public boolean canRespawnHere() {
        return false;
    }

    public String getDimensionName() {
        return "The Wildwood";
    }
}

