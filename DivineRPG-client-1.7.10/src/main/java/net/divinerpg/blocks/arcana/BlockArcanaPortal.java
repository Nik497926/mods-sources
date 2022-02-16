/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.dimensions.arcana.TeleporterArcana;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class BlockArcanaPortal
extends BlockMod {
    private int firetick;
    private int firemax = 200;

    public BlockArcanaPortal(String name) {
        super(EnumBlockType.PORTAL, name, 5.0f);
        this.setLightLevel(1.0f);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0f);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean isReplaceable(IBlockAccess w, int x, int y, int z) {
        return false;
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity) {
    }

    public int quantityDropped(Random par1Random) {
        return 0;
    }

    public void dropBlockAsItemWithChance(World worldIn, int x, int y, int z, int meta, float chance, int fortune) {
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
            EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
            if (thePlayer.timeUntilPortal > 0) {
                thePlayer.timeUntilPortal = 10;
            } else if (thePlayer.dimension != ConfigurationHelper.arcana) {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, ConfigurationHelper.arcana, (Teleporter)new TeleporterArcana(thePlayer.mcServer.worldServerForDimension(ConfigurationHelper.arcana)));
                thePlayer.triggerAchievement((StatBase)DivineRPGAchievements.whatLiesWithin);
            } else {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, (Teleporter)new TeleporterArcana(thePlayer.mcServer.worldServerForDimension(0)));
            }
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int startX = x;
        int startZ = z;
        if (block == ArcanaBlocks.arcanaPortalFrame) {
            while (world.getBlock(startX - 1, y, startZ) == this) {
                --startX;
            }
            while (world.getBlock(startX, y, startZ - 1) == this) {
                --startZ;
            }
            for (int scanZ = startZ; scanZ < startZ + 3; ++scanZ) {
                for (int scanX = startX; scanX < startX + 3; ++scanX) {
                    if (world.getBlock(scanX, y, scanZ) != this) continue;
                    world.setBlockToAir(scanX, y, scanZ);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        double distanceX = (float)x + rand.nextFloat();
        double distanceY = (float)y + 0.8f;
        double distanceZ = (float)z + rand.nextFloat();
        world.spawnParticle("smoke", distanceX, distanceY, distanceZ, 0.0, 0.0, 0.0);
    }
}

