/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import java.util.List;
import java.util.Random;
import net.divinerpg.entities.arcana.projectile.EntityAttractor;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAttractor
extends ItemMod {
    private Random rand = new Random();

    public ItemAttractor() {
        super("arcaniumAttractor");
        this.setCreativeTab(DivineRPGTabs.utility);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && ArcanaHelper.getProperties(player).useBar(20.0f)) {
            if (!world.isRemote) {
                world.playSoundAtEntity((Entity)player, Sounds.reflector.getPrefixedName(), 1.0f, 1.0f);
            }
            EntityAttractor entity = new EntityAttractor(world, (EntityLivingBase)player);
            world.spawnEntityInWorld((Entity)entity);
        }
        return stack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Pulls mobs toward you");
        list.add(TooltipLocalizer.arcanaConsumed(20));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

