/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.lelyetia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BossClear {
    public static void clear(int x, int y, int z, World w, EntityLivingBase e) {
        EntityPlayer var1 = w.getClosestVulnerablePlayerToEntity((Entity)e, 40.0);
        int count = 0;
        if (var1 == null || var1.getDistanceToEntity((Entity)e) > 40.0f) {
            return;
        }
        if (!w.isRemote) {
            for (int i = x - 10; i < x + 5; ++i) {
                for (int j = y - 3; j < y + 5; ++j) {
                    for (int k = z - 10; k < z + 5; ++k) {
                        if (w.getBlock(i, j, k) == Blocks.bedrock) continue;
                        w.setBlock(i, j, k, Blocks.air);
                        ++count;
                    }
                }
            }
        }
        if (count == 0) {
            e.setPositionAndUpdate(var1.posX, var1.posY, var1.posZ);
        }
    }
}

