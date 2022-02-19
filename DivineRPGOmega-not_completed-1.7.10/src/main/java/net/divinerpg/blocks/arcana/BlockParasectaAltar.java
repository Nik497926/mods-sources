/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityParasectaAltar;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockParasectaAltar
extends BlockContainer {
    public BlockParasectaAltar(String name) {
        super(Material.rock);
        this.setBlockName(name);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setBlockUnbreakable();
        this.setResistance(6000000.0f);
        this.setBlockTextureName("stone");
        LangRegistry.addBlock((Block)this);
        GameRegistry.registerBlock((Block)this, (String)name);
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityParasectaAltar();
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

