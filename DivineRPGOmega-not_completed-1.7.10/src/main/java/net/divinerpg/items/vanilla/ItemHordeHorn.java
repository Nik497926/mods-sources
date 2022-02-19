/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemHordeHorn
extends ItemMod {
    public ItemHordeHorn(String name) {
        super(name);
        this.maxStackSize = 1;
        this.setCreativeTab(DivineRPGTabs.spawner);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float xFloat, float yFloat, float zFloat) {
        player.addChatMessage(Util.getChatComponent(Util.RED + StatCollector.translateToLocal((String)"item.hordehorn")));
        return true;
    }
}

