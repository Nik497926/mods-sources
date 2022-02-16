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
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.iceika;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.divinerpg.blocks.iceika.tileentity.TileEntityPresentBox;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPresentBox
extends BlockContainer {
    private Random rand = new Random();

    public BlockPresentBox() {
        super(Material.wood);
        String name = "presentBox";
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setStepSound(soundTypePiston);
        this.setBlockTextureName("divinerpg:" + name);
        this.setBlockName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.setHardness(2.0f);
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

    public void breakBlock(World w, int x, int y, int z, Block b, int i) {
        TileEntityPresentBox items = (TileEntityPresentBox)w.getTileEntity(x, y, z);
        if (items != null) {
            ItemStack itemstack = null;
            for (int i1 = 0; i1 < items.getSizeInventory(); ++i1) {
                itemstack = items.getStackInSlot(i1);
                if (itemstack == null) continue;
                float f = this.rand.nextFloat() * 0.8f + 0.1f;
                float f1 = this.rand.nextFloat() * 0.8f + 0.1f;
                float f3 = 0.05f;
                float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int j1 = this.rand.nextInt(21) + 10;
                    if (j1 > itemstack.stackSize) {
                        j1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= j1;
                    EntityItem entityitem = new EntityItem(w, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                    entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                    entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2f;
                    entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                    w.spawnEntityInWorld((Entity)entityitem);
                }
            }
            w.func_147453_f(x, y, z, b);
        }
        super.breakBlock(w, x, y, z, b, i);
    }

    private IInventory getInventory(World w, int x, int y, int z) {
        TileEntityPresentBox o = (TileEntityPresentBox)w.getTileEntity(x, y, z);
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
        return new TileEntityPresentBox();
    }
}

