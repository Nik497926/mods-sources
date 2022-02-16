/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityKingOfScorchersMeteor;
import net.divinerpg.entities.vanilla.projectile.EntityKingOfScorchersShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityKingOfScorchers
extends EntityDivineRPGBoss {
    private int special;

    public EntityKingOfScorchers(World var1) {
        super(var1);
        this.setSize(2.0f, 3.9f);
        this.special = 0;
        this.isImmuneToFire = true;
    }

    public void setInPortal() {
        if (this.timeUntilPortal > 0) {
            this.timeUntilPortal = this.getPortalCooldown();
        } else {
            double d0 = this.prevPosX - this.posX;
            double d1 = this.prevPosZ - this.posZ;
            if (!this.worldObj.isRemote && !this.inPortal) {
                this.teleportDirection = Direction.getMovementDirection((double)d0, (double)d1);
            }
            this.inPortal = false;
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.kingOfScorchersHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.kingOfScorchersDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.kingOfScorchersSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.kingOfScorchersFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(100));
        this.dataWatcher.addObject(13, (Object)new Byte(0));
    }

    private boolean teleportToEntity(Entity entity) {
        double d1 = entity.posX;
        double d2 = entity.posY;
        double d3 = entity.posZ;
        return this.teleportTo(d1, d2, d3);
    }

    private boolean teleportTo(double eX, double eY, double eZ) {
        int d3 = (int)eX;
        int d4 = (int)eY;
        int d5 = (int)eZ;
        this.setPositionAndUpdate(d3, d4, d5);
        this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 10.0f, true);
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            double d7 = (double)d3 + (this.posX - (double)d3) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            double d8 = (double)d4 + (this.posY - (double)d4) * d6 + this.rand.nextDouble() * (double)this.height;
            double d9 = (double)d5 + (this.posZ - (double)d5) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        this.worldObj.playSoundEffect((double)d3, (double)d4, (double)d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.playSound("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    protected void updateAITasks() {
        if (this.ticksExisted % 250 == 0 && this.special == 0) {
            this.special = 15;
        }
        if (this.special > 0 && this.entityToAttack != null) {
            --this.special;
            if (this.special % 5 == 0) {
                for (int i = 0; i < 4; ++i) {
                    EntityKingOfScorchersMeteor var1 = new EntityKingOfScorchersMeteor(this.worldObj, this.entityToAttack.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 2.0, this.entityToAttack.posY + 10.0, this.entityToAttack.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 2.0);
                    var1.motionX = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    var1.motionY = -0.7;
                    var1.motionZ = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    this.worldObj.spawnEntityInWorld((Entity)var1);
                }
            }
        }
        super.updateAITasks();
        this.entityToAttack = this.worldObj.getClosestPlayerToEntity((Entity)this, 60.0);
        if (this.entityToAttack != null && !this.worldObj.isRemote && this.ticksExisted % 10 == 0) {
            this.attackEntityWithRangedAttack(this.entityToAttack);
            if (this.ticksExisted % 500 == 0) {
                this.teleportToEntity(this.entityToAttack);
            }
        }
    }

    public int getTotalArmorValue() {
        return 10;
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.kingScorcher);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.kingScorcherHurt);
    }

    protected String getDeathSound() {
        return null;
    }

    protected Item getDropItem() {
        return VanillaItemsOther.furyFire;
    }

    public void dropFewItems(boolean par1, int par2) {
        this.dropItem(this.getDropItem(), 1);
        this.dropItem(VanillaItemsOther.bluefireStone, 12);
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.kosStatue), 1);
        }
    }

    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (source.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(source, par2);
    }

    public void attackEntityWithRangedAttack(Entity entity) {
        double tx = entity.posX - this.posX;
        double ty = entity.boundingBox.minY - this.posY - 2.0;
        double tz = entity.posZ - this.posZ;
        EntityKingOfScorchersShot e = new EntityKingOfScorchersShot(this.worldObj, (EntityLivingBase)this);
        e.setThrowableHeading(tx, ty, tz, 1.3f, 1.0f);
        this.worldObj.spawnEntityInWorld((Entity)e);
    }

    @Override
    public String mobName() {
        return "King of Scorchers";
    }

    @Override
    public String name() {
        return "King of Scorchers";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

