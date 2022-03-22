/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.wasted;

import com.meteor.extrabotany.common.core.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.relic.ItemRelic;

import javax.swing.text.html.ObjectView;

public class ItemTheseusShip
extends ItemRelicAdv
implements ILensEffect,
IManaUsingItem {
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    public static final String TAG_MODE = "mode";
    public static final String TAG_DELAY = "delay";

    public ItemTheseusShip(String name) {
        super(name);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        super.onUpdate(stack, world, entity, i, b);
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (ItemTheseusShip.getDelay(stack) > 0) {
                ItemTheseusShip.setDelay(stack, ItemTheseusShip.getDelay(stack) - 1);
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        super.onUsingTick(stack, player, count);
        int m = ItemTheseusShip.getMode(stack);
        if (!player.worldObj.isRemote) {
            if (player.isSneaking()) {
                if (count <= this.getMaxItemUseDuration(stack) - 40 && ItemTheseusShip.getDelay(stack) == 0) {
                    int cost = ItemTheseusShip.getMode(stack) == 3 ? 0 : ItemTheseusShip.getMode(stack) + 1;
                    ItemTheseusShip.setMode(stack, cost);
                    ItemTheseusShip.setDelay(stack, 20);
                    player.addChatMessage(new ChatComponentTranslation("botaniamisc.theseussetMode" + ItemTheseusShip.getMode(stack)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
                    player.stopUsingItem();
                }
            } else if (count % 3 == 0 && !player.isSneaking() && count <= this.getMaxItemUseDuration(stack) - 10 && ItemRelic.isRightPlayer(player, stack)) {
                int cost = m == 3 ? 1 : (m == 2 ? 2 : (cost = m == 1 ? 5 : 2));
                if (ManaItemHandler.requestManaExact(stack, player, cost, true)) {
                    player.worldObj.spawnEntityInWorld(this.getBurst(player, stack));
                }
            }
        }
    }

    public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
        EntityManaBurst burst = new EntityManaBurst(player);
        float motionModifier = 7.0f;
        int mode = ItemTheseusShip.getMode(stack);
        burst.setColor(mode == 3 ? 15958315 : (mode == 2 ? 7199809 : (mode == 1 ? 3639014 : 14500035)));
        burst.setMana(1);
        burst.setStartingMana(1);
        burst.setMinManaLoss(100);
        burst.setManaLossPerTick(10.0f);
        burst.setGravity(0.0f);
        burst.setMotion(burst.motionX * (double)motionModifier, burst.motionY * (double)motionModifier, burst.motionZ * (double)motionModifier);
        ItemStack lens = stack.copy();
        ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.getCommandSenderName());
        burst.setSourceLens(lens);
        return burst;
    }

    public boolean usesMana(ItemStack arg0) {
        return true;
    }

    public void apply(ItemStack arg0, BurstProperties arg1) {
    }

    public boolean collideBurst(IManaBurst arg0, MovingObjectPosition arg1, boolean arg2, boolean dead, ItemStack arg4) {
        return dead;
    }

    public boolean doParticles(IManaBurst arg0, ItemStack arg1) {
        return true;
    }

    public void updateBurst(IManaBurst burst, ItemStack stack) {
        List livings;
        EntityThrowable entity = (EntityThrowable)burst;
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(1.0, 1.0, 1.0);
        AxisAlignedBB axisbig = AxisAlignedBB.getBoundingBox(entity.posX - (double)0.2f, entity.posY - (double)0.2f, entity.posZ - (double)0.2f, entity.lastTickPosX + (double)0.2f, entity.lastTickPosY + (double)0.2f, entity.lastTickPosZ + (double)0.2f).expand(1.0, 1.0, 1.0);
        String attacker = ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, "");
        if (entity.ticksExisted > 3) {
            entity.setDead();
        }
        if (ItemTheseusShip.getMode(stack) == 0) {
            livings = entity.worldObj.getEntitiesWithinAABB(IMob.class, axis);
            for (Object l : livings) {
                EntityLiving s = (EntityLiving)l;
                if (s.hurtTime != 0) continue;
                int cost = 1;
                int mana = burst.getMana();
                if (mana < cost) continue;
                burst.setMana(mana - cost);
                float damage = 3.0f;
                if (burst.isFake() || entity.worldObj.isRemote) continue;
                EntityPlayer player = s.worldObj.getPlayerEntityByName(attacker);
                s.attackEntityFrom(player == null ? DamageSource.magic : DamageSource.causePlayerDamage(player), damage);
                entity.setDead();
                break;
            }
        }
        if (ItemTheseusShip.getMode(stack) == 1) {
            livings = entity.worldObj.getEntitiesWithinAABB(EntityItem.class, axisbig);
            for (Object l : livings) {
                EntityItem l1 = (EntityItem)l;
                ItemStack s1;
                if (l1.age <= 59 || (s1 = l1.getEntityItem()).getItemDamage() <= 0 || burst.isFake() || entity.worldObj.isRemote) continue;
                s1.setItemDamage(Math.max(0, s1.getItemDamage() - 1));
                entity.setDead();
                break;
            }
        }
        if (ItemTheseusShip.getMode(stack) == 2) {
            livings = entity.worldObj.getEntitiesWithinAABB(EntityLiving.class, axis);
            for (Object l : livings) {
                EntityLiving l2 = (EntityLiving)l;
                if (l2 instanceof IMob) continue;
                l2.heal(3.0f);
            }
        }
        if (ItemTheseusShip.getMode(stack) == 3) {
            livings = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
            for (Object l : livings) {
                EntityLivingBase l3 = (EntityLivingBase) l;
                if (l3 instanceof IMob || l3 == entity.getThrower()) continue;
                l3.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 1, 50));
                l3.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 1, 50));
                if (!(l3 instanceof EntityPlayer)) continue;
                PropertyHandler.addShieldAmount(0.2f, (EntityPlayer)l3);
            }
        }
    }

    public static int getMode(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MODE, 0);
    }

    public static void setMode(ItemStack stack, int i) {
        ItemNBTHelper.setInt(stack, TAG_MODE, i);
    }

    public static int getDelay(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_DELAY, 0);
    }

    public static void setDelay(ItemStack stack, int i) {
        ItemNBTHelper.setInt(stack, TAG_DELAY, i);
    }

    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemTheseusShip.addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
    }

    public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
        ItemTheseusShip.addStringToTooltip(EnumChatFormatting.BLUE + StatCollector.translateToLocal("botaniamisc.theseusmode" + ItemTheseusShip.getMode(stack) + ".desc"), list);
        if (GuiScreen.isShiftKeyDown()) {
            String bind = ItemTheseusShip.getSoulbindUsernameS(stack);
            if (bind.isEmpty()) {
                ItemTheseusShip.addStringToTooltip(StatCollector.translateToLocal("botaniamisc.relicUnbound"), list);
            } else {
                ItemTheseusShip.addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.relicSoulbound"), bind), list);
                if (!ItemTheseusShip.isRightPlayer(player, stack)) {
                    ItemTheseusShip.addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.notYourSagittarius"), bind), list);
                }
            }
        } else {
            ItemTheseusShip.addStringToTooltip(StatCollector.translateToLocal("botaniamisc.shiftinfo"), list);
        }
    }

    static void addStringToTooltip(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.bow;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }
}

