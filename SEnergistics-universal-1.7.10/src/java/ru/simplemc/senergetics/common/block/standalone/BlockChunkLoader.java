/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.standalone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.block.BlockContainerBaseFacing;
import ru.simplemc.senergetics.common.item.machine.ItemBlockChunkLoader;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityChunkLoader;
import ru.simplemc.senergetics.util.InventoryUtils;

public class BlockChunkLoader
extends BlockContainerBaseFacing {
    public BlockChunkLoader() {
        super(Material.iron, "block_chunkloader", ItemBlockChunkLoader.class);
        this.setStepSound(soundTypeMetal);
        this.setHardness(1.0f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking() && world.getTileEntity(x, y, z) instanceof TileEntityChunkLoader) {
            if (!world.isRemote) {
                player.openGui((Object)SEnergetics.getInstance(), 1, world, x, y, z);
            }
            return true;
        }
        return super.onBlockActivated(world, x, y, y, player, side, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityChunkLoader();
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityChunkLoader && InventoryUtils.dropItemsFromInventory(((TileEntityChunkLoader)tileEntity).getInventory(), world, x, y, z)) {
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }
}

