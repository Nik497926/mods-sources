/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import ru.simplemc.senergetics.SEnergetics;

public class ItemBlockSpawner
extends ItemBlock {
    public ItemBlockSpawner(Block block) {
        super(block);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        Collections.addAll(list, String.valueOf(StatCollector.translateToLocal((String)(stack.getUnlocalizedName() + ".lore"))).replace("%energyPerTick%", String.valueOf(SEnergetics.getConfig().getSpawnerLevels().get(0).getEnergyUsage())).split("<br>"));
    }
}

