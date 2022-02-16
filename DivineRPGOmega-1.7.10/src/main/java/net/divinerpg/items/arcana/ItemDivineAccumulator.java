/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.DivineRPG;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.network.MessageDirth;
import net.divinerpg.network.MessageDivineAccumulator;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.proxies.CommonProxy;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDivineAccumulator
extends ItemMod {
    public ItemDivineAccumulator() {
        super("divineAccumulator", DivineRPGTabs.utility);
        this.setMaxStackSize(1);
        this.setFull3D();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            int x = (int)player.posX;
            int y = (int)player.posY;
            int z = (int)player.posZ;
            if (ArcanaHelper.getProperties(player).useBar(80.0f)) {
                EntityPlayerMP pl = (EntityPlayerMP)player;
                DivineRPG.network.sendTo((IMessage)new MessageDirth(2.0f), pl);
                DivineRPG.network.sendToDimension((IMessage)new MessageDivineAccumulator(x, y, z), pl.dimension);
                world.playSoundAtEntity((Entity)pl, Sounds.divineAccumulator.getPrefixedName(), 1.0f, 1.0f);
                pl.addPotionEffect(new PotionEffect(CommonProxy.nofallPotion.id, 110, 0));
                pl.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 110, 0));
            }
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(Util.GOLD + I18n.format((String)"items.accumulator", (Object[])new Object[0]));
        list.add(Util.GOLD + I18n.format((String)"items.accumulator2", (Object[])new Object[0]));
        list.add(TooltipLocalizer.arcanaConsumed(80));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

