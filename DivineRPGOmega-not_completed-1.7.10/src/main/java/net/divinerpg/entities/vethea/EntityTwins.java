/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityTwins
extends VetheaMob
implements IRangedAttackMob {
    public static final int SLOW = 0;
    public static final int FAST = 1;
    public static int ability;
    private int abilityCoolDown;
    private EntityAIBase rangedAI = new EntityAIArrowAttack((IRangedAttackMob)this, 0.25, 10, 64.0f);
    private int rangedAttackCounter;

    public EntityTwins(World par1World) {
        super(par1World);
        this.tasks.addTask(5, this.rangedAI);
        this.addAttackingAI();
        ability = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.twinsHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.twinsDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.twinsSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.twinsFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 2;
    }

    protected void updateAITasks() {
        this.manageAbilities();
        super.updateAITasks();
    }

    public void manageAbilities() {
        if (ability == 0 && this.abilityCoolDown == 0) {
            ability = 1;
            this.abilityCoolDown = 50;
            this.rangedAttackCounter = 0;
        } else if (ability == 1 && this.abilityCoolDown == 0) {
            ability = 0;
            this.abilityCoolDown = 60;
            this.rangedAttackCounter = 0;
        } else if (this.abilityCoolDown > 0) {
            --this.abilityCoolDown;
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase entity, float par2) {
        switch (ability) {
            case 1: {
                EntityArrow var2 = new EntityArrow(this.worldObj, (EntityLivingBase)this, entity, 1.6f, 12.0f);
                var2.setDamage(2.0);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                break;
            }
            case 0: {
                ++this.rangedAttackCounter;
                if ((this.rangedAttackCounter & 4) != 0) break;
                EntityArrow var4 = new EntityArrow(this.worldObj, (EntityLivingBase)this, entity, 1.6f, 12.0f);
                var4.setDamage(4.0);
                this.worldObj.spawnEntityInWorld((Entity)var4);
                break;
            }
        }
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

    protected String getLivingSound() {
        return Sounds.duo.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.duoHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    @Override
    public String mobName() {
        return "Twins";
    }
}

