/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.system.ItemBlockWithMetadataAndName;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockElfPool
extends ItemBlockWithMetadataAndName {
    public ItemBlockElfPool(Block par2Block) {
        super(par2Block);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.getItemDamage() == 1) {
            for (int i = 0; i < 2; ++i) {
                par3List.add(StatCollector.translateToLocal((String)("botaniamisc.creativePool" + i)));
            }
        }
    }
}

