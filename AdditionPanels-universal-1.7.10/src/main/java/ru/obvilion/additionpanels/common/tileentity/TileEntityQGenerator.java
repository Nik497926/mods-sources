package ru.obvilion.additionpanels.common.tileentity;

import advsolar.network.IHasButton;
import com.google.common.collect.Maps;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import ru.obvilion.additionpanels.common.container.ContainerQGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import ic2.api.Direction;

public class TileEntityQGenerator extends TileEntity implements IEnergyTile, IWrenchable, IEnergySource, IHasButton {

    public static HashMap<String, Integer> generators = Maps.newHashMap();

    public static Random randomizer = new Random();
    public int ticker;
    private int machineTire;
    public int production;
    private short facing = 2;
    public boolean addedToEnergyNet;
    public boolean active;
    public boolean lastState;
    public int maxPacketSize;
    private int lastX;
    private int lastY;
    private int lastZ;

    private int maxGeneration;
    private String name;
    public boolean loaded = false;
    private static List<String> fields = Arrays.asList();

    public TileEntityQGenerator() {

        this.ticker = randomizer.nextInt(this.tickRate());
        this.lastX = this.xCoord;
        this.lastY = this.yCoord;
        this.lastZ = this.zCoord;
        this.lastState = false;
        this.machineTire = 2147483647;
    }

    public TileEntityQGenerator(String name, int maxGeneration) {
        this.ticker = randomizer.nextInt(this.tickRate());
        this.lastX = this.xCoord;
        this.lastY = this.yCoord;
        this.lastZ = this.zCoord;
        this.lastState = false;
        this.machineTire = 2147483647;
        this.production = maxGeneration;
        this.maxPacketSize = maxGeneration;
        this.maxGeneration = maxGeneration;
        this.name = name;
    }

    public void validate() {
        super.validate();
        if (!this.isInvalid() && this.worldObj.blockExists(this.xCoord, this.yCoord, this.zCoord)) {
            this.onLoaded();
        }
    }

    public void invalidate() {
        if (this.loaded) {
            this.onUnloaded();
        }

        super.invalidate();
    }

    public void onLoaded() {
        if (!this.worldObj.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            this.addedToEnergyNet = true;
        }

        this.loaded = true;
    }

    public void onChunkUnload() {
        if (this.loaded) {
            this.onUnloaded();
        }

        super.onChunkUnload();
    }

    public void onUnloaded() {
        if (this.addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            this.addedToEnergyNet = false;
        }

        this.loaded = false;
    }

    public boolean canUpdate() {
        return true;
    }

    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            if (!this.addedToEnergyNet) {
                this.onLoaded();
            }

