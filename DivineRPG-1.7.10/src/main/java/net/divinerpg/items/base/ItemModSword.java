/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemModSword
extends ItemSword {
    protected String name;
    protected String textureName;
    protected String skinName;
    protected Item.ToolMaterial mat;

    public ItemModSword(Item.ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.setMaxStackSize(1);
        this.name = name;
        this.textureName = "divinerpg:" + name;
        this.setUnlocalizedName(name);
        this.setTextureName(this.textureName);
        this.setCreativeTab(DivineRPGTabs.swords);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
        this.mat = toolMaterial;
    }

    public ItemModSword(Item.ToolMaterial toolMaterial, String name, String skinName) {
        super(toolMaterial);
        this.setMaxStackSize(1);
        this.name = name;
        this.textureName = "divinerpg:" + name;
        this.skinName = skinName;
        this.setUnlocalizedName(name);
        this.setTextureName(this.textureName);
        this.setCreativeTab(DivineRPGTabs.skins);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
        this.mat = toolMaterial;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        if (this.skinName != null) {
            list.add(TooltipLocalizer.getSkin(this.skinName));
        }
        double lvl = (double)EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) * 1.25;
        if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) == 0) {
            list.add(TooltipLocalizer.meleeDam(this.mat.getDamageVsEntity() + 5.0f));
        } else if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.sharpness.effectId, (ItemStack)item) > 0) {
            list.add(TooltipLocalizer.meleeDam((double)(this.mat.getDamageVsEntity() + 5.0f) + lvl));
        }
        this.addAdditionalInformation(list);
        if (item.getMaxDamage() == -1) {
            list.add(TooltipLocalizer.infiniteUses());
        } else {
            int dur = item.getMaxDamage() - item.getItemDamage();
            double max = item.getMaxDamage();
            int res = (int)((double)dur / max * 100.0);
            list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
        }
    }

    protected void addAdditionalInformation(List list) {
    }
}

