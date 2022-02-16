/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.village.MerchantRecipeList
 */
package net.divinerpg.entities.base;

import net.divinerpg.entities.base.InfiniteTrade;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.village.MerchantRecipeList;

public class InfiniteTradeList
extends MerchantRecipeList {
    public InfiniteTradeList() {
    }

    public InfiniteTradeList(NBTTagCompound tag) {
        super(tag);
    }

    public void readRecipiesFromTags(NBTTagCompound tag) {
        NBTTagList list = tag.getTagList("Recipes", 10);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = list.getCompoundTagAt(i);
            this.add((Object)new InfiniteTrade(nbttagcompound1));
        }
    }
}

