/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.twilight;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.divinerpg.blocks.twilight.TileEntityEdenChest;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEdenChest
extends BlockContainer {
    private Random rand = new Random();

    public BlockEdenChest(String name) {
        super(Material.rock);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setStepSound(soundTypePiston);
        this.setBlockTextureName("divinerpg:edenBlock");
        this.setBlockName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setHardness(15.0f);
        this.setResistance(1000000.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f1, float f2) {
        if (w.isRemote) {
            return true;
        }
        IInventory iinventory = this.getInventory(w, x, y, z);
        if (iinventory != null) {
            p.displayGUIChest(iinventory);
        }
        return true;
    }

    public void breakBlock(World world, int i, int j, int k, Block i1, int i2) {
        TileEntityEdenChest tileentitychest = (TileEntityEdenChest)world.getTileEntity(i, j, k);
        if (tileentitychest != null) {
            Object itemstack = null;
            this.dropContent(0, tileentitychest, world, tileentitychest.xCoord, tileentitychest.yCoord, tileentitychest.zCoord);
        }
        super.breakBlock(world, i, j, k, i1, i2);
    }

    public void dropContent(int newSize, IInventory chest, World world, int xCoord, int yCoord, int zCoord) {
        for (int l = newSize; l < chest.getSizeInventory(); ++l) {
            ItemStack itemstack = chest.getStackInSlot(l);
            if (itemstack == null) continue;
            float f = this.rand.nextFloat() * 0.8f + 0.1f;
            float f1 = this.rand.nextFloat() * 0.8f + 0.1f;
            float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
            while (itemstack.stackSize > 0) {
                int i1 = this.rand.nextInt(21) + 10;
                if (i1 > itemstack.stackSize) {
                    i1 = itemstack.stackSize;
                }
                itemstack.stackSize -= i1;
                EntityItem entityitem = new EntityItem(world, (double)((float)xCoord + f), (double)((float)(yCoord + (newSize > 0 ? 1 : 0)) + f1), (double)((float)zCoord + f2), new ItemStack(itemstack.getItem(), i1, itemstack.getItemDamage()));
                float f3 = 0.05f;
                entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2f;
                entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                if (itemstack.hasTagCompound()) {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                }
                world.spawnEntityInWorld((Entity)entityitem);
            }
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z) {
        this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
    }

    private IInventory getInventory(World w, int x, int y, int z) {
        TileEntityEdenChest o = (TileEntityEdenChest)w.getTileEntity(x, y, z);
        return o;
    }

    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack i) {
        int l = MathHelper.floor_double((double)((double)(e.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        int i1 = w.getBlockMetadata(x, y, z);
        ++l;
        if ((l %= 4) == 0) {
            w.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (l == 1) {
            w.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 2) {
            w.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 3) {
            w.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }

    public int getRenderType() {
        return -1;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityEdenChest();
    }
}

