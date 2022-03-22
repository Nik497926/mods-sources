/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.side.client.render.handler;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.prototype.extraamulets.common.item.ItemAmulet;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.RenderPlayerEvent;

@SideOnly(value=Side.CLIENT)
public final class RenderAmuletHandler {
    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Post event) {
        EntityPlayer player = event.entityPlayer;
        if (player.getActivePotionEffect(Potion.invisibility) != null) {
            return;
        }
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
        ItemStack amulet = baubles.func_70301_a(0);
        if (amulet != null && amulet.getItem() instanceof ItemAmulet) {
            ((ItemAmulet)amulet.getItem()).getRender().render(player, event.partialRenderTick);
        }
    }
}

