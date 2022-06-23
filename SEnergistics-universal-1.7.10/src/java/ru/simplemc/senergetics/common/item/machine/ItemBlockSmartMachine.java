/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSmartMachine
extends ItemBlock {
    public ItemBlockSmartMachine(Block block) {
        super(block);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
    }
}

