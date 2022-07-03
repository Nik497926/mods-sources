/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard.Biome;

import com.meteor.extrabotany.common.world.Asgard.Biome.BiomeGenAsgard;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeRegistry {
    public static BiomeGenBase biomeAsgard;

    public static void mainRegsitry() {
        BiomeRegistry.initializeBiome();
        BiomeRegistry.registerBiome();
    }

    public static void initializeBiome() {
        biomeAsgard = new BiomeGenAsgard(137).setColor(2518783).setBiomeName("Asgard").setTemperatureRainfall(1.2f, 0.9f);
    }

    public static void registerBiome() {
        BiomeDictionary.registerBiomeType((BiomeGenBase)biomeAsgard, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.MAGICAL});
        BiomeManager.addSpawnBiome((BiomeGenBase)biomeAsgard);
    }
}

