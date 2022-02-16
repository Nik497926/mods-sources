/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.divinerpg.blocks.base.tileentity.TileEntityModFurnace;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockModFurnace
extends BlockContainer {
    protected boolean active;
    protected static boolean keepInventory;
    protected Random rand = new Random();
    private int guiID;

    public BlockModFurnace(String name, boolean act, int GuiID) {
        super(Material.rock);
        this.setStepSound(Block.soundTypeStone);
        if (!act) {
            this.setCreativeTab(DivineRPGTabs.blocks);
        }
        this.setBlockName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setHardness(3.5f);
        this.active = act;
        this.guiID = GuiID;
        if (act) {
            this.setLightLevel(0.875f);
        }
    }

    public Item getItemDropped(int i, Random r, int j) {
        return Util.toItem((Block)this);
    }

    public void onBlockAdded(World w, int x, int y, int z) {
        super.onBlockAdded(w, x, y, z);
        if (!w.isRemote) {
            int meta = 3;
            Block block = w.getBlock(x, y, z - 1);
            Block block1 = w.getBlock(x, y, z + 1);
            Block block2 = w.getBlock(x - 1, y, z);
            Block block3 = w.getBlock(x + 1, y, z);
            if (block.func_149730_j() && !block1.func_149730_j()) {
                meta = 3;
            }
            if (block1.func_149730_j() && !block.func_149730_j()) {
                meta = 2;
            }
            if (block2.func_149730_j() && !block3.func_149730_j()) {
                meta = 5;
            }
            if (block3.func_149730_j() && !block2.func_149730_j()) {
                meta = 4;
            }
            w.setBlockMetadataWithNotify(x, y, z, meta, 2);
        }
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float j, float k, float f) {
        TileEntity furnace = w.getTileEntity(x, y, z);
        if (furnace != null && !p.isSneaking()) {
            if (!w.isRemote) {
                p.openGui((Object)DivineRPG.instance, this.guiID, w, x, y, z);
            }
            return true;
        }
        return false;
    }

    public static void updateActiveStates(boolean active, World w, int x, int y, int z) {
        int meta = w.getBlockMetadata(x, y, z);
        TileEntity tileentity = w.getTileEntity(x, y, z);
        keepInventory = true;
        if (active) {
            w.setBlock(x, y, z, IceikaBlocks.coalstoneFurnaceOn);
        } else {
            w.setBlock(x, y, z, IceikaBlocks.coalstoneFurnace);
        }
        keepInventory = false;
        w.setBlockMetadataWithNotify(x, y, z, meta, 2);
        if (tileentity != null) {
            tileentity.validate();
            w.setTileEntity(x, y, z, tileentity);
        }
    }

    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase living, ItemStack item) {
        int meta = MathHelper.floor_double((double)((double)(living.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (meta == 0) {
            w.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (meta == 1) {
            w.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (meta == 2) {
            w.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (meta == 3) {
            w.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (item.hasDisplayName()) {
            ((TileEntityModFurnace)w.getTileEntity(x, y, z)).setCustomInventoryName(item.getDisplayName());
        }
    }

    public void breakBlock(World w, int x, int y, int z, Block b, int meta) {
        if (!keepInventory) {
            TileEntityInfiniteFurnace entity;
            if (w.getTileEntity(x, y, z) instanceof TileEntityModFurnace) {
                TileEntityModFurnace entity2 = (TileEntityModFurnace)w.getTileEntity(x, y, z);
                if (entity2 != null) {
                    for (int i1 = 0; i1 < entity2.getSizeInventory(); ++i1) {
                        ItemStack itemstack = entity2.getStackInSlot(i1);
                        if (itemstack == null) continue;
                        float f = this.rand.nextFloat() * 0.8f + 0.1f;
                        float f1 = this.rand.nextFloat() * 0.8f + 0.1f;
                        float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
                        while (itemstack.stackSize > 0) {
                            int j1 = this.rand.nextInt(21) + 10;
                            if (j1 > itemstack.stackSize) {
                                j1 = itemstack.stackSize;
                            }
                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(w, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }
                            float f3 = 0.05f;
                            entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                            entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2f;
                            entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                            w.spawnEntityInWorld((Entity)entityitem);
                        }
                    }
                    w.func_147453_f(x, y, z, b);
                }
            } else if (w.getTileEntity(x, y, z) instanceof TileEntityInfiniteFurnace && (entity = (TileEntityInfiniteFurnace)w.getTileEntity(x, y, z)) != null) {
                for (int i1 = 0; i1 < entity.getSizeInventory(); ++i1) {
                    ItemStack itemstack = entity.getStackInSlot(i1);
                    if (itemstack == null) continue;
                    float f = this.rand.nextFloat() * 0.8f + 0.1f;
                    float f1 = this.rand.nextFloat() * 0.8f + 0.1f;
                    float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
                    while (itemstack.stackSize > 0) {
                        int j1 = this.rand.nextInt(21) + 10;
                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(w, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        float f3 = 0.05f;
                        entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                        entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2f;
                        entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                        w.spawnEntityInWorld((Entity)entityitem);
                    }
                }
                w.func_147453_f(x, y, z, b);
            }
        }
        super.breakBlock(w, x, y, z, b, meta);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World w, int x, int y, int z, Random r) {
        int meta = w.getBlockMetadata(x, y, z);
        if (this.active || meta >= 8) {
            float f = (float)x + 0.5f;
            float f1 = (float)y + 0.0f + r.nextFloat() * 6.0f / 16.0f;
            float f2 = (float)z + 0.5f;
            float f3 = 0.52f;
            float f4 = r.nextFloat() * 0.6f - 0.3f;
            if (meta == 4 || meta == 9) {
                w.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            } else if (meta == 5 || meta == 11) {
                w.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            } else if (meta == 2 || meta == 8) {
                w.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
            } else if (meta == 3 || meta == 10) {
                w.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
                w.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
            }
        }
    }

    public Item getItem(World w, int x, int y, int z) {
        return Util.toItem((Block)this);
    }
}

