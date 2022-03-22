/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary;

import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

public class ItemEternalSlience
extends ItemRelicAdv
implements IManaItem,
IManaTooltipDisplay {
    private static final int MAX_MANA = 50000;
    private static final String TAG_MANA = "mana";
    private static final String TAG_ONE_USE = "oneUse";
    private static final String TAG_MODE = "mode";
    private static final int DELAY = 30;
    private static final int RANGE = 7;
    private final int cd = 0;

    public ItemEternalSlience(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
        this.setNoRepair();
    }

    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1));
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (!player.worldObj.isRemote && count <= this.getMaxItemUseDuration(stack) - 30 && player.isSneaking()) {
            this.setMode(stack, !ItemEternalSlience.isMode(stack));
            player.addChatMessage(new ChatComponentTranslation("botaniamisc.eternalslience" + ItemEternalSlience.isMode(stack)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN)));
            player.stopUsingItem();
        }
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 400000;
    }

    private boolean soloItem(EntityPlayer player, int slotID) {
        InventoryPlayer inv = player.inventory;
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack var0 = inv.getStackInSlot(i);
            if (var0 == null || !(var0.getItem() instanceof ItemEternalSlience) || !ItemEternalSlience.isMode(var0) || i >= slotID) continue;
            return false;
        }
        return true;
    }

    public static boolean isMode(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_MODE, false);
    }

    public void setMode(ItemStack stack, boolean b) {
        ItemNBTHelper.setBoolean(stack, TAG_MODE, b);
    }

    public float getManaFractionForDisplay(ItemStack stack) {
        return (float)this.getMana(stack) / (float)this.getMaxMana(stack);
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    public void addMana(ItemStack stack, int mana) {
        ItemEternalSlience.setMana(stack, Math.min(this.getMana(stack) + mana, 50000));
    }

    public boolean canExportManaToItem(ItemStack arg0, ItemStack arg1) {
        return true;
    }

    public boolean canExportManaToPool(ItemStack arg0, TileEntity arg1) {
        return false;
    }

    public boolean canReceiveManaFromItem(ItemStack arg0, ItemStack arg1) {
        return false;
    }

    public boolean canReceiveManaFromPool(ItemStack arg0, TileEntity arg1) {
        return false;
    }

    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    public int getMaxMana(ItemStack stack) {
        return 50000;
    }

    public boolean isNoExport(ItemStack arg0) {
        return false;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - (double)this.getManaFractionForDisplay(stack);
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.block;
    }

    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemEternalSlience.addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
    }

    public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
        ItemEternalSlience.addStringToTooltip(EnumChatFormatting.BLUE + StatCollector.translateToLocal("botaniamisc.eternalmode" + ItemEternalSlience.isMode(stack)), list);
        if (GuiScreen.isShiftKeyDown()) {
            String bind = ItemEternalSlience.getSoulbindUsernameS(stack);
            if (bind.isEmpty()) {
                ItemEternalSlience.addStringToTooltip(StatCollector.translateToLocal("botaniamisc.relicUnbound"), list);
            } else {
                ItemEternalSlience.addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.relicSoulbound"), bind), list);
                if (!ItemEternalSlience.isRightPlayer(player, stack)) {
                    ItemEternalSlience.addStringToTooltip(String.format(StatCollector.translateToLocal("botaniamisc.notYourSagittarius"), bind), list);
                }
            }
            if (stack.getItem() == ModItems.aesirRing) {
                ItemEternalSlience.addStringToTooltip(StatCollector.translateToLocal("botaniamisc.dropIkea"), list);
            }
            if (stack.getItem() == ModItems.dice) {
                ItemEternalSlience.addStringToTooltip("", list);
                String name = stack.getUnlocalizedName() + ".poem";
                for (int i = 0; i < 4; ++i) {
                    ItemEternalSlience.addStringToTooltip(EnumChatFormatting.ITALIC + StatCollector.translateToLocal(name + i), list);
                }
            }
        } else {
            ItemEternalSlience.addStringToTooltip(StatCollector.translateToLocal("botaniamisc.shiftinfo"), list);
        }
    }

    static void addStringToTooltip(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }
}

