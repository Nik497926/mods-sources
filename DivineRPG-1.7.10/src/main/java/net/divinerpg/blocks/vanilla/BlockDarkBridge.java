/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockDarkBridge
extends BlockMod {
    private final boolean on;

    public BlockDarkBridge(boolean on) {
        super(EnumBlockType.GLASS, on ? "darkBridgeOn" : "darkBridge", 1.5f);
        this.on = on;
        this.setBlockName("darkBridge");
        if (on) {
            this.setLightLevel(1.0f);
            this.setCreativeTab(null);
            this.setLightOpacity(3);
        }
    }

    public void onBlockAdded(World w, int x, int y, int z) {
        if (!w.isRemote) {
            if (this.on && !w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.scheduleBlockUpdate(x, y, z, (Block)this, 4);
            } else if (!this.on && w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.setBlock(x, y, z, VanillaBlocks.darkBridgeOn, 0, 2);
            }
        }
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block neighbor) {
        if (!w.isRemote) {
            if (this.on && !w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.scheduleBlockUpdate(x, y, z, (Block)this, 4);
            } else if (!this.on && w.isBlockIndirectlyGettingPowered(x, y, z)) {
                w.setBlock(x, y, z, VanillaBlocks.darkBridgeOn, 0, 2);
            }
        }
    }

    public void updateTick(World w, int x, int y, int z, Random random) {
        if (!w.isRemote && this.on && !w.isBlockIndirectlyGettingPowered(x, y, z)) {
            w.setBlock(x, y, z, VanillaBlocks.darkBridge, 0, 2);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock((Block)VanillaBlocks.darkBridge);
    }

    public boolean isOpaqueCube() {
        return this.on;
    }

    public boolean renderAsNormalBlock() {
        return this.on;
    }

    public int getRenderBlockPass() {
        return !this.on ? 1 : 0;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return !this.on ? null : AxisAlignedBB.getBoundingBox((double)((double)x + this.minX), (double)((double)y + this.minY), (double)((double)z + this.minZ), (double)((double)x + this.maxX), (double)((double)y + this.maxY), (double)((double)z + this.maxZ));
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemFromBlock((Block)VanillaBlocks.darkBridge);
    }

    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(VanillaBlocks.darkBridge);
    }
}

