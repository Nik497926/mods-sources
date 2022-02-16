/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import java.util.List;
import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityQuadro
extends EntityDivineRPGBoss {
    public int ability;
    private final int SLOW = 0;
    private final int FAST = 1;
    private final int MSLOW = 2;
    private final int MFAST = 3;
    private int abilityCoolDown;
    private int rangedAttackCounter;
    public boolean dir;
    private int deathTicks;

    public EntityQuadro(World w) {
        super(w);
        this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 80.0f));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.ability = 0;
        this.setSize(2.0f, 2.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(22.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.quadroHealth);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.quadroSpeedFast);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.quadroFollowRange);
    }

    protected void updateAITasks() {
        if (!this.worldObj.isRemote && this.entityToAttack != null && this.entityToAttack instanceof EntityLivingBase) {
            this.attackEntityWithRangedAttack((EntityLivingBase)this.entityToAttack, 0.0f);
        }
        if (this.abilityCoolDown == 0) {
            this.ability = this.rand.nextInt(4);
            this.abilityCoolDown = 500;
            this.rangedAttackCounter = 0;
            this.dir = true;
            int s = this.rand.nextInt(9);
            List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
            block10: for (EntityPlayer p : players) {
                switch (s) {
                    case 0: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroDieBefore.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.die")));
                        continue block10;
                    }
                    case 1: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroEnough.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.enough")));
                        continue block10;
                    }
                    case 2: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroPunch.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.punch")));
                        continue block10;
                    }
                    case 3: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroIsNext.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.next")));
                        continue block10;
                    }
                    case 4: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroKillMine.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.mine")));
                        continue block10;
                    }
                    case 5: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroMyKill.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.kill")));
                        continue block10;
                    }
                    case 6: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroNoDie.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.no")));
                        continue block10;
                    }
                    case 7: {
                        this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroSitDown.getPrefixedName(), 1.0f, 1.0f);
                        if (this.worldObj.isRemote) continue block10;
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.sit")));
                        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.deserve")));
                        continue block10;
                    }
                }
                this.worldObj.playSoundAtEntity((Entity)p, Sounds.quadroTasteFist.getPrefixedName(), 1.0f, 1.0f);
                if (this.worldObj.isRemote) continue;
                p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.quadro.taste")));
            }
        }
        if (this.abilityCoolDown == 480) {
            --this.abilityCoolDown;
            this.dir = false;
        }
        if (this.abilityCoolDown > 0) {
            --this.abilityCoolDown;
        }
        if (this.ability == 2) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.quadroSpeedSlow);
            this.setAIMoveSpeed((float)EntityStats.quadroSpeedSlow);
        } else if (this.ability == 3) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.quadroSpeedFast);
            this.setAIMoveSpeed((float)EntityStats.quadroSpeedFast);
        } else if (this.ability == 0) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
            this.setAIMoveSpeed(0.0f);
        } else if (this.ability == 1) {
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
            this.setAIMoveSpeed(0.0f);
        }
        super.updateAITasks();
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return null;
    }

    @Override
    protected String getHurtSound() {
        return this.getLivingSound();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.quadroticLump, 25);
    }

    public void attackEntityWithRangedAttack(EntityLivingBase par1, float par2) {
        switch (this.ability) {
            case 1: {
                if (this.rangedAttackCounter % 5 == 0) {
                    EntityDivineArrow var2 = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, par1, 1.6f, 6.0f, 1.0f, "quadroArrow");
                    this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
                    this.worldObj.spawnEntityInWorld((Entity)var2);
                }
                ++this.rangedAttackCounter;
                break;
            }
            case 0: {
                if (this.rangedAttackCounter % 15 == 0) {
                    EntityDivineArrow var4 = new EntityDivineArrow(this.worldObj, (EntityLivingBase)this, par1, 1.6f, 6.0f, 2.0f, "quadroArrow");
                    this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
                    this.worldObj.spawnEntityInWorld((Entity)var4);
                }
                ++this.rangedAttackCounter;
                break;
            }
        }
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;
        if (this.deathTicks >= 180 && this.deathTicks <= 200) {
            float var1 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            float var2 = (this.rand.nextFloat() - 0.5f) * 4.0f;
            float var3 = (this.rand.nextFloat() - 0.5f) * 8.0f;
            this.worldObj.spawnParticle("hugeexplosion", this.posX + (double)var1, this.posY + 2.0 + (double)var2, this.posZ + (double)var3, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote) {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0) {
                int var5;
                for (int var4 = 1000; var4 > 0; var4 -= var5) {
                    var5 = EntityXPOrb.getXPSplit((int)var4);
                    this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
                }
            }
            if (this.deathTicks == 1) {
                this.worldObj.playBroadcastSound(1018, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            }
        }
        this.moveEntity(0.0, 0.1f, 0.0);
        this.renderYawOffset = this.rotationYaw += 20.0f;
        if (this.deathTicks == 200 && !this.worldObj.isRemote) {
            int var5;
            for (int var4 = 2000; var4 > 0; var4 -= var5) {
                var5 = EntityXPOrb.getXPSplit((int)var4);
                this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
            }
            this.setDead();
        }
    }

    @Override
    public String mobName() {
        return "Quadro";
    }

    @Override
    public String name() {
        return "Quadro";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

