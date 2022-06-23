/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.tool;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import ru.simplemc.senergetics.common.item.ItemBase;

public class ItemMobDebug
extends ItemBase {
    public ItemMobDebug() {
        super("item_mob_debug");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.add(StatCollector.translateToLocal((String)(this.getUnlocalizedNameInefficiently(stack) + ".lore")) + ".");
    }
}

