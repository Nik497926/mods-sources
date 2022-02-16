/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSerenadeStriker
extends ItemMod {
    public ItemSerenadeStriker(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.setMaxDamage(100);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        double eyeHeight = 1.62;
        double reachDistance = 300.0;
        MovingObjectPosition rarTrace = Util.rayTrace(player, 100.0);
        if (rarTrace != null && rarTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int x = rarTrace.blockX;
            int y = rarTrace.blockY;
            int z = rarTrace.blockZ;
            if (Math.abs(Math.sqrt(player.posX * player.posX + player.posY * player.posY + player.posZ * player.posZ) - Math.sqrt(x * x + y * y + z * z)) < 100.0) {
                world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)x, (double)y, (double)z));
                world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)x, (double)y, (double)z));
                world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, (double)x, (double)y, (double)z));
                if (!player.capabilities.isCreativeMode) {
                    stack.damageItem(1, (EntityLivingBase)player);
                }
            }
        }
        return stack;
    }

    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.serenadestriker"));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }
}

