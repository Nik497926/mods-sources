/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.vethea;

import java.util.List;
import net.divinerpg.items.base.ItemModFood;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemVetheanFood
extends ItemModFood {
    private int food;
    protected float saturation;
    private String name;

    public ItemVetheanFood(int par2, float par3, String name) {
        super(par2, par3, false, name);
        this.food = par2;
        this.saturation = par3;
        this.setCreativeTab(DivineRPGTabs.vethea);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Replenishes " + this.food + " Hunger");
        list.add("" + this.saturation + " Saturation");
        list.add("Pet Food: False");
        list.add(TooltipLocalizer.vethean());
    }
}

