/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.vanilla.projectile.EntityDeath;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSerenadeOfDeath
extends ItemMod {
    public ItemSerenadeOfDeath(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.setMaxStackSize(1);
        this.setMaxDamage(500);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            world.playSoundAtEntity((Entity)player, Sounds.serenade.getPrefixedName(), 1.0f, 1.0f);
            world.spawnEntityInWorld((Entity)new EntityDeath(world, (EntityLivingBase)player));
            stack.damageItem(1, (EntityLivingBase)player);
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedDam(14.0));
        list.add("Poisons enemies for 2 seconds");
        list.add(TooltipLocalizer.usesRemaining(this.getMaxDamage() - stack.getItemDamage()));
    }
}

