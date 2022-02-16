/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemArcaniteBlade
extends ItemModSword {
    public ItemArcaniteBlade() {
        super(ToolMaterialMod.ArcaniteBlade, "arcaniteBlade");
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return !player.worldObj.isRemote && !ArcanaHelper.getProperties(player).useBar(12.0f);
    }

    @Override
    public void addAdditionalInformation(List list) {
        list.add(TooltipLocalizer.arcanaConsumed(12));
    }
}

