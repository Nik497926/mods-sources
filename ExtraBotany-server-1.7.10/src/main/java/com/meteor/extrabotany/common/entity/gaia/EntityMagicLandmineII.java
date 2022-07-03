/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.EntityOdin;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIV;
import com.meteor.extrabotany.common.entity.gaia.IMinion;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;

public class EntityMagicLandmineII
extends Entity
implements IMinion {
    public EntityGaiaIII summoner;
    public EntityExMachine summoner2;
    public EntityOdin summonerOdin;
    public EntityGaiaIV summoner3;
    public static DamageSource damageOdin = new DamageSource("eb.damageOdin").setDamageAllowedInCreativeMode().setDamageBypassesArmor().setDamageIsAbsolute();

    public EntityMagicLandmineII(World par1World) {
        super(par1World);
        this.setSize(0.0f, 0.0f);
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    public void onUpdate() {
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        super.onUpdate();
        float range = 2.5f;
        float r = 0.2f;
        float g = 0.0f;
        float b = 0.2f;
        for (int m = 0; m < 6; ++m) {
            Botania.proxy.wispFX(this.worldObj, this.posX - (double)range + Math.random() * (double)range * 2.0, this.posY, this.posZ - (double)range + Math.random() * (double)range * 2.0, r, g, b, 0.4f, -0.015f, 1.0f);
        }
        if (this.ticksExisted >= 55) {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "botania:gaiaTrap", 0.3f, 1.0f);
            float var10 = 0.35f;
            g = 0.4f;
            for (int players = 0; players < 25; ++players) {
                Botania.proxy.wispFX(this.worldObj, this.posX, this.posY + 1.0, this.posZ, r, g, b, 0.5f, (float)(Math.random() - 0.5) * var10, (float)(Math.random() - 0.5) * var10, (float)(Math.random() - 0.5) * var10);
            }
            if (!this.worldObj.isRemote) {
                List<EntityPlayer> var11 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)(this.posX - (double)range), (double)(this.posY - (double)range), (double)(this.posZ - (double)range), (double)(this.posX + (double)range), (double)(this.posY + (double)range), (double)(this.posZ + (double)range)));
                for (EntityPlayer player : var11) {
                    if (this.summonerOdin != null) {
                        if (Math.random() <= 0.1) {
                            player.setHealth(player.getHealth() - 2.0f);
                            player.addChatComponentMessage((IChatComponent)new ChatComponentText("\u00a7e\u0410\u0442\u0430\u043a\u0430 \u041e\u0434\u0438\u043d\u0430 \u0431\u044b\u043b\u0430 \u043c\u0435\u043d\u0442\u0430\u043b\u044c\u043d\u043e\u0439, \u0412\u0430\u0448\u0435 hp \u0443\u043f\u0430\u043b\u043e"));
                            player.setHealth(Math.max(1.0f, player.getHealth()));
                        } else {
                            player.attackEntityFrom(damageOdin, 12.0f);
                        }
                    } else {
                        player.attackEntityFrom(this.summoner == null && this.summoner3 == null ? DamageSource.generic : DamageSource.causeMobDamage((EntityLivingBase)(this.summoner == null ? this.summoner3 : this.summoner)), 8.0f);
                    }
                    player.addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 1));
                    player.addPotionEffect(new PotionEffect(Potion.weakness.id, 30, 1));
                }
            }
            this.setDead();
        }
    }

    protected void entityInit() {
    }

    protected void readEntityFromNBT(NBTTagCompound var1) {
    }

    protected void writeEntityToNBT(NBTTagCompound var1) {
    }
}

