/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.SubTileEntity;

public abstract class SubTileFunctional
extends SubTileEntity {
    public static final int RANGE = 10;
    private static final String TAG_MANA = "mana";
    private static final String TAG_POOL_X = "poolX";
    private static final String TAG_POOL_Y = "poolY";
    private static final String TAG_POOL_Z = "poolZ";
    public int mana;
    public int redstoneSignal = 0;
    int sizeLastCheck = -1;
    TileEntity linkedPool = null;
    public int knownMana = -1;
    ChunkCoordinates cachedPoolCoordinates = null;

    public boolean acceptsRedstone() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public void onUpdate() {
        super.onUpdate();
        this.linkPool();
        int manaInPool;
        int manaMissing;
        if (this.linkedPool != null && this.isValidBinding()) {
            IManaPool pool = (IManaPool)this.linkedPool;
            manaInPool = pool.getCurrentMana();
            manaMissing = this.getMaxMana() - this.mana;
            int manaToRemove = Math.min(manaMissing, manaInPool);
            pool.recieveMana(-manaToRemove);
            this.addMana(manaToRemove);
        }
        if (this.acceptsRedstone()) {
            this.redstoneSignal = 0;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                int redstoneSide = this.supertile.getWorldObj().getIndirectPowerLevelTo(this.supertile.xCoord + dir.offsetX, this.supertile.yCoord + dir.offsetY, this.supertile.zCoord + dir.offsetZ, dir.ordinal());
                this.redstoneSignal = Math.max(this.redstoneSignal, redstoneSide);
            }
        }
        if (this.supertile.getWorldObj().isRemote) {
            double particleChance = 1.0 - (double)this.mana / (double)this.getMaxMana() / 3.5;
            Color color = new Color(this.getColor());
            if (Math.random() > particleChance) {
                BotaniaAPI.internalHandler.sparkleFX(this.supertile.getWorldObj(), (double)this.supertile.xCoord + 0.3 + Math.random() * 0.5, (double)this.supertile.yCoord + 0.5 + Math.random() * 0.5, (double)this.supertile.zCoord + 0.3 + Math.random() * 0.5, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)Math.random(), 5);
            }
        }
    }

    public void linkPool() {
        TileEntity tileAt;
        boolean needsNew = false;
        if (this.linkedPool == null) {
            needsNew = true;
            if (this.cachedPoolCoordinates != null) {
                needsNew = false;
                if (this.supertile.getWorldObj().blockExists(this.cachedPoolCoordinates.posX, this.cachedPoolCoordinates.posY, this.cachedPoolCoordinates.posZ)) {
                    needsNew = true;
                    tileAt = this.supertile.getWorldObj().getTileEntity(this.cachedPoolCoordinates.posX, this.cachedPoolCoordinates.posY, this.cachedPoolCoordinates.posZ);
                    if (tileAt != null && tileAt instanceof IManaPool && !tileAt.isInvalid()) {
                        this.linkedPool = tileAt;
                        needsNew = false;
                    }
                    this.cachedPoolCoordinates = null;
                }
            }
        } else {
            tileAt = this.supertile.getWorldObj().getTileEntity(this.linkedPool.xCoord, this.linkedPool.yCoord, this.linkedPool.zCoord);
            if (tileAt != null && tileAt instanceof IManaPool) {
                this.linkedPool = tileAt;
            }
        }
        if (needsNew && this.ticksExisted == 1) {
            IManaNetwork network = BotaniaAPI.internalHandler.getManaNetworkInstance();
            int size = network.getAllPoolsInWorld(this.supertile.getWorldObj()).size();
            if (BotaniaAPI.internalHandler.shouldForceCheck() || size != this.sizeLastCheck) {
                ChunkCoordinates coords = new ChunkCoordinates(this.supertile.xCoord, this.supertile.yCoord, this.supertile.zCoord);
                this.linkedPool = network.getClosestPool(coords, this.supertile.getWorldObj(), 10);
                this.sizeLastCheck = size;
            }
        }
    }

    public void linkToForcefully(TileEntity pool) {
        this.linkedPool = pool;
    }

    public void addMana(int mana) {
        this.mana = Math.min(this.getMaxMana(), this.mana + mana);
    }

    public boolean onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return false;
        }
        this.knownMana = this.mana;
        player.worldObj.playSoundAtEntity((Entity)player, "botania:ding", 0.1f, 1.0f);
        return super.onWanded(player, wand);
    }

    public int getMaxMana() {
        return 20;
    }

    public int getColor() {
        return 0xFFFFFF;
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        this.mana = cmp.getInteger(TAG_MANA);
        int x = cmp.getInteger(TAG_POOL_X);
        int y = cmp.getInteger(TAG_POOL_Y);
        int z = cmp.getInteger(TAG_POOL_Z);
        this.cachedPoolCoordinates = y < 0 ? null : new ChunkCoordinates(x, y, z);
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        cmp.setInteger(TAG_MANA, this.mana);
        if (this.cachedPoolCoordinates != null) {
            cmp.setInteger(TAG_POOL_X, this.cachedPoolCoordinates.posX);
            cmp.setInteger(TAG_POOL_Y, this.cachedPoolCoordinates.posY);
            cmp.setInteger(TAG_POOL_Z, this.cachedPoolCoordinates.posZ);
        } else {
            int x = this.linkedPool == null ? 0 : this.linkedPool.xCoord;
            int y = this.linkedPool == null ? -1 : this.linkedPool.yCoord;
            int z = this.linkedPool == null ? 0 : this.linkedPool.zCoord;
            cmp.setInteger(TAG_POOL_X, x);
            cmp.setInteger(TAG_POOL_Y, y);
            cmp.setInteger(TAG_POOL_Z, z);
        }
    }

    public ChunkCoordinates getBinding() {
        if (this.linkedPool == null) {
            return null;
        }
        return new ChunkCoordinates(this.linkedPool.xCoord, this.linkedPool.yCoord, this.linkedPool.zCoord);
    }

    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        TileEntity tile;
        int range = 10;
        double dist = (x - this.supertile.xCoord) * (x - this.supertile.xCoord) + (y - this.supertile.yCoord) * (y - this.supertile.yCoord) + (z - this.supertile.zCoord) * (z - this.supertile.zCoord);
        if ((double)(range *= range) >= dist && (tile = player.worldObj.getTileEntity(x, y, z)) instanceof IManaPool) {
            this.linkedPool = tile;
            return true;
        }
        return false;
    }

    public boolean isValidBinding() {
        return this.linkedPool != null && !this.linkedPool.isInvalid() && this.supertile.getWorldObj().getTileEntity(this.linkedPool.xCoord, this.linkedPool.yCoord, this.linkedPool.zCoord) == this.linkedPool;
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        String name = StatCollector.translateToLocal((String)("tile.botania:flower." + this.getUnlocalizedName() + ".name"));
        int color = this.getColor();
        BotaniaAPI.internalHandler.drawComplexManaHUD(color, this.knownMana, this.getMaxMana(), name, res, BotaniaAPI.internalHandler.getBindDisplayForFlowerType((SubTileEntity)this), this.isValidBinding());
    }
}

