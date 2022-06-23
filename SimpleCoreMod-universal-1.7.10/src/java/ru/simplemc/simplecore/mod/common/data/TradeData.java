/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.data;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.data.TradeType;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;
import ru.simplemc.simplecore.mod.handler.network.NetworkHandler;
import ru.simplemc.simplecore.mod.handler.network.message.TradeSyncMessage;
import ru.simplemc.simplecore.mod.util.ChatBuildedMessage;
import ru.simplemc.simplecore.mod.util.PermissionsHelper;

public class TradeData {
    private int tickCounter = 0;
    private ServerComponent serverComponent;
    private TradeStationPos pos;
    private ItemStack itemStack = null;
    private TradeType type = TradeType.BUY;
    private String owner = null;
    private BigDecimal price = new BigDecimal(0);
    private BigDecimal turnover = new BigDecimal(0);
    private int transactions = 0;
    private int availableTradeItemsCount;
    private boolean infinity = false;
    private boolean canRenderItem = true;
    private boolean canRenderItemHigher = false;

    public void updateAtServer(TileEntityTradeStation tradeStation) {
        if (this.canUpdate()) {
            InventoryTileEntityTradeStation inventory = tradeStation.getInventory();
            this.itemStack = inventory.getTradeItemStack();
            this.availableTradeItemsCount = inventory.getAvailableTradeItemsCount();
            if (this.pos == null) {
                this.pos = new TradeStationPos(tradeStation.xCoord, tradeStation.yCoord, tradeStation.zCoord);
            }
            if (this.serverComponent == null) {
                this.serverComponent = new ServerComponent(tradeStation);
            }
            this.serverComponent.sendSyncMessage(this);
            this.serverComponent.sendTradeMessagesForOwner();
        }
    }

    public void updateAtClient(TileEntityTradeStation tradeStation) {
        if (this.canUpdate()) {
            Block block = tradeStation.getWorldObj().getBlock(tradeStation.xCoord, tradeStation.yCoord + 1, tradeStation.zCoord);
            this.canRenderItem = this.itemStack != null || block == null || !block.renderAsNormalBlock() || !block.isOpaqueCube();
            this.canRenderItemHigher = block != null && block != Blocks.air && !block.isOpaqueCube();
        }
    }

    public boolean isOwned() {
        return this.owner != null;
    }

    public boolean isOwner(@Nonnull EntityPlayer player) {
        return player.getCommandSenderName().equals(this.owner) && !this.infinity;
    }

    public boolean hasOwnerAccess(EntityPlayer player) {
        return this.isOwner(player) || this.hasAdminAccess(player);
    }

    public boolean hasInventoryAccess(EntityPlayer player) {
        if (this.isInfinity()) {
            return this.hasAdminAccess(player);
        }
        return this.hasOwnerAccess(player) || this.hasRegionAccess(player);
    }

    private boolean hasAdminAccess(EntityPlayer player) {
        if (SimpleCore.PROXY.isIntegrated()) {
            return SimpleCore.PROXY.getIntegrationManager().hasPermission(player, "group.adminhelper");
        }
        return PermissionsHelper.isAdmin(player);
    }

    private boolean hasRegionAccess(EntityPlayer player) {
        if (SimpleCore.PROXY.isIntegrated()) {
            return SimpleCore.PROXY.getIntegrationManager().canBreakBlock(player, this.pos.getX(), this.pos.getY(), this.pos.getZ()) || SimpleCore.PROXY.getIntegrationManager().hasPermission(player, "group.headmoder");
        }
        return false;
    }

