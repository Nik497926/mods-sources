/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.entities.arcana.projectile.EntityGrenade;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGrenade
extends ItemMod {
    private int counter = 0;

    public ItemGrenade(String name) {
        super(name, DivineRPGTabs.ranged);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (this.counter == 0 && !world.isRemote) {
            if (!world.isRemote) {
                world.spawnEntityInWorld((Entity)new EntityGrenade(world, player));
                world.playSoundAtEntity((Entity)player, "random.bow", 1.0f, 1.0f);
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
        }
        if (this.counter < 3) {
            ++this.counter;
        }
        if (this.counter == 3) {
            this.counter = 0;
        }
        return stack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.explosiveShots());
    }
}

