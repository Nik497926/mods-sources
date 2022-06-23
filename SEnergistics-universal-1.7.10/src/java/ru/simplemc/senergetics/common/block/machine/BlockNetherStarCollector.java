/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBase;
import ru.simplemc.senergetics.common.inventory.container.ContainerNetherStarCollector;
import ru.simplemc.senergetics.common.item.machine.ItemBlockNetherStarCollector;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityNetherStarCollector;

public class BlockNetherStarCollector
extends BlockMachineBase {
    public BlockNetherStarCollector() {
        super("block_nether_star_collector", 12, ItemBlockNetherStarCollector.class);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_front");
        this.frontIconActive = iconRegister.registerIcon(this.getTextureName() + "_front_active");
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        TileEntity tileEntity = blockAccess.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityMachine && ((TileEntityMachine)tileEntity).isActive()) {
            return this.frontIconActive;
        }
        return this.blockIcon;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityNetherStarCollector();
    }

    protected TileEntityNetherStarCollector getTileEntityMachineAt(World world, int posX, int posY, int posZ) {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityNetherStarCollector) {
            return (TileEntityNetherStarCollector)tileEntity;
        }
        return null;
    }

    public ContainerNetherStarCollector createContainer(World world, int posX, int posY, int posZ, EntityPlayer player) {
        TileEntityNetherStarCollector netherStarCollector = this.getTileEntityMachineAt(world, posX, posY, posZ);
        if (netherStarCollector != null) {
            return new ContainerNetherStarCollector(netherStarCollector.getInventory(), player);
        }
        return null;
    }
}

