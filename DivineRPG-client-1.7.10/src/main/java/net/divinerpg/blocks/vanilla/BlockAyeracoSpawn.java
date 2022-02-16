/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoSpawn;
import net.divinerpg.utils.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAyeracoSpawn
extends BlockContainer {
    public BlockAyeracoSpawn() {
        super(Material.rock);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0f);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        String name = "ayeracoSpawn";
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        LangRegistry.addBlock((Block)this);
        GameRegistry.registerBlock((Block)this, (String)name);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityAyeracoSpawn();
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }
}

