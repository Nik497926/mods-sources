/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileBlockSpawner;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSpawner
extends Block
implements ITileEntityProvider {
    public BlockSpawner() {
        super(Material.rock);
        this.setHardness(0.0f);
        this.setResistance(10.0f);
        this.setBlockName("odinSpawner");
        GameRegistry.registerBlock(this, "odinSpawner");
    }

    public String getLocalizedName() {
        return Blocks.quartz_block.getLocalizedName();
    }

    public IIcon getIcon(int side, int meta) {
        return Blocks.quartz_block.getIcon(side, meta);
    }

    public float getBlockHardness(World p_149712_1_, int p_149712_2_, int p_149712_3_, int p_149712_4_) {
        return 0.0f;
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileBlockSpawner();
    }
}

