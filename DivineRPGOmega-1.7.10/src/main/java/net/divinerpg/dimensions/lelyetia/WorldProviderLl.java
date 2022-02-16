/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.dimensions.lelyetia.ChunkProviderLelyetia;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderLl
extends WorldProvider {
    public String getDimensionName() {
        return "Lelyetia";
    }

    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.LelyetiaBiome, 1.0f);
        this.dimensionId = ConfigurationHelper.lelyetia;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderLelyetia(this.worldObj, this.worldObj.getSeed());
    }

    public float getCloudHeight() {
        return 128.0f;
    }

    public boolean canRespawnHere() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos((float)(par1 * (float)Math.PI * 2.0f)) * 2.0f + 0.5f;
        if (f2 < 1.5f) {
            f2 = 1.5f;
        }
        if (f2 > 5.0f) {
            f2 = 5.0f;
        }
        float f3 = 0.7529412f;
        float f4 = 0.84705883f;
        float f5 = 1.0f;
        f3 *= f2 * 0.94f + 0.06f;
        f4 *= f2 * 0.94f + 0.06f;
        f5 *= f2 * 0.91f + 0.09f;
        f3 = 0.614f * f2;
        f4 = 0.321f * f2;
        f5 = 0.13f * f2;
        return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
    }

    public boolean doesXZShowFog(int par1, int par2) {
        return true;
    }

    public String getSaveFolder() {
        return "Lelyetia";
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    public float calculateCelestialAngle(long var1, float var3) {
        return 0.0f;
    }
}

