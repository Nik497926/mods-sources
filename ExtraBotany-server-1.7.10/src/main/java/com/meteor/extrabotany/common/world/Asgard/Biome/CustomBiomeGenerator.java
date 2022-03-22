/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

import com.meteor.extrabotany.common.world.Asgard.Biome.AsgardChunk;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import ru.justagod.cutter.invoke.Invoke;

public class CustomBiomeGenerator
implements IChunkProvider {
    private final World worldObj;
    private double[] noiseField;

    public CustomBiomeGenerator(World par1World) {
        this.worldObj = par1World;
    }

    public Chunk loadChunk(int par1, int par2) {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int chunkX, int chunkZ) {
        this.noiseField = new double[825];
        Block[] blockInChunk = new Block[32768];
        Invoke.server(() -> {});
        AsgardChunk chunk = new AsgardChunk(this.worldObj, blockInChunk, chunkX, chunkZ);
        chunk.resetRelightChecks();
        return chunk;
    }

    public boolean chunkExists(int par1, int par2) {
        return true;
    }

    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
        int _chunkX = chunkX;
        int _chunkZ = chunkZ;
        Invoke.server(() -> {});
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "Asgard";
    }

    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int chunkX, int chunkZ) {
    }

    public void saveExtraData() {
    }

    public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
        return new ArrayList();
    }
}

