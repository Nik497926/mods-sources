/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.tool.spawner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import ru.simplemc.senergetics.common.item.ItemBase;
import ru.simplemc.senergetics.common.item.tool.spawner.SpawnerUpgradeType;

public class ItemSpawnerUpgrade
extends ItemBase {
    private final SpawnerUpgradeType type;

    public ItemSpawnerUpgrade(SpawnerUpgradeType type) {
        super("item_spawner_upgrade_" + type.toString().toLowerCase());
        this.setMaxStackSize(1);
        this.type = type;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        Collections.addAll(list, ("" + StatCollector.translateToLocal((String)(this.getUnlocalizedNameInefficiently(stack) + ".lore"))).trim().split("<br>"));
    }

    public SpawnerUpgradeType getType() {
        return this.type;
    }
}

