/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockMachineBase;
import ru.simplemc.senergetics.common.inventory.container.ContainerChunkLoader;
import ru.simplemc.senergetics.common.inventory.container.ContainerSolarPanel;
import ru.simplemc.senergetics.common.inventory.container.ContainerSpawner;
import ru.simplemc.senergetics.common.tileentity.electricity.TileEntitySolarPanel;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityChunkLoader;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;

public class GuiHandler
implements IGuiHandler {
    public static final int SILENT_SPAWNER_GUI_ID = 0;
    public static final int CHUNK_LOADER_GUI_ID = 1;
    public static final int SOLAR_PANEL_GUI_ID = 2;

    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == 0) {
            TileEntitySpawner tileEntitySpawner = (TileEntitySpawner)world.getTileEntity(x, y, z);
            return new ContainerSpawner(player.inventory, tileEntitySpawner);
        }
        if (id == 1) {
            TileEntityChunkLoader chunkLoader = (TileEntityChunkLoader)world.getTileEntity(x, y, z);
            return new ContainerChunkLoader(player.inventory, chunkLoader.getInventory());
        }
        if (id == 2) {
            TileEntitySolarPanel solarPanel = (TileEntitySolarPanel)world.getTileEntity(x, y, z);
            return new ContainerSolarPanel(solarPanel, player);
        }
        return this.handleBlockMachineGui(id, player, world, x, y, z, true);
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return this.handleBlockMachineGui(id, player, world, x, y, z, false);
    }

    private Object handleBlockMachineGui(int guiId, EntityPlayer player, World world, int posX, int posY, int posZ, boolean serverSide) {
        Block block = world.getBlock(posX, posY, posZ);
        if (block instanceof BlockMachineBase) {
            BlockMachineBase blockMachine = (BlockMachineBase)block;
            if (blockMachine.getGuiId() != guiId) {
                return null;
            }
            return blockMachine.createContainer(world, posX, posY, posZ, player);
        }
        return null;
    }
}

