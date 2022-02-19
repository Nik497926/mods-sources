/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.vanilla.EntityAyeraco;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAyeracoGreen
extends EntityAyeraco {
    private EntityAyeraco aBlue;
    private EntityAyeraco aRed;
    private EntityAyeraco aYellow;
    private EntityAyeraco aPurple;
    private String blueUUID;
    private String redUUID;
    private String yellowUUID;
    private String purpleUUID;

    public EntityAyeracoGreen(World par1World) {
        super(par1World, "Green");
    }

    public void initOthers(EntityAyeraco par2, EntityAyeraco par3, EntityAyeraco par4, EntityAyeraco par5) {
        this.aBlue = par2;
        this.aRed = par3;
        this.aYellow = par4;
        this.aPurple = par5;
    }

    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        this.worldObj.setBlock(this.beamX, this.beamY, this.beamZ, Blocks.air);
    }

    @Override
    protected boolean canBlockProjectiles() {
        return true;
    }

    @Override
    protected boolean canTeleport() {
        return this.aPurple != null && this.aPurple.abilityActive();
    }

    protected void dropRareDrop(int par1) {
        this.dropItem(VanillaItemsWeapons.greenEnderSword, 1);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            if (this.aBlue == null && this.blueUUID != null) {
                this.aBlue = (EntityAyeraco)Util.findEntityByUUID(this.blueUUID, this.worldObj);
                this.blueUUID = null;
            }
            if (this.aRed == null && this.redUUID != null) {
                this.aRed = (EntityAyeraco)Util.findEntityByUUID(this.redUUID, this.worldObj);
                this.redUUID = null;
            }
            if (this.aYellow == null && this.yellowUUID != null) {
                this.aYellow = (EntityAyeraco)Util.findEntityByUUID(this.yellowUUID, this.worldObj);
                this.yellowUUID = null;
            }
            if (this.aPurple == null && this.purpleUUID != null) {
                this.aPurple = (EntityAyeraco)Util.findEntityByUUID(this.purpleUUID, this.worldObj);
                this.purpleUUID = null;
            }
        }
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.blueUUID = tag.getString("blueUUID");
        this.redUUID = tag.getString("redUUID");
        this.yellowUUID = tag.getString("yellowUUID");
        this.purpleUUID = tag.getString("purpleUUID");
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("blueUUID", this.aBlue.getPersistentID().toString());
        tag.setString("redUUID", this.aRed.getPersistentID().toString());
        tag.setString("yellowUUID", this.aYellow.getPersistentID().toString());
        tag.setString("purpleUUID", this.aPurple.getPersistentID().toString());
    }
}

