/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.iceika;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.dimensions.iceika.ChunkProviderIceika;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderIceika
extends WorldProvider {
    public void registerWorldChunkManager() {
        this.dimensionId = ConfigurationHelper.iceika;
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.iceikaBiome, 1.0f);
        this.hasNoSky = true;
    }

    public float getCloudHeight() {
        return 128.0f;
    }

    public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
        return true;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderIceika(this.worldObj, this.worldObj.getSeed());
    }

    protected void generateLightBrightnessTable() {
        float f = 0.01f;
        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0f - (float)i / 15.0f;
            this.lightBrightnessTable[i] = (1.0f - f1) / (f1 * 3.0f + 1.0f) * (1.0f - f) + f;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
        return null;
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public float calculateCelestialAngle(long var1, float var3) {
        return 0.28f;
    }

    public boolean canRespawnHere() {
        return false;
    }

    public String getDimensionName() {
        return "Iceika";
    }

    public String getSaveFolder() {
        return "Iceika";
    }
}

