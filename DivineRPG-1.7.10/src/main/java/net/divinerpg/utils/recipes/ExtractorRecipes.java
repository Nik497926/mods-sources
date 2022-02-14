/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.utils.recipes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExtractorRecipes {
    private static final ExtractorRecipes smeltingBase = new ExtractorRecipes();
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaSmeltingList = new HashMap();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap();

    public static final ExtractorRecipes smelting() {
        return smeltingBase;
    }

    private ExtractorRecipes() {
        this.addSmelting(ArcanaBlocks.arcaniumOre, new ItemStack(ArcanaItems.arcanium), 0.0f);
        this.addItemSmelting(ArcanaItems.arcanium, new ItemStack(ArcanaItems.arcanaCrystall, 4), 0.0f);
        this.addItemSmelting(JourneyItemsOther.celestiumIngot, new ItemStack(JourneyItemsOther.celestiumDust, 3), 0.0f);
    }

    public void addSmelting(Block par1, ItemStack par2ItemStack, float par3) {
        this.smeltingList.put(Item.getIdFromItem((Item)Item.getItemFromBlock((Block)par1)), par2ItemStack);
        this.experienceList.put(Item.getIdFromItem((Item)par2ItemStack.getItem()), Float.valueOf(par3));
    }

    public void addItemSmelting(Item par1, ItemStack par2ItemStack, float par3) {
        this.smeltingList.put(Item.getIdFromItem((Item)par1), par2ItemStack);
        this.experienceList.put(Item.getIdFromItem((Item)par2ItemStack.getItem()), Float.valueOf(par3));
    }

    public Map getSmeltingList() {
        return this.smeltingList;
    }

    public void addSmelting(int itemID, int metadata, ItemStack itemstack, float experience) {
        this.metaSmeltingList.put(Arrays.asList(itemID, metadata), itemstack);
        this.metaExperience.put(Arrays.asList(itemID, metadata), Float.valueOf(experience));
    }

    public ItemStack getSmeltingResult(ItemStack item) {
        if (item == null) {
            return null;
        }
        ItemStack ret = this.metaSmeltingList.get(Arrays.asList(Item.getIdFromItem((Item)item.getItem()), item.getItemDamage()));
        if (ret != null) {
            return ret;
        }
        return (ItemStack)this.smeltingList.get(Item.getIdFromItem((Item)item.getItem()));
    }

    public float getExperience(ItemStack item) {
        float f;
        if (item == null || item.getItem() == null) {
            return 0.0f;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0.0f && this.metaExperience.containsKey(Arrays.asList(Item.getIdFromItem((Item)item.getItem()), item.getItemDamage()))) {
            ret = this.metaExperience.get(Arrays.asList(Item.getIdFromItem((Item)item.getItem()), item.getItemDamage())).floatValue();
        }
        if (ret >= 0.0f || this.experienceList.containsKey(Item.getIdFromItem((Item)item.getItem()))) {
            // empty if block
        }
        ret = ((Float)this.experienceList.get(Item.getIdFromItem((Item)item.getItem()))).floatValue();
        return f < 0.0f ? 0.0f : ret;
    }
}

