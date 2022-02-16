/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import java.util.List;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.projectile.EntityRaglokBomb;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRaglok
extends EntityDivineRPGBoss {
    private int ability;
    private final int DEFAULT = 0;
    private final int LIGHTNING = 1;
    private final int BLIND = 2;
    private final int BOMBS = 3;
    private final int SLOW = 4;
    private double prevPlayerX;
    private double prevPlayerY;
    private double prevPlayerZ;
    private int abilityCooldown;
    private boolean loaded = false;
    private int rangedAttackCounter;
    private int deathTicks;

    public EntityRaglok(World par1) {
        super(par1);
        this.addAttackingAI();
        this.isImmuneToFire = true;
        this.ability = 0;
        this.setSize(1.5f, 4.0f);
    }

    protected void updateAITasks() {
        this.manageAbilities();
        super.updateAITasks();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.raglokHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.raglokDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.raglokSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.raglokFollowRange);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.loaded && !this.worldObj.isRemote) {
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
            for (EntityPlayer p : players) {
                p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.dare")));
                this.worldObj.playSoundAtEntity((Entity)p, Sounds.raglokAwaken.getPrefixedName(), 1.0f, 1.0f);
            }
            this.loaded = true;
        }
    }

    public void manageAbilities() {
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (this.abilityCooldown == 0 || this.ability == 0) {
            this.abilityCooldown = 400;
            switch (this.rand.nextInt(4)) {
                case 0: {
                    this.ability = 1;
                    this.rangedAttackCounter = 0;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0);
                    break;
                }
                case 1: {
                    this.ability = 2;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
                    break;
                }
                case 2: {
                    this.ability = 3;
                    this.rangedAttackCounter = 0;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
                    break;
                }
                case 3: {
                    this.ability = 4;
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
                    break;
                }
            }
            this.message();
        } else if (this.abilityCooldown > 0) {
            --this.abilityCooldown;
        }
        if (this.ability == 2 && player != null) {
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 25, 0, true));
        }
        if (this.ability == 4 && player != null) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 1, true));
        }
        if (this.ability == 1) {
            if (this.abilityCooldown % 40 == 0 && player != null) {
                this.worldObj.spawnEntityInWorld((Entity)new EntityLightningBolt(this.worldObj, this.prevPlayerX, this.prevPlayerY, this.prevPlayerZ));
                ++this.rangedAttackCounter;
            } else if (this.abilityCooldown % 40 != 0 && this.abilityCooldown % 20 == 0 && player != null) {
                this.prevPlayerX = player.posX;
                this.prevPlayerY = player.posY;
                this.prevPlayerZ = player.posZ;
            }
            if (this.rangedAttackCounter == 10) {
                this.ability = 0;
            }
        }
        if (this.ability == 3) {
            if (this.abilityCooldown % 30 == 0 && player != null) {
                for (int i = 0; i < 4; ++i) {
                    EntityRaglokBomb var2 = new EntityRaglokBomb(this.worldObj);
                    var2.setPosition(player.posX, player.posY + 5.0, player.posZ);
                    var2.motionX = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    var2.motionY = -0.14;
                    var2.motionZ = (this.rand.nextDouble() - this.rand.nextDouble()) / 5.0;
                    this.worldObj.spawnEntityInWorld((Entity)var2);
                    ++this.rangedAttackCounter;
                }
            }
            if (this.rangedAttackCounter == 12) {
                this.ability = 0;
            }
        }
    }

    private void message() {
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(64.0, 64.0, 64.0));
        block6: for (int var1 = 0; var1 < list.size(); ++var1) {
            if (!(list.get(var1) instanceof EntityPlayer)) continue;
            EntityPlayer player = (EntityPlayer)list.get(var1);
            switch (this.ability) {
                case 1: {
                    this.playSound(Sounds.raglokGuardian.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block6;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.think")));
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.great")));
                    continue block6;
                }
                case 2: {
                    this.playSound(Sounds.raglokDark.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block6;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.future")));
                    continue block6;
                }
                case 3: {
                    this.playSound(Sounds.raglokRain.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block6;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.rain")));
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.kill")));
                    continue block6;
                }
                case 4: {
                    this.playSound(Sounds.raglokNothing.getPrefixedName(), 1.0f, 1.0f);
                    if (this.worldObj.isRemote) continue block6;
                    player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.compare")));
                    continue block6;
                }
            }
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (Object)new Integer(100));
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    protected String getDeathSound() {
        EntityPlayer player;
        if (!this.worldObj.isRemote) {
            List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(30.0, 30.0, 30.0));
            for (EntityPlayer p : players) {
                p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("message.raglok.avenge")));
            }
        }
        if ((player = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0)) != null) {
            for (int i = 0; i < 10; ++i) {
                this.worldObj.spawnEntityInWorld((Entity)new EntityLightningBolt(this.worldObj, player.posX, player.posY, player.posZ));
            }
        }
        return Sounds.raglokAvenge.getPrefixedName();
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        int var2 = (int)EntityStats.raglokDamage;
        int var3 = 0;
        boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (float)var2);
        if (var4) {
            int var5;
            if (this.ability == 4) {
                var3 = 5;
            }
            if (var3 > 0) {
                par1Entity.addVelocity((double)(-MathHelper.sin((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.rotationYaw * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            if ((var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this)) > 0) {
                par1Entity.setFire(var5 * 4);
            }
        }
        return var4;
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isExplosion()) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.heliosisLump, 25);
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
            if (this.deathTicks == 1 && this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0) != null) {
                this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0).attackEntityFrom(DamageSource.magic, 16.0f);
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
        return "Raglok";
    }

    @Override
    public String name() {
        return "Raglok";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

