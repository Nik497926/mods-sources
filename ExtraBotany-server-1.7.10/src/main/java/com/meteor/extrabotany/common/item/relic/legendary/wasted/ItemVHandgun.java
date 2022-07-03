/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.wasted;

import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemVHandgun
extends ItemRelicAdv
implements ILensEffect {
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    private static final String TAG_HOME_ID = "homeID";

    public ItemVHandgun() {
        super("vhandgun_double");
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (ItemRelic.isRightPlayer((EntityPlayer)par3EntityPlayer, (ItemStack)par1ItemStack)) {
            EntityManaBurst burst = this.getBurst(par3EntityPlayer, par1ItemStack);
            par2World.spawnEntityInWorld((Entity)burst);
            par2World.playSoundAtEntity((Entity)par3EntityPlayer, "botania:terraBlade", 0.4f, 1.4f);
        }
        return par1ItemStack;
    }

    public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
        EntityManaBurst burst = new EntityManaBurst(player);
        float motionModifier = 25.0f;
        burst.setColor(6314849);
        burst.setMana(1);
        burst.setStartingMana(1);
        burst.setMinManaLoss(100);
        burst.setManaLossPerTick(1.0f);
        burst.setGravity(0.0f);
        burst.setMotion(burst.motionX * (double)motionModifier, burst.motionY * (double)motionModifier, burst.motionZ * (double)motionModifier);
        ItemStack lens = stack.copy();
        ItemNBTHelper.setString((ItemStack)lens, (String)TAG_ATTACKER_USERNAME, (String)player.getCommandSenderName());
        burst.setSourceLens(lens);
        return burst;
    }

    public void apply(ItemStack stack, BurstProperties props) {
    }

    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return dead;
    }

    public void updateBurst(IManaBurst burst, ItemStack stack) {
        Entity i$1;
        EntityThrowable entity = (EntityThrowable)burst;
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox((double)entity.posX, (double)entity.posY, (double)entity.posZ, (double)entity.lastTickPosX, (double)entity.lastTickPosY, (double)entity.lastTickPosZ).expand(1.0, 1.0, 1.0);
        String attacker = ItemNBTHelper.getString((ItemStack)burst.getSourceLens(), (String)TAG_ATTACKER_USERNAME, (String)"");
        int homeID = ItemNBTHelper.getInt((ItemStack)stack, (String)TAG_HOME_ID, (int)-1);
        if (homeID == -1) {
            AxisAlignedBB entities = AxisAlignedBB.getBoundingBox((double)entity.posX, (double)entity.posY, (double)entity.posZ, (double)entity.lastTickPosX, (double)entity.lastTickPosY, (double)entity.lastTickPosZ).expand(5.0, 5.0, 5.0);
            List<EntityLivingBase> i$ = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entities);
            for (EntityLivingBase cost : i$) {
                if (cost instanceof EntityPlayer || !(cost instanceof IMob) || cost.hurtTime != 0) continue;
                homeID = cost.getEntityId();
                ItemNBTHelper.setInt((ItemStack)stack, (String)TAG_HOME_ID, (int)homeID);
                break;
            }
        }
        List<EntityLivingBase> entities1 = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
        if (homeID != -1 && (i$1 = entity.worldObj.getEntityByID(homeID)) != null) {
            Vector3 living1 = Vector3.fromEntityCenter((Entity)i$1);
            Vector3 cost1 = Vector3.fromEntityCenter((Entity)entity);
            Vector3 mana = living1.sub(cost1);
            Vector3 damage = new Vector3(entity.motionX, entity.motionY, entity.motionZ);
            mana.normalize().multiply(damage.mag());
            burst.setMotion(mana.x, mana.y, mana.z);
        }
        for (EntityLivingBase living2 : entities1) {
            if (living2 instanceof EntityPlayer && (((EntityPlayer)living2).getCommandSenderName().equals(attacker) || MinecraftServer.getServer() != null && !MinecraftServer.getServer().isPVPEnabled()) || living2.hurtTime != 0) continue;
            int cost2 = 1;
            int mana1 = burst.getMana();
            if (mana1 < cost2) continue;
            burst.setMana(mana1 - cost2);
            float damage1 = 4.0f;
            if (burst.isFake() || entity.worldObj.isRemote) continue;
            EntityPlayer player = living2.worldObj.getPlayerEntityByName(attacker);
            living2.attackEntityFrom(player == null ? DamageSource.magic : DamageSource.causePlayerDamage((EntityPlayer)player), damage1);
            entity.setDead();
            break;
        }
    }

    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
    }
}

