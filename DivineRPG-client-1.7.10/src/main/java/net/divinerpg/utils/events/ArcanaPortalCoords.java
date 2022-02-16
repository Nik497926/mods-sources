/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package net.divinerpg.utils.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ArcanaPortalCoords
implements IExtendedEntityProperties {
    private final EntityPlayer player;
    public static final String NAME = "ArcanaPort";
    public double returnPortalX;
    public double returnPortalY;
    public double returnPortalZ;

    public ArcanaPortalCoords(EntityPlayer player) {
        this.player = player;
    }

    public void saveNBTData(NBTTagCompound n) {
        NBTTagCompound tag = this.player.getEntityData().getCompoundTag("PlayerPersisted");
        tag.setDouble("returnPortalX", this.returnPortalX);
        tag.setDouble("returnPortalY", this.returnPortalY);
        tag.setDouble("returnPortalZ", this.returnPortalZ);
        this.player.getEntityData().setTag("PlayerPersisted", (NBTBase)tag);
    }

    public void loadNBTData(NBTTagCompound n) {
        NBTTagCompound tag = this.player.getEntityData().getCompoundTag("PlayerPersisted");
        if (!tag.hasKey("returnPortalX")) {
            return;
        }
        this.returnPortalX = tag.getDouble("returnPortalX");
        this.returnPortalY = tag.getDouble("returnPortalY");
        this.returnPortalZ = tag.getDouble("returnPortalZ");
        this.player.getEntityData().setTag("PlayerPersisted", (NBTBase)tag);
    }

    public static void addProperties(EntityPlayer player) {
        player.registerExtendedProperties(NAME, (IExtendedEntityProperties)new ArcanaPortalCoords(player));
    }

    public static ArcanaPortalCoords getProperties(EntityPlayer player) {
        return (ArcanaPortalCoords)player.getExtendedProperties(NAME);
    }

    public void setReturnPortalX(double x) {
        this.returnPortalX = x;
    }

    public double getReturnPortalX() {
        return this.returnPortalX;
    }

    public void setReturnPortalY(double y) {
        this.returnPortalY = y;
    }

    public double getReturnPortalY() {
        return this.returnPortalY;
    }

    public void setReturnPortalZ(double z) {
        this.returnPortalZ = z;
    }

    public double getReturnPortalZ() {
        return this.returnPortalZ;
    }

    public void init(Entity entity, World world) {
    }
}

