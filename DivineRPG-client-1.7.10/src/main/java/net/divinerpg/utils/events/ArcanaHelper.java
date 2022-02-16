/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.divinerpg.DivineRPG;
import net.divinerpg.network.MessageArcanaBar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ArcanaHelper
implements IExtendedEntityProperties {
    private final EntityPlayer player;
    public static final String NAME = "Arcana";
    private float barValue;
    public int regenDelay;

    public ArcanaHelper(EntityPlayer player) {
        this.player = player;
    }

    public void saveNBTData(NBTTagCompound n) {
        NBTTagCompound tag = this.player.getEntityData().getCompoundTag("PlayerPersisted");
        tag.setFloat("ArcanaBarValue", this.barValue);
        tag.setInteger("ArcanaRegenDelay", this.regenDelay);
        this.player.getEntityData().setTag("PlayerPersisted", (NBTBase)tag);
    }

    public void loadNBTData(NBTTagCompound n) {
        NBTTagCompound tag = this.player.getEntityData().getCompoundTag("PlayerPersisted");
        if (!tag.hasKey("ArcanaBarValue")) {
            return;
        }
        this.barValue = tag.getFloat("ArcanaBarValue");
        this.regenDelay = tag.getInteger("ArcanaRegenDelay");
        this.player.getEntityData().setTag("PlayerPersisted", (NBTBase)tag);
    }

    public static void addProperties(EntityPlayer player) {
        player.registerExtendedProperties(NAME, (IExtendedEntityProperties)new ArcanaHelper(player));
    }

    public static ArcanaHelper getProperties(EntityPlayer player) {
        return (ArcanaHelper)player.getExtendedProperties(NAME);
    }

    public void updateAllBars() {
        if (this.barValue != 200.0f) {
            this.regen(1.0f);
        } else {
            this.regen(0.0f);
        }
        if (this.barValue >= 200.0f) {
            this.barValue = 200.0f;
        }
        if (this.player instanceof EntityPlayerMP) {
            DivineRPG.network.sendTo((IMessage)new MessageArcanaBar(this.barValue, this.regenDelay == 0), (EntityPlayerMP)this.player);
        }
    }

    public boolean useBar(float amount) {
        if (this.barValue < amount) {
            this.regenDelay = 50;
            return false;
        }
        this.barValue -= amount;
        this.regenDelay = 50;
        if (this.player instanceof EntityPlayerMP) {
            DivineRPG.network.sendTo((IMessage)new MessageArcanaBar(this.barValue, this.regenDelay == 0), (EntityPlayerMP)this.player);
        }
        return true;
    }

    public void regen(float amount) {
        if (this.regenDelay == 0) {
            this.barValue += amount;
        } else {
            --this.regenDelay;
        }
        if (this.player instanceof EntityPlayerMP) {
            DivineRPG.network.sendTo((IMessage)new MessageArcanaBar(this.barValue, this.regenDelay == 0), (EntityPlayerMP)this.player);
        }
    }

    public void forceRegen(float amount) {
        this.barValue += amount;
        if (this.player instanceof EntityPlayerMP) {
            DivineRPG.network.sendTo((IMessage)new MessageArcanaBar(this.barValue, this.regenDelay == 0), (EntityPlayerMP)this.player);
        }
    }

    public float getBarValue() {
        return this.barValue;
    }

    public void setBarValue(float i) {
        this.barValue = i;
        if (this.player instanceof EntityPlayerMP) {
            DivineRPG.network.sendTo((IMessage)new MessageArcanaBar(this.barValue, this.regenDelay == 0), (EntityPlayerMP)this.player);
        }
    }

    public void removeValue(float i) {
        this.regenDelay = 50;
        this.barValue -= i;
        if (this.player instanceof EntityPlayerMP) {
            DivineRPG.network.sendTo((IMessage)new MessageArcanaBar(this.barValue, this.regenDelay == 0), (EntityPlayerMP)this.player);
        }
    }

    public void init(Entity entity, World world) {
    }
}

