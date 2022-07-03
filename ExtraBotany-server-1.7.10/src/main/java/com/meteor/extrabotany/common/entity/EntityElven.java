/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.core.handler.ElvenHandler;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicMissileII;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;

public class EntityElven
extends EntityTameable {
    ElvenHandler eh = new ElvenHandler(this);
    public static final int affinity = 0;
    public static final float ATK = 3.0f;
    public static final float ASPD = 40.0f;
    public static final int level = 0;
    public static final float color_r = 2.3f;
    public static final float color_g = 2.3f;
    public static final float color_b = 1.0f;
    public static final float size = 3.0f;
    public static final float range = 4.0f;
    public static final int exp = 0;
    public static final int delay = 0;
    public static final int mana = 0;
    public static final int maxmana = 0;
    private static final String TAG_AFFINITY = "affinity";
    private static final String TAG_ATTACK = "ATK";
    private static final String TAG_ATTACKSPEED = "ASPD";
    private static final String TAG_SITTING = "isSitting";
    private static final String TAG_LEVEL = "level";
    private static final String TAG_COLOR_R = "r";
    private static final String TAG_COLOR_G = "g";
    private static final String TAG_COLOR_B = "b";
    private static final String TAG_SIZE = "size";
    private static final String TAG_OWNER = "owner";
    private static final String TAG_RANGE = "range";
    private static final String TAG_EXP = "exp";
    private static final String TAG_DELAY = "delay";
    private static final String TAG_MANA = "mana";
    private static final String TAG_MAX_MANA = "maxmana";
    private static EntityPlayer owner;

    public EntityElven(World world) {
        super(world);
        this.setSize(0.0f, 0.0f);
        this.experienceValue = 3000;
        this.setTamed(false);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, Float.MAX_VALUE));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
    }

    public static boolean spawn(EntityPlayer player, double x, double y, double z) {
        EntityElven e = new EntityElven(player.worldObj);
        owner = player;
        e.setAffinity(0);
        e.setRange(4.0f);
        e.setEXP(0);
        e.setATK(3.0f);
        e.setASPD(40.0f);
        e.setLevel(0);
        e.setSitting(false);
        e.setRGBS(2.3f, 2.3f, 1.0f, 3.0f);
        e.setTamed(true);
        e.setOwnerName(player.getUniqueID().toString());
        player.worldObj.setEntityState((Entity)e, (byte)7);
        e.setMana(0);
        e.setMaxMana(0);
        e.setDelay(0);
        e.setPosition(x, y, z);
        player.worldObj.spawnEntityInWorld((Entity)e);
        return true;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)0);
        this.dataWatcher.addObject(19, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(20, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(21, (Object)0);
        this.dataWatcher.addObject(22, (Object)0);
        this.dataWatcher.addObject(23, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(24, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(25, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(26, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(27, (Object)Float.valueOf(0.0f));
        this.dataWatcher.addObject(28, (Object)0);
        this.dataWatcher.addObject(29, (Object)0);
        this.dataWatcher.addObject(30, (Object)0);
        this.dataWatcher.addObject(31, (Object)0);
    }

    public int getAffinity() {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    public float getATK() {
        return this.dataWatcher.getWatchableObjectFloat(19);
    }

    public float getASPD() {
        return this.dataWatcher.getWatchableObjectFloat(20);
    }

    public boolean isSitting() {
        return this.dataWatcher.getWatchableObjectByte(21) == 1;
    }

    public int getLevel() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public float getR() {
        return this.dataWatcher.getWatchableObjectFloat(23);
    }

    public float getG() {
        return this.dataWatcher.getWatchableObjectFloat(24);
    }

    public float getB() {
        return this.dataWatcher.getWatchableObjectFloat(25);
    }

    public float getSize() {
        return this.dataWatcher.getWatchableObjectFloat(26);
    }

    public String getOwnerName() {
        return this.dataWatcher.getWatchableObjectString(17);
    }

    public float getRange() {
        return this.dataWatcher.getWatchableObjectFloat(27);
    }

    public int getEXP() {
        return this.dataWatcher.getWatchableObjectInt(28);
    }

    public int getDelay() {
        return this.dataWatcher.getWatchableObjectInt(29);
    }

    public int getMana() {
        return this.dataWatcher.getWatchableObjectInt(30);
    }

    public int getMaxMana() {
        return this.dataWatcher.getWatchableObjectInt(31);
    }

    public void setAffinity(int affinity) {
        this.dataWatcher.updateObject(18, (Object)affinity);
    }

    public void setATK(float atk) {
        this.dataWatcher.updateObject(19, (Object)Float.valueOf(atk));
    }

    public void setASPD(float aspd) {
        this.dataWatcher.updateObject(20, (Object)Float.valueOf(aspd));
    }

    public void setSitting(boolean b) {
        this.dataWatcher.updateObject(21, (Object)((byte)(b ? 1 : 0)));
    }

    public void setLevel(int level) {
        this.dataWatcher.updateObject(22, (Object)level);
    }

    public void setRGBS(float r, float g, float b, float s) {
        this.dataWatcher.updateObject(23, (Object)Float.valueOf(r));
        this.dataWatcher.updateObject(24, (Object)Float.valueOf(g));
        this.dataWatcher.updateObject(25, (Object)Float.valueOf(b));
        this.dataWatcher.updateObject(26, (Object)Float.valueOf(s));
    }

    public void setOwnerName(String string) {
        this.dataWatcher.updateObject(17, (Object)string);
    }

    public void setRange(float range) {
        this.dataWatcher.updateObject(27, (Object)Float.valueOf(range));
    }

    public void setEXP(int exp) {
        this.dataWatcher.updateObject(28, (Object)exp);
    }

    public void setDelay(int delay) {
        this.dataWatcher.updateObject(29, (Object)delay);
    }

    public void setMana(int mana) {
        this.dataWatcher.updateObject(30, (Object)mana);
    }

    public void setMaxMana(int maxmana) {
        this.dataWatcher.updateObject(31, (Object)maxmana);
    }

    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setInteger(TAG_AFFINITY, this.getAffinity());
        nbt.setFloat(TAG_ATTACK, this.getATK());
        nbt.setFloat(TAG_ATTACKSPEED, this.getASPD());
        nbt.setBoolean(TAG_SITTING, this.isSitting());
        nbt.setInteger(TAG_LEVEL, this.getLevel());
        nbt.setFloat(TAG_COLOR_R, this.getR());
        nbt.setFloat(TAG_COLOR_G, this.getG());
        nbt.setFloat(TAG_COLOR_B, this.getB());
        nbt.setFloat(TAG_SIZE, this.getSize());
        nbt.setFloat(TAG_RANGE, this.getRange());
        nbt.setInteger(TAG_EXP, this.getEXP());
        nbt.setInteger(TAG_DELAY, this.getDelay());
        nbt.setInteger(TAG_MANA, this.getMana());
        nbt.setInteger(TAG_MAX_MANA, this.getMaxMana());
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setAffinity(nbt.getInteger(TAG_AFFINITY));
        this.setATK(nbt.getFloat(TAG_ATTACK));
        this.setASPD(nbt.getFloat(TAG_ATTACKSPEED));
        this.setSitting(nbt.getBoolean(TAG_SITTING));
        this.setLevel(nbt.getInteger(TAG_LEVEL));
        this.setRGBS(nbt.getFloat(TAG_COLOR_R), nbt.getFloat(TAG_COLOR_G), nbt.getFloat(TAG_COLOR_B), nbt.getFloat(TAG_SIZE));
        this.setRange(nbt.getFloat(TAG_RANGE));
        this.setEXP(nbt.getInteger(TAG_EXP));
        this.setDelay(nbt.getInteger(TAG_DELAY));
        this.setMana(nbt.getInteger(TAG_MANA));
        this.setMaxMana(nbt.getInteger(TAG_MAX_MANA));
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected boolean canDespawn() {
        return false;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public void onUpdate() {
        super.onUpdate();
    }

    public void onLivingUpdate() {
        ElvenHandler var10001;
        super.onLivingUpdate();
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        if (this.getOwner() != null) {
            if (!this.isSitting()) {
                this.posX = this.getOwner().posX;
                this.posY = this.getOwner().posY + (double)0.74f;
                this.posZ = this.getOwner().posZ;
            } else {
                var10001 = this.eh;
                this.posX = ElvenHandler.getLastPosX();
                var10001 = this.eh;
                this.posY = ElvenHandler.getLastPosY() + (double)0.74f;
                var10001 = this.eh;
                this.posZ = ElvenHandler.getLastPosZ();
            }
        } else {
            var10001 = this.eh;
            this.posX = ElvenHandler.getLastPosX();
            var10001 = this.eh;
            this.posY = ElvenHandler.getLastPosY() + (double)0.74f;
            var10001 = this.eh;
            this.posZ = ElvenHandler.getLastPosZ();
        }
        float var10000 = this.getATK();
        var10001 = this.eh;
        float ATK = var10000 + ElvenHandler.getExtraATK();
        var10000 = this.getRange();
        var10001 = this.eh;
        float RANGE = var10000 + ElvenHandler.getExtraRange();
        var10000 = this.getASPD();
        var10001 = this.eh;
        float ASPD = var10000 + ElvenHandler.getExtraRange();
        List<EntityXPOrb> xps = this.worldObj.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox((double)(this.posX - (double)RANGE), (double)(this.posY - (double)RANGE), (double)(this.posZ - (double)RANGE), (double)(this.posX + (double)RANGE + 1.0), (double)(this.posY + (double)RANGE + 1.0), (double)(this.posZ + (double)RANGE + 1.0)));
        for (EntityXPOrb mobs : xps) {
            if (this.getLevel() < 100) {
                this.setEXP(this.getEXP() + mobs.xpValue);
            }
            mobs.setDead();
        }
        this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)(this.posX - (double)RANGE), (double)(this.posY - (double)RANGE), (double)(this.posZ - (double)RANGE), (double)(this.posX + (double)RANGE + 1.0), (double)(this.posY + (double)RANGE + 1.0), (double)(this.posZ + (double)RANGE + 1.0)));
        Botania.proxy.sparkleFX(this.worldObj, this.posX, this.posY, this.posZ, this.getR(), this.getG(), this.getB(), this.getSize(), 6);
        Botania.proxy.sparkleFX(this.worldObj, this.posX, this.posY, this.posZ, this.getR(), this.getG(), this.getB(), this.getSize() / 10.0f, 2);
        if (this.ticksExisted % 3600 == 0 && Math.random() > 0.7 && this.getAffinity() < 30) {
            this.setAffinity(this.getAffinity() + 1);
        }
        List var10 = this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)(this.posX - (double)this.getRange()), (double)(this.posY - (double)this.getRange()), (double)(this.posZ - (double)this.getRange()), (double)(this.posX + (double)this.getRange() + 1.0), (double)(this.posY + (double)this.getRange() + 1.0), (double)(this.posZ + (double)this.getRange() + 1.0)));
        int s = var10.size();
        if (this.getDelay() > 0) {
            this.setDelay(this.getDelay() - 1);
        }
        if (!this.worldObj.isRemote && !this.isSitting() && (float)this.ticksExisted % (ASPD + 20.0f) == 0.0f) {
            do {
                this.setDelay((int)(ASPD + 10.0f));
                for (int i = 0; i < s + 1; ++i) {
                    EntityMagicMissileII missile = new EntityMagicMissileII((EntityLivingBase)this, false);
                    missile.setPosition(this.posX + (Math.random() - 0.05), this.posY + (double)0.68f + (Math.random() - 0.05), this.posZ + (Math.random() - 0.05));
                    missile.setATK(ATK);
                    if (!missile.getTarget()) continue;
                    this.worldObj.playSoundAtEntity((Entity)this, "botania:missile", 0.6f, 0.8f + (float)Math.random() * 0.2f);
                    this.worldObj.spawnEntityInWorld((Entity)missile);
                }
            } while (s > 0 && this.getDelay() == 0 && ATK > 0.0f && ASPD > 0.0f);
        }
    }

    public boolean interact(EntityPlayer player) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        if (this.isTamed() && player == this.getOwner()) {
            if (!this.worldObj.isRemote) {
                ElvenHandler var10000 = this.eh;
                ElvenHandler.setLastPos(player.posX, player.posY, player.posZ);
            }
            this.setSitting(!this.isSitting());
            this.isJumping = false;
            this.setPathToEntity(null);
            this.setTarget(null);
            this.setAttackTarget(null);
            return true;
        }
        return super.interact(player);
    }

    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return null;
    }
}

