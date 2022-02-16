/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.entities.vethea.projectile.EntityKazroticShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityKazrotic
extends VetheaMob
implements IRangedAttackMob {
    public EntityKazrotic(World par1World) {
        super(par1World);
        this.targetTasks.addTask(6, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 0.25, 15, 40.0f));
    }

    @Override
    public int getSpawnLayer() {
        return 3;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.kazroticHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.kazroticSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.kazroticFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.kazrotic.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.kazroticHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    public void attackEntityWithRangedAttack(EntityLivingBase entity, float par2) {
        double tx = entity.posX - this.posX;
        double ty = entity.boundingBox.minY - this.posY;
        double tz = entity.posZ - this.posZ;
        EntityKazroticShot shot = new EntityKazroticShot(this.worldObj, (EntityLiving)this);
        shot.setThrowableHeading(tx, ty, tz, 0.6f, 0.3f);
        this.playSound("random.bow", 1.0f, 1.0f);
        this.worldObj.spawnEntityInWorld((Entity)shot);
    }

    public boolean attackEntityFrom(DamageSource par1, float par2) {
        if (par1.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(par1, par2);
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.polishedPearls, 1);
    }

    @Override
    public String mobName() {
        return "Kazrotic";
    }
}

