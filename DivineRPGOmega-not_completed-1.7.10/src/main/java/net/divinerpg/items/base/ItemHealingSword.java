/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  ru.harvax.OmegaHolograms
 */
package net.divinerpg.items.base;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ru.harvax.OmegaHolograms;

public class ItemHealingSword
extends ItemModSword {
    private float healAmount;
    private String skin;

    public ItemHealingSword(String name, Item.ToolMaterial mat, float healAmount) {
        super(mat, name);
        this.healAmount = healAmount;
    }

    public ItemHealingSword(String name, Item.ToolMaterial mat, float healAmount, String skin) {
        super(mat, name, skin);
        this.skin = skin;
        this.healAmount = healAmount;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        float current = player.getHealth();
        if (current >= 0.0f && current < player.getMaxHealth()) {
            player.heal(this.healAmount);
            OmegaHolograms.INSTANCE.sendText("" + (int)this.healAmount, 1043226, (Entity)player);
            stack.damageItem(1, (EntityLivingBase)player);
        }
        return super.onItemRightClick(stack, world, player);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        if (this.skin != null && !this.skin.isEmpty()) {
            list.add(TooltipLocalizer.getSkin(this.skin));
        }
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.healsword1", (Object[])new Object[0]) + " " + this.healAmount + " " + I18n.format((String)"items.healsword2", (Object[])new Object[0]));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

