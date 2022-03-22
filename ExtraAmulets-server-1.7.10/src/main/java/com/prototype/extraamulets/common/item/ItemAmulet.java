/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.prototype.extraamulets.common.ability.Abilities;
import com.prototype.extraamulets.common.amulet.Amulet;
import com.prototype.extraamulets.common.render.RenderAmulet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class ItemAmulet
extends Item
implements IBauble {
    private final Amulet amulet;

    public ItemAmulet(String name, Amulet amulet) {
        this.amulet = amulet;
        this.setUnlocalizedName(name);
        this.setTextureName(String.format("%s:%s", "extraamulets", name));
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    public Amulet getAmulet() {
        return this.amulet;
    }

    public RenderAmulet getRender() {
        return this.amulet.getRender();
    }

    public Abilities getAbilities() {
        return this.amulet.getAbilities();
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        this.amulet.getAbilities().addInformation(player, tooltip);
    }

    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    public void onWornTick(ItemStack itemStack, EntityLivingBase player) {
    }

    public void onEquipped(ItemStack itemStack, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            this.amulet.getAbilities().onEquipped((EntityPlayer)entity);
        }
    }

    public void onUnequipped(ItemStack itemStack, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            this.amulet.getAbilities().onUnequipped((EntityPlayer)entity);
        }
    }

    public boolean canEquip(ItemStack itemStack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemStack, EntityLivingBase player) {
        return true;
    }
}

