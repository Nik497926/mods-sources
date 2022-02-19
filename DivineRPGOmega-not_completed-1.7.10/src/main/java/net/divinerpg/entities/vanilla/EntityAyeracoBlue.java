/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.vanilla.EntityAyeraco;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAyeracoBlue
extends EntityAyeraco {
    private EntityAyeraco aGreen;
    private EntityAyeraco aRed;
    private EntityAyeraco aYellow;
    private EntityAyeraco aPurple;
    private String greenUUID;
    private String redUUID;
    private String yellowUUID;
    private String purpleUUID;

    public EntityAyeracoBlue(World par1World) {
        super(par1World, "Blue");
    }

    public void initOthers(EntityAyeraco par2, EntityAyeraco par3, EntityAyeraco par4, EntityAyeraco par5) {
        this.aGreen = par2;
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
        return this.aGreen != null && this.aGreen.abilityActive();
    }

    @Override
    protected void tickAbility() {
        this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
        if (this.aGreen != null && !this.aGreen.isDead) {
            this.aGreen.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
        }
        if (this.aYellow != null && !this.aYellow.isDead) {
            this.aYellow.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
        }
        if (this.aRed != null && !this.aRed.isDead) {
            this.aRed.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
        }
        if (this.aPurple != null && !this.aPurple.isDead) {
            this.aPurple.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
        }
    }

    @Override
    protected boolean canTeleport() {
        return this.aPurple != null && this.aPurple.abilityActive();
    }

    protected void dropRareDrop(int par1) {
        this.dropItem(VanillaItemsWeapons.blueEnderSword, 1);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            if (this.aGreen == null && this.greenUUID != null) {
                this.aGreen = (EntityAyeraco)Util.findEntityByUUID(this.greenUUID, this.worldObj);
                this.greenUUID = null;
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
        this.greenUUID = tag.getString("greenUUID");
        this.redUUID = tag.getString("redUUID");
        this.yellowUUID = tag.getString("yellowUUID");
        this.purpleUUID = tag.getString("purpleUUID");
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("greenUUID", this.aGreen.getPersistentID().toString());
        tag.setString("redUUID", this.aRed.getPersistentID().toString());
        tag.setString("yellowUUID", this.aYellow.getPersistentID().toString());
        tag.setString("purpleUUID", this.aPurple.getPersistentID().toString());
    }
}

