/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vethea;

import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockKarosHeatTile
extends BlockMod {
    public BlockKarosHeatTile() {
        super(EnumBlockType.GLASS, "karosHeatTileRed", false, DivineRPGTabs.vethea);
        this.setTickRandomly(true);
    }

    public void updateTick(World w, int x, int y, int z, Random rand) {
        if (!w.isRemote) {
            w.setBlock(x, y, z, VetheaBlocks.karosHeatTileGreen);
        }
    }

    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode) {
            entity.setFire(5);
            entity.attackEntityFrom(Util.trapSource, 6.0f);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float f = 0.0625f;
        return AxisAlignedBB.getBoundingBox((double)x, (double)y, (double)z, (double)(x + 1), (double)((double)(y + 1) - (double)f), (double)(z + 1));
    }
}

