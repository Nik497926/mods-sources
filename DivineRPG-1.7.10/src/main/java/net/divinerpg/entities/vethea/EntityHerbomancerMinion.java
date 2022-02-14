/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHerbomancerMinion
extends EntityDivineRPGMob {
    public float jumpHeight;
    public float height;
    public float height1;
    private int slimeJumpDelay = 0;

    public EntityHerbomancerMinion(World par1World) {
        super(par1World);
        this.yOffset = 0.0f;
        this.slimeJumpDelay = this.rand.nextInt(20) + 10;
        this.setSize(1.0f, 1.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.herbomancerMinionHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.herbomancerMinionDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.herbomancerMinionSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.herbomancerMinionFollowRange);
    }

    protected String getSlimeParticle() {
        return "slime";
    }

    protected String getJumpSound() {
        return "mob.slime.jump";
    }

    public void onUpdate() {
        if (!this.worldObj.isRemote) {
            this.isDead = true;
        }
        this.height += (this.jumpHeight - this.height) * 0.5f;
        this.height1 = this.height;
        boolean var1 = this.onGround;
        super.onUpdate();
        if (this.onGround && !var1) {
            int var2 = 1;
            for (int var3 = 0; var3 < var2 * 8; ++var3) {
                float var4 = this.rand.nextFloat() * (float)Math.PI * 2.0f;
                float var5 = this.rand.nextFloat() * 0.5f + 0.5f;
                float var6 = MathHelper.sin((float)var4) * (float)var2 * 0.5f * var5;
                float var7 = MathHelper.cos((float)var4) * (float)var2 * 0.5f * var5;
                this.worldObj.spawnParticle(this.getSlimeParticle(), this.posX + (double)var6, this.boundingBox.minY, this.posZ + (double)var7, 0.0, 0.0, 0.0);
            }
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            this.jumpHeight = -0.5f;
        } else if (!this.onGround && var1) {
            this.jumpHeight = 1.0f;
        }
        this.jumpHeight();
        if (this.worldObj.isRemote) {
            this.setSize(1.0f, 1.0f);
        }
    }

    protected void updateEntityActionState() {
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 32.0);
        if (var1 != null) {
            this.faceEntity((Entity)var1, 10.0f, 20.0f);
        }
        if (this.onGround && this.slimeJumpDelay-- <= 0) {
            this.slimeJumpDelay = this.getJumpDelay();
            if (var1 != null) {
                this.slimeJumpDelay /= 3;
            }
            this.isJumping = true;
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) * 0.8f);
            this.moveStrafing = 1.0f - this.rand.nextFloat() * 2.0f;
            this.moveForward = 1.0f;
        } else {
            this.isJumping = false;
            if (this.onGround) {
                this.moveForward = 0.0f;
                this.moveStrafing = 0.0f;
            }
        }
    }

    protected void jumpHeight() {
        this.jumpHeight *= 0.6f;
    }

    protected int getJumpDelay() {
        return this.rand.nextInt(20) + 10;
    }

    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if (this.canEntityBeSeen((Entity)par1EntityPlayer) && this.getDistanceSqToEntity((Entity)par1EntityPlayer) < 1.0 && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 5.0f)) {
            this.playSound("mob.attack", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        }
    }

    @Override
    public String mobName() {
        return "Herbomancer Minion";
    }
}

