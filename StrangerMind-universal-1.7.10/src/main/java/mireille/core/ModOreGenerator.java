package mireille.core;

import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import mireille.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.world.gen.feature.*;

public class ModOreGenerator implements IWorldGenerator
{
    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
        if (ModConfig.OreGenerator) {
            this.addOreSpawn(ModBlocks.CreonitOre, world, random, chunkX * 16, chunkZ * 16, 3, 4, 2, 2, 8);
        }
    }
    
    public void addOreSpawn(final Block block, final World world, final Random random, final int chunkXPos, final int chunkZPos, final int minVainSize, final int maxVainSize, final int chancesToSpawn, final int minY, final int maxY) {
        for (int i = 0; i < chancesToSpawn; ++i) {
            final int posX = chunkXPos + random.nextInt(16);
            final int posY = minY + random.nextInt(maxY - minY);
            final int posZ = chunkZPos + random.nextInt(16);
            new WorldGenMinable(block, 0, 7, Blocks.stone).generate(world, random, posX, posY, posZ);
        }
    }
}
