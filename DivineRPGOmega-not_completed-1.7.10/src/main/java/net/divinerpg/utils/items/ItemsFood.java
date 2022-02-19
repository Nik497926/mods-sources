/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.items.base.ItemFastFood;
import net.divinerpg.items.base.ItemModFood;
import net.minecraft.item.Item;

public class ItemsFood {
    public static Item bacon = new ItemModFood(2, 3.0f, true, "bacon");
    public static Item hotPie = new ItemModFood(5, 7.0f, true, "hotPumpkinPie");
    public static Item boiledEgg = new ItemModFood(4, 0.5f, false, "boiledEgg");
    public static Item cheese = new ItemModFood(2, 0.2f, false, "cheese");
    public static Item whiteMushroom = new ItemModFood(1, 0.1f, false, "whiteMushroom");
    public static Item advMushroomStew = new ItemModFood(10, 10.0f, false, "advancedMushroomStew");
    public static Item chickenDinner = new ItemModFood(20, 20.0f, false, "chickenDinner");
    public static Item tomato = new ItemModFood(4, 0.3f, false, "tomato");
    public static Item donut = new ItemModFood(16, 0.3f, false, "donut");
    public static Item rawEmpoweredMeat = new ItemModFood(5, 2.0f, true, "rawEmpoweredMeat");
    public static Item empoweredMeat = new ItemModFood(10, 4.0f, true, "empoweredMeat");
    public static Item magicMeat = new ItemFastFood(5, 1.0f, true, "magicMeat");
    public static Item enrichedMagicMeat = new ItemFastFood(7, 2.5f, true, "enrichedMagicMeat");
    public static Item eucaMeat = new ItemFastFood(6, 3.0f, true, "eucaMeat");
    public static Item rocMeat = new ItemModFood(4, 0.6f, true, "rocMeat");
    public static Item cookedRocMeat = new ItemModFood(7, 1.0f, true, "cookedRocMeat");

    public static void init() {
    }
}

