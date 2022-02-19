/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemSword
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package net.divinerpg.utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.items.arcana.ItemDivineAccumulator;
import net.divinerpg.items.arcana.ItemEnderScepter;
import net.divinerpg.items.arcana.ItemTeleportStaff;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class OmegaEvents {
    private long timer;
    boolean name = false;
    private Item boots = null;
    private Item body = null;
    private Item legs = null;
    private Item helmet = null;
    public static final String[] walkSpeed = new String[]{"g", "walkSpeed", "walkSpeed"};

    @SubscribeEvent
    public void PlayerInteract(BlockEvent.BreakEvent e) {
        if (e.getPlayer().dimension == 0) {
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.arcana && (e.y < 9 || e.y > 39)) {
            e.setCanceled(true);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.eden && e.block.equals(VanillaBlocks.divineRock)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.wildwood && e.block.equals(TwilightBlocks.edenBlock)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.apalachia && e.block.equals(TwilightBlocks.wildwoodBlock)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.skythern && e.block.equals(TwilightBlocks.apalachiaBlock)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.lelyetia && e.block.equals(JourneyBlocks.LelyetiaPortalFrame)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.mortum && e.block.equals(TwilightBlocks.skythernBlock)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.euca && e.block.equals(JourneyBlocks.eucaPortalFrame)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
            return;
        }
        if (e.getPlayer().dimension == ConfigurationHelper.boiling && e.block.equals(JourneyBlocks.boilingPortalFrame)) {
            e.world.func_147480_a(e.x, e.y, e.z, false);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void swordInteractEvent(PlayerInteractEvent event) {
        if (event.entityPlayer.getHeldItem() != null && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && (event.entityPlayer.getHeldItem().getItem() instanceof ItemSword || event.entityPlayer.getHeldItem().getItem() instanceof ItemDivineAccumulator || event.entityPlayer.getHeldItem().getItem() instanceof ItemEnderScepter || event.entityPlayer.getHeldItem().getItem() instanceof ItemTeleportStaff || event.entityPlayer.getHeldItem().getItem() instanceof ItemFood)) {
            event.setCanceled(true);
        }
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) == VanillaBlocks.divineGlass) {
            event.setCanceled(true);
        }
    }
}

