/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenLakes
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package net.divinerpg.dimensions.vanilla;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.divinerpg.dimensions.vanilla.WorldGenBlacksmithHouse;
import net.divinerpg.dimensions.vanilla.WorldGenDivineTree;
import net.divinerpg.dimensions.vanilla.WorldGenHut;
import net.divinerpg.dimensions.vanilla.WorldGenOres;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.BiomeDictionary;

public class DivineWorldgen
implements IWorldGenerator {
    private static WorldGenDivineTree tree = new WorldGenDivineTree(true);
    private static WorldGenHut hut = new WorldGenHut();
    private static WorldGenBlacksmithHouse blacksmith = new WorldGenBlacksmithHouse();

    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1: {
                this.generateNether(world, rand, chunkX * 16, chunkZ * 16);
                break;
            }
            case 0: {
                this.generateOverworld(world, rand, chunkX * 16, chunkZ * 16);
                break;
            }
            case 1: {
                this.generateEnd(world, rand, chunkX * 16, chunkZ * 16);
            }
        }
    }

    private void generateOverworld(World world, Random random, int i, int k) {
        int posX;
        int z;
        int y;
        int x;
        int c;
        BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(i, k);
        for (c = 0; c < 3; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(20);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.realmiteOre, world, random, Blocks.stone, x, y, z, 5);
        }
        for (c = 0; c < 3; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(40);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(JourneyBlocks.LuckyBlock, world, random, Blocks.stone, x, y, z, 5);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.arlemiteOre, world, random, Blocks.stone, x, y, z, 2);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.rupeeOre, world, random, Blocks.stone, x, y, z, 2);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.copperOre, world, random, Blocks.stone, x, y, z, 2);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(JourneyBlocks.sapphireOre, world, random, Blocks.stone, x, y, z, 2);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(JourneyBlocks.shadiumOre, world, random, Blocks.stone, x, y, z, 2);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(JourneyBlocks.luniumOre, world, random, Blocks.stone, x, y, z, 2);
        }
        for (c = 0; c < 2; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(16);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.amazoniteOre, world, random, Blocks.stone, x, y, z, 2);
        }
        if (random.nextInt(8) == 0) {
            posX = i + random.nextInt(16) + 8;
            int posY2 = random.nextInt(150);
            int posZ = k + random.nextInt(16) + 8;
            if (random.nextInt(10) == 0 || posY2 < 60) {
                new WorldGenLakes(VanillaBlocks.tar).generate(world, random, posX, posY2, posZ);
            }
        }
        if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.FOREST) && !BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.CONIFEROUS) && !BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE) && !BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.DENSE) && biome != BiomeGenBase.birchForest && biome != BiomeGenBase.birchForestHills && random.nextInt(2) == 0) {
            posX = i + random.nextInt(16);
            int posZ = k + random.nextInt(16);
            int posY = world.getHeightValue(posX, posZ);
            tree.generate(world, random, posX, posY, posZ);
        }
        if (world.provider.terrainType != WorldType.FLAT && (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.PLAINS) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SAVANNA)) && random.nextInt(6) == 0) {
            hut.generate(world, random, i + 8, world.getHeightValue(i + 8, k + 8) - 1, k + 8);
        }
    }

    private void generateNether(World world, Random random, int i, int k) {
        int z;
        int y;
        int x;
        int c;
        for (c = 0; c < 15; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(128);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.netheriteOre, world, random, Blocks.netherrack, x, y, z, 4);
        }
        for (c = 0; c < 15; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(128);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.bloodgemOre, world, random, Blocks.netherrack, x, y, z, 4);
        }
        for (c = 0; c < 15; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(128);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(TwilightBlocks.netheriumOre, world, random, Blocks.netherrack, x, y, z, 6);
        }
        for (c = 0; c < 5; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(128);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(JourneyBlocks.bloodcrustOre, world, random, Blocks.netherrack, x, y, z, 4);
        }
        for (c = 0; c < 5; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(128);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(VanillaBlocks.nightmareOre, world, random, Blocks.netherrack, x, y, z, 4);
        }
        for (c = 0; c < 3; ++c) {
            x = i + random.nextInt(16);
            y = random.nextInt(128);
            z = k + random.nextInt(16);
            new WorldGenOres().generate(JourneyBlocks.ash, world, random, Blocks.netherrack, x, y, z, 6);
        }
    }

    private void generateEnd(World world, Random random, int x, int z) {
    }
}

