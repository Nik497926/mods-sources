/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.electricity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.info.Info;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ru.simplemc.senergetics.common.inventory.InventorySolarPanel;
import ru.simplemc.senergetics.common.item.electicity.panel.ItemPanelParticleCollector;

public class TileEntitySolarPanel
extends TileEntity
implements IEnergySource,
IInventory {
    private final InventorySolarPanel inventory = new InventorySolarPanel(this);
    private int ticksCounter = 0;
    private int particleCollectorId = 0;
    private double energyGeneratedPerTick = 8.0;
    private double energyStored = 0.0;
    private double energyCapacity = 40000.0;
    private boolean forceUpdate = false;
    private boolean hasSun = false;
    private boolean addedToEnet = false;

    public void updateEntity() {
        if (this.worldObj.isRemote) {
            return;
        }
        if (!this.addedToEnet) {
            this.onLoaded();
        }
        if (this.ticksCounter++ == 1 || this.ticksCounter % 5 == 0) {
            this.calculateEnergySourceProperties();
        }
        if (this.ticksCounter == 1 || this.ticksCounter % 20 == 0) {
            this.calculateSunPosition();
            this.forceUpdate = true;
        }
        this.energyStored += this.energyGeneratedPerTick;
        if (this.energyStored > this.energyCapacity) {
            this.energyStored = this.energyCapacity;
        }
        for (int i = 0; i < this.inventory.getSizeInventory() - 1; ++i) {
            ItemStack itemStack = this.inventory.getStackInSlot(i);
            if (itemStack == null || !(itemStack.getItem() instanceof IElectricItem) || !this.chargeItemStack(itemStack)) continue;
            this.markDirty();
        }
        if (this.forceUpdate) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.forceUpdate = false;
        }
    }

    private void onLoaded() {
        if (!this.addedToEnet && !FMLCommonHandler.instance().getEffectiveSide().isClient() && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post((Event)new EnergyTileLoadEvent((IEnergyTile)this));
            this.addedToEnet = true;
        }
    }

    private boolean chargeItemStack(ItemStack stack) {
        if (stack != null && Info.isIc2Available()) {
            double amount = ElectricItem.manager.charge(stack, this.energyStored, 10, false, false);
            this.energyStored -= amount;
            return amount > 0.0;
        }
        return false;
    }

    private void calculateEnergySourceProperties() {
        Optional<ItemStack> optionalItemStack = this.inventory.getParticleCollectorItemStack();
        int particleCollectorIdCurrent = this.particleCollectorId;
        if (optionalItemStack.isPresent()) {
            ItemStack itemStack = optionalItemStack.get();
            ItemPanelParticleCollector itemPanelParticleCollector = (ItemPanelParticleCollector)itemStack.getItem();
            this.particleCollectorId = itemPanelParticleCollector.getTextureId();
            this.energyGeneratedPerTick = itemPanelParticleCollector.getEnergyGenerated() * (double)itemStack.stackSize;
            this.energyCapacity = itemPanelParticleCollector.getEnergyCapacity() * (double)itemStack.stackSize;
        } else {
            this.particleCollectorId = 0;
            this.energyGeneratedPerTick = 8.0;
            this.energyCapacity = 40000.0;
            this.energyStored = Math.min(this.energyStored, this.energyCapacity);
        }
        if (particleCollectorIdCurrent != this.particleCollectorId) {
            this.forceUpdate = true;
        }
        this.energyGeneratedPerTick = this.hasSun ? this.energyGeneratedPerTick : this.energyGeneratedPerTick / 2.0;
    }

    private void calculateSunPosition() {
        if (this.worldObj.provider.hasNoSky) {
            this.hasSun = false;
            return;
        }
        if (this.worldObj.isDaytime() && !this.worldObj.isRaining() && !this.worldObj.isThundering()) {
            this.hasSun = this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
            return;
        }
        this.hasSun = false;
    }

    @SideOnly(value=Side.CLIENT)
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        int particleCollectorIdCurrent;
        NBTTagCompound compound = packet.func_148857_g();
        if (compound.hasKey("HasSun")) {
            this.hasSun = compound.getBoolean("HasSun");
        }
        if (compound.hasKey("EnergyGeneratedPerTick")) {
            this.energyGeneratedPerTick = compound.getDouble("EnergyGeneratedPerTick");
        }
        if (compound.hasKey("EnergyStored")) {
            this.energyStored = compound.getDouble("EnergyStored");
        }
        if (compound.hasKey("ParticleCollectorId") && this.particleCollectorId != (particleCollectorIdCurrent = compound.getInteger("ParticleCollectorId"))) {
            this.particleCollectorId = particleCollectorIdCurrent;
            this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setDouble("EnergyGeneratedPerTick", this.energyGeneratedPerTick);
        compound.setDouble("EnergyStored", this.energyStored);
        compound.setInteger("ParticleCollectorId", this.particleCollectorId);
        compound.setBoolean("HasSun", this.hasSun);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, compound);
    }

    public void invalidate() {
        this.onChunkUnload();
        super.invalidate();
    }

    public void onChunkUnload() {
        if (this.addedToEnet && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post((Event)new EnergyTileUnloadEvent((IEnergyTile)this));
            this.addedToEnet = false;
        }
        super.onChunkUnload();
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.inventory.writeToNBT(compound);
        compound.setDouble("EnergyCapacity", this.energyCapacity);
        compound.setDouble("EnergyStored", this.energyStored);
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory.readFromNBT(compound);
        this.energyCapacity = compound.getDouble("EnergyCapacity");
        this.energyStored = compound.getDouble("EnergyStored");
    }

    public int getSizeInventory() {
        return this.inventory.getSizeInventory();
    }

    public ItemStack getStackInSlot(int index) {
        return this.inventory.getStackInSlot(index);
    }

    public ItemStack decrStackSize(int index, int size) {
        return this.inventory.decrStackSize(index, size);
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        return this.inventory.getStackInSlotOnClosing(index);
    }

    public void setInventorySlotContents(int index, ItemStack itemStack) {
        this.inventory.setInventorySlotContents(index, itemStack);
    }

    public String getInventoryName() {
        return this.inventory.getInventoryName();
    }

    public boolean hasCustomInventoryName() {
        return this.inventory.hasCustomInventoryName();
    }

    public int getInventoryStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.inventory.isUseableByPlayer(entityPlayer);
    }

    public void openInventory() {
        this.inventory.openInventory();
    }

    public void closeInventory() {
        this.inventory.closeInventory();
    }

    public boolean isItemValidForSlot(int index, ItemStack itemStack) {
        return this.inventory.isItemValidForSlot(index, itemStack);
    }

    public double getOfferedEnergy() {
        return Math.min(this.energyStored, this.energyGeneratedPerTick * 2.0);
    }

    public void drawEnergy(double amount) {
        this.energyStored -= amount;
    }

    public int getSourceTier() {
        return 0;
    }

    public boolean emitsEnergyTo(TileEntity tileEntity, ForgeDirection forgeDirection) {
        return true;
    }

    public InventorySolarPanel getInventory() {
        return this.inventory;
    }

    public int getTicksCounter() {
        return this.ticksCounter;
    }

    public int getParticleCollectorId() {
        return this.particleCollectorId;
    }

    public double getEnergyGeneratedPerTick() {
        return this.energyGeneratedPerTick;
    }

    public double getEnergyStored() {
        return this.energyStored;
    }

    public double getEnergyCapacity() {
        return this.energyCapacity;
    }

    public boolean isForceUpdate() {
        return this.forceUpdate;
    }

    public boolean isHasSun() {
        return this.hasSun;
    }

    public boolean isAddedToEnet() {
        return this.addedToEnet;
    }
}

