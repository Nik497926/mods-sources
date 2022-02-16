/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.entities.vethea.projectile.EntityMandragoraProjectile;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMandragora
extends VetheaMob {
    public EntityMandragora(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.mandragoraHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.mandragoraDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.mandragoraSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.mandragoraFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 2;
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.mandragora.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.mandragora.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public void onUpdate() {
        super.onUpdate();
        EntityPlayer target = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0);
        if (!this.worldObj.isRemote && target != null && this.ticksExisted % 20 == 0) {
            this.attackEntity((EntityLivingBase)target);
        }
    }

    public void attackEntity(EntityLivingBase e) {
        double tx = e.posX - this.posX;
        double ty = e.boundingBox.minY - this.posY;
        double tz = e.posZ - this.posZ;
        EntityMandragoraProjectile p = new EntityMandragoraProjectile(this.worldObj, (EntityLiving)this);
        p.setThrowableHeading(tx, ty, tz, 1.3f, 15.0f);
        this.playSound(Sounds.mandragora.getPrefixedName(), 2.0f, 2.0f);
        if (!this.worldObj.isRemote) {
            this.worldObj.spawnEntityInWorld((Entity)p);
        }
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

    @Override
    public String mobName() {
        return "Mandragora";
    }
}

