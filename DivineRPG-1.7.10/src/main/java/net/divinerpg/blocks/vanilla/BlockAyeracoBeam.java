/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoBeam;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAyeracoBeam
extends BlockContainer {
    private String tex;

    public BlockAyeracoBeam(String name, String tex) {
        super(Material.cactus);
        this.setBlockTextureName("divinerpg:beam" + tex);
        this.setBlockName(name);
        this.setCreativeTab(null);
        this.setResistance(1000000.0f);
        this.tex = tex;
        GameRegistry.registerBlock((Block)this, (String)name);
    }

    public int getRenderType() {
        return -1;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 200.0f, 0.0f);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAyeracoBeam(new ResourceLocation("divinerpg:textures/blocks/beam" + this.tex + ".png"));
    }
}

