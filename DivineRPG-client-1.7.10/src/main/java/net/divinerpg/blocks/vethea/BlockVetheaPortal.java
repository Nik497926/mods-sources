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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vethea;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.dimensions.vethea.TeleporterVetheaToOverworld;
import net.divinerpg.entities.fx.EntitySkythernPortalFX;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class BlockVetheaPortal
extends BlockBreakable {
    public static final int[][] sides = new int[][]{new int[0], {3, 1}, {2, 0}};

    public BlockVetheaPortal() {
        super("divinerpg:vetheaPortal", Material.portal, false);
        String name = "vetheaPortal";
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        this.setCreativeTab(null);
        this.setTickRandomly(true);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setBlockUnbreakable();
        this.setLightLevel(0.8f);
    }

    public void onEntityCollidedWithBlock(World world, int xPos, int yPos, int zPos, Entity entity) {
        if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            if (player.timeUntilPortal > 0) {
                player.timeUntilPortal = 10;
            } else if (player.dimension == ConfigurationHelper.vethea) {
                player.timeUntilPortal = 10;
                player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, (Teleporter)new TeleporterVetheaToOverworld(player.mcServer.worldServerForDimension(0)));
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return null;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int xPos, int yPos, int zPos) {
        int meta = this.getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));
        if (meta == 0) {
            meta = blockAccess.getBlock(xPos - 1, yPos, zPos) != this && blockAccess.getBlock(xPos + 1, yPos, zPos) != this ? 2 : 1;
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

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int xPos, int yPos, int zPos, int side) {
        boolean flag5;
        int i1 = 0;
        if (blockAccess.getBlock(xPos, yPos, zPos) == this && ((i1 = this.getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos))) == 0 || i1 == 1 && side != 3 && side != 2 || i1 == 2 && side != 5 && side != 4)) {
            return false;
        }
        boolean flag = blockAccess.getBlock(xPos - 1, yPos, zPos) == this && blockAccess.getBlock(xPos - 2, yPos, zPos) != this;
        boolean flag1 = blockAccess.getBlock(xPos + 1, yPos, zPos) == this && blockAccess.getBlock(xPos + 2, yPos, zPos) != this;
        boolean flag2 = blockAccess.getBlock(xPos, yPos, zPos - 1) == this && blockAccess.getBlock(xPos, yPos, zPos - 2) != this;
        boolean flag3 = blockAccess.getBlock(xPos, yPos, zPos + 1) == this && blockAccess.getBlock(xPos, yPos, zPos + 2) != this;
        boolean flag4 = flag || flag1 || i1 == 1;
        boolean bl = flag5 = flag2 || flag3 || i1 == 2;
        return flag4 && side == 4 ? true : (flag4 && side == 5 ? true : (flag5 && side == 2 ? true : flag5 && side == 3));
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
            double d0 = (float)xPos + rand.nextFloat();
            double d1 = (float)yPos + rand.nextFloat();
            double d2 = (float)zPos + rand.nextFloat();
            double d3 = 0.0;
            double d4 = 0.0;
            double d5 = 0.0;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5) * 0.5;
            d4 = ((double)rand.nextFloat() - 0.5) * 0.5;
            d5 = ((double)rand.nextFloat() - 0.5) * 0.5;
            if (world.getBlock(xPos - 1, yPos, zPos) != this && world.getBlock(xPos + 1, yPos, zPos) != this) {
                d0 = (double)xPos + 0.5 + 0.25 * (double)i1;
                d3 = rand.nextFloat() * 2.0f * (float)i1;
            } else {
                d2 = (double)zPos + 0.5 + 0.25 * (double)i1;
                d5 = rand.nextFloat() * 2.0f * (float)i1;
            }
            EntitySkythernPortalFX var20 = new EntitySkythernPortalFX(world, d0, d1, d2, d3, d4, d5);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)var20);
        }
    }

    public int getMeta(int meta) {
        return meta & 3;
    }
}

