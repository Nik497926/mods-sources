/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package net.divinerpg.dimensions.euca;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.dimensions.euca.ChunkProviderEuca;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderEuca
extends WorldProvider {
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.eucaBiome, 0.5f);
        this.dimensionId = ConfigurationHelper.euca;
        this.isHellWorld = false;
    }

    public String getSaveFolder() {
        return "Euca";
    }

    public float getCloudHeight() {
        return 128.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float f1, float f2) {
        return Vec3.createVectorHelper((double)1.9, (double)1.1, (double)1.0);
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderEuca(this.worldObj, this.worldObj.getSeed());
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
        return "Euca";
    }
}

