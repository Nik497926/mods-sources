package mireille.common.tileentity;

import net.minecraft.tileentity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.player.*;

public class TileSkinWorkbench extends TileEntity implements IInventory
{
    public ItemStack[] inventory;
    private String SkinWorkbenchName;
    
    public TileSkinWorkbench() {
        this.inventory = new ItemStack[3];
        this.SkinWorkbenchName = "SkinWorkbench";
    }
    
    public Packet getDescriptionPacket() {
        final NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return (Packet)new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, super.blockMetadata, compound);
    }
    
    @SideOnly(Side.CLIENT)
    public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readFromNBT(pkt.func_148857_g());
    }
    
    public int getSizeInventory() {
        return this.inventory.length;
    }
    
    public ItemStack getStackInSlot(final int slot) {
        return this.inventory[slot];
    }
    
    public ItemStack decrStackSize(final int slot, final int count) {
        if (this.inventory[slot] != null) {
            ItemStack stack;
            if (this.inventory[slot].stackSize <= count) {
                stack = this.inventory[slot];
                this.inventory[slot] = null;
            }
            else {
                stack = this.inventory[slot].splitStack(count);
                if (this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }
            }
            this.markDirty();
            return stack;
        }
        return null;
    }
    
    public ItemStack getStackInSlotOnClosing(final int slot) {
        if (this.inventory[slot] != null) {
            final ItemStack itemstack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        }
        return null;
    }
    
    public void setInventorySlotContents(final int slot, final ItemStack stack) {
        this.inventory[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }
    
    public String getInventoryName() {
        return null;
    }
    
    public boolean hasCustomInventoryName() {
        return false;
    }
    
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        final NBTTagList tagList = compound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            final NBTTagCompound slot = tagList.getCompoundTagAt(i);
            final int pos = slot.getByte("Slot");
            if (pos < this.inventory.length) {
                this.inventory[pos] = ItemStack.loadItemStackFromNBT(slot);
            }
        }
    }
    
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        final NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                final NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(slot);
                nbttaglist.appendTag((NBTBase)slot);
            }
        }
        compound.setTag("Items", (NBTBase)nbttaglist);
    }
    
    public int getInventoryStackLimit() {
        return 1;
    }
    
    public boolean isUseableByPlayer(final EntityPlayer player) {
        return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) == this;
    }
    
    public void openInventory() {
    }
    
    public void closeInventory() {
    }
    
    public boolean isItemValidForSlot(final int index, final ItemStack stack) {
        return false;
    }
    
    public boolean canUpdate() {
        return true;
    }
}