    public boolean canUpdate() {
        return this.tickCounter++ % 20 == 0 && this.isOwned();
    }

    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        if (compound.hasKey("Owner")) {
            this.owner = compound.getString("Owner");
        }
        if (compound.hasKey("Price")) {
            this.price = new BigDecimal(compound.getString("Price"));
        }
        if (compound.hasKey("Turnover")) {
            this.turnover = new BigDecimal(compound.getString("Turnover"));
        }
        if (compound.hasKey("Transactions")) {
            this.transactions = compound.getInteger("Transactions");
        }
        if (compound.hasKey("Type")) {
            this.type = TradeType.valueOf(compound.getString("Type"));
        }
        if (compound.hasKey("Infinity")) {
            this.infinity = compound.getBoolean("Infinity");
        }
    }

    public void writeToNBT(@Nonnull NBTTagCompound compound) {
        if (this.isOwned()) {
            compound.setString("Owner", this.owner);
        }
        compound.setString("Price", this.price.toPlainString());
        compound.setString("Turnover", this.turnover.toPlainString());
        compound.setInteger("Transactions", this.transactions);
        compound.setString("Type", this.type.toString());
        compound.setBoolean("Infinity", this.infinity);
    }

    public TradeData copy() {
        TradeData data = new TradeData();
        data.setPos(this.pos);
        data.setItemStack(this.itemStack);
        data.setType(this.type);
        data.setOwner(this.owner);
        data.setPrice(this.price);
        data.setTurnover(this.turnover);
        data.setTransactions(this.transactions);
        data.setAvailableTradeItemsCount(this.availableTradeItemsCount);
        data.setInfinity(this.infinity);
        return data;
    }

    public ServerComponent getServerComponent() {
        return this.serverComponent;
    }

    public TradeStationPos getPos() {
        return this.pos;
    }

    public void setPos(TradeStationPos pos) {
        this.pos = pos;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public TradeType getType() {
        return this.type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTurnover() {
        return this.turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public int getTransactions() {
        return this.transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public int getAvailableTradeItemsCount() {
        return this.availableTradeItemsCount;
    }

    public void setAvailableTradeItemsCount(int availableTradeItemsCount) {
        this.availableTradeItemsCount = availableTradeItemsCount;
    }

    public boolean isInfinity() {
        return this.infinity;
    }

    public void setInfinity(boolean infinity) {
        this.infinity = infinity;
    }

    public boolean isCanRenderItem() {
        return this.canRenderItem;
    }

    public boolean isCanRenderItemHigher() {
        return this.canRenderItemHigher;
    }

    public class ServerComponent {
        private final NetworkRegistry.TargetPoint targetPoint;
        private final List<ChatBuildedMessage> messagesForOwner;
        private final World world;

        private ServerComponent(TileEntityTradeStation tradeStation) {
            this.world = tradeStation.getWorldObj();
            this.targetPoint = new NetworkRegistry.TargetPoint(this.world.provider.dimensionId, (double)TradeData.this.pos.getX(), (double)TradeData.this.pos.getY(), (double)TradeData.this.pos.getZ(), 64.0);
            this.messagesForOwner = new ArrayList<ChatBuildedMessage>();
        }

        public void addMessageForOwner(ChatBuildedMessage message) {
            this.messagesForOwner.add(message);
        }

        private void sendTradeMessagesForOwner() {
            EntityPlayer player = this.world.getPlayerEntityByName(TradeData.this.owner);
            if (player != null) {
                Iterator<ChatBuildedMessage> iterator = this.messagesForOwner.iterator();
                while (iterator.hasNext()) {
                    iterator.next().send(player);
                    iterator.remove();
                }
            }
        }

        private void sendSyncMessage(TradeData data) {
            NetworkHandler.NETWORK_WRAPPER.sendToAllAround((IMessage)new TradeSyncMessage(data), this.targetPoint);
        }
    }

    public static class TradeStationPos {
        private final int x;
        private final int y;
        private final int z;

        public TradeStationPos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean equals(Object obj) {
            if (obj instanceof TradeStationPos) {
                TradeStationPos pos = (TradeStationPos)obj;
                return pos.getX() == this.x && pos.getY() == this.y && pos.getZ() == this.z;
            }
            return false;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getZ() {
            return this.z;
        }
    }
}

