/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.boiling;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.dimensions.boiling.ChunkProviderBoiling;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderBoiling
extends WorldProvider {
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.boilingBiome, 0.5f);
        this.dimensionId = ConfigurationHelper.boiling;
        this.isHellWorld = true;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float f1, float f2) {
        return Vec3.createVectorHelper((double)0.2, (double)0.1, (double)0.0);
    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    public String getSaveFolder() {
        return "BoilingPoint";
    }

    public float getCloudHeight() {
        return 128.0f;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderBoiling(this.worldObj, this.worldObj.getSeed());
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public float calculateCelestialAngle(long var1, float var3) {
        return 0.6f;
    }

    public boolean canRespawnHere() {
        return false;
    }

    public String getDimensionName() {
        return "BoilingPoint";
    }
}

