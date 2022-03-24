package mireille.core;

import java.util.*;
import mireille.common.items.*;
import net.minecraft.item.*;

public class ModCases
{
    public static ArrayList<ItemBox> Pure_crystal;
    public static ArrayList<ItemBox> Fairy_wing;
    public static ArrayList<ItemBox> DaedraHeart;
    public static ArrayList<ItemBox> DarkStone;
    public static ArrayList<ItemBox> Tron_disk1;
    public static ArrayList<ItemBox> Tron_disk2;
    public static ArrayList<ItemBox> Tron_disk3;
    public static ArrayList<ItemBox> Tron_disk4;
    public static ArrayList<ItemBox> Tron_disk5;
    public static ArrayList<ItemBox> Devil_stone;
    public static ArrayList<ItemBox> Creeper_dust;
    
    public static void registerBoxes() {
        ModCases.Tron_disk1.add(new ItemBox(new ItemStack((Item)ModItems.Tron_head_yellow), 5, 4));
        ModCases.Tron_disk1.add(new ItemBox(new ItemStack((Item)ModItems.Tron_chest_yellow), 5, 4));
        ModCases.Tron_disk1.add(new ItemBox(new ItemStack((Item)ModItems.Tron_leggs_yellow), 5, 4));
        ModCases.Tron_disk1.add(new ItemBox(new ItemStack((Item)ModItems.Tron_boots_yellow), 5, 4));
        ModCases.Tron_disk2.add(new ItemBox(new ItemStack((Item)ModItems.Tron_head_blue), 5, 2));
        ModCases.Tron_disk2.add(new ItemBox(new ItemStack((Item)ModItems.Tron_chest_blue), 5, 2));
        ModCases.Tron_disk2.add(new ItemBox(new ItemStack((Item)ModItems.Tron_leggs_blue), 5, 2));
        ModCases.Tron_disk2.add(new ItemBox(new ItemStack((Item)ModItems.Tron_boots_blue), 5, 2));
        ModCases.Tron_disk3.add(new ItemBox(new ItemStack((Item)ModItems.Tron_head_red), 5, 5));
        ModCases.Tron_disk3.add(new ItemBox(new ItemStack((Item)ModItems.Tron_chest_red), 5, 5));
        ModCases.Tron_disk3.add(new ItemBox(new ItemStack((Item)ModItems.Tron_leggs_red), 5, 5));
        ModCases.Tron_disk3.add(new ItemBox(new ItemStack((Item)ModItems.Tron_boots_red), 5, 5));
        ModCases.Tron_disk4.add(new ItemBox(new ItemStack((Item)ModItems.Tron_head_green), 5, 1));
        ModCases.Tron_disk4.add(new ItemBox(new ItemStack((Item)ModItems.Tron_chest_green), 5, 1));
        ModCases.Tron_disk4.add(new ItemBox(new ItemStack((Item)ModItems.Tron_leggs_green), 5, 1));
        ModCases.Tron_disk4.add(new ItemBox(new ItemStack((Item)ModItems.Tron_boots_green), 5, 1));
        ModCases.Tron_disk5.add(new ItemBox(new ItemStack((Item)ModItems.Tron_head_purple), 5, 3));
        ModCases.Tron_disk5.add(new ItemBox(new ItemStack((Item)ModItems.Tron_chest_purple), 5, 3));
        ModCases.Tron_disk5.add(new ItemBox(new ItemStack((Item)ModItems.Tron_leggs_purple), 5, 3));
        ModCases.Tron_disk5.add(new ItemBox(new ItemStack((Item)ModItems.Tron_boots_purple), 5, 3));
        ModCases.Pure_crystal.add(new ItemBox(new ItemStack((Item)ModItems.CrystalArmorHead), 5, 2));
        ModCases.Pure_crystal.add(new ItemBox(new ItemStack((Item)ModItems.CrystalArmorChest), 5, 2));
        ModCases.Pure_crystal.add(new ItemBox(new ItemStack((Item)ModItems.CrystalArmorLegs), 5, 2));
        ModCases.Pure_crystal.add(new ItemBox(new ItemStack((Item)ModItems.CrystalArmorBoots), 5, 2));
        ModCases.Devil_stone.add(new ItemBox(new ItemStack((Item)ModItems.DevilArmorHead), 5, 5));
        ModCases.Devil_stone.add(new ItemBox(new ItemStack((Item)ModItems.DevilArmorChest), 5, 5));
        ModCases.Devil_stone.add(new ItemBox(new ItemStack((Item)ModItems.DevilArmorLegs), 5, 5));
        ModCases.Devil_stone.add(new ItemBox(new ItemStack((Item)ModItems.DevilArmorBoots), 5, 5));
        ModCases.Creeper_dust.add(new ItemBox(new ItemStack((Item)ModItems.CreepArmorHead), 5, 1));
        ModCases.Creeper_dust.add(new ItemBox(new ItemStack((Item)ModItems.CreepArmorChest), 5, 1));
        ModCases.Creeper_dust.add(new ItemBox(new ItemStack((Item)ModItems.CreepArmorLegs), 5, 1));
        ModCases.Creeper_dust.add(new ItemBox(new ItemStack((Item)ModItems.CreepArmorBoots), 5, 1));
        ModCases.Fairy_wing.add(new ItemBox(new ItemStack((Item)ModItems.FairyArmorHead), 5, 3));
        ModCases.Fairy_wing.add(new ItemBox(new ItemStack((Item)ModItems.FairyArmorChest), 5, 3));
        ModCases.Fairy_wing.add(new ItemBox(new ItemStack((Item)ModItems.FairyArmorLegs), 5, 3));
        ModCases.Fairy_wing.add(new ItemBox(new ItemStack((Item)ModItems.FairyArmorBoots), 5, 3));
    }
    
    static {
        ModCases.Pure_crystal = new ArrayList<ItemBox>();
        ModCases.Fairy_wing = new ArrayList<ItemBox>();
        ModCases.DaedraHeart = new ArrayList<ItemBox>();
        ModCases.DarkStone = new ArrayList<ItemBox>();
        ModCases.Tron_disk1 = new ArrayList<ItemBox>();
        ModCases.Tron_disk2 = new ArrayList<ItemBox>();
        ModCases.Tron_disk3 = new ArrayList<ItemBox>();
        ModCases.Tron_disk4 = new ArrayList<ItemBox>();
        ModCases.Tron_disk5 = new ArrayList<ItemBox>();
        ModCases.Devil_stone = new ArrayList<ItemBox>();
        ModCases.Creeper_dust = new ArrayList<ItemBox>();
    }
}
