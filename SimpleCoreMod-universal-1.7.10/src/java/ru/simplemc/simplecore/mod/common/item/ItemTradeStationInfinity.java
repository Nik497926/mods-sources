/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.block.BlockTradeStationCap;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.item.ItemBase;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;

public class ItemTradeStationInfinity
extends ItemBase {
    public ItemTradeStationInfinity() {
        super("trade_station_infinity");
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (SimpleCore.PROXY.isIntegrated() && !SimpleCore.PROXY.getIntegrationManager().hasPermission(player, "group.adminhelper")) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.trade_station.infinity.error", new Object[0]));
                return false;
            }
            if (world.blockExists(posX, posY, posZ)) {
                TileEntity tileEntity;
                if (world.getBlock(posX, posY, posZ) instanceof BlockTradeStationCap) {
                    --posY;
                }
                if ((tileEntity = world.getTileEntity(posX, posY, posZ)) instanceof TileEntityTradeStation) {
                    TradeData data = ((TileEntityTradeStation)tileEntity).getData();
                    data.setInfinity(!(data = ((TileEntityTradeStation)tileEntity).getData()).isInfinity());
                    player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.trade_station.infinity.success", new Object[0]));
                    return true;
                }
            }
            return false;
        }
        return super.onItemUse(itemStack, player, world, posX, posY, posZ, side, hitX, hitY, hitZ);
    }
}

