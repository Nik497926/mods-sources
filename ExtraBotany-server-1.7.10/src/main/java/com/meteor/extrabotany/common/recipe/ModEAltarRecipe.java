/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.recipe;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGBoots;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGChest;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGHelm;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGLegs;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.lexicon.page.PageEAltar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class ModEAltarRecipe {
    public static final ModEAltarRecipe instance = new ModEAltarRecipe();
    private final HashMap<String, ItemStack[]> input = new HashMap();
    private final HashMap<String, ItemStack> output = new HashMap();
    private final HashMap<String, Integer> efir = new HashMap();

    public void init() {
        this.addRecipe("singa", new ItemStack[]{new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ModItems.efirfragment), new ItemStack(ModItems.material, 1, 9), new ItemStack(ModItems.material, 1, 2)}, new ItemStack(ConfigItems.itemEldritchObject, 2, 3), 500);
        this.addArmRecipe(0);
        this.addArmRecipe(1);
        this.addArmRecipe(2);
        this.addArmRecipe(3);
    }

    public void initLexicon() {
        for (Map.Entry<String, ItemStack[]> entry : this.input.entrySet()) {
            BLexiconEntry lexiconEntry = new BLexiconEntry(entry.getKey(), LexiconModData.categoryEAltar);
            lexiconEntry.setLexiconPages(new LexiconPage[]{new PageEAltar(this.getOutput(entry.getKey()).getDisplayName(), this.getOutput(entry.getKey()), this.getInput(entry.getKey()), this.getEfir(entry.getKey()))});
            lexiconEntry.setIcon(this.getOutput(entry.getKey()));
        }
    }

    private void addArmRecipe(int i) {
        Item item = i == 0 ? ModItems.awakeogboots : (i == 1 ? ModItems.awakeoglegs : (i == 2 ? ModItems.awakeogchest : ModItems.awakeoghelm));
        ItemStack itemStack = new ItemStack(item);
        ItemNBTHelper.setInteger(itemStack, "level", 7);
        ItemStack out = new ItemStack(item);
        ItemNBTHelper.setInteger(out, "level", 8);
        String s = "elfarm8" + i;
        this.addRecipe(s, new ItemStack[]{itemStack, new ItemStack(ModBlocks.blockelfirium), new ItemStack(ModItems.material, 1, 2), new ItemStack(ModItems.material, 1, 9)}, out, 4000);
    }

    public boolean isRecipe(String s) {
        return this.input.containsKey(s);
    }

    private boolean equalItemStack(ItemStack[] stacks, ItemStack[] itemStacks) {
        ItemStack[] st1;
        if (stacks.length != itemStacks.length) {
            return false;
        }
        ItemStack[] st = this.clearArray((ItemStack[])stacks.clone());
        if (st.length != (st1 = this.clearArray((ItemStack[])itemStacks.clone())).length) {
            return false;
        }
        int falseitem = st.length;
        for (ItemStack itemStack : st) {
            for (int j = 0; j < st.length; ++j) {
                if (itemStack.getItem() instanceof ItemAwakeOGBoots && st1[j].getItem() instanceof ItemAwakeOGBoots) {
                    if (ItemNBTHelper.getInteger(st1[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (itemStack.getItem() instanceof ItemAwakeOGLegs && st1[j].getItem() instanceof ItemAwakeOGLegs) {
                    if (ItemNBTHelper.getInteger(st1[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (itemStack.getItem() instanceof ItemAwakeOGChest && st1[j].getItem() instanceof ItemAwakeOGChest) {
                    if (ItemNBTHelper.getInteger(st1[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (itemStack.getItem() instanceof ItemAwakeOGHelm && st1[j].getItem() instanceof ItemAwakeOGHelm) {
                    if (ItemNBTHelper.getInteger(st1[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (!ItemStack.areItemStacksEqual((ItemStack)itemStack, (ItemStack)st1[j])) continue;
                --falseitem;
            }
        }
        return falseitem == 0;
    }

    public String[] getNameAllRecipe() {
        String[] res = new String[this.input.size()];
        int ind = 0;
        for (Map.Entry<String, ItemStack[]> entry : this.input.entrySet()) {
            res[ind] = entry.getKey();
            ++ind;
        }
        return res;
    }

    public ItemStack getOutput(String s) {
        if (s.equalsIgnoreCase("singa")) {
            return new ItemStack(ConfigItems.itemEldritchObject, 2, 3);
        }
        if (s.toLowerCase().contains("elfarm")) {
            Item[] arm = new Item[]{ModItems.awakeogboots, ModItems.awakeoglegs, ModItems.awakeogchest, ModItems.awakeoghelm};
            ItemStack out = null;
            if (s.toLowerCase().contains("elfarm80")) {
                out = new ItemStack(arm[0]);
            }
            if (s.toLowerCase().contains("elfarm81")) {
                out = new ItemStack(arm[1]);
            }
            if (s.toLowerCase().contains("elfarm82")) {
                out = new ItemStack(arm[2]);
            }
            if (s.toLowerCase().contains("elfarm83")) {
                out = new ItemStack(arm[3]);
            }
            if (out != null) {
                ItemNBTHelper.setInteger(out, "level", 8);
                return out;
            }
        }
        return null;
    }

    public int getEfir(String s) {
        if (s.equalsIgnoreCase("singa")) {
            return 500;
        }
        if (s.toLowerCase().contains("elfarm")) {
            return 4000;
        }
        return 1;
    }

    public ItemStack[] getInput(String s) {
        if (this.input.containsKey(s)) {
            return this.input.get(s);
        }
        return new ItemStack[0];
    }

    private ItemStack[] clearArray(ItemStack[] itemStacks) {
        ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) continue;
            stack.add(itemStack);
        }
        stack = this.sort(stack);
        ItemStack[] st1 = new ItemStack[stack.size()];
        for (int j = 0; j < stack.size(); ++j) {
            st1[j] = stack.get(j);
        }
        return st1;
    }

    private ArrayList<ItemStack> sort(ArrayList<ItemStack> stacks) {
        Object[] out = new Double[stacks.size()];
        HashMap<Double, NBTTagCompound> nbt = new HashMap<Double, NBTTagCompound>();
        for (int i = 0; i < stacks.size(); ++i) {
            ItemStack itemStack = stacks.get(i);
            int id = Item.itemRegistry.getIDForObject((Object)itemStack.getItem());
            int meta = itemStack.getItemDamage();
            double aDouble = Double.parseDouble(id + "." + meta);
            out[i] = aDouble;
            nbt.put((Double)out[i], itemStack.getTagCompound());
        }
        Arrays.sort(out);
        ArrayList<ItemStack> stackOut = new ArrayList<ItemStack>();
        for (Object aDouble : out) {
            String[] a = String.valueOf(aDouble).split("[.]");
            int id2 = Integer.parseInt(a[0]);
            int meta2 = Integer.parseInt(a[1]);
            ItemStack st = new ItemStack((Item)Item.itemRegistry.getObjectById(id2), 1, meta2);
            if (nbt.get(aDouble) != null) {
                vazkii.botania.common.core.helper.ItemNBTHelper.injectNBT((ItemStack)st, (NBTTagCompound)((NBTTagCompound)nbt.get(aDouble)));
            }
            stackOut.add(st);
        }
        return stackOut;
    }

    private void addRecipe(String id_recipe, ItemStack[] in, ItemStack out, int efir) {
        if (out != null && efir > 0 && !this.input.containsKey(id_recipe)) {
            this.efir.put(id_recipe, efir);
            this.input.put(id_recipe, in);
            this.output.put(id_recipe, out);
        }
    }
}

