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

public class ItemSlowingSword
extends ItemModSword {
    private float time;
    private float level;

    public ItemSlowingSword(Item.ToolMaterial toolMaterial, String name) {
        super(toolMaterial, name);
        this.time = 50.0f;
        this.level = 1.0f;
    }

    public ItemSlowingSword(Item.ToolMaterial toolMaterial, String name, float time, float level) {
        super(toolMaterial, name);
        this.time = time;
        this.level = level;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, (int)this.time, (int)this.level));
        }
        return false;
    }

    @Override
    public void addAdditionalInformation(List l) {
        l.add(TooltipLocalizer.slow(this.time / 20.0f));
    }
}

