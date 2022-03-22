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
            BLexiconEntry var0 = new BLexiconEntry(entry.getKey(), LexiconModData.categoryEAltar);
            var0.setLexiconPages(new PageEAltar(this.getOutput(entry.getKey()).getDisplayName(), this.getOutput(entry.getKey()), this.getInput(entry.getKey()), this.getEfir(entry.getKey())));
            var0.setIcon(this.getOutput(entry.getKey()));
        }
    }

    private void addArmRecipe(int i) {
        Item $in = i == 0 ? ModItems.awakeogboots : (i == 1 ? ModItems.awakeoglegs : (i == 2 ? ModItems.awakeogchest : ModItems.awakeoghelm));
        ItemStack in = new ItemStack($in);
        ItemNBTHelper.setInteger(in, "level", 7);
        ItemStack out = new ItemStack($in);
        ItemNBTHelper.setInteger(out, "level", 8);
        String s = "elfarm8" + i;
        this.addRecipe(s, new ItemStack[]{in, new ItemStack(ModBlocks.blockelfirium), new ItemStack(ModItems.material, 1, 2), new ItemStack(ModItems.material, 1, 9)}, out, 4000);
    }

    public boolean isRecipe(String s) {
        return this.input.containsKey(s);
    }

    private boolean equalItemStack(ItemStack[] a, ItemStack[] b) {
        ItemStack[] _b;
        if (a.length != b.length) {
            return false;
        }
        ItemStack[] _a = this.clearArray(a.clone());
        if (_a.length != (_b = this.clearArray(b.clone())).length) {
            return false;
        }
        int falseitem = _a.length;
        for (int i = 0; i < _a.length; ++i) {
            for (int j = 0; j < _a.length; ++j) {
                if (_a[i].getItem() instanceof ItemAwakeOGBoots && _b[j].getItem() instanceof ItemAwakeOGBoots) {
                    if (ItemNBTHelper.getInteger(_b[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (_a[i].getItem() instanceof ItemAwakeOGLegs && _b[j].getItem() instanceof ItemAwakeOGLegs) {
                    if (ItemNBTHelper.getInteger(_b[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (_a[i].getItem() instanceof ItemAwakeOGChest && _b[j].getItem() instanceof ItemAwakeOGChest) {
                    if (ItemNBTHelper.getInteger(_b[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (_a[i].getItem() instanceof ItemAwakeOGHelm && _b[j].getItem() instanceof ItemAwakeOGHelm) {
                    if (ItemNBTHelper.getInteger(_b[j], "level", 0) != 7) continue;
                    --falseitem;
                    continue;
                }
                if (!ItemStack.areItemStacksEqual(_a[i], _b[j])) continue;
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

    private ItemStack[] clearArray(ItemStack[] a) {
        ArrayList<ItemStack> _a = new ArrayList<ItemStack>();
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == null) continue;
            _a.add(a[i]);
        }
        _a = this.sort(_a);
        ItemStack[] $a = new ItemStack[_a.size()];
        for (int i = 0; i < _a.size(); ++i) {
            $a[i] = _a.get(i);
        }
        return $a;
    }

    private ArrayList<ItemStack> sort(ArrayList<ItemStack> in) {
        Object[] out = new Double[in.size()];
        HashMap<Double, NBTTagCompound> __nbt = new HashMap<Double, NBTTagCompound>();
        for (int i = 0; i < in.size(); ++i) {
            ItemStack _in = in.get(i);
            int id = Item.itemRegistry.getIDForObject(_in.getItem());
            int meta = _in.getItemDamage();
            Double __d = Double.parseDouble(id + "." + meta);
            out[i] = __d;
            __nbt.put(__d, _in.getTagCompound());
        }
        Arrays.sort(out);
        ArrayList<ItemStack> __out = new ArrayList<ItemStack>();
        for (int i = 0; i < out.length; ++i) {
            String[] a = String.valueOf(out[i]).split("[.]");
            int id = Integer.parseInt(a[0]);
            int meta = Integer.parseInt(a[1]);
            ItemStack st = new ItemStack((Item)Item.itemRegistry.getObjectById(id), 1, meta);
            if (__nbt.get(out[i]) != null) {
                vazkii.botania.common.core.helper.ItemNBTHelper.injectNBT(st, __nbt.get(out[i]));
            }
            __out.add(st);
        }
        return __out;
    }

    private void addRecipe(String id_recipe, ItemStack[] in, ItemStack out, int efir) {
        if (out != null && efir > 0 && !this.input.containsKey(id_recipe)) {
            this.efir.put(id_recipe, efir);
            this.input.put(id_recipe, in);
            this.output.put(id_recipe, out);
        }
    }
}

