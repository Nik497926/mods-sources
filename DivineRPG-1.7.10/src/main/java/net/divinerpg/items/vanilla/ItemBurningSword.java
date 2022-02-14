/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package net.divinerpg.items.vanilla;

import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.libs.Effects;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.Ticker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemBurningSword
extends ItemModSword {
    private int burnSeconds;
    private String extraEffect;
    private int cooldown = 0;

    public ItemBurningSword(Item.ToolMaterial toolMaterial, String name, int seconds) {
        super(toolMaterial, name);
        this.burnSeconds = seconds;
        this.cooldown = 0;
    }

    public ItemBurningSword(Item.ToolMaterial toolMaterial, String name, int seconds, String extraEffect, int cooldown) {
        super(toolMaterial, name);
        this.burnSeconds = seconds;
        this.extraEffect = extraEffect;
        this.cooldown = cooldown;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase livingBase1, EntityLivingBase livingBase2) {
        stack.damageItem(1, livingBase2);
        livingBase1.setFire(this.burnSeconds);
        if (Ticker.timerSword == 0) {
            if (this.extraEffect != null) {
                if (this.extraEffect == Effects.INCINERATION) {
                    livingBase1.addPotionEffect(new PotionEffect(Potion.weakness.id, this.burnSeconds * 20, 0));
                }
                if (this.extraEffect == Effects.BLINDNESS) {
                    livingBase1.addPotionEffect(new PotionEffect(Potion.blindness.id, (this.burnSeconds - 8) * 20, 1));
                }
            }
            Ticker.timerSword = this.cooldown * 20;
        }
        return true;
    }

    @Override
    protected void addAdditionalInformation(List list) {
        list.add(TooltipLocalizer.burn(this.burnSeconds));
        if (this.extraEffect != null && this.cooldown > 0) {
            if (this.extraEffect == Effects.INCINERATION) {
                list.add(TooltipLocalizer.incineration(this.burnSeconds));
            } else {
                list.add(TooltipLocalizer.blindness(this.burnSeconds - 5));
            }
            list.add(TooltipLocalizer.cooldown(this.cooldown));
        }
    }
}

