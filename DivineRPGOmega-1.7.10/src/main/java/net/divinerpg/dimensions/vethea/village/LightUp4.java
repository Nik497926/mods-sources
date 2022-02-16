/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vethea.village;

import java.util.Random;
import net.divinerpg.entities.vethea.EntityTheHunger;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class LightUp4
extends WorldGenerator {
    protected int[] GetValidSpawnBlocks() {
        return new int[0];
    }

    public boolean generate(World world, Random rand, int i, int j, int k) {
        EntityTheHunger entity = new EntityTheHunger(world);
        entity.setLocationAndAngles((float)i - 1.0f, (float)j + 1.0f, (float)k - 1.0f, 0.0f, 0.0f);
        world.spawnEntityInWorld((Entity)entity);
        world.setBlock(i + 0, j + 0, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 0, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 1, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 1, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 2, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 2, k + 1, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 2, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 3, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 3, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 4, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 4, k + 1, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 4, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 0, j + 5, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 5, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 6, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 0, j + 6, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 1, j + 2, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 1, j + 2, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 1, j + 4, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 1, j + 4, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 0, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 0, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 1, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 1, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 2, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 2, k + 1, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 2, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 3, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 3, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 4, k + 0, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 4, k + 1, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 4, k + 2, VetheaBlocks.darkEverstone);
        world.setBlock(i + 2, j + 5, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 5, k + 2, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 6, k + 0, VetheaBlocks.firelight);
        world.setBlock(i + 2, j + 6, k + 2, VetheaBlocks.firelight);
        return true;
    }
}

