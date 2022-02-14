/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 */
package net.divinerpg.managers;

import java.util.ArrayList;
import java.util.List;
import net.divinerpg.utils.items.JourneyItemsArmor;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ArmorManager {
    private List<ItemArmor.ArmorMaterial> armors = new ArrayList<ItemArmor.ArmorMaterial>();

    public void putArmors() {
        this.armors.add(this.getArmorMaterial(JourneyItemsArmor.celestiumHelmet));
        this.armors.add(this.getArmorMaterial(TwilightItemsArmor.darkHelmet));
        this.armors.add(this.getArmorMaterial(TwilightItemsArmor.lightHelmet));
    }

    public boolean isCanRepair(ItemArmor.ArmorMaterial armorMaterial) {
        return this.armors.contains(armorMaterial);
    }

    private ItemArmor.ArmorMaterial getArmorMaterial(Item armor) {
        return ((ItemArmor)armor).getArmorMaterial();
    }
}

