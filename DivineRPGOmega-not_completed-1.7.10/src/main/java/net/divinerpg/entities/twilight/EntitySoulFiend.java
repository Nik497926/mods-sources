/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.projectile.EntitySoulFiendProjectile;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySoulFiend
extends EntityDivineRPGBoss {
    public EntitySoulFiend(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.soulFiendHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.soulFiendDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.soulFiendSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.soulFiendFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && this.ticksExisted % 300 == 0) {
            for (int i = 0; i < 4; ++i) {
                EntitySoulFiendProjectile e = new EntitySoulFiendProjectile(this.worldObj, (EntityLivingBase)this);
                e.setThrowableHeading(this.rand.nextDouble() - this.rand.nextDouble(), -0.25, this.rand.nextDouble() - this.rand.nextDouble(), 0.5f, 12.0f);
                this.worldObj.spawnEntityInWorld((Entity)e);
            }
        }
    }

    public boolean attackEntityAsMob(Entity e) {
        if (super.attackEntityAsMob(e)) {
            if (e instanceof EntityPlayer) {
                ((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.confusion.id, 240, 0));
                ((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.blindness.id, 240, 0));
            }
            e.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f))) * 2.5, 0.4, (double)MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * 2.5);
            this.motionX *= 0.6;
            this.motionZ *= 0.6;
            return true;
        }
        return false;
    }

    public void dropFewItems(boolean par1, int par2) {
        int chance = this.rand.nextInt(99);
        this.dropItem(TwilightItemsWeapons.haliteSlicer, 200);
        if (chance < 50) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.soulFiendStatue), 1);
            if (chance < 25) {
                this.dropItem(VanillaItemsOther.tarnishedCrystal, 1);
            }
        }
    }

    @Override
    public String mobName() {
        return "Soul Fiend";
    }

    @Override
    public String name() {
        return "Soul Fiend";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

