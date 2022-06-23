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
import ru.simplemc.senergetics.util.TranslateUtils;

public class ItemBlockChunkLoader
extends ItemBlock {
    public ItemBlockChunkLoader(Block block) {
        super(block);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.addAll(TranslateUtils.translateForItemStack(this.getUnlocalizedNameInefficiently(stack) + ".lore"));
    }
}

