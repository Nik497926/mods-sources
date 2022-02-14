/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vethea;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityDreamLamp;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDreamLamp
extends BlockContainer {
    private static boolean shouldDropContent = true;

    public BlockDreamLamp(String name, boolean on) {
        super(Material.glass);
        this.setStepSound(soundTypeGlass);
        if (on) {
            this.setCreativeTab(null);
            this.setLightLevel(1.0f);
        } else {
            this.setCreativeTab(DivineRPGTabs.vethea);
        }
        this.setBlockTextureName("divinerpg:" + name);
        this.setBlockName("dreamLamp");
        this.setHardness(1.0f);
        LangRegistry.addBlock((Block)this);
        GameRegistry.registerBlock((Block)this, (String)name);
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float j, float k, float f) {
        TileEntityDreamLamp te = (TileEntityDreamLamp)w.getTileEntity(x, y, z);
        if (te != null && !p.isSneaking()) {
            if (!w.isRemote) {
                p.openGui((Object)DivineRPG.instance, GuiHandler.dreamLamp, w, x, y, z);
            }
            return true;
        }
        return false;
    }

    public static void updateState(boolean active, World w, int x, int y, int z) {
        TileEntity tileentity = w.getTileEntity(x, y, z);
        shouldDropContent = false;
        if (active) {
            w.setBlock(x, y, z, VetheaBlocks.dreamLampOn);
        } else {
            w.setBlock(x, y, z, VetheaBlocks.dreamLamp);
        }
        if (tileentity != null) {
            tileentity.validate();
            w.setTileEntity(x, y, z, tileentity);
        }
        shouldDropContent = true;
    }

    public TileEntity createNewTileEntity(World w, int meta) {
        return new TileEntityDreamLamp();
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(VetheaBlocks.dreamLamp));
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityDreamLamp && ((IInventory)te).getStackInSlot(0) != null) {
            drops.add(((IInventory)te).getStackInSlot(0));
        }
        return drops;
    }

    public void breakBlock(World w, int x, int y, int z, Block broken, int meta) {
        TileEntity te = w.getTileEntity(x, y, z);
        if (shouldDropContent && te instanceof TileEntityDreamLamp && ((IInventory)te).getStackInSlot(0) != null) {
            this.dropBlockAsItem(w, x, y, z, ((IInventory)te).getStackInSlot(0));
        }
    }
}

