/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBlock;
import ru.simplemc.simplecore.mod.common.block.BlockTradeStation;
import ru.simplemc.simplecore.mod.common.block.BlockTradeStationCap;
import ru.simplemc.simplecore.mod.common.item.ItemBackpack;
import ru.simplemc.simplecore.mod.common.item.ItemTradeStationInfinity;
import ru.simplemc.simplecore.mod.common.item.ItemTradeStationInfo;

@GameRegistry.ObjectHolder(value="simplecore")
public class SimpleCoreRegistry {
    public static ItemBlock ITEM_BLOCK_TRADE_STATION;
    public static ItemBlock ITEM_BLOCK_TRADE_STATION_CAP;
    public static ItemBackpack ITEM_BACKPACK;
    public static ItemTradeStationInfinity ITEM_TRADE_STATION_INFINITY;
    public static ItemTradeStationInfo ITEM_TRADE_STATION_INFO;

    public static void initItems() {
        ITEM_BLOCK_TRADE_STATION = SimpleCore.PROXY.getRegistrationManager().registerBlock(new BlockTradeStation());
        ITEM_BLOCK_TRADE_STATION_CAP = SimpleCore.PROXY.getRegistrationManager().registerBlock(new BlockTradeStationCap());
        ITEM_BACKPACK = (ItemBackpack)SimpleCore.PROXY.getRegistrationManager().registerItem(new ItemBackpack());
        ITEM_TRADE_STATION_INFINITY = (ItemTradeStationInfinity)SimpleCore.PROXY.getRegistrationManager().registerItem(new ItemTradeStationInfinity());
        ITEM_TRADE_STATION_INFO = (ItemTradeStationInfo)SimpleCore.PROXY.getRegistrationManager().registerItem(new ItemTradeStationInfo());
    }
}

