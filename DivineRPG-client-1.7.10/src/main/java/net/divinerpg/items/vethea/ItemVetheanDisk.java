/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVetheanDisk
extends ItemMod {
    public int damage;

    public ItemVetheanDisk(int par1, String name) {
        super(name);
        this.maxStackSize = 1;
        this.damage = par1;
        this.setCreativeTab(DivineRPGTabs.vethea);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedDam(this.damage));
        list.add("Returns to sender");
        list.add(TooltipLocalizer.vethean());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        if (!par3.capabilities.isCreativeMode) {
            --par1.stackSize;
        }
        par2.playSoundAtEntity((Entity)par3, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        if (!par2.isRemote) {
            this.shoot(par1, par2, par3);
        }
        return par1;
    }

    protected void shoot(ItemStack stack, World par2, EntityPlayer par3) {
    }
}

