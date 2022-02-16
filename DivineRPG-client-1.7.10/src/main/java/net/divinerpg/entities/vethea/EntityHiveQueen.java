/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.EntityHiveSoldier;
import net.divinerpg.entities.vethea.EntityHoverStinger;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityHiveQueen
extends EntityDivineRPGBoss {
    private int spawnTick;
    private int deathTicks;

    public EntityHiveQueen(World var1) {
        super(var1);
        this.addAttackingAI();
        this.spawnTick = 80;
        this.setSize(1.5f, 4.0f);
    }

    @Override
    public String mobName() {
        return "Hive Queen";
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.hiveQueenHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.hiveQueenDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.hiveQueenFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.spawnTick % 40 == 0 && !this.worldObj.isRemote && this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 20.0) != null) {
            if (this.rand.nextBoolean()) {
                EntityHoverStinger var2 = new EntityHoverStinger(this.worldObj);
                var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                this.worldObj.spawnParticle("reddust", var2.posX, var2.posY + 0.5, var2.posZ, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0);
            } else {
                EntityHiveSoldier var2 = new EntityHiveSoldier(this.worldObj);
                var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rand.nextFloat() * 360.0f, 0.0f);
                this.worldObj.spawnEntityInWorld((Entity)var2);
                this.worldObj.spawnParticle("reddust", var2.posX, var2.posY + 0.5, var2.posZ, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0, this.rand.nextGaussian() * 2.0 - 1.0);
            }
            this.spawnTick = 80;
        }
        --this.spawnTick;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.hiveQueen.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.hiveQueenHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.honeychunk, 10);
        this.dropItem(VetheaItems.honeysuckle, 15);
        this.dropItem(VetheaItems.cermileLump, 17);
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;
        if (this.deathTicks >= 180 && this.deathTicks <= 200) {
            float var1 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            float var2 = (this.rand.nextFloat() - 0.5f) * 4.0f;
            float var3 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)var1, this.posY + 2.0 + (double)var2, this.posZ + (double)var3, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote) {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0) {
                int var5;
                for (int var4 = 1000; var4 > 0; var4 -= var5) {
                    var5 = EntityXPOrb.getXPSplit((int)var4);
                    this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
                }
            }
            if (this.deathTicks == 1) {
                this.worldObj.playBroadcastSound(1018, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
        }
        this.moveEntity(0.0, 0.1f, 0.0);
        this.renderYawOffset = this.rotationYaw += 20.0f;
        if (this.deathTicks == 200 && !this.worldObj.isRemote) {
            int var5;
            for (int var4 = 2000; var4 > 0; var4 -= var5) {
                var5 = EntityXPOrb.getXPSplit((int)var4);
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }
            this.setDead();
        }
    }

    @Override
    public String name() {
        return "Hive Queen";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

