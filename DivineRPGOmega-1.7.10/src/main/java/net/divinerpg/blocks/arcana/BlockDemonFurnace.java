/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDemonFurnace;
import net.divinerpg.blocks.base.BlockModFurnace;
import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDemonFurnace
extends BlockModFurnace {
    @SideOnly(value=Side.CLIENT)
    protected IIcon side;
    @SideOnly(value=Side.CLIENT)
    protected IIcon top;
    @SideOnly(value=Side.CLIENT)
    protected IIcon front;

    public BlockDemonFurnace(String name, boolean act) {
        super(name, act, GuiHandler.demon);
        this.setBlockTextureName("stone");
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityDemonFurnace();
    }

    public static void updateFurnaceBlockState(boolean act, World par1World, int par2, int par3, int par4) {
        int m = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        keepInventory = true;
        if (act) {
            par1World.setBlock(par2, par3, par4, ArcanaBlocks.demonFurnaceOn);
            m |= 8;
        } else {
            par1World.setBlock(par2, par3, par4, ArcanaBlocks.demonFurnace);
            m &= 0xFFFFFFF7;
        }
        keepInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, m, 2);
        if (tileentity != null) {
            tileentity.validate();
            par1World.setTileEntity(par2, par3, par4, tileentity);
        }
    }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float j, float k, float f) {
        TileEntityInfiniteFurnace furnace = (TileEntityInfiniteFurnace)w.getTileEntity(x, y, z);
        if (furnace != null && !p.isSneaking()) {
            if (!w.isRemote) {
                p.openGui((Object)DivineRPG.instance, GuiHandler.demon, w, x, y, z);
                p.triggerAchievement((StatBase)DivineRPGAchievements.totalDemonization);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
        int meta = ((MathHelper.floor_double((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3) + 1) % 4;
        int i1 = w.getBlockMetadata(x, y, z);
        if (meta == 0) {
            w.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
        if (meta == 1) {
            w.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
        if (meta == 2) {
            w.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (meta == 3) {
            w.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return -1;
    }
}

