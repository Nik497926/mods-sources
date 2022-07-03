/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.handler;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PropertyHandler
implements IExtendedEntityProperties {
    private static EntityPlayer player;
    public static final String EXT_PROP_NAME = "Shield";
    public static final String TAG_CURRENT_SHIELD = "CurrentShield";
    public static final String TAG_MAX_SHIELD = "MaxShield";
    public static float currentShield;
    public static float maxShield;

    public static void setPlayer(EntityPlayer p) {
        player = p;
    }

    public static void setShieldAmount(float shield, EntityPlayer p) {
        PropertyHandler.setPlayer(p);
    }

    public static float getShieldAmount(EntityPlayer p) {
        PropertyHandler.setPlayer(p);
        return 0.0f;
    }

    public static void addShieldAmount(float shield, EntityPlayer p) {
        PropertyHandler.setPlayer(p);
        player.getDataWatcher().updateObject(30, (Object)((int)Math.min(PropertyHandler.getShieldAmount(player) + shield, PropertyHandler.getMaxShieldAmount(player))));
    }

    public static float getMaxShieldAmount(EntityPlayer p) {
        PropertyHandler.setPlayer(p);
        player.getDataWatcher().updateObject(29, (Object)((int)(player.getMaxHealth() + (float)ConfigHandler.extraShieldAmount)));
        return 0.0f;
    }

    public PropertyHandler(EntityPlayer player) {
        player.getDataWatcher().addObject(29, (Object)0);
        player.getDataWatcher().addObject(30, (Object)0);
        currentShield = 0.0f;
        maxShield = 20.0f;
    }

    public static final void register(EntityPlayer player) {
        player.registerExtendedProperties(EXT_PROP_NAME, (IExtendedEntityProperties)new PropertyHandler(player));
    }

    public static final PropertyHandler get(EntityPlayer player) {
        return (PropertyHandler)player.getExtendedProperties(EXT_PROP_NAME);
    }

    public void saveNBTData(NBTTagCompound compound) {
    }

    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
    }

    public void init(Entity entity, World world) {
    }

    private static String getSaveKey(EntityPlayer player) {
        return player.getDisplayName() + ":Shield";
    }
}

