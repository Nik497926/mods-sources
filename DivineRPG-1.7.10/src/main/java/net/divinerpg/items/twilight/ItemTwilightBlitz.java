/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.items.twilight;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTwilightBlitz
extends ItemProjectileShooter {
    public ItemTwilightBlitz(String name, String projectileTex, Item ammo, float damage) {
        super(name, damage, Sounds.blitz.getPrefixedName(), ammo, projectileTex, -1, 0);
    }

    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedAndMelee(this.damage));
        list.add(TooltipLocalizer.ammo(this.ammo));
        if (item.getMaxDamage() != -1) {
            int dur = item.getMaxDamage() - item.getItemDamage();
            double max = item.getMaxDamage();
            int res = (int)((double)dur / max * 100.0);
            list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
        } else {
            list.add(TooltipLocalizer.infiniteUses());
        }
    }
}

