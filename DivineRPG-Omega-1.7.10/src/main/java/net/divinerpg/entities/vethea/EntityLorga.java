/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLorga
extends EntityDivineRPGMob {
    private int spawnTick;
    public boolean canSpawnMinions;

    public EntityLorga(World w) {
        this(w, true);
    }

    public EntityLorga(World var1, boolean canSpawnMinions) {
        super(var1);
        this.addAttackingAI();
        this.canSpawnMinions = canSpawnMinions;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.lorgaHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.lorgaDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.lorgaSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.lorgaFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        List nearby = this.worldObj.getEntitiesWithinAABB(EntityLorga.class, this.boundingBox.expand(10.0, 10.0, 10.0));
        if (this.spawnTick == 0 && this.canSpawnMinions && !this.worldObj.isRemote && nearby.size() < 12) {
            this.spawnTick = 260;
            EntityLorga var2 = new EntityLorga(this.worldObj, false);
            var2.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ + 1.0, this.rotationYaw, this.rotationPitch);
            this.worldObj.spawnEntityInWorld((Entity)var2);
        } else if (this.spawnTick > 0) {
            --this.spawnTick;
        }
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setBoolean("CanSpawnMinions", this.canSpawnMinions);
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.canSpawnMinions = tag.getBoolean("CanSpawnMinions");
    }

    protected String getLivingSound() {
        return Sounds.lorga.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.lorgaHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    public String mobName() {
        return "Lorga";
    }
}

