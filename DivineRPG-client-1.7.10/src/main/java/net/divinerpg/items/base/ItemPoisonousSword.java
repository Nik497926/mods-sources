/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package net.divinerpg.items.base;

import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemPoisonousSword
extends ItemModSword {
    private float poisonSeconds;

    public ItemPoisonousSword(Item.ToolMaterial mat, String name, float seconds) {
        super(mat, name);
        this.poisonSeconds = seconds;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, (int)(this.poisonSeconds * 20.0f), 1));
        }
        return false;
    }

    @Override
    protected void addAdditionalInformation(List list) {
        list.add(TooltipLocalizer.poison(this.poisonSeconds));
    }
}

