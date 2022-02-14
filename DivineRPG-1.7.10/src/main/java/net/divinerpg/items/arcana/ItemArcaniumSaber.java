/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArcaniumSaber
extends ItemModSword {
    public ItemArcaniumSaber(String name, Item.ToolMaterial var2) {
        super(var2, name);
        this.maxStackSize = 1;
        this.setMaxDamage(-1);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (ArcanaHelper.getProperties(player).useBar(12.0f)) {
            Sounds.playSound((Entity)player, player.worldObj, Sounds.arcaniumSaber);
            return false;
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaConsumed(12));
        list.add(TooltipLocalizer.meleeDam(22.0));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

