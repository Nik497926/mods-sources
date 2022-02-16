/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHealingSword
extends ItemModSword {
    private float healAmount;

    public ItemHealingSword(String name, Item.ToolMaterial mat, float healAmount) {
        super(mat, name);
        this.healAmount = healAmount;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        float current = player.getHealth();
        if (current >= 0.0f && current < 20.0f) {
            player.setHealth(current + this.healAmount);
            stack.damageItem(1, (EntityLivingBase)player);
            world.playSoundAtEntity((Entity)player, Sounds.heal.getPrefixedName(), 1.0f, 1.0f);
        }
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Heals " + this.healAmount / 2.0f + " hearts on use");
        list.add(TooltipLocalizer.usesRemaining(stack.getMaxDamage() - stack.getItemDamage()));
    }
}

