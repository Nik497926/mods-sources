/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.items.vethea.ItemVetheanClaw;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemVetheanSword
extends ItemModSword {
    public ItemVetheanSword(String name, Item.ToolMaterial toolMaterial) {
        super(toolMaterial, name);
        this.setMaxDamage(-1);
        this.setCreativeTab(DivineRPGTabs.vethea);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
        if (item.getMaxDamage() != -1) {
            int dur = item.getMaxDamage() - item.getItemDamage();
            double max = item.getMaxDamage();
            int res = (int)((double)dur / max * 100.0);
            infoList.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
        } else {
            infoList.add(TooltipLocalizer.infiniteUses());
        }
        infoList.add(TooltipLocalizer.meleeDam(this.mat.getDamageVsEntity() + 5.0f));
        if (this instanceof ItemVetheanClaw) {
            infoList.add(TooltipLocalizer.cantBlock());
        }
        infoList.add(TooltipLocalizer.vethean());
    }
}

