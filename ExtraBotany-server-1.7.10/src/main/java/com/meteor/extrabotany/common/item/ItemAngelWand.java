/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.common.item.ItemMods;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;

public class ItemAngelWand
extends ItemMods
implements IManaUsingItem {
    private static final float RANGE = 1.5f;
    private static final String TAG_TARGET = "target";
    private static final String TAG_TICKS_TILL_EXPIRE = "ticksTillExpire";
    private static final String TAG_DIST = "dist";

    public ItemAngelWand(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    public void onUpdate(ItemStack stack, World world, Entity par3Entity, int p_77663_4_, boolean p_77663_5_) {
        if (par3Entity instanceof EntityPlayer) {
            int ticksTillExpire = ItemNBTHelper.getInt(stack, TAG_TICKS_TILL_EXPIRE, 0);
            if (ticksTillExpire == 0) {
                ItemNBTHelper.setInt(stack, TAG_TARGET, -1);
                ItemNBTHelper.setDouble(stack, TAG_DIST, -1.0);
            }
            ItemNBTHelper.setInt(stack, TAG_TICKS_TILL_EXPIRE, --ticksTillExpire);
        }
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        Vector3 playerVector;
        super.onItemRightClick(stack, world, player);
        int targetID = ItemNBTHelper.getInt(stack, TAG_TARGET, -1);
        double length = ItemNBTHelper.getDouble(stack, TAG_DIST, -1.0);
        Object item = null;
        if (targetID != -1 && player.worldObj.getEntityByID(targetID) != null) {
            Entity targetEntity = player.worldObj.getEntityByID(targetID);
            boolean sourceVector = false;
            playerVector = Vector3.fromEntityCenter(player);
            List motion = new ArrayList();
            for (int distance = 1; motion.size() == 0 && distance < 45; ++distance) {
                playerVector.add(new Vector3(player.getLookVec()).multiply(distance));
                playerVector.y += 0.5;
                motion = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(playerVector.x - 1.5, playerVector.y - 1.5, playerVector.z - 1.5, playerVector.x + 1.5, playerVector.y + 1.5, playerVector.z + 1.5));
                if (!motion.contains(targetEntity)) continue;
                sourceVector = true;
            }
            if (sourceVector) {
                item = player.worldObj.getEntityByID(targetID);
            }
        }
        if (item == null) {
            Vector3 var13 = Vector3.fromEntityCenter(player);
            List var15 = new ArrayList();
            for (int var16 = 1; var15.size() == 0 && var16 < 45; ++var16) {
                var13.add(new Vector3(player.getLookVec()).multiply(var16));
                var13.y += 0.5;
                var15 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(var13.x - 1.5, var13.y - 1.5, var13.z - 1.5, var13.x + 1.5, var13.y + 1.5, var13.z + 1.5));
            }
            if (var15.size() > 0) {
                item = var15.get(0);
                length = 5.5;
                if (item instanceof EntityItem) {
                    length = 2.0;
                }
            }
        }
        if (item != null) {
            if (BotaniaAPI.isEntityBlacklistedFromGravityRod(item.getClass())) {
                return stack;
            }
            if (ManaItemHandler.requestManaExactForTool(stack, player, 1, true)) {
                if (item instanceof EntityLivingBase) {
                    EntityLivingBase var14 = (EntityLivingBase)item;
                    Vector3 var17 = new Vector3(var14.posX + 0.5, var14.posY + 0.5, var14.posZ + 0.5);
                    playerVector = Vector3.fromEntityCenter(player);
                    Vector3 var18 = var17.copy().sub(playerVector).copy().normalize();
                    player.motionX = var18.x * (double)1.4f;
                    player.motionY = var18.y + 0.05;
                    player.motionZ = var18.z * (double)1.4f;
                }
                ItemNBTHelper.setInt(stack, TAG_TARGET, (int) ((Entity) item).getEntityId());
                ItemNBTHelper.setDouble(stack, TAG_DIST, length);
            }
        }
        if (item != null) {
            ItemNBTHelper.setInt(stack, TAG_TICKS_TILL_EXPIRE, 5);
        }
        return stack;
    }

    public boolean usesMana(ItemStack arg0) {
        return true;
    }
}

