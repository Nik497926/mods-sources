/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntitySaguaroWormShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySaguaroWorm
extends EntityDivineRPGMob {
    public EntitySaguaroWorm(World par1World) {
        super(par1World);
        this.setSize(0.5f, 3.0f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.saguaroWormHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.saguaroWormDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.saguaroWormSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.saguaroWormFollowRange);
    }

    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(17, (Object)0);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            this.entityToAttack = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 10.0);
            if (this.entityToAttack == null) {
                this.dataWatcher.updateObject(17, (Object)0);
            } else {
                this.dataWatcher.updateObject(17, (Object)1);
                if (this.ticksExisted % 50 == 0) {
                    this.attack((EntityLivingBase)this.entityToAttack);
                }
            }
        }
        if (this.dataWatcher.getWatchableObjectInt(17) == 0) {
            this.renderYawOffset = 0.0f;
        }
    }

    public void dropFewItems(boolean hit, int looting) {
        this.dropItem(VanillaItemsOther.terranShards, this.rand.nextInt(3 + looting));
        this.dropItem(Item.getItemFromBlock((Block)Blocks.cactus), this.rand.nextInt(3) + 3);
    }

    public void attack(EntityLivingBase e) {
        double y = this.boundingBox.minY + 2.7;
        double tx = e.posX - this.posX;
        double ty = e.boundingBox.minY - y;
        double tz = e.posZ - this.posZ;
        for (double h = -1.5; h < 1.5; h += 0.3) {
            for (double r = 0.0; r < 1.5 - Math.abs(h); r += 0.3) {
                for (double theta = 0.0; theta < Math.PI * 2; theta += 0.39269908169872414) {
                    EntitySaguaroWormShot shot = new EntitySaguaroWormShot(this.worldObj, (EntityLivingBase)this);
                    shot.posX = this.posX + r * Math.cos(theta);
                    shot.posY = this.posY + 5.0 + h;
                    shot.posZ = this.posZ + r * Math.sin(theta);
                    shot.setThrowableHeading(tx, ty, tz, 0.9f, 5.0f);
                    this.worldObj.spawnEntityInWorld((Entity)shot);
                }
            }
        }
    }

    @Override
    protected String getLivingSound() {
        return null;
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.saguaroWorm);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.saguaroWorm);
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.getBlock((int)this.posX, MathHelper.floor_double((double)this.boundingBox.minY) - 1, (int)this.posZ) == Blocks.sand && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Saguaro Worm";
    }

    @Override
    public boolean needsSpecialAI() {
        return true;
    }
}

