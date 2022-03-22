/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.handler;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.core.network.DataReader;
import com.meteor.extrabotany.common.core.network.DataWriter;
import com.meteor.extrabotany.common.core.network.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ShieldHandler
implements IExtendedEntityProperties {
    public static final String identifier = "Shield";
    private Entity entity;
    public static String SHIELD = "shield";
    public static String SHIELD_CURRENT = "currentShield";
    public static String SHIELD_MAX = "maxShield";
    private float currentShield = 0.0f;
    private float maxShield = 20.0f;
    private boolean hasDoneFullSync = false;
    private boolean hasUpdate = false;
    private boolean forcingSync = false;
    private int ticksToSync = 0;
    private final int syncTickDelay = 100;

    public ShieldHandler() {
        this.setupDefaults();
    }

    private void setupDefaults() {
        this.currentShield = 0.0f;
        this.maxShield = 20.0f + (float)ConfigHandler.extraShieldAmount;
    }

    public static ShieldHandler For(EntityLivingBase living) {
        return (ShieldHandler)living.getExtendedProperties(identifier);
    }

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound tags = new NBTTagCompound();
        compound.setTag(SHIELD, tags);
        compound.setFloat(SHIELD_CURRENT, this.currentShield);
        compound.setFloat(SHIELD_MAX, this.maxShield);
    }

    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound tags = compound.getCompoundTag(SHIELD);
        this.currentShield = compound.getFloat(SHIELD_CURRENT);
        this.maxShield = compound.getFloat(SHIELD_MAX);
    }

    public void init(Entity entity, World world) {
        this.entity = entity;
    }

    public boolean HasDoneFullSync() {
        return this.hasDoneFullSync;
    }

    public void setFullSync() {
        this.ticksToSync = 0;
        this.hasUpdate = true;
        this.hasDoneFullSync = true;
        this.forcingSync = true;
    }

    public void setDelayedSync(int delay) {
        this.setFullSync();
        this.ticksToSync = delay;
    }

    public void forceSync() {
        this.forcingSync = true;
        this.ticksToSync = 0;
    }

    public boolean hasUpdate() {
        if (!(this.entity instanceof EntityPlayer) && !this.forcingSync) {
            return false;
        }
        --this.ticksToSync;
        if (this.ticksToSync <= 0) {
            this.getClass();
            this.ticksToSync = 100;
        }
        if (this.hasUpdate) {
            int var10000 = this.ticksToSync;
            this.getClass();
            if (var10000 == 100) {
                boolean var1 = true;
                return var1;
            }
        }
        boolean var1 = false;
        return var1;
    }

    private byte[] getUpdateData() {
        DataWriter writer = new DataWriter();
        writer.add(this.entity.getEntityId());
        writer.add(this.currentShield);
        writer.add(this.maxShield);
        this.hasUpdate = false;
        this.forcingSync = false;
        return writer.generate();
    }

    public boolean handlePacketData(byte[] data) {
        DataReader rdr = new DataReader(data, false);
        int entID = rdr.getInt();
        if (entID != this.entity.getEntityId()) {
            return false;
        }
        this.currentShield = rdr.getFloat();
        this.maxShield = rdr.getFloat();
        return true;
    }

    public void handleExtendedPropertySync() {
        if (!this.HasDoneFullSync()) {
            this.setFullSync();
        }
        if (!this.entity.worldObj.isRemote && this.hasUpdate()) {
            byte[] data = this.getUpdateData();
            NetworkHandler.INSTANCE.sendPacketToAllClientsNear(this.entity.worldObj.provider.dimensionId, this.entity.posX, this.entity.posY, this.entity.posZ, 32.0, (byte)1, data);
        }
    }

    public void setShieldAmount(float shield, EntityPlayer p) {
        this.currentShield = 0.0f;
    }

    public float getShieldAmount(EntityPlayer p) {
        return this.currentShield;
    }

    public void addShieldAmount(float shield, EntityPlayer p) {
        this.currentShield = Math.min(this.currentShield + shield, this.maxShield);
    }

    public float getMaxShieldAmount(EntityPlayer p) {
        return this.maxShield + (float)ConfigHandler.extraShieldAmount;
    }
}

