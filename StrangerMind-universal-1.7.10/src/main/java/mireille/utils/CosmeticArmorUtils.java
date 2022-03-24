package mireille.utils;

import net.minecraft.entity.player.*;
import mireille.*;
import net.minecraft.item.*;
import lain.mods.cos.*;
import lain.mods.cos.inventory.*;

public class CosmeticArmorUtils
{
    public static boolean isWear(final EntityPlayer player, final Item head, final Item body, final Item legs, final Item foots) {
        final ItemStack hat = player.getEquipmentInSlot(4);
        final ItemStack chest = player.getEquipmentInSlot(3);
        final ItemStack leggings = player.getEquipmentInSlot(2);
        final ItemStack boots = player.getEquipmentInSlot(1);
        return ((head == null || (hat != null && hat.getItem().equals(head))) && (body == null || (chest != null && chest.getItem().equals(body))) && (legs == null || (leggings != null && leggings.getItem().equals(legs))) && (foots == null || (boots != null && boots.getItem().equals(foots)))) || (StrangerMind.CosmeticArmor && checkCosmetic(player, head, body, legs, foots));
    }
    
    private static boolean checkCosmetic(final EntityPlayer player, final Item head, final Item body, final Item legs, final Item foots) {
        if (player.getUniqueID() == null) {
            return false;
        }
        final InventoryCosArmor inv = CosmeticArmorReworked.invMan.getCosArmorInventoryClient(player.getUniqueID());
        if (inv != null) {
            final ItemStack[] cosmeticSlots = inv.getInventory();
            final ItemStack hat = cosmeticSlots[3];
            final ItemStack chest = cosmeticSlots[2];
            final ItemStack leggings = cosmeticSlots[1];
            final ItemStack boots = cosmeticSlots[0];
            if ((head == null || (hat != null && hat.getItem().equals(head))) && (body == null || (chest != null && chest.getItem().equals(body))) && (legs == null || (leggings != null && leggings.getItem().equals(legs))) && (foots == null || (boots != null && boots.getItem().equals(foots)))) {
                return true;
            }
        }
        return false;
    }
}
