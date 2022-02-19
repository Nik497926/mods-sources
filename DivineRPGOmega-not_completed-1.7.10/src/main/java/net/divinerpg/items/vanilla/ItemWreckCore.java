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
package net.divinerpg.items.vanilla;

import net.divinerpg.DivineRPG;
import net.divinerpg.entities.iceika.EntityWreck;
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

public class ItemWreckCore
extends ItemMod {
    public ItemWreckCore(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(DivineRPGTabs.spawner);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (world.provider.dimensionId != 57) {
            ChatComponentTranslation message = DivineRPG.addChatMessage("boss.wreck.spawn", new Object[0]);
            message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA));
            player.addChatMessage((IChatComponent)message);
        }
        if (world.provider.dimensionId == 57 && player.posY < 250.0) {
            if (!world.isRemote) {
                EntityWreck entity = new EntityWreck(world);
                entity.setPosition(x, y + 1, z);
                if (world.getCollidingBoundingBoxes((Entity)entity, entity.boundingBox).isEmpty()) {
                    world.spawnEntityInWorld((Entity)entity);
                    if (!player.capabilities.isCreativeMode) {
                        --stack.stackSize;
                    }
                }
            }
            return true;
        }
        return false;
    }
}

