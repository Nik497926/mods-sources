/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package net.divinerpg.dimensions.twilight.mortum;

import java.util.Random;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMortumStone
extends WorldGenerator {
    private int numberOfBlocks;

    public WorldGenMortumStone(int var2) {
        this.numberOfBlocks = var2;
    }

    public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
        float var6 = var2.nextFloat() * (float)Math.PI;
        double var7 = (float)(var3 + 8) + MathHelper.sin((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var9 = (float)(var3 + 8) - MathHelper.sin((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var11 = (float)(var5 + 8) + MathHelper.cos((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var13 = (float)(var5 + 8) - MathHelper.cos((float)var6) * (float)this.numberOfBlocks / 8.0f;
        double var15 = var4 + var2.nextInt(3) - 2;
        double var17 = var4 + var2.nextInt(3) - 2;
        for (int var19 = 0; var19 <= this.numberOfBlocks; ++var19) {
            double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.numberOfBlocks;
            double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.numberOfBlocks;
            double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.numberOfBlocks;
            double var26 = var2.nextDouble() * (double)this.numberOfBlocks / 16.0;
            double var28 = (double)(MathHelper.sin((float)((float)var19 * (float)Math.PI / (float)this.numberOfBlocks)) + 1.0f) * var26 + 1.0;
            double var30 = (double)(MathHelper.sin((float)((float)var19 * (float)Math.PI / (float)this.numberOfBlocks)) + 1.0f) * var26 + 1.0;
            int var32 = MathHelper.floor_double((double)(var20 - var28 / 2.0));
            int var33 = MathHelper.floor_double((double)(var22 - var30 / 2.0));
            int var34 = MathHelper.floor_double((double)(var24 - var28 / 2.0));
            int var35 = MathHelper.floor_double((double)(var20 + var28 / 2.0));
            int var36 = MathHelper.floor_double((double)(var22 + var30 / 2.0));
            int var37 = MathHelper.floor_double((double)(var24 + var28 / 2.0));
            for (int var38 = var32; var38 <= var35; ++var38) {
                double var39 = ((double)var38 + 0.5 - var20) / (var28 / 2.0);
                if (var39 * var39 >= 1.0) continue;
                for (int var41 = var33; var41 <= var36; ++var41) {
                    double var42 = ((double)var41 + 0.5 - var22) / (var30 / 2.0);
                    if (var39 * var39 + var42 * var42 >= 1.0) continue;
                    for (int var44 = var34; var44 <= var37; ++var44) {
                        double var45 = ((double)var44 + 0.5 - var24) / (var28 / 2.0);
                        if (var39 * var39 + var42 * var42 + var45 * var45 >= 1.0 || var1.getBlock(var38, var41, var44) != TwilightBlocks.mortumGrass) continue;
                        var1.setBlock(var38, var41, var44, TwilightBlocks.twilightStone);
                    }
                }
            }
        }
        return true;
    }
}

