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
 *  net.minecraft.init.Items
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityCaveRock;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCaveclops
extends EntityDivineRPGMob
implements IRangedAttackMob {
    public EntityCaveclops(World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 0.25, 30, 10.0f));
        this.addAttackingAI();
        this.setSize(1.0f, 2.9f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.caveclopsHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.caveclopsDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.caveclopsSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.caveclopsFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.cyclops);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.cyclopsHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.cyclopsHurt);
    }

    protected void dropFewItems(boolean par1, int par2) {
        int var4;
        int var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(VanillaItemsOther.realmiteIngot, 3);
        }
        var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(Items.golden_pickaxe, 1);
        }
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 20.0 && super.getCanSpawnHere();
    }

    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
        EntityCaveRock var2 = new EntityCaveRock(this.worldObj, (EntityLivingBase)this);
        double var3 = var1.posX - this.posX;
        double var5 = var1.posY + (double)var1.getEyeHeight() - (double)1.1f - var2.posY;
        double var7 = var1.posZ - this.posZ;
        float var9 = MathHelper.sqrt_double((double)(var3 * var3 + var7 * var7)) * 0.2f;
        var2.setThrowableHeading(var3, var5 + (double)var9, var7, 1.6f, 12.0f);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)var2);
    }

    @Override
    public String mobName() {
        return "Caveclops";
    }
}

