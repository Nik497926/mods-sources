/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.util;

import java.util.Optional;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackUtils {
    public static Optional<ItemStack> findItemStackInRegistry(String itemRegistryName) {
        String[] parts = itemRegistryName.trim().split("@");
        Object registryObject = Item.itemRegistry.getObject(parts[0]);
        if (registryObject instanceof Item) {
            ItemStack itemStack = new ItemStack((Item)registryObject);
            itemStack.setItemDamage(parts.length > 1 ? ItemStackUtils.parseItemMeta(parts[1]) : -1);
            return Optional.of(itemStack);
        }
        return Optional.empty();
    }

    public static ItemStack findItemStackInRegistryUnsafe(String itemRegistryName) {
        return ItemStackUtils.findItemStackInRegistry(itemRegistryName).orElseThrow(() -> new RuntimeException("Item with registry name '" + itemRegistryName + "' not found!"));
    }

    private static int parseItemMeta(String rawMeta) {
        int meta;
        try {
            meta = Integer.parseInt(rawMeta);
        }
        catch (NumberFormatException e) {
            meta = -1;
        }
        return meta;
    }
}

