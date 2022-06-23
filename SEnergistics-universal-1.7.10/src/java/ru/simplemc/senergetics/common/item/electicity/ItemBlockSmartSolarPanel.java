/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.electicity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.util.TranslateUtils;

public class ItemBlockSmartSolarPanel
extends ItemBlock {
    public ItemBlockSmartSolarPanel(Block block) {
        super(block);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        Collections.addAll(list, String.format("\u0412\u044b\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0435\u0442 \u0434\u043d\u0435\u043c: \u00a7e%s", TranslateUtils.translateValueOf(8.0, "EU/t")), String.format("\u0412\u044b\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0435\u0442 \u043d\u043e\u0447\u044c\u044e: \u00a7e%s", TranslateUtils.translateValueOf(4.0, "EU/t")), String.format("\u0420\u0430\u0437\u043c\u0435\u0440 \u0445\u0440\u0430\u043d\u0438\u043b\u0438\u0449\u0430: \u00a7e%s", TranslateUtils.translateValueOf(40000.0, "EU")));
    }
}

