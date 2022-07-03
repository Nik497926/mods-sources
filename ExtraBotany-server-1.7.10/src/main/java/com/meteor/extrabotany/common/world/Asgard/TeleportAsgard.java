/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world.Asgard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleportAsgard
extends Teleporter {
    public static double[] coord = new double[]{0.5, 33.0, 0.0, 5.0};
    private final WorldServer worldServerInstance;
    private final Random random;
    private final LongHashMap destinationCoordinateCache = new LongHashMap();
    private final List destinationCoordinateKeys = new ArrayList();

    public TeleportAsgard(WorldServer worldserver) {
        super(worldserver);
        this.worldServerInstance = worldserver;
        this.random = new Random(worldserver.getSeed());
    }

    public void placeInPortal(Entity entity, double par2, double par4, double par6, float par8) {
        if (this.worldServerInstance.provider.dimensionId == 0 && entity instanceof EntityPlayer) {
            ChunkCoordinates coords = this.worldServerInstance.getSpawnPoint();
            entity.rotationPitch = 0.0f;
            entity.rotationYaw = 0.0f;
            ChunkCoordinates ch = this.worldServerInstance.getSpawnPoint();
            ((EntityPlayer)entity).setPositionAndUpdate((double)ch.posX + 0.5, (double)ch.posY + 1.6, (double)ch.posZ + 0.5);
        }
        entity.setLocationAndAngles(339.0, 6.0, 773.0, entity.rotationYaw, 0.0f);
        entity.motionZ = 0.0;
        entity.motionY = 0.0;
        entity.motionX = 0.0;
    }

    public boolean placeInExistingPortal(Entity entity, double par2, double par4, double par6, float par8) {
        return true;
    }
}

