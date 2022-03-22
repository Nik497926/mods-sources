/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.util;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ODHelper {
    public static boolean isOreDict(String oredict, ItemStack stack) {
        ItemStack ostack;
        ItemStack cstack;
        Iterator i$ = OreDictionary.getOres(oredict).iterator();
        do {
            if (i$.hasNext()) continue;
            return false;
        } while (!stack.isItemEqual(cstack = (ostack = (ItemStack)i$.next()).copy()));
        return true;
    }

    public static boolean isODendswith(String oredict, ItemStack stack) {
        String[] od = OreDictionary.getOreNames();
        for (int i = 0; i < od.length; ++i) {
            String ood = od[i];
            if (!ood.endsWith(oredict)) continue;
            for (ItemStack ostack : OreDictionary.getOres(ood)) {
                ItemStack cstack = ostack.copy();
                if (!stack.isItemEqual(cstack)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isODstartswith(String oredict, ItemStack stack) {
        String[] od = OreDictionary.getOreNames();
        for (int i = 0; i < od.length; ++i) {
            String ood = od[i];
            if (!ood.startsWith(oredict)) continue;
            for (ItemStack ostack : OreDictionary.getOres(ood)) {
                ItemStack cstack = ostack.copy();
                if (!stack.isItemEqual(cstack)) continue;
                return true;
            }
        }
        return false;
    }
}

