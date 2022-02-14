/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.arcana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.divinerpg.dimensions.arcana.ChunkPrimer;
import net.divinerpg.dimensions.arcana.components.DungeonCeiling;
import net.divinerpg.dimensions.arcana.components.DungeonComponenet18;
import net.divinerpg.dimensions.arcana.components.DungeonComponenet19;
import net.divinerpg.dimensions.arcana.components.DungeonComponent;
import net.divinerpg.dimensions.arcana.components.DungeonComponent1;
import net.divinerpg.dimensions.arcana.components.DungeonComponent10;
import net.divinerpg.dimensions.arcana.components.DungeonComponent11;
import net.divinerpg.dimensions.arcana.components.DungeonComponent12;
import net.divinerpg.dimensions.arcana.components.DungeonComponent13;
import net.divinerpg.dimensions.arcana.components.DungeonComponent14;
import net.divinerpg.dimensions.arcana.components.DungeonComponent15;
import net.divinerpg.dimensions.arcana.components.DungeonComponent16;
import net.divinerpg.dimensions.arcana.components.DungeonComponent17;
import net.divinerpg.dimensions.arcana.components.DungeonComponent2;
import net.divinerpg.dimensions.arcana.components.DungeonComponent22;
import net.divinerpg.dimensions.arcana.components.DungeonComponent3;
import net.divinerpg.dimensions.arcana.components.DungeonComponent4;
import net.divinerpg.dimensions.arcana.components.DungeonComponent5;
import net.divinerpg.dimensions.arcana.components.DungeonComponent6;
import net.divinerpg.dimensions.arcana.components.DungeonComponent7;
import net.divinerpg.dimensions.arcana.components.DungeonComponent8;
import net.divinerpg.dimensions.arcana.components.DungeonComponent9;
import net.divinerpg.dimensions.arcana.components.DungeonComponentBase;
import net.divinerpg.dimensions.arcana.components.DungeonComponentDramix;
import net.divinerpg.dimensions.arcana.components.DungeonComponentParasecta;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkProviderArcana
implements IChunkProvider {
    private ArrayList Rooms;
    private ArrayList BossRooms;
    private DungeonCeiling Ceiling;
    private World worldObj;
    private Random random;
    private Map chunkTileEntityMap;

    public ChunkProviderArcana(World world, long seed) {
        this.worldObj = world;
        this.random = new Random(seed);
        this.Rooms = new ArrayList(21);
        this.BossRooms = new ArrayList(2);
        this.Rooms.add(new DungeonComponent());
        this.Rooms.add(new DungeonComponent1());
        this.Rooms.add(new DungeonComponent2());
        this.Rooms.add(new DungeonComponent3());
        this.Rooms.add(new DungeonComponent4());
        this.Rooms.add(new DungeonComponent5());
        this.Rooms.add(new DungeonComponent6());
        this.Rooms.add(new DungeonComponent7());
        this.Rooms.add(new DungeonComponent9());
        this.Rooms.add(new DungeonComponent10());
        this.Rooms.add(new DungeonComponent11());
        this.Rooms.add(new DungeonComponent12());
        this.Rooms.add(new DungeonComponent13());
        this.Rooms.add(new DungeonComponent14());
        this.Rooms.add(new DungeonComponent15());
        this.Rooms.add(new DungeonComponent16());
        this.Rooms.add(new DungeonComponent17());
        this.Rooms.add(new DungeonComponent22());
        this.Rooms.add(new DungeonComponenet18());
        this.Rooms.add(new DungeonComponenet19());
        this.Rooms.add(new DungeonComponent8());
        this.BossRooms.add(new DungeonComponentParasecta());
        this.BossRooms.add(new DungeonComponentDramix());
        this.Ceiling = new DungeonCeiling();
        this.chunkTileEntityMap = new HashMap();
    }

    public boolean chunkExists(int i, int j) {
        return false;
    }

    public Chunk provideChunk(int chunkX, int chunkZ) {
        ChunkPrimer primer = new ChunkPrimer();
        for (int i = 4; i > 0; --i) {
            DungeonComponentBase room = (DungeonComponentBase)this.Rooms.get(this.random.nextInt(21));
            if (room instanceof DungeonComponent8 && i >= 3) {
                room = (DungeonComponentBase)this.Rooms.get(this.random.nextInt(10) + 10);
            }
            room.generate(primer, this.random, 0, i * 8, 0);
        }
        this.Ceiling.generate(primer, this.random, 0, 40, 0);
        this.chunkTileEntityMap.put(new ChunkCoords(chunkX, chunkZ), primer.chunkTileEntityPositions);
        Chunk chunk = new Chunk(this.worldObj, primer.getChunkData(), primer.getChunkMetadata(), chunkX, chunkZ);
        chunk.generateSkylightMap();
        BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(null, chunkX * 16, chunkZ * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();
        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte)abiomegenbase[i].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public Chunk loadChunk(int i, int j) {
        return this.provideChunk(i, j);
    }

    public void populate(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
        int i;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + 16, z + 16);
        boolean flag = false;
        this.random.setSeed(this.worldObj.getSeed());
        long var8 = this.random.nextLong() / 2L * 2L + 1L;
        long var10 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)chunkX * var8 + (long)chunkZ * var10 ^ this.worldObj.getSeed());
        Random rand = this.random;
        Chunk chunk = this.worldObj.getChunkFromChunkCoords(chunkX, chunkZ);
        ChunkCoords chunkCoords = new ChunkCoords(chunkX, chunkZ);
        List chunkTileEntityPositions = (List)this.chunkTileEntityMap.get(chunkCoords);
        if (chunkTileEntityPositions != null) {
            for (i = 0; i < chunkTileEntityPositions.size(); ++i) {
                ChunkPosition chunkPosition = (ChunkPosition)chunkTileEntityPositions.get(i);
                Block b = chunk.getBlock(chunkPosition.chunkPosX, chunkPosition.chunkPosY, chunkPosition.chunkPosZ);
                TileEntity te = b.createTileEntity(this.worldObj, 0);
                this.worldObj.setTileEntity(x + chunkPosition.chunkPosX, chunkPosition.chunkPosY, z + chunkPosition.chunkPosZ, te);
            }
            this.chunkTileEntityMap.remove(chunkCoords);
        }
        if ((chunkX & 1) == 1 && (chunkZ & 1) == 1) {
            for (i = 1; i < 4; ++i) {
                if (this.random.nextInt(30) != 0 && this.random.nextInt(30) != 0 && this.random.nextInt(30) != 0) continue;
                int roomToGenerate = rand.nextInt(2);
                ((WorldGenerator)this.BossRooms.get(roomToGenerate)).generate(this.worldObj, rand, x, i * 8, z);
                this.random.setSeed((long)chunkX * var8 + (long)chunkZ * var10 ^ this.worldObj.getSeed() * (long)i << 2 | var10);
                break;
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "Arcana";
    }

    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k) {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(i, k);
        return biomegenbase == null ? null : biomegenbase.getSpawnableList(enumcreaturetype);
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int i, int j) {
    }

    public void saveExtraData() {
    }

    public ChunkPosition func_147416_a(World var1, String var2, int var3, int var4, int var5) {
        return null;
    }

    public class ChunkCoords {
        public final int chunkCoordX;
        public final int chunkCoordZ;

        public ChunkCoords(int X, int Z) {
            this.chunkCoordX = X;
            this.chunkCoordZ = Z;
        }

        public boolean equals(Object o) {
            if (!(o instanceof ChunkCoords)) {
                return false;
            }
            ChunkCoords chunkCoords = (ChunkCoords)o;
            return chunkCoords.chunkCoordX == this.chunkCoordX && chunkCoords.chunkCoordZ == this.chunkCoordZ;
        }

        public int hashCode() {
            return this.chunkCoordX + this.chunkCoordZ * 31;
        }
    }
}

