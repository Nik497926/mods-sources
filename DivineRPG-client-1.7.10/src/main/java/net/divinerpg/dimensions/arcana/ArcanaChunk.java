/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.ChunkPosition
 */
package net.divinerpg.dimensions.arcana;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.world.ChunkPosition;

public class ArcanaChunk {
    private Block[] data = new Block[32768];
    private byte[] meta = new byte[32768];
    public List<ChunkPosition> chunkTileEntityPositions = new ArrayList<ChunkPosition>();

    public void setBlock(int x, int y, int z, Block b) {
        this.setBlock(x, y, z, b, 0);
    }

    public void setBlock(int x, int y, int z, Block b, int m) {
        this.data[x << 11 | z << 7 | y] = b;
        this.meta[x << 11 | z << 7 | y] = (byte)m;
        if (b.hasTileEntity(m)) {
            this.chunkTileEntityPositions.add(new ChunkPosition(x, y, z));
        }
    }

    public Block getBlock(int x, int y, int z) {
        return this.data[x << 11 | z << 7 | y];
    }

    public Block[] getChunkData() {
        return this.data;
    }

    public byte[] getChunkMetadata() {
        return this.meta;
    }
}

