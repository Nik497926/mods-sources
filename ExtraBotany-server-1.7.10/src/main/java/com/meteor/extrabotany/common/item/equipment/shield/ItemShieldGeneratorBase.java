/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment.shield;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemShieldGeneratorBase
extends ItemBauble {
    public static final String TAG_CURRENTSHIELD = "currentShield";
    public static final String TAG_CD = "CD";
    public static final String TAG_MAXSHIELD = "maxShield";

    public float getGenerateSpeed() {
        return 0.0f;
    }

    public int getCD() {
        return 0;
    }

    public int getManaCost() {
        return 0;
    }

    public static float getShieldAmount(EntityPlayer player) {
        if (ItemShieldGeneratorBase.getShieldGenerator(player) != null) {
            ItemStack sg = ItemShieldGeneratorBase.getShieldGenerator(player);
            return ItemShieldGeneratorBase.getCurrentShield(sg);
        }
        return 0.0f;
    }

    public static int getShieldCD(EntityPlayer player) {
        if (ItemShieldGeneratorBase.getShieldGenerator(player) != null) {
            ItemStack sg = ItemShieldGeneratorBase.getShieldGenerator(player);
            return ItemShieldGeneratorBase.getCD(sg);
        }
        return 0;
    }

    public static void recoverShield(EntityPlayer player, float f) {
        if (ItemShieldGeneratorBase.getShieldGenerator(player) != null) {
            ItemStack sg = ItemShieldGeneratorBase.getShieldGenerator(player);
            ItemShieldGeneratorBase.setCurrentShield(sg, Math.min(f + ItemShieldGeneratorBase.getCurrentShield(sg), ItemShieldGeneratorBase.getMaxShield(sg)));
        }
    }

    public ItemShieldGeneratorBase(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
        if (GuiScreen.isShiftKeyDown()) {
            ItemShieldGeneratorBase.addStringToTooltip(StatCollector.translateToLocal((String)"botania.shieldgenerator.info0").replace("%A%", String.valueOf(ItemShieldGeneratorBase.getMaxShield(stack))), list);
            ItemShieldGeneratorBase.addStringToTooltip(StatCollector.translateToLocal((String)"botania.shieldgenerator.info1").replace("%A%", String.valueOf(ItemShieldGeneratorBase.getCurrentShield(stack))), list);
            ItemShieldGeneratorBase.addStringToTooltip(StatCollector.translateToLocal((String)"botania.shieldgenerator.info2").replace("%A%", String.valueOf(this.getGenerateSpeed() * 20.0f)), list);
            ItemShieldGeneratorBase.addStringToTooltip(StatCollector.translateToLocal((String)"botania.shieldgenerator.info3").replace("%A%", String.valueOf(this.getCD() / 20)), list);
            ItemShieldGeneratorBase.addStringToTooltip(StatCollector.translateToLocal((String)"botania.shieldgenerator.info4").replace("%A%", String.valueOf(ItemShieldGeneratorBase.getCD(stack) / 20)), list);
        }
    }

    static void addStringToTooltip(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public void onWornTick(ItemStack stack, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (ItemShieldGeneratorBase.getCD(stack) > 0) {
                ItemShieldGeneratorBase.setCD(stack, ItemShieldGeneratorBase.getCD(stack) - 1);
            } else if (ManaItemHandler.requestManaExact((ItemStack)stack, (EntityPlayer)player, (int)Math.max(1, (int)((float)this.getManaCost() * this.getGenerateSpeed() * 20.0f)), (boolean)true)) {
                ItemShieldGeneratorBase.recoverShield(player, this.getGenerateSpeed());
            }
        }
    }

    public static ItemStack getShieldGenerator(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
        ItemStack stack1 = baubles.func_70301_a(1);
        ItemStack stack2 = baubles.func_70301_a(2);
        return ItemShieldGeneratorBase.isShieldGenerator(stack1) ? stack1 : (ItemShieldGeneratorBase.isShieldGenerator(stack2) ? stack2 : null);
    }

    private static boolean isShieldGenerator(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemShieldGeneratorBase;
    }

    @SubscribeEvent
    public void onPlayerAttacked(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && ItemShieldGeneratorBase.getShieldGenerator(player = (EntityPlayer)event.entity) != null) {
            ItemStack sg = ItemShieldGeneratorBase.getShieldGenerator(player);
            int dam = (int)event.ammount;
            ItemShieldGeneratorBase.setCurrentShield(sg, Math.max(ItemShieldGeneratorBase.getCurrentShield(sg) - (float)dam, 0.0f));
            event.ammount = Math.max(event.ammount - ItemShieldGeneratorBase.getCurrentShield(sg), 0.0f);
            ItemShieldGeneratorBase.setCD(sg, this.getCD());
        }
    }

    public static float getMaxShield(ItemStack stack) {
        return ItemNBTHelper.getFloat((ItemStack)stack, (String)TAG_MAXSHIELD, (float)0.0f);
    }

    public static void setMaxShield(ItemStack stack, float i) {
        ItemNBTHelper.setFloat((ItemStack)stack, (String)TAG_MAXSHIELD, (float)i);
    }

    public static float getCurrentShield(ItemStack stack) {
        return ItemNBTHelper.getFloat((ItemStack)stack, (String)TAG_CURRENTSHIELD, (float)0.0f);
    }

    public static void setCurrentShield(ItemStack stack, float i) {
        ItemNBTHelper.setFloat((ItemStack)stack, (String)TAG_CURRENTSHIELD, (float)i);
    }

    public static int getCD(ItemStack stack) {
        return ItemNBTHelper.getInt((ItemStack)stack, (String)TAG_CD, (int)0);
    }

    public static void setCD(ItemStack stack, int i) {
        ItemNBTHelper.setInt((ItemStack)stack, (String)TAG_CD, (int)i);
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }
}

