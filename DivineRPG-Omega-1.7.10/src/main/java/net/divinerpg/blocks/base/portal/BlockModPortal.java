/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBreakable
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemMonsterPlacer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package net.divinerpg.blocks.base.portal;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockModFire;
import net.divinerpg.dimensions.base.DivineTeleporter;
import net.divinerpg.entities.fx.EntityEdenPortalFX;
import net.divinerpg.entities.fx.EntityMortumPortalFX;
import net.divinerpg.entities.fx.EntitySkythernPortalFX;
import net.divinerpg.entities.fx.EntityWildwoodPortalFX;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockModPortal
extends BlockBreakable {
    public static final int[][] sides = new int[][]{new int[0], {3, 1}, {2, 0}};
    protected String field_149995_b;
    protected int dimensionID;
    protected Block fireBlock;
    protected Block blockFrame;
    private Random rand = new Random();

    public BlockModPortal(String name, int dimensionID, Block fireBlock, Block blockFrame) {
        super("divinerpg:" + name, Material.portal, false);
        this.field_149995_b = name;
        this.dimensionID = dimensionID;
        this.fireBlock = fireBlock;
        this.blockFrame = blockFrame;
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        this.setCreativeTab(null);
        this.setTickRandomly(true);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setBlockUnbreakable();
        ((BlockModFire)fireBlock).addPortal(this);
        this.setLightLevel(0.8f);
    }

    public void onEntityCollidedWithBlock(World world, int xPos, int yPos, int zPos, Entity entity) {
        if (entity.ridingEntity == null && entity.riddenByEntity == null) {
            if (entity instanceof EntityPlayerMP) {
                EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
                thePlayer.addExperienceLevel(0);
                if (thePlayer.timeUntilPortal <= 0) {
                    if (thePlayer.dimension != this.dimensionID) {
                        thePlayer.addExperienceLevel(0);
                        thePlayer.timeUntilPortal = 100;
                        thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, this.dimensionID, (Teleporter)new DivineTeleporter(thePlayer.mcServer.worldServerForDimension(this.dimensionID), this.dimensionID, (Block)this, this.blockFrame));
                        if (thePlayer.dimension == ConfigurationHelper.iceika) {
                            thePlayer.triggerAchievement((StatBase)DivineRPGAchievements.frozenLand);
                        }
                        if (thePlayer.dimension == ConfigurationHelper.mortum) {
                            thePlayer.triggerAchievement((StatBase)DivineRPGAchievements.darkAnotherDay);
                        }
                        if (thePlayer.dimension == ConfigurationHelper.eden) {
                            thePlayer.triggerAchievement((StatBase)DivineRPGAchievements.possibilities);
                        }
                    } else {
                        thePlayer.timeUntilPortal = 100;
                        thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, (Teleporter)new DivineTeleporter(thePlayer.mcServer.worldServerForDimension(0), 0, (Block)this, this.blockFrame));
                    }
                }
            } else {
                entity.timeUntilPortal = 100;
            }
        }
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("doMobSpawning") && rand.nextInt(2000) < world.difficultySetting.getDifficultyId()) {
            Entity entity;
            int l;
            for (l = y; !World.doesBlockHaveSolidTopSurface((IBlockAccess)world, (int)x, (int)y, (int)z) && l > 0; --l) {
            }
            if (l > 0 && !world.getBlock(x, l + 1, z).isNormalCube() && (entity = ItemMonsterPlacer.spawnCreature((World)world, (int)57, (double)((double)x + 0.5), (double)((double)l + 1.1), (double)((double)z + 0.5))) != null) {
                entity.timeUntilPortal = entity.getPortalCooldown();
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int xPos, int yPos, int zPos) {
        int meta = this.getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));
        if (meta == 0) {
            int n = meta = blockAccess.getBlock(xPos - 1, yPos, zPos) != this && blockAccess.getBlock(xPos + 1, yPos, zPos) != this ? 2 : 1;
            if (blockAccess instanceof World && !((World)blockAccess).isRemote) {
                ((World)blockAccess).setBlockMetadataWithNotify(xPos, yPos, zPos, meta, 2);
            }
        }
        float f = 0.125f;
        float f1 = 0.125f;
        if (meta == 1) {
            f = 0.5f;
        }
        if (meta == 2) {
            f1 = 0.5f;
        }
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f1, 0.5f + f, 1.0f, 0.5f + f1);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int size1 = 0;
        int size2 = 1;
        if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this) {
            size1 = 1;
            size2 = 0;
        }
        int i1 = y;
        while (world.getBlock(x, i1 - 1, z) == this) {
            --i1;
        }
        if (world.getBlock(x, i1 - 1, z) != this.blockFrame) {
            world.setBlockToAir(x, y, z);
        } else {
            int j1;
            for (j1 = 1; j1 < 4 && world.getBlock(x, i1 + j1, z) == this; ++j1) {
            }
            if (j1 == 3 && world.getBlock(x, i1 + j1, z) == this.blockFrame) {
                boolean flag = world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this;
                boolean flag1 = world.getBlock(x, y, z - 1) == this || world.getBlock(x, y, z + 1) == this;
                boolean bl = flag1;
                if (flag && flag1) {
                    world.setBlockToAir(x, y, z);
                } else if (!(world.getBlock(x + size1, y, z + size2) == this.blockFrame && world.getBlock(x - size1, y, z - size2) == this || world.getBlock(x - size1, y, z - size2) == this.blockFrame && world.getBlock(x + size1, y, z + size2) == this)) {
                    world.setBlockToAir(x, y, z);
                }
            } else {
                world.setBlockToAir(x, y, z);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int xPos, int yPos, int zPos, int side) {
        int i1 = 0;
        if (blockAccess.getBlock(xPos, yPos, zPos) == this && ((i1 = this.getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos))) == 0 || i1 == 1 && side != 3 && side != 2 || i1 == 2 && side != 5 && side != 4)) {
            return false;
        }
        boolean flag = blockAccess.getBlock(xPos - 1, yPos, zPos) == this && blockAccess.getBlock(xPos - 2, yPos, zPos) != this;
        boolean flag1 = blockAccess.getBlock(xPos + 1, yPos, zPos) == this && blockAccess.getBlock(xPos + 2, yPos, zPos) != this;
        boolean flag2 = blockAccess.getBlock(xPos, yPos, zPos - 1) == this && blockAccess.getBlock(xPos, yPos, zPos - 2) != this;
        boolean flag3 = blockAccess.getBlock(xPos, yPos, zPos + 1) == this && blockAccess.getBlock(xPos, yPos, zPos + 2) != this;
        boolean flag4 = flag || flag1 || i1 == 1;
        boolean flag5 = flag2 || flag3 || i1 == 2;
        boolean bl = flag5;
        return flag4 && side == 4 || flag4 && side == 5 || flag5 && side == 2 || flag5 && side == 3;
    }

    public int quantityDropped(Random rand) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int xPos, int yPos, int zPos, Random rand) {
        for (int l = 0; l < 4; ++l) {
            double d0 = (double) ((float) xPos + rand.nextFloat());
            double d1 = (double) ((float) yPos + rand.nextFloat());
            double d2 = (double) ((float) zPos + rand.nextFloat());

            int i1 = rand.nextInt(2) * 2 - 1;

            double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;

            if (world.getBlock(xPos - 1, yPos, zPos) != this && world.getBlock(xPos + 1, yPos, zPos) != this) {
                d0 = (double) xPos + 0.5D + 0.25D * (double) i1;
                d3 = (double) (rand.nextFloat() * 2.0F * (float) i1);
            } else {
                d2 = (double) zPos + 0.5D + 0.25D * (double) i1;
                d5 = (double) (rand.nextFloat() * 2.0F * (float) i1);
            }
            EntityFX var20 = this == TwilightBlocks.edenPortal ? new EntityEdenPortalFX(world, d0, d1, d2, d3, d4, d5) : (this == TwilightBlocks.wildwoodPortal ? new EntityWildwoodPortalFX(world, d0, d1, d2, d3, d4, d5) : (this == TwilightBlocks.apalachiaPortal ? new EntityPortalFX(world, d0, d1, d2, d3, d4, d5) : (this == TwilightBlocks.skythernPortal ? new EntitySkythernPortalFX(world, d0, d1, d2, d3, d4, d5) : (this == TwilightBlocks.mortumPortal ? new EntityMortumPortalFX(world, d0, d1, d2, d3, d4, d5) : (this == IceikaBlocks.iceikaPortal ? new EntitySkythernPortalFX(world, d0, d1, d2, d3, d4, d5) : null)))));
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
        }
    }

    public int getMeta(int meta) {
        return meta & 3;
    }

    public boolean tryCreatePortal(World world, int x, int y, int z) {
        int j;
        int i;
        LogHelper.debug("Trying to create portal");
        int size1 = 0;
        int size2 = 0;
        if (world.getBlock(x - 1, y, z) == this.blockFrame || world.getBlock(x + 1, y, z) == this.blockFrame) {
            size1 = 1;
        }
        if (world.getBlock(x, y, z - 1) == this.blockFrame || world.getBlock(x, y, z + 1) == this.blockFrame) {
            size2 = 1;
        }
        if (size1 == size2) {
            return false;
        }
        if (world.isAirBlock(x - size1, y, z - size2)) {
            x -= size1;
            z -= size2;
        }
        for (i = -1; i <= 2; ++i) {
            for (j = -1; j <= 3; ++j) {
                boolean flag = i == -1 || i == 2 || j == -1 || j == 3;
                boolean bl = flag;
                if (!(i != -1 && i != 2 || j != -1 && j != 3)) continue;
                Block b1 = world.getBlock(x + size1 * i, y + j, z + size2 * i);
                boolean isAir = world.isAirBlock(x + size1 * i, y + j, z + size2 * i);
                if (flag) {
                    if (b1 == this.blockFrame) continue;
                } else if (isAir || b1 == this.fireBlock) continue;
                return false;
            }
        }
        LogHelper.debug("Creating Portal");
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 3; ++j) {
                world.setBlock(x + size1 * i, y + j, z + size2 * i, (Block)this, 0, 2);
            }
        }
        return true;
    }

    public static void sendEntityToDimension(Entity entity, int dimId, Teleporter tp) {
        if (!entity.worldObj.isRemote && !entity.isDead) {
            entity.worldObj.theProfiler.startSection("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            int j = entity.dimension;
            WorldServer worldserver = minecraftserver.worldServerForDimension(j);
            WorldServer worldserver1 = minecraftserver.worldServerForDimension(dimId);
            entity.dimension = dimId;
            if (j == dimId && dimId != 0) {
                worldserver1 = minecraftserver.worldServerForDimension(0);
                entity.dimension = 0;
            }
            entity.worldObj.removeEntity(entity);
            entity.isDead = false;
            minecraftserver.getConfigurationManager().transferEntityToWorld(entity, j, worldserver, worldserver1, tp);
            Entity newEntity = EntityList.createEntityByName((String)EntityList.getEntityString((Entity)entity), (World)worldserver1);
            if (newEntity != null) {
                newEntity.copyDataFrom(entity, true);
                if (j == dimId && dimId != 0) {
                    ChunkCoordinates chunkcoordinates = worldserver1.getSpawnPoint();
                    chunkcoordinates.posY = entity.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
                    newEntity.setLocationAndAngles((double)chunkcoordinates.posX, (double)chunkcoordinates.posY, (double)chunkcoordinates.posZ + 10.0, newEntity.rotationYaw, newEntity.rotationPitch);
                }
                worldserver1.spawnEntityInWorld(newEntity);
            }
            entity.isDead = true;
            entity.worldObj.theProfiler.endSection();
            worldserver.resetUpdateEntityTick();
            worldserver1.resetUpdateEntityTick();
            entity.worldObj.theProfiler.endSection();
        }
    }
}

