/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.twilight;

import net.divinerpg.blocks.base.BlockModDoublePlant;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockBrambles
extends BlockModDoublePlant {
    public BlockBrambles(String name, Block grass) {
        super(name, grass);
    }

    public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity entity) {
        if (entity instanceof EntityPlayer) {
            entity.attackEntityFrom(DamageSource.cactus, 6.0f);
        }
    }

    public void harvestBlock(World w, EntityPlayer player, int x, int y, int z, int meta) {
        super.harvestBlock(w, player, x, y, z, meta);
        player.attackEntityFrom(DamageSource.cactus, 1.0f);
    }
}

