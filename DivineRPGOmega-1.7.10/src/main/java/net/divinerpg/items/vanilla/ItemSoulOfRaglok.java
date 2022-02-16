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
import net.divinerpg.entities.vanilla.EntityRaglok;
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

public class ItemSoulOfRaglok
extends ItemMod {
    public ItemSoulOfRaglok(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(DivineRPGTabs.spawner);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (world.provider.dimensionId == -1) {
            if (!world.isRemote) {
                EntityRaglok entity = new EntityRaglok(world);
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
        if (player.posY < 120.0) {
            ChatComponentTranslation message = DivineRPG.addChatMessage("\u0420\u0430\u0433\u043b\u043e\u043a\u0430 \u043c\u043e\u0436\u043d\u043e \u0441\u043f\u0430\u0432\u043d\u0438\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043d\u0438\u0436\u0435 120 \u0432\u044b\u0441\u043e\u0442\u044b \u0432 \u0430\u0434\u0443.", new Object[0]);
            message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            player.addChatMessage((IChatComponent)message);
            return false;
        }
        ChatComponentTranslation message = DivineRPG.addChatMessage("boss.raglock.spawn", new Object[0]);
        message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA));
        player.addChatMessage((IChatComponent)message);
        return false;
    }
}