            if (this.lastX != this.xCoord || this.lastZ != this.zCoord || this.lastY != this.yCoord) {
                this.lastX = this.xCoord;
                this.lastY = this.yCoord;
                this.lastZ = this.zCoord;
                this.onUnloaded();
                this.onLoaded();
            }

        }
    }

    public boolean getActive() {
        this.active = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
        if (this.active != this.lastState) {
            this.lastState = this.active;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }

        return this.active;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.production = nbttagcompound.getInteger("production");
        this.maxPacketSize = nbttagcompound.getInteger("maxPacketSize");
        this.name =  nbttagcompound.getString("generator");
        if (maxGeneration == 0){
            if (generators.containsKey(name)) {
                this.maxGeneration = generators.get(name);;
                this.name = name;
            }
        }
        this.lastX = nbttagcompound.getInteger("lastX");
        this.lastY = nbttagcompound.getInteger("lastY");
        this.lastZ = nbttagcompound.getInteger("lastZ");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        new NBTTagList();
        nbttagcompound.setInteger("production", this.production);
        nbttagcompound.setInteger("maxPacketSize", this.maxPacketSize);
        nbttagcompound.setInteger("lastX", this.lastX);
        nbttagcompound.setInteger("lastY", this.lastY);
        nbttagcompound.setInteger("lastZ", this.lastZ);
        nbttagcompound.setString("generator", this.name);
    }

    public boolean isAddedToEnergyNet() {
        return this.addedToEnergyNet;
    }

    public boolean emitsEnergyTo(TileEntity receiver, Direction direction) {
        return true;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
            return false;
        } else {
            return entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
        }
    }

    public int tickRate() {
        return 128;
    }

    public short getFacing() {
        return this.facing;
    }

    public void setFacing(short facing) {
        this.facing = facing;
    }

    public boolean wrenchCanSetFacing(EntityPlayer entityplayer, int i) {
        return false;
    }

    public boolean wrenchCanRemove(EntityPlayer entityplayer) {
        return true;
    }

    public float getWrenchDropRate() {
        return 1.0F;
    }

    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }

    public List<String> getNetworkedFields() {
        return fields;
    }

    public Container getGuiContainer(InventoryPlayer inventoryplayer) {
        return new ContainerQGenerator(inventoryplayer, this);
    }

    public String getInvName() {
        return "QuantumGenerator";
    }

    public void changeProductionOutput(int value) {
        this.production += value;
        if (this.production > maxGeneration) {
            this.production = maxGeneration;
        }
        if (this.production < 1) {
            this.production = 1;
        }

    }

    public void changeMaxPacketSize(int value) {
        this.maxPacketSize += value;
        if (this.maxPacketSize > maxGeneration) {
            this.maxPacketSize = maxGeneration;
        }
        if (this.maxPacketSize < 1) {
            this.maxPacketSize = 1;
        }

    }

    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
        return true;
    }

    public double getOfferedEnergy() {
        this.getActive();
        return !this.active ? (double)this.production : 0.0D;
    }

    public void drawEnergy(double amount) {
    }

    public void handleButtonClick(int event) {
        switch(event) {
            case 1:
                this.changeProductionOutput(-100);
                break;
            case 2:
                this.changeProductionOutput(-10);
                break;
            case 3:
                this.changeProductionOutput(-1);
                break;
            case 4:
                this.changeProductionOutput(1);
                break;
            case 5:
                this.changeProductionOutput(10);
                break;
            case 6:
                this.changeProductionOutput(100);
                break;
            case 7:
                this.changeMaxPacketSize(-100);
                break;
            case 8:
                this.changeMaxPacketSize(-10);
                break;
            case 9:
                this.changeMaxPacketSize(-1);
                break;
            case 10:
                this.changeMaxPacketSize(1);
                break;
            case 11:
                this.changeMaxPacketSize(10);
                break;
            case 12:
                this.changeMaxPacketSize(100);
                break;
            case 101:
                this.changeProductionOutput(-1000);
                break;
            case 102:
                this.changeProductionOutput(-100);
                break;
            case 103:
                this.changeProductionOutput(-10);
                break;
            case 104:
                this.changeProductionOutput(10);
                break;
            case 105:
                this.changeProductionOutput(100);
                break;
            case 106:
                this.changeProductionOutput(1000);
                break;
            case 107:
                this.changeMaxPacketSize(-1000);
                break;
            case 108:
                this.changeMaxPacketSize(-100);
                break;
            case 109:
                this.changeMaxPacketSize(-10);
                break;
            case 110:
                this.changeMaxPacketSize(10);
                break;
            case 111:
                this.changeMaxPacketSize(100);
                break;
            case 112:
                this.changeMaxPacketSize(1000);
        }

    }

    public int getSourceTier() {
        return this.machineTire;
    }
}



