/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSlimeLight
extends BlockMod {
    private final boolean isOn;

    public BlockSlimeLight(boolean on) {
        super(EnumBlockType.GLASS, on ? "slimeLightOn" : "slimeLight", 2.0f);
        this.isOn = on;
        this.setBlockName("slimeLight");
        if (on) {
            this.setLightLevel(1.0f);
            this.setCreativeTab(null);
        }
    }

    public void onBlockAdded(World w, int x, int y, int z) {
        if (!w.isRemote) {
            if (this.isOn && !w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.scheduleBlockUpdate(x, y, z, (Block)this, 4);
            } else if (!this.isOn && w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.setBlock(x, y, z, VanillaBlocks.slimeLightOn, 0, 2);
            }
        }
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block neighbor) {
        if (!w.isRemote) {
            if (this.isOn && !w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.scheduleBlockUpdate(x, y, z, (Block)this, 4);
            } else if (!this.isOn && w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.setBlock(x, y, z, VanillaBlocks.slimeLightOn, 0, 2);
            }
        }
    }

    public void updateTick(World w, int x, int y, int z, Random random) {
        if (!w.isRemote && this.isOn && !w.isBlockIndirectlyGettingPowered(x, y, z)) {
            w.setBlock(x, y, z, VanillaBlocks.slimeLight, 0, 2);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock((Block)VanillaBlocks.slimeLight);
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemFromBlock((Block)VanillaBlocks.slimeLight);
    }

    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(VanillaBlocks.slimeLight);
    }
}

