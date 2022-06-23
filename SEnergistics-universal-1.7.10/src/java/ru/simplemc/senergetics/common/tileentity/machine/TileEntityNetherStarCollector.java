/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine;

import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.inventory.slot.SlotSoulSand;
import ru.simplemc.senergetics.common.inventory.slot.SlotWitherSkull;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.config.ConfigMachine;
import ru.simplemc.senergetics.util.InventoryUtils;

public class TileEntityNetherStarCollector
extends TileEntityMachine<TileEntityNetherStarCollector> {
    private static final int[] ACCESSIBLE_SLOTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final ItemStack RESULT_ITEM_STACK = new ItemStack(Items.nether_star);
    private static final ConfigMachine CONFIGURATION = SEnergetics.getConfig().getMachines().getOrDefault("nether_star_collector", new ConfigMachine(60, 20.0, 40000.0));
    private static final Set<UpgradableProperty> UPGRADABLE_PROPERTIES = EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.EnergyStorage, UpgradableProperty.Transformer, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    private int processingTicks = -1;

    public TileEntityNetherStarCollector() {
        this.setEnergyUsagePerTick(CONFIGURATION.getEnergyUsage());
        this.setEnergyCapacity(CONFIGURATION.getEnergyCapacity());
        this.setTicksToProcess(CONFIGURATION.getTicksForProcess());
    }

    @Override
    protected InventoryTileEntity<TileEntityNetherStarCollector> createInventory() {
        return new InventoryTileEntity<TileEntityNetherStarCollector>(this, 20);
    }

    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return UPGRADABLE_PROPERTIES;
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return ACCESSIBLE_SLOTS;
    }

    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side) {
        return slotIndex < 7 && this.isItemValidForSlot(slotIndex, itemStack) && (slotIndex < 3 && SlotWitherSkull.isMatches(itemStack) || slotIndex > 2 && SlotSoulSand.isMatches(itemStack));
    }

    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side) {
        return slotIndex > 6 && this.isItemValidForSlot(slotIndex, itemStack);
    }

    @Override
    protected void processCommonTick() {
        if (this.processingTicks == -1 && this.canTick(5) && this.checkIfRecipeIsDone()) {
            this.processingTicks = 0;
        }
        if (this.processingTicks >= 0) {
            if (this.processingTicks > this.getTicksToProcess()) {
                if (this.generateOutputItem()) {
                    this.processingTicks = -1;
                }
            } else if (this.useEnergy(this.getEnergyUsagePerTick())) {
                ++this.processingTicks;
            }
        }
        this.processCommonTickUpgrades();
    }

    @Override
    protected void processClientTick() {
    }

    public void processCommonTickUpgrades() {
        double processTimeMultiplier = 1.0;
        double energyUsageMultiplier = 1.0;
        double energyCapacityMultiplier = 1.0;
        int extraProcessTime = 0;
        int extraEnergyDemand = 0;
        int extraEnergyStorage = 0;
        for (int slotUpgrade = this.inventory.getSizeInventory() - 4; slotUpgrade < this.inventory.getSizeInventory(); ++slotUpgrade) {
            ItemStack itemStackUpgrade = this.inventory.getStackInSlot(slotUpgrade);
            if (itemStackUpgrade == null || !(itemStackUpgrade.getItem() instanceof IUpgradeItem)) continue;
            IUpgradeItem upgradeItem = (IUpgradeItem)itemStackUpgrade.getItem();
            upgradeItem.onTick(itemStackUpgrade, (IUpgradableBlock)this);
            processTimeMultiplier *= Math.pow(upgradeItem.getProcessTimeMultiplier(itemStackUpgrade, (IUpgradableBlock)this), itemStackUpgrade.stackSize);
            energyUsageMultiplier *= Math.pow(upgradeItem.getEnergyDemandMultiplier(itemStackUpgrade, (IUpgradableBlock)this), itemStackUpgrade.stackSize);
            energyCapacityMultiplier *= Math.pow(upgradeItem.getEnergyStorageMultiplier(itemStackUpgrade, (IUpgradableBlock)this), itemStackUpgrade.stackSize);
            extraProcessTime += upgradeItem.getExtraProcessTime(itemStackUpgrade, (IUpgradableBlock)this) * itemStackUpgrade.stackSize;
            extraEnergyDemand += upgradeItem.getExtraEnergyDemand(itemStackUpgrade, (IUpgradableBlock)this) * itemStackUpgrade.stackSize;
            extraEnergyStorage += upgradeItem.getExtraEnergyStorage(itemStackUpgrade, (IUpgradableBlock)this) * itemStackUpgrade.stackSize;
        }
        this.setTicksToProcess((int)Math.max(1.0, (double)(CONFIGURATION.getTicksForProcess() + extraProcessTime) * processTimeMultiplier));
        this.setEnergyUsagePerTick((CONFIGURATION.getEnergyUsage() + (double)extraEnergyDemand) * energyUsageMultiplier);
        this.setEnergyCapacity((CONFIGURATION.getEnergyCapacity() + (double)extraEnergyStorage) * energyCapacityMultiplier);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("processingTicks", this.processingTicks);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.processingTicks = compound.getInteger("processingTicks");
    }

    private boolean generateOutputItem() {
        ItemStack itemStackResult = RESULT_ITEM_STACK.copy();
        for (int slotIndex = 7; slotIndex < 16; ++slotIndex) {
            ItemStack itemStackInSlot = this.inventory.getStackInSlot(slotIndex);
            if (itemStackInSlot == null) {
                this.inventory.setInventorySlotContents(slotIndex, itemStackResult);
                return true;
            }
            if (itemStackInSlot.stackSize >= itemStackInSlot.getMaxStackSize() || !InventoryUtils.isStackEqualStrict(itemStackInSlot, itemStackResult)) continue;
            int transfer = Math.min(itemStackResult.stackSize, itemStackInSlot.getMaxStackSize() - itemStackInSlot.stackSize);
            itemStackInSlot.stackSize += transfer;
            itemStackResult.stackSize -= transfer;
            if (itemStackResult.stackSize > 0) continue;
            return true;
        }
        return false;
    }

    private boolean checkIfRecipeIsDone() {
        int foundedSkulls = 0;
        int foundedSouls = 0;
        for (int slotIndex = 0; slotIndex < 7; ++slotIndex) {
            ItemStack itemStackInSlot = this.inventory.getStackInSlot(slotIndex);
            if (itemStackInSlot == null) continue;
            if (SlotWitherSkull.isMatches(itemStackInSlot)) {
                foundedSkulls += itemStackInSlot.stackSize;
            }
            if (SlotSoulSand.isMatches(itemStackInSlot)) {
                foundedSouls += itemStackInSlot.stackSize;
            }
            if (foundedSkulls == 3 && foundedSouls == 4) break;
        }
        if (foundedSkulls >= 3 && foundedSouls >= 4) {
            int usedSkulls = 0;
            int usedSouls = 0;
            while (usedSkulls < 3 || usedSouls < 4) {
                for (int slotIndex = 0; slotIndex < 7; ++slotIndex) {
                    ItemStack itemStackInSlot = this.inventory.getStackInSlot(slotIndex);
                    if (usedSkulls < 3 && SlotWitherSkull.isMatches(itemStackInSlot)) {
                        --itemStackInSlot.stackSize;
                        ++usedSkulls;
                    }
                    if (usedSouls < 4 && SlotSoulSand.isMatches(itemStackInSlot)) {
                        --itemStackInSlot.stackSize;
                        ++usedSouls;
                    }
                    if (itemStackInSlot != null && itemStackInSlot.stackSize == 0) {
                        this.setInventorySlotContents(slotIndex, null);
                    }
                    if (usedSkulls != 3 || usedSouls != 4) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public int getProcessingTicks() {
        return this.processingTicks;
    }
}

