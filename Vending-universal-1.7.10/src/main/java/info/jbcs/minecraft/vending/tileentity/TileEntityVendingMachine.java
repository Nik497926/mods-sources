/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.ironchest.BlockIronChest
 *  cpw.mods.ironchest.TileEntityIronChest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package info.jbcs.minecraft.vending.tileentity;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.TileEntityIronChest;
import info.jbcs.minecraft.vending.EconomyControl;
import info.jbcs.minecraft.vending.inventory.InventoryStatic;
import info.jbcs.minecraft.vending.tileentity.ClientTicks;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVendingMachine
extends TileEntity
implements IInventory,
ISidedInventory {
    public int mode;
    public String ownerName = "";
    public ItemStack[] sold = new ItemStack[]{null, null, null, null};
    public ItemStack[] bought = new ItemStack[]{null, null, null, null};
    public boolean advanced = false;
    public boolean infinite = false;
    public boolean eco = false;
    private static final int[] side0 = new int[0];
    public InventoryStatic inventory = new InventoryStatic(14, this){

        @Override
        public String getInventoryName() {
            return "Vending Machine";
        }

        @Override
        public void onInventoryChanged() {
            int i;
            if (TileEntityVendingMachine.this.worldObj == null) {
                return;
            }
            for (i = 0; i < TileEntityVendingMachine.this.getSoldItems().length; ++i) {
                if (ItemStack.areItemStacksEqual((ItemStack)TileEntityVendingMachine.this.sold[i], (ItemStack)TileEntityVendingMachine.this.getSoldItems()[i])) continue;
                TileEntityVendingMachine.this.sold[i] = TileEntityVendingMachine.this.getSoldItems()[i];
                if (TileEntityVendingMachine.this.sold[i] != null) {
                    TileEntityVendingMachine.this.sold[i] = TileEntityVendingMachine.this.sold[i].copy();
                }
                TileEntityVendingMachine.this.worldObj.markBlockForUpdate(TileEntityVendingMachine.this.xCoord, TileEntityVendingMachine.this.yCoord, TileEntityVendingMachine.this.zCoord);
            }
            for (i = 0; i < TileEntityVendingMachine.this.getBoughtItems().length; ++i) {
                if (ItemStack.areItemStacksEqual((ItemStack)TileEntityVendingMachine.this.sold[i], (ItemStack)TileEntityVendingMachine.this.getBoughtItems()[i])) continue;
                TileEntityVendingMachine.this.bought[i] = TileEntityVendingMachine.this.getBoughtItems()[i];
                if (TileEntityVendingMachine.this.bought[i] != null) {
                    TileEntityVendingMachine.this.bought[i] = TileEntityVendingMachine.this.bought[i].copy();
                }
                TileEntityVendingMachine.this.worldObj.markBlockForUpdate(TileEntityVendingMachine.this.xCoord, TileEntityVendingMachine.this.yCoord, TileEntityVendingMachine.this.zCoord);
            }
            this.markDirty();
        }

        public void markDirty() {
        }

        @Override
        public boolean isUseableByPlayer(EntityPlayer entityplayer) {
            if (TileEntityVendingMachine.this.worldObj.getTileEntity(TileEntityVendingMachine.this.xCoord, TileEntityVendingMachine.this.yCoord, TileEntityVendingMachine.this.zCoord) != TileEntityVendingMachine.this) {
                return false;
            }
            return entityplayer.getDistanceSq((double)TileEntityVendingMachine.this.xCoord + 0.5, (double)TileEntityVendingMachine.this.yCoord + 0.5, (double)TileEntityVendingMachine.this.zCoord + 0.5) <= 64.0;
        }
    };
    public int money = 0;
    public int soldPrice = 0;
    public int boughtPrice = 0;
    public int count;

    public int findCount(TileEntity source, ItemStack itemStack, boolean simulate) {
        TileEntity chestTile = this.worldObj.getTileEntity(source.xCoord, source.yCoord - 1, source.zCoord);
        if (chestTile == null || !(chestTile instanceof TileEntityIronChest)) {
            return 0;
        }
        TileEntityIronChest chest = (TileEntityIronChest)chestTile;
        int c = 0;             // getSizeInventory();
        for (int i = 0; i < chest.func_70302_i_(); ++i) {
                                       // getStackInSlot(i);
            ItemStack stackInSlot = chest.func_70301_a(i);
            if (stackInSlot == null) {
                return c;
            }
            if (stackInSlot.getItem() != itemStack.getItem() || stackInSlot.getItemDamage() != itemStack.getItemDamage()) continue;
            c += stackInSlot.stackSize;
        }
        return c;
    }

    public void updateEntity() {
        if (this.worldObj.isRemote && !this.infinite && ClientTicks.ticks == 20) {
            this.updateCount();
        }
    }

    public void updateCount() {
        int newCount = 0x7FFFFFFE;
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (ItemStack itemStack : this.getSoldItems()) {
            if (itemStack == null) continue;
            list.add(itemStack);
        }
        for (ItemStack stack : list) {
            int available = this.findCount(this, stack, true);
            newCount = Math.min(available, newCount);
        }
        this.count = newCount;
    }

    public int getCount() {
        if (this.infinite) {
            return -1;
        }
        return this.count;
    }

    public int getSizeInventory() {
        return this.inventory.getSizeInventory() + (this.advanced ? -1 : 0);
    }

    public ItemStack getStackInSlot(int i) {
        return this.inventory.getStackInSlot(i);
    }

    public ItemStack[] getSoldItems() {
        if (this.eco) {
            return new ItemStack[]{this.inventory.getStackInSlot(9), this.inventory.getStackInSlot(10), this.inventory.getStackInSlot(11), this.inventory.getStackInSlot(12)};
        }
        return new ItemStack[]{this.inventory.getStackInSlot(9)};
    }

    public ItemStack[] getBoughtItems() {
        return new ItemStack[]{this.inventory.getStackInSlot(this.eco ? 13 : 10)};
    }

    public void setBoughtItem(ItemStack stack) {
        this.boughtPrice = EconomyControl.getMinPrice(stack);
        this.inventory.setInventorySlotContents(this.eco ? 13 : 10, stack);
    }

    private boolean findAroundChestFit() {
        if (this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) instanceof BlockIronChest) {
            TileEntityIronChest tile = (TileEntityIronChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
                                              // getSizeInventory();
            for (int content = 0; content < tile.func_70302_i_(); ++content) {
                      // getStackInSlot(content)
                if (tile.func_70301_a(content) != null) continue;
                return true;
            }
        }
        return false;
    }

    public boolean doesStackFit(ItemStack itemstack) {
        return this.findAroundChestFit();
    }

    public ItemStack decrStackSize(int i, int j) {
        return this.inventory.decrStackSize(i, j);
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        if (this.advanced && i == 10 || this.advanced && this.eco && i == 13) {
            return;
        }
        this.inventory.setInventorySlotContents(i, itemstack);
    }

    public String getInventoryName() {
        return this.inventory.getInventoryName();
    }

    public int getInventoryStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.inventory.isUseableByPlayer(entityplayer);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.inventory.clear();
        this.inventory.readFromNBT(nbttagcompound);
        this.ownerName = nbttagcompound.getString("owner");
        this.advanced = nbttagcompound.getBoolean("advanced");
        this.infinite = nbttagcompound.getBoolean("infinite");
        this.eco = nbttagcompound.getBoolean("eco");
        this.money = nbttagcompound.getInteger("money");
        this.soldPrice = nbttagcompound.getInteger("soldPrice");
        this.boughtPrice = nbttagcompound.getInteger("boughtPrice");
        this.mode = nbttagcompound.getInteger("mode");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        this.inventory.writeToNBT(nbttagcompound);
        nbttagcompound.setString("owner", this.ownerName);
        nbttagcompound.setBoolean("advanced", this.advanced);
        nbttagcompound.setBoolean("infinite", this.infinite);
        nbttagcompound.setBoolean("eco", this.eco);
        nbttagcompound.setInteger("money", this.money);
        nbttagcompound.setInteger("soldPrice", EconomyControl.getMinPrice(this.getSoldItems()));
        nbttagcompound.setInteger("boughtPrice", EconomyControl.getMinPrice(this.getBoughtItems()[0]));
        nbttagcompound.setInteger("mode", this.mode);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
        this.updateCount();
    }

    public ItemStack getStackInSlotOnClosing(int var1) {
        return null;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return (this.eco || i != 100) && (!this.advanced || !this.eco || i != 13);
    }

    public boolean canInsertItem(int index, ItemStack stack, int par3) {
        return false;
    }

    public boolean canExtractItem(int index, ItemStack stack, int side) {
        return false;
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return side0;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        int newMoney = Math.max(0, money);
        if ((int)(Math.log10(newMoney) + 1.0) > 7) {
            newMoney = this.money;
        }
        this.money = newMoney;
    }
}

