/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.twilight;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTwilightClock
extends ItemMod {
    public ItemTwilightClock(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.utility);
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
        if (var7 != 1) {
            return false;
        }
        if (var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1)) {
            Block var11 = var3.getBlock(var4, var5, var6);
            if (var11 == JourneyBlocks.LelyetiaPortalFrame || var11 == VanillaBlocks.divineRock || var11 == JourneyBlocks.boilingPortalFrame || var11 == JourneyBlocks.eucaPortalFrame || var11 == TwilightBlocks.mortumBlock || var11 == TwilightBlocks.edenBlock || var11 == TwilightBlocks.wildwoodBlock || var11 == TwilightBlocks.apalachiaBlock || var11 == TwilightBlocks.skythernBlock && var3.isAirBlock(var4, var5 + 1, var6)) {
                var3.setBlock(var4, var5 + 1, var6, TwilightBlocks.blueFire);
                return true;
            }
            return false;
        }
        return false;
    }
}

