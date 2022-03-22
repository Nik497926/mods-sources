/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ICreativeManaProvider;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemElfTablet
extends ItemMod
implements IManaItem,
ICreativeManaProvider,
IManaTooltipDisplay {
    IIcon[] icons;
    private static final int MAX_MANA = 8000000;
    private static final String TAG_MANA = "mana";
    private static final String TAG_CREATIVE = "creative";
    private static final String TAG_ONE_USE = "oneUse";

    public ItemElfTablet() {
        this.setMaxStackSize(1);
        this.setMaxDamage(1000);
        this.setUnlocalizedName("elfTablet");
        this.setNoRepair();
    }

    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par2CreativeTabs = CreativeTabs.tabMaterials;
        par3List.add(new ItemStack(par1, 1));
        ItemStack fullPower = new ItemStack(par1, 1);
        ItemElfTablet.setMana(fullPower, 8000000);
        par3List.add(fullPower);
        ItemStack creative = new ItemStack(par1, 1);
        ItemElfTablet.setMana(creative, 8000000);
        ItemElfTablet.setStackCreative(creative);
        par3List.add(creative);
    }

    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        float mana = this.getMana(par1ItemStack);
        return par2 == 1 ? Color.HSBtoRGB(0.499f, mana / 8000000.0f, 1.0f) : 0xFFFFFF;
    }

    public int getDamage(ItemStack stack) {
        if (super.getDamage(stack) != 0) {
            super.setDamage(stack, 0);
        }
        return 0;
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, this, i);
        }
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icons[Math.min(1, pass)];
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (ItemElfTablet.isStackCreative(par1ItemStack)) {
            par3List.add(StatCollector.translateToLocal("botaniamisc.creative"));
        }
    }

    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    public static void setStackCreative(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, TAG_CREATIVE, true);
    }

    public static boolean isStackCreative(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_CREATIVE, false);
    }

    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    public int getMaxMana(ItemStack stack) {
        return ItemElfTablet.isStackCreative(stack) ? 8001000 : 8000000;
    }

    public void addMana(ItemStack stack, int mana) {
        if (!ItemElfTablet.isStackCreative(stack)) {
            ItemElfTablet.setMana(stack, Math.min(this.getMana(stack) + mana, 8000000));
        }
    }

    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return !ItemNBTHelper.getBoolean(stack, TAG_ONE_USE, false);
    }

    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return !this.isCreative(stack);
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

    public boolean isCreative(ItemStack stack) {
        return ItemElfTablet.isStackCreative(stack);
    }

    public float getManaFractionForDisplay(ItemStack stack) {
        return (float)this.getMana(stack) / (float)this.getMaxMana(stack);
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return !ItemElfTablet.isStackCreative(stack);
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - (double)this.getManaFractionForDisplay(stack);
    }
}

