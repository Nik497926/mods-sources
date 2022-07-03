/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ToolHandler {
    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, double range) {
        float f = 1.0f;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f;
        if (!world.isRemote && player instanceof EntityPlayer) {
            d1 += 1.62;
        }
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
        Vec3 vec3 = Vec3.createVectorHelper((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.cos((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.sin((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.cos((float)(-f1 * ((float)Math.PI / 180)));
        float f6 = MathHelper.sin((float)(-f1 * ((float)Math.PI / 180)));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP && range < 10.0) {
            d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.rayTraceBlocks(vec3, vec31);
    }

    public static void updateGhostBlocks(EntityPlayer player, World world) {
        if (world.isRemote) {
            return;
        }
        int xPos = (int)player.posX;
        int yPos = (int)player.posY;
        int zPos = (int)player.posZ;
        for (int x = xPos - 6; x < xPos + 6; ++x) {
            for (int y = yPos - 6; y < yPos + 6; ++y) {
                for (int z = zPos - 6; z < zPos + 6; ++z) {
                    ((EntityPlayerMP)player).playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange(x, y, z, world));
                }
            }
        }
    }

    public static float getBaseAttackDamage(ItemStack stack) {
        float dmg = 0.0f;
        if (stack == null) {
            return 1.0f;
        }
        float sharpMod = (float)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)stack) * 4.0f;
        if (stack.getItem() == ModItems.awakepick) {
            dmg = 0.0f;
        } else if (stack.getItem() instanceof ItemSword) {
            dmg = ((ItemSword)stack.getItem()).func_150931_i() + sharpMod;
        }
        return dmg;
    }

    public static float getDamageAgainstEntity(ItemStack stack, Entity entity) {
        float baseAttack = ToolHandler.getBaseAttackDamage(stack);
        float smiteMod = (float)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.smite.effectId, (ItemStack)stack) * 6.0f;
        float athropodsMod = (float)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.baneOfArthropods.effectId, (ItemStack)stack) * 6.0f;
        if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isEntityUndead()) {
            baseAttack += smiteMod;
        }
        if (entity instanceof EntitySpider) {
            baseAttack += athropodsMod;
        }
        return baseAttack;
    }
}

