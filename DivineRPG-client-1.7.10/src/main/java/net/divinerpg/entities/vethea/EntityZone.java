/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityZone
extends VetheaMob {
    public EntityZone(World par1World) {
        super(par1World);
        this.setSize(0.8f, 1.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.zoneHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.zoneDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.zoneSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.zoneFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 4;
    }

    public void onUpdate() {
        super.onUpdate();
        this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 32.0);
        if (!this.worldObj.isRemote && this.ticksExisted % 40 == 0 && this.getEntityToAttack() != null && this.getEntityToAttack() instanceof EntityLivingBase) {
            this.shootEntity((EntityLivingBase)this.getEntityToAttack());
        }
    }

    @Override
    protected String getLivingSound() {
        return Sounds.zone.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.zoneHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    private void shootEntity(EntityLivingBase e) {
        EntityDivineArrow arrow = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, e, 1.6f, 12.0f, 20.0f, "karosArrow");
        this.worldObj.spawnEntityInWorld((Entity)arrow);
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.shinyPearls, 1);
    }

    @Override
    public String mobName() {
        return "Zone";
    }
}

