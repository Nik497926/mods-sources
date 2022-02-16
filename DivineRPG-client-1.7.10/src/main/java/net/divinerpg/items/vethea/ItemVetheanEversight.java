/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.items.VetheaItems;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemVetheanEversight
extends ItemProjectileShooter {
    public ItemVetheanEversight(String name) {
        super(name, 42.0f, Sounds.blitz.getPrefixedName(), VetheaItems.acid, EntityResourceLocation.eversight.toString(), -1, 0);
        this.setCreativeTab(DivineRPGTabs.vethea);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedDam(this.damage));
        list.add(TooltipLocalizer.infiniteUses());
        list.add(TooltipLocalizer.ammo(VetheaItems.acid));
        list.add(TooltipLocalizer.vethean());
    }
}

