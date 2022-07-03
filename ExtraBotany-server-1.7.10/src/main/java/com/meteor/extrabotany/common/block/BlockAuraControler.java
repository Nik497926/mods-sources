/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileAuraControler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.blocks.ItemJarNode;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;

public class BlockAuraControler
extends BlockContainer
implements IWandHUD,
IWandable {
    protected BlockAuraControler() {
        super(Material.rock);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setStepSound(soundTypeStone);
        this.setBlockName("auracontroler");
        this.setBlockTextureName("ExtraBotania:auracontroler");
        GameRegistry.registerBlock((Block)this, (String)"auracontroler");
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
    }

    public IInventory getInv(World world, int x, int y, int z) {
        TileAuraControler object = (TileAuraControler)world.getTileEntity(x, y, z);
        return object == null ? null : (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN) ? null : (BlockAuraControler.func_149953_o(world, x, y, z) ? null : (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, ForgeDirection.DOWN) || BlockAuraControler.func_149953_o(world, x - 1, y, z)) ? null : (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, ForgeDirection.DOWN) || BlockAuraControler.func_149953_o(world, x + 1, y, z)) ? null : (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, ForgeDirection.DOWN) || BlockAuraControler.func_149953_o(world, x, y, z - 1)) ? null : (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, ForgeDirection.DOWN) || BlockAuraControler.func_149953_o(world, x, y, z + 1)) ? null : object))))));
    }

    private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_) {
        for (Object entity : p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1)))) {
            EntityOcelot entityocelot = (EntityOcelot)entity;
            if (!entityocelot.isSitting()) continue;
            return true;
        }
        return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var1, float var2, float var3, float var4) {
        if (player != null) {
            boolean needItem;
            TileAuraControler tile = (TileAuraControler)world.getTileEntity(x, y, z);
            boolean bl = needItem = tile.getStackInSlot(0) == null;
            if (player.inventory.getStackInSlot(player.inventory.currentItem) == null) {
                if (needItem) {
                    return true;
                }
                ItemStack var0 = tile.getStackInSlot(0);
                player.inventory.setInventorySlotContents(player.inventory.currentItem, var0);
                tile.setInventorySlotContents(0, null);
                tile.markDirty();
                return true;
            }
            if (needItem) {
                ItemStack var0 = player.inventory.getStackInSlot(player.inventory.currentItem);
                if (var0.getItem() instanceof ItemJarNode) {
                    tile.setInventorySlotContents(0, var0);
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    tile.markDirty();
                }
                return true;
            }
            return true;
        }
        return false;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int f) {
        TileAuraControler te = (TileAuraControler)world.getTileEntity(x, y, z);
        if (te.getStackInSlot(0) != null) {
            world.spawnEntityInWorld((Entity)new EntityItem(world, (double)x, (double)y, (double)z, te.getStackInSlot(0)));
        }
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileAuraControler();
    }

    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int i, int i1, int i2) {
        TileEntity te = world.getTileEntity(i, i1, i2);
        if (te instanceof TileAuraControler) {
            ((TileAuraControler)te).renderHUD(minecraft, scaledResolution);
        }
    }

    public boolean onUsedByWand(EntityPlayer entityPlayer, ItemStack itemStack, World world, int i, int i1, int i2, int i3) {
        TileAuraControler te = (TileAuraControler)world.getTileEntity(i, i1, i2);
        te.onUsedByWand(entityPlayer, itemStack, world, i, i1, i2, i3);
        return true;
    }
}

