/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.ItemMods;
import com.meteor.extrabotany.common.lib.LibItemName;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;

public class ItemMaterial
extends ItemMods
implements IFlowerComponent {
    final int types = 13;
    IIcon[] icons;

    public ItemMaterial(String name) {
        super(name);
        this.setHasSubtypes(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 13; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[13];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forName((IIconRegister)par1IconRegister, (String)LibItemName.MATERIAL_NAMES[i]);
        }
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World world, EntityPlayer entityplayer) {
        if (!entityplayer.capabilities.isCreativeMode) {
            --par1ItemStack.stackSize;
        }
        if (!world.isRemote && par1ItemStack.getItemDamage() == 0) {
            EntityVillager entityspawning = new EntityVillager(world);
            entityspawning.setPosition(entityplayer.posX + 0.0, entityplayer.posY + 0.0, entityplayer.posZ + 0.0);
            world.spawnEntityInWorld((Entity)entityspawning);
        }
        return par1ItemStack;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 1;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.getItemDamage() == 0) {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }
        return par1ItemStack;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item." + LibItemName.MATERIAL_NAMES[Math.min(12, par1ItemStack.getItemDamage())];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public boolean canFit(ItemStack arg0, IInventory arg1) {
        return true;
    }

    public int getParticleColor(ItemStack arg0) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        return par1ItemStack.getItemDamage() == 11 ? Color.HSBtoRGB((float)(Botania.proxy.getWorldElapsedTicks() * 2L % 360L) / 360.0f, 0.25f, 1.0f) : 0xFFFFFF;
    }
}

