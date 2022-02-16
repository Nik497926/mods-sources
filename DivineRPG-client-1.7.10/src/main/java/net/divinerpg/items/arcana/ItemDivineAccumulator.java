/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import net.divinerpg.DivineRPG;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.network.MessageDivineAccumulator;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
        int x = (int)player.posX;
        int y = (int)player.posY;
        int z = (int)player.posZ;
        if (ArcanaHelper.getProperties(player).useBar(80.0f)) {
            if (!world.isRemote) {
                DivineRPG.network.sendToDimension((IMessage)new MessageDivineAccumulator(x, y, z), player.dimension);
                world.playSoundAtEntity((Entity)player, Sounds.divineAccumulator.getPrefixedName(), 1.0f, 1.0f);
            }
            player.motionY = 2.0;
        }
        return stack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Lauches you into the air");
        list.add("Does not prevent fall damage");
        list.add(TooltipLocalizer.arcanaConsumed(80));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

