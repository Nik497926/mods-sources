/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemModSword;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTestSword
extends ItemModSword {
    static int h = 0;
    public int l;
    private static long timer;

    public ItemTestSword(Item.ToolMaterial toolMaterial, String name) {
        super(toolMaterial, name);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase live, EntityLivingBase player) {
        ItemTestSword.addDamage();
        ItemTestSword.tick(player);
        this.checkInt();
        return true;
    }

    public static void addDamage() {
        if (timer < System.currentTimeMillis()) {
            timer = System.currentTimeMillis() + 150L;
            System.out.print(++h);
        }
    }

    public static int tick(EntityLivingBase player) {
        if (h >= 10) {
            player.heal(player.getMaxHealth() * 30.0f / 100.0f);
        }
        return h;
    }

    public int checkInt() {
        if (h >= 10) {
            h = 0;
            return 0;
        }
        return h;
    }

    public static void onDeath(EntityLivingBase player) {
        if (player.isDead) {
            h = 0;
        }
    }
}

