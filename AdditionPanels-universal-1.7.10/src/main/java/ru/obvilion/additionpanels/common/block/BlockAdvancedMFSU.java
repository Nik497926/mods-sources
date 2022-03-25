package ru.obvilion.additionpanels.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.wiring.*;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.tileentity.TileEntityAdvancedMFSU;
import ru.obvilion.additionpanels.common.utils.ItemStackUtils;

import java.util.Random;

public class BlockAdvancedMFSU extends BlockContainer {

    public BlockAdvancedMFSU() {
        super(Material.iron);
        this.setBlockName("advancedmfsu");
        this.setHardness(1.5F);
        this.setStepSound(soundTypeMetal);
        this.setCreativeTab(AdditionPanels.TAB);
    }
    @GradleSideOnly(GradleSide.SERVER)
    public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        ItemStackUtils.dropItems(tileentity, world);
        world.removeTileEntity(i, j, k);
        super.breakBlock(world, i, j, k, par5, par6);
    }

    @GradleSideOnly(GradleSide.SERVER)
    public Item getItemDropped(int meta, Random random, int fortune) {
        if (ConfigUtil.getBool(MainConfig.get(), "balance/ignoreWrenchRequirement")) {
            return Item.getItemFromBlock(this);
        }
        return Ic2Items.machine.getItem();
    }
    @SideOnly(Side.CLIENT)
    protected String getTextureName() {
        return AdditionPanels.MODID + ":" + this.getUnlocalizedName().replace("tile.", "") ;
    }

    public int damageDropped(int meta) {
        if (ConfigUtil.getBool(MainConfig.get(), "balance/ignoreWrenchRequirement")) {
            return meta;
        }
        return Ic2Items.machine.getItemDamage();
    }


    public int quantityDropped(Random random) {
        return 1;
    }
    @GradleSideOnly(GradleSide.SERVER)
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntityBlock te = (TileEntityBlock)blockAccess.getTileEntity(x, y, z);
        return ((TileEntityAdvancedMFSU)te).isEmittingRedstone() ? 15 : 0;
    }

    public boolean canProvidePower() {
        return true;
    }

    public boolean isNormalCube(IBlockAccess world, int i, int j, int k) {
        return false;
    }

    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
    @GradleSideOnly(GradleSide.SERVER)
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
        if (IC2.platform.isSimulating()) {

            TileEntityBlock te = (TileEntityBlock)world.getTileEntity(x, y, z);
            if (te != null) {
                NBTTagCompound nbttagcompound = StackUtil.getOrCreateNbtData(itemStack);
                ((TileEntityAdvancedMFSU)te).energy = nbttagcompound.getDouble("energy");

                if (entityliving == null) {
                    te.setFacing((short)1);
                } else {
                    int yaw = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                    int pitch = Math.round(entityliving.rotationPitch);
                    if (pitch >= 65) {
                        te.setFacing((short)1);
                    } else if (pitch <= -65) {
                        te.setFacing((short)0);
                    } else {
                        switch(yaw) {
                            case 0:
                                te.setFacing((short)2);
                                break;
                            case 1:
                                te.setFacing((short)5);
                                break;
                            case 2:
                                te.setFacing((short)3);
                                break;
                            case 3:
                                te.setFacing((short)4);
                        }
                    }
                }

            }
        }
    }
    @GradleSideOnly(GradleSide.SERVER)
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3) {
        if (player.isSneaking()) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null) {
            player.openGui(AdditionPanels.instance, 1, world, i, j, k);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return  EnumRarity.common;
    }
    @GradleSideOnly(GradleSide.SERVER)
    public boolean hasComparatorInputOverride() {
        return true;
    }
    @GradleSideOnly(GradleSide.SERVER)
    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        TileEntityBlock te = (TileEntityBlock)world.getTileEntity(x, y, z);
        if (te == null) {
            return 0;
        } else {
            TileEntityElectricBlock teb = (TileEntityElectricBlock)te;
            return (int)Math.round(Util.map(teb.energy, teb.maxStorage, 15.0D));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAdvancedMFSU();
    }
}

