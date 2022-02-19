/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package net.divinerpg.blocks.vethea.container.tileentity;

import net.divinerpg.blocks.vethea.BlockDreamLamp;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDreamLamp
extends TileEntity
implements IInventory {
    public ItemStack slot;

    public void updateEntity() {
        super.updateEntity();
        if (this.slot != null && this.slot.getItem() == VetheaItems.acid && this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == VetheaBlocks.dreamLamp) {
            BlockDreamLamp.updateState(true, this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
        } else if ((this.slot == null || this.slot != null && this.slot.getItem() != VetheaItems.acid) && this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == VetheaBlocks.dreamLampOn) {
            BlockDreamLamp.updateState(false, this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int slotIn) {
        return this.slot;
    }

    public ItemStack decrStackSize(int index, int count) {
        if (this.slot != null) {
            if (this.slot.stackSize <= count) {
                ItemStack itemstack = this.slot;
                this.slot = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.slot.splitStack(count);
            if (this.slot.stackSize == 0) {
                this.slot = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        return this.slot;
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        this.slot = stack;
    }

    public String getInventoryName() {
        return "Dream Lamp";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return stack != null && stack.getItem() == VetheaItems.acid;
    }

    public Packet getDescriptionPacket() {
        if (this.slot != null) {
            return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, this.slot.writeToNBT(new NBTTagCompound()));
        }
        return null;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        this.slot = ItemStack.loadItemStackFromNBT((NBTTagCompound)packet.func_148857_g());
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (this.slot != null) {
            tag.setTag("Slot", (NBTBase)this.slot.writeToNBT(new NBTTagCompound()));
        }
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("Slot")) {
            this.slot = ItemStack.loadItemStackFromNBT((NBTTagCompound)((NBTTagCompound)tag.getTag("Slot")));
        }
    }
}

