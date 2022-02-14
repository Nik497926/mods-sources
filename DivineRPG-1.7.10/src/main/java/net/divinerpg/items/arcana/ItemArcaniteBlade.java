/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemArcaniteBlade
extends ItemModSword {
    public ItemArcaniteBlade() {
        super(ToolMaterialMod.ArcaniteBlade, "arcaniteBlade");
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity live) {
        if (live instanceof EntityPlayer) {
            EntityPlayer pl = (EntityPlayer)live;
            if (ArcanaHelper.getProperties(pl).getBarValue() > 21.0f) {
                ArcanaHelper.getProperties(pl).useBar(20.0f);
            }
            ArcanaHelper.getProperties(pl).forceRegen(10.0f);
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaConsumed(12));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

