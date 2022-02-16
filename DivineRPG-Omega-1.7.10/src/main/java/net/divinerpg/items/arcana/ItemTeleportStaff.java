/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemTeleportStaff
extends ItemMod {
    public ItemTeleportStaff(String name) {
        super(name, DivineRPGTabs.swords);
        this.setMaxStackSize(1);
        this.setMaxDamage(2);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        if (par3.dimension == 0) {
            if (par2.getBlock((int)par3.posX > 0 ? (int)par3.posX : (int)par3.posX - 1, (int)par3.posY + 2, (int)par3.posZ > 0 ? (int)par3.posZ : (int)par3.posZ - 1) != Blocks.air) {
                MinecraftServer mc = MinecraftServer.getServer();
                par3.setPositionAndUpdate((double)mc.getEntityWorld().provider.getSpawnPoint().posX, (double)mc.getEntityWorld().provider.getSpawnPoint().posY, (double)mc.getEntityWorld().provider.getSpawnPoint().posZ);
                par3.addChatMessage((IChatComponent)new ChatComponentText(Util.YELLOW + "\u0412\u044b \u0431\u044b\u043b\u0438 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u044b \u043d\u0430 \u0441\u043f\u0430\u0432\u043d"));
                par1.damageItem(1, (EntityLivingBase)par3);
            } else {
                par3.addChatMessage((IChatComponent)new ChatComponentText(Util.RED + "\u041d\u0430\u0434 \u0412\u0430\u043c\u0438 \u043d\u0435\u0442 \u0431\u043b\u043e\u043a\u043e\u0432"));
            }
        }
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + I18n.format((String)"items.teleport", (Object[])new Object[0]));
        list.add(Util.GOLD + I18n.format((String)"items.teleport2", (Object[])new Object[0]));
        list.add(Util.GOLD + I18n.format((String)"items.teleport3", (Object[])new Object[0]));
    }
}

