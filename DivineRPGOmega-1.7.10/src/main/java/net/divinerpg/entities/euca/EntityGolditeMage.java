/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGolditeMage
extends EntityDivineRPGMob {
    public EntityGolditeMage(World par1World) {
        super(par1World);
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 1, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.golditeMageHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.golditeMageDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.golditeMageSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.golditeMageFollowRange);
    }

    public String getLivingSound() {
        return Sounds.getSoundName(Sounds.insecto);
    }

    public String getHurtSound() {
        return Sounds.getSoundName(Sounds.insectoHurt);
    }

    public String getDeathSound() {
        return Sounds.getSoundName(Sounds.insectoHurt);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted % 15 == 0) {
            this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 16.0);
            if (this.entityToAttack != null && !this.worldObj.isRemote) {
                double tx = this.entityToAttack.posX - this.posX;
                double ty = this.entityToAttack.boundingBox.minY - this.posY;
                double tz = this.entityToAttack.posZ - this.posZ;
                EntityParticleBullet fb = new EntityParticleBullet(this.worldObj, (EntityLivingBase)this, 4, 0, "fire", 20, "fire");
                fb.setThrowableHeading(tx, ty, tz, 1.6f, 12.0f);
                this.worldObj.spawnEntityInWorld((Entity)fb);
                this.worldObj.playSoundAtEntity(this.entityToAttack, Sounds.mageFire.getPrefixedName(), 1.0f, 1.0f);
            }
        }
    }

    protected void dropFewItems(boolean b, int j) {
        if (this.rand.nextInt(99) < 5) {
            this.dropItem(VanillaItemsOther.repairEssence, 1);
        }
    }

    @Override
    public String mobName() {
        return "Goldite Mage";
    }
}

