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

public class ItemDonateScroll
extends ItemMod {
    private String status;

    public ItemDonateScroll(String name, String status) {
        super(name, DivineRPGTabs.items);
        this.status = status;
        this.setMaxStackSize(1);
        this.setMaxDamage(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        MinecraftServer server = MinecraftServer.getServer();
        if (server instanceof DedicatedServer) {
            ((DedicatedServer)server).addPendingCommand("lp user " + par3.getCommandSenderName() + " parent set " + this.status, (ICommandSender)server);
        }
        --par1.stackSize;
        --par1.stackSize;
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + I18n.format((String)("items.donatescroll." + this.status), (Object[])new Object[0]));
    }
}

