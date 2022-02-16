/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockArcanaFrame
extends BlockMod {
    protected Block portal;

    public BlockArcanaFrame(String name, Block portal) {
        super(EnumBlockType.ROCK, name, 5.0f);
        this.portal = portal;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        int var6 = ((MathHelper.floor_double((double)((double)(entityLiving.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3) + 2) % 4;
        world.setBlockMetadataWithNotify(x, y, z, var6, 3);
        if (entityLiving instanceof EntityPlayerMP && entityLiving.dimension != ConfigurationHelper.arcana) {
            boolean validFrame = true;
            int startX = x;
            int startZ = z;
            if (world.getBlock(startX - 1, y, startZ) == this || world.getBlock(startX + 1, y, startZ) == this) {
                while (world.getBlock(startX - 1, y, startZ) == this) {
                    --startX;
                }
                if (world.getBlock(--startX, y, startZ - 1) == this) {
                    startZ -= 4;
                }
            } else if (world.getBlock(startX, y, startZ - 1) == this || world.getBlock(startX, y, startZ + 1) == this) {
                while (world.getBlock(startX, y, startZ - 1) == this) {
                    --startZ;
                }
                if (world.getBlock(startX - 1, y, --startZ) == this) {
                    startX -= 4;
                }
            }
            block2: for (int scanZ = startZ; scanZ < startZ + 5; ++scanZ) {
                int scanX;
                if (scanZ == startZ || scanZ == startZ + 4) {
                    for (scanX = startX + 1; scanX < startX + 4; ++scanX) {
                        if (world.getBlock(scanX, y, scanZ) == this) continue;
                        validFrame = false;
                        break block2;
                    }
                    continue;
                }
                for (scanX = startX; scanX < startX + 5; ++scanX) {
                    if (scanX == startX || scanX == startX + 4) {
                        if (world.getBlock(scanX, y, scanZ) == this) continue;
                        validFrame = false;
                        break block2;
                    }
                    if (world.getBlock(scanX, y, scanZ) == Blocks.air) continue;
                    validFrame = false;
                    break block2;
                }
            }
            if (validFrame) {
                world.setBlock(startX + 1, y, startZ + 1, this.portal);
                world.setBlock(startX + 2, y, startZ + 1, this.portal);
                world.setBlock(startX + 3, y, startZ + 1, this.portal);
                world.setBlock(startX + 1, y, startZ + 2, this.portal);
                world.setBlock(startX + 2, y, startZ + 2, this.portal);
                world.setBlock(startX + 3, y, startZ + 2, this.portal);
                world.setBlock(startX + 1, y, startZ + 3, this.portal);
                world.setBlock(startX + 2, y, startZ + 3, this.portal);
                world.setBlock(startX + 3, y, startZ + 3, this.portal);
            }
        }
    }
}

