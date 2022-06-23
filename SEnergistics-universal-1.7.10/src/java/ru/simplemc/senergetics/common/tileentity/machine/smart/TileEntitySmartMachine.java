/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine.smart;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.RecipeOutput;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.inventory.InventorySmartMachine;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcessHolder;
import ru.simplemc.senergetics.common.tileentity.component.process.smart.SmartMachineProcessOutput;
import ru.simplemc.senergetics.config.ConfigMachine;

public abstract class TileEntitySmartMachine<T extends TileEntitySmartMachine<T>>
extends TileEntityMachine<T> {
    private static final int[] ACCESSIBLE_SLOTS_FROM_SIDES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 12, 13, 14, 15, 16, 17, 18, 19};
    private static final Set<UpgradableProperty> UPGRADABLE_PROPERTIES = EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.EnergyStorage, UpgradableProperty.Transformer, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    protected final SmartMachineProcessHolder<T> processesHolder;
    protected final ConfigMachine configuration = TileEntitySmartMachine.getConfiguration(this);

    private static ConfigMachine getConfiguration(TileEntity tileEntity) {
        return SEnergetics.getConfig().getMachines().getOrDefault(TileEntitySmartMachine.getConfigurationKey(tileEntity), new ConfigMachine(60, 20.0, 40000.0));
    }

    private static String getConfigurationKey(TileEntity tileEntity) {
        return tileEntity.getClass().getSimpleName().replace("TileEntitySmart", "").toLowerCase();
    }

    public TileEntitySmartMachine() {
        this.setTicksToProcess(this.configuration.getTicksForProcess());
        this.setEnergyUsagePerTick(this.configuration.getEnergyUsage());
        this.setEnergyCapacity(this.configuration.getEnergyCapacity());
        this.setSinkTier(0);
        this.processesHolder = (SmartMachineProcessHolder<T>) new SmartMachineProcessHolder<T>((T) this);
    }

    @Override
    protected InventorySmartMachine<T> createInventory() {
        return new InventorySmartMachine(this);
    }

    protected abstract IMachineRecipeManager getMachineRecipeManager();

    @Nullable
    public SmartMachineProcessOutput getMachineProcessOutput(ItemStack itemStackInput) {
        IMachineRecipeManager recipeManager = this.getMachineRecipeManager();
        if (recipeManager == null) {
            return null;
        }
        int itemStackSizeBeforeRecipe = itemStackInput.stackSize;
        RecipeOutput recipeOutput = recipeManager.getOutputFor(itemStackInput, true);
        int itemStackSizesDifference = itemStackSizeBeforeRecipe - itemStackInput.stackSize;
        if (itemStackSizesDifference == 0) {
            return null;
        }
        int outputMultiplier = itemStackSizeBeforeRecipe / itemStackSizesDifference;
        ItemStack itemStackInputProcessing = itemStackInput.copy();
        itemStackInputProcessing.stackSize = itemStackSizesDifference * outputMultiplier;
        itemStackInput.stackSize = itemStackSizeBeforeRecipe - itemStackInputProcessing.stackSize;
        return new SmartMachineProcessOutput(recipeOutput, itemStackInputProcessing, outputMultiplier);
    }

    @Override
    public void processCommonTick() {
        this.processesHolder.processCommonTick();
        this.processCommonTickUpgrades();
        if (this.canTick(10) && this.processesHolder.getActiveProcesses() < 8) {
            for (int slotInput = 0; slotInput < 8; ++slotInput) {
                ItemStack inputItemStack = this.inventory.getStackInSlot(slotInput);
                if (inputItemStack == null || !this.processesHolder.isProcessSlotIsEmpty(slotInput)) continue;
                SmartMachineProcessOutput processOutput = this.getMachineProcessOutput(inputItemStack);
                if (inputItemStack.stackSize == 0) {
                    this.setInventorySlotContents(slotInput, null);
                }
                if (processOutput == null) continue;
                if (!this.canUseEnergy(this.getEnergyUsagePerTick())) {
                    this.inventory.setInventorySlotContents(slotInput, processOutput.getItemStackInput());
                    continue;
                }
                this.processesHolder.addProcess(new SmartMachineProcess<T>((T) this, processOutput, slotInput, slotInput + 12));
            }
        }
    }

    @Override
    public void processClientTick() {
    }

    public void processCommonTickUpgrades() {
        double processTimeMultiplier = 1.0;
        double energyUsageMultiplier = 1.0;
        double energyCapacityMultiplier = 1.0;
        int extraProcessTime = 0;
        int extraEnergyDemand = 0;
        int extraEnergyStorage = 0;
        for (int slotUpgrade = 8; slotUpgrade < 12; ++slotUpgrade) {
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
        this.setTicksToProcess((int)Math.max(0.0, (double)(this.configuration.getTicksForProcess() + extraProcessTime) * processTimeMultiplier));
        this.setEnergyUsagePerTick((this.configuration.getEnergyUsage() + (double)extraEnergyDemand) * energyUsageMultiplier);
        this.setEnergyCapacity((this.configuration.getEnergyCapacity() + (double)extraEnergyStorage) * energyCapacityMultiplier);
    }

    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return UPGRADABLE_PROPERTIES;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.processesHolder.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.processesHolder.writeToNBT(compound);
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return ACCESSIBLE_SLOTS_FROM_SIDES;
    }

    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return slot >= 0 && slot < 8 && this.isItemValidForSlot(slot, itemStack) && (this.processesHolder.isProcessSlotIsEmpty(slot) || this.processesHolder.getActiveProcesses() == ((SmartMachineProcess[])this.processesHolder.getProcesses()).length);
    }

    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return slot >= 12 && slot < 20;
    }

    public SmartMachineProcessHolder<T> getProcessesHolder() {
        return this.processesHolder;
    }
}

