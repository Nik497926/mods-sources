/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileVoiduim
extends SubTileFunctional {
    private static final int DELAY = 200;
    private static final int RANGE = 3;

    public LexiconEntry getEntry() {
        return LexiconModData.voiduim;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public int getMaxMana() {
        return 1200;
    }

    public int getColor() {
        return 4594247;
    }

    public ItemStack getOreToPut() {
        ArrayList<StringRandomItem> values = new ArrayList<StringRandomItem>();
        Map map = this.getOreMap();
        for (Object ores : map.keySet()) {
            values.add(new StringRandomItem((Integer)map.get(ores), (String) ores));
        }
        String ore1 = ((StringRandomItem)WeightedRandom.getRandomItem(this.supertile.getWorldObj().rand, values)).s;
        ArrayList ores1 = OreDictionary.getOres(ore1);
        Iterator i$ = ores1.iterator();
        if (i$.hasNext()) {
            ItemStack stack = (ItemStack)i$.next();
            Item item = stack.getItem();
            return stack;
        }
        return this.getOreToPut();
    }

    public Map getOreMap() {
        return BotaniaAPI.oreWeights;
    }

    public static void addOreWeight(String ore, int weight) {
        BotaniaAPI.oreWeights.put(ore, weight);
    }

    public static int getOreWeight(String ore) {
        return BotaniaAPI.oreWeights.get(ore);
    }

    private static class StringRandomItem
    extends WeightedRandom.Item {
        public String s;

        public StringRandomItem(int par1, String s) {
            super(par1);
            this.s = s;
        }
    }
}

