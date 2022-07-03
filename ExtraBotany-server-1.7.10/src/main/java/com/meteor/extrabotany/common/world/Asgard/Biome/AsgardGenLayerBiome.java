/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

import com.meteor.extrabotany.common.world.Asgard.Biome.BiomeRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager;

public class AsgardGenLayerBiome
extends GenLayer {
    private List<BiomeManager.BiomeEntry> desertBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private List<BiomeManager.BiomeEntry> warmBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private List<BiomeManager.BiomeEntry> coolBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private List<BiomeManager.BiomeEntry> icyBiomes = new ArrayList<BiomeManager.BiomeEntry>();
    private static final String __OBFID = "CL_00000555";

    public AsgardGenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_, WorldType p_i2122_4_) {
        super(p_i2122_1_);
        this.parent = p_i2122_3_;
        this.desertBiomes.addAll(BiomeManager.desertBiomes);
        this.warmBiomes.addAll(BiomeManager.warmBiomes);
        this.coolBiomes.addAll(BiomeManager.coolBiomes);
        this.icyBiomes.addAll(BiomeManager.icyBiomes);
        if (p_i2122_4_ == WorldType.DEFAULT_1_1) {
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.desert, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.forest, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.extremeHills, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.swampland, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.plains, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.taiga, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeRegistry.biomeAsgard, 10));
        } else {
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.desert, 30));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.savanna, 20));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeGenBase.plains, 10));
            this.desertBiomes.add(new BiomeManager.BiomeEntry(BiomeRegistry.biomeAsgard, 10));
        }
    }

    public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
        int[] aint = this.parent.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
        int[] aint1 = IntCache.getIntCache((int)(p_75904_3_ * p_75904_4_));
        for (int i1 = 0; i1 < p_75904_4_; ++i1) {
            for (int j1 = 0; j1 < p_75904_3_; ++j1) {
                this.initChunkSeed(j1 + p_75904_1_, i1 + p_75904_2_);
                int k1 = aint[j1 + i1 * p_75904_3_];
                int l1 = (k1 & 0xF00) >> 8;
                if (AsgardGenLayerBiome.isBiomeOceanic((int)(k1 &= 0xFFFFF0FF))) {
                    aint1[j1 + i1 * p_75904_3_] = k1;
                    continue;
                }
                if (k1 == BiomeGenBase.mushroomIsland.biomeID) {
                    aint1[j1 + i1 * p_75904_3_] = k1;
                    continue;
                }
                if (k1 == 1) {
                    if (l1 > 0) {
                        if (this.nextInt(3) == 0) {
                            aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.mesaPlateau.biomeID;
                            continue;
                        }
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.mesaPlateau_F.biomeID;
                        continue;
                    }
                    aint1[j1 + i1 * p_75904_3_] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.desertBiomes, (int)((int)(this.nextLong((long)((long)(WeightedRandom.getTotalWeight(this.desertBiomes) / 10))) * 10L)))).biome.biomeID;
                    continue;
                }
                if (k1 == 2) {
                    if (l1 > 0) {
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.jungle.biomeID;
                        continue;
                    }
                    aint1[j1 + i1 * p_75904_3_] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.warmBiomes, (int)((int)(this.nextLong((long)((long)(WeightedRandom.getTotalWeight(this.warmBiomes) / 10))) * 10L)))).biome.biomeID;
                    continue;
                }
                if (k1 == 3) {
                    if (l1 > 0) {
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.megaTaiga.biomeID;
                        continue;
                    }
                    aint1[j1 + i1 * p_75904_3_] = ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.coolBiomes, (int)((int)(this.nextLong((long)((long)(WeightedRandom.getTotalWeight(this.coolBiomes) / 10))) * 10L)))).biome.biomeID;
                    continue;
                }
                aint1[j1 + i1 * p_75904_3_] = k1 == 4 ? ((BiomeManager.BiomeEntry)WeightedRandom.getItem(this.icyBiomes, (int)((int)(this.nextLong((long)((long)(WeightedRandom.getTotalWeight(this.icyBiomes) / 10))) * 10L)))).biome.biomeID : BiomeGenBase.mushroomIsland.biomeID;
            }
        }
        return aint1;
    }
}

