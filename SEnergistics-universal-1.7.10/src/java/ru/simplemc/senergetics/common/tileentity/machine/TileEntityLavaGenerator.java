/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine;

import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcess;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcessHolder;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcessOutput;
import ru.simplemc.senergetics.config.ConfigMachine;
import ru.simplemc.senergetics.recipe.LavaGeneratorRecipe;

public class TileEntityLavaGenerator
extends TileEntityMachine<TileEntityLavaGenerator>
implements IFluidHandler {
    private static final ConfigMachine CONFIGURATION = SEnergetics.getConfig().getMachines().getOrDefault("lavagenerator", new ConfigMachine(60, 20.0, 40000.0));
    private static final Set<UpgradableProperty> UPGRADABLE_PROPERTIES = EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.EnergyStorage, UpgradableProperty.Transformer, UpgradableProperty.FluidConsuming, UpgradableProperty.FluidProducing);
    private final FluidTank fluidTank = new FluidTank(new FluidStack(FluidRegistry.LAVA, 0), 100000);
    private final LavaGeneratorProcessHolder processesHolder = new LavaGeneratorProcessHolder(this);

    public TileEntityLavaGenerator() {
        this.setEnergyCapacity(CONFIGURATION.getEnergyCapacity());
        this.setEnergyUsagePerTick(CONFIGURATION.getEnergyUsage());
        this.setTicksToProcess(CONFIGURATION.getTicksForProcess());
        this.setSinkTier(0);
    }

    @Override
    protected InventoryTileEntity<TileEntityLavaGenerator> createInventory() {
        return new InventoryTileEntity<TileEntityLavaGenerator>(this, 11);
    }

    @Override
    public void processCommonTick() {
        this.processCommonTickUpgrades();
        if (this.processesHolder.hasActiveProcesses()) {
            for (LavaGeneratorProcess process : (LavaGeneratorProcess[])this.processesHolder.getProcesses()) {
                if (process == null || !process.isPowered() || !process.isFinished()) continue;
                this.processesHolder.removeProcess(process);
            }
        }
        if (this.processesHolder.getActiveProcesses() < 7) {
            for (int slot = 0; slot < this.inventory.getSizeInventory() - 4; ++slot) {
                ItemStack inputItemStack = this.inventory.getStackInSlot(slot);
                if (inputItemStack == null || !this.processesHolder.isProcessSlotIsEmpty(slot)) continue;
                LavaGeneratorProcessOutput processOutput = this.getMachineProcessOutput(inputItemStack);
                if (inputItemStack.stackSize == 0) {
                    this.setInventorySlotContents(slot, null);
                }
                if (processOutput == null) continue;
                if (!this.canUseEnergy(this.getEnergyUsagePerTick())) {
                    this.inventory.setInventorySlotContents(slot, processOutput.getItemStackInput());
                    continue;
                }
                this.processesHolder.addProcess(new LavaGeneratorProcess(this, processOutput, slot));
            }
        }
    }

    public void processCommonTickUpgrades() {
        double processTimeMultiplier = 1.0;
        double energyUsageMultiplier = 1.0;
        double energyCapacityMultiplier = 1.0;
        int extraProcessTime = 0;
        int extraEnergyDemand = 0;
        int extraEnergyStorage = 0;
        for (int slotUpgrade = 7; slotUpgrade < 12; ++slotUpgrade) {
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
        this.setTicksToProcess((int)Math.max(0.0, (double)(CONFIGURATION.getTicksForProcess() + extraProcessTime) * processTimeMultiplier));
        this.setEnergyUsagePerTick((CONFIGURATION.getEnergyUsage() + (double)extraEnergyDemand) * energyUsageMultiplier);
        this.setEnergyCapacity((CONFIGURATION.getEnergyCapacity() + (double)extraEnergyStorage) * energyCapacityMultiplier);
    }

    @Override
    public void processClientTick() {
    }

    @Nullable
    public LavaGeneratorProcessOutput getMachineProcessOutput(ItemStack itemStackInSlot) {
        Optional<LavaGeneratorRecipe> optionalRecipe = LavaGeneratorRecipe.getRecipeForItemStack(itemStackInSlot);
        if (optionalRecipe.isPresent()) {
            LavaGeneratorRecipe recipe = optionalRecipe.get();
            int outputMultiplier = itemStackInSlot.stackSize / recipe.getInputItemStack().stackSize;
            ItemStack itemStackInput = itemStackInSlot.copy();
            FluidStack fluidStackOutput = recipe.getOutputFluidStack();
            itemStackInput.stackSize = recipe.getInputItemStack().stackSize * outputMultiplier;
            itemStackInSlot.stackSize -= itemStackInput.stackSize;
            fluidStackOutput.amount *= outputMultiplier;
            return new LavaGeneratorProcessOutput(itemStackInput, fluidStackOutput, outputMultiplier);
        }
        return null;
    }

    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return UPGRADABLE_PROPERTIES;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.fluidTank.writeToNBT(compound);
        this.processesHolder.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.fluidTank.readFromNBT(compound);
        this.processesHolder.readFromNBT(compound);
    }

    @Override
    protected void readDescriptionPacket(NBTTagCompound compound) {
        super.readDescriptionPacket(compound);
        this.fluidTank.readFromNBT(compound);
        this.fluidTank.setCapacity(compound.getInteger("fluidTankCapacity"));
    }

    @Override
    protected void writeDescriptionPacket(NBTTagCompound compound) {
        super.writeDescriptionPacket(compound);
        this.fluidTank.writeToNBT(compound);
        compound.setInteger("fluidTankCapacity", this.fluidTank.getCapacity());
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return !this.canFill(from, resource.getFluid()) ? 0 : this.getFluidTank().fill(resource, doFill);
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource != null && resource.isFluidEqual(this.getFluidTank().getFluid())) {
            return !this.canDrain(from, resource.getFluid()) ? null : this.getFluidTank().drain(resource.amount, doDrain);
        }
        return null;
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return !this.canDrain(from, null) ? null : this.fluidTank.drain(maxDrain, doDrain);
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.fluidTank.getInfo()};
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.LAVA;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    public int[] getAccessibleSlotsFromSide(int slot) {
        return new int[0];
    }

    public boolean canInsertItem(int slot, ItemStack itemStack, int amount) {
        return false;
    }

    public boolean canExtractItem(int slot, ItemStack itemStack, int amount) {
        return false;
    }

    public FluidTank getFluidTank() {
        return this.fluidTank;
    }

    public LavaGeneratorProcessHolder getProcessesHolder() {
        return this.processesHolder;
    }
}

