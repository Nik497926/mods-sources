/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.vanilla;

import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBurningSword
extends ItemModSword {
    private int burnSeconds;

    public ItemBurningSword(Item.ToolMaterial toolMaterial, String name, int seconds) {
        super(toolMaterial, name);
        this.burnSeconds = seconds;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase livingBase1, EntityLivingBase livingBase2) {
        stack.damageItem(1, livingBase2);
        livingBase1.setFire(this.burnSeconds);
        return true;
    }

    @Override
    protected void addAdditionalInformation(List list) {
        list.add(TooltipLocalizer.burn(this.burnSeconds));
    }
}

