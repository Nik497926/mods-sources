/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.World;

public class ItemClanCreator
extends ItemMod {
    public ItemClanCreator(String name) {
        super(name, DivineRPGTabs.items);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        MinecraftServer server = MinecraftServer.getServer();
        if (server instanceof DedicatedServer) {
            ((DedicatedServer)server).addPendingCommand("eco give " + par3.getCommandSenderName() + " 8000", (ICommandSender)server);
        }
        --par1.stackSize;
        --par1.stackSize;
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + I18n.format((String)"items.clan", (Object[])new Object[0]));
    }
}

