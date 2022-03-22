/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.IMinion;
import com.meteor.extrabotany.common.item.ModItems;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import vazkii.botania.common.item.relic.ItemRelic;

public class EntityGaiaIIIPhantom
extends EntityMob
implements IMinion {
    EntityGaiaIII summoner;
    public static final int SPAWN_TICKS = 100;
    private static final float RANGE = 12.0f;
    private static final float MAX_HP = 20.0f;
    private static final String TAG_INVUL_TIME = "invulTime";

    public EntityGaiaIIIPhantom(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.isImmuneToFire = true;
        this.experienceValue = 0;
    }

    public EntityGaiaIIIPhantom(World world, EntityGaiaIII summoner) {
        super(world);
        this.summoner = summoner;
    }

    public static boolean spawn(World par3World, double posX, double posY, double posZ, EntityGaiaIII summoner) {
        EntityGaiaIIIPhantom e = new EntityGaiaIIIPhantom(par3World, summoner);
        e.setPosition(posX + 0.5, posY + 3.0, posZ + 0.5);
        e.setInvulTime(100);
        e.setHealth(1.0f);
        String b = "Made by Vazkii";
        ItemStack s2 = new ItemStack(ModItems.oghelm);
        ItemRelic.bindToUsernameS(b, s2);
        ItemStack s3 = new ItemStack(ModItems.ogchest);
        ItemRelic.bindToUsernameS(b, s3);
        ItemStack s4 = new ItemStack(ModItems.oglegs);
        ItemRelic.bindToUsernameS(b, s4);
        ItemStack s5 = new ItemStack(ModItems.ogboots);
        ItemRelic.bindToUsernameS(b, s5);
        e.setCurrentItemOrArmor(1, s2);
        e.setCurrentItemOrArmor(2, s3);
        e.setCurrentItemOrArmor(3, s4);
        e.setCurrentItemOrArmor(4, s5);
        e.setEquipmentDropChance(0, -1.0f);
        e.setEquipmentDropChance(1, -1.0f);
        e.setEquipmentDropChance(2, -1.0f);
        e.setEquipmentDropChance(3, -1.0f);
        e.setEquipmentDropChance(4, -1.0f);
        par3World.playSoundAtEntity(e, "mob.enderdragon.growl", 10.0f, 0.1f);
        par3World.spawnEntityInWorld(e);
        return true;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, 0);
    }

    public int getInvulTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setInvulTime(int time) {
        this.dataWatcher.updateObject(20, time);
    }

    public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeEntityToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setInteger(TAG_INVUL_TIME, this.getInvulTime());
    }

    public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readEntityFromNBT(par1nbtTagCompound);
        this.setInvulTime(par1nbtTagCompound.getInteger(TAG_INVUL_TIME));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
    }

    public void onDeath(DamageSource p_70645_1_) {
        super.onDeath(p_70645_1_);
        EntityLivingBase entitylivingbase = this.func_94060_bK();
        if (entitylivingbase instanceof EntityPlayer) {
            entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.summoner), 6.0f);
        }
    }

    protected boolean canDespawn() {
        return false;
    }

    public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6.0f);
    }

    public void onLivingUpdate() {
        boolean peaceful;
        int range;
        List players;
        super.onLivingUpdate();
        int invul = this.getInvulTime();
        if (invul > 0) {
            if (invul < 100 && invul > 50 && this.worldObj.rand.nextInt(100 - invul + 1) == 0) {
                for (int i = 0; i < 2; ++i) {
                    this.spawnExplosionParticle();
                }
            }
            if (!this.worldObj.isRemote) {
                this.setHealth(this.getHealth() + (this.getMaxHealth() - 1.0f) / 100.0f);
                this.setInvulTime(invul - 1);
            }
        }
        if ((players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.posX + 0.5 - (double)(range = 11), this.posY + 0.5 - (double)range, this.posZ + 0.5 - (double)range, this.posX + 0.5 + (double)range, this.posY + 0.5 + (double)range, this.posZ + 0.5 + (double)range))).isEmpty() && !this.worldObj.playerEntities.isEmpty()) {
            this.setDead();
        }
        boolean bl = peaceful = this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL;
        if (!this.worldObj.isRemote && peaceful) {
            this.setDead();
        }
    }

    @Override
    public boolean canDestroy() {
        return true;
    }
}

