/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityExtractor;
import net.divinerpg.blocks.base.BlockModFurnace;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockExtractor
extends BlockModFurnace {
    public BlockExtractor(String name) {
        super(name, false, GuiHandler.extractor);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0f);
        this.setBlockTextureName("stone");
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityExtractor();
    }

    public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }
}

