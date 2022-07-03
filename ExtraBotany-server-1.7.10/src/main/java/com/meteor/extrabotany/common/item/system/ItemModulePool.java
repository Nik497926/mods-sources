/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.system;

import com.meteor.extrabotany.ExtraBotany;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.common.item.ItemMod;

public class ItemModulePool
extends ItemMod {
    public IIcon[] icons = new IIcon[2];

    public ItemModulePool() {
        this.setUnlocalizedName("modulePool");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }

    public void registerIcons(IIconRegister ir) {
        this.icons[0] = ir.registerIcon(ExtraBotany.modid + ":ItemModuleCapacity");
        this.icons[1] = ir.registerIcon(ExtraBotany.modid + ":ItemModuleSpeed");
    }

    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + ":" + stack.getItemDamage();
    }

    public IIcon getIconFromDamage(int p_77617_1_) {
        if (p_77617_1_ < 8) {
            return this.icons[0];
        }
        return this.icons[1];
    }

    public static int getBoostCapacityPool(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        if (stack.getItem() instanceof ItemModulePool) {
            switch (stack.getItemDamage()) {
                case 0: {
                    return 2;
                }
                case 1: {
                    return 4;
                }
                case 2: {
                    return 6;
                }
                case 3: {
                    return 10;
                }
                case 4: {
                    return 20;
                }
                case 5: {
                    return 30;
                }
                case 6: {
                    return 40;
                }
                case 7: {
                    return 50;
                }
            }
            return 0;
        }
        return 0;
    }

    public static int getBoostTransferMana(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        if (stack.getItem() instanceof ItemModulePool) {
            switch (stack.getItemDamage()) {
                case 8: {
                    return 2;
                }
                case 9: {
                    return 4;
                }
                case 10: {
                    return 6;
                }
                case 11: {
                    return 8;
                }
                case 12: {
                    return 10;
                }
                case 13: {
                    return 20;
                }
                case 14: {
                    return 40;
                }
                case 15: {
                    return 80;
                }
            }
            return 0;
        }
        return 0;
    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 16; ++i) {
            list.add(new ItemStack((Item)this, 1, i));
        }
    }
}

