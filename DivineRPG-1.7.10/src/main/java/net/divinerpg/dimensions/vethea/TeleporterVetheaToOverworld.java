/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.LongHashMap
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.WorldServer
 */
package net.divinerpg.dimensions.vethea;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterVetheaToOverworld
extends Teleporter {
    private final WorldServer worldServerInstance;
    private final Random random;
    private final LongHashMap destinationCoordinateCache = new LongHashMap();
    private final List destinationCoordinateKeys = new ArrayList();

    public TeleporterVetheaToOverworld(WorldServer par1WorldServer) {
        super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random(par1WorldServer.getSeed());
    }

    public void placeInPortal(EntityPlayer player, double x, double y, double z, float par8) {
        if (this.worldServerInstance.provider.dimensionId == 0) {
            ChunkCoordinates bed = player.getBedLocation(0);
            if (bed == null) {
                bed = this.worldServerInstance.getSpawnPoint();
            }
            for (int i = bed.posY; i < 255; ++i) {
                if (this.worldServerInstance.getBlock(bed.posX, i, bed.posZ) != Blocks.air || this.worldServerInstance.getBlock(bed.posX, i - 1, bed.posZ) == Blocks.air && i != bed.posY) continue;
                player.setPositionAndUpdate((double)bed.posX, (double)i, (double)bed.posZ);
                break;
            }
        }
    }

    public void placeInPortal(Entity entity, double x, double y, double z, float par8) {
        if (entity instanceof EntityPlayer) {
            this.placeInPortal((EntityPlayer)entity, x, y, z, par8);
        }
    }

    public boolean makePortal(Entity par1Entity) {
        return true;
    }

    private static int getRandomIntegerBetween(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) - min;
    }
}

