/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.vethea.village;

import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.dimensions.vethea.all.WorldGenConeUp;
import net.divinerpg.dimensions.vethea.village.HouseDown1;
import net.divinerpg.dimensions.vethea.village.HouseDown2;
import net.divinerpg.dimensions.vethea.village.HouseDown3;
import net.divinerpg.dimensions.vethea.village.HouseUp1;
import net.divinerpg.dimensions.vethea.village.HouseUp2;
import net.divinerpg.dimensions.vethea.village.HouseUp3;
import net.divinerpg.dimensions.vethea.village.HouseUp4;
import net.divinerpg.dimensions.vethea.village.HouseUp5;
import net.divinerpg.dimensions.vethea.village.LightDown1;
import net.divinerpg.dimensions.vethea.village.LightDown2;
import net.divinerpg.dimensions.vethea.village.LightDown3;
import net.divinerpg.dimensions.vethea.village.LightDown4;
import net.divinerpg.dimensions.vethea.village.LightUp1;
import net.divinerpg.dimensions.vethea.village.LightUp2;
import net.divinerpg.dimensions.vethea.village.LightUp3;
import net.divinerpg.dimensions.vethea.village.LightUp4;
import net.divinerpg.dimensions.vethea.village.LightUp5;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVillageIsland
extends WorldGenerator {
    public final WorldGenConeUp spike = new WorldGenConeUp(VetheaBlocks.dreamStone);
    public final ArrayList<WorldGenerator> housesDown = new ArrayList(3);
    public final ArrayList<WorldGenerator> housesUp;
    public final ArrayList<WorldGenerator> lampsDown;
    public final ArrayList<WorldGenerator> lampsUp;

    public WorldGenVillageIsland() {
        this.housesDown.add(new HouseDown1());
        this.housesDown.add(new HouseDown2());
        this.housesDown.add(new HouseDown3());
        this.housesUp = new ArrayList(5);
        this.housesUp.add(new HouseUp1());
        this.housesUp.add(new HouseUp2());
        this.housesUp.add(new HouseUp3());
        this.housesUp.add(new HouseUp4());
        this.housesUp.add(new HouseUp5());
        this.lampsDown = new ArrayList(4);
        this.lampsDown.add(new LightDown1());
        this.lampsDown.add(new LightDown2());
        this.lampsDown.add(new LightDown3());
        this.lampsDown.add(new LightDown4());
        this.lampsUp = new ArrayList(5);
        this.lampsUp.add(new LightUp1());
        this.lampsUp.add(new LightUp2());
        this.lampsUp.add(new LightUp3());
        this.lampsUp.add(new LightUp4());
        this.lampsUp.add(new LightUp5());
    }

    public boolean generate(World par1, Random par2, int par3, int par4, int par5) {
        int var2 = par2.nextInt(15) + 25;
        this.placeSpikeIteration(par1, par2, par3, par4, par5, var2);
        return true;
    }

    void placeSpikeIteration(World par1World, Random par2, int x, int y, int z, int radius) {
        for (float i = 0.0f; i < (float)radius; i += 5.0f) {
            float j = 0.0f;
            while ((double)j < Math.PI * 2 * (double)i) {
                int var1;
                this.spike.generate(par1World, par2, (int)Math.round((double)x + Math.sin(j) * (double)(i - (float)par2.nextInt(2))), y - (int)(i / 10.0f), (int)Math.round((double)z + Math.cos(j) * (double)(i - (float)par2.nextInt(2))), (int)((float)radius - i) / 2);
                if (j % 8.0f == 0.0f) {
                    var1 = par2.nextInt(5);
                    if (var1 == 0) {
                        this.lampsDown.get(par2.nextInt(4)).generate(par1World, par2, (int)Math.round((double)x + Math.sin(j) * (double)(i - (float)par2.nextInt(2))), y - (int)(i / 10.0f) - 5, (int)Math.round((double)z + Math.cos(j) * (double)(i - (float)par2.nextInt(2))));
                    } else if (var1 == 1) {
                        this.housesDown.get(par2.nextInt(3)).generate(par1World, par2, (int)Math.round((double)x + Math.sin(j) * (double)(i - (float)par2.nextInt(2))), y - (int)(i / 10.0f) - 5, (int)Math.round((double)z + Math.cos(j) * (double)(i - (float)par2.nextInt(2))));
                    }
                } else if (par2.nextInt(5) == 0 && j % 8.0f == 4.0f) {
                    var1 = par2.nextInt(5);
                    int var2 = y + (int)((float)radius - i) / 2 + 1;
                    while (!par1World.isAirBlock((int)Math.round((double)x + Math.sin(j) * (double)(i - (float)par2.nextInt(2))), var2, (int)Math.round((double)z + Math.cos(j) * (double)(i - (float)par2.nextInt(2))))) {
                        ++var2;
                    }
                    if (var1 == 0) {
                        this.lampsUp.get(par2.nextInt(4)).generate(par1World, par2, (int)Math.round((double)x + Math.sin(j) * (double)(i - (float)par2.nextInt(2))), var2, (int)Math.round((double)z + Math.cos(j) * (double)(i - (float)par2.nextInt(2))));
                    } else if (var1 == 1) {
                        this.housesUp.get(par2.nextInt(5)).generate(par1World, par2, (int)Math.round((double)x + Math.sin(j) * (double)(i - (float)par2.nextInt(2))), var2, (int)Math.round((double)z + Math.cos(j) * (double)(i - (float)par2.nextInt(2))));
                    }
                }
                j += 4.0f;
            }
        }
    }
}

