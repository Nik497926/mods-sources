/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import com.meteor.extrabotany.common.item.ItemManaReader;
import com.meteor.extrabotany.common.item.ItemTest;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.recipe.ModEAltarRecipe;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.justagod.cutter.invoke.Invoke;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class TileEAltar
extends TileEntity
implements ISidedInventory {
    private ItemStack[] chestContents = new ItemStack[72];
    private int playersUsing;
    private TileEntity $tile = null;
    private String recipe = "";
    private int needEfir = 0;
    private int tick = 0;
    private int[] withConnect = new int[0];

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    public int getSizeInventory() {
        return 5;
    }

    public ItemStack getStackInSlot(int index) {
        return this.chestContents[index];
    }

    public ItemStack decrStackSize(int index, int count) {
        if (this.chestContents[index] != null) {
            if (this.chestContents[index].stackSize <= count) {
                ItemStack itemstack = this.chestContents[index];
                this.chestContents[index] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[index].splitStack(count);
            if (this.chestContents[index].stackSize == 0) {
                this.chestContents[index] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.chestContents[index] != null) {
            ItemStack itemstack = this.chestContents[index];
            this.chestContents[index] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int index, ItemStack item) {
        this.chestContents[index] = item;
        if (item != null && item.stackSize > this.getInventoryStackLimit()) {
            item.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    public String getInventoryName() {
        return null;
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && p_70300_1_.getDistanceSq((double) this.xCoord + 0.5, (double) this.yCoord + 0.5, (double) this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {
        this.triggerPlayerUsageChange(1);
    }

    public void closeInventory() {
        this.triggerPlayerUsageChange(-1);
    }

    private void triggerPlayerUsageChange(int change) {
        if (this.worldObj != null) {
            this.playersUsing += change;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, super.getBlockType(), 1, this.playersUsing);
        }
    }

    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
        if (nbt.hasKey("link")) {
            NBTTagCompound _n = nbt.getCompoundTag("link");
            int xc = _n.getInteger("x");
            int yc = _n.getInteger("y");
            int zc = _n.getInteger("z");
            this.withConnect = new int[]{xc, yc, zc};
        }
        this.recipe = nbt.hasKey("recipe") ? nbt.getString("recipe") : "";
        this.needEfir = nbt.hasKey("needEfir") ? nbt.getInteger("needEfir") : 0;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
        }
        nbt.setTag("Items", nbttaglist);
        if (this.$tile != null) {
            NBTTagCompound _n = new NBTTagCompound();
            _n.setInteger("x", this.$tile.xCoord);
            _n.setInteger("y", this.$tile.yCoord);
            _n.setInteger("z", this.$tile.zCoord);
            nbt.setTag("link", _n);
        }
        if (!this.recipe.isEmpty()) {
            nbt.setString("recipe", this.recipe);
        }
        nbt.setInteger("needEfir", this.needEfir);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player) {
        ItemStack equipped = player.getCurrentEquippedItem();
        if (equipped != null) {
            if (equipped.getItem() instanceof ItemTest) {
                this.needEfir = 10;
                return true;
            }
            if (equipped.getItem() instanceof ItemManaReader) {
                return true;
            }
            ItemStack in = equipped.copy();
            in.stackSize = 1;
            ItemStack out = equipped.copy();
            if (out.stackSize == 1) {
                out = null;
            } else {
                --out.stackSize;
            }
            if (this.setItem(in)) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
                Invoke.server(() -> {});
                return true;
            }
            return false;
        }
        ItemStack out = this.getStack();
        if (out == null) {
            return false;
        }
        Invoke.server(() -> {});
        player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
        return true;
    }

    private ItemStack getStack() {
        for (int i = 0; i < 5; ++i) {
            ItemStack _st = this.getStackInSlot(i);
            if (_st == null) continue;
            this.setInventorySlotContents(i, null);
            return _st;
        }
        return null;
    }

    private boolean setItem(ItemStack stack) {
        for (int i = 0; i < 4; ++i) {
            if (this.getStackInSlot(i) != null) continue;
            this.setInventorySlotContents(i, stack);
            return true;
        }
        return false;
    }

    private int getNeedEfir() {
        return this.needEfir;
    }

    public void updateEntity() {
        TileEntity te;
        super.updateEntity();
        if (this.withConnect.length == 3) {
            te = this.worldObj.getTileEntity(this.withConnect[0], this.withConnect[1], this.withConnect[2]);
            if (te != null) {
                this.$tile = te;
            }
            this.withConnect = new int[0];
        }
        if (this.worldObj.isRemote) {
            return;
        }
        if (this.$tile != null && !((te = this.worldObj.getTileEntity(this.$tile.xCoord, this.$tile.yCoord, this.$tile.zCoord)) instanceof TileBlockPoolEfir)) {
            this.$tile = null;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
        if (this.getStackInSlot(4) == null && this.$tile != null && this.$tile instanceof TileBlockPoolEfir && !this.recipe.isEmpty()) {
            if (this.needEfir > 0 && ((TileBlockPoolEfir)this.$tile).getEfir() > 5) {
                if (this.tick < 10) {
                    ++this.tick;
                    return;
                }
                this.tick = 0;
                ((TileBlockPoolEfir)this.$tile).takeEfir(5);
                this.needEfir -= 5;
                SimpleNetworkWrapper packhand = PacketHandler.INSTANCE;
                PacketFXEssentiaSource essentiaSource = new PacketFXEssentiaSource(this.xCoord, this.yCoord, this.zCoord, (byte)(this.xCoord - this.$tile.xCoord), (byte)(this.yCoord - this.$tile.yCoord), (byte)(this.zCoord - this.$tile.zCoord), Aspect.LIFE.getColor());
                packhand.sendToAllAround(essentiaSource, new NetworkRegistry.TargetPoint(this.getWorldObj().provider.dimensionId, this.xCoord, (double)this.yCoord + 0.5, this.zCoord, 32.0));
            } else if (this.needEfir <= 0) {
                ItemStack out = ModEAltarRecipe.instance.getOutput(this.recipe);
                NBTTagList ench = null;
                for (int i = 0; i < 4; ++i) {
                    NBTTagList _n;
                    if (this.getStackInSlot(i) != null && this.getStackInSlot(i).getItem() instanceof ItemAwakeOGArmor && (_n = ItemNBTHelper.getList(this.getStackInSlot(i), "ench", 10, true)) != null) {
                        ench = _n;
                    }
                    this.setInventorySlotContents(i, null);
                }
                if (ench != null) {
                    ItemNBTHelper.setList(out, "ench", ench);
                }
                this.setInventorySlotContents(4, out);
                this.recipe = "";
            }
        }
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    public int getEfir() {
        return this.needEfir;
    }

    public void setLink(TileEntity te) {
        if (te != null && te instanceof TileBlockPoolEfir) {
            this.$tile = te;
        }
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    public String getRecipe() {
        return this.recipe;
    }

    public boolean hasLink() {
        return this.$tile != null && this.$tile instanceof TileBlockPoolEfir;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
}

