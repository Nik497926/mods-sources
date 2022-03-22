/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard;

import com.meteor.extrabotany.common.world.Asgard.Biome.BiomeRegistry;
import com.meteor.extrabotany.common.world.Asgard.Biome.CustomBiomeGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class AsgardWorldProvider
extends WorldProvider {
    public String getDimensionName() {
        return "Asgard Land";
    }

    public String getWelcomeMessage() {
        return "Entering Asgard Lands";
    }

    public String getDepartMessage() {
        return "Leaving Asgard Lands";
    }

    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return true;
    }

    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return false;
    }

    public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
        return false;
    }

    public boolean canDoLightning(Chunk chunk) {
        return true;
    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeRegistry.biomeAsgard, 0.0f);
        this.dimensionId = 150;
        this.hasNoSky = false;
    }

    public IChunkProvider createChunkGenerator() {
        return new CustomBiomeGenerator(this.worldObj);
    }

    public float calculateCelestialAngle(long var1, float var3) {
        float j = var1 % 24000L;
        float f1 = (j + var3) / 24000.0f - 0.25f;
        if (f1 < 0.0f) {
            f1 += 1.0f;
        }
        if (f1 > 1.0f) {
            f1 -= 1.0f;
        }
        float f2 = f1;
        f1 = (float)(1.0 - (Math.cos((double)f1 * Math.PI) + 1.0) / 2.0);
        f1 = f2 + (f1 - f2) / 3.0f;
        return f1;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float sunAngle, float partialTicks) {
        return super.getFogColor(sunAngle, partialTicks);
    }

    public boolean canRespawnHere() {
        return false;
    }

    public boolean isSurfaceWorld() {
        return true;
    }

    public boolean canCoordinateBeSpawn(int p_76566_1_, int p_76566_2_) {
        return this.worldObj.getTopBlock(p_76566_1_, p_76566_2_).getMaterial().blocksMovement();
    }

    public ChunkCoordinates getEntrancePortalLocation() {
        return null;
    }

    public float getCloudHeight() {
        return 164.0f;
    }
}

