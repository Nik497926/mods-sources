package mireille.core;

import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.block.*;

public class ModItemsImport
{
    public static Item QuantumHelm;
    public static Item QuantumChest;
    public static Item QuantumLegs;
    public static Item QuantumBoots;
    
    public static void importItems() {
        ModItemsImport.QuantumHelm = GameRegistry.findItem("IC2", "itemArmorQuantumHelmet");
        ModItemsImport.QuantumChest = GameRegistry.findItem("IC2", "itemArmorQuantumChestplate");
        ModItemsImport.QuantumLegs = GameRegistry.findItem("IC2", "itemArmorQuantumLegs");
        ModItemsImport.QuantumBoots = GameRegistry.findItem("IC2", "itemArmorQuantumBoots");
    }
    
    public static ItemStack getForgeItem(final String forgeName) {
        if (forgeName.toLowerCase().equals("null")) {
            return null;
        }
        final String[] entryData = forgeName.split(":");
        if (entryData.length < 2) {
            System.out.println("Invalid filter entry %s");
        }
        else {
            final Block block = GameRegistry.findBlock(entryData[0], entryData[1]);
            final Item item = GameRegistry.findItem(entryData[0], entryData[1]);
            int meta = 0;
            if (entryData.length > 2) {
                meta = (entryData[2].equals("*") ? 32767 : Integer.parseInt(entryData[2]));
            }
            if (block != null) {
                return new ItemStack(block, 1, meta);
            }
            if (item != null) {
                return new ItemStack(item, 1, meta);
            }
        }
        return new ItemStack(Blocks.bedrock, 1);
    }
}
