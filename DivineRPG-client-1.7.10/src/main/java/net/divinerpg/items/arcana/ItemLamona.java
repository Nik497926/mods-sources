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
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.arcana.projectile.EntityLamona;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLamona
extends ItemMod {
    public ItemLamona() {
        super("lamona");
        this.setCreativeTab(DivineRPGTabs.utility);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
        }
        if (!world.isRemote) {
            world.playSoundAtEntity((Entity)player, "random.bow", 1.0f, 1.0f);
            EntityLamona bullet = new EntityLamona(world, (EntityLivingBase)player);
            world.spawnEntityInWorld((Entity)bullet);
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Casts light when thrown");
    }
}

