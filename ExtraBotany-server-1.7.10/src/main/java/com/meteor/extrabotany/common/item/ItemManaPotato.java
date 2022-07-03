/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.common.item.ItemMods;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemManaPotato
extends ItemMods
implements IManaItem,
IManaTooltipDisplay {
    private static final int MAX_MANA = 10000;
    private static final String TAG_MANA = "mana";
    private static final String TAG_ONE_USE = "oneUse";

    public ItemManaPotato(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
        this.setNoRepair();
    }

    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1));
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt((ItemStack)stack, (String)TAG_MANA, (int)mana);
    }

    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt((ItemStack)stack, (String)TAG_MANA, (int)0);
    }

    public int getMaxMana(ItemStack stack) {
        return 10000;
    }

    public void addMana(ItemStack stack, int mana) {
        ItemManaPotato.setMana(stack, Math.min(this.getMana(stack) + mana, 10000));
    }

    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return !ItemNBTHelper.getBoolean((ItemStack)stack, (String)TAG_ONE_USE, (boolean)false);
    }

    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return true;
    }

    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return true;
    }

    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return true;
    }

    public boolean isNoExport(ItemStack stack) {
        return false;
    }

    public float getManaFractionForDisplay(ItemStack stack) {
        return (float)this.getMana(stack) / (float)this.getMaxMana(stack);
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - (double)this.getManaFractionForDisplay(stack);
    }
}

