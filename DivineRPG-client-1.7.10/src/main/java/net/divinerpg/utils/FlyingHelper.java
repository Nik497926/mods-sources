/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package net.divinerpg.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class FlyingHelper
implements IExtendedEntityProperties {
    private final EntityPlayer player;
    public static final String NAME = "FlyingProps";
    public boolean couldFly;

    public FlyingHelper(EntityPlayer player) {
        this.player = player;
    }

    public void saveNBTData(NBTTagCompound n) {
    }

    public void loadNBTData(NBTTagCompound n) {
    }

    public static void addProperties(EntityPlayer player) {
        player.registerExtendedProperties(NAME, (IExtendedEntityProperties)new FlyingHelper(player));
    }

    public static FlyingHelper getProperties(EntityPlayer player) {
        return (FlyingHelper)player.getExtendedProperties(NAME);
    }

    public void init(Entity entity, World world) {
    }
}

