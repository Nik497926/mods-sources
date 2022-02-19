/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import net.divinerpg.DivineRPG;
import net.divinerpg.entities.arcana.EntityLadyLuna;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemMoonClock
extends ItemMod {
    public ItemMoonClock() {
        super("moonClock", DivineRPGTabs.spawner);
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.provider.dimensionId != 56) {
            ChatComponentTranslation message = DivineRPG.addChatMessage("boss.luna.spawn", new Object[0]);
            message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA));
            player.addChatMessage((IChatComponent)message);
        }
        if (!world.isRemote && world.provider.dimensionId == 56) {
            EntityLadyLuna entity = new EntityLadyLuna(world);
            entity.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
            world.spawnEntityInWorld((Entity)entity);
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
            return true;
        }
        return false;
    }
}

