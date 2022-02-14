/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.item.ItemSword
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.divinerpg.items.base.ItemHealingSword;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class EventTooltip {
    @SubscribeEvent
    public void tooltip(ItemTooltipEvent evt) {
        int i;
        for (i = 0; i < evt.toolTip.size(); ++i) {
            if (!((String)evt.toolTip.get(i)).contains("Attack Damage") || !((String)evt.toolTip.get(i)).contains("+") || !(evt.itemStack.getItem() instanceof ItemSword) && !(evt.itemStack.getItem() instanceof ItemProjectileShooter)) continue;
            evt.toolTip.remove(i);
            evt.toolTip.remove(i - 1);
        }
        for (i = 0; i < evt.toolTip.size(); ++i) {
            if (!((String)evt.toolTip.get(i)).contains("+") || !(evt.itemStack.getItem() instanceof ItemSword) && !(evt.itemStack.getItem() instanceof ItemProjectileShooter)) continue;
            evt.toolTip.remove(i);
            evt.toolTip.remove(i - 1);
        }
        for (i = 0; i < evt.toolTip.size(); ++i) {
            if (!((String)evt.toolTip.get(i)).contains("Durability")) continue;
            evt.toolTip.remove(i);
        }
        if (!(evt.itemStack.getItem() instanceof ItemModSword) && evt.itemStack.getItem() instanceof ItemSword) {
            evt.toolTip.add(TooltipLocalizer.meleeDam((int)((ItemSword)evt.itemStack.getItem()).func_150931_i() + 5));
        }
        if (evt.itemStack.getItem() instanceof ItemHealingSword) {
            for (i = 0; i < evt.toolTip.size(); ++i) {
                if (!((String)evt.toolTip.get(i)).isEmpty()) continue;
                evt.toolTip.remove(i);
            }
        }
    }
}

