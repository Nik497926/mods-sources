/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.common.block.BlockTradeStationCap;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.item.ItemBase;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;

public class ItemTradeStationInfo
extends ItemBase {
    public ItemTradeStationInfo() {
        super("trade_station_info");
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && world.blockExists(posX, posY, posZ)) {
            TileEntity tileEntity;
            if (world.getBlock(posX, posY, posZ) instanceof BlockTradeStationCap) {
                --posY;
            }
            if ((tileEntity = world.getTileEntity(posX, posY, posZ)) instanceof TileEntityTradeStation) {
                TradeData data = ((TileEntityTradeStation)tileEntity).getData();
                Object itemStackChatComponent = data.getItemStack() == null ? new ChatComponentText("\u00a7f[AIR x 1]") : data.getItemStack().func_151000_E().appendText(" x " + data.getItemStack().stackSize);
                player.addChatComponentMessage(new ChatComponentText("\u00a7e[\u041c\u0430\u0433\u0430\u0437\u0438\u043d] \u00a78\u00a7o\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u0441\u0442\u0430\u043d\u0446\u0438\u0438:").appendText("\n\u00a7e * \u00a77\u0412\u043b\u0430\u0434\u0435\u043b\u0435\u0446: \u00a76" + data.getOwner()).appendText("\n\u00a7e * \u00a77\u0422\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0439: \u00a76" + data.getTransactions()).appendText("\n\u00a7e * \u00a77\u041e\u0431\u043e\u0440\u043e\u0442: \u00a76" + data.getTurnover()).appendText("\n\u00a7e * \u00a77\u0426\u0435\u043d\u0430: \u00a76" + data.getPrice()).appendText("\n\u00a7e * \u00a77\u0420\u0435\u0436\u0438\u043c: \u00a76").appendSibling((IChatComponent)data.getType().getTranslationComponent()).appendText("\n\u00a7e * \u00a77\u041f\u0440\u0435\u0434\u043c\u0435\u0442: \u00a76").appendSibling((IChatComponent)itemStackChatComponent));
                return true;
            }
        }
        return super.onItemUse(itemStack, player, world, posX, posY, posZ, side, hitX, hitY, hitZ);
    }
}

