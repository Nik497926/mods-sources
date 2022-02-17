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

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.kingOfScorchersHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.kingOfScorchersDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.kingOfScorchersSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.kingOfScorchersFollowRange);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, 100);
        this.dataWatcher.addObject(13, (byte)0);
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
        this.entityToAttack = this.worldObj.getClosestPlayerToEntity((Entity)this, 40.0);
        if (this.entityToAttack != null && !this.worldObj.isRemote && this.ticksExisted % 15 == 0) {
            this.attackEntityWithRangedAttack(this.entityToAttack);
        }
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.kingScorcher);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.kingScorcherHurt);
    }

    @Override
    protected String getDeathSound() {
        return null;
    }

    protected Item getDropItem() {
        return VanillaItemsOther.furyFire;
    }

    public void dropFewItems(boolean par1, int par2) {
        this.dropItem(this.getDropItem(), 1);
        this.dropItem(VanillaItemsOther.bluefireStone, 6);
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

