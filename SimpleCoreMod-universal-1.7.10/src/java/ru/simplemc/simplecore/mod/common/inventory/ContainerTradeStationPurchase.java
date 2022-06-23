/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.inventory;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.math.BigDecimal;
import javax.annotation.Nonnull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.data.TradeData;
import ru.simplemc.simplecore.mod.common.data.TradeType;
import ru.simplemc.simplecore.mod.common.inventory.ContainerTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.InventoryTileEntityTradeStation;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlocked;
import ru.simplemc.simplecore.mod.common.inventory.slot.SlotBlockedPurchase;
import ru.simplemc.simplecore.mod.handler.network.NetworkHandler;
import ru.simplemc.simplecore.mod.handler.network.message.TradeGuiNotifyMessage;
import ru.simplemc.simplecore.mod.util.ChatBuildedMessage;
import ru.simplemc.simplecore.mod.util.ItemStackHelper;

public class ContainerTradeStationPurchase
extends ContainerTradeStation {
    public ContainerTradeStationPurchase(InventoryTileEntityTradeStation inventory, InventoryPlayer inventoryPlayer) {
        super(inventory, inventoryPlayer);
        this.addSlots();
    }

    private void addSlots() {
        int i;
        this.addSlotToContainer(new SlotBlockedPurchase(this.inventoryTradeStation, 104, 9, 21));
        this.addSlotToContainer(new Slot((IInventory)this.inventoryTradeStation, 105, 153, 43));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.inventoryPlayer, j + i * 9 + 9, 9 + j * 18, 98 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventoryPlayer, i, 9 + i * 18, 156));
        }
    }

    public ItemStack slotClick(int slotIndex, int mouseButton, int clickType, EntityPlayer player) {
        Slot slot;
        if (slotIndex > 0 && slotIndex < this.inventorySlots.size() && (slot = (Slot)this.inventorySlots.get(slotIndex)) instanceof SlotBlocked) {
            return null;
        }
        return super.slotClick(slotIndex, mouseButton, clickType, player);
    }

    protected boolean mergeItemStack(ItemStack itemStack, int slotsIndexStart, int slotsIndexEnd, boolean reverseMode) {
        ItemStack currentItemStack;
        Slot currentSlot;
        boolean isMerged = false;
        int indexStart = slotsIndexStart;
        if (reverseMode) {
            indexStart = slotsIndexEnd - 1;
        }
        if (itemStack.isStackable()) {
            while (itemStack.stackSize > 0 && (!reverseMode && indexStart < slotsIndexEnd || reverseMode && indexStart >= slotsIndexStart)) {
                currentSlot = (Slot)this.inventorySlots.get(indexStart);
                currentItemStack = currentSlot.getStack();
                if (!(currentItemStack == null || currentSlot instanceof SlotBlocked || currentItemStack.getItem() != itemStack.getItem() || itemStack.getHasSubtypes() && itemStack.getItemDamage() != currentItemStack.getItemDamage() || !ItemStack.areItemStackTagsEqual((ItemStack)itemStack, (ItemStack)currentItemStack))) {
                    int stackSizeMerged = currentItemStack.stackSize + itemStack.stackSize;
                    if (stackSizeMerged <= itemStack.getMaxStackSize()) {
                        itemStack.stackSize = 0;
                        currentItemStack.stackSize = stackSizeMerged;
                        currentSlot.onSlotChanged();
                        isMerged = true;
                    } else if (currentItemStack.stackSize < itemStack.getMaxStackSize()) {
                        itemStack.stackSize -= itemStack.getMaxStackSize() - currentItemStack.stackSize;
                        currentItemStack.stackSize = itemStack.getMaxStackSize();
                        currentSlot.onSlotChanged();
                        isMerged = true;
                    }
                }
                if (reverseMode) {
                    --indexStart;
                    continue;
                }
                ++indexStart;
            }
        }
        if (itemStack.stackSize > 0) {
            indexStart = reverseMode ? slotsIndexEnd - 1 : slotsIndexStart;
            while (!reverseMode && indexStart < slotsIndexEnd || reverseMode && indexStart >= slotsIndexStart) {
                currentSlot = (Slot)this.inventorySlots.get(indexStart);
                currentItemStack = currentSlot.getStack();
                if (currentItemStack == null && !(currentSlot instanceof SlotBlocked)) {
                    currentSlot.putStack(itemStack.copy());
                    currentSlot.onSlotChanged();
                    itemStack.stackSize = 0;
                    isMerged = true;
                    break;
                }
                if (reverseMode) {
                    --indexStart;
                    continue;
                }
                ++indexStart;
            }
        }
        return isMerged;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = (Slot)this.inventorySlots.get(index);
        ItemStack transferredItemStack = null;
        if (slot instanceof SlotBlocked) {
            return null;
        }
        if (slot != null && slot.getHasStack()) {
            ItemStack slotItemStack = slot.getStack();
            transferredItemStack = slotItemStack.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (index < inventoryRange ? !this.mergeItemStack(slotItemStack, inventoryRange, this.inventorySlots.size(), true) : !this.mergeItemStack(slotItemStack, 0, inventoryRange, false)) {
                return null;
            }
            if (slotItemStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            slot.onPickupFromSlot(player, slotItemStack);
        }
        return transferredItemStack;
    }

    @Override
    public void onContainerClosed(@Nonnull EntityPlayer player) {
        ItemStack purchasedSlotItemStack = this.inventoryTradeStation.getPurchaseSlotItemStack();
        if (purchasedSlotItemStack != null) {
            this.inventoryTradeStation.setPurchaseSlotItemStack(null);
            if (!player.inventory.addItemStackToInventory(purchasedSlotItemStack)) {
                player.dropPlayerItemWithRandomChoice(purchasedSlotItemStack, true);
            }
        }
        super.onContainerClosed(player);
    }

    public void processingPurchase(EntityPlayerMP player) {
        TradeData data = this.inventoryTradeStation.getTradeStation().getData();
        if (!data.isOwned() || data.getItemStack() == null || data.getPrice().compareTo(BigDecimal.ZERO) < 1) {
            NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u0422\u043e\u0440\u0433\u043e\u0432\u0430\u044f \u0441\u0442\u0430\u043d\u0446\u0438\u044f", "\u043d\u0435 \u043d\u0430\u0441\u0442\u0440\u043e\u0435\u043d\u0430!"), player);
            return;
        }
        if (data.isOwner((EntityPlayer)player)) {
            NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u0412\u044b \u043d\u0435 \u043c\u043e\u0436\u0435\u0442\u0435 \u0442\u043e\u0440\u0433\u043e\u0432\u0430\u0442\u044c", "\u0441 \u0441\u0430\u043c\u0438\u043c \u0441\u043e\u0431\u043e\u0439!"), player);
            return;
        }
        ItemStack tradeItemStack = this.inventoryTradeStation.getTradeItemStack();
        ItemStack purchasedItemStack = this.inventoryTradeStation.getPurchaseSlotItemStack();
        if (data.getType().equals((Object)TradeType.BUY)) {
            if (this.inventoryTradeStation.getAvailableTradeItemsCount() < 1) {
                NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u041a \u0441\u043e\u0436\u0430\u043b\u0435\u043d\u0438\u044e \u0442\u043e\u0432\u0430\u0440\u0430", "\u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435\u0442 \u0432 \u043d\u0430\u043b\u0438\u0447\u0438\u0438!"), player);
                return;
            }
            if (purchasedItemStack != null) {
                if (ItemStackHelper.isStackEqualStrict(purchasedItemStack, tradeItemStack)) {
                    if (purchasedItemStack.stackSize == purchasedItemStack.getMaxStackSize()) {
                        NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u0417\u0430\u0431\u0435\u0440\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442, \u0447\u0442\u043e\u0431\u044b", "\u043f\u0440\u043e\u0434\u043e\u043b\u0436\u0438\u0442\u044c \u043f\u043e\u043a\u0443\u043f\u043a\u0438!"), player);
                        return;
                    }
                    if (purchasedItemStack.stackSize > tradeItemStack.getMaxStackSize() - tradeItemStack.stackSize) {
                        NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u041d\u0435 \u0445\u0432\u0430\u0442\u0430\u0435\u0442 \u043c\u0435\u0441\u0442\u0430 \u0432 \u0441\u043b\u043e\u0442\u0435!", "\u0417\u0430\u0431\u0435\u0440\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442."), player);
                        return;
                    }
                } else {
                    NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u041e\u0441\u0432\u043e\u0431\u043e\u0434\u0438\u0442\u0435 \u0441\u043b\u043e\u0442, \u0447\u0442\u043e\u0431\u044b", "\u0441\u043e\u0432\u0435\u0440\u0448\u0438\u0442\u044c \u043f\u043e\u043a\u0443\u043f\u043a\u0443!"), player);
                    return;
                }
            }
            BigDecimal ownerProfit = data.getPrice().multiply(new BigDecimal("0.95"));
            if (SimpleCore.PROXY.isIntegrated()) {
                if (SimpleCore.PROXY.getIntegrationManager().decrMoney((EntityPlayer)player, data.getPrice())) {
                    if (!data.isInfinity()) {
                        SimpleCore.PROXY.getIntegrationManager().addMoney(data.getOwner(), ownerProfit);
                    }
                } else {
                    NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u0423 \u0432\u0430\u0441 \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0434\u0435\u043d\u0435\u0433", "\u0434\u043b\u044f \u043f\u043e\u043a\u0443\u043f\u043a\u0438!"), player);
                    return;
                }
            }
            data.setTurnover(data.getTurnover().add(ownerProfit));
            data.setTransactions(data.getTransactions() + 1);
            this.inventoryTradeStation.extractItemStackForPurchase(tradeItemStack, purchasedItemStack);
            if (!data.isInfinity()) {
                data.getServerComponent().addMessageForOwner(new ChatBuildedMessage("\u00a7e[\u041c\u0430\u0433\u0430\u0437\u0438\u043d] \u00a78\u00a7o\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435 \u043e \u043f\u043e\u043a\u0443\u043f\u043a\u0435:").append("\u00a77 * \u041d\u043e\u0432\u0430\u044f \u043f\u0440\u043e\u0434\u0430\u0436\u0430 \u0438\u0433\u0440\u043e\u043a\u0443 \u00a76" + player.getCommandSenderName()).append(new ChatComponentText("\u00a77 * \u041f\u0440\u0435\u0434\u043c\u0435\u0442: ").appendSibling(data.getItemStack().func_151000_E()).appendText(" x " + data.getItemStack().stackSize)).append("\u00a77 * \u041f\u0440\u0438\u0431\u044b\u043b\u044c: \u00a76" + ownerProfit + "\u00a77 \u044d\u043a\u043e\u043d\u043e\u0432. (5% \u043d\u0430\u043b\u043e\u0433 \u043d\u0430 \u0434\u043e\u0445\u043e\u0434)."));
            }
            ChatBuildedMessage.create("\u00a7e[\u041c\u0430\u0433\u0430\u0437\u0438\u043d] \u00a78\u00a7o\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435 \u043e \u043f\u043e\u043a\u0443\u043f\u043a\u0435:").append("\u00a77 * \u0412\u044b \u043a\u0443\u043f\u0438\u043b\u0438 \u0443 " + (data.isInfinity() ? "\u0441\u0435\u0440\u0432\u0435\u0440\u0430" : "\u0438\u0433\u0440\u043e\u043a\u0430 \u00a76" + data.getOwner())).append(new ChatComponentText("\u00a77 * \u041f\u0440\u0435\u0434\u043c\u0435\u0442: ").appendSibling(data.getItemStack().func_151000_E()).appendText(" x " + data.getItemStack().stackSize)).append("\u00a77 * \u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c: \u00a76" + data.getPrice() + "\u00a77 \u044d\u043a\u043e\u043d\u043e\u0432.").send((EntityPlayer)player);
            SimpleCore.LOGGER.info("[TradeStation] \u0418\u0433\u0440\u043e\u043a " + player.getCommandSenderName() + " \u043a\u0443\u043f\u0438\u043b \u0443 " + (data.isInfinity() ? "\u0441\u0435\u0440\u0432\u0435\u0440\u0430" : data.getOwner()) + " [" + data.getItemStack().getDisplayName() + " x " + data.getItemStack().stackSize + "] \u0437\u0430 " + data.getPrice() + " \u044d\u043a\u043e\u043d\u043e\u0432.");
        } else {
            if (this.inventoryTradeStation.getPurchaseSlotItemStack() == null) {
                NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u041f\u043e\u043b\u043e\u0436\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0432 \u0441\u043b\u043e\u0442!"), player);
                return;
            }
            if (ItemStackHelper.isStackEqualStrict(tradeItemStack, purchasedItemStack)) {
                if (purchasedItemStack.stackSize < tradeItemStack.stackSize) {
                    NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e!", "\u041f\u043e\u043b\u043e\u0436\u0438\u0442\u0435 \u0435\u0449\u0435 " + (tradeItemStack.stackSize - purchasedItemStack.stackSize) + " \u0448\u0442."), player);
                    return;
                }
                if (!this.inventoryTradeStation.canAddItem(purchasedItemStack)) {
                    NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u0412 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435 \u043c\u0430\u0433\u0430\u0437\u0438\u043d\u0430", "\u043d\u0435 \u0445\u0432\u0430\u0442\u0430\u0435\u0442 \u043c\u0435\u0441\u0442\u0430."), player);
                    return;
                }
                if (!data.isInfinity() && SimpleCore.PROXY.isIntegrated() && !SimpleCore.PROXY.getIntegrationManager().decrMoney(data.getOwner(), data.getPrice())) {
                    NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u0423 \u0432\u043b\u0430\u0434\u0435\u043b\u044c\u0446\u0430 \u043c\u0430\u0433\u0430\u0437\u0438\u043d\u0430", "\u043d\u0435 \u0445\u0432\u0430\u0442\u0430\u0435\u0442 \u0434\u0435\u043d\u0435\u0433."), player);
                    return;
                }
                BigDecimal playerProfit = data.getPrice().multiply(new BigDecimal("0.95"));
                if (SimpleCore.PROXY.isIntegrated()) {
                    SimpleCore.PROXY.getIntegrationManager().addMoney((EntityPlayer)player, playerProfit);
                }
                data.setTurnover(data.getTurnover().add(data.getPrice()));
                data.setTransactions(data.getTransactions() + 1);
                ItemStack itemStackSplit = purchasedItemStack.splitStack(tradeItemStack.stackSize);
                if (!data.isInfinity()) {
                    ItemStack itemStackAdded = this.inventoryTradeStation.addItem(itemStackSplit);
                    if (itemStackAdded != null) {
                        purchasedItemStack.stackSize = itemStackAdded.stackSize;
                    }
                    data.getServerComponent().addMessageForOwner(new ChatBuildedMessage().append("\u00a7e[\u041c\u0430\u0433\u0430\u0437\u0438\u043d] \u00a78\u00a7o\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435 \u043e \u0441\u043a\u0443\u043f\u043a\u0435:").append("\u00a77 * \u0412\u044b \u043a\u0443\u043f\u0438\u043b\u0438 \u0443 \u0438\u0433\u0440\u043e\u043a\u0430 \u00a76" + player.getCommandSenderName()).append(new ChatComponentText("\u00a77 * \u041f\u0440\u0435\u0434\u043c\u0435\u0442: ").appendSibling(data.getItemStack().func_151000_E().appendText(" x " + data.getItemStack().stackSize))).append("\u00a77 * \u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c: \u00a76" + data.getPrice() + "\u00a77 \u044d\u043a\u043e\u043d\u043e\u0432."));
                }
                ChatBuildedMessage.create("\u00a7e[\u041c\u0430\u0433\u0430\u0437\u0438\u043d] \u00a78\u00a7o\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435 \u043e \u0441\u043a\u0443\u043f\u043a\u0435:").append("\u00a77 * \u0412\u044b \u043f\u0440\u043e\u0434\u0430\u043b\u0438 " + (data.isInfinity() ? "\u0441\u0435\u0440\u0432\u0435\u0440\u0443" : "\u0438\u0433\u0440\u043e\u043a\u0443 \u00a76" + data.getOwner())).append(new ChatComponentText("\u00a77 * \u041f\u0440\u0435\u0434\u043c\u0435\u0442: ").appendSibling(data.getItemStack().func_151000_E()).appendText(" x " + data.getItemStack().stackSize)).append("\u00a77 * \u041f\u0440\u0438\u0431\u044b\u043b\u044c: \u00a76" + playerProfit + "\u00a77 \u044d\u043a\u043e\u043d\u043e\u0432. (5% \u043d\u0430\u043b\u043e\u0433 \u043d\u0430 \u0434\u043e\u0445\u043e\u0434).").send((EntityPlayer)player);
                SimpleCore.LOGGER.info("[TradeStation] \u0418\u0433\u0440\u043e\u043a " + player.getCommandSenderName() + " \u043f\u0440\u043e\u0434\u0430\u043b " + (data.isInfinity() ? "\u0441\u0435\u0440\u0432\u0435\u0440\u0443" : "\u0438\u0433\u0440\u043e\u043a\u0443 " + data.getOwner()) + " [" + data.getItemStack().getDisplayName() + " x " + data.getItemStack().stackSize + "] \u0437\u0430 " + data.getPrice() + " \u044d\u043a\u043e\u043d\u043e\u0432.");
            } else {
                NetworkHandler.NETWORK_WRAPPER.sendTo((IMessage)new TradeGuiNotifyMessage("\u041f\u0440\u0435\u0434\u043c\u0435\u0442 \u043d\u0435 \u043f\u043e\u0434\u0445\u043e\u0434\u0438\u0442 \u0434\u043b\u044f", "\u043f\u0440\u043e\u0434\u0430\u0436\u0438!"), player);
            }
        }
        if (purchasedItemStack != null && purchasedItemStack.stackSize == 0) {
            this.inventoryTradeStation.setPurchaseSlotItemStack(null);
        }
    }
}

