/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockHotSpike
extends BlockMod {
    public BlockHotSpike() {
        super(EnumBlockType.ROCK, "hotSpikeBlock", 3.0f);
    }

    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e) {
        if (e instanceof EntityLivingBase) {
            e.attackEntityFrom(Util.spikeSource, 8.0f);
            e.setFire(10);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        float f = 0.0625f;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
}

