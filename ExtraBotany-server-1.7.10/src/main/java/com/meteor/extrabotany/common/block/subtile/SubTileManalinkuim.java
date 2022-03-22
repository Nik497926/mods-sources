/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.SubTileEntity;

public class SubTileManalinkuim
extends SubTileEntity {
    public static final int RANGE = 24;
    private static final String TAG_MANA = "mana";
    private static final String TAG_COLLECTOR_X = "collectorX";
    private static final String TAG_COLLECTOR_Y = "collectorY";
    private static final String TAG_COLLECTOR_Z = "collectorZ";
    private static final String TAG_POOL_X = "poolX";
    private static final String TAG_POOL_Y = "poolY";
    private static final String TAG_POOL_Z = "poolZ";
    protected int mana;
    public int redstoneSignal = 0;
    int sizeLastCheck = -1;
    protected TileEntity linkedCollector = null;
    TileEntity linkedPool = null;
    public int knownMana = -1;
    ChunkCoordinates cachedCollectorCoordinates = null;
    ChunkCoordinates cachedPoolCoordinates = null;

    public LexiconEntry getEntry() {
        return LexiconModData.manalinkuim;
    }

    public void onUpdate() {
        super.onUpdate();
        this.redstoneSignal = 0;
        for (ForgeDirection manaToRemove : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide = this.supertile.getWorldObj().getIndirectPowerLevelTo(this.supertile.xCoord + manaToRemove.offsetX, this.supertile.yCoord + manaToRemove.offsetY, this.supertile.zCoord + manaToRemove.offsetZ, manaToRemove.ordinal());
            this.redstoneSignal = Math.max(this.redstoneSignal, redstoneSide);
        }
        this.linkCollector();
        this.linkPool();
        if (this.redstoneSignal == 0) {
            if (this.linkedPool != null) {
                IManaPool var6 = (IManaPool)this.linkedPool;
                int manaInPool = var6.getCurrentMana();
                int color = this.getMaxMana() - this.mana;
                int var9 = Math.min(color, manaInPool);
                var6.recieveMana(-var9);
                this.addMana(var9);
            }
            this.emptyManaIntoCollector();
        }
        if (this.supertile.getWorldObj().isRemote) {
            double var7 = 1.0 - (double)this.mana / (double)this.getMaxMana() / 3.5;
            Color var8 = new Color(this.getColor());
            if (Math.random() > var7) {
                BotaniaAPI.internalHandler.sparkleFX(this.supertile.getWorldObj(), (double)this.supertile.xCoord + 0.3 + Math.random() * 0.5, (double)this.supertile.yCoord + 0.5 + Math.random() * 0.5, (double)this.supertile.zCoord + 0.3 + Math.random() * 0.5, (float)var8.getRed() / 255.0f, (float)var8.getGreen() / 255.0f, (float)var8.getBlue() / 255.0f, (float)Math.random(), 5);
            }
        }
    }

    public int getColor() {
        return 3364592;
    }

    public int getMaxMana() {
        return 1000;
    }

    public void linkCollector() {
        boolean needsNew = false;
        if (this.linkedCollector == null) {
            needsNew = true;
            if (this.cachedCollectorCoordinates != null) {
                needsNew = false;
                if (this.supertile.getWorldObj().blockExists(this.cachedCollectorCoordinates.posX, this.cachedCollectorCoordinates.posY, this.cachedCollectorCoordinates.posZ)) {
                    needsNew = true;
                    TileEntity network = this.supertile.getWorldObj().getTileEntity(this.cachedCollectorCoordinates.posX, this.cachedCollectorCoordinates.posY, this.cachedCollectorCoordinates.posZ);
                    if (network != null && network instanceof IManaCollector && !network.isInvalid()) {
                        this.linkedCollector = network;
                        needsNew = false;
                    }
                    this.cachedCollectorCoordinates = null;
                }
            }
        } else {
            TileEntity network = this.supertile.getWorldObj().getTileEntity(this.linkedCollector.xCoord, this.linkedCollector.yCoord, this.linkedCollector.zCoord);
            if (network != null && network instanceof IManaCollector) {
                this.linkedCollector = network;
            }
        }
        if (needsNew && this.ticksExisted == 1) {
            IManaNetwork network1 = BotaniaAPI.internalHandler.getManaNetworkInstance();
            int size = network1.getAllCollectorsInWorld(this.supertile.getWorldObj()).size();
            if (BotaniaAPI.internalHandler.shouldForceCheck() || size != this.sizeLastCheck) {
                ChunkCoordinates coords = new ChunkCoordinates(this.supertile.xCoord, this.supertile.yCoord, this.supertile.zCoord);
                this.linkedCollector = network1.getClosestCollector(coords, this.supertile.getWorldObj(), 24);
                this.sizeLastCheck = size;
            }
        }
    }

    public void linkPool() {
        boolean needsNew = false;
        if (this.linkedPool == null) {
            needsNew = true;
            if (this.cachedPoolCoordinates != null) {
                needsNew = false;
                if (this.supertile.getWorldObj().blockExists(this.cachedPoolCoordinates.posX, this.cachedPoolCoordinates.posY, this.cachedPoolCoordinates.posZ)) {
                    needsNew = true;
                    TileEntity network = this.supertile.getWorldObj().getTileEntity(this.cachedPoolCoordinates.posX, this.cachedPoolCoordinates.posY, this.cachedPoolCoordinates.posZ);
                    if (network != null && network instanceof IManaPool && !network.isInvalid()) {
                        this.linkedPool = network;
                        needsNew = false;
                    }
                    this.cachedPoolCoordinates = null;
                }
            }
        } else {
            TileEntity network = this.supertile.getWorldObj().getTileEntity(this.linkedPool.xCoord, this.linkedPool.yCoord, this.linkedPool.zCoord);
            if (network != null && network instanceof IManaPool) {
                this.linkedPool = network;
            }
        }
        if (needsNew && this.ticksExisted == 1) {
            IManaNetwork network1 = BotaniaAPI.internalHandler.getManaNetworkInstance();
            int size = network1.getAllPoolsInWorld(this.supertile.getWorldObj()).size();
            if (BotaniaAPI.internalHandler.shouldForceCheck() || size != this.sizeLastCheck) {
                ChunkCoordinates coords = new ChunkCoordinates(this.supertile.xCoord, this.supertile.yCoord, this.supertile.zCoord);
                this.linkedPool = network1.getClosestPool(coords, this.supertile.getWorldObj(), 24);
                this.sizeLastCheck = size;
            }
        }
    }

    public void addMana(int mana) {
        this.mana = Math.min(this.getMaxMana(), this.mana + mana);
    }

    public void emptyManaIntoCollector() {
        IManaCollector collector;
        if (this.linkedCollector != null && this.isValidBinding() && !(collector = (IManaCollector)this.linkedCollector).isFull() && this.mana > 0) {
            int manaval = Math.min(this.mana, collector.getMaxMana() - collector.getCurrentMana());
            this.mana -= manaval;
            collector.recieveMana(manaval);
        }
    }

    public boolean onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return false;
        }
        if (!player.worldObj.isRemote) {
            this.sync();
        }
        this.knownMana = this.mana;
        player.worldObj.playSoundAtEntity(player, "botania:ding", 0.1f, 1.0f);
        return super.onWanded(player, wand);
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        this.mana = cmp.getInteger(TAG_MANA);
        int x = cmp.getInteger(TAG_COLLECTOR_X);
        int y = cmp.getInteger(TAG_COLLECTOR_Y);
        int z = cmp.getInteger(TAG_COLLECTOR_Z);
        this.cachedCollectorCoordinates = y < 0 ? null : new ChunkCoordinates(x, y, z);
        int px = cmp.getInteger(TAG_POOL_X);
        int py = cmp.getInteger(TAG_POOL_Y);
        int pz = cmp.getInteger(TAG_POOL_Z);
        this.cachedPoolCoordinates = py < 0 ? null : new ChunkCoordinates(px, py, pz);
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        int z;
        int y;
        int x;
        cmp.setInteger(TAG_MANA, this.mana);
        cmp.setInteger("ticksExisted", this.ticksExisted);
        if (this.cachedCollectorCoordinates != null) {
            cmp.setInteger(TAG_COLLECTOR_X, this.cachedCollectorCoordinates.posX);
            cmp.setInteger(TAG_COLLECTOR_Y, this.cachedCollectorCoordinates.posY);
            cmp.setInteger(TAG_COLLECTOR_Z, this.cachedCollectorCoordinates.posZ);
        } else {
            x = this.linkedCollector == null ? 0 : this.linkedCollector.xCoord;
            y = this.linkedCollector == null ? -1 : this.linkedCollector.yCoord;
            z = this.linkedCollector == null ? 0 : this.linkedCollector.zCoord;
            cmp.setInteger(TAG_COLLECTOR_X, x);
            cmp.setInteger(TAG_COLLECTOR_Y, y);
            cmp.setInteger(TAG_COLLECTOR_Z, z);
        }
        if (this.cachedPoolCoordinates != null) {
            cmp.setInteger(TAG_POOL_X, this.cachedPoolCoordinates.posX);
            cmp.setInteger(TAG_POOL_Y, this.cachedPoolCoordinates.posY);
            cmp.setInteger(TAG_POOL_Z, this.cachedPoolCoordinates.posZ);
        } else {
            x = this.linkedPool == null ? 0 : this.linkedPool.xCoord;
            y = this.linkedPool == null ? -1 : this.linkedPool.yCoord;
            z = this.linkedPool == null ? 0 : this.linkedPool.zCoord;
            cmp.setInteger(TAG_POOL_X, x);
            cmp.setInteger(TAG_POOL_Y, y);
            cmp.setInteger(TAG_POOL_Z, z);
        }
    }

    public ChunkCoordinates getBinding() {
        return this.linkedCollector == null ? null : new ChunkCoordinates(this.linkedCollector.xCoord, this.linkedCollector.yCoord, this.linkedCollector.zCoord);
    }

    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        TileEntity tile;
        int range = 24;
        int range1 = range * range;
        double dist = (x - this.supertile.xCoord) * (x - this.supertile.xCoord) + (y - this.supertile.yCoord) * (y - this.supertile.yCoord) + (z - this.supertile.zCoord) * (z - this.supertile.zCoord);
        if ((double)range1 >= dist && (tile = player.worldObj.getTileEntity(x, y, z)) instanceof IManaCollector) {
            this.linkedCollector = tile;
            return true;
        }
        return false;
    }

    public boolean isValidBinding() {
        return this.linkedCollector != null && !this.linkedCollector.isInvalid() && this.supertile.getWorldObj().getTileEntity(this.linkedCollector.xCoord, this.linkedCollector.yCoord, this.linkedCollector.zCoord) == this.linkedCollector;
    }

    public boolean isOvergrowthAffected() {
        return true;
    }
}

