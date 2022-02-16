/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockStarBridge
extends BlockMod {
    private final boolean powered;

    public BlockStarBridge(String name, boolean powered) {
        super(EnumBlockType.GLASS, name, false, !powered ? DivineRPGTabs.blocks : null);
        this.powered = powered;
        if (powered) {
            this.setLightLevel(1.0f);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return w.getBlock(x, y, z) == ArcanaBlocks.starBridge ? null : super.getCollisionBoundingBoxFromPool(w, x, y, z);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4) {
        if (!var1.isRemote) {
            if (this.powered && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
                var1.scheduleBlockUpdate(var2, var3, var4, (Block)this, 4);
            } else if (!this.powered && var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
                var1.setBlock(var2, var3, var4, ArcanaBlocks.starBridgeOn);
            }
        }
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
        if (!var1.isRemote) {
            if (this.powered && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
                var1.scheduleBlockUpdate(var2, var3, var4, (Block)this, 4);
            } else if (!this.powered && var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
                var1.setBlock(var2, var3, var4, ArcanaBlocks.starBridgeOn);
            }
        }
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        if (!var1.isRemote && this.powered && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
            var1.setBlock(var2, var3, var4, ArcanaBlocks.starBridge);
        }
    }
}

