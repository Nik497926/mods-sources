/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.world.Asgard.TeleportAsgard;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityDoppleganger;

public class EntityAsgard
extends EntityMob
implements IBossDisplayData {
    private int attackTimer = 0;

    public EntityAsgard(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.isImmuneToFire = true;
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 1.0, 48.0f));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.experienceValue = 1225;
        this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(240.0);
        super.setHealth(240.0f);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, 0);
        this.dataWatcher.addObject(17, 0);
        this.dataWatcher.addObject(20, new Integer(0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    public int getTotalArmorValue() {
        return 16;
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.playSound("mob.irongolem.walk", 1.0f, 1.0f);
    }

    public String getCommandSenderName() {
        return StatCollector.translateToLocal("entity.ExtraBotania.asgard.name");
    }

    public void setRevengeTarget(EntityLivingBase entity) {
        if (!(entity instanceof EntityAsgard || entity instanceof EntityDoppleganger || entity instanceof EntityGaiaIII || entity instanceof EntityExMachine)) {
            super.setRevengeTarget(entity);
        }
    }

    public int getInvulTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void setInvulTime(int par1) {
        this.dataWatcher.updateObject(20, par1);
    }

    public void init() {
        this.setInvulTime(150);
        this.setHealth(this.getMaxHealth() / 4.0f);
    }

    public boolean isAIEnabled() {
        return true;
    }

    protected void updateAITick() {
        super.updateAITick();
    }

    protected void updateAITasks() {
        if (this.getInvulTime() > 0) {
            int i = this.getInvulTime() - 1;
            if (i <= 0) {
                this.worldObj.playBroadcastSound(1013, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
            this.setInvulTime(i);
            if (this.ticksExisted % 10 == 0) {
                this.heal(20.0f);
            }
        } else {
            super.updateAITasks();
            if (this.ticksExisted % 20 == 0) {
                this.heal(2.0f);
            }
        }
    }

    protected int decreaseAirSupply(int par1) {
        return par1;
    }

    protected void collideWithEntity(Entity par1Entity) {
        super.collideWithEntity(par1Entity);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        this.attackTimer = 10;
        double distance = this.getDistanceSq(par1Entity.posX, par1Entity.posY, par1Entity.posZ);
        double height = 0.0;
        int force = 4;
        if (distance <= 9.0) {
            height = 1.0;
            force = 40;
        } else if (distance <= 36.0) {
            height = 0.8;
            force = 30;
        } else if (distance <= 81.0) {
            height = 0.5;
            force = 10;
        } else if (distance <= 256.0) {
            height = 0.2;
            force = 6;
        }
        this.worldObj.setEntityState(this, (byte)4);
        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(6 + this.rand.nextInt(force)));
        if (flag) {
            par1Entity.motionY += 0.5 + height;
        }
        this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        return flag;
    }

    public boolean attackEntityFrom(DamageSource source, float dmg) {
        if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {
            return super.attackEntityFrom(source, dmg);
        }
        return false;
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Invul", this.getInvulTime());
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setInvulTime(par1NBTTagCompound.getInteger("Invul"));
    }

    @SideOnly(value=Side.CLIENT)
    public void handleHealthUpdate(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 10;
            this.playSound("mob.irongolem.throw", 1.0f, 1.0f);
        } else {
            super.handleHealthUpdate(par1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    protected String getDeathSound() {
        return "mob.irongolem.death";
    }

    public float getBrightness(float par1) {
        return 1.0f;
    }

    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
    }

    protected boolean interact(EntityPlayer pl) {
        ItemStack st;
        if (pl != null && !pl.isDead && !pl.worldObj.isRemote && (st = pl.inventory.getCurrentItem()) != null && st.getItemDamage() == 11 && st.stackSize >= 4 && pl instanceof EntityPlayerMP && pl.worldObj.provider.dimensionId != 150) {
            for (int i = 0; i <= 3; ++i) {
                boolean b = false;
                if (pl.inventory.armorInventory[i] == null) {
                    b = true;
                } else if (!(pl.inventory.armorInventory[i].getItem() instanceof ItemOGArmor)) {
                    b = true;
                }
                if (!b) continue;
                pl.addChatComponentMessage(new ChatComponentText("\u00a7e\u0410\u0441\u0433\u0430\u0440\u0434\u0435\u0446> \u00a7c\u042f \u043d\u0435 \u043c\u043e\u0433\u0443 \u043f\u0443\u0441\u0442\u0438\u0442\u044c \u0442\u0435\u0431\u044f \u0432 \u0410\u0441\u0433\u0430\u0440\u0434 \u0432 \u044d\u0442\u043e\u0439 \u0431\u0440\u043e\u043d\u0435."));
                return true;
            }
            st.stackSize -= 4;
            if (st.stackSize <= 0) {
                st = null;
            }
            pl.inventory.setInventorySlotContents(pl.inventory.currentItem, st);
            pl.addChatComponentMessage(new ChatComponentText("\u00a7f[\u00a76Asgard\u00a7f]: \u041f\u043b\u0430\u0442\u0430 \u043f\u0440\u0438\u043d\u044f\u0442\u0430. \u041d\u043e \u043d\u0435 \u043d\u0430\u0434\u0435\u0439\u0441\u044f, \u0447\u0442\u043e \u0432 \u0441\u043b\u0435\u0434. \u0440\u0430\u0437 \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u0441\u044f \u0442\u0430\u043a\u0436\u0435..."));
            pl.timeUntilPortal = 10;
            MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
            ((EntityPlayerMP)pl).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)pl, 150, new TeleportAsgard(mServer.worldServerForDimension(150)));
        }
        return super.interact(pl);
    }

    public boolean getCanSpawnHere() {
        return true;
    }

    public int getMaxSpawnedInChunk() {
        return 10;
    }
}

