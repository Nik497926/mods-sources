/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityEnderTripletFireball;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAyeraco
extends EntityDivineRPGBoss {
    private String colour;
    private int waitTick;
    private ChunkCoordinates currentFlightTarget;
    private boolean attacks;
    private double moveX;
    private double moveZ;
    private boolean halfHp;
    public float moveSpeed = 0.3f;
    public int beamX;
    public int beamY;
    public int beamZ;

    public EntityAyeraco(World var1, String par3) {
        super(var1);
        this.setSize(2.8f, 1.2f);
        this.colour = par3;
        this.waitTick = 0;
        this.attacks = false;
        this.moveX = this.rand.nextGaussian() * 0.25 - 0.125;
        this.moveZ = this.rand.nextGaussian() * 0.25 - 0.125;
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.ayeracoHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.ayeracoDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.ayeracoSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.ayeracoFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(100));
    }

    public String getEntityName() {
        return "Ayeraco " + this.colour;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.ayeraco);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.ayeracoHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.ayeracoHurt);
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= (double)0.6f;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getHealth() * 2.0f < this.getMaxHealth()) {
            this.tickAbility();
            this.halfHp = false;
            if (false) {
                this.worldObj.playSoundAtEntity((Entity)this, Sounds.getSoundName(Sounds.ayeracoHalfHealth), 20.0f, 0.4f / (this.rand.nextFloat() * 0.4f + 0.8f));
            }
        }
        if (this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 128.0) != null) {
            this.setAttackTarget((EntityLivingBase)this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 128.0));
        }
        if (this.waitTick == 0) {
            if (this.getAttackTarget() != null) {
                int var1 = (int)this.getAttackTarget().posX;
                int var2 = (int)this.getAttackTarget().posY;
                int var3 = (int)this.getAttackTarget().posZ;
                this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
            }
            this.motionY = 0.0;
            if (this.currentFlightTarget != null) {
                double var1 = (double)this.currentFlightTarget.posX - this.posX;
                double var3 = (double)this.currentFlightTarget.posY - this.posY;
                double var5 = (double)this.currentFlightTarget.posZ - this.posZ;
                if (Math.signum(var1) != 0.0 || Math.signum(var3) != 0.0 || Math.signum(var5) != 0.0) {
                    this.motionX = Math.signum(var1) * (double)this.moveSpeed;
                    this.motionY += (Math.signum(var3) * 5.699999988079071 - this.motionY) * (double)0.1f;
                    this.motionZ = Math.signum(var5) * (double)this.moveSpeed;
                    float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
                    float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - this.rotationYaw));
                    this.moveForward = 0.5f;
                    this.rotationYaw += var8;
                }
            }
        } else {
            --this.waitTick;
            this.motionY = 0.3;
            this.motionX = this.moveX;
            this.motionZ = this.moveZ;
        }
        if (!this.attacks) {
            this.waitTick = 80;
            this.attacks = true;
            if (this.canTeleport()) {
                this.waitTick = 0;
                this.teleportRandomUp(this.worldObj);
            }
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if (par1DamageSource.getSourceOfDamage() instanceof EntityEnderTripletFireball) {
            return false;
        }
        if (par1DamageSource.isProjectile() && this.canBlockProjectiles()) {
            return false;
        }
        if (par1DamageSource.getSourceOfDamage() instanceof EntityPlayer) {
            this.attacks = false;
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(VanillaItemsOther.divineShards, 2 + this.rand.nextInt(2));
        if (this.rand.nextInt(100) < 2) {
            switch (this.rand.nextInt(2)) {
                case 0: {
                    this.dropItem(VanillaItemsArmor.divineBody, 1);
                }
                case 1: {
                    this.dropItem(VanillaItemsArmor.divineLegs, 1);
                }
            }
        }
        if (this.rand.nextInt(100) < 5) {
            this.dropItem(VanillaItemsArmor.divineBoots, 1);
        }
        if (this.rand.nextInt(100) < 3) {
            this.dropItem(VanillaItemsArmor.divineHelmet, 1);
        }
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        this.attacks = false;
        return super.attackEntityAsMob(par1Entity);
    }

    protected void tickAbility() {
    }

    protected boolean canBlockProjectiles() {
        return true;
    }

    protected boolean canTeleport() {
        return true;
    }

    public boolean abilityActive() {
        return this.getHealth() * 2.0f <= this.getMaxHealth() && !this.isDead;
    }

    protected void teleportRandomUp(World par1) {
        par1.playSoundAtEntity((Entity)this, Sounds.ayeracoTeleport.getPrefixedName(), 2.0f, 0.4f / (this.rand.nextFloat() * 0.4f + 0.8f));
        this.motionY = 20.0;
        this.motionX = this.rand.nextInt(5);
        this.motionZ = this.rand.nextInt(5);
    }

    public void setBeamLocation(int x, int y, int z) {
        this.beamX = x;
        this.beamY = y;
        this.beamZ = z;
    }

    public IChatComponent func_145748_c_() {
        return null;
    }

    protected Item getDropItem() {
        return VanillaItemsOther.divineShards;
    }

    @Override
    public String mobName() {
        return "Ayeraco " + this.colour;
    }

    @Override
    public String name() {
        return this.mobName();
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

