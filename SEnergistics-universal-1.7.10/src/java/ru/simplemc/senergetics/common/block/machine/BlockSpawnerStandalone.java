/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.machine.BlockSpawner;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;

public class BlockSpawnerStandalone
extends BlockSpawner {
    public BlockSpawnerStandalone() {
        super("block_silent_spawner_standalone");
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        this.textureTop = register.registerIcon("senergetics:spawner/block_silent_spawner_4");
        this.textureTopActive = register.registerIcon("senergetics:spawner/block_silent_spawner_5");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        TileEntitySpawner tileEntitySpawner = new TileEntitySpawner();
        tileEntitySpawner.setStandalone(true);
        return tileEntitySpawner;
    }
}

