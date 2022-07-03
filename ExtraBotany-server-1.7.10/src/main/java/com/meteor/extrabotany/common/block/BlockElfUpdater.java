/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileBlockElfUpdater;
import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockElfUpdater
extends BlockContainer {
    protected BlockElfUpdater(int a1) {
        super(Material.rock);
        this.setCreativeTab(ExtraBotany.tabExtraBotany);
        this.setBlockName("awakeelfupdater");
        this.setBlockTextureName("ExtraBotania:awakeelfupdater");
        this.setHardness(3.0f);
        GameRegistry.registerBlock((Block)this, (String)"awakeelfupdater");
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
        TileBlockElfUpdater object = (TileBlockElfUpdater)world.getTileEntity(x, y, z);
        return object == null ? null : (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN) ? null : (BlockElfUpdater.func_149953_o(world, x, y, z) ? null : (world.getBlock(x - 1, y, z) == this && (world.isSideSolid(x - 1, y + 1, z, ForgeDirection.DOWN) || BlockElfUpdater.func_149953_o(world, x - 1, y, z)) ? null : (world.getBlock(x + 1, y, z) == this && (world.isSideSolid(x + 1, y + 1, z, ForgeDirection.DOWN) || BlockElfUpdater.func_149953_o(world, x + 1, y, z)) ? null : (world.getBlock(x, y, z - 1) == this && (world.isSideSolid(x, y + 1, z - 1, ForgeDirection.DOWN) || BlockElfUpdater.func_149953_o(world, x, y, z - 1)) ? null : (world.getBlock(x, y, z + 1) == this && (world.isSideSolid(x, y + 1, z + 1, ForgeDirection.DOWN) || BlockElfUpdater.func_149953_o(world, x, y, z + 1)) ? null : object))))));
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var1, float var2, float var3, float var4) {
        if (world.isRemote) {
            return true;
        }
        player.addChatComponentMessage((IChatComponent)new ChatComponentText("\u00a7c\u0414\u0430\u043d\u043d\u044b\u0439 \u0431\u043b\u043e\u043a \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442"));
        EntityItem elf = new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(ModItems.elfirium, 4));
        world.spawnEntityInWorld((Entity)elf);
        EntityItem res = new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(ModItems.material, 4, 9));
        world.spawnEntityInWorld((Entity)res);
        EntityItem mem = new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(ModItems.awakearmcontrol));
        world.spawnEntityInWorld((Entity)mem);
        world.setBlockToAir(x, y, z);
        return false;
    }

    private static boolean func_149953_o(World p_149953_0_, int p_149953_1_, int p_149953_2_, int p_149953_3_) {
        for (Object entity : p_149953_0_.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)p_149953_1_, (double)(p_149953_2_ + 1), (double)p_149953_3_, (double)(p_149953_1_ + 1), (double)(p_149953_2_ + 2), (double)(p_149953_3_ + 1)))) {
            EntityOcelot entityocelot = (EntityOcelot)entity;
            if (!entityocelot.isSitting()) continue;
            return true;
        }
        return false;
    }

    public TileEntity createNewTileEntity(World world, int i) {
        TileBlockElfUpdater tile = new TileBlockElfUpdater();
        return tile;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int f) {
        TileBlockElfUpdater te = (TileBlockElfUpdater)world.getTileEntity(x, y, z);
        for (int i = 0; i < te.getSizeInventory(); ++i) {
            if (te.getStackInSlot(i) == null) continue;
            world.spawnEntityInWorld((Entity)new EntityItem(world, (double)x, (double)y, (double)z, te.getStackInSlot(i)));
        }
    }
}

