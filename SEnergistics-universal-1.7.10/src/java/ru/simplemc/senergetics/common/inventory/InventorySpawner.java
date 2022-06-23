/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.SEnergeticsRegistry;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.item.tool.ItemMobScanner;
import ru.simplemc.senergetics.common.item.tool.spawner.ItemSpawnerUpgrade;
import ru.simplemc.senergetics.common.item.tool.spawner.SpawnerUpgradeType;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;

public class InventorySpawner
extends InventoryTileEntity<TileEntitySpawner>
implements ISidedInventory {
    private static final int[] ACCESSIBLE_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 34, 35, 36, 37, 37, 38, 39};
    private static final int[] ACCESSIBLE_SLOTS_TOP_SIDE = new int[]{40, 41, 42, 43, 44, 45};
    private static final int[] UPGRADES_SLOTS_IDS = new int[]{42, 43, 44, 45};
    private static final int MOB_SCANNER_SLOT_ID = 40;
    private static final int ATTACKED_ITEM_SLOT_ID = 41;
    public final TileEntitySpawner spawner;

    public InventorySpawner(int inventorySize, TileEntitySpawner spawner) {
        super(spawner, inventorySize);
        this.spawner = spawner;
    }

    public boolean isAllItemsReadyToWork() {
        return this.hasMobScanner() && this.hasAttackItem();
    }

    public ItemStack getMobScanner() {
        return this.getStackInSlot(40);
    }

    public boolean hasMobScanner() {
        ItemStack itemStack = this.getMobScanner();
        return itemStack != null && itemStack.getItem() == SEnergeticsRegistry.itemMobScanner && ItemMobScanner.isMobWritten(itemStack);
    }

    public void setMobScannerItemStack(ItemStack itemStack) {
        this.setInventorySlotContents(40, itemStack);
    }

    public int getSpawnerUpgradesAmount(SpawnerUpgradeType type) {
        int amount = 0;
        for (int i = UPGRADES_SLOTS_IDS[0]; i <= UPGRADES_SLOTS_IDS[UPGRADES_SLOTS_IDS.length - 1]; ++i) {
            ItemSpawnerUpgrade spawnerUpgrade;
            ItemStack itemStack = this.getStackInSlot(i);
            if (itemStack == null || !(itemStack.getItem() instanceof ItemSpawnerUpgrade) || (spawnerUpgrade = (ItemSpawnerUpgrade)itemStack.getItem()).getType() != SpawnerUpgradeType.SUPER && spawnerUpgrade.getType() != type) continue;
            ++amount;
        }
        return amount;
    }

    public ItemStack getAttackItem() {
        return this.getStackInSlot(41);
    }

    public boolean hasAttackItem() {
        return this.getAttackItem() != null;
    }

    public void setAttackItemStack(ItemStack itemStack) {
        this.setInventorySlotContents(41, itemStack);
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        for (int upgradeSlot : UPGRADES_SLOTS_IDS) {
            if (slot != upgradeSlot) continue;
            return itemStack.getItem() instanceof ItemSpawnerUpgrade;
        }
        if (slot == 40) {
            return itemStack.getItem() == SEnergeticsRegistry.itemMobScanner;
        }
        return true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return !this.spawner.isInvalid() && player.worldObj.getTileEntity(this.spawner.xCoord, this.spawner.yCoord, this.spawner.zCoord) == this.spawner && player.getDistanceSq((double)this.spawner.xCoord + 0.5, (double)this.spawner.yCoord + 0.5, (double)this.spawner.zCoord + 0.5) <= 64.0;
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 1 ? ACCESSIBLE_SLOTS_TOP_SIDE : ACCESSIBLE_SLOTS;
    }

    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        for (int availableSlot : this.getAccessibleSlotsFromSide(side)) {
            if (availableSlot != slot || !this.isItemValidForSlot(slot, itemStack)) continue;
            return true;
        }
        return false;
    }

    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        for (int availableSlot : this.getAccessibleSlotsFromSide(side)) {
            if (availableSlot != slot || !this.isItemValidForSlot(slot, itemStack)) continue;
            return true;
        }
        return false;
    }
}

