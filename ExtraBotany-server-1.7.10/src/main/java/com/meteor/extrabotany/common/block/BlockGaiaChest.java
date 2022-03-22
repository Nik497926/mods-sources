/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGaiaChest
extends BlockContainer {
    private final Random field_149955_b = new Random();
    public final int field_149956_a;
    public static Map<String, String> dataWhoPlacer = new HashMap<String, String>();
    private IInventory maininv;
    private Integer xCoord;
    private Integer yCoord;
    private Integer zCoord;
    private Boolean close = false;

    protected BlockGaiaChest(int p_i45397_1_) {
        super(Material.iron);
        this.field_149956_a = p_i45397_1_;
        this.setCreativeTab(ExtraBotany.tabExtraBotany);
        this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
        this.setBlockTextureName("ExtraBotania:gaiachest");
        this.setBlockName("gaiachest");
        this.setHardness(3.0f);
        GameRegistry.registerBlock(this, "gaiachest");
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 668;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if (world.getBlock(x, y, z - 1) == this) {
            this.setBlockBounds(0.0625f, 0.0f, 0.0f, 0.9375f, 0.875f, 0.9375f);
        } else if (world.getBlock(x, y, z + 1) == this) {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 1.0f);
        } else if (world.getBlock(x - 1, y, z) == this) {
            this.setBlockBounds(0.0f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
        } else if (world.getBlock(x + 1, y, z) == this) {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 1.0f, 0.875f, 0.9375f);
        } else {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
        }
    }

    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
        this.func_149954_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
        Block block = p_149726_1_.getBlock(p_149726_2_, p_149726_3_, p_149726_4_ - 1);
        Block block1 = p_149726_1_.getBlock(p_149726_2_, p_149726_3_, p_149726_4_ + 1);
        Block block2 = p_149726_1_.getBlock(p_149726_2_ - 1, p_149726_3_, p_149726_4_);
        Block block3 = p_149726_1_.getBlock(p_149726_2_ + 1, p_149726_3_, p_149726_4_);
        if (block == this) {
            this.func_149954_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_ - 1);
        }
        if (block1 == this) {
            this.func_149954_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_ + 1);
        }
        if (block2 == this) {
            this.func_149954_e(p_149726_1_, p_149726_2_ - 1, p_149726_3_, p_149726_4_);
        }
        if (block3 == this) {
            this.func_149954_e(p_149726_1_, p_149726_2_ + 1, p_149726_3_, p_149726_4_);
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        Block block = world.getBlock(x, y, z - 1);
        Block block1 = world.getBlock(x, y, z + 1);
        Block block2 = world.getBlock(x - 1, y, z);
        Block block3 = world.getBlock(x + 1, y, z);
        int b0 = 0;
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0f / 360.0f) + 0.5) & 3;
        if (l == 0) {
            b0 = 2;
        }
        if (l == 1) {
            b0 = 5;
        }
        if (l == 2) {
            b0 = 3;
        }
        if (l == 3) {
            b0 = 4;
        }
        EntityPlayer player = (EntityPlayer)p_149689_5_;
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileGaiaChest) {
            TileGaiaChest tet = (TileGaiaChest)te;
            if (player != null) {
                tet.setPlayer(player.getDisplayName());
            }
        }
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        if (block != this && block1 != this && block2 != this && block3 != this) {
            world.setBlockMetadataWithNotify(x, y, z, b0, 3);
        } else {
            if (!(block != this && block1 != this || b0 != 4 && b0 != 5)) {
                if (block == this) {
                    world.setBlockMetadataWithNotify(x, y, z - 1, b0, 3);
                } else {
                    world.setBlockMetadataWithNotify(x, y, z + 1, b0, 3);
                }
                world.setBlockMetadataWithNotify(x, y, z, b0, 3);
            }
            if (!(block2 != this && block3 != this || b0 != 2 && b0 != 3)) {
                if (block2 == this) {
                    world.setBlockMetadataWithNotify(x - 1, y, z, b0, 3);
                } else {
                    world.setBlockMetadataWithNotify(x + 1, y, z, b0, 3);
                }
                world.setBlockMetadataWithNotify(x, y, z, b0, 3);
            }
        }
        if (p_149689_6_.hasDisplayName()) {
            ((TileGaiaChest)world.getTileEntity(x, y, z)).func_145976_a(p_149689_6_.getDisplayName());
        }
    }

    public void func_149954_e(World p_149954_1_, int p_149954_2_, int p_149954_3_, int p_149954_4_) {
        if (!p_149954_1_.isRemote) {
            int b0;
            Block block = p_149954_1_.getBlock(p_149954_2_, p_149954_3_, p_149954_4_ - 1);
            Block block1 = p_149954_1_.getBlock(p_149954_2_, p_149954_3_, p_149954_4_ + 1);
            Block block2 = p_149954_1_.getBlock(p_149954_2_ - 1, p_149954_3_, p_149954_4_);
            Block block3 = p_149954_1_.getBlock(p_149954_2_ + 1, p_149954_3_, p_149954_4_);
            boolean flag = true;
            if (block != this && block1 != this) {
                if (block2 != this && block3 != this) {
                    b0 = 3;
                    if (block.func_149730_j() && !block1.func_149730_j()) {
                        b0 = 3;
                    }
                    if (block1.func_149730_j() && !block.func_149730_j()) {
                        b0 = 2;
                    }
                    if (block2.func_149730_j() && !block3.func_149730_j()) {
                        b0 = 5;
                    }
                    if (block3.func_149730_j() && !block2.func_149730_j()) {
                        b0 = 4;
                    }
                } else {
                    int l = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
                    Block block4 = p_149954_1_.getBlock(l, p_149954_3_, p_149954_4_ - 1);
                    int i1 = block2 == this ? p_149954_2_ - 1 : p_149954_2_ + 1;
                    Block block5 = p_149954_1_.getBlock(i1, p_149954_3_, p_149954_4_ + 1);
                    b0 = 3;
                    boolean flag1 = true;
                    int j1 = block2 == this ? p_149954_1_.getBlockMetadata(p_149954_2_ - 1, p_149954_3_, p_149954_4_) : p_149954_1_.getBlockMetadata(p_149954_2_ + 1, p_149954_3_, p_149954_4_);
                    if (j1 == 2) {
                        b0 = 2;
                    }
                    if ((block.func_149730_j() || block4.func_149730_j()) && !block1.func_149730_j() && !block5.func_149730_j()) {
                        b0 = 3;
                    }
                    if ((block1.func_149730_j() || block5.func_149730_j()) && !block.func_149730_j() && !block4.func_149730_j()) {
                        b0 = 2;
                    }
                }
            } else {
                int l = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
                Block block4 = p_149954_1_.getBlock(p_149954_2_ - 1, p_149954_3_, l);
                int i1 = block == this ? p_149954_4_ - 1 : p_149954_4_ + 1;
                Block block5 = p_149954_1_.getBlock(p_149954_2_ + 1, p_149954_3_, i1);
                b0 = 5;
                boolean flag1 = true;
                int j1 = block == this ? p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_ - 1) : p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_ + 1);
                if (j1 == 4) {
                    b0 = 4;
                }
                if ((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j() && !block5.func_149730_j()) {
                    b0 = 5;
                }
                if ((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j() && !block4.func_149730_j()) {
                    b0 = 4;
                }
            }
            if (p_149954_1_.getBlockMetadata(p_149954_2_, p_149954_3_, p_149954_4_) != 15) {
                p_149954_1_.setBlockMetadataWithNotify(p_149954_2_, p_149954_3_, p_149954_4_, b0, 3);
            }
        }
    }

    private boolean isDoubleChest(World w, int x, int y, int z) {
        return w.getBlock(x, y, z) == this && (w.getBlock(x - 1, y, z) == this || w.getBlock(x + 1, y, z) == this || w.getBlock(x, y, z - 1) == this || w.getBlock(x, y, z + 1) == this);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int fs) {
        TileGaiaChest tileentitychest = (TileGaiaChest)world.getTileEntity(x, y, z);
        if (tileentitychest != null) {
            for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileentitychest.getStackInSlot(i1);
                if (itemstack == null) continue;
                float f = this.field_149955_b.nextFloat() * 0.8f + 0.1f;
                float f1 = this.field_149955_b.nextFloat() * 0.8f + 0.1f;
                float f2 = this.field_149955_b.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int j1 = this.field_149955_b.nextInt(21) + 10;
                    if (j1 > itemstack.stackSize) {
                        j1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= j1;
                    EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                    float f3 = 0.05f;
                    entityitem.motionX = (float)this.field_149955_b.nextGaussian() * f3;
                    entityitem.motionY = (float)this.field_149955_b.nextGaussian() * f3 + 0.2f;
                    entityitem.motionZ = (float)this.field_149955_b.nextGaussian() * f3;
                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }
                    world.spawnEntityInWorld(entityitem);
                }
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, fs);
    }

    public void ClosetChest(Integer i) {
        this.close = i == 0;
    }

    public static void isGenerated(World world, int x, int y, int z) {
        if (world.getTileEntity(x, y, z) instanceof TileGaiaChest) {
            ((TileGaiaChest)world.getTileEntity(x, y, z)).isGenerated();
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        }
        if (world.getBlockMetadata(x, y, z) == 15) {
            ((TileGaiaChest)world.getTileEntity(x, y, z)).generationLoot();
        }
        if (player.isSneaking() && player.inventory.getCurrentItem() != null) {
            ((TileGaiaChest)world.getTileEntity(x, y, z)).activate(player, player.inventory.getCurrentItem());
            return true;
        }
        IInventory iinventory = this.getInv(world, x, y, z);
        if (iinventory != null) {
            this.maininv = iinventory;
            if (player == null) {
                ((TileGaiaChest)world.getTileEntity(x, y, z)).updateInv(iinventory);
            } else {
                player.displayGUIChest(iinventory);
            }
        }
        return true;
    }

    public IInventory getInv(World world, int x, int y, int z) {
        TileGaiaChest object = (TileGaiaChest)world.getTileEntity(x, y, z);
        return object == null ? null : (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN) ? null : (BlockGaiaChest.func_149953_o(world, x, y, z) ? null : (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, ForgeDirection.DOWN) || BlockGaiaChest.func_149953_o(world, x - 1, y, z)) ? null : (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, ForgeDirection.DOWN) || BlockGaiaChest.func_149953_o(world, x + 1, y, z)) ? null : (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, ForgeDirection.DOWN) || BlockGaiaChest.func_149953_o(world, x, y, z - 1)) ? null : (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, ForgeDirection.DOWN) || BlockGaiaChest.func_149953_o(world, x, y, z + 1)) ? null : object))))));
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        TileGaiaChest tileentitychest = new TileGaiaChest();
        return tileentitychest;
    }

    public boolean canProvidePower() {
        return true;
    }

    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        if (!this.canProvidePower()) {
            return 0;
        }
        int i1 = ((TileGaiaChest)p_149709_1_.getTileEntity(p_149709_2_, p_149709_3_, p_149709_4_)).numPlayersUsing;
        return MathHelper.clamp_int(i1, 0, 15);
    }

    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        return p_149748_5_ == 1 ? this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_) : 0;
    }

    private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_) {
        for (Object entity : p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox(p_149953_1_, p_149953_2_ + 1, p_149953_3_, p_149953_1_ + 1, p_149953_2_ + 2, p_149953_3_ + 1))) {
            EntityOcelot entityocelot = (EntityOcelot)entity;
            if (!entityocelot.isSitting()) continue;
            return true;
        }
        return false;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int f) {
        return Container.calcRedstoneFromInventory(this.getInv(world, x, y, z));
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon("planks_oak");
    }
}

