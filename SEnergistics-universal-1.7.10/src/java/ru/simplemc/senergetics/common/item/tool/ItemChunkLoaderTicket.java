/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.senergetics.common.item.ItemBase;
import ru.simplemc.senergetics.util.TranslateUtils;

public class ItemChunkLoaderTicket
extends ItemBase {
    private final int level;

    public ItemChunkLoaderTicket(int level) {
        super("item_chunkloader_ticket_level_" + level);
        this.setMaxDamage(64);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.level = level;
    }

    @SideOnly(value=Side.CLIENT)
    public String getAvailableUsageTime(ItemStack itemStack) {
        int seconds = this.getAvailableTicks(itemStack) / 20;
        int hours = seconds / 3600;
        String color = hours < 4 ? "\u00a7c" : (hours < 16 ? "\u00a7e" : "\u00a7a");
        return color + I18n.format((String)"item.item_chunkloader_ticket.lore.time", (Object[])new Object[]{hours, seconds % 3600 / 60, seconds % 60, this.level * 24});
    }

    public ItemStack onTicketUse(ItemStack itemStack, int costInTicks) {
        int availableTicks = 0;
        if (itemStack.getItem() instanceof ItemChunkLoaderTicket) {
            availableTicks = ((ItemChunkLoaderTicket)itemStack.getItem()).getAvailableTicks(itemStack);
        }
        if (availableTicks > 0) {
            itemStack.getTagCompound().setInteger("AvailableTicks", availableTicks - costInTicks);
            itemStack.setItemDamage(64 - (int)(64.0f * ((float)availableTicks / (float)(1728000 * this.level))));
        } else {
            itemStack = null;
        }
        return itemStack;
    }

    public int getAvailableTicks(ItemStack itemStack) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            tagCompound.setInteger("AvailableTicks", 1728000 * this.level);
            itemStack.setTagCompound(tagCompound);
        }
        return tagCompound.getInteger("AvailableTicks");
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.addAll(TranslateUtils.translateForItemStackFormatted("item.item_chunkloader_ticket.lore", this.getAvailableUsageTime(stack), 24 * this.level + " " + (this.level == 1 || this.level == 3 ? "\u0447\u0430\u0441\u0430" : "\u0447\u0430\u0441\u043e\u0432")));
    }

    public int getLevel() {
        return this.level;
    }
}

