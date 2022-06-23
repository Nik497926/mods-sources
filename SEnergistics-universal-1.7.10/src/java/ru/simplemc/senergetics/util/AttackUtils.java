/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.util;

import cpw.mods.fml.common.eventhandler.Event;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class AttackUtils {
    private static String GET_EXPERIENCE_METHOD_NAME = "getExperiencePoints";

    public static int getExperiencePoints(EntityLivingBase entity, EntityPlayer player) {
        try {
            Method method = EntityLivingBase.class.getDeclaredMethod(GET_EXPERIENCE_METHOD_NAME, EntityPlayer.class);
            method.setAccessible(true);
            return (Integer)method.invoke(entity, player);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
            if (!GET_EXPERIENCE_METHOD_NAME.equals("getExperiencePoints")) {
                GET_EXPERIENCE_METHOD_NAME = "getExperiencePoints";
                return AttackUtils.getExperiencePoints(entity, player);
            }
            return 0;
        }
    }

    public static void attackTargetEntityWithCurrentItem(FakePlayer fakePlayer, EntityLivingBase victim) {
        if (MinecraftForge.EVENT_BUS.post((Event)new AttackEntityEvent((EntityPlayer)fakePlayer, (Entity)victim))) {
            return;
        }
        ItemStack stack = fakePlayer.getCurrentEquippedItem();
        if (stack != null && stack.getItem().onLeftClickEntity(stack, (EntityPlayer)fakePlayer, (Entity)victim)) {
            return;
        }
        if (victim.canAttackWithItem() && !victim.hitByEntity((Entity)fakePlayer)) {
            float attackDamage = (float)fakePlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            int fireAspectModifier = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)fakePlayer);
            if (fireAspectModifier > 0) {
                victim.setFire(fireAspectModifier * 4);
            }
            if (victim.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)fakePlayer), attackDamage)) {
                fakePlayer.setLastAttacker((Entity)victim);
                EnchantmentHelper.func_151384_a((EntityLivingBase)victim, (Entity)fakePlayer);
                EnchantmentHelper.func_151385_b((EntityLivingBase)fakePlayer, (Entity)victim);
                ItemStack itemstack = fakePlayer.getCurrentEquippedItem();
                if (itemstack != null) {
                    itemstack.hitEntity(victim, (EntityPlayer)fakePlayer);
                    if (itemstack.stackSize <= 0) {
                        fakePlayer.destroyCurrentEquippedItem();
                    }
                }
                fakePlayer.addExhaustion(0.3f);
            }
        }
    }
}

