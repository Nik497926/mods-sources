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
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ArcanaHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

public class ItemShadowSword
extends ItemModSword {
    public ItemShadowSword(String name, Item.ToolMaterial mat) {
        super(mat, name);
        this.maxStackSize = 1;
        this.setMaxDamage(-1);
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase hitter) {
        if (ArcanaHelper.getProperties((EntityPlayer)hitter).useBar(12.0f)) {
            target.worldObj.playSoundAtEntity((Entity)target, Sounds.shadowSaber.getPrefixedName(), 1.0f, 1.0f);
            hitter.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 1));
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaConsumed(12));
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.shadowsword"));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