//public class TileEntityQGenerator extends TileEntity implements IEnergyTile, IWrenchable, IEnergySource, IInventory, INetworkDataProvider, INetworkUpdateListener {
//    public static HashMap<String, TileEntitySolarPanel.PanelData> generators = Maps.newHashMap();
//    public static Random randomizer = new Random();
//    private static final List<String> fields = Arrays.asList();
//    public int ticker;
//    public int generating;
//    public int genDay;
//    public int genNight;
//    public boolean initialized;
//    public boolean sunIsUp;
//    public boolean addedToEnergyNet;
//    public int storage;
//    public String panelName;
//    public int production;
//    public int maxStorage;
//    public boolean loaded;
//    private short facing = 2;
//    private final int machineTire;
//    private ItemStack[] chargeSlots;
//    private int lastX;
//    private int lastY;
//    private int lastZ;
//
//    public TileEntityQGenerator() {
//        this.storage = 0;
//        this.chargeSlots = new ItemStack[4];
//        this.ticker = randomizer.nextInt(this.tickRate());
//        this.lastX = this.xCoord;
//        this.lastY = this.yCoord;
//        this.lastZ = this.zCoord;
//        this.machineTire = 2147483647;
//    }
//
//
//    public TileEntityQGenerator(TileEntitySolarPanel.PanelData data) {
//        this.genDay = data.getGenDay();
//        this.genNight = data.getGenNight();
//        this.panelName = data.getPanelName();
//        this.maxStorage = data.getMaxStorage();
//        this.production = data.getOutputEu();
//        this.storage = 0;
//        this.chargeSlots = new ItemStack[4];
//        this.ticker = randomizer.nextInt(this.tickRate());
//        this.lastX = this.xCoord;
//        this.lastY = this.yCoord;
//        this.lastZ = this.zCoord;
//        this.machineTire = 2147483647;
//    }
//
//    public void validate() {
//        super.validate();
//        if (!this.isInvalid() && this.worldObj.blockExists(this.xCoord, this.yCoord, this.zCoord)) {
//            this.onLoaded();
//        }
//    }
//
//    public void invalidate() {
//        if (this.loaded) {
//            this.onUnloaded();
//        }
//
//        super.invalidate();
//    }
//
//    public void onLoaded() {
//        if (!this.worldObj.isRemote) {
//            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
//            this.addedToEnergyNet = true;
//        }
//
//        this.loaded = true;
//    }
//
//    public void onChunkUnload() {
//        if (this.loaded) {
//            this.onUnloaded();
//        }
//
//        super.onChunkUnload();
//    }
//
//    public void onUnloaded() {
//        if (!this.worldObj.isRemote && this.addedToEnergyNet) {
//            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
//            this.addedToEnergyNet = false;
//        }
//
//        this.loaded = false;
//    }
//
//    public void intialize() {
//        this.updateVisibility();
//        this.initialized = true;
//        if (!this.addedToEnergyNet) {
//            this.onLoaded();
//        }
//
//    }
//
//    public void updateEntity() {
//        super.updateEntity();
//        if (!this.initialized && this.worldObj != null) {
//            this.intialize();
//        }
//
//        if (!this.worldObj.isRemote) {
//            if (this.lastX != this.xCoord || this.lastZ != this.zCoord || this.lastY != this.yCoord) {
//                this.lastX = this.xCoord;
//                this.lastY = this.yCoord;
//                this.lastZ = this.zCoord;
//                this.onUnloaded();
//                this.intialize();
//            }
//
//            this.gainFuel();
//            if (this.generating > 0) {
//                if (this.storage + this.generating <= this.maxStorage) {
//                    this.storage += this.generating;
//                } else {
//                    this.storage = this.maxStorage;
//                }
//            }
//
//            boolean needInvUpdate = false;
//            double sentPacket;
//
//            for (int i = 0; i < this.chargeSlots.length; ++i) {
//                if (this.chargeSlots[i] != null && this.chargeSlots[i].getItem() instanceof IElectricItem && this.storage > 0) {
//                    sentPacket = ElectricItem.manager.charge(this.chargeSlots[i], this.storage, 2147483647, false, false);
//                    if (sentPacket > 0.0D) {
//                        needInvUpdate = true;
//                    }
//
//                    this.storage = (int) ((double) this.storage - sentPacket);
//                }
//            }
//
//            if (needInvUpdate) {
//                super.markDirty();
//            }
//
//        }
//    }
//
//    public int gainFuel() {
//        if (this.ticker++ % this.tickRate() == 0) {
//            this.updateVisibility();
//        }
//
//        if (this.sunIsUp) {
//            this.generating = this.genDay;
//        } else {
//            this.generating = this.genNight;
//        }
//        return this.generating;
//    }
//
//    public void updateVisibility() {
//        this.sunIsUp = this.worldObj.isDaytime();
//
//
//    }
//
//    public void readFromNBT(NBTTagCompound nbttagcompound) {
//        super.readFromNBT(nbttagcompound);
//        this.storage = nbttagcompound.getInteger("storage");
//        this.lastX = nbttagcompound.getInteger("lastX");
//        this.lastY = nbttagcompound.getInteger("lastY");
//        this.lastZ = nbttagcompound.getInteger("lastZ");
//        if (genDay == 0){
//            if (generators.containsKey(panelName)) {
//                TileEntitySolarPanel.PanelData panelData = generators.get(panelName);
//                this.genDay = panelData.getGenDay();
//                this.genNight = panelData.getGenNight();
//                this.panelName = panelData.getPanelName();
//                this.maxStorage = panelData.getMaxStorage();
//                this.production = panelData.getOutputEu();
//            }
//        }
//
//        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
//        this.chargeSlots = new ItemStack[this.getSizeInventory()];
//
//        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
//            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
//            int j = nbttagcompound1.getByte("Slot") & 255;
//            if (j >= 0 && j < this.chargeSlots.length) {
//                this.chargeSlots[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
//            }
//        }
//
//    }
//
//    public void writeToNBT(NBTTagCompound nbttagcompound) {
//        super.writeToNBT(nbttagcompound);
//        NBTTagList nbttaglist = new NBTTagList();
//        nbttagcompound.setInteger("storage", this.storage);
//        nbttagcompound.setInteger("lastX", this.lastX);
//        nbttagcompound.setInteger("lastY", this.lastY);
//        nbttagcompound.setInteger("lastZ", this.lastZ);
//        nbttagcompound.setString("panelName", this.panelName);
//        for (int i = 0; i < this.chargeSlots.length; ++i) {
//            if (this.chargeSlots[i] != null) {
//                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
//                nbttagcompound1.setByte("Slot", (byte) i);
//                this.chargeSlots[i].writeToNBT(nbttagcompound1);
//                nbttaglist.appendTag(nbttagcompound1);
//            }
//        }
//
//        nbttagcompound.setTag("Items", nbttaglist);
//    }
//
//
//    public int gaugeEnergyScaled(int i) {
//        return this.storage * i / this.maxStorage;
//    }
//
//
//    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
//        return entityplayer.getDistance((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
//    }
//
//    public void openInventory() {
//    }
//
//    public void closeInventory() {
//    }
//
//    public int tickRate() {
//        return 128;
//    }
//
//    public short getFacing() {
//        return this.facing;
//    }
//
//    public void setFacing(short facing) {
//        this.facing = facing;
//    }
//
//    public boolean wrenchCanSetFacing(EntityPlayer entityplayer, int i) {
//        return false;
//    }
//
//    public boolean wrenchCanRemove(EntityPlayer entityplayer) {
//        return true;
//    }
//
//    public float getWrenchDropRate() {
//        return 1.0F;
//    }
//
//    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
//        return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
//    }
//
//    public int getSizeInventory() {
//        return 4;
//    }
//
//    public ItemStack getStackInSlot(int i) {
//        return this.chargeSlots[i];
//    }
//
//    public ItemStack decrStackSize(int i, int j) {
//        if (this.chargeSlots[i] != null) {
//            ItemStack itemstack1;
//            if (this.chargeSlots[i].stackSize <= j) {
//                itemstack1 = this.chargeSlots[i];
//                this.chargeSlots[i] = null;
//                return itemstack1;
//            } else {
//                itemstack1 = this.chargeSlots[i].splitStack(j);
//                if (this.chargeSlots[i].stackSize == 0) {
//                    this.chargeSlots[i] = null;
//                }
//
//                return itemstack1;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    public void setInventorySlotContents(int i, ItemStack itemstack) {
//        this.chargeSlots[i] = itemstack;
//        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
//            itemstack.stackSize = this.getInventoryStackLimit();
//        }
//
//    }
//
//    public String getInventoryName() {
//        return "Q Generator";
//    }
//
//    public boolean hasCustomInventoryName() {
//        return false;
//    }
//
//    public int getInventoryStackLimit() {
//        return 64;
//    }
//
//    public Container getGuiContainer(InventoryPlayer inventoryplayer) {
//        return new ContainerQGenerator(inventoryplayer, this);
//    }
//
//    public ItemStack getStackInSlotOnClosing(int var1) {
//        if (this.chargeSlots[var1] != null) {
//            ItemStack var2 = this.chargeSlots[var1];
//            this.chargeSlots[var1] = null;
//            return var2;
//        } else {
//            return null;
//        }
//    }
//
//    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
//        return true;
//    }
//
//    public void onNetworkUpdate(String field) {
//    }
//
//    public List<String> getNetworkedFields() {
//        return fields;
//    }
//
//    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
//        return true;
//    }
//
//    public double getOfferedEnergy() {
//        return Math.min(this.production, this.storage);
//    }
//
//    public void drawEnergy(double amount) {
//        this.storage = (int) ((double) this.storage - amount);
//    }
//
//    public int getSourceTier() {
//        return this.machineTire;
//    }
//}
