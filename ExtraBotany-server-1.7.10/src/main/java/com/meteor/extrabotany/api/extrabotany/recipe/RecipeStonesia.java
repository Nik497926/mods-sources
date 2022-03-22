/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.api.extrabotany.recipe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.subtile.SubTileEntity;

public class RecipeStonesia {
    private static final Map oreMap = new HashMap();
    Object input;
    int mana;

    public RecipeStonesia(Object input, int mana) {
        this.input = input;
        this.mana = mana;
        if (input != null && !(input instanceof String) && !(input instanceof Block)) {
            throw new IllegalArgumentException("input must be an oredict String or a Block.");
        }
    }

    public boolean matches(World world, int x, int y, int z, SubTileEntity pureDaisy, Block block, int meta) {
        if (this.input instanceof Block) {
            return block == this.input;
        }
        ItemStack stack = new ItemStack(block, 1, meta);
        String oredict = (String)this.input;
        return this.isOreDict(stack, oredict);
    }

    public boolean isOreDict(ItemStack stack, String entry) {
        if (stack != null && stack.getItem() != null) {
            ItemStack cstack;
            List ores;
            if (oreMap.containsKey(entry)) {
                ores = (List)oreMap.get(entry);
            } else {
                ores = OreDictionary.getOres(entry);
                oreMap.put(entry, ores);
            }
            Iterator i$ = ores.iterator();
            do {
                if (!i$.hasNext()) {
                    return false;
                }
                ItemStack ostack = (ItemStack)i$.next();
                cstack = ostack.copy();
                if (cstack.getItemDamage() != Short.MAX_VALUE) continue;
                cstack.setItemDamage(stack.getItemDamage());
            } while (!stack.isItemEqual(cstack));
            return true;
        }
        return false;
    }

    public boolean set(World world, int x, int y, int z, SubTileEntity pureDaisy) {
        if (!world.isRemote) {
            world.func_147480_a(x, y, z, false);
        }
        return true;
    }

    public Object getInput() {
        return this.input;
    }

    public int getMana() {
        return this.mana;
    }
}

