/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.handler;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import ru.simplemc.senergetics.common.block.standalone.BlockChunkLoader;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityChunkLoader;

public class ChunkLoadingCallbackHandler
implements ForgeChunkManager.OrderedLoadingCallback {
    public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount) {
        ArrayList<ForgeChunkManager.Ticket> ticketsValidated = new ArrayList<ForgeChunkManager.Ticket>();
        tickets.forEach(ticket -> {
            int posZ;
            int posY;
            int posX = ticket.getModData().getInteger("chunkLoaderPosX");
            Block block = world.getBlock(posX, posY = ticket.getModData().getInteger("chunkLoaderPosY"), posZ = ticket.getModData().getInteger("chunkLoaderPosZ"));
            if (block instanceof BlockChunkLoader) {
                ticketsValidated.add((ForgeChunkManager.Ticket)ticket);
            }
        });
        return ticketsValidated;
    }

    public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
        tickets.forEach(ticket -> {
            int posZ;
            int posY;
            int posX = ticket.getModData().getInteger("chunkLoaderPosX");
            TileEntity tileEntity = world.getTileEntity(posX, posY = ticket.getModData().getInteger("chunkLoaderPosY"), posZ = ticket.getModData().getInteger("chunkLoaderPosZ"));
            if (tileEntity instanceof TileEntityChunkLoader) {
                TileEntityChunkLoader tileEntityChunkLoader = (TileEntityChunkLoader)tileEntity;
                tileEntityChunkLoader.setChunkLoaderTicket((ForgeChunkManager.Ticket)ticket);
                tileEntityChunkLoader.forceChunks((ForgeChunkManager.Ticket)ticket);
            }
        });
    }
}

