/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.basic;

import com.meteor.extrabotany.common.item.ItemMods;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.recipe.IFlowerComponent;

public class awakeArmController
extends ItemMods
implements IFlowerComponent {
    IIcon[] icons;

    public awakeArmController(String name) {
        super(name);
        this.setTextureName("extrabotania:awakearmcontrol");
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par1) {
        super.addInformation(stack, player, list, par1);
    }

    public boolean canFit(ItemStack itemStack, IInventory iInventory) {
        return false;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.awakearmcontrol";
    }

    public int getParticleColor(ItemStack itemStack) {
        return 0;
    }

    public int getMaxDamage() {
        return 3000;
    }
}

